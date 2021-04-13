package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Bebida;
import model.Comida;
import model.FinanceiroDAO;
import model.Mesa;
import model.MesaDAO;
import model.Financeiro;
import view.TelaFinanceiro;
import view.TelaMenuAdmin;

public class InputListenerFinanceiro implements ActionListener, ListSelectionListener {

	private TelaFinanceiro tela;
	private MesaDAO mesaDAO = new MesaDAO();
	private FinanceiroDAO financeiroDAO = new FinanceiroDAO();
	private String selectedMesa = new String();
	private String selectedPedido = new String();
	
	public InputListenerFinanceiro (TelaFinanceiro tela) {
		this.tela = tela;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand() == "VOLTAR") {
			voltar();
		}
		else if (event.getActionCommand() == "EXCLUIR") {
			excluir();
			update();
			selectedMesa = new String();
		}
		else if (event.getActionCommand() == "DESCONTO") {
			desconto();
			update();
			selectedMesa = new String();
		}
	}
	
	private void excluir() {		
		try {
			mesaDAO.conectar();
			ArrayList<Mesa> mesas = mesaDAO.downloadMesa();
			mesaDAO.desconectar();
			financeiroDAO.conectar();
			ArrayList<Financeiro> financas = financeiroDAO.downloadFinancas();
			financeiroDAO.desconectar();			
			for (Mesa mesa : mesas) {
				if (selectedMesa.equals(mesa.toString())) {
					for (Comida comida : mesa.getComidas()) {
//						mesa.removeComida(comida);
						mesaDAO.conectar();
						mesaDAO.removeComida(mesa, comida);
						mesaDAO.desconectar();					
					}
					for (Bebida bebida : mesa.getBebidas()) {
//						mesa.removeBebida(bebida);
						mesaDAO.conectar();
						mesaDAO.removeBebida(mesa, bebida);
						mesaDAO.desconectar();						
					}
					mesaDAO.conectar();
					mesaDAO.editMesa(mesa, mesa.getId());
					mesaDAO.desconectar();
					for (Financeiro financeiro : financas) {
						if (mesa.getFinanceiro() == financeiro.getId()) {
							mesaDAO.conectar();
							mesaDAO.excluirMesa(mesa);
							mesaDAO.desconectar();
							financeiroDAO.conectar();
							financeiroDAO.excluirFinanceiro(financeiro);
							financeiroDAO.desconectar();
						}
					}					
					break;
				}
//				this.tela.updateListPedidos(mesa);
			}
		} catch (SQLException e) {
			String message = "Erro ao conectar ao banco de dados";
			JOptionPane.showMessageDialog(tela, message, "ERRO BD", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
		}		
	}
	
	private void desconto() {		
		String message = "Entre com o percentual de desconto";
		message = JOptionPane.showInputDialog(tela, message, "DESCONTO", JOptionPane.QUESTION_MESSAGE);
		if (message != null) {
			try {
				int numero = Integer.valueOf(message);
				mesaDAO.conectar();
				ArrayList<Mesa> mesas = mesaDAO.downloadMesa();
				mesaDAO.desconectar();
				financeiroDAO.conectar();
				ArrayList<Financeiro> financas = financeiroDAO.downloadFinancas();
				financeiroDAO.desconectar();
				for (Mesa mesa : mesas) {
					if (selectedMesa.equals(mesa.toString())) {
//						System.out.println("Mesa " + mesa.getId() + ", financeiro " +mesa.getFinanceiro());
						for (Financeiro financeiro : financas) {
//							System.out.println("For financeiro");
							if (mesa.getFinanceiro() == financeiro.getId()) {
//								System.out.println("Financeiro");
								financeiro.desconto(numero);
//								System.out.println(String.valueOf(financeiro.getConta()));
								financeiroDAO.conectar();
								financeiroDAO.editFinanceiro(financeiro, financeiro.getId());
								financeiroDAO.desconectar();
								message = "Desconto aplicado!";
								JOptionPane.showMessageDialog(tela, message, "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
								break;
							}
						}
						break;
					}
				}
			} catch (NumberFormatException e) {
				message = "Valor invalido!";
				JOptionPane.showMessageDialog(tela, message, "INVALIDO", JOptionPane.ERROR_MESSAGE);
				//e.printStackTrace();
			} catch (SQLException e) {
				message = "Erro ao conectar ao banco de dados";
				JOptionPane.showMessageDialog(tela, message, "ERRO BD", JOptionPane.ERROR_MESSAGE);
				//e.printStackTrace();
			}
		}		
	}
	
	private void voltar() {
		try {
			tela.dispose();
			new TelaMenuAdmin().setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void update() {
		try {
			this.tela.updateListMesas();
			this.tela.updateListPedidos(null);
			SwingUtilities.updateComponentTreeUI(this.tela);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent event) {
		if (this.tela.getListMesas().getSelectedValue() != null) {
			selectedMesa = this.tela.getMesa();
			this.tela.getListMesas().clearSelection();
			try {
				mesaDAO.conectar();
				ArrayList<Mesa> mesas = mesaDAO.downloadMesa();
				mesaDAO.desconectar();
				for (Mesa mesa : mesas) {
					if (selectedMesa.equals(mesa.toString())) {
						this.tela.updateListPedidos(mesa);
						break;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (this.tela.getListPedidos().getSelectedValue() != null) {
			this.tela.getListPedidos().clearSelection();
		}
	}

}

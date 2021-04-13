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
import model.BebidaDAO;
import model.Comida;
import model.ComidaDAO;
import model.FinanceiroDAO;
import model.Mesa;
import model.MesaDAO;
import model.Financeiro;
import view.TelaAtendimento;
import view.TelaLogin;

public class InputListenerAtendimento implements ActionListener, ListSelectionListener {

	private TelaAtendimento tela;
	private String selectedMesa = new String();
	private String selectedComida = new String();
	private String selectedBebida = new String();
	private MesaDAO mesaDAO = new MesaDAO();
	private ComidaDAO comidaDAO = new ComidaDAO();
	private BebidaDAO bebidaDAO = new BebidaDAO();
	private FinanceiroDAO financeiroDAO = new FinanceiroDAO();
	
	public InputListenerAtendimento(TelaAtendimento tela) {
		this.tela = tela;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand() == "LOGOUT") {
			logout();
		}
		else if (event.getActionCommand() == "ADICIONAR") {
			adicionar();
			update();
		}
		else if (event.getActionCommand() == "FECHAR") {
			fechar();
			update();
		}
		else if (event.getActionCommand() == "NOVA") {
			nova();
			update();
		}

	}
	
	private void nova() {
		String message = "Entre com o numero da Mesa";
		message = JOptionPane.showInputDialog(tela, message, "NOVA MESA", JOptionPane.QUESTION_MESSAGE);
		if (message != null) {
			try {
				int numero = Integer.valueOf(message);
				mesaDAO.conectar();
				mesaDAO.insertMesa(new Mesa(0, numero, 0, false));
				mesaDAO.desconectar();
				message = "Mesa cadastrada!";
				JOptionPane.showMessageDialog(tela, message, "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
			} catch (NumberFormatException e) {
				message = "Numero invalido!";
				JOptionPane.showMessageDialog(tela, message, "INVALIDO", JOptionPane.ERROR_MESSAGE);
				//e.printStackTrace();
			} catch (SQLException e) {
				message = "Erro ao conectar ao banco de dados";
				JOptionPane.showMessageDialog(tela, message, "ERRO BD", JOptionPane.ERROR_MESSAGE);
				//e.printStackTrace();
			}
		}
	}

	private void fechar() {
		try {
			mesaDAO.conectar();
			ArrayList<Mesa> mesas = mesaDAO.downloadMesa();
			mesaDAO.desconectar();
			for (Mesa mesa : mesas) {
				if (selectedMesa.equals(mesa.toString())) {
					mesa.setFinalizada(true);
					mesa.setConta(0);
					Financeiro financeiro = new Financeiro(0, mesa.getConta());
					financeiroDAO.conectar();
					financeiroDAO.insertFinanceiro(financeiro);
					mesa.setFinanceiro(financeiroDAO.ultimoInserido());
					financeiroDAO.desconectar();
					mesaDAO.conectar();
					mesaDAO.editMesa(mesa, mesa.getId());
					mesaDAO.desconectar();
					break;
				}
			}
			String message = "Mesa fechada!";
			JOptionPane.showMessageDialog(tela, message, "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			String message = "Erro ao conectar ao banco de dados";
			JOptionPane.showMessageDialog(tela, message, "ERRO BD", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
	}

	private void adicionar() {
		try {
			mesaDAO.conectar();
			ArrayList<Mesa> mesas = mesaDAO.downloadMesa();
			mesaDAO.desconectar();
			for (Mesa mesa : mesas) {
				if (selectedMesa.equals(mesa.toString())) {
					if (selectedComida != null) {
						comidaDAO.conectar();
						ArrayList<Comida> comidas = comidaDAO.downloadComidas();
						comidaDAO.desconectar();
						for (Comida comida : comidas) {
							if (selectedComida.equals(comida.toString())) {
								mesa.addComida(comida);
								mesaDAO.conectar();
								mesaDAO.addComida(mesa, comida);
								mesaDAO.desconectar();
								String message = "Comida adicionada!";
								JOptionPane.showMessageDialog(tela, message, "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
								break;
							}
						}
					}
					if (selectedBebida != null) {
						bebidaDAO.conectar();
						ArrayList<Bebida> bebidas = bebidaDAO.downloadBebidas();
						bebidaDAO.desconectar();
						for (Bebida bebida : bebidas) {
							if (selectedBebida.equals(bebida.toString())) {
								mesa.addBebida(bebida);
								mesaDAO.conectar();
								mesaDAO.addBebida(mesa, bebida);
								mesaDAO.desconectar();
								String message = "Bebida adicionada!";
								JOptionPane.showMessageDialog(tela, message, "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
								break;
							}
						}
					}
						
						mesaDAO.conectar();
						mesaDAO.editMesa(mesa, mesa.getId());
						mesaDAO.desconectar();
				}
			}
		} catch (SQLException e) {
			String message = "Erro ao conectar ao banco de dados";
			JOptionPane.showMessageDialog(tela, message, "ERRO BD", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	private void logout() {
		try {
			//Setar o usuario atual para null
			tela.dispose();
			new TelaLogin().setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void update() {
		try {
			this.tela.updateListMesas();
			this.tela.updateListComidas();
			this.tela.updateListBebidas();
			SwingUtilities.updateComponentTreeUI(this.tela);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (this.tela.getListMesas().getSelectedValue() != null) {
			selectedMesa = this.tela.getListMesas().getSelectedValue().toString();
			this.tela.getListMesas().clearSelection();
		}
		if (this.tela.getListComidas().getSelectedValue() != null) {
			selectedComida = this.tela.getListComidas().getSelectedValue().toString();
			selectedBebida = null;
			this.tela.getListComidas().clearSelection();
			this.tela.getListBebidas().clearSelection();
		}
		if (this.tela.getListBebidas().getSelectedValue() != null) {
			selectedBebida = this.tela.getListBebidas().getSelectedValue().toString();
			selectedComida = null;
			this.tela.getListComidas().clearSelection();
			this.tela.getListBebidas().clearSelection();
		}
	}

}

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
import model.Mesa;
import model.MesaDAO;
import model.Usuario;
import view.TelaEditarPedidos;
import view.TelaMenuAdmin;

public class InputListenerEditarPedidos implements ActionListener, ListSelectionListener {

	private TelaEditarPedidos tela;
	private MesaDAO mesaDAO = new MesaDAO();
	private String selectedMesa = new String();
	private String selectedPedido = new String();
	
	public InputListenerEditarPedidos (TelaEditarPedidos tela) {
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
			selectedPedido = new String();
		}
	}
	
	private void excluir() {		
		try {
			mesaDAO.conectar();
			ArrayList<Mesa> mesas = mesaDAO.downloadMesa();
			mesaDAO.desconectar();
			for (Mesa mesa : mesas) {
				if (selectedMesa.equals(mesa.toString())) {
					for (Comida comida : mesa.getComidas()) {
						if (selectedPedido.equals(comida.toString())) {
							mesa.removeComida(comida);
							mesaDAO.conectar();
							mesaDAO.removeComida(mesa, comida);
							mesaDAO.editMesa(mesa, mesa.getId());
							mesaDAO.desconectar();
							break;
						}
					}
					for (Bebida bebida : mesa.getBebidas()) {
						if (selectedPedido.equals(bebida.toString())) {
							mesa.removeBebida(bebida);
							mesaDAO.conectar();
							mesaDAO.removeBebida(mesa, bebida);
							mesaDAO.editMesa(mesa, mesa.getId());
							mesaDAO.desconectar();
							break;
						}						
					}
					break;
				}
				this.tela.updateListPedidos(mesa);
			}
		} catch (SQLException e) {
			String message = "Erro ao conectar ao banco de dados";
			JOptionPane.showMessageDialog(tela, message, "ERRO BD", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
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
			selectedPedido = this.tela.getPedido();
			this.tela.getListPedidos().clearSelection();
		}
	}
}

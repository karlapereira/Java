package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import model.Comida;
import model.Mesa;
import model.MesaDAO;
import model.ComidaDAO;
import view.TelaCozinha;
import view.TelaEditarUsuario;
import view.TelaLogin;

public class InputListenerCozinha implements ActionListener {

	private TelaCozinha tela;
	private MesaDAO mesaDAO = new MesaDAO();
	private ComidaDAO comidaDAO = new ComidaDAO();
	
	public InputListenerCozinha (TelaCozinha tela) {
		this.tela = tela;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand() == "LOGOUT") {
			logout();
		}
		else if (event.getActionCommand() == "PRONTO") {
			if (tela.getList().getSelectedValue() != null) {
				pronto(tela.getList().getSelectedValue().toString());
				update();
			}			
		}
		else if (event.getActionCommand() == "INDISPONIVEL") {
			if (tela.getList().getSelectedValue() != null) {
				indisponivel(tela.getList().getSelectedValue().toString());
				update();
			}
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
	
	private void pronto(String item) {
		try {
			mesaDAO.conectar();
			ArrayList<Mesa> mesas = mesaDAO.downloadMesa();
			mesaDAO.desconectar();
			
			for(Mesa mesa : mesas) {
				for(Comida comida : mesa.getComidas()) {
					if (item.equals(comida.toString())) {
						comida.setServida(true);
						mesaDAO.conectar();
						mesaDAO.checkComida(mesa, comida);
						mesaDAO.editMesa(mesa, mesa.getId());
						mesaDAO.desconectar();						
						break;
					}
				}
			}
		} catch (SQLException e) {
			String message = "Erro ao conectar ao banco de dados";
			JOptionPane.showMessageDialog(tela, message, "ERRO BD", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
		}
	}
	
	private void indisponivel(String item) {
		try {			
			comidaDAO.conectar();
			ArrayList<Comida> comidas = comidaDAO.downloadComidas();
			comidaDAO.desconectar();
			
			for(Comida comida : comidas) {
				if(item.equals(comida.toString())) {
					comida.setDisponivel(false);
					comidaDAO.conectar();
					comidaDAO.editComida(comida, comida.getId());
					comidaDAO.desconectar();
				}
			}
		} catch (SQLException e) {
			String message = "Erro ao conectar ao banco de dados";
			JOptionPane.showMessageDialog(tela, message, "ERRO BD", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
		}
	}
	
	private void update() {
		try {
			this.tela.updateList();
			SwingUtilities.updateComponentTreeUI(this.tela);
			//this.tela.dispose();
			//new Cozinha().setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import model.Bebida;
import model.Mesa;
import model.MesaDAO;
import model.BebidaDAO;
import view.TelaBar;
import view.TelaLogin;

public class InputListenerBar implements ActionListener {

	private TelaBar tela;
	private MesaDAO mesaDAO = new MesaDAO();
	private BebidaDAO bebidaDAO = new BebidaDAO();
	
	public InputListenerBar (TelaBar tela) {
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
				for(Bebida bebida : mesa.getBebidas()) {
					if (item.equals(bebida.toString())) {
						bebida.setServida(true);
						mesaDAO.conectar();
						mesaDAO.checkBebida(mesa, bebida);
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
			bebidaDAO.conectar();
			ArrayList<Bebida> bebidas = bebidaDAO.downloadBebidas();
			bebidaDAO.desconectar();
			
			for(Bebida bebida : bebidas) {
				if(item.equals(bebida.toString())) {
					bebida.setDisponivel(false);
					bebidaDAO.conectar();
					bebidaDAO.editBebida(bebida, bebida.getId());
					bebidaDAO.desconectar();
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
			//new Bar().setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

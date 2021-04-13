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
import view.TelaEditarBebida;
import view.TelaEditarComida;
import view.TelaEditarPedidos;
import view.TelaEditarUsuario;
import view.TelaFinanceiro;
import view.TelaLogin;
import view.TelaMenuAdmin;

public class InputListenerMenuAdmin implements ActionListener{

	private MesaDAO mesaDAO = new MesaDAO();
	private TelaMenuAdmin tela;
	
	public InputListenerMenuAdmin (TelaMenuAdmin tela) {
		this.tela = tela;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {		
		if (event.getActionCommand() == "MESAS") {
			mesas();
		}
		else if (event.getActionCommand() == "COZINHA") {
			cozinha();
		}
		else if (event.getActionCommand() == "BAR") {
			bar();
		}
		else if (event.getActionCommand() == "PEDIDOS") {
			pedidos();
		}
		else if (event.getActionCommand() == "COMIDAS") {
			comidas();
		}
		else if (event.getActionCommand() == "BEBIDAS") {
			bebidas();
		}
		else if (event.getActionCommand() == "USUARIOS") {
			usuarios();
		}
		else if (event.getActionCommand() == "FINANCEIRO") {
			financeiro();
		}
		else if (event.getActionCommand() == "LOGOUT") {
			logout();
		}

	}
	
	private void mesas() {
		tela.setBtnMesas(false);
		tela.setBtnCozinha(true);
		tela.setBtnBar(true);
		tela.updateListMesas();
		update();
	}

	private void cozinha() {
		tela.setBtnMesas(true);
		tela.setBtnCozinha(false);
		tela.setBtnBar(true);
		tela.updateListCozinha();
		update();
	}

	private void bar() {
		tela.setBtnMesas(true);
		tela.setBtnCozinha(true);
		tela.setBtnBar(false);
		tela.updateListBar();
		update();
	}

	private void pedidos() {
		try {
			tela.dispose();
			new TelaEditarPedidos().setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	private void comidas() {
		try {
			mesaDAO.conectar();
			ArrayList<Mesa> mesas = mesaDAO.downloadMesa();
			mesaDAO.desconectar();
			for(Mesa mesa : mesas) {
				if(!mesa.isFinalizada()) {					
					String message = "Existem contas em aberto! Não é possível alterar o cardápio!";
					JOptionPane.showMessageDialog(tela, message, "Impossível", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			}
		} catch (SQLException e) {
			String message = "Erro ao conectar ao banco de dados";
			JOptionPane.showMessageDialog(tela, message, "ERRO BD", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
		}
		
		try {
			tela.dispose();
			new TelaEditarComida().setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void bebidas() {
		try {
			mesaDAO.conectar();
			ArrayList<Mesa> mesas = mesaDAO.downloadMesa();
			mesaDAO.desconectar();
			for(Mesa mesa : mesas) {
				if(!mesa.isFinalizada()) {					
					String message = "Existem contas em aberto! Não é possível alterar o cardápio!";
					JOptionPane.showMessageDialog(tela, message, "Impossível", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			}
		} catch (SQLException e) {
			String message = "Erro ao conectar ao banco de dados";
			JOptionPane.showMessageDialog(tela, message, "ERRO BD", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
		}
		
		try {
			tela.dispose();
			new TelaEditarBebida().setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	private void usuarios() {
		try {
			tela.dispose();
			new TelaEditarUsuario().setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	private void financeiro() {
		try {
			tela.dispose();
			new TelaFinanceiro().setVisible(true);
		} catch (Exception e) {
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
			SwingUtilities.updateComponentTreeUI(this.tela);
			//tela.dispose();
			//new MenuAdmin().setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

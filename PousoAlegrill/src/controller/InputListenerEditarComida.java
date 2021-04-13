package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Comida;
import model.ComidaDAO;
import model.MesaDAO;
import view.TelaEditarComida;
import view.TelaMenuAdmin;

public class InputListenerEditarComida implements ActionListener, ListSelectionListener {

	private ComidaDAO comidaDAO = new ComidaDAO();
	private MesaDAO mesaDAO = new MesaDAO();
	
	private TelaEditarComida tela;
	
	public InputListenerEditarComida (TelaEditarComida tela) {
		this.tela = tela;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand() == "VOLTAR") {
			voltar();
		}
		else if (event.getActionCommand() == "SALVAR") {
			if (tela.getList().getSelectedValue() != null) {
				salvar();
				update();
			}
		}
		else if (event.getActionCommand() == "EXCLUIR") {
			if (tela.getList().getSelectedValue() != null) {
				excluir();
				update();
			}
		}
		else if (event.getActionCommand() == "ADICIONAR") {
			adicionar();
			update();
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
	
	private void salvar() {
		try {	
			comidaDAO.conectar();
			ArrayList<Comida> comidas = comidaDAO.downloadComidas();
			comidaDAO.desconectar();
			
			String nome = tela.getNome();
			String preco = tela.getPreco();
			Boolean disponivel = tela.getDisponivel();
			
			if(nome.equals("") || preco.equals("")) {
				String message = "Existem campos em branco";
				JOptionPane.showMessageDialog(tela, message, "Warning", JOptionPane.WARNING_MESSAGE);
			}
			else {
				Comida comida = new Comida(0, nome, Float.parseFloat(preco), disponivel);
				
				comidaDAO.conectar();
				comidaDAO.editComida(comida, comidas.get(tela.getList().getSelectedIndex()).getId());
				comidaDAO.desconectar();
				
				String message = "Comida alterada com sucesso!";
				JOptionPane.showMessageDialog(tela, message, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
			}			
		} catch (SQLException e) {
			String message = "Erro ao conectar ao banco de dados";
			JOptionPane.showMessageDialog(tela, message, "ERRO BD", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
		}
	}
	
	private void excluir() {
		try {
			comidaDAO.conectar();
			ArrayList<Comida> comidas = comidaDAO.downloadComidas();
			comidaDAO.desconectar();
			for(Comida comida : comidas) {
				if(comidas.indexOf(comida) == tela.getList().getSelectedIndex()) {					
					mesaDAO.conectar();
					mesaDAO.removeTodaComida(comida);
					mesaDAO.desconectar();
					comidaDAO.conectar();
					comidaDAO.excluirComida(comida);
					comidaDAO.desconectar();
					String message = "Comida excluida!";
					JOptionPane.showMessageDialog(tela, message, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		} catch (SQLException e) {
			String message = "Erro ao conectar ao banco de dados";
			JOptionPane.showMessageDialog(tela, message, "ERRO BD", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
		}
	}
	
	private void adicionar() {
		try {			
			String nome = tela.getNome();
			String preco = tela.getPreco();
			Boolean disponivel = tela.getDisponivel();
			
			if(nome.equals("") || preco.equals("")) {
				String message = "Existem campos em branco";
				JOptionPane.showMessageDialog(tela, message, "Warning", JOptionPane.WARNING_MESSAGE);
			}
			else {
				Comida novaComida = new Comida(0, nome, Float.parseFloat(preco), disponivel);
				
				comidaDAO.conectar();
				comidaDAO.insertComida(novaComida);
				comidaDAO.desconectar();
				
				String message = "Comida cadastrado com sucesso!";
				JOptionPane.showMessageDialog(tela, message, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		try {
			comidaDAO.conectar();
			ArrayList<Comida> comidas = comidaDAO.downloadComidas();
			comidaDAO.desconectar();
			
			for(Comida comida : comidas) {
				if(comidas.indexOf(comida) == tela.getList().getSelectedIndex()) {					
					tela.setNome(comida.getNome());
					tela.setPreco(comida.getPreco());
					tela.setDisponivel(comida.getDisponivel());
				}
			}
		} catch (SQLException e) {
			String message = "Erro ao conectar ao banco de dados";
			JOptionPane.showMessageDialog(tela, message, "ERRO BD", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
		}
	}

}

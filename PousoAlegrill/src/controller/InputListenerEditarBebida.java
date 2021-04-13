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
import model.MesaDAO;
import view.TelaEditarBebida;
import view.TelaMenuAdmin;

public class InputListenerEditarBebida implements ActionListener, ListSelectionListener {

	private BebidaDAO bebidaDAO = new BebidaDAO();
	private MesaDAO mesaDAO = new MesaDAO();
	
	private TelaEditarBebida tela;
	
	public InputListenerEditarBebida (TelaEditarBebida tela) {
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
			bebidaDAO.conectar();
			ArrayList<Bebida> bebidas = bebidaDAO.downloadBebidas();
			bebidaDAO.desconectar();
			
			String nome = tela.getNome();
			String preco = tela.getPreco();
			Boolean disponivel = tela.getDisponivel();
			
			if(nome.equals("") || preco.equals("")) {
				String message = "Existem campos em branco";
				JOptionPane.showMessageDialog(tela, message, "Warning", JOptionPane.WARNING_MESSAGE);
			}
			else {
				Bebida bebida = new Bebida(0, nome, Float.parseFloat(preco), disponivel);
				
				bebidaDAO.conectar();
				bebidaDAO.editBebida(bebida, bebidas.get(tela.getList().getSelectedIndex()).getId());
				bebidaDAO.desconectar();
				
				String message = "Bebida alterada com sucesso!";
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
			bebidaDAO.conectar();
			ArrayList<Bebida> bebidas = bebidaDAO.downloadBebidas();
			bebidaDAO.desconectar();
			for(Bebida bebida : bebidas) {
				if(bebidas.indexOf(bebida) == tela.getList().getSelectedIndex()) {					
					mesaDAO.conectar();
					mesaDAO.removeTodaBebida(bebida);
					mesaDAO.desconectar();
					bebidaDAO.conectar();
					bebidaDAO.excluirBebida(bebida);
					bebidaDAO.desconectar();
					String message = "Bebida excluida!";
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
				Bebida novaBebida = new Bebida(0, nome, Float.parseFloat(preco), disponivel);
				
				bebidaDAO.conectar();
				bebidaDAO.insertBebida(novaBebida);
				bebidaDAO.desconectar();
				
				String message = "Bebida cadastrado com sucesso!";
				JOptionPane.showMessageDialog(tela, message, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
				}
		} catch (SQLException e) {
			String message = "Erro ao conectar ao banco de dados!!!!";
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
			bebidaDAO.conectar();
			ArrayList<Bebida> bebidas = bebidaDAO.downloadBebidas();
			bebidaDAO.desconectar();
			
			for(Bebida bebida : bebidas) {
				if(bebidas.indexOf(bebida) == tela.getList().getSelectedIndex()) {					
					tela.setNome(bebida.getNome());
					tela.setPreco(bebida.getPreco());
					tela.setDisponivel(bebida.getDisponivel());
				}
			}
		} catch (SQLException e) {
			String message = "Erro ao conectar ao banco de dados";
			JOptionPane.showMessageDialog(tela, message, "ERRO BD", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
		}
	}

}

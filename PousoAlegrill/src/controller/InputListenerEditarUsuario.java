package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Usuario;
import model.UsuarioDAO;
import view.TelaEditarUsuario;
import view.TelaMenuAdmin;

public class InputListenerEditarUsuario implements ActionListener, ListSelectionListener {

	private TelaEditarUsuario tela;
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	
	public InputListenerEditarUsuario (TelaEditarUsuario tela) {
		this.tela = tela;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand() == "VOLTAR") {
			voltar();
		}
		else if (event.getActionCommand() == "SALVAR") {
			salvar();
			update();
		}
		else if (event.getActionCommand() == "EXCLUIR") {
			excluir();
			update();
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
			usuarioDAO.conectar();
			ArrayList<Usuario> usuarios = usuarioDAO.downloadUsers();
			usuarioDAO.desconectar();
			
			String nome = tela.getNome();
			String email = tela.getEmail();
			String senha = tela.getSenha();
			int categoria = tela.getCategoria();
			System.out.println(categoria);
			
			if(nome.equals("") || email.equals("") || senha.equals("")) {
				String message = "Existem campos em branco";
				JOptionPane.showMessageDialog(tela, message, "Warning", JOptionPane.WARNING_MESSAGE);
			}
			else {
				Usuario usuario = new Usuario(0, nome, email, senha, categoria);
				
				usuarioDAO.conectar();
				usuarioDAO.editUser(usuario, usuarios.get(tela.getList().getSelectedIndex()).getId());
				usuarioDAO.desconectar();
				
				String message = "Usuario alterado com sucesso!";
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
			usuarioDAO.conectar();
			ArrayList<Usuario> usuarios = usuarioDAO.downloadUsers();
			usuarioDAO.desconectar();
			
			for(Usuario usuario : usuarios) {
				if(usuarios.indexOf(usuario) == tela.getList().getSelectedIndex()) {					
					usuarioDAO.conectar();
					usuarioDAO.excluirUser(usuario);
					usuarioDAO.desconectar();
					
					String message = "Usuario excluido!";
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
			String email = tela.getEmail();
			String senha = tela.getSenha();
			int categoria = tela.getCategoria();
			
			if(nome.equals("") || email.equals("") || senha.equals("")) {
				String message = "Existem campos em branco";
				JOptionPane.showMessageDialog(tela, message, "Warning", JOptionPane.WARNING_MESSAGE);
			}
			else {
				Usuario novoUsuario = new Usuario(0, nome, email, senha, categoria);
				
				usuarioDAO.conectar();
				usuarioDAO.insertUser(novoUsuario);
				usuarioDAO.desconectar();
				
				String message = "Usuario cadastrado com sucesso!";
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
			//tela.dispose();
			//new EditarUsuario().setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent event) {
		try {
			usuarioDAO.conectar();
			ArrayList<Usuario> usuarios = usuarioDAO.downloadUsers();
			usuarioDAO.desconectar();
			
			for(Usuario usuario : usuarios) {
				if(usuarios.indexOf(usuario) == tela.getList().getSelectedIndex()) {					
					tela.setNome(usuario.getNome());
					tela.setEmail(usuario.getEmail());
					tela.setSenha(usuario.getSenha());
					tela.setCategoria(usuario.getFuncao());
				}
			}
		} catch (SQLException e) {
			String message = "Erro ao conectar ao banco de dados";
			JOptionPane.showMessageDialog(tela, message, "ERRO BD", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
		}
	}

}

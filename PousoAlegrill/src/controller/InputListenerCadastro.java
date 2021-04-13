package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import model.Usuario;
import model.UsuarioDAO;
import view.TelaCadastro;
import view.TelaLogin;

public class InputListenerCadastro implements ActionListener {

	private TelaCadastro tela;
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	
	public InputListenerCadastro(TelaCadastro tela) {
		this.tela = tela;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getActionCommand() == "CADASTRAR") {
			cadastrar();
		}
		else if(event.getActionCommand() == "VOLTAR") {
			voltar();
		}
		
	}
	
	private void cadastrar() {
		try {
			String nome = tela.getNome();
			String email = tela.getEmail();
			String senha = tela.getSenha();
			String confirmaSenha = tela.getConfirmaSenha();
				
			if(nome.equals("") || email.equals("") || senha.equals("") || confirmaSenha.equals("")) {
				String message = "Existem campos em branco";
				JOptionPane.showMessageDialog(tela, message, "Warning", JOptionPane.WARNING_MESSAGE);
			}
			else if(!email.contains("@")) {
				String message = "Email invalido";
				JOptionPane.showMessageDialog(tela,  message, "Warning", JOptionPane.WARNING_MESSAGE);
			}
			else if (nome.matches(".*\\d+.*")){
				String message = "Nome invalido";
				JOptionPane.showMessageDialog(tela,  message, "Warning", JOptionPane.WARNING_MESSAGE);
			}
			else {
				if(senha.equals(confirmaSenha)) {
					
					Usuario usuario = new Usuario(0,nome, email, senha, 0);
					
					usuarioDAO.conectar();
					usuarioDAO.insertUser(usuario);
					usuarioDAO.desconectar();
					
					String message = "Cadastrado com sucesso!";
					JOptionPane.showMessageDialog(tela, message, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
					voltar();
					
				}
				else {
					String message = "As senhas não conferem";
					JOptionPane.showMessageDialog(tela, message, "ERRO", JOptionPane.ERROR_MESSAGE);
				}
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
			new TelaLogin().setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

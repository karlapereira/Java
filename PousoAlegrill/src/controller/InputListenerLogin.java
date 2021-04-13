package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Usuario;
import model.UsuarioDAO;
import view.TelaAtendimento;
import view.TelaBar;
import view.TelaCadastro;
import view.TelaCozinha;
import view.TelaLogin;
import view.TelaMenuAdmin;

public class InputListenerLogin implements ActionListener{

	private TelaLogin tela;
	private UsuarioDAO UsuarioDAO = new UsuarioDAO();
	private Usuario user;
	
	public InputListenerLogin(TelaLogin tela) {
		this.tela = tela;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {

		if(event.getActionCommand() == "CADASTRAR") {
			cadastrar();
		}
		else if(event.getActionCommand() == "LOGIN") {
			login();
		}
		
	}

	private void login() {
		try {
		
			String email = tela.getEmail();
			String senha = tela.getSenha();
			
			if(email.equals("") || senha.equals("") || !email.contains("@")) {
				String message = "Informacoes incorretas";
				JOptionPane.showMessageDialog(tela, message, "Warning", JOptionPane.WARNING_MESSAGE);
			}
			else {
					ArrayList<Usuario> usuarios = new ArrayList<>();
					
					UsuarioDAO.conectar();
					usuarios = UsuarioDAO.downloadUsers();
					UsuarioDAO.desconectar();
					
					@SuppressWarnings("unused")
					boolean isMatch = false;
					
					for(Usuario usuario : usuarios) {
						if(usuario.getEmail().equals(email)) {
							if(usuario.getSenha().equals(senha)) {
								user.setUser(usuario);
								isMatch = true;
								break;
							}
						}
					}
					
					if(isMatch) {
						tela.dispose();
						switch (user.getInstance().getFuncao()) {
						case 1:
							new TelaAtendimento().setVisible(true);
							break;
						case 2:
							new TelaCozinha().setVisible(true);
							break;
						case 3:
							new TelaBar().setVisible(true);
							break;
						case 4:
							new TelaMenuAdmin().setVisible(true);
							break;
						default:							
							String message = "Voce nao exerce nenhuma funcao. Comunique ao administrador do sistema.";
							JOptionPane.showMessageDialog(tela, message, "Warning", JOptionPane.WARNING_MESSAGE);
							new TelaLogin().setVisible(true);
							break;
						}
					}
					else {
						String message = "Email ou senha incorretos!";
						JOptionPane.showMessageDialog(tela, message, "Erro", JOptionPane.ERROR_MESSAGE);
					}
				
			}
		} catch (SQLException e) {
			String message = "Erro ao conectar ao banco de dados";
			JOptionPane.showMessageDialog(tela, message, "ERRO BD", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
		}
				
	}

	private void cadastrar() {
		try {
			tela.dispose();
			new TelaCadastro().setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

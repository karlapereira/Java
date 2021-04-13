package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JTextField;

import controller.InputListenerLogin;
import model.Usuario;

import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.awt.Color;

public class TelaLogin extends JFrame {

	private static final long serialVersionUID = -2623346310258823692L;
	private InputListenerLogin listener;
	private JPanel contentPane;
	private JPanel panel;
	private JTextField textFieldEmail;
	private JPasswordField passwordField;
	private JLabel lblEmail;
	private JLabel lblSenha;
	private JLabel backgroundLabel;
	private JButton btnLogin;
	private JButton btnCadastrar;
	
	/**
	 * Create the frame.
	 */
	public TelaLogin() {
		setTitle("LOGIN");
		initialize();
		setActionCommand();
		listener = new InputListenerLogin(this);
		listenerInitialize();
		
	}
	
	private void initialize() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		//setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("re-icon.png")));
		contentPane = new JPanel();
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		
		getContentPane().add(getPanel(), BorderLayout.CENTER);
		
	}
	
	private void listenerInitialize() {
		
		getBtnCadastrar().addActionListener(listener);
		getBtnLogin().addActionListener(listener);
		
	}
	
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(null);
			panel.add(getTextFieldEmail());
			panel.add(getPasswordField());
			panel.add(getLblEmail());
			panel.add(getLblSenha());
			panel.add(getBtnLogin());
			panel.add(getBtnCadastrar());
			panel.add(getBackgroundLabel());
			//panel.setBackground(new JLabel(new ImageIcon("C:\Users\LF\workspace\Resident_Evil\src\view\wallpaper1.jpg")));
			
		}
		return panel;
	}
	
	private JTextField getTextFieldEmail() {
		if (textFieldEmail == null) {
			textFieldEmail = new JTextField();
			textFieldEmail.setBounds(128, 57, 200, 24);
			textFieldEmail.setColumns(10);
		}
		return textFieldEmail;
	}
	private JPasswordField getPasswordField() {
		if (passwordField == null) {
			passwordField = new JPasswordField();
			passwordField.setBounds(128, 122, 200, 24);
		}
		return passwordField;
	}
	private JLabel getLblEmail() {
		if (lblEmail == null) {
			lblEmail = new JLabel("E-mail:");
			lblEmail.setForeground(Color.BLACK);
			lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblEmail.setBounds(138, 32, 46, 14);
		}
		return lblEmail;
	}
	private JLabel getLblSenha() {
		if (lblSenha == null) {
			lblSenha = new JLabel("Senha:");
			lblSenha.setForeground(Color.BLACK);
			lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblSenha.setBounds(138, 97, 46, 14);
		}
		return lblSenha;
	}
	private JButton getBtnLogin() {
		if (btnLogin == null) {
			btnLogin = new JButton("LOGIN");
			btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnLogin.setBounds(128, 157, 200, 36);
		}
		return btnLogin;
	}
	private JButton getBtnCadastrar() {
		if (btnCadastrar == null) {
			btnCadastrar = new JButton("CADASTRAR");
			btnCadastrar.setForeground(Color.BLACK);
			btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnCadastrar.setBounds(128, 204, 200, 36);
		}
		return btnCadastrar;
	}
	
	public JLabel getBackgroundLabel() {
		if(backgroundLabel==null){
			backgroundLabel = new JLabel();
			backgroundLabel.setBounds(0, 0, 444, 270);
			backgroundLabel.setOpaque(true);
		}
		return backgroundLabel;
	}
	
	private void setActionCommand() {
		getBtnCadastrar().setActionCommand("CADASTRAR");
		getBtnLogin().setActionCommand("LOGIN");		
	}
	
	public String getEmail() {
		return getTextFieldEmail().getText();
	}
	public String getSenha() {
		return String.copyValueOf(getPasswordField().getPassword());
	}
	
}

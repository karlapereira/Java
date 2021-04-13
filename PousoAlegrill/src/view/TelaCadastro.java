package view;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.InputListenerCadastro;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;

public class TelaCadastro extends JFrame {

	private static final long serialVersionUID = -417397475806884830L;
	private InputListenerCadastro listener;
	private JPanel contentPane;
	private JPanel panel;
	private JTextField textFieldNome;
	private JTextField textFieldEmail;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldConfirma;
	private JButton btnVoltar;
	private JButton btnCadastrar;
	private JLabel lblNome;
	private JLabel lblEmail;
	private JLabel lblSenha;
	private JLabel lblConfirmarSenha;
	private JLabel backgroundLabel;

	/**
	 * Create the frame.
	 */
	public TelaCadastro() {
		setTitle("CADASTRO");		
		initialize();
		setActionCommand();
		listener = new InputListenerCadastro(this);
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
		btnVoltar.addActionListener(listener);
		btnCadastrar.addActionListener(listener);		
	}
	
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(null);
			panel.add(getTextFieldNome());
			panel.add(getTextFieldEmail());
			panel.add(getPasswordField());
			panel.add(getPasswordFieldConfirma());
			panel.add(getBtnVoltar());
			panel.add(getBtnCadastrar());
			panel.add(getLblNome());
			panel.add(getLblEmail());
			panel.add(getLblSenha());
			panel.add(getLblConfirmarSenha());
			panel.add(getBackgroundLabel());
		}
		return panel;
	}
	private JTextField getTextFieldNome() {
		if (textFieldNome == null) {
			textFieldNome = new JTextField();
			textFieldNome.setBounds(132, 11, 200, 24);
			textFieldNome.setColumns(10);
		}
		return textFieldNome;
	}
	private JTextField getTextFieldEmail() {
		if (textFieldEmail == null) {
			textFieldEmail = new JTextField();
			textFieldEmail.setBounds(132, 51, 200, 24);
			textFieldEmail.setColumns(10);
		}
		return textFieldEmail;
	}
	private JPasswordField getPasswordField() {
		if (passwordField == null) {
			passwordField = new JPasswordField();
			passwordField.setBounds(132, 92, 200, 24);
		}
		return passwordField;
	}
	private JPasswordField getPasswordFieldConfirma() {
		if (passwordFieldConfirma == null) {
			passwordFieldConfirma = new JPasswordField();
			passwordFieldConfirma.setBounds(132, 130, 200, 24);
		}
		return passwordFieldConfirma;
	}
	private JButton getBtnVoltar() {
		if (btnVoltar == null) {
			btnVoltar = new JButton("Voltar");
			btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnVoltar.setBounds(132, 210, 200, 36);
		}
		return btnVoltar;
	}
	private JButton getBtnCadastrar() {
		if (btnCadastrar == null) {
			btnCadastrar = new JButton("Salvar");
			btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnCadastrar.setBounds(132, 163, 200, 36);
		}
		return btnCadastrar;
	}
	private JLabel getLblNome() {
		if (lblNome == null) {
			lblNome = new JLabel("Nome:");
			lblNome.setForeground(Color.BLACK);
			lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNome.setBounds(37, 21, 46, 14);
		}
		return lblNome;
	}
	private JLabel getLblEmail() {
		if (lblEmail == null) {
			lblEmail = new JLabel("E-mail:");
			lblEmail.setForeground(Color.BLACK);
			lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblEmail.setBounds(37, 61, 46, 14);
		}
		return lblEmail;
	}
	private JLabel getLblSenha() {
		if (lblSenha == null) {
			lblSenha = new JLabel("Senha:");
			lblSenha.setForeground(Color.BLACK);
			lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblSenha.setBounds(37, 102, 46, 14);
		}
		return lblSenha;
	}
	private JLabel getLblConfirmarSenha() {
		if (lblConfirmarSenha == null) {
			lblConfirmarSenha = new JLabel("Confirmar Senha:");
			lblConfirmarSenha.setForeground(Color.BLACK);
			lblConfirmarSenha.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblConfirmarSenha.setBounds(37, 140, 118, 14);
		}
		return lblConfirmarSenha;
	}
	
	public JLabel getBackgroundLabel() {
		if(backgroundLabel==null){
			backgroundLabel = new JLabel();
			backgroundLabel.setBounds(0, 0, 444, 270);
			backgroundLabel.setIcon(new ImageIcon("C:\\Users\\LF\\workspace\\Resident_Evil\\src\\view\\wallpaper1.jpg"));
			backgroundLabel.setOpaque(true);
		}
		return backgroundLabel;
	}
	
	private void setActionCommand() {
		getBtnVoltar().setActionCommand("VOLTAR");
		getBtnCadastrar().setActionCommand("CADASTRAR");
	}
	
	public String getNome() {
		return getTextFieldNome().getText();
	}
	public String getEmail() {
		return getTextFieldEmail().getText();
	}
	public String getSenha() {
		return String.copyValueOf(getPasswordField().getPassword());
	}
	public String getConfirmaSenha() {
		return String.copyValueOf(getPasswordFieldConfirma().getPassword());
	}
	
}

package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.InputListenerEditarUsuario;
import model.Mesa;
import model.Usuario;
import model.UsuarioDAO;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

public class TelaEditarUsuario extends JFrame {
	
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	
	private static final long serialVersionUID = 427889571756261812L;
	private InputListenerEditarUsuario listener;
	private JPanel contentPane;
	private JPanel panel;
	private JTextField textFieldNome;
	private JTextField textFieldEmail;
	private JTextField textFieldSenha;
	private JButton btnAdicionar;
	private JButton btnExcluir;
	private JButton btnSalvar;
	private JButton btnVoltar;
	private JComboBox comboBoxCategoria;
	private JLabel lblFuncao;
	private JLabel lblSenha;
	private JLabel lblEmail;
	private JLabel lblNome;
	private JList list;

	public TelaEditarUsuario() {
		setTitle("EDITAR USUARIO");
		initialize();
		setActionCommand();
		listener = new InputListenerEditarUsuario(this);
		listenerInitialize();
		this.updateList();
	}
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		contentPane = new JPanel();
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		getContentPane().add(getPanel(), BorderLayout.CENTER);
	}
	
	private void listenerInitialize() {
		getBtnVoltar().addActionListener(listener);
		getBtnSalvar().addActionListener(listener);
		getBtnAdicionar().addActionListener(listener);
		getBtnExcluir().addActionListener(listener);
		list.addListSelectionListener(listener);
	}
	
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(null);
			panel.add(getBtnVoltar());
			panel.add(getTextFieldNome());
			panel.add(getTextFieldEmail());
			panel.add(getTextFieldSenha());
			panel.add(getJComboBoxCategoria());
			panel.add(getList());
			
			
			
			lblNome = new JLabel("Nome:");
			lblNome.setBounds(280, 12, 144, 14);
			panel.add(lblNome);
			
			
			
			lblEmail = new JLabel("Email:");
			lblEmail.setBounds(280, 46, 144, 14);
			panel.add(lblEmail);
			
			
			
			lblSenha = new JLabel("Senha:");
			lblSenha.setBounds(280, 80, 144, 14);
			panel.add(lblSenha);
			
			
			
			lblFuncao = new JLabel("Funcao:");
			lblFuncao.setBounds(280, 114, 144, 14);
			panel.add(lblFuncao);
			
			
			
			panel.add(getBtnSalvar());
			panel.add(getBtnAdicionar());
			panel.add(getBtnExcluir());
		}
		return panel;
	}
	
	public JList getList() {
		if(list == null) {
			list = new JList();
			list.setBounds(10, 11, 260, 179);
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			list.setVisible(true);
		}
		return list;
	}
	
	private JComboBox getJComboBoxCategoria() {
		if (comboBoxCategoria == null) {
			comboBoxCategoria = new JComboBox();
			comboBoxCategoria.setModel(new DefaultComboBoxModel(new String[] {"Sem funcao", "Atendimento", "Cozinha", "Bar", "Administrador"}));
			comboBoxCategoria.setBounds(280, 128, 144, 20);			
		}
		return comboBoxCategoria;
	}
	
	private JTextField getTextFieldNome() {
		if (textFieldNome == null) {
			textFieldNome = new JTextField();
			textFieldNome.setBounds(280, 26, 144, 20);			
			textFieldNome.setColumns(10);
		}
		return textFieldNome;
	}
	
	private JTextField getTextFieldEmail() {
		if (textFieldEmail == null) {
			textFieldEmail = new JTextField();
			textFieldEmail.setBounds(280, 60, 144, 20);
			textFieldEmail.setColumns(10);
		}
		return textFieldEmail;
	}
	
	private JTextField getTextFieldSenha() {
		if (textFieldSenha == null) {
			textFieldSenha = new JTextField();
			textFieldSenha.setColumns(10);
			textFieldSenha.setBounds(280, 94, 144, 20);
		}
		return textFieldSenha;
	}
	
	private JButton getBtnVoltar() {
		if(btnVoltar == null) {
			btnVoltar = new JButton("Voltar");
			btnVoltar.setBounds(10, 226, 116, 24);
		}
		return btnVoltar;
	}
	
	private JButton getBtnSalvar() {
		if(btnSalvar == null) {
			btnSalvar = new JButton("Salvar");
			btnSalvar.setBounds(10, 201, 116, 24);
		}
		return btnSalvar;
	}
	
	private JButton getBtnAdicionar() {
		if(btnAdicionar == null) {
			btnAdicionar = new JButton("Adicionar");
			btnAdicionar.setBounds(154, 201, 116, 24);
		}
		return btnAdicionar;
	}
	
	private JButton getBtnExcluir() {
		if(btnExcluir == null) {
			btnExcluir = new JButton("Excluir");
			btnExcluir.setBounds(154, 226, 116, 24);
		}
		return btnExcluir;
	}
	
	private void setActionCommand() {
		getBtnVoltar().setActionCommand("VOLTAR");
		getBtnSalvar().setActionCommand("SALVAR");
		getBtnAdicionar().setActionCommand("ADICIONAR");
		getBtnExcluir().setActionCommand("EXCLUIR");
	}
	
	public String getNome() {
		return getTextFieldNome().getText();
	}
	
	public void setNome(String nome) {
		this.textFieldNome.setText(nome);
	}
	
	public String getEmail() {
		return getTextFieldEmail().getText();
	}
	
	public void setEmail(String email) {
		this.textFieldEmail.setText(email);
	}
	
	public String getSenha() {
		return getTextFieldSenha().getText();
	}
	
	public void setSenha(String senha) {
		this.textFieldSenha.setText(senha);
	}
	
	public int getCategoria() {
		return getJComboBoxCategoria().getSelectedIndex();
	}
	
	public void setCategoria(int categoria) {
		this.comboBoxCategoria.setSelectedIndex(categoria);
	}
	
	public void updateList() {
		DefaultListModel listModel = new DefaultListModel<>();
		try {
			
			usuarioDAO.conectar();
			ArrayList<Usuario> usuarios = usuarioDAO.downloadUsers();
			usuarioDAO.desconectar();
			
			for(Usuario usuario : usuarios) {
				listModel.addElement(usuario.toString());
			}
		} catch (SQLException e) {
			String message = "Erro ao conectar ao banco de dados";
			JOptionPane.showMessageDialog(this, message, "ERRO BD", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
		}
		this.list.setModel(listModel);
	}
}

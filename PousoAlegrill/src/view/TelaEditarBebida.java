package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.InputListenerEditarBebida;
import model.Bebida;
import model.BebidaDAO;
import model.Usuario;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

public class TelaEditarBebida extends JFrame {

	private BebidaDAO bebidaDAO = new BebidaDAO();
	
	private static final long serialVersionUID = 3722158400731864830L;	
	private InputListenerEditarBebida listener;
	private JPanel contentPane;
	private JPanel panel;
	private JTextField textFieldNome;
	private JTextField textFieldPreco;
	private JList list;
	private JLabel lblNome;
	private JLabel lblDisponivel;
	private JComboBox comboBoxDisponivel;
	private JLabel lblPreco;
	private JButton btnSalvar;
	private JButton btnAdicionar;
	private JButton btnExcluir;
	private JButton btnVoltar;

	public TelaEditarBebida() {
		setTitle("EDITAR COMIDA");
		initialize();
		setActionCommand();
		listener = new InputListenerEditarBebida(this);
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
			panel.add(getBtnAdicionar());
			panel.add(getBtnExcluir());
			panel.add(getBtnSalvar());
			panel.add(getList());
			panel.add(getTextFieldNome());
			panel.add(getTextFieldPreco());
			panel.add(getJComboBoxDisponivel());
			
			lblNome = new JLabel("Nome:");
			lblNome.setBounds(280, 12, 144, 14);
			panel.add(lblNome);
			
			lblDisponivel = new JLabel("Disponivel:");
			lblDisponivel.setBounds(280, 102, 144, 14);
			panel.add(lblDisponivel);
			
			lblPreco = new JLabel("Preco:");
			lblPreco.setBounds(280, 57, 144, 14);
			panel.add(lblPreco);
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

	private JTextField getTextFieldNome() {
		if (textFieldNome == null) {
			textFieldNome = new JTextField();
			textFieldNome.setBounds(280, 26, 144, 20);
			textFieldNome.setColumns(10);			
		}
		return textFieldNome;
	}
	
	private JTextField getTextFieldPreco() {
		if (textFieldPreco == null) {
			textFieldPreco = new JTextField();
			textFieldPreco.setColumns(10);
			textFieldPreco.setBounds(280, 71, 144, 20);
		}
		return textFieldPreco;
	}
	
	private JComboBox getJComboBoxDisponivel() {
		if (comboBoxDisponivel == null) {
			comboBoxDisponivel = new JComboBox();
			comboBoxDisponivel.setModel(new DefaultComboBoxModel(new String[] {"Indisponivel", "Disponivel"}));
			comboBoxDisponivel.setBounds(280, 116, 144, 20);			
		}
		return comboBoxDisponivel;
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
	
	public String getNome() {
		return getTextFieldNome().getText();
	}

	public void setNome(String nome) {
		this.textFieldNome.setText(nome);
	}
	
	public String getPreco() {
		return getTextFieldPreco().getText();
	}
	
	public void setPreco(float preco) {
		this.textFieldPreco.setText(String.valueOf(preco));
	}
	
	public Boolean getDisponivel() {
		if (getJComboBoxDisponivel().getSelectedIndex() == 0) {
			return false;
		}
		else return true;
	}
	
	public void setDisponivel(Boolean disponivel) {
		if (disponivel) {
			this.comboBoxDisponivel.setSelectedIndex(1);
		}
		else this.comboBoxDisponivel.setSelectedIndex(0);		
	}
	
	public void updateList() {
		DefaultListModel listModel = new DefaultListModel<>();
		try {
			
			bebidaDAO.conectar();
			ArrayList<Bebida> bebidas = bebidaDAO.downloadBebidas();
			bebidaDAO.desconectar();
			
			for(Bebida bebida : bebidas) {
				listModel.addElement(bebida.toString());
			}
		} catch (SQLException e) {
			String message = "Erro ao conectar ao banco de dados";
			JOptionPane.showMessageDialog(this, message, "ERRO BD", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
		}
		this.list.setModel(listModel);
	}
	
	private void setActionCommand() {
		getBtnVoltar().setActionCommand("VOLTAR");
		getBtnSalvar().setActionCommand("SALVAR");
		getBtnAdicionar().setActionCommand("ADICIONAR");
		getBtnExcluir().setActionCommand("EXCLUIR");
	}
}

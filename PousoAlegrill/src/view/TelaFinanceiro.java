package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.InputListenerAtendimento;
import controller.InputListenerEditarBebida;
import controller.InputListenerFinanceiro;
import model.Bebida;
import model.BebidaDAO;
import model.Comida;
import model.ComidaDAO;
import model.Financeiro;
import model.FinanceiroDAO;
import model.Mesa;
import model.MesaDAO;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JTextArea;

public class TelaFinanceiro extends JFrame {

	private MesaDAO mesaDAO = new MesaDAO();
	private ComidaDAO comidaDAO = new ComidaDAO();
	private BebidaDAO bebidaDAO = new BebidaDAO();
	private FinanceiroDAO financeiroDAO = new FinanceiroDAO();
	
	private static final long serialVersionUID = 8373305023122725636L;
	private InputListenerFinanceiro listener;
	private JPanel contentPane;
	private JPanel panel;	
	private JButton btnDesconto;
	private JButton btnImprimirConta;
	private JButton btnExcluir;
	private JButton btnVoltar;
	private JList listPedidos;
	private JList listMesas;

	public TelaFinanceiro() {
		setTitle("FINANCEIRO");
		initialize();
		setActionCommand();
		listener = new InputListenerFinanceiro(this);
		listenerInitialize();
		updateListMesas();
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
		getBtnExcluir().addActionListener(listener);
		getBtnDesconto().addActionListener(listener);
		getListMesas().addListSelectionListener(listener);
		getListPedidos().addListSelectionListener(listener);
	}
	
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(null);
			panel.add(getBtnVoltar());
			panel.add(getBtnExcluir());
			panel.add(getBtnDesconto());
			panel.add(getListMesas());
			panel.add(getListPedidos());
		}
		return panel;
	}

	private JButton getBtnVoltar() {
		if(btnVoltar == null) {
			btnVoltar = new JButton("Voltar");
			btnVoltar.setBounds(10, 201, 162, 51);
		}
		return btnVoltar;
	}
	
	private JButton getBtnExcluir() {
		if(btnExcluir == null) {
			btnExcluir = new JButton("Excluir");
			btnExcluir.setBounds(182, 201, 116, 51);
		}
		return btnExcluir;
	}
	
	private JButton getBtnDesconto() {
		if(btnDesconto == null) {
			btnDesconto = new JButton("Desconto");
			btnDesconto.setBounds(308, 201, 116, 51);
		}
		return btnDesconto;
	}
	
	public JList getListPedidos() {
		if (listPedidos == null) {
			listPedidos = new JList();
			listPedidos.setBounds(182, 11, 242, 179);
			listPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listPedidos.setVisible(true);
		}
		return listPedidos;
	}

	public JList getListMesas() {
		if (listMesas == null) {
			listMesas = new JList();
			listMesas.setBounds(10, 11, 162, 179);
			listMesas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listMesas.setVisible(true);
		}
		return listMesas;
	}
	
	private void setActionCommand() {
		getBtnVoltar().setActionCommand("VOLTAR");
		getBtnExcluir().setActionCommand("EXCLUIR");
		getBtnDesconto().setActionCommand("DESCONTO");
	}
	
	public String getMesa() {
		return getListMesas().getSelectedValue().toString();
	}
	
	public void updateListMesas() {
		DefaultListModel listModel = new DefaultListModel<>();
		try {
			mesaDAO.conectar();
			ArrayList<Mesa> mesas = mesaDAO.downloadMesa();
			mesaDAO.desconectar();
			financeiroDAO.conectar();
			ArrayList<Financeiro> financas = financeiroDAO.downloadFinancas();
			financeiroDAO.desconectar();
			for (Mesa mesa : mesas) {
				if (mesa.isFinalizada()) {
					for (Financeiro financeiro : financas) {
						if (mesa.getFinanceiro() == financeiro.getId()) {
							listModel.addElement(mesa.toString());
							listModel.addElement(financeiro.toString());
						}
						break;
					}
				}
			}
		} catch (SQLException e) {
			String message = "Erro ao conectar ao banco de dados";
			JOptionPane.showMessageDialog(this, message, "ERRO BD", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
		}
		this.listMesas.setModel(listModel);
	}
	
	public void updateListPedidos(Mesa mesa) {
		DefaultListModel listModel = new DefaultListModel<>();
		if (mesa != null) {
			for (Comida comida : mesa.getComidas()) {
				listModel.addElement(comida.toString());
			}
			for (Bebida bebida : mesa.getBebidas()) {
				listModel.addElement(bebida.toString());
			}
		}		
		this.listPedidos.setModel(listModel);
	}
}

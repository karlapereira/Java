package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.InputListenerEditarPedidos;
import model.Bebida;
import model.Comida;
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

public class TelaEditarPedidos extends JFrame {

	private MesaDAO mesaDAO = new MesaDAO();
	
	private static final long serialVersionUID = 8373305023122725636L;
	private InputListenerEditarPedidos listener;
	private JPanel contentPane;
	private JPanel panel;
	private JList listMesas;
	private JList listPedidos;
	private JButton btnVoltar;
	private JButton btnExcluir;

	public TelaEditarPedidos() {
		setTitle("EDITAR PEDIDOS");
		initialize();
		setActionCommand();
		listener = new InputListenerEditarPedidos(this);
		listenerInitialize();
		updateListMesas();
	}
	
	public void initialize() {
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
		getListMesas().addListSelectionListener(listener);
		getListPedidos().addListSelectionListener(listener);
	}
	
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(null);
			panel.add(getBtnVoltar());
			panel.add(getBtnExcluir());
			panel.add(getListMesas());			
			panel.add(getListPedidos());
		}
		return panel;
	}
	
	public JList getListMesas() {
		if (listMesas == null) {
			listMesas = new JList();
			listMesas.setBounds(10, 11, 132, 179);
			listMesas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listMesas.setVisible(true);
		}
		return listMesas;
	}
	
	public JList getListPedidos() {
		if (listPedidos == null) {
			listPedidos = new JList();
			listPedidos.setBounds(152, 11, 272, 179);
			listPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listPedidos.setVisible(true);
		}
		return listPedidos;
	}
	
	private JButton getBtnVoltar() {
		if (btnVoltar == null) {
			btnVoltar = new JButton("Voltar");
			btnVoltar.setBounds(10, 201, 116, 51);
		}
		return btnVoltar;
	}
	
	private JButton getBtnExcluir() {
		if (btnExcluir == null) {
			btnExcluir = new JButton("Excluir");
			btnExcluir.setBounds(308, 201, 116, 51);
		}
		return btnExcluir;
	}
	
	private void setActionCommand() {
		getBtnVoltar().setActionCommand("VOLTAR");
		getBtnExcluir().setActionCommand("EXCLUIR");
	}
	
	public String getMesa() {
		return getListMesas().getSelectedValue().toString();
	}
	
	public String getPedido() {
		return getListPedidos().getSelectedValue().toString();
	}
	
	public void updateListMesas() {
		DefaultListModel listModel = new DefaultListModel<>();
		try {
			mesaDAO.conectar();
			ArrayList<Mesa> mesas = mesaDAO.downloadMesa();
			mesaDAO.desconectar();
			for(Mesa mesa : mesas) {
				if (!mesa.isFinalizada()) {
					listModel.addElement(mesa.toString());
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

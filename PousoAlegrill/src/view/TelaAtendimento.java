package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.InputListenerAtendimento;
import controller.InputListenerEditarBebida;
import model.Bebida;
import model.BebidaDAO;
import model.Comida;
import model.ComidaDAO;
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

public class TelaAtendimento extends JFrame {
	
	private MesaDAO mesaDAO = new MesaDAO();
	private ComidaDAO comidaDAO = new ComidaDAO();
	private BebidaDAO bebidaDAO = new BebidaDAO();

	private static final long serialVersionUID = 8373305023122725636L;
	private InputListenerAtendimento listener;
	private JPanel contentPane;
	private JPanel panel;
	private JList listBebidas;
	private JButton btnFecharMesa;
	private JButton btnNovaMesa;
	private JButton btnAdicionar;
	private JButton btnLogout;
	private JList listMesas;
	private JList listComidas;

	public TelaAtendimento() {
		setTitle("ATENDIMENTO");
		initialize();
		setActionCommand();
		listener = new InputListenerAtendimento(this);
		listenerInitialize();
		updateListMesas();
		updateListComidas();
		updateListBebidas();
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
		getBtnLogout().addActionListener(listener);
		getBtnAdicionar().addActionListener(listener);
		getBtnFecharMesa().addActionListener(listener);
		getBtnNovaMesa().addActionListener(listener);
		getListMesas().addListSelectionListener(listener);
		getListComidas().addListSelectionListener(listener);
		getListBebidas().addListSelectionListener(listener);
	}
	
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(null);
			panel.add(getBtnLogout());
			panel.add(getListBebidas());
			panel.add(getBtnFecharMesa());
			panel.add(getBtnFecharMesa());
			panel.add(getBtnNovaMesa());
			panel.add(getBtnAdicionar());
			panel.add(getListMesas());
			panel.add(getListComidas());
		}
		return panel;
	}
	
	private JButton getBtnNovaMesa() {
		if (btnNovaMesa == null) {
			btnNovaMesa = new JButton("Nova Mesa");
			btnNovaMesa.setBounds(10, 201, 162, 24);
		}		
		return btnNovaMesa;
	}	

	public JList getListComidas() {
		if (listComidas == null) {
			listComidas = new JList();
			listComidas.setBounds(182, 11, 116, 179);
			listComidas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listComidas.setVisible(true);
		}		
		return listComidas;
	}
	
	public JList getListBebidas() {
		if (listBebidas == null) {
			listBebidas = new JList();
			listBebidas.setBounds(308, 11, 116, 179);
			listBebidas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listBebidas.setVisible(true);
		}		
		return listBebidas;
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

	private JButton getBtnAdicionar() {
		if (btnAdicionar == null) {
			btnAdicionar = new JButton("Adicionar");
			btnAdicionar.setBounds(182, 201, 116, 51);
		}		
		return btnAdicionar;
	}

	private JButton getBtnFecharMesa() {
		if (btnFecharMesa == null) {
			btnFecharMesa = new JButton("Fechar Mesa");
			btnFecharMesa.setBounds(10, 228, 162, 24);
		}		
		return btnFecharMesa;
	}

	private JButton getBtnLogout() {
		if (btnLogout == null) {
			btnLogout = new JButton("LOGOUT");
			btnLogout.setBounds(308, 201, 116, 51);
		}
		return btnLogout;
	}
		
	private void setActionCommand() {
		getBtnLogout().setActionCommand("LOGOUT");
		getBtnAdicionar().setActionCommand("ADICIONAR");
		getBtnFecharMesa().setActionCommand("FECHAR");
		getBtnNovaMesa().setActionCommand("NOVA");
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
	
	public void updateListComidas() {
		DefaultListModel listModel = new DefaultListModel<>();
		try {
			comidaDAO.conectar();
			ArrayList<Comida> comidas = comidaDAO.downloadComidas();
			comidaDAO.desconectar();
			for(Comida comida : comidas) {
				if (comida.getDisponivel()) {
					listModel.addElement(comida.toString());
				}
			}
		} catch (SQLException e) {
			String message = "Erro ao conectar ao banco de dados";
			JOptionPane.showMessageDialog(this, message, "ERRO BD", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
		}
		this.listComidas.setModel(listModel);
	}
	
	public void updateListBebidas() {
		DefaultListModel listModel = new DefaultListModel<>();
		try {
			bebidaDAO.conectar();
			ArrayList<Bebida> bebidas = bebidaDAO.downloadBebidas();
			bebidaDAO.desconectar();
			for(Bebida bebida : bebidas) {
				if (bebida.getDisponivel()) {
					listModel.addElement(bebida.toString());
				}
			}
		} catch (SQLException e) {
			String message = "Erro ao conectar ao banco de dados";
			JOptionPane.showMessageDialog(this, message, "ERRO BD", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
		}
		this.listBebidas.setModel(listModel);
	}
}

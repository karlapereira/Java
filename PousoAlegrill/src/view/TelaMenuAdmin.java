package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import controller.InputListenerMenuAdmin;
import model.Bebida;
import model.Comida;
import model.Mesa;
import model.MesaDAO;
import model.Usuario;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class TelaMenuAdmin extends JFrame {	
	private MesaDAO mesaDAO = new MesaDAO();
	
	private static final long serialVersionUID = -1125530819704100604L;
	private InputListenerMenuAdmin listener;
	private JPanel contentPane;
	private JPanel panel;
	private JButton btnMesas;
	private JButton btnCozinha;
	private JButton btnBar;
	private JList list;
	private JLabel lblExibir;
	private JLabel lblEditar;
	private JButton btnPedidos;
	private JButton btnComidas;
	private JButton btnBebidas;
	private JButton btnUsuarios;
	private JButton btnLogout;
	private JButton btnFinanceiro;
	
	public TelaMenuAdmin() {
		setTitle("MENU ADMINISTRADOR");
		initialize();
		setActionCommand();
		listener = new InputListenerMenuAdmin(this);
		listenerInitialize();
		this.updateListMesas();
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
		getBtnMesas().addActionListener(listener);
		getBtnCozinha().addActionListener(listener);
		getBtnBar().addActionListener(listener);
		getBtnPedidos().addActionListener(listener);
		getBtnComidas().addActionListener(listener);
		getBtnBebidas().addActionListener(listener);
		getBtnUsuarios().addActionListener(listener);
		getBtnFinanceiro().addActionListener(listener);
	}
	
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(null);
			panel.add(getBtnLogout());			
			panel.add(getBtnMesas());			
			panel.add(getBtnCozinha());			
			panel.add(getBtnBar());
			panel.add(getBtnPedidos());			
			panel.add(getBtnComidas());		
			panel.add(getBtnBebidas());			
			panel.add(getBtnUsuarios());		
			panel.add(getBtnFinanceiro());			
			panel.add(getList());			
			panel.add(getLblExibir());			
			panel.add(getLblEditar());
		}
		return panel;
	}
	
	private JLabel getLblExibir() {
		if (lblExibir == null) {
			lblExibir = new JLabel("Exibir:");
			lblExibir.setBounds(10, 201, 46, 14);
		}
		return lblExibir;
	}
	
	private JLabel getLblEditar() {
		if (lblEditar == null) {
			lblEditar = new JLabel("Editar:");
			lblEditar.setBounds(280, 12, 46, 14);
		}
		return lblEditar;
	}
	
	private JList getList() {
		if (list == null) {
			list = new JList();
			list.setBounds(10, 11, 260, 179);
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			list.setVisible(true);
		}
		return list;
	}
		
	private JButton getBtnLogout() {
		if (btnLogout == null) {
			btnLogout = new JButton("Logout");
			btnLogout.setBounds(280, 214, 144, 36);
		}
		return btnLogout;
	}

	private JButton getBtnMesas() {
		if (btnMesas == null) {
			btnMesas = new JButton("Mesas");
			btnMesas.setBounds(10, 226, 80, 24);
		}
		return btnMesas;
	}

	private JButton getBtnCozinha() {
		if (btnCozinha == null) {
			btnCozinha = new JButton("Cozinha");
			btnCozinha.setBounds(100, 226, 80, 24);
		}
		return btnCozinha;
	}

	private JButton getBtnBar() {
		if (btnBar == null) {
			btnBar = new JButton("Bar");
			btnBar.setBounds(190, 226, 80, 24);
		}
		return btnBar;
	}

	private JButton getBtnPedidos() {
		if (btnPedidos == null) {
			btnPedidos = new JButton("Pedidos");
			btnPedidos.setBounds(280, 37, 144, 24);
		}
		return btnPedidos;
	}

	private JButton getBtnComidas() {
		if (btnComidas == null) {
			btnComidas = new JButton("Comidas");
			btnComidas.setBounds(280, 72, 144, 24);
		}
		return btnComidas;
	}

	private JButton getBtnBebidas() {
		if (btnBebidas == null) {
			btnBebidas = new JButton("Bebidas");
			btnBebidas.setBounds(280, 107, 144, 24);
		}
		return btnBebidas;
	}

	private JButton getBtnUsuarios() {
		if (btnUsuarios == null) {
			btnUsuarios = new JButton("Usuarios");
			btnUsuarios.setBounds(280, 142, 144, 24);
		}
		return btnUsuarios;
	}

	private JButton getBtnFinanceiro() {
		if (btnFinanceiro == null) {
			btnFinanceiro = new JButton("Financeiro");
			btnFinanceiro.setBounds(280, 177, 144, 24);
		}
		return btnFinanceiro;
	}
	
	public void setBtnMesas(Boolean enabled) {
		btnMesas.setEnabled(enabled);
	}
	
	public void setBtnCozinha(Boolean enabled) {
		btnCozinha.setEnabled(enabled);
	}
	
	public void setBtnBar(Boolean enabled) {
		btnBar.setEnabled(enabled);
	}
	
	public void setList(DefaultListModel<String> listModel) {
		this.list.setModel(listModel);
	}
	
	private void setActionCommand() {
		getBtnMesas().setActionCommand("MESAS");
		getBtnCozinha().setActionCommand("COZINHA");
		getBtnBar().setActionCommand("BAR");
		getBtnPedidos().setActionCommand("PEDIDOS");
		getBtnComidas().setActionCommand("COMIDAS");
		getBtnBebidas().setActionCommand("BEBIDAS");
		getBtnUsuarios().setActionCommand("USUARIOS");
		getBtnFinanceiro().setActionCommand("FINANCEIRO");
		getBtnLogout().setActionCommand("LOGOUT");
	}
	
	public void updateListBar() {
		DefaultListModel listModel = new DefaultListModel();
		try {
			ArrayList<Bebida> fila = new ArrayList<>();
			
			mesaDAO.conectar();
			ArrayList<Mesa> mesas = mesaDAO.downloadMesa();
			mesaDAO.desconectar();
			
			for(Mesa mesa : mesas) {
				for(Bebida bebida : mesa.getBebidas()) {
					if (!bebida.getServida()) {
						fila.add(bebida);
					}
				}
			}
			for(Bebida bebida : fila) {
				listModel.addElement(bebida.toString());
			}
		} catch (SQLException e) {
			String message = "Erro ao conectar ao banco de dados";
			JOptionPane.showMessageDialog(this, message, "ERRO BD", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
		}
		this.list.setModel(listModel);
	}
	
	public void updateListCozinha() {
		DefaultListModel listModel = new DefaultListModel();
		try {
			ArrayList<Comida> fila = new ArrayList<>();
			
			mesaDAO.conectar();
			ArrayList<Mesa> mesas = mesaDAO.downloadMesa();
			mesaDAO.desconectar();
			
			for(Mesa mesa : mesas) {
				for(Comida comida : mesa.getComidas()) {
					if (!comida.getServida()) {
						fila.add(comida);
					}
				}
			}
			for(Comida comida : fila) {
				listModel.addElement(comida.toString());
			}
		} catch (SQLException e) {
			String message = "Erro ao conectar ao banco de dados";
			JOptionPane.showMessageDialog(this, message, "ERRO BD", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
		}
		this.list.setModel(listModel);
	}
	
	public void updateListMesas() {
		DefaultListModel listModel = new DefaultListModel();
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
		this.list.setModel(listModel);
	}
}

package view;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import controller.InputListenerBar;
import model.Bebida;
import model.Mesa;
import model.MesaDAO;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

public class TelaBar extends JFrame {

	private static final long serialVersionUID = 4696878053022772270L;

	private MesaDAO mesaDAO = new MesaDAO();

	private InputListenerBar listener;
	private JPanel contentPane;
	private JPanel panel;
	private JList list;
	private JButton btnLogout;
	private JButton btnPronto;
	private JButton btnIndisponivel;

	public TelaBar() {
		setTitle("BAR");
		initialize();
		setActionCommand();
		listener = new InputListenerBar(this);
		listenerInitialize();
		updateList();
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
		getBtnPronto().addActionListener(listener);
		getBtnIndisponivel().addActionListener(listener);
	}
	
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(null);
			panel.add(getBtnLogout());
			panel.add(getBtnPronto());
			panel.add(getBtnIndisponivel());
			panel.add(getList());
		}
		return panel;
	}
	
	public JList getList() {
		if (list == null) {
			list = new JList();
			list.setVisibleRowCount(4);
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			list.setBounds(10, 11, 260, 239);
			list.setVisible(true);
		}
		return list;
	}
	
	private JButton getBtnLogout() {
		if (btnLogout == null) {
			btnLogout = new JButton("LOGOUT");
			btnLogout.setBounds(280, 214, 144, 36);
		}
		return btnLogout;
	}
	
	private JButton getBtnPronto() {
		if (btnPronto == null) {
			btnPronto = new JButton("Pronto");
			btnPronto.setBounds(280, 11, 144, 48);
		}
		return btnPronto;
	}
	
	private JButton getBtnIndisponivel() {
		if (btnIndisponivel == null) {
			btnIndisponivel = new JButton("Indisponivel");
			btnIndisponivel.setBounds(280, 70, 144, 48);
		}
		return btnIndisponivel;
	}
	
	private void setActionCommand() {
		getBtnLogout().setActionCommand("LOGOUT");
		getBtnPronto().setActionCommand("PRONTO");
		getBtnIndisponivel().setActionCommand("INDISPONIVEL");
	}
	
	public void updateList() {
		DefaultListModel listModel = new DefaultListModel<>();
		try {
			
			mesaDAO.conectar();
			ArrayList<Mesa> mesas = mesaDAO.downloadMesa();
			mesaDAO.desconectar();
			
			for(Mesa mesa : mesas) {
				if (!mesa.isFinalizada()) {
					for(Bebida bebida : mesa.getBebidas()) {
						if(!bebida.getServida() && bebida.getDisponivel()) {
							listModel.addElement(bebida.toString());
						}
					}
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

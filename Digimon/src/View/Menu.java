package View;

import java.awt.GridBagConstraints;

import View.CadastraDigimonFrame;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Model.Digimon;
import Model.Treinador;

public class Menu extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 260943810130542500L;
	
	
	//Interface objects
	private JButton cadastroDigimonButton;
	private JButton listarDigimonButton;
	private JButton pesquisarDigimonButton;
	private JButton cadastroTreinadorButton;
	private JButton listarTreinadorButton;
	private JButton sairButton;
	
	//Interface position objects
	private GridBagConstraints cadastroDigimonButtonConstraints;
	private GridBagConstraints listarDigimonButtonConstraints;
	private GridBagConstraints pesquisarDigimonButtonConstraints;
	private GridBagConstraints cadastroTreinadorButtonConstraints;
	private GridBagConstraints listarTreinadorButtonConstraints;
	private GridBagConstraints sairButtonConstraints;
	
	public static ArrayList<Digimon> digimons = new ArrayList<>();
	public static ArrayList<Treinador> treinadores = new ArrayList<>();
	
	
	
	public Menu(){
		
		super("Menu");
		initialize();
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}
	
	public Menu(Digimon digimonRecebido){
		
		super("Menu");
		initialize();
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
	}
	
	private void initialize() { 
		setLayout(new GridBagLayout());
		add(getCadastroDigimonButton(), getCadastroDigimonButtonConstraints());
		add(getListarDigimonButton(), getListarDigimonButtonConstraints());
		add(getPesquisarDigimonButton(), getPesquisarDigimonButtonConstraints());
		add(getCadastroTreinadorButton(), getCadastroTreinadorButtonConstraints());
		add(getListarTreinadorButton(), getListarTreinadorButtonConstraints());
		add(getSairButton(), getSairButtonConstraints());
	}

	public JButton getCadastroDigimonButton() {
		if (cadastroDigimonButton == null) {
			cadastroDigimonButton = new JButton("Cadastrar Digimon");
			cadastroDigimonButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					new CadastraDigimonFrame().setVisible(true);
				}
			});
		}
		return cadastroDigimonButton;
	}

	public JButton getListarDigimonButton() {
		if (listarDigimonButton == null) {
			listarDigimonButton = new JButton("Listar Digimon");
			listarDigimonButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(!digimons.isEmpty()){
						new ListarDigimon2(digimons, 0).setVisible(true);
					}else{
						JOptionPane.showMessageDialog(null, "Não há digimons a serem exibidos.");
					}
				}
			});
		}
		return listarDigimonButton;
	}

	public JButton getPesquisarDigimonButton() {
		if (pesquisarDigimonButton == null) {
			pesquisarDigimonButton = new JButton("Pesquisar Digimon");
			pesquisarDigimonButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					new PesquisaDigimonFrame().setVisible(true);
				}
			});
		}
		return pesquisarDigimonButton;
	}

	public JButton getCadastroTreinadorButton() {
		if (cadastroTreinadorButton == null) {
			cadastroTreinadorButton = new JButton("Cadastrar Treinador");
			cadastroTreinadorButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					new CadastraTreinadorFrame(treinadores,0).setVisible(true);
				}
			});
		}
		return cadastroTreinadorButton;
	}

	public JButton getListarTreinadorButton() {
		if (listarTreinadorButton == null) {
			listarTreinadorButton = new JButton("Listar treinador");
			listarTreinadorButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					new ListarTreinadorFrame().setVisible(true);
				}
			});
		}
		return listarTreinadorButton;
	}

	public JButton getSairButton() {
		if (sairButton == null) {
			sairButton = new JButton("Quit");
			sairButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					new Menu().setVisible(false);
				}
			});
		}
		return sairButton;
	}

	public GridBagConstraints getCadastroDigimonButtonConstraints() {
		if(cadastroDigimonButtonConstraints==null)
		{
			cadastroDigimonButtonConstraints = new GridBagConstraints();
			cadastroDigimonButtonConstraints.gridx = 0;
			cadastroDigimonButtonConstraints.gridy = 0;
		}
		return cadastroDigimonButtonConstraints;
	}

	public GridBagConstraints getListarDigimonButtonConstraints() {
		if(listarDigimonButtonConstraints==null)
		{
			listarDigimonButtonConstraints = new GridBagConstraints();
			listarDigimonButtonConstraints.gridx = 0;
			listarDigimonButtonConstraints.gridy = 1;
		}
		return listarDigimonButtonConstraints;
	}

	public GridBagConstraints getPesquisarDigimonButtonConstraints() {
		if(pesquisarDigimonButtonConstraints==null)
		{
			pesquisarDigimonButtonConstraints = new GridBagConstraints();
			pesquisarDigimonButtonConstraints.gridx = 0;
			pesquisarDigimonButtonConstraints.gridy = 2;
		}
		return pesquisarDigimonButtonConstraints;
	}

	public GridBagConstraints getCadastroTreinadorButtonConstraints() {
		if(cadastroTreinadorButtonConstraints==null)
		{
			cadastroTreinadorButtonConstraints = new GridBagConstraints();
			cadastroTreinadorButtonConstraints.gridx = 0;
			cadastroTreinadorButtonConstraints.gridy = 3;
		}
		return cadastroTreinadorButtonConstraints;
	}

	public GridBagConstraints getListarTreinadorButtonConstraints() {
		if(listarTreinadorButtonConstraints==null)
		{
			listarTreinadorButtonConstraints = new GridBagConstraints();
			listarTreinadorButtonConstraints.gridx = 0;
			listarTreinadorButtonConstraints.gridy = 4;
		}
		return listarTreinadorButtonConstraints;
	}

	public GridBagConstraints getSairButtonConstraints() {
		if(sairButtonConstraints==null)
		{
			sairButtonConstraints = new GridBagConstraints();
			sairButtonConstraints.gridx = 0;
			sairButtonConstraints.gridy = 5;
		}
		return sairButtonConstraints;
	}
	
}

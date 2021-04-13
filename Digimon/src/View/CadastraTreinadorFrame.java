package View;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Model.Evolucao;
import Model.Treinador;

public class CadastraTreinadorFrame extends JFrame{

	private static final long serialVersionUID = 4823461071538298297L;
	
	static private ArrayList<Treinador> treinador= new ArrayList<>();
	
	private JLabel nomeLabel;
	
	private JTextField nomeTextField;
	
	private JButton salvarButton;
	private JButton limparButton;
	private JButton voltarButton;
	
	public static Treinador tempTrei = new Treinador();
	
	private GridBagConstraints nomeLabelConstraints;
	private GridBagConstraints nomeTextFieldConstraints;
	private GridBagConstraints salvarButtonConstraints;
	private GridBagConstraints limparButtonConstraints;
	private GridBagConstraints voltarButtonConstraints;
	
	public CadastraTreinadorFrame(ArrayList<Treinador> treinadores, int pos)
	{
		super("Cadastro de Treinador");
		initialize();
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initialize() {
		
		setLayout(new GridBagLayout());
		
		add(getNomeLabel(),getNomeLabelConstraints());
		add(getNomeTextField(),getNomeTextFieldConstraints());
		add(getSalvarButton(),getSalvarButtonConstraints());
		add(getLimparButton(),getLimparButtonConstraints());
		add(getVoltarButton(),getVoltarButtonConstraints());
		
	}

	public JLabel getNomeLabel() {
		if(nomeLabel==null)
		{
			nomeLabel = new JLabel();
			nomeLabel.setText("Nome:");
		}
		return nomeLabel;
	}
	public JTextField getNomeTextField() {
		if(nomeTextField==null)
		{
			nomeTextField = new JTextField();
			Dimension prefSize = nomeTextField.getPreferredSize();
			prefSize.height = 30;
			prefSize.width = 150;
			nomeTextField.setPreferredSize(prefSize);
		}
		return nomeTextField;
	}
	
	public JButton getSalvarButton() {
		if(salvarButton==null)
		{
			salvarButton = new JButton("Salvar");
			salvarButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String nome=null;
					if(!nomeTextField.getText().equals(""))
					{
						nome = nomeTextField.getText();
						Treinador t = new Treinador();
						t.setNome(nome);
						Menu.treinadores.add(t);
						dispose();
						new Menu().setVisible(true);
					}else{
						JOptionPane.showMessageDialog(null, "Nome do treinador está vazio!");
					}
				}
			});
		}
		return salvarButton;
	}
	public JButton getLimparButton() {
		if(limparButton==null)
		{
			limparButton = new JButton("Limpar");
			limparButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					nomeTextField.setText("");
				}
			});
		}
		return limparButton;
	}
	public JButton getVoltarButton() {
		if(voltarButton==null)
		{
			voltarButton = new JButton("Voltar");
			voltarButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					new Menu().setVisible(true);
					
				}
			});
		}
		return voltarButton;
	}
	public GridBagConstraints getNomeLabelConstraints() {
		if(nomeLabelConstraints==null)
		{
			nomeLabelConstraints = new GridBagConstraints();
			nomeLabelConstraints.gridx = 0;
			nomeLabelConstraints.gridy = 0;
		}
		return nomeLabelConstraints;
	}

	public GridBagConstraints getNomeTextFieldConstraints() {
		if(nomeTextFieldConstraints==null)
		{
			nomeTextFieldConstraints = new GridBagConstraints();
			nomeTextFieldConstraints.gridx = 1;
			nomeTextFieldConstraints.gridy = 0;
		}
		return nomeTextFieldConstraints;
	}

	public GridBagConstraints getSalvarButtonConstraints() {
		if(salvarButtonConstraints==null)
		{
			salvarButtonConstraints = new GridBagConstraints();
			salvarButtonConstraints.gridx = 0;
			salvarButtonConstraints.gridy = 3;
		}
		return salvarButtonConstraints;
	}
	
	public GridBagConstraints getLimparButtonConstraints() {
		if(limparButtonConstraints==null)
		{
			limparButtonConstraints = new GridBagConstraints();
			limparButtonConstraints.gridx = 1;
			limparButtonConstraints.gridy = 3;
		}
		return limparButtonConstraints;
	}
	
	public GridBagConstraints getVoltarButtonConstraints() {
		if(voltarButtonConstraints==null)
		{
			voltarButtonConstraints = new GridBagConstraints();
			voltarButtonConstraints.gridx = 2;
			voltarButtonConstraints.gridy = 3;
		}
		return voltarButtonConstraints;
	}
	
}
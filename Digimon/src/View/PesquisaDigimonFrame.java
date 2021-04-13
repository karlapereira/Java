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

import Model.Digimon;
import Model.Treinador;

public class PesquisaDigimonFrame extends JFrame{

	private static final long serialVersionUID = 4823461071538298297L;
	
	private JLabel nomeLabel;
	
	private JTextField nomeTextField;
	
	private JButton pesquisarButton;
	private JButton voltarButton;
	
	private GridBagConstraints nomeLabelConstraints;
	private GridBagConstraints nomeTextFieldConstraints;
	private GridBagConstraints pesquisarButtonConstraints;
	private GridBagConstraints voltarButtonConstraints;
	
	public PesquisaDigimonFrame()
	{
		super("Pesquisa Digimon");
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
		add(getPesquisarButton(),getPesquisarButtonConstraints());
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
	
	public JButton getPesquisarButton() {
		if(pesquisarButton==null)
		{
			pesquisarButton = new JButton("Pesquisar");
			pesquisarButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int found = 0;
					if(nomeTextField.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Você deve digitar um nome.");
					}
					else{
						for (int i = 0; i < Menu.digimons.size(); i++) {
								if(Menu.digimons.get(i).getNome().equals(nomeTextField.getText())){
									found = 1;
									dispose();
									new ListarDigimon2(Menu.digimons, i).setVisible(true);
								}
								
							}
						if(found == 0){
							JOptionPane.showMessageDialog(null, "Não foi encontrado digimon com este nome.");
							nomeTextField.setText("");
						}
					}
					
				}
			});
		}
		return pesquisarButton;
	}
	public JButton getVoltarButton() {
		if(voltarButton==null)
		{
			voltarButton = new JButton("Voltar");
			voltarButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					
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

	public GridBagConstraints getPesquisarButtonConstraints() {
		if(pesquisarButtonConstraints==null)
		{
			pesquisarButtonConstraints = new GridBagConstraints();
			pesquisarButtonConstraints.gridx = 1;
			pesquisarButtonConstraints.gridy = 3;
		}
		return pesquisarButtonConstraints;
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
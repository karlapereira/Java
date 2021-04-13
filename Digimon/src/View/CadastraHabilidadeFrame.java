package View;

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

import Model.Habilidade;

public class CadastraHabilidadeFrame extends JFrame{

	private static final long serialVersionUID = 6152972220647227112L;

	
	private JLabel nomeHLabel;
	private JButton salvarButton;
	private JButton limparButton;
	private JButton voltarButton;
	private JTextField nomeTextField;
	
	private GridBagConstraints nomeLabelConstraints;
	private GridBagConstraints habilidadeLabelConstraints;
	private GridBagConstraints salvarButtonConstraints;
	private GridBagConstraints limparButtonConstraints;
	private GridBagConstraints voltarButtonConstraints;
	private GridBagConstraints nomeTextFieldConstraints;

	public GridBagConstraints getNomeLabelConstraints() {
		if(nomeLabelConstraints==null)
		{
			nomeLabelConstraints = new GridBagConstraints();
			nomeLabelConstraints.gridx = 0;
			nomeLabelConstraints.gridy = 0;
		}
		
		return nomeLabelConstraints;
	}

	public GridBagConstraints getHabilidadeLabelConstraints() {
		if(habilidadeLabelConstraints==null)
		{
			habilidadeLabelConstraints = new GridBagConstraints();
			habilidadeLabelConstraints.gridx = 2;
			habilidadeLabelConstraints.gridy = 1;
		}
		return habilidadeLabelConstraints;
	}

	public GridBagConstraints getSalvarButtonConstraints() {
		if(salvarButtonConstraints==null)
		{
			salvarButtonConstraints = new GridBagConstraints();
			salvarButtonConstraints.gridx = 2;
			salvarButtonConstraints.gridy = 4;
		}
		return salvarButtonConstraints;
	}

	public GridBagConstraints getLimparButtonConstraints() {
		if(limparButtonConstraints==null)
		{
			limparButtonConstraints = new GridBagConstraints();
			limparButtonConstraints.gridx = 1;
			limparButtonConstraints.gridy = 4;
		}
		return limparButtonConstraints;
	}

	public GridBagConstraints getVoltarButtonConstraints() {
		if(voltarButtonConstraints==null)
		{
			voltarButtonConstraints = new GridBagConstraints();
			voltarButtonConstraints.gridx = 0;
			voltarButtonConstraints.gridy = 4;
		}
		return voltarButtonConstraints;
	}

	public GridBagConstraints getNomeTextFieldConstraints() {
		if(nomeTextFieldConstraints==null)
		{
			nomeTextFieldConstraints = new GridBagConstraints();
			nomeTextFieldConstraints.gridx = 1;
			nomeTextFieldConstraints.gridy = 0;
			nomeTextFieldConstraints.fill = GridBagConstraints.HORIZONTAL;
			nomeTextFieldConstraints.gridwidth = 100;
		}
		return nomeTextFieldConstraints;
	}

	public CadastraHabilidadeFrame() {
		initialize();
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
	}

	private void initialize() {
		// Inicializa o frame e seus botões
		setLayout(new GridBagLayout());
		add(getNomeLabel(), getNomeLabelConstraints());
		add(getNomeTextField(), getNomeTextFieldConstraints());
		add(getVoltarButton(), getVoltarButtonConstraints());
		add(getLimparButton(), getLimparButtonConstraints());
		add(getSalvarButton(), getSalvarButtonConstraints());
	}

	public JLabel getNomeLabel() {
		if (nomeHLabel == null){
			nomeHLabel =  new JLabel("Nome:");
		}
		return nomeHLabel;
	}
	
	public JTextField getNomeTextField(){
		if (nomeTextField == null){
			nomeTextField = new JTextField();
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
					String nomeH;
					if(!nomeTextField.getText().equals(""))
					{
						nomeH = nomeTextField.getText();
						Habilidade hab = new Habilidade(nomeH);
						CadastraDigimonFrame.tempEvol.addHabilidade(hab);
						hab = null;
						dispose();
						new CadastraHabilidadeFrame().setVisible(true);
					}else{
						JOptionPane.showMessageDialog(null, "Existe campos em branco!");
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
					
				}
			});
		}
		return voltarButton;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}

package View;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Model.Digimon;
import Model.Evolucao;
import Model.Habilidade;

public class CadastraDigimonFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7547328896654943355L;
	
	//String lists
	private String elemento[] = {"Fogo", "Água", "Gelo", "Vento", "Trovão", "Madeira", "Metal", "Terra", "Luz", "Trevas"};
	private String level[] = {"Bebê", "Treinamento", "Novato", "Campeao", "Supremo"};
	static private ArrayList<Digimon> digimon = new ArrayList<>();
	
	//Initialize buttons
	private JLabel nomeLabel;
	private JComboBox elementosBox;
	private JLabel elementoLabel;
	private JButton salvarButton;
	private JButton limparButton;
	private JButton voltarButton;
	private JTextField nomeTextField;
	private JButton addHabilidadeButton;
	private JComboBox levelsBox;
	private JLabel levelLabel;
	
	public static Evolucao tempEvol = new Evolucao();
	
	private GridBagConstraints addHabilidadeButtonConstraints;
	private GridBagConstraints levelLabelConstraints;
	private GridBagConstraints elementoLabelConstraints;
	private GridBagConstraints nomeLabelConstraints;
	private GridBagConstraints elementosBoxConstraints;
	private GridBagConstraints habilidadeLabelConstraints;
	private GridBagConstraints levelsBoxConstraints;
	private GridBagConstraints salvarButtonConstraints;
	private GridBagConstraints limparButtonConstraints;
	private GridBagConstraints voltarButtonConstraints;
	private GridBagConstraints nomeTextFieldConstraints;
	
	public GridBagConstraints getlevelLabelConstraints(){
		if(levelLabelConstraints == null){
			levelLabelConstraints = new GridBagConstraints();
			levelLabelConstraints.gridx = 0;
			levelLabelConstraints.gridy = 1;
		}
		return levelLabelConstraints;
	}
	
	
	public JButton getAddHabilidadeButton() {
		if (addHabilidadeButton == null){
			addHabilidadeButton = new JButton("Adicionar Habilidade");
			addHabilidadeButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					new CadastraHabilidadeFrame().setVisible(true);
				}
			});
		}
		return addHabilidadeButton;
	}

	public GridBagConstraints getAddHabilidadeButtonConstraints() {
		if(addHabilidadeButtonConstraints == null){
			addHabilidadeButtonConstraints = new GridBagConstraints();
			addHabilidadeButtonConstraints.gridx = 0;
			addHabilidadeButtonConstraints.gridy = 3;
			addHabilidadeButtonConstraints.fill = GridBagConstraints.HORIZONTAL;
			addHabilidadeButtonConstraints.gridwidth = 100;
		}
		return addHabilidadeButtonConstraints;
	}
	
	public GridBagConstraints getLevelLabelConstraints(){
		if(levelLabelConstraints == null){
			levelLabelConstraints = new GridBagConstraints();
			levelLabelConstraints.gridx = 0;
			levelLabelConstraints.gridy = 1;
		}
		return levelLabelConstraints;
	}
	
	public GridBagConstraints getlevelsBoxConstraints() {
		if(levelsBoxConstraints==null)
		{
			levelsBoxConstraints = new GridBagConstraints();
			levelsBoxConstraints.gridx = 1;
			levelsBoxConstraints.gridy = 1;
			levelsBoxConstraints.gridwidth = 100;
			levelsBoxConstraints.fill = GridBagConstraints.HORIZONTAL;
		}
		return levelsBoxConstraints;
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

	public GridBagConstraints getElementosBoxConstraints() {
		if(elementosBoxConstraints==null)
		{
			elementosBoxConstraints = new GridBagConstraints();
			elementosBoxConstraints.gridx = 1;
			elementosBoxConstraints.gridy = 2;
			elementosBoxConstraints.fill = GridBagConstraints.HORIZONTAL;
			elementosBoxConstraints.gridwidth = 100;
		}
		return elementosBoxConstraints;
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

	public GridBagConstraints getLevelsBoxConstraints() {
		if(levelsBoxConstraints==null)
		{
			levelsBoxConstraints = new GridBagConstraints();
			levelsBoxConstraints.gridx = 1;
			levelsBoxConstraints.gridy = 1;
			levelsBoxConstraints.gridwidth = 100;
			levelsBoxConstraints.fill = GridBagConstraints.HORIZONTAL;
		}
		return levelsBoxConstraints;
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

	public CadastraDigimonFrame() {
		initialize();
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		if(tempEvol == null){
			tempEvol = new Evolucao();
		}
	}

	private void initialize() {
		// Inicializa o frame e seus botões
		setLayout(new GridBagLayout());
		add(getNomeLabel(), getNomeLabelConstraints());
		add(getNomeTextField(), getNomeTextFieldConstraints());
		add(getElementoLabel(), getElementoLabelConstraints());
		add(getElementosBox(), getElementosBoxConstraints());
		add(getVoltarButton(), getVoltarButtonConstraints());
		add(getLimparButton(), getLimparButtonConstraints());
		add(getSalvarButton(), getSalvarButtonConstraints());
		add(getLevelLabel(), getLevelLabelConstraints());
		add(getLevelsBox(), getLevelsBoxConstraints());
		add(getAddHabilidadeButton(), getAddHabilidadeButtonConstraints());
	}

	public JLabel getLevelLabel() {
		if (levelLabel == null){
			levelLabel =  new JLabel("Level:");
		}
		return levelLabel;
	}

	public JComboBox getLevelsBox() {
		if (levelsBox == null){
			levelsBox = new JComboBox(level);
		}
		return levelsBox;
	}
	
	public JLabel getNomeLabel() {
		if (nomeLabel == null){
			nomeLabel =  new JLabel("Nome:");
		}
		return nomeLabel;
	}
	
	public JTextField getNomeTextField(){
		if (nomeTextField == null){
			nomeTextField = new JTextField();
		}
		return nomeTextField;
	}

	public JComboBox getElementosBox() {
		if (elementosBox == null){
			elementosBox = new JComboBox(elemento);
		}
		return elementosBox;
	}

	public JButton getSalvarButton() {
		if(salvarButton==null)
		{
			salvarButton = new JButton("Salvar");
			salvarButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String nome, elemento = null;
					if(!nomeTextField.getText().equals(""))
					{
						nome = nomeTextField.getText();
						elemento = (String)elementosBox.getSelectedItem();
						Digimon d = new Digimon();
						d.setNome(nome);
						tempEvol.setLevel((String)levelsBox.getSelectedItem());
						d.setElemento(elemento);
						d.addEvolucao(tempEvol);
						tempEvol = null;
						Menu.digimons.add(d);
						d = null;
						nome = null;
						elemento = null;
						dispose();
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
					levelsBox.setSelectedIndex(0);
					elementosBox.setSelectedIndex(0);
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

	public JLabel getElementoLabel() {
		if(elementoLabel == null){
			elementoLabel = new JLabel("Elemento:");
		}
		return elementoLabel;
	}

	public GridBagConstraints getElementoLabelConstraints() {
		if (elementoLabelConstraints == null){
			elementoLabelConstraints = new GridBagConstraints();
			elementoLabelConstraints.gridx = 0;
			elementoLabelConstraints.gridy = 2;
		}
		return elementoLabelConstraints;
	}
	
}

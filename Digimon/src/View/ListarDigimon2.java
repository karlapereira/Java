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
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Model.Digimon;

public class ListarDigimon2 extends JFrame {

	private static final long serialVersionUID = -6337335440778313925L;
	private ArrayList<Digimon> digimonsList = new ArrayList<>();
	
	//Declarations
	private JLabel nomeDigimon;
	private JLabel level;
	private JLabel elemento;
	private JLabel evolucoes;
	private JTextArea evolucoesLista;
	private JButton proximoButton;
	private JButton anteriorButton;
	private JButton sairButton;
	private JButton excluirButton;
	private int pos;
	
	private GridBagConstraints nomeDigimonConstraints;
	private GridBagConstraints levelConstraints;
	private GridBagConstraints elementoConstraints;
	private GridBagConstraints evolucoesConstraints;
	private GridBagConstraints evolucoesListaConstraints;
	private GridBagConstraints proximoButtonConstraints;
	private GridBagConstraints anteriorButtonConstraints;
	private GridBagConstraints sairButtonConstraints;
	private GridBagConstraints excluirButtonConstraints;
	
	public ListarDigimon2(){
		initialize();
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	public ListarDigimon2(ArrayList<Digimon> digimons, int pos) {
		initialize();
		this.pos = pos;
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		this.digimonsList = digimons;
		Digimon tempDig = digimons.get(pos);
		nomeDigimon.setText("Nome: "+tempDig.getNome());
		elemento.setText("Elemento: "+tempDig.getElemento());
		int sizeArray = tempDig.getEvolucoes().size();
		level.setText("Level: "+tempDig.getEvolucoes().get(sizeArray-1).getLevel());
		evolucoesLista.setText(tempDig.toString());
		
		if((pos + 2) > digimons.size()){
			proximoButton.setEnabled(false);
		}
		if ((pos-1) < 0){
			anteriorButton.setEnabled(false);
		}
	}

	public void initialize(){
		setLayout(new GridBagLayout());
		add(getNomeDigimon(), getNomeDigimonConstraints());
		add(getLevel(), getLevelConstraints());
		add(getElemento(), getElementoConstraints());
		add(getEvolucoes(), getEvolucoesConstraints());
		add(getEvolucoesLista(), getEvolucoesListaConstraints());
		add(getProximoButton(), getProximoButtonConstraints());
		add(getAnteriorButton(), getAnteriorButtonConstraints());
		add(getSairButton(), getSairButtonConstraints());
		add(getExcluirButton(), getExcluirButtonConstraints());
	}
	
	//Gets and sets
	
	public JLabel getNomeDigimon() {
		if( nomeDigimon == null){
			nomeDigimon = new JLabel("Nome:");//TODO: adicionar o nome do digimon ja direto na frente
		}
		
		return nomeDigimon;
	}
	public JLabel getLevel() {
		if( level == null){
			level = new JLabel("Level:");//TODO: pegar o level
		}
		return level;
	}
	public JLabel getElemento() {
		if(elemento == null){
			elemento = new JLabel("Elemento: ");//TODO: pegar elemento
		}
		return elemento;
	}

	public JLabel getEvolucoes() {
		if(evolucoes == null){
			evolucoes = new JLabel("Evolucoes e habilidades:");
		}
		return evolucoes;
	}
	public JTextArea getEvolucoesLista() {
		if(evolucoesLista == null){
			evolucoesLista = new JTextArea();
			evolucoesLista.setEditable(false);
			evolucoesLista.setPreferredSize(new Dimension(500, 500));
		}
		return evolucoesLista;
	}
	public JButton getProximoButton() {
		if( proximoButton == null){
			proximoButton = new JButton("Próximo >");
			proximoButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					new ListarDigimon2(Menu.digimons, (pos+1)).setVisible(true);
					dispose();
				}
			});
		}
		return proximoButton;
	}
	public JButton getAnteriorButton() {
		if( anteriorButton == null){
			anteriorButton = new JButton("< Anterior");
			anteriorButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					new ListarDigimon2(Menu.digimons, (pos-1)).setVisible(true);
					dispose();
				}
			});
		}
		return anteriorButton;
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
	
	public JButton getExcluirButton() {
		if(excluirButton == null){
			excluirButton = new JButton("Excluir Digimon");
			excluirButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int dialog = JOptionPane.showConfirmDialog(null, "Você quer mesmo excluir este digimon?");
						if(dialog == JOptionPane.YES_OPTION){
							Menu.digimons.remove(pos);
							if (!Menu.digimons.isEmpty()){
								dispose();
								new ListarDigimon2(Menu.digimons, 0);
							}
							else{
								JOptionPane.showMessageDialog(null, "Não há mais digimons cadastrados.");
								dispose();
							}
							
						}
					
				}
			});
		}
		return excluirButton;
	}

	//GridBagConstraints gets
	
	public GridBagConstraints getNomeDigimonConstraints() {
		if( nomeDigimonConstraints == null){
			nomeDigimonConstraints = new GridBagConstraints();
			nomeDigimonConstraints.gridx = 0;
			nomeDigimonConstraints.gridy = 0;
		}
		return nomeDigimonConstraints;
	}
	public GridBagConstraints getLevelConstraints() {
		if( levelConstraints == null){
			levelConstraints = new GridBagConstraints();
			levelConstraints.gridx = 0;
			levelConstraints.gridy = 1;
		}
		return levelConstraints;
	}
	public GridBagConstraints getElementoConstraints() {
		if( elementoConstraints == null){
			elementoConstraints = new GridBagConstraints();
			elementoConstraints.gridx = 0;
			elementoConstraints.gridy = 2;
		}
		return elementoConstraints;
	}

	public GridBagConstraints getEvolucoesConstraints() {
		if( evolucoesConstraints == null){
			evolucoesConstraints = new GridBagConstraints();
			evolucoesConstraints.gridx = 0;
			evolucoesConstraints.gridy = 3;
			
		}
		return evolucoesConstraints;
	}
	public GridBagConstraints getEvolucoesListaConstraints() {
		if( evolucoesListaConstraints == null){
			evolucoesListaConstraints = new GridBagConstraints();
			evolucoesListaConstraints.gridx = 0;
			evolucoesListaConstraints.gridy = 4;
			evolucoesListaConstraints.fill = GridBagConstraints.HORIZONTAL;
			
		}
		return evolucoesListaConstraints;
	}
	public GridBagConstraints getProximoButtonConstraints() {
		if( proximoButtonConstraints == null){
			proximoButtonConstraints = new GridBagConstraints();
			proximoButtonConstraints.gridx = 0;
			proximoButtonConstraints.gridy = 6;
			proximoButtonConstraints.fill = GridBagConstraints.HORIZONTAL;
		}
		return proximoButtonConstraints;
	}
	public GridBagConstraints getAnteriorButtonConstraints() {
		if( anteriorButtonConstraints == null){
			anteriorButtonConstraints = new GridBagConstraints();
			anteriorButtonConstraints.gridx = 0;
			anteriorButtonConstraints.gridy = 5;
			anteriorButtonConstraints.fill = GridBagConstraints.HORIZONTAL;
		}
		return anteriorButtonConstraints;
	}
	public GridBagConstraints getSairButtonConstraints() {
		if( sairButtonConstraints == null){
			sairButtonConstraints = new GridBagConstraints();
			sairButtonConstraints.gridx = 0;
			sairButtonConstraints.gridy = 7;
			sairButtonConstraints.fill = GridBagConstraints.HORIZONTAL;
		}
		return sairButtonConstraints;
	}
	public GridBagConstraints getExcluirButtonConstraints() {
		if( excluirButtonConstraints == null){
			excluirButtonConstraints = new GridBagConstraints();
			excluirButtonConstraints.gridx = 0;
			excluirButtonConstraints.gridy = 8;
			excluirButtonConstraints.fill = GridBagConstraints.HORIZONTAL;
		}
		return excluirButtonConstraints;
	}
}

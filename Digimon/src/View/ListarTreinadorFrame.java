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

import Model.Treinador;

public class ListarTreinadorFrame extends JFrame {

	private static final long serialVersionUID = -6337335440778313925L;
	private ArrayList<Treinador> treinadoresList = new ArrayList<>();
	
	//Declarations
	private JLabel nomeTreinador;
	private JButton proximoButton;
	private JButton anteriorButton;
	private JButton sairButton;
	private JButton excluirButton;
	private int pos;
	
	private GridBagConstraints nomeTreinadorConstraints;
	private GridBagConstraints proximoButtonConstraints;
	private GridBagConstraints anteriorButtonConstraints;
	private GridBagConstraints sairButtonConstraints;
	private GridBagConstraints excluirButtonConstraints;
	
	public ListarTreinadorFrame(){
		initialize();
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	public ListarTreinadorFrame(ArrayList<Treinador> treinadores, int pos) {
		initialize();
		this.pos = pos;
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		this.treinadoresList = treinadores;
		Treinador tempT = treinadores.get(pos);
		nomeTreinador.setText("Nome: "+tempT.getNome());
		
		if((pos + 2) > treinadores.size()){
			proximoButton.setEnabled(false);
		}
		if ((pos-1) < 0){
			anteriorButton.setEnabled(false);
		}
	}

	public void initialize(){
		setLayout(new GridBagLayout());
		add(getNomeTreinador(), getNomeTreinadorConstraints());
		add(getProximoButton(), getProximoButtonConstraints());
		add(getAnteriorButton(), getAnteriorButtonConstraints());
		add(getSairButton(), getSairButtonConstraints());
		add(getExcluirButton(), getExcluirButtonConstraints());
	}
	
	//Gets and sets
	
	public JLabel getNomeTreinador() {
		if( nomeTreinador == null){
			nomeTreinador = new JLabel("Nome:");
		}
		
		return nomeTreinador;
	}
	
	public JButton getProximoButton() {
		if( proximoButton == null){
			proximoButton = new JButton("Próximo >");
			proximoButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					new ListarTreinadorFrame(Menu.treinadores, (pos+1)).setVisible(true);
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
					new ListarTreinadorFrame(Menu.treinadores, (pos-1)).setVisible(true);
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
			excluirButton = new JButton("Excluir Treinador");
			excluirButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int dialog = JOptionPane.showConfirmDialog(null, "Você quer mesmo excluir este treinador?");
						if(dialog == JOptionPane.YES_OPTION){
							Menu.treinadores.remove(pos);
							if (!Menu.treinadores.isEmpty()){
								dispose();
								new ListarTreinadorFrame(Menu.treinadores, 0);
							}
							else{
								JOptionPane.showMessageDialog(null, "Não há mais treinadores cadastrados.");
								dispose();
							}
							
						}
					
				}
			});
		}
		return excluirButton;
	}

	//GridBagConstraints gets
	
	public GridBagConstraints getNomeTreinadorConstraints() {
		if( nomeTreinadorConstraints == null){
			nomeTreinadorConstraints = new GridBagConstraints();
			nomeTreinadorConstraints.gridx = 0;
			nomeTreinadorConstraints.gridy = 0;
		}
		return nomeTreinadorConstraints;
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

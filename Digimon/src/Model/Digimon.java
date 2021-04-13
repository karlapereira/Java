package Model;

import java.util.ArrayList;

public class Digimon{
	
	String nome, elemento;

	public ArrayList<Evolucao> getEvolucoes() {
		return evolucoes;
	}

	public void setEvolucoes(ArrayList<Evolucao> evolucoes) {
		this.evolucoes = evolucoes;
	}

	ArrayList<Evolucao> evolucoes = new ArrayList<Evolucao>();

	public String getElemento() {
		return elemento;
	}

	public void setElemento(String elemento) {
		this.elemento = elemento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void addEvolucao(Evolucao evolucao){
		evolucoes.add(evolucao);
	}
	
	@Override
	public String toString() {
		Evolucao tempEvol = new Evolucao();
		String result = "";
		
		for (int i = 0; i < evolucoes.size(); i++) {
			tempEvol = evolucoes.get(i);
			result+= " Evolucao "+(i+1)+": " + "\n"; 
			result += " Level: " + tempEvol.getLevel() + "\n";
			
			for (int j = 0; j < tempEvol.getHabArraySize(); j++) {
				Habilidade tempHab = new Habilidade();
				tempHab = tempEvol.getArrayElement(j);
				result+= " - Habilidade "+(j+1)+": "+ tempHab.getHabPoder() + "\n";
			}
		}
		return result;
	}

}
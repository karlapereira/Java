package Model;

import java.util.ArrayList;

public class Evolucao{
	
	String level, nomEvol;
	int quantEvol;
	private ArrayList<Habilidade> poderHab = new ArrayList<Habilidade>();
	
	public int getQuantEvol() {
		return quantEvol;
	}

	public void setQuantEvol(int quantEvol) {
		this.quantEvol = quantEvol;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getNomEvol() {
		return nomEvol;
	}

	public void setNomEvol(String nomEvol) {
		this.nomEvol = nomEvol;
	}
	
	public void addHabilidade(Habilidade habil){
		poderHab.add(habil);
	}
	
	public int getHabArraySize(){
		return poderHab.size();
	}
	
	public Habilidade getArrayElement(int index){
		return poderHab.get(index);
	}
}

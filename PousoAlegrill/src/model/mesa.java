package model;

import java.util.ArrayList;

public class Mesa {

	private int id;
	private int numero;
	private float conta;
	private boolean finalizada;
	private ArrayList<Comida> comidas;
	private ArrayList<Bebida> bebidas;
	private int idFinanceiro;

	public Mesa(int id, int numero, float conta, boolean finalizada) {
		super();
		this.id = id;
		this.numero = numero;
		if(conta <= 0) {
			this.conta = 0;
		}
		else {
			this.conta = conta;
		}
		this.comidas = new ArrayList<>();
		this.bebidas = new ArrayList<>();
		this.finalizada = finalizada;
		this.idFinanceiro = 0;
	}
	
	public Mesa(int id, int numero, float conta, boolean finalizada, int financeiro, ArrayList<Comida> comidas, ArrayList<Bebida> bebidas) {
		super();
		this.id = id;
		this.numero = numero;
		if(conta <= 0) {
			this.conta = 0;
		}
		else {
			this.conta = conta;
		}
		this.comidas = comidas;
		this.bebidas = bebidas;
		this.finalizada = finalizada;
		this.idFinanceiro = financeiro;
	}

	public int getId() {
		return id;
	}

	public int getNumero() {
		return numero;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}

	public float getConta() {
		return this.conta;
	}
	
	public void setConta(float desconto) {
		if (idFinanceiro == 0) {
			this.conta = 0;
			if (this.comidas != null) {
				for (Comida comida : this.comidas) {
					this.conta += comida.getPreco();
				}
			}
			if (this.bebidas != null) {
				for (Bebida bebida : this.bebidas) {
					this.conta += bebida.getPreco();
				}
			}
		}
		else {
			desconto = 1 - desconto / 100;
			this.conta *= desconto;
		}
	}

	public ArrayList<Comida> getComidas() {
		return this.comidas;
	}
	
	public void addComida(Comida comida) {
		this.comidas.add(comida);
	}
	
	public void removeComida(Comida comida) {
		this.comidas.remove(comida);
	}

	public ArrayList<Bebida> getBebidas() {
		return this.bebidas;
	}
	
	public void addBebida(Bebida bebida) {
		this.bebidas.add(bebida);
	}
	
	public void removeBebida(Bebida bebida) {
		this.bebidas.remove(bebida);
	}

	public boolean isFinalizada() {
		return this.finalizada;
	}

	public void setFinalizada(boolean finalizada) {
		this.finalizada = finalizada;
	}
	
	public void setFinanceiro(int idFinanceiro) {
		this.idFinanceiro = idFinanceiro;
	}
	
	public int getFinanceiro() {
		return this.idFinanceiro;
	}
	
	public String toString() {		
		return "Num.: " + String.valueOf(this.getNumero()) + " - R$" + String.format("%.2f", this.getConta());
	}
}

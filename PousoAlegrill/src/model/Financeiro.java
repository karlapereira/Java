package model;

public class Financeiro {
	private int id;
	private float conta;
	
	public Financeiro(int id, float conta) {
		super();
		this.id = id;
		this.conta = conta;
	}

	public int getId() {
		return id;
	}

	public float getConta() {
		return conta;
	}
	
	public void desconto(int desconto) {
		float porcentagem = 1 - (float) desconto / 100;
		this.conta *= porcentagem;
	}
	
	public String toString() {
		return "R$" + String.format("%.2f", this.getConta());
	}
}

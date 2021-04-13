package model;

public class Bebida {

	private int id;
	private String nome;
	private Boolean disponivel; //true - disponivel, false - indisponivel
	private float preco;
	private Boolean servida;
	
	public Bebida(int id, String nome, float preco, Boolean disponivel) {
		super();
		this.id = id;
		this.nome = nome;
		this.disponivel = disponivel;
		this.preco = preco;
		this.servida = false;
	}
	
	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
	public Boolean getDisponivel() {
		return disponivel;
	}

	public float getPreco() {
		return preco;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setDisponivel(Boolean disponivel) {
		this.disponivel = disponivel;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}
	
	public Boolean getServida() {
		return servida;
	}
	
	public void setServida(Boolean servida) {
		this.servida = servida;
	}
	
	public String toString() {
		return this.getNome() + " - R$" + String.format("%.2f", this.getPreco());
	}
	
	

}

package model;

public class Usuario {

	private static Usuario usuarioInstance;
	
	private int id;
	private String nome;
	private String email;
	private String senha;
	private int funcao; //0 - não assignado, 1 - admin, 2 - atendimento, 3 - cozinha, 4 - bar

	public Usuario(int id, String nome, String email, String senha, int funcao) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.funcao = funcao;
	}
	
	public Usuario(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.senha = usuario.getSenha();
		this.funcao = usuario.getFuncao();
	}
	
	public static Usuario getInstance() {
		return usuarioInstance;
	}
	
	public static Usuario setUser(Usuario usuario) {
		if (usuarioInstance == null) {
			usuarioInstance = usuario;
		}
		return usuarioInstance;
	}
	
	public static void usuarioLogout() {
		usuarioInstance = null;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public int getFuncao() {
		return funcao;
	}

	public void setFuncao(int funcao) {
		this.funcao = funcao;
	}
	
	public String toString() {
		return this.getNome() + ", " + "\t" + String.valueOf(this.getFuncao()) + ";";
	}
}

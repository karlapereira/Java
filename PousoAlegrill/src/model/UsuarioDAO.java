package model;

import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO extends ConexaoDAO{
	public ArrayList<Usuario> downloadUsers() throws SQLException{
		ArrayList<Usuario> usuario = new ArrayList<Usuario>();
		
		String sql;
		sql = "SELECT *FROM usuario";
		ps = connection.prepareStatement(sql);
		resultSet = ps.executeQuery();
		
		while(resultSet.next()){
			int id = resultSet.getInt("idUsuario");
			String nome = resultSet.getString("nome");
			String email = resultSet.getString("email");
			String senha = resultSet.getString("senha");
			int funcao = resultSet.getInt("funcao"); 
			usuario.add(new Usuario(id, nome, email, senha, funcao));
		}
		return usuario;
	}
	
	public void insertUser(Usuario user) throws SQLException{
		String sql = "INSERT INTO usuario (nome, email, senha, funcao) VALUES (?,?,?,?)";
		ps = connection.prepareStatement(sql);
		ps.setString(1, user.getNome());
		ps.setString(2, user.getEmail());
		ps.setString(3, user.getSenha());
		ps.setInt(4, user.getFuncao());
		ps.executeUpdate();
	}
	
	public void editUser(Usuario user, int id) throws SQLException{
		String sql = "UPDATE usuario SET nome=?, email=?, senha=?, funcao=? WHERE idUsuario=?";
		ps = connection.prepareStatement(sql);
		ps.setString(1, user.getNome());
		ps.setString(2, user.getEmail());
		ps.setString(3, user.getSenha());
		ps.setInt(4, user.getFuncao());
		ps.setInt(5, id);
		ps.executeUpdate();
	}	
	
	public void excluirUser(Usuario user) throws SQLException{
		String sql = "DELETE FROM usuario WHERE idUsuario=?";
		ps = connection.prepareStatement(sql);
		ps.setInt(1, user.getId());
		ps.executeUpdate();
	}
}

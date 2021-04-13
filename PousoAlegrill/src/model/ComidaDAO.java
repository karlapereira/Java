package model;

import java.sql.SQLException;
import java.util.ArrayList;

public class ComidaDAO extends ConexaoDAO{
	public ArrayList<Comida> downloadComidas() throws SQLException{
		ArrayList<Comida> comida = new ArrayList<Comida>();
		
		String sql;
		sql = "SELECT *FROM comida";
		ps = connection.prepareStatement(sql);
		resultSet = ps.executeQuery();
		
		while(resultSet.next()){
			int id = resultSet.getInt("idComida");
			String nome = resultSet.getString("nome");
			Boolean disponivel = resultSet.getBoolean("disponivel");
			float preco = resultSet.getFloat("preco");
			comida.add(new Comida(id, nome, preco, disponivel));
		}
		return comida;
	}
	
	public void insertComida(Comida comida) throws SQLException{
		String sql = "INSERT INTO comida (nome, disponivel, preco) VALUES (?,?,?)";
		ps = connection.prepareStatement(sql);
		ps.setString(1, comida.getNome());
		ps.setBoolean(2, comida.getDisponivel());
		ps.setFloat(3, comida.getPreco());
		ps.executeUpdate();
	}
	
	public void editComida(Comida comida, int id) throws SQLException{
		String sql = "UPDATE comida SET nome=?, disponivel=?, preco=? WHERE idComida=?";
		ps = connection.prepareStatement(sql);
		ps.setString(1,  comida.getNome());
		ps.setBoolean(2, comida.getDisponivel());
		ps.setFloat(3, comida.getPreco());
		ps.setInt(4, id);
		ps.executeUpdate();
	}
	public void excluirComida(Comida comida) throws SQLException{
		String sql = "DELETE FROM comida WHERE idComida=?";
		ps = connection.prepareStatement(sql);
		ps.setInt(1, comida.getId());
		ps.executeUpdate();
	}
}
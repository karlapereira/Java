package model;

import java.sql.SQLException;
import java.util.ArrayList;

public class BebidaDAO extends ConexaoDAO{
	public ArrayList<Bebida> downloadBebidas() throws SQLException{
		ArrayList<Bebida> bebida = new ArrayList<Bebida>();
		
		String sql;
		sql = "SELECT *FROM bebida";
		ps = connection.prepareStatement(sql);
		resultSet = ps.executeQuery();
		
		while(resultSet.next()){
			int id = resultSet.getInt("idBebida");
			String nome = resultSet.getString("nome");
			Boolean disponivel = resultSet.getBoolean("disponivel");
			float preco = resultSet.getFloat("preco");
			bebida.add(new Bebida(id, nome, preco, disponivel));
		}
		return bebida;
	}
	
	public void insertBebida(Bebida bebida) throws SQLException{
		String sql = "INSERT INTO bebida (nome, disponivel, preco) VALUES (?,?,?)";
		ps = connection.prepareStatement(sql);
		ps.setString(1, bebida.getNome());
		ps.setBoolean(2, bebida.getDisponivel());
		ps.setFloat(3, bebida.getPreco());
		ps.executeUpdate();
	}
	
	
	public void editBebida(Bebida bebida, int id) throws SQLException{
		String sql = "UPDATE bebida SET nome=?, disponivel=?, preco=? WHERE idBebida=?";
		ps = connection.prepareStatement(sql);
		ps.setString(1,  bebida.getNome());
		ps.setBoolean(2, bebida.getDisponivel());
		ps.setFloat(3, bebida.getPreco());
		ps.setInt(4, id);
		ps.executeUpdate();
	}
	
	public void excluirBebida(Bebida bebida) throws SQLException{
		String sql = "DELETE FROM bebida WHERE idBebida=?";
		ps = connection.prepareStatement(sql);
		ps.setInt(1, bebida.getId());
		ps.executeUpdate();
	}
}

package model;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.xml.crypto.Data;

public class FinanceiroDAO extends ConexaoDAO{
	public ArrayList<Financeiro> downloadFinancas() throws SQLException{
		ArrayList<Financeiro> financ = new ArrayList<Financeiro>();
		
		String sql;
		sql = "SELECT *FROM financeiro";
		ps = connection.prepareStatement(sql);
		resultSet = ps.executeQuery();
		
		while(resultSet.next()){
			int id = resultSet.getInt("idFinanceiro");
			float conta = resultSet.getFloat("conta");
			financ.add(new Financeiro(id, conta));
		}
		return financ;
	}
	
	public void insertFinanceiro(Financeiro financeiro) throws SQLException{
		String sql = "INSERT INTO financeiro (conta) VALUES (?)";
		ps = connection.prepareStatement(sql);
		ps.setFloat(1, financeiro.getConta());
		ps.executeUpdate();
	}
	
	public void editFinanceiro(Financeiro financeiro, int id) throws SQLException{
		String sql = "UPDATE financeiro SET conta=? WHERE idFinanceiro=?";
		ps = connection.prepareStatement(sql);
		ps.setFloat(1, financeiro.getConta());
		ps.setInt(2, id);
		ps.executeUpdate();
	}	
	
	public void excluirFinanceiro(Financeiro financeiro) throws SQLException{
		String sql = "DELETE FROM financeiro WHERE idFinanceiro=?";
		ps = connection.prepareStatement(sql);
		ps.setInt(1, financeiro.getId());
		ps.executeUpdate();
	}
	
	public int ultimoInserido() throws SQLException{
		String sql = "SELECT LAST_INSERT_ID() FROM financeiro";
		ps = connection.prepareStatement(sql);
		resultSet = ps.executeQuery();
		if (resultSet.last()) {
			return resultSet.getInt("last_insert_id()");
		}
		else {
			return 0;
		}
	}
}
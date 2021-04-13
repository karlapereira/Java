package model;

import java.sql.SQLException;
import java.util.ArrayList;

public class MesaDAO extends ConexaoDAO{
	
	private ComidaDAO comidaDAO = new ComidaDAO();
	private BebidaDAO bebidaDAO = new BebidaDAO();
	
	public ArrayList<Mesa> downloadMesa() throws SQLException{
		ArrayList<Mesa> mesas = new ArrayList<Mesa>();

		String sql;
		sql = "SELECT *FROM mesa";
		ps = connection.prepareStatement(sql);
		resultSet = ps.executeQuery();
		
		while(resultSet.next()){
			int id = resultSet.getInt("idMesa");
			int numero = resultSet.getInt("numero");
			float conta = resultSet.getFloat("conta");
			boolean finalizada = resultSet.getBoolean("finalizada");
			int idFinanceiro = resultSet.getInt("Financeiro_idFinanceiro");
			mesas.add(new Mesa(id,  numero, conta, finalizada, idFinanceiro, getComidas(id), getBebidas(id)));
		}
		return mesas;
	}
	
	public void insertMesa(Mesa mesa) throws SQLException{
		String sql = "INSERT INTO Mesa (numero, conta, finalizada) VALUES (?,?,?)";
		ps = connection.prepareStatement(sql);
		ps.setInt(1, mesa.getNumero());
		ps.setFloat(2, mesa.getConta());
		ps.setBoolean(3, mesa.isFinalizada());
		ps.executeUpdate();
	}
	
	public void editMesa(Mesa mesa, int id) throws SQLException{
		String sql = "UPDATE mesa SET numero=?, conta=?, finalizada=?, Financeiro_idFinanceiro = ? WHERE idMesa=?";
		ps = connection.prepareStatement(sql);
		ps.setInt(1, mesa.getNumero());
		ps.setFloat(2, mesa.getConta());
		ps.setBoolean(3, mesa.isFinalizada());
		ps.setInt(4, mesa.getFinanceiro());
		ps.setInt(5, id);
		ps.executeUpdate();
	}	
	
	public void excluirMesa(Mesa mesa) throws SQLException{
		String sql = "DELETE FROM mesa WHERE idMesa=?";
		ps = connection.prepareStatement(sql);
		ps.setInt(1, mesa.getId());
		ps.executeUpdate();
	}
	
	public void addComida(Mesa mesa, Comida comida) throws SQLException{
		String sql = "INSERT INTO mesa_has_comida (mesa_idMesa, comida_idComida, servida) VALUES (?,?,?)";
		ps = connection.prepareStatement(sql);
		ps.setInt(1, mesa.getId());
		ps.setFloat(2, comida.getId());
		ps.setBoolean(3, false);
		ps.executeUpdate();
	}
	
	public void checkComida(Mesa mesa, Comida comida) throws SQLException{
		String sql = "UPDATE mesa_has_comida SET servida=? WHERE mesa_idMesa=? AND comida_idComida=? AND servida = false ORDER BY mesa_idMesa LIMIT 1";
		ps = connection.prepareStatement(sql);
		ps.setBoolean(1, true);
		ps.setInt(2, mesa.getId());
		ps.setInt(3, comida.getId());
		ps.executeUpdate();
	}
	
	public void removeComida(Mesa mesa, Comida comida) throws SQLException{
		String sql = "DELETE FROM mesa_has_comida WHERE mesa_idMesa=? AND comida_idComida=? ORDER BY mesa_idMesa LIMIT 1";
		ps = connection.prepareStatement(sql);
		ps.setInt(1, mesa.getId());
		ps.setInt(2, comida.getId());
		ps.executeUpdate();
	}
	
	public void removeTodaComida(Comida comida) throws SQLException{
		String sql = "DELETE FROM mesa_has_comida WHERE comida_idComida=?";
		ps = connection.prepareStatement(sql);
		ps.setInt(1, comida.getId());
		ps.executeUpdate();
	}
	
	public void addBebida(Mesa mesa, Bebida bebida) throws SQLException{
		String sql = "INSERT INTO mesa_has_bebida (mesa_idMesa, bebida_idBebida, servida) VALUES (?,?,?)";
		ps = connection.prepareStatement(sql);
		ps.setInt(1, mesa.getId());
		ps.setFloat(2, bebida.getId());
		ps.setBoolean(3, false);
		ps.executeUpdate();
	}
	
	public void checkBebida(Mesa mesa, Bebida bebida) throws SQLException{
		String sql = "UPDATE mesa_has_bebida SET servida=? WHERE mesa_idMesa=? AND bebida_idBebida=? AND servida = false ORDER BY mesa_idMesa LIMIT 1";
		ps = connection.prepareStatement(sql);
		ps.setBoolean(1, true);
		ps.setInt(2, mesa.getId());
		ps.setInt(3, bebida.getId());
		ps.executeUpdate();
	}
	
	public void removeBebida(Mesa mesa, Bebida bebida) throws SQLException{
		String sql = "DELETE FROM mesa_has_bebida WHERE mesa_idMesa=? AND bebida_idBebida=? ORDER BY mesa_idMesa LIMIT 1";
		ps = connection.prepareStatement(sql);
		ps.setInt(1, mesa.getId());
		ps.setInt(2, bebida.getId());
		ps.executeUpdate();
	}
	
	public void removeTodaBebida(Bebida bebida) throws SQLException{
		String sql = "DELETE FROM mesa_has_bebida WHERE bebida_idBebida=?";
		ps = connection.prepareStatement(sql);
		ps.setInt(1, bebida.getId());
		ps.executeUpdate();
	}
	
	private ArrayList<Comida> getComidas(int idMesa) throws SQLException {
		ArrayList<Comida> comidas = new ArrayList<>();
		String sql = "SELECT * FROM mesa_has_comida WHERE mesa_idMesa=?";
		ps2 = connection.prepareStatement(sql);
		ps2.setInt(1, idMesa);
		resultSet2 = ps2.executeQuery();		
		while(resultSet2.next()){		
			comidaDAO.conectar();
			ArrayList<Comida> cardapio = comidaDAO.downloadComidas();
			comidaDAO.desconectar();
			for(Comida comida : cardapio) {
				if (comida.getId() == resultSet2.getInt("comida_idComida")) {
					comida.setServida(resultSet2.getBoolean("servida"));
					comidas.add(comida);					
					break;
				}
			}			
		}
		return comidas;
	}
	
	private ArrayList<Bebida> getBebidas(int idMesa) throws SQLException {
		ArrayList<Bebida> bebidas = new ArrayList<>();
		String sql = "SELECT * FROM mesa_has_bebida WHERE mesa_idMesa=?";
		ps2 = connection.prepareStatement(sql);
		ps2.setInt(1, idMesa);
		resultSet2 = ps2.executeQuery();		
		while(resultSet2.next()){
			bebidaDAO.conectar();
			ArrayList<Bebida> cardapio = bebidaDAO.downloadBebidas();
			bebidaDAO.desconectar();			
			for(Bebida bebida : cardapio) {
				if (bebida.getId() == resultSet2.getInt("bebida_idBebida")) {
					bebida.setServida(resultSet2.getBoolean("servida"));
					bebidas.add(bebida);
					break;
				}
			}
		}
		return bebidas;
	}
}

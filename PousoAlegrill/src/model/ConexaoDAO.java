package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConexaoDAO {
	protected Connection connection = null;
	protected PreparedStatement ps = null;
	protected PreparedStatement ps2 = null;
	protected ResultSet resultSet = null;
	protected ResultSet resultSet2 = null;

	public void conectar(){
		String servidor = "jdbc:mysql://localhost:3306/pousoalegrill_bd?characterEncoding=UTF-8&useSSL=false";
		String usuario = "root"; 
		String senha = "root";
		
		try{
			connection = DriverManager.getConnection(servidor, usuario, senha);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Nao foi possivel conectar ao Banco de Dados","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void desconectar(){
		try {
			connection.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Nao foi possivel conectar ao Banco de Dados","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
}

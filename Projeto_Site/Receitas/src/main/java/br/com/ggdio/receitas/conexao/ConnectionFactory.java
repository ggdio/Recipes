package br.com.ggdio.receitas.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionFactory 
{
	private static final String URL = "jdbc:mysql://localhost:3306/receitas";
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String USUARIO = "receitas_web";
	private static final String SENHA = "receitas@satiecer";
	
	public static Connection getConexao()
	{
		try 
		{
			Class.forName(DRIVER);
			return DriverManager.getConnection(URL, USUARIO, SENHA);
		}
		catch (ClassNotFoundException e)
		{
			throw new RuntimeException(e);
		}
		catch(SQLException e)
		{
			throw new RuntimeException("Não foi possível se conectar com o servidor de dados !", e);
		}
	}
	
}

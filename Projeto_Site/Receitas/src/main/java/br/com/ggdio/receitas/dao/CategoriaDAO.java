package br.com.ggdio.receitas.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;

import br.com.ggdio.projectutils.dao.Dao;
import br.com.ggdio.receitas.conexao.ConnectionFactory;
import br.com.ggdio.receitas.model.Categoria;

public class CategoriaDAO extends Dao<Categoria>
{
	private Connection conexao;

	@Override
	public void adiciona(Categoria categoria) 
	{
		String sql = "INSERT INTO tbl_categoria VALUES (?,?,?)";
		this.conexao = ConnectionFactory.getConexao();
		try 
		{
			//Prepara o statement
			PreparedStatement insert = this.conexao.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			insert.setObject(1, null);
			insert.setString(2, categoria.getTipo());
			insert.setString(3, categoria.getDescricao());
			insert.executeUpdate();
			
			//Recupera o ID gerado
			ResultSet retorno = insert.getGeneratedKeys();
			if(retorno.next())
				categoria.setCodigo(retorno.getLong(1));
			
			//Finaliza
			retorno.close();
			insert.close();
			this.conexao.close();
		} 
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	@Override
	public void altera(Categoria categoria) 
	{
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE tbl_categoria SET ");
		sql.append("tipo = ?,");
		sql.append("descricao = ? ");
		sql.append("WHERE codigo = ?");
		this.conexao = ConnectionFactory.getConexao();
		try
		{
			//Prepara o statement
			PreparedStatement update = this.conexao.prepareStatement(sql.toString());
			update.setString(1, categoria.getTipo());
			update.setString(2, categoria.getDescricao());
			update.setLong(3, categoria.getCodigo());
			update.executeUpdate();
			
			//Finaliza
			update.close();
			this.conexao.close();
		}
		catch(SQLException e)
		{
			throw new RuntimeException(e);
		}
	}

	@Override
	public Categoria get(int codigo) 
	{
		String sql = "SELECT * FROM tbl_categoria WHERE codigo = ?";
		this.conexao = ConnectionFactory.getConexao();
		try
		{
			//Prepara o statement
			PreparedStatement select = this.conexao.prepareStatement(sql);
			select.setInt(1, codigo);
			ResultSet retorno = select.executeQuery();
			
			//Carrega dados
			Categoria categoria =  carregaCategoria(retorno);
			
			//Finaliza
			retorno.close();
			select.close();
			this.conexao.close();
			
			//Retorna
			return categoria;
		}
		catch(SQLException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<Categoria> getLista()
	{
		String sql = "SELECT * FROM tbl_categoria";
		this.conexao = ConnectionFactory.getConexao();
		try
		{
			//Prepara o statement
			Statement select = this.conexao.createStatement();
			ResultSet retorno = select.executeQuery(sql);
			
			//Carrega dados
			List<Categoria> categorias = carregaCategorias(retorno);
			
			//Finaliza
			retorno.close();
			select.close();
			this.conexao.close();
			
			//Retorna
			return categorias;
		}
		catch(SQLException e)
		{
			throw new RuntimeException(e);
		}
	}

	@Override
	public void remove(Categoria categoria) 
	{
		String sql = "DELETE FROM tbl_categoria WHERE codigo = ?";
		this.conexao = ConnectionFactory.getConexao();
		try
		{
			//Prepara o statement
			PreparedStatement delete = this.conexao.prepareStatement(sql);
			delete.setLong(1, categoria.getCodigo());
			delete.executeUpdate();
			
			//Finaliza
			delete.close();
			this.conexao.close();
		}
		catch(SQLException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<Categoria> getPorCampo(String campo, String comparador,Object valor)
	{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM tbl_categoria WHERE ");
		sql.append(campo+" ");
		sql.append(comparador+" ");
		sql.append("?");
		this.conexao = ConnectionFactory.getConexao();
		try
		{
			//Prepara o statement
			PreparedStatement select = this.conexao.prepareStatement(sql.toString());
			select.setObject(1, valor);
			ResultSet retorno = select.executeQuery();
			
			//Carrega dados
			List<Categoria> categorias = carregaCategorias(retorno);
			
			//Finaliza
			retorno.close();
			select.close();
			this.conexao.close();
			
			//Retorna
			return categorias;
		}
		catch(SQLException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	private Categoria carregaCategoria(ResultSet retorno) throws SQLException
	{
		Categoria categoria = new Categoria();
		if(retorno.next())
		{
			categoria.setCodigo(retorno.getLong("codigo"));
			categoria.setTipo(retorno.getString("tipo"));
			categoria.setDescricao(retorno.getString("descricao"));
		}
		return categoria;
	}
	
	private List<Categoria> carregaCategorias(ResultSet retorno) throws SQLException
	{
		List<Categoria> categorias = new ArrayList<Categoria>();
		while(retorno.next())
		{
			Categoria categoria = new Categoria();
			categoria.setCodigo(retorno.getLong("codigo"));
			categoria.setTipo(retorno.getString("tipo"));
			categoria.setDescricao(retorno.getString("descricao"));
			categorias.add(categoria);
		}
		return categorias;
	}

	
}

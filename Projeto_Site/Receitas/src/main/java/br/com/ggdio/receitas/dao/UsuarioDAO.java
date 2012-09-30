package br.com.ggdio.receitas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.ggdio.projectutils.dao.Dao;
import br.com.ggdio.projectutils.tempo.TransformaData;
import br.com.ggdio.receitas.conexao.ConnectionFactory;
import br.com.ggdio.receitas.model.Usuario;

public class UsuarioDAO extends Dao<Usuario>
{
	private Connection conexao;
	
	@Override
	public void adiciona(Usuario usuario) 
	{
		String sql = "INSERT INTO tbl_usuario VALUES (?,?,?,?,?,?,?)";
		this.conexao = ConnectionFactory.getConexao();
		try
		{
			//Prepara o statement
			PreparedStatement insert = this.conexao.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			insert.setObject(1, null);
			insert.setString(2, usuario.getNome());
			insert.setString(3, usuario.getLogin());
			insert.setString(4, usuario.getSenha());
			insert.setBoolean(5, usuario.isAtivo());
			insert.setDate(6, new TransformaData("dd/MM/yyyy kk:mm:ss").getSqlDate(usuario.getUltimoAcesso()));
			insert.setBoolean(7, usuario.isAdmin());
			insert.executeUpdate();
			
			//Recupeta o ID gerado
			ResultSet retorno = insert.getGeneratedKeys();
			if(retorno.next())
				usuario.setCodigo(retorno.getInt(1));
			
			//Finaliza
			retorno.close();
			insert.close();
			this.conexao.close();
		}
		catch(SQLException e)
		{
			throw new RuntimeException(e);
		}
	}

	@Override
	public void altera(Usuario usuario) 
	{
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE tbl_usuario SET ");
		sql.append("nome = ?,");
		sql.append("login = ?,");
		sql.append("senha = ?,");
		sql.append("status = ?,");
		sql.append("ultimoAcesso = ?,");
		sql.append("admin = ? ");
		sql.append("WHERE codigo = ?");
		this.conexao = ConnectionFactory.getConexao();
		try
		{
			//Prepara o statement
			PreparedStatement update = this.conexao.prepareStatement(sql.toString());
			update.setString(1,usuario.getNome());
			update.setString(2,usuario.getLogin());
			update.setString(3,usuario.getSenha());
			update.setBoolean(4,usuario.isAtivo());
			update.setDate(5,new TransformaData("dd/MM/yyyy kk:mm:ss").getSqlDate(usuario.getUltimoAcesso()));
			update.setBoolean(6, usuario.isAdmin());
			update.setLong(7, usuario.getCodigo());
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
	public Usuario get(int codigo) 
	{
		String sql = "SELECT * FROM tbl_usuario WHERE codigo = ?";
		this.conexao = ConnectionFactory.getConexao();
		try
		{
			//Prepara o statement
			PreparedStatement select = this.conexao.prepareStatement(sql);
			select.setInt(1, codigo);
			ResultSet retorno = select.executeQuery();
			
			//Carrega dados
			Usuario usuario = carregaUsuario(retorno);
			
			//Finaliza
			retorno.close();
			select.close();
			this.conexao.close();
			
			return usuario;
		}
		catch(SQLException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public Usuario get(String login,String senha,boolean status) 
	{
		String sql = "SELECT * FROM tbl_usuario WHERE login = ? AND senha = ? AND status = ?";
		this.conexao = ConnectionFactory.getConexao();
		try
		{
			//Prepara o statement
			PreparedStatement select = this.conexao.prepareStatement(sql);
			select.setString(1, login);
			select.setString(2, senha);
			select.setBoolean(3, status);
			ResultSet retorno = select.executeQuery();
			
			//Carrega dados
			Usuario usuario = carregaUsuario(retorno);
			
			//Finaliza
			retorno.close();
			select.close();
			this.conexao.close();
			
			return usuario;
		}
		catch(SQLException e)
		{
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Usuario> getLista() 
	{
		String sql = "SELECT * FROM tbl_usuario";
		this.conexao = ConnectionFactory.getConexao();
		try
		{
			//Prepara o statement
			Statement select = this.conexao.createStatement();
			ResultSet retorno = select.executeQuery(sql);
			
			//Carrega dados
			List<Usuario> usuarios =  carregaUsuarios(retorno);
			
			//Finaliza
			retorno.close();
			select.close();
			this.conexao.close();
			
			//Retorna
			return usuarios;
		}
		catch(SQLException e)
		{
			throw new RuntimeException(e);
		}
	}

	@Override
	public void remove(Usuario usuario) 
	{
		String sql = "DELETE FROM tbl_usuario WHERE codigo = ?";
		this.conexao = ConnectionFactory.getConexao();
		try
		{
			//Prepara o statement
			PreparedStatement delete = this.conexao.prepareStatement(sql);
			delete.setLong(1, usuario.getCodigo());
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
	public List<Usuario> getPorCampo(String campo, String comparador,Object valor) 
	{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM tbl_usuario WHERE ");
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
			List<Usuario> usuarios = carregaUsuarios(retorno);
			
			//Finaliza
			retorno.close();
			select.close();
			this.conexao.close();
			
			return usuarios;
		}
		catch(SQLException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	private Usuario carregaUsuario(ResultSet retorno) throws SQLException
	{
		if(retorno.next())
		{
			Usuario usuario = new Usuario();
			usuario.setCodigo(retorno.getLong("codigo"));
			usuario.setNome(retorno.getString("nome"));
			usuario.setLogin(retorno.getString("login"));
			usuario.setSenha(retorno.getString("senha"));
			usuario.setStatus(retorno.getBoolean("status"));
			usuario.setUltimoAcesso(new TransformaData("dd/MM/yyyy kk:mm:ss").getCalendar(retorno.getDate("ultimoAcesso")));
			usuario.setAdmin(retorno.getBoolean("admin"));
			return usuario;
		}
		return null;
	}
	
	private List<Usuario> carregaUsuarios(ResultSet retorno) throws SQLException
	{
		List<Usuario> usuarios = new ArrayList<Usuario>();
		while(retorno.next())
		{
			Usuario usuario = new Usuario();
			usuario.setCodigo(retorno.getLong("codigo"));
			usuario.setNome(retorno.getString("nome"));
			usuario.setLogin(retorno.getString("login"));
			usuario.setSenha(retorno.getString("senha"));
			usuario.setStatus(retorno.getBoolean("status"));
			usuario.setUltimoAcesso(new TransformaData("dd/MM/yyyy kk:mm:ss").getCalendar(retorno.getDate("ultimoAcesso")));
			usuario.setAdmin(retorno.getBoolean("admin"));
			usuarios.add(usuario);
		}
		return usuarios;
	}
	
}

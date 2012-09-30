package br.com.ggdio.receitas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.ggdio.projectutils.dao.Dao;
import br.com.ggdio.projectutils.tempo.TransformaData;
import br.com.ggdio.receitas.conexao.ConnectionFactory;
import br.com.ggdio.receitas.model.Receita;
import br.com.ggdio.receitas.model.Usuario;

public class ReceitaDAO extends Dao<Receita>
{
	private Connection conexao;
	
	@Override
	public void adiciona(Receita receita) 
	{
		String sql = "INSERT INTO tbl_receita VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		this.conexao = ConnectionFactory.getConexao();
		try
		{
			//Prepara o statement
			PreparedStatement insert = this.conexao.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			insert.setObject(1,null);
			insert.setString(2,receita.getTitulo());
			insert.setString(3,receita.getDescricao());
			insert.setString(4,receita.getIngredientes(";"));
			insert.setLong(5, receita.getCategoria().getCodigo());
			insert.setString(6,receita.getPreparo());
			insert.setInt(7,receita.getRendimento());
			insert.setInt(8,receita.getTempo());
			insert.setString(9,receita.getImagem());
			insert.setLong(10, receita.getAutor().getCodigo());
			insert.setString(11,receita.getTags(";"));
			insert.setTimestamp(12, new TransformaData("dd/MM/yyyy kk:mm:ss").getTimestamp(receita.getData()));
			insert.setLong(13, receita.getVisualizacoes());
			insert.executeUpdate();
			
			//Recupera ID
			ResultSet retorno = insert.getGeneratedKeys();
			if(retorno.next())
				receita.setCodigo(retorno.getLong(1));
			
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
	public void altera(Receita receita) 
	{
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE tbl_receita SET ");
		sql.append("titulo = ?,");
		sql.append("descricao = ?,");
		sql.append("ingredientes = ?,");
		sql.append("categoria = ?,");
		sql.append("preparo = ?,");
		sql.append("rendimento = ?,");
		sql.append("tempo = ?,");
		sql.append("imagem = ?,");
		sql.append("autor = ?,");
		sql.append("tags = ?,");
		sql.append("data = ?,");
		sql.append("visualizacoes = ? ");
		sql.append("WHERE codigo = ?");
		this.conexao = ConnectionFactory.getConexao();
		try
		{
			//Prepara o statement
			PreparedStatement update = this.conexao.prepareStatement(sql.toString());
			update.setString(1,receita.getTitulo());
			update.setString(2,receita.getDescricao());
			update.setString(3,receita.getIngredientes(";"));
			update.setLong(4, receita.getCategoria().getCodigo());
			update.setString(5,receita.getPreparo());
			update.setInt(6,receita.getRendimento());
			update.setInt(7,receita.getTempo());
			update.setString(8,receita.getImagem());
			update.setLong(9, receita.getAutor().getCodigo());
			update.setString(10,receita.getTags(";"));
			update.setTimestamp(11, new TransformaData("dd/MM/yyyy kk:mm:ss").getTimestamp(receita.getData()));
			update.setLong(12, receita.getVisualizacoes());
			update.setLong(13, receita.getCodigo());
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
	public Receita get(int codigo) 
	{
		String sql = "SELECT * FROM tbl_receita WHERE codigo = ?";
		this.conexao = ConnectionFactory.getConexao();
		try
		{
			//Prepara o statement
			PreparedStatement select = this.conexao.prepareStatement(sql);
			select.setLong(1, codigo);
			ResultSet retorno = select.executeQuery();
			
			//Carrega dados
			Receita receita = carregaReceita(retorno);
			
			//Finaliza
			retorno.close();
			select.close();
			this.conexao.close();
			
			//Retorna
			return receita;
		}
		catch(SQLException e)
		{
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Receita> getLista() 
	{
		String sql = "SELECT * FROM tbl_receita";
		this.conexao = ConnectionFactory.getConexao();
		try
		{
			//Prepara o statement
			Statement select = this.conexao.createStatement();
			ResultSet retorno = select.executeQuery(sql);
			
			//Carrega dados
			List<Receita> receitas = carregaReceitas(retorno);
			
			//Finaliza
			retorno.close();
			select.close();
			this.conexao.close();
			
			//Retorna
			return receitas;
		}
		catch(SQLException e)
		{
			throw new RuntimeException(e);
		}
	}

	@Override
	public void remove(Receita receita)
	{
		String sql = "DELETE FROM tbl_receita WHERE codigo = ?";
		this.conexao = ConnectionFactory.getConexao();
		try
		{
			//Prepara o statement
			PreparedStatement delete = this.conexao.prepareStatement(sql);
			delete.setLong(1, receita.getCodigo());
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
	public List<Receita> getPorCampo(String campo, String comparador,Object valor) 
	{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM tbl_receita WHERE ");
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
			List<Receita> receitas = carregaReceitas(retorno);
			
			//Fianliza
			retorno.close();
			select.close();
			this.conexao.close();
			
			//Retorna
			return receitas;
		}
		catch(SQLException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public List<Receita> getMaisRecentes()
	{
		String sql = "SELECT * FROM tbl_receita ORDER BY data DESC";
		this.conexao = ConnectionFactory.getConexao();
		try
		{
			//Prepara o statement
			Statement select = this.conexao.createStatement();
			ResultSet retorno = select.executeQuery(sql);
			
			//Carrega dados
			List<Receita> receitas = carregaReceitas(retorno);
			
			//Fianliza
			retorno.close();
			select.close();
			this.conexao.close();
			
			//Retorna
			return receitas;
		}
		catch(SQLException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public List<Receita> getMaisRecentes(int limite)
	{
		String sql = "SELECT * FROM tbl_receita ORDER BY data DESC LIMIT "+limite;
		this.conexao = ConnectionFactory.getConexao();
		try
		{
			//Prepara o statement
			Statement select = this.conexao.createStatement();
			ResultSet retorno = select.executeQuery(sql);
			
			//Carrega dados
			List<Receita> receitas = carregaReceitas(retorno);
			
			//Fianliza
			retorno.close();
			select.close();
			this.conexao.close();
			
			//Retorna
			return receitas;
		}
		catch(SQLException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public List<Receita> getMaisVisualizadas(int limite)
	{
		String sql = "SELECT * FROM tbl_receita WHERE visualizacoes > 0 ORDER BY visualizacoes DESC LIMIT "+limite;
		this.conexao = ConnectionFactory.getConexao();
		try
		{
			//Prepara o statement
			Statement select = this.conexao.createStatement();
			ResultSet retorno = select.executeQuery(sql);
			
			//Carrega dados
			List<Receita> receitas = carregaReceitas(retorno);
			
			//Fianliza
			retorno.close();
			select.close();
			this.conexao.close();
			
			//Retorna
			return receitas;
		}
		catch(SQLException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public List<Receita> pesquisaPorTag(String busca)
	{
		String sql = "SELECT *,MATCH(titulo,tags) AGAINST(?) AS compatibilidade FROM tbl_receita "
				   + "WHERE MATCH(titulo,tags) AGAINST(?) ORDER BY(compatibilidade) DESC LIMIT 50";
		this.conexao = ConnectionFactory.getConexao();
		try
		{
			//Prepara o statement
			PreparedStatement select = this.conexao.prepareStatement(sql);
			select.setString(1, busca);
			select.setString(2, busca);
			ResultSet retorno = select.executeQuery();
			
			//Carrega dados
			List<Receita> receitas = carregaReceitas(retorno);
			
			//Fianliza
			retorno.close();
			select.close();
			this.conexao.close();
			
			//Retorna
			return receitas;
		}
		catch(SQLException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	private Receita carregaReceita(ResultSet retorno) throws SQLException
	{
		if(retorno.next())
		{
			//Dados imutáveis
			Usuario autor = new UsuarioDAO().get(retorno.getInt("autor"));
			Calendar data = new TransformaData("dd/MM/yyyy kk:mm:ss").getCalendar(retorno.getTimestamp("data"));
			
			//Monta o objeto
			Receita receita = new Receita(autor, data);
			receita.setCodigo(retorno.getLong(Receita.CAMPO_CODIGO));
			receita.setTitulo(retorno.getString(Receita.CAMPO_TITULO));
			receita.setDescricao(retorno.getString(Receita.CAMPO_DESCRICAO));
			receita.setIngredientes(retorno.getString(Receita.CAMPO_INGREDIENTES), ";");
			receita.setCategoria(new CategoriaDAO().get(retorno.getInt(Receita.CAMPO_CATEGORIA)));
			receita.setPreparo(retorno.getString(Receita.CAMPO_PREPARO));
			receita.setRendimento(retorno.getInt(Receita.CAMPO_RENDIMENTO));
			receita.setTempo(retorno.getInt(Receita.CAMPO_TEMPO));
			receita.setImagem(retorno.getString(Receita.CAMPO_IMAGEM));
			receita.setTags(retorno.getString(Receita.CAMPO_TAGS), ";");
			receita.setVisualizacoes(retorno.getLong(Receita.CAMPO_VISUALIZACOES));
			return receita;
		}
		return null;
	}
	
	private List<Receita> carregaReceitas(ResultSet retorno) throws SQLException
	{
		List<Receita> receitas = new ArrayList<Receita>();
		while(retorno.next())
		{
			//Dados imutáveis
			Usuario autor = new UsuarioDAO().get(retorno.getInt("autor"));
			Calendar data = new TransformaData("dd/MM/yyyy kk:mm:ss").getCalendar(retorno.getTimestamp("data"));
			
			//Monta o objeto
			Receita receita = new Receita(autor, data);
			receita.setCodigo(retorno.getLong(Receita.CAMPO_CODIGO));
			receita.setTitulo(retorno.getString(Receita.CAMPO_TITULO));
			receita.setDescricao(retorno.getString(Receita.CAMPO_DESCRICAO));
			receita.setIngredientes(retorno.getString(Receita.CAMPO_INGREDIENTES), ";");
			receita.setCategoria(new CategoriaDAO().get(retorno.getInt(Receita.CAMPO_CATEGORIA)));
			receita.setPreparo(retorno.getString(Receita.CAMPO_PREPARO));
			receita.setRendimento(retorno.getInt(Receita.CAMPO_RENDIMENTO));
			receita.setTempo(retorno.getInt(Receita.CAMPO_TEMPO));
			receita.setImagem(retorno.getString(Receita.CAMPO_IMAGEM));
			receita.setTags(retorno.getString(Receita.CAMPO_TAGS), ";");
			receita.setVisualizacoes(retorno.getLong(Receita.CAMPO_VISUALIZACOES));
			receitas.add(receita);
		}
		return receitas;
	}
	
}

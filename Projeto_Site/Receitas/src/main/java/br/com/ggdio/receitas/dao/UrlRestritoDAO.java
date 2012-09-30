package br.com.ggdio.receitas.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.ggdio.projectutils.dao.Dao;
import br.com.ggdio.receitas.conexao.ConnectionFactory;
import br.com.ggdio.receitas.model.UrlRestrito;
import br.com.ggdio.receitas.util.ContextoSistema;

public class UrlRestritoDAO extends Dao<UrlRestrito>
{
	private Connection conexao;
	private final ContextoSistema contexto;
	
	public UrlRestritoDAO(ContextoSistema contexto) 
	{
		this.contexto = contexto;
	}

	@Override
	public void adiciona(UrlRestrito url) 
	{
		
	}

	@Override
	public void altera(UrlRestrito url) 
	{
		
	}

	@Override
	public UrlRestrito get(int codigo) 
	{
		String sql = "SELECT * FROM tbl_url_restrito WHERE codigo = "+codigo;
		this.conexao = ConnectionFactory.getConexao();
		try
		{
			//Prepara o statement
			Statement select = this.conexao.createStatement();
			ResultSet retorno = select.executeQuery(sql);
			
			//Carrega dados
			UrlRestrito url = this.carregaUrl(retorno);
			
			//Finaliza
			retorno.close();
			select.close();
			this.conexao.close();
			
			//Retorna
			return url;
		}
		catch(SQLException e)
		{
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<UrlRestrito> getLista() 
	{
		String sql = "SELECT * FROM tbl_url_restrito";
		this.conexao = ConnectionFactory.getConexao();
		try
		{
			//Prepara o statement
			Statement select = this.conexao.createStatement();
			ResultSet retorno = select.executeQuery(sql);
			
			//Carrega dados
			List<UrlRestrito> urls = this.carregaUrls(retorno);
			
			//Finaliza
			retorno.close();
			select.close();
			this.conexao.close();
			
			//Retorna
			return urls;
		}
		catch(SQLException e)
		{
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<UrlRestrito> getPorCampo(String campo, String comparador, Object valor) 
	{
		return null;
	}

	@Override
	public void remove(UrlRestrito url) 
	{
		
	}
	
	private UrlRestrito carregaUrl(ResultSet retorno) throws SQLException
	{
		if(retorno.next())
		{
			UrlRestrito url = new UrlRestrito(this.contexto);
			url.setCodigo(retorno.getLong(UrlRestrito.CAMPO_CODIGO));
			url.setUrl(retorno.getString(UrlRestrito.CAMPO_URL));
			url.setDescricao(retorno.getString(UrlRestrito.CAMPO_DESCRICAO));
			url.setAdminRestrito(retorno.getBoolean(UrlRestrito.CAMPO_ADMIN_RESTRITO));
			return url;
		}
		return null;
	}
	
	private List<UrlRestrito> carregaUrls(ResultSet retorno) throws SQLException
	{
		List<UrlRestrito> urls = new ArrayList<UrlRestrito>();
		UrlRestrito url = this.carregaUrl(retorno);
		while(url != null)
		{
			urls.add(url);
			url = this.carregaUrl(retorno);
		}
		return urls;
	}
	
}

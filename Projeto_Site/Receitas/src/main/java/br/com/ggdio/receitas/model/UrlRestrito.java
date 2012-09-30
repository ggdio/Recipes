package br.com.ggdio.receitas.model;

import br.com.ggdio.receitas.util.ContextoSistema;

public final class UrlRestrito 
{
	private long codigo;
	private String url;
	private String descricao;
	private boolean admin;
	private final ContextoSistema contexto;
	
	public static final String CAMPO_CODIGO = "codigo";
	public static final String CAMPO_URL = "url";
	public static final String CAMPO_DESCRICAO = "descricao";
	public static final String CAMPO_ADMIN_RESTRITO = "admin";
	
	@Override
	public String toString() 
	{
		return "codigo: "+this.codigo
			  +"\nurl: "+this.url
			  +"\ndescrição: "+this.descricao
		 	  +"\nrestrito_admin: "+this.admin;
	}
	
	@Override
	public boolean equals(Object obj) 
	{
		if(obj instanceof UrlRestrito)
		{
			UrlRestrito url = (UrlRestrito)obj;
			return url.getUrl().equals(this.url) || this.url.startsWith(url.getUrl());
		}
		return false;
	}
	
	public UrlRestrito(ContextoSistema contexto) 
	{
		this.contexto = contexto;
	}
	public UrlRestrito(ContextoSistema contexto,String url) 
	{
		this.contexto = contexto;
		this.url = trataUrl(url);
	}
	
	public long getCodigo() 
	{
		return codigo;
	}
	public String getUrl() 
	{
		return url;
	}
	public String getUrlSemContexto()
	{
		return url.replace(this.contexto.getDiretorioWebAplicacao(), "");
	}
	public String getDescricao() 
	{
		return descricao;
	}
	public boolean isAdminRestrito() 
	{
		return admin;
	}
	public void setCodigo(long codigo) 
	{
		this.codigo = codigo;
	}
	public void setUrl(String url) 
	{
		this.url = trataUrl(url);
	}
	public void setDescricao(String descricao) 
	{
		this.descricao = descricao;
	}
	public void setAdminRestrito(boolean restrito)
	{
		this.admin = restrito;
	}
	
	private String trataUrl(String url)
	{
		String retorno = this.contexto.getDiretorioWebAplicacao();
		if(!url.equals(retorno))
		{
			if(!url.startsWith(retorno))
				retorno += url;
			else
				retorno = url;
		}
		return retorno;
	}
}

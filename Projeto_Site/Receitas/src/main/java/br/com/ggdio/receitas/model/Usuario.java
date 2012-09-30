package br.com.ggdio.receitas.model;

import java.util.Calendar;

public class Usuario implements Cloneable
{
	private long codigo;
	private String nome;
	private String login;
	private String senha;
	private Boolean status;
	private Calendar ultimoAcesso;
	private Boolean admin;
	
	public static final String CAMPO_CODIGO = "codigo";
	public static final String CAMPO_NOME = "nome";
	public static final String CAMPO_LOGIN = "login";
	public static final String CAMPO_SENHA = "senha";
	public static final String CAMPO_STATUS = "status";
	public static final String CAMPO_ULTIMO_ACESSO = "ultimoAcesso";
	public static final String CAMPO_ADMIN = "admin";
	
	@Override
	public boolean equals(Object obj) 
	{
		if(obj instanceof Usuario)
		{
			Usuario usuario = (Usuario) obj;
			if(usuario.getCodigo() == this.codigo && usuario.getNome().equals(this.nome) &&
			   usuario.getLogin().equals(this.login) && usuario.getSenha().equals(this.senha) &&
			   usuario.isAtivo() == this.status && usuario.getUltimoAcesso().equals(this.ultimoAcesso) &&
			   usuario.isAdmin() == this.admin)
			{
				return true;
			}
		}
		return false;
	}
	
	public Usuario clone()
	{
		Usuario usuario = new Usuario();
		usuario.setCodigo(this.codigo);
		usuario.setNome(this.nome);
		usuario.setLogin(this.login);
		usuario.setSenha(this.senha);
		usuario.setStatus(this.status);
		usuario.setUltimoAcesso(this.ultimoAcesso);
		usuario.setAdmin(this.admin);
		return usuario;
	}
	
	public long getCodigo() 
	{
		return codigo;
	}
	public String getNome() 
	{
		return nome;
	}
	public String getLogin()
	{
		return login;
	}
	public String getSenha() 
	{
		return senha;
	}
	public Boolean isAtivo() 
	{
		return status;
	}
	public Calendar getUltimoAcesso() 
	{
		return ultimoAcesso;
	}
	public Boolean isAdmin() 
	{
		return admin;
	}
	public void setCodigo(long codigo) 
	{
		this.codigo = codigo;
	}
	public void setNome(String nome) 
	{
		this.nome = nome;
	}
	public void setLogin(String login)
	{
		this.login = login;
	}
	public void setSenha(String senha) 
	{
		this.senha = senha;
	}
	public void setStatus(Boolean status) 
	{
		this.status = status;
	}
	public void setUltimoAcesso(Calendar ultimoAcesso) 
	{
		this.ultimoAcesso = ultimoAcesso;
	}
	public void setAdmin(Boolean admin) 
	{
		this.admin = admin;
	}
}

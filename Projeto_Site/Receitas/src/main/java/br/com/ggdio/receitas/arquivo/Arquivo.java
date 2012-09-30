package br.com.ggdio.receitas.arquivo;

import java.util.Calendar;

public class Arquivo
{
	private String nome;
	private String extensao;
	private long tamanho;
	private String caminhoReal;
	private String caminhoWeb;
	private Calendar ultimaModificacao;
	
	public String getNome() 
	{
		return nome;
	}
	public String getExtensao() 
	{
		return extensao;
	}
	public long getTamanho() 
	{
		return tamanho;
	}
	public String getCaminhoReal()
	{
		return caminhoReal;
	}
	public String getCaminhoWeb()
	{
		return caminhoWeb;
	}
	public Calendar getUltimaModificacao() 
	{
		return ultimaModificacao;
	}
	public void setNome(String nome)
	{
		this.nome = nome;
	}
	public void setExtensao(String extensao) 
	{
		this.extensao = extensao;
	}
	public void setTamanho(long tamanho)
	{
		this.tamanho = tamanho;
	}
	public void setCaminhoReal(String caminhoReal) 
	{
		this.caminhoReal = caminhoReal;
	}
	public void setCaminhoWeb(String caminhoWeb)
	{
		this.caminhoWeb = caminhoWeb;
	}
	public void setUltimaModificacao(Calendar ultimaModificacao)
	{
		this.ultimaModificacao = ultimaModificacao;
	}

}

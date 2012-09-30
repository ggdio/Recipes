package br.com.ggdio.receitas.response;

public class Response<T>
{
	public static final String SUCESSO = "Sucesso";
	public static final String ERRO = "Erro";
	private String status;
	private String mensagem;
	private T informacao;
	
	public Response(){}
	
	public Response(T informacao) 
	{
		this.informacao = informacao;
	}
	
	public String getStatus() 
	{
		return status;
	}
	public String getMensagem() 
	{
		return mensagem;
	}
	public T getInformacao()
	{
		return informacao;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}
	public void setMensagem(String mensagem) 
	{
		this.mensagem = mensagem;
	}
	public void setInformacao(T informacao) 
	{
		this.informacao = informacao;
	}
}

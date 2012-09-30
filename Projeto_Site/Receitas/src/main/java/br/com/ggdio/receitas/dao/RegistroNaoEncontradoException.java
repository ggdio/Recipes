package br.com.ggdio.receitas.dao;

@SuppressWarnings("serial")
public class RegistroNaoEncontradoException extends RuntimeException
{
	public RegistroNaoEncontradoException(String msg) 
	{
		super(msg);
	}
}

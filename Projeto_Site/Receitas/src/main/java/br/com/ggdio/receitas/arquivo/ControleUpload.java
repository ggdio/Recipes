package br.com.ggdio.receitas.arquivo;

import java.io.File;

public final class ControleUpload
{
	private final int TAMANHO_EXTENSAO = 4;
	private File diretorio;
	private String separador;
	
	public ControleUpload(File diretorio,String separador)
	{
		this.diretorio = diretorio;
		this.separador = separador;
	}
	
	public ControleUpload(String diretorio,String separador)
	{
		this(new File(diretorio),separador);
	}
	
	public int identificaProximoUpload()
	{
		int ultimo = 0;
		if(this.diretorio.exists())
		{
			for(File arquivo : this.diretorio.listFiles())
			{
				try
				{
					final String nome = arquivo.getName();
					if(!nome.equals("Thumbs.db"))
					{
						String valor = nome.substring(nome.indexOf(this.separador)+separador.length(),nome.length()-TAMANHO_EXTENSAO);
						final int posicao = Integer.parseInt(valor);
						if(posicao > ultimo)
							ultimo = posicao;
					}
				}
				catch(Exception e)
				{
					break;
				}
			}
		}
		return ultimo+1;
	}
	
	public File getDiretorio() 
	{
		return this.diretorio;
	}
}

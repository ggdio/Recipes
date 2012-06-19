package br.com.ggdio.receitas.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class Receita
{
	private long codigo;
	private String titulo;
	private String descricao;
	private List<String> ingredientes;
	private Categoria categoria;
	private String preparo;
	private int rendimento;
	private int tempo;
	private String imagem;
	private final Usuario autor;
	private final Calendar data;
	private List<String> tags;
	
	public Receita(Usuario autor,Calendar data)
	{
		this.autor = autor.clone();
		this.data = data;
		this.ingredientes = new ArrayList<String>();
		this.tags = new ArrayList<String>();
	}
	
	@Override
	public boolean equals(Object obj) 
	{
		if(obj instanceof Receita)
		{
			Receita receita = (Receita) obj;
			if(receita.getCodigo() == this.codigo && receita.getTitulo().equals(this.titulo) &&
			   receita.getDescricao().equals(this.descricao) && receita.getIngredientes().equals(this.ingredientes) &&
			   receita.getCategoria().equals(this.categoria) && receita.getPreparo().equals(this.preparo) &&
			   receita.getRendimento() == this.rendimento && receita.getTempo() == this.tempo &&
			   receita.getImagem().equals(this.imagem) && receita.getAutor().equals(this.autor) &&
			   receita.getData().equals(this.data) && receita.getTags().equals(this.tags))
			{
				return true;
			}
		}
		return false;
	}

	public long getCodigo() 
	{
		return codigo;
	}

	public String getTitulo() 
	{
		return titulo;
	}

	public List<String> getIngredientes() 
	{
		return ingredientes;
	}
	
	public String getIngredientes(String separador)
	{
		StringBuilder builder = new StringBuilder();
		for(String ingrediente : this.ingredientes)
		{
			builder.append(ingrediente);
			builder.append(separador);
		}
		return builder.toString();
	}

	public Categoria getCategoria() 
	{
		return categoria;
	}

	public String getPreparo() 
	{
		return preparo;
	}

	public int getRendimento() 
	{
		return rendimento;
	}
	
	public int getTempo() 
	{
		return tempo;
	}

	public String getImagem() 
	{
		return imagem;
	}
	
	public Usuario getAutor()
	{
		return this.autor.clone();
	}
	
	public Calendar getData() 
	{
		return (Calendar)this.data.clone();
	}

	public String getDescricao() 
	{
		return descricao;
	}

	public List<String> getTags()
	{
		return tags;
	}
	
	public String getTags(String separador)
	{
		StringBuilder builder = new StringBuilder();
		for(String tag : this.tags)
		{
			builder.append(tag);
			builder.append(separador);
		}
		return builder.toString();
	}

	public void setCodigo(long codigo)
	{
		this.codigo = codigo;
	}

	public void setTitulo(String titulo)
{
		this.titulo = titulo;
	}

	public void setIngredientes(List<String> ingredientes) 
	{
		this.ingredientes = ingredientes;
	}
	
	public void setIngredientes(String ingredientes,String separador) 
	{
		this.ingredientes = Arrays.asList(ingredientes.split(separador));
	}

	public void setCategoria(Categoria categoria)
	{
		this.categoria = categoria;
	}

	public void setPreparo(String preparo) 
	{
		this.preparo = preparo;
	}

	public void setRendimento(int rendimento) 
	{
		this.rendimento = rendimento;
	}
	
	public void setTempo(int tempo) 
	{
		this.tempo = tempo;
	}

	public void setImagem(String imagem) 
	{
		this.imagem = imagem;
	}

	public void setDescricao(String descricao) 
	{
		this.descricao = descricao;
	}

	public void setTags(List<String> tags) 
	{
		this.tags = tags;
	}
	
	public void setTags(String tags,String separador) 
	{
		this.tags = Arrays.asList(tags.split(separador));
	}

}

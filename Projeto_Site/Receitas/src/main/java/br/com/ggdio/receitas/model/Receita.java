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
	private List<String> tags;
	private final Calendar data;
	private long visualizacoes;
	
	public static final String CAMPO_CODIGO = "codigo";
	public static final String CAMPO_TITULO = "titulo";
	public static final String CAMPO_DESCRICAO = "descricao";
	public static final String CAMPO_INGREDIENTES = "ingredientes";
	public static final String CAMPO_CATEGORIA = "categoria";
	public static final String CAMPO_PREPARO = "preparo";
	public static final String CAMPO_RENDIMENTO = "rendimento";
	public static final String CAMPO_TEMPO = "tempo";
	public static final String CAMPO_IMAGEM = "imagem";
	public static final String CAMPO_AUTOR = "autor";
	public static final String CAMPO_TAGS = "tags";
	public static final String CAMPO_DATA = "data";
	public static final String CAMPO_VISUALIZACOES = "visualizacoes";
	
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
	
	public void incrementaVisualizacao()
	{
		this.visualizacoes++;
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
		if(this.ingredientes.size() == 0) return "";
		StringBuilder builder = new StringBuilder();
		builder.append(this.ingredientes.get(0));
		for(int aux = 1;aux < this.ingredientes.size();aux++)
		{
			builder.append(separador);
			builder.append(ingredientes.get(aux));
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
		if(this.tags.size() == 0) return "";
		StringBuilder builder = new StringBuilder();
		builder.append(this.tags.get(0));
		for(int aux = 1;aux < this.tags.size();aux++)
		{
			builder.append(separador);
			builder.append(tags.get(aux));
		}
		return builder.toString();
	}
	
	public long getVisualizacoes() 
	{
		return visualizacoes;
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
		if(!ingredientes.isEmpty())
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
		if(!tags.isEmpty())
			this.tags = Arrays.asList(tags.split(separador));
	}
	public void setVisualizacoes(long visualizacoes)
	{
		this.visualizacoes = visualizacoes;
	}

}

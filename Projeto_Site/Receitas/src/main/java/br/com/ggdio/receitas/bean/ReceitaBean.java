package br.com.ggdio.receitas.bean;

import java.util.Calendar;
import java.util.List;

import br.com.ggdio.receitas.model.Categoria;
import br.com.ggdio.receitas.model.Usuario;

public class ReceitaBean 
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
	private Usuario autor;
	private Calendar data;
	private List<String> tags;
	private long visualizacoes;
	
	public long getCodigo() 
	{
		return codigo;
	}
	public String getTitulo() 
	{
		return titulo;
	}
	public String getDescricao() 
	{
		return descricao;
	}
	public List<String> getIngredientes() 
	{
		return ingredientes;
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
		return autor;
	}
	public Calendar getData()
	{
		return data;
	}
	public List<String> getTags() 
	{
		return tags;
	}
	public long getVisualizacoes() 
	{
		return this.visualizacoes;
	}
	public void setCodigo(long codigo) 
	{
		this.codigo = codigo;
	}
	public void setTitulo(String titulo) 
	{
		this.titulo = titulo;
	}
	public void setDescricao(String descricao) 
	{
		this.descricao = descricao;
	}
	public void setIngredientes(List<String> ingredientes) 
	{
		this.ingredientes = ingredientes;
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
	public void setAutor(Usuario autor)
	{
		this.autor = autor;
	}
	public void setData(Calendar data)
	{
		this.data = data;
	}
	public void setTags(List<String> tags) 
	{
		this.tags = tags;
	}
	public void setVisualizacoes(long visualizacoes)
	{
		this.visualizacoes = visualizacoes;
	}
}

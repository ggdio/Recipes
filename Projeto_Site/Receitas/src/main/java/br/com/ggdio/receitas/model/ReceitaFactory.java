package br.com.ggdio.receitas.model;

import java.util.Calendar;

import br.com.ggdio.receitas.bean.ReceitaBean;

public class ReceitaFactory 
{
	public Receita constroiReceitaPorBean(ReceitaBean bean)
	{
		Receita receita = new Receita(bean.getAutor(), bean.getData());
		receita.setCodigo(bean.getCodigo());
		String titulo = bean.getTitulo().replaceAll("\\\\", "");
		titulo = titulo.replaceAll("/", "");
		receita.setTitulo(titulo);
		receita.setDescricao(bean.getDescricao());
		receita.setIngredientes(bean.getIngredientes());
		receita.setCategoria(bean.getCategoria());
		receita.setPreparo(bean.getPreparo());
		receita.setRendimento(bean.getRendimento());
		receita.setTempo(bean.getTempo());
		receita.setImagem(bean.getImagem());
		receita.setTags(bean.getTags());
		receita.setVisualizacoes(bean.getVisualizacoes());
		return receita;
	}
	
	public Receita constroiReceitaPorBean(ReceitaBean bean,Usuario usuario,Calendar data)
	{
		bean.setAutor(usuario);
		bean.setData(data);
		return this.constroiReceitaPorBean(bean);
	}
	
	public Receita constroiReceitaPorBean(ReceitaBean bean,Usuario usuario,Calendar data,Categoria categoria)
	{
		bean.setAutor(usuario);
		bean.setData(data);
		bean.setCategoria(categoria);
		return this.constroiReceitaPorBean(bean);
	}
}

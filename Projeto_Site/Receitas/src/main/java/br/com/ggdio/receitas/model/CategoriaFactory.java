package br.com.ggdio.receitas.model;

import br.com.ggdio.receitas.bean.CategoriaBean;

public class CategoriaFactory
{
	public Categoria constroiCategoriaPorBean(CategoriaBean bean)
	{
		Categoria categoria = new Categoria();
		categoria.setCodigo(bean.getCodigo());
		categoria.setDescricao(bean.getDescricao());
		categoria.setTipo(bean.getTipo());
		categoria.setImagem(bean.getImagem());
		return categoria;
	}
}

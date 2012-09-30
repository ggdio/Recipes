package br.com.ggdio.receitas.converter;

import java.util.List;

import br.com.ggdio.projectutils.dao.Dao;
import br.com.ggdio.receitas.converter.Converter;
import br.com.ggdio.receitas.dao.CategoriaDAO;
import br.com.ggdio.receitas.dao.RegistroNaoEncontradoException;
import br.com.ggdio.receitas.model.Categoria;

public class CategoriaConverter implements Converter<Categoria, String> 
{
	@Override
	public String convertTo(Categoria objeto) 
	{
		if(objeto == null) throw new NullPointerException("A categoria informada é nula");
		if(objeto.getTipo() == null) throw new NullPointerException("O tipo da categoria informada é nulo");
		return objeto.getTipo();
	}

	@Override
	public Categoria convertFrom(String objeto) 
	{
		if(objeto == null) throw new NullPointerException("O tipo da categoria informada é nulo");
		List<Categoria> categorias = new CategoriaDAO().getPorCampo(Categoria.CAMPO_TIPO, Dao.EQUALS, objeto);
		if(categorias.isEmpty()) throw new RegistroNaoEncontradoException("A categoria do tipo ["+objeto+"] não foi encontrada !");
		return categorias.get(0);
	}
}

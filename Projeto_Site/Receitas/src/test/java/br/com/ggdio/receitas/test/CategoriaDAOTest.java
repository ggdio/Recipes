package br.com.ggdio.receitas.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import br.com.ggdio.projectutils.dao.Dao;
import br.com.ggdio.receitas.dao.CategoriaDAO;
import br.com.ggdio.receitas.model.Categoria;

public class CategoriaDAOTest
{
	private Dao<Categoria> dao;
	
	public CategoriaDAOTest() 
	{
		this.dao = new CategoriaDAO();
	}
	
//INSERT COM SUCESSO
//	@Test
//	public void testInsertCategoriaDAO()
//	{
//		Categoria categoria = new Categoria();
//		categoria.setTipo("Carnes");
//		categoria.setDescricao("Receitas de carnes");
//		Boolean ok = true;
//		try
//		{
//			this.dao.adiciona(categoria);
//		}
//		catch(RuntimeException e)
//		{
//			ok = false;
//		}
//		Assert.assertTrue(ok);
//	}
	
	@Test
	public void testSelectCategoria()
	{
		Categoria categoria = this.dao.get(4);
		Assert.assertEquals("Carnes", categoria.getTipo());
	}
	
	@Test
	public void testSelectCategoriaPorCampoSemRuntimeExceptionComEquals()
	{
		Boolean ok = true;
		try
		{
			this.dao.getPorCampo("tipo", Dao.EQUALS, "Carnes");
		}
		catch(RuntimeException e)
		{
			ok = false;
		}
		Assert.assertTrue(ok);
	}
	
	@Test
	public void testSelectCategoriaPorCampoSemRuntimeExceptionComLike()
	{
		Boolean ok = true;
		try
		{
			this.dao.getPorCampo("tipo", Dao.LIKE, "%Carne%");
		}
		catch(RuntimeException e)
		{
			ok = false;
		}
		Assert.assertTrue(ok);
	}
	
	@Test
	public void testSelectCategoriaPorCampoComEquals()
	{
		List<Categoria> categorias = this.dao.getPorCampo("tipo", Dao.EQUALS, "Carnes");
		Assert.assertEquals("Carnes", categorias.get(0).getTipo());
	}
	
	
	@Test
	public void testSelectCategoriaPorCampoComLike()
	{
		List<Categoria> categorias = this.dao.getPorCampo("tipo", Dao.LIKE, "Carne%");
		Assert.assertEquals("Carnes", categorias.get(0).getTipo());
	}
	
	
	
	@Test
	public void testUpdateCategoriaSemRuntimeException()
	{
		Boolean ok = true;
		try
		{
			this.dao.altera(this.dao.get(4));
		}
		catch(RuntimeException e)
		{
			ok = false;
		}
		Assert.assertTrue(ok);
	}
	
	@Test
	public void testUpdateDescricaoCategoria()
	{
		Categoria categoria = this.dao.get(4);
		categoria.setDescricao("Todos os tipos de receitas de carnes");
		this.dao.altera(categoria);
		Assert.assertEquals("Todos os tipos de receitas de carnes", this.dao.get(4).getDescricao());
	}
	
	@Test
	public void testDeleteCategoriaDAO()
	{
		Categoria categoria = new Categoria();
		categoria.setTipo("Teste de Delete - VALOR ESPECÍFICO");
		categoria.setDescricao("Descrição");
		this.dao.adiciona(categoria);
		this.dao.remove(categoria);
		int quantidade = this.dao.getPorCampo("tipo", Dao.EQUALS, "Teste de Delete - VALOR ESPECÍFICO").size();
		Assert.assertEquals(0,quantidade);
	}
}

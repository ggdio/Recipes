package br.com.ggdio.receitas.test;


import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.ggdio.projectutils.dao.Dao;
import br.com.ggdio.receitas.dao.CategoriaDAO;
import br.com.ggdio.receitas.dao.ReceitaDAO;
import br.com.ggdio.receitas.dao.UsuarioDAO;
import br.com.ggdio.receitas.model.Categoria;
import br.com.ggdio.receitas.model.Receita;
import br.com.ggdio.receitas.model.Usuario;

public class ReceitaDAOTest
{
	private Dao<Receita> dao;
	
	public ReceitaDAOTest() 
	{
		this.dao = new ReceitaDAO();
	}
	
//SUCESSO
	@Test
	public void testInsertReceita()
	{
		Usuario autor = new UsuarioDAO().get(1);
		Categoria categoria = new CategoriaDAO().get(1);
		Receita receita = new Receita(autor, Calendar.getInstance());
		receita.setCategoria(categoria);
		receita.setTitulo("Calabresa C/ Pimenta");
		receita.setDescricao("Calabresa Apimentada");
		receita.setIngredientes("linguiça calabresa;pimenta vermelha;", ";");
		receita.setCategoria(categoria);
		receita.setPreparo("Fritar");
		receita.setRendimento(2);
		receita.setTempo(20);
		receita.setImagem("foto");
		receita.setTags("calabresa;", ";");
		this.dao.adiciona(receita);
	}

	@Test
	public void testSelectReceita()
	{
		Receita receita = this.dao.get(1);
		Assert.assertEquals("Receita Filet Mignon",receita.getTitulo());
	}

	
	@Test
	public void testSelectReceitaPorCampoSemRuntimeExceptionComEquals()
	{
		Boolean ok = true;
		try
		{
			this.dao.getPorCampo("titulo", Dao.EQUALS, "Receita Filet Mignon");
		}
		catch(RuntimeException e)
		{
			ok = false;
		}
		Assert.assertTrue(ok);
	}

	@Test
	public void testSelectReceitaPorCampoSemRuntimeExceptionComLike()
	{
		Boolean ok = true;
		try
		{
			this.dao.getPorCampo("titulo", Dao.LIKE, "%Filet%");
		}
		catch(RuntimeException e)
		{
			ok = false;
		}
		Assert.assertTrue(ok);
	}
	
	@Test
	public void testSelectReceitaPorCampoComEquals()
	{
		List<Receita> receitas = this.dao.getPorCampo("titulo", Dao.EQUALS, "Receita Filet Mignon");
		Assert.assertEquals("Receita Filet Mignon", receitas.get(0).getTitulo());
	}

	@Test
	public void testSelectReceitaPorCampoComLike()
	{
		List<Receita> receitas = this.dao.getPorCampo("titulo", Dao.LIKE, "%Filet%");
		Assert.assertEquals("Receita Filet Mignon", receitas.get(0).getTitulo());
	}

	@Test
	public void testUpdateReceitaSemRuntimeException()
	{
		Boolean ok = true;
		try
		{
			this.dao.altera(this.dao.get(1));
		}
		catch(RuntimeException e)
		{
			ok = false;
		}
		Assert.assertTrue(ok);
	}

	@Test
	public void testUpdateReceitaDescricao()
	{
		Receita receita = this.dao.get(1);
		receita.setDescricao("Descrição Alterada");
		this.dao.altera(receita);
		Assert.assertEquals("Descrição Alterada", this.dao.get(1).getDescricao());
	}
	
	@Test
	public void testDeleteReceita()
	{
		Usuario autor = new UsuarioDAO().get(1);
		Categoria categoria = new CategoriaDAO().get(1);
		Receita receita = new Receita(autor, Calendar.getInstance());
		receita.setCategoria(categoria);
		receita.setTitulo("Receita para Deleta");
		receita.setDescricao("VALOR ESPECÍFICO PARA BUSCAR NO SELECT");
		receita.setIngredientes("abc;123;456;", ";");
		receita.setCategoria(categoria);
		receita.setPreparo("Tostar");
		receita.setRendimento(5);
		receita.setTempo(10);
		receita.setImagem("foto");
		receita.setTags("carne;salgado;molho;", ";");
		this.dao.adiciona(receita);
		this.dao.remove(receita);
		int quantidade = this.dao.getPorCampo("descricao", Dao.EQUALS, "VALOR ESPECÍFICO PARA BUSCAR NO SELECT").size();
		Assert.assertEquals(0,quantidade);
	}
}

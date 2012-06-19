package br.com.ggdio.receitas.test;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import br.com.ggdio.receitas.dao.ReceitaDAO;
import br.com.ggdio.receitas.model.Receita;
import br.com.ggdio.receitas.model.Usuario;

public class ReceitaTest 
{

	@Test
	public void testTags() 
	{
		Receita receita = new Receita(new Usuario(), Calendar.getInstance());
		receita.setTags("tag1;tag2;tag3;tag4;", ";");
		Assert.assertEquals(4, receita.getTags().size());
	}
	
	@Test
	public void testIngredientes() 
	{
		Receita receita = new Receita(new Usuario(), Calendar.getInstance());
		receita.setIngredientes("ingrediente1;ingrediente2;ingrediente3;ingrediente4;ingrediente1;", ";");
		Assert.assertEquals(5, receita.getIngredientes().size());
	}
	
	@Test
	public void testComparacaoReceita() 
	{
		Receita receitaA = new ReceitaDAO().get(1);
		Receita receitaB = new ReceitaDAO().get(1);
		Assert.assertTrue(receitaA.equals(receitaB));
	}

}

package br.com.ggdio.receitas.test;

import org.junit.Test;

import br.com.ggdio.receitas.dao.ReceitaDAO;

public class PaginacaoTest 
{
	@Test
	public void testQuantidadeRegistros() 
	{
		new ReceitaDAO().get(1);
	}
}

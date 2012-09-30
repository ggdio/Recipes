package br.com.ggdio.receitas.test;

import java.util.Arrays;
import java.util.Calendar;

import br.com.ggdio.receitas.model.Receita;
import br.com.ggdio.receitas.model.Usuario;


public class TesteGeral 
{
	public static void main(String[] args) 
	{
		Receita receita = new Receita(new Usuario(), Calendar.getInstance());
		receita.setTags(Arrays.asList("Tag A"));
		System.out.println(receita.getTags(","));
	}
}

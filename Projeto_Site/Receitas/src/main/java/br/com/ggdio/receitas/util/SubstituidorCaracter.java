package br.com.ggdio.receitas.util;

public class SubstituidorCaracter
{
	public String substituiAcentos(String valor) 
	{
		valor = valor.replaceAll("[�����]", "A");
		valor = valor.replaceAll("[�����]", "a");
		valor = valor.replaceAll("[����]", "E");
		valor = valor.replaceAll("[����]", "e");
		valor = valor.replaceAll("����", "I");
		valor = valor.replaceAll("����", "i");
		valor = valor.replaceAll("[�����]", "O");
		valor = valor.replaceAll("[�����]", "o");
		valor = valor.replaceAll("[����]", "U");
		valor = valor.replaceAll("[����]", "u");
		valor = valor.replaceAll("�", "C");
		valor = valor.replaceAll("�", "c");
		valor = valor.replaceAll("[��]", "y");
		valor = valor.replaceAll("�", "Y");
		valor = valor.replaceAll("�", "n");
		valor = valor.replaceAll("�", "N");
		valor = valor.replaceAll("['<>\\|/]", "");
		return valor;
	}
}

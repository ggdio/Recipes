package br.com.ggdio.receitas.util;

public class SubstituidorCaracter
{
	public String substituiAcentos(String valor) 
	{
		valor = valor.replaceAll("[ÂÀÁÄÃ]", "A");
		valor = valor.replaceAll("[âãàáä]", "a");
		valor = valor.replaceAll("[ÊÈÉË]", "E");
		valor = valor.replaceAll("[êèéë]", "e");
		valor = valor.replaceAll("ÎÍÌÏ", "I");
		valor = valor.replaceAll("îíìï", "i");
		valor = valor.replaceAll("[ÔÕÒÓÖ]", "O");
		valor = valor.replaceAll("[ôõòóö]", "o");
		valor = valor.replaceAll("[ÛÙÚÜ]", "U");
		valor = valor.replaceAll("[ûúùü]", "u");
		valor = valor.replaceAll("Ç", "C");
		valor = valor.replaceAll("ç", "c");
		valor = valor.replaceAll("[ıÿ]", "y");
		valor = valor.replaceAll("İ", "Y");
		valor = valor.replaceAll("ñ", "n");
		valor = valor.replaceAll("Ñ", "N");
		valor = valor.replaceAll("['<>\\|/]", "");
		return valor;
	}
}

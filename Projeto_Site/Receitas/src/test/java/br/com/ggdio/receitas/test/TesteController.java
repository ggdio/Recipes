package br.com.ggdio.receitas.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/testes/")
public class TesteController 
{
	@RequestMapping("upload")
	public String getForm()
	{
		return "teste/teste";
	}
}


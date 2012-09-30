package br.com.ggdio.receitas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/arearestrita")
public class AreaRestritaController 
{
	@RequestMapping("/painel")
	public String painelControle()
	{
		return "areaRestrita/painel";
	}
}

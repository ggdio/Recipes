package br.com.ggdio.receitas.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teste")
public class TestController 
{
	@RequestMapping("/carrossel")
	public String testeCarrossel()
	{
		return "teste/teste";
	}
	
	@RequestMapping("/exception")
	public String testeException()
	{
		throw new RuntimeException("Foi causada uma Exception");
	}
}

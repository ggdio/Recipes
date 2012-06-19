package br.com.ggdio.receitas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController 
{
	@RequestMapping("/formulario")
	public String formulario()
	{
		return "login/formulario";
	}
}

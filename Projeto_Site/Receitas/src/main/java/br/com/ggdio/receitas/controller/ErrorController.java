package br.com.ggdio.receitas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ErrorController 
{
	@RequestMapping(value={"404","400"})
	public String pageNotFound()
	{
		return "error/404";
	}
	
	@RequestMapping(value={"exception","500"})
	public String exception(Exception exception)
	{
		return "error/exception";
	}
}

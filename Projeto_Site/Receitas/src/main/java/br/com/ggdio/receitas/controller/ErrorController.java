package br.com.ggdio.receitas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ErrorController 
{
	@RequestMapping("404")
	public String pageNotFound()
	{
		return "error/404";
	}
	
	@RequestMapping("exception")
	public String exception(Exception exception)
	{
		return "error/exception";
	}
}
package br.com.ggdio.receitas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController
{
	@RequestMapping("/")
	public String indexAction()
	{
		return "index/index";
	}
}

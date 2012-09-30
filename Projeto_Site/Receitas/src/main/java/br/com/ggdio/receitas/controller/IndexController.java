package br.com.ggdio.receitas.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.ggdio.receitas.dao.ReceitaDAO;
import br.com.ggdio.receitas.model.Receita;

@Controller
@RequestMapping("/")
public class IndexController
{
	private ReceitaDAO dao;
	
	public IndexController() 
	{
		this.dao = new ReceitaDAO();
	}
	
	@RequestMapping("")
	public String indexAction(Model model)
	{
		//Recupera as receitas mais recentes
		List<Receita> receitasRecente = this.dao.getMaisRecentes(5);
		model.addAttribute("receitasRecente", receitasRecente);
		
		//Recupera as receitas mais visualizadas
		List<Receita> receitasDestaque = this.dao.getMaisVisualizadas(6);
		model.addAttribute("receitasDestaque", receitasDestaque);
		
		return "index";
	}
}

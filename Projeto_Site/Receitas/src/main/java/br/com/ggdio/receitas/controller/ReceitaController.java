package br.com.ggdio.receitas.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.ggdio.projectutils.dao.Dao;
import br.com.ggdio.receitas.dao.CategoriaDAO;
import br.com.ggdio.receitas.dao.ReceitaDAO;
import br.com.ggdio.receitas.model.Categoria;
import br.com.ggdio.receitas.model.Receita;

@Controller
@RequestMapping("/receitas")
public class ReceitaController 
{
	private Dao<Receita> dao;
	
	public ReceitaController() 
	{
		this.dao = new ReceitaDAO();
	}
	
	@RequestMapping("/")
	public void inicio()
	{
		//Index das receitas
	}
	
	@RequestMapping("/formulario")
	public String formularioAdiciona(Model model)
	{
		List<Categoria> categorias = new CategoriaDAO().getLista();
		model.addAttribute("categorias", categorias);
		return "receita/adiciona";
	}
	
	@RequestMapping("/lista")
	public String listaReceitas(Model model)
	{
		List<Receita> receitas = this.dao.getLista();
		model.addAttribute("receitas", receitas);
		return "receita/lista";
	}
	
	@RequestMapping("/{titulo}")
	public void exibeReceita(@PathVariable String titulo)
	{
		//Implementar
	}
	
	@RequestMapping("/adiciona")
	public void adicionaReceita(Receita receita)
	{
		//Implementar
	}
	
	@RequestMapping("/altera")
	public void alteraReceita(Receita receita)
	{
		//Implementar
	}
	
	@RequestMapping("/remove")
	public void removeReceita(Receita receita)
	{
		//Implementar
	}
}

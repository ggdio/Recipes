package br.com.ggdio.receitas.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.ggdio.receitas.bean.CategoriaBean;
import br.com.ggdio.receitas.dao.CategoriaDAO;
import br.com.ggdio.receitas.model.Categoria;
import br.com.ggdio.receitas.model.CategoriaFactory;
import br.com.ggdio.receitas.model.Receita;

@Controller
@RequestMapping("/categorias")
public class CategoriaController 
{
	private CategoriaDAO categoriaDao;
	
	public CategoriaController() 
	{
		categoriaDao = new CategoriaDAO();
	}
	
	@RequestMapping("/formulario")
	public String formularioAdiciona()
	{
		return "categoria/formulario";
	}
	
	@RequestMapping(value="/adiciona",method=RequestMethod.POST)
	public void adicionaCategoria(CategoriaBean categoriaBean,HttpServletResponse response)
	{	
		//Constrói a receita
		Categoria categoria = new CategoriaFactory().constroiCategoriaPorBean(categoriaBean);
		
		//Grava
		this.categoriaDao.adiciona(categoria);
		
		response.setStatus(200);
	}
	
	@RequestMapping(value="/altera",method=RequestMethod.POST)
	public void alteraCategoria(CategoriaBean categoriaBean,HttpServletResponse response)
	{
		//Constrói a receita
		Categoria categoria = new CategoriaFactory().constroiCategoriaPorBean(categoriaBean);
		
		//Altera
		this.categoriaDao.altera(categoria);
		
		response.setStatus(200);
	}
	
	@RequestMapping(value="/remove",method=RequestMethod.POST)
	public void removeCategoria(Receita receita)
	{
		
	}
	
}

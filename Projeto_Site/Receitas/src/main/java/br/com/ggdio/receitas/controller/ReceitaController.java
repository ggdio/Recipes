package br.com.ggdio.receitas.controller;

import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.ggdio.projectutils.dao.Dao;
import br.com.ggdio.receitas.bean.ReceitaBean;
import br.com.ggdio.receitas.converter.CategoriaConverter;
import br.com.ggdio.receitas.dao.CategoriaDAO;
import br.com.ggdio.receitas.dao.ReceitaDAO;
import br.com.ggdio.receitas.dao.UsuarioDAO;
import br.com.ggdio.receitas.model.Categoria;
import br.com.ggdio.receitas.model.Receita;
import br.com.ggdio.receitas.model.ReceitaFactory;
import br.com.ggdio.receitas.model.Usuario;
import br.com.ggdio.receitas.response.Response;
import br.com.ggdio.receitas.response.ResponseUtil;
import br.com.ggdio.receitas.util.SubstituidorCaracter;

@Controller
@RequestMapping("/receitas")
public class ReceitaController 
{
	private Dao<Receita> daoReceita;
	private Dao<Categoria> daoCategoria;
	private Dao<Usuario> daoUsuario;
	private ResponseUtil responseUtil;
	
	public ReceitaController()
	{
		this.daoReceita = new ReceitaDAO();
		this.daoCategoria = new CategoriaDAO();
		this.daoUsuario = new UsuarioDAO();
		this.responseUtil = new ResponseUtil();
	}
	
	@RequestMapping("/formulario")
	public String formularioAdiciona(Model model)
	{
		//Lista de categorias para ComboBox
		List<Categoria> categorias = this.daoCategoria.getLista();
		model.addAttribute("categorias", categorias);
		
		//Converter das categorias
		model.addAttribute("converter", new CategoriaConverter());
		
		//View
		return "receita/formulario";
	}
	
	@RequestMapping("/altera/{titulo}/{codigo}")
	public String formularioAltera(@PathVariable("codigo")long codigo,Model model,HttpServletResponse response)
	{
		//Código inválido
		if(codigo <= 0) return responseUtil.redirectPaginaInexistente(response);
		
		//Consulta pelo codigo
		Receita receita = this.daoReceita.get((int)codigo);
		
		//Receita inexistente
		if(receita == null) return responseUtil.redirectPaginaInexistente(response);
		
		//Retorna a view com os dados
		model.addAttribute("receita", receita);
		model.addAttribute("categorias", this.daoCategoria.getLista());
		model.addAttribute("converter", new CategoriaConverter());
		return "receita/formulario";
	}
	
	@RequestMapping("/pesquisa/{busca}")
	public String pesquisaReceitas(@PathVariable("busca") String busca,Model model)
	{
		//Remove os sinais
		String valor = busca.replaceAll(Pattern.quote("+"), " ");
		
		//Listagem
		List<Receita> receitas = ((ReceitaDAO)this.daoReceita).pesquisaPorTag(valor);
		
		//Resposta para o client
		Response<List<Receita>> response = new Response<List<Receita>>(receitas);
		
		//Monta a mensagem
		int size = receitas.size();
		if(size <= 0)
		{
			response.setStatus(Response.ERRO);
			response.setMensagem("Nenhum registro foi encontrado na busca por: '"+valor+"'");
		}
		else
		{
			response.setStatus(Response.SUCESSO);
			if(size == 1)
			{
				response.setMensagem("Foi encontrado 1 resultado !");
			}
			else
			{
				response.setMensagem("Foram encontrados "+size+" resultados !");
			}
		}
			
		//Retorno dos valores
		model.addAttribute("response",response);
		return "receita/lista";
	}
	
	@RequestMapping("/lista")
	public String listaReceitas(Model model)
	{
		//Listagem
		List<Receita> receitas = this.daoReceita.getLista();
		
		//Resposta para o client
		Response<List<Receita>> response = new Response<List<Receita>>(receitas);
		
		//Nenhum valor encontrado
		if(receitas.size() == 0)
		{
			response.setMensagem("Não existem registros ainda !");
			response.setStatus(Response.ERRO);
		}
		
		//Retorno dos valores
		model.addAttribute("response", response);
		return "receita/lista";
	}
	
	@RequestMapping("/{titulo}/{codigo}")
	public String exibeReceita(@PathVariable("titulo") String titulo,@PathVariable("codigo")long codigo,Model model,HttpServletResponse response)
	{
		//Código inválido
		if(codigo <= 0) return responseUtil.redirectPaginaInexistente(response);
		
		//Recupera do banco
		Receita receita = this.daoReceita.get((int)codigo);
		
		//Verifica se existe a receita
		if(receita == null) return responseUtil.redirectPaginaInexistente(response);
		
		SubstituidorCaracter substituidor = new SubstituidorCaracter();
		
		//Verificação de igualdade no título
		String tituloRetornado = substituidor.substituiAcentos(receita.getTitulo().toLowerCase());
		String tituloInformado = substituidor.substituiAcentos(titulo.replace("-", " ").toLowerCase());
		if(!tituloRetornado.equals(tituloInformado))
		{
			String pagina = "/receitas/"+tituloRetornado.replace(" ", "-").toLowerCase()+"/"+codigo;
			return responseUtil.redirectPagina(response,pagina);
		}
		
		//Incrementa as visualizações
		receita.incrementaVisualizacao();
		this.daoReceita.altera(receita);
		
		//Retorna a view+dados
		model.addAttribute("receita", receita);
		return "receita/exibe";
	}
	
	@RequestMapping(value="/adiciona",method=RequestMethod.POST)
	public Response<Receita> adicionaReceita(ReceitaBean receitaBean,String tipoCategoria,HttpServletResponse response,HttpSession session)
	{
		//Carrega a categoria pelo tipo
		Categoria categoria = this.daoCategoria.getPorCampo(Categoria.CAMPO_TIPO, Dao.EQUALS, tipoCategoria).get(0);
		Usuario usuario = (Usuario)session.getAttribute("usuarioLogado");
		
		//Constrói a receita
		Receita receita = new ReceitaFactory().constroiReceitaPorBean(receitaBean, usuario, Calendar.getInstance(), categoria);
		
		//Grava
		this.daoReceita.adiciona(receita);
		
		response.setStatus(200);
		Response<Receita> retorno = new Response<Receita>(receita);
		retorno.setMensagem("Receita adicionada com sucesso !");
		retorno.setStatus(Response.SUCESSO);
		return retorno;
	}
	
	@RequestMapping(value="/altera",method=RequestMethod.POST)
	public void alteraReceita(ReceitaBean receitaBean,String tipoCategoria,long codigoUsuario,HttpServletResponse response)
	{
		//Carrega a categoria pelo tipo
		Categoria categoria = this.daoCategoria.getPorCampo(Categoria.CAMPO_TIPO, Dao.EQUALS, tipoCategoria).get(0);
		Usuario usuario = this.daoUsuario.get((int)codigoUsuario);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Constrói a receita
		Receita receita = new ReceitaFactory().constroiReceitaPorBean(receitaBean, usuario, Calendar.getInstance(), categoria);
		
		//Grava
		this.daoReceita.altera(receita);
		
		response.setStatus(200);
	}
	
	@RequestMapping(value="/remove",method=RequestMethod.POST)
	public void removeReceita(Receita receita)
	{
		//Implementar
	}
}

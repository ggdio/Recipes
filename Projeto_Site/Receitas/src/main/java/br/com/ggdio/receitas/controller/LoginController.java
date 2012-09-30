package br.com.ggdio.receitas.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.ggdio.projectutils.seguranca.GeradorCriptografia;
import br.com.ggdio.receitas.dao.UsuarioDAO;
import br.com.ggdio.receitas.model.Usuario;
import br.com.ggdio.receitas.response.Response;

@Controller
@RequestMapping("/login")
public class LoginController 
{
	private UsuarioDAO usuarioDao;
	
	public LoginController() 
	{
		this.usuarioDao = new UsuarioDAO();
	}
	
	@RequestMapping("/formulario")
	public String formulario()
	{
		return "login/formulario";
	}
	
	@RequestMapping(value="/entrar",method=RequestMethod.POST)
	public String realizaLogin(Usuario usuario,HttpSession session,Model model)
	{
		//Verifica se o usu�rio j� esta conectado
		if(session.getAttribute("usuarioLogado") != null)
		{
			this.montaResponse(model, Response.ERRO, "O usu�rio j� esta conectado !");
			return "forward:/";
		}
		
		//Encripa a senha fornecida para a consulta ao banco
		String senha = new GeradorCriptografia().encriptaMD5(usuario.getSenha());
		Usuario identificado = this.usuarioDao.get(usuario.getLogin(), senha, true);
		
		//Verifica se o usu�rio � valido
		if(identificado == null)
		{
			//Indica que o usu�rio ou senha informado(a) � inv�lido
			this.montaResponse(model, Response.ERRO, "Login e/ou Senha inv�lidos !");
			return "forward:/login/formulario";
		}
		
		//Adiciona o usu�rio na sess�o e permite a navega��o
		session.setAttribute("usuarioLogado", identificado);
		this.montaResponse(model, Response.SUCESSO, "Seja bem vindo "+identificado.getNome()+" !");
		
		//Verifica se h� uma p�gina solicitada antes de realizar login
//		if(session.getAttribute("paginaSolicitada") != null)
//		{
//			//Recupera p�gina da se��o e redireciona para a mesma
//			UrlRestrito url = (UrlRestrito)session.getAttribute("paginaSolicitada");
//			return "forward:/" + url.getUrlSemContexto();
//		}
		
		//Encaminha os dados para a p�gina incial
		return "forward:/";
	}
	
	@RequestMapping("/sair")
	public String realizaLogout(HttpSession session,Model model)
	{
		Usuario usuario = (Usuario)session.getAttribute("usuarioLogado");
		if(usuario != null)
		{
			session.invalidate();
			montaResponse(model, Response.SUCESSO, "At� mais tarde "+usuario.getNome()+" !");
		}
		else
		{
			montaResponse(model, Response.ERRO, "Voc� ainda n�o esta logado no sistema !");
		}
		return "forward:/";
	}
	
	private void montaResponse(Model model,String status,String mensagem)
	{
		Response<String> response = new Response<String>();
		response.setMensagem(mensagem);
		response.setStatus(status);
		model.addAttribute("response", response);
	}
}

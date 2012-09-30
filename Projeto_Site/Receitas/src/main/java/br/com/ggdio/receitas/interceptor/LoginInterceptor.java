package br.com.ggdio.receitas.interceptor;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.ggdio.receitas.dao.UrlRestritoDAO;
import br.com.ggdio.receitas.model.UrlRestrito;
import br.com.ggdio.receitas.model.Usuario;
import br.com.ggdio.receitas.response.Response;
import br.com.ggdio.receitas.util.ContextoSistema;

public class LoginInterceptor extends HandlerInterceptorAdapter
{
	@Autowired
	private ContextoSistema contexto;
	
	public LoginInterceptor() 
	{
		super();
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception 
	{
		//Lista de URLs restritos
		List<UrlRestrito> restritos = new UrlRestritoDAO(this.contexto).getLista();
		
		//Verifica se o URL requisitado consta na lista
		int index = restritos.indexOf(new UrlRestrito(this.contexto,request.getRequestURI()));
		
		//Se constar na lista, então verifica se foi efetuado login
		if(index > -1)
		{
			//Recupera da sessão o atributo de login
			Usuario usuario = (Usuario)request.getSession().getAttribute("usuarioLogado");
			
			//Se for nulo, então não foi feito login no sistema
			if(usuario == null)
			{
				//Guarda na sessão a página solicitada
				request.getSession().setAttribute("paginaSolicitada", restritos.get(index));
				
				//Redireciona para a página de login
				redireciona(request,response,"Para acessar a página é necessário realizar login !","/login/formulario");
				return false;
			}
			else
			{
				//Verifica se a página solicitada é restrita à administradores.
				if(restritos.get(index).isAdminRestrito() && !usuario.isAdmin())
				{
					//Redireciona para pagina inicial informando que é necessário ser administrador
					redireciona(request,response,"Sua conta não possuí privilégios suficientes para este tipo de ação !","/");
					return false;
				}
			}
		}
		//Permite o acesso a página
		return true;
	}
	
	private void redireciona(HttpServletRequest request,HttpServletResponse response,String alerta,String pagina) throws ServletException, IOException
	{
		//Monta um response com a mensagem de alerta e o status
		Response<String> mensagem = new Response<String>();
		mensagem.setMensagem(alerta);
		mensagem.setStatus(Response.ERRO);
		request.setAttribute("response", mensagem);
		RequestDispatcher dispatcher = this.contexto.getServletContext().getRequestDispatcher("/login/formulario");
		dispatcher.forward(request, response);
	}
}
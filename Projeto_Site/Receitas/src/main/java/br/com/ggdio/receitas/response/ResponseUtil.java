package br.com.ggdio.receitas.response;

import javax.servlet.http.HttpServletResponse;

public class ResponseUtil 
{
	public HttpServletResponse response;
	
	public ResponseUtil() {}
	
	public ResponseUtil(HttpServletResponse response)
	{
		this.response = response;
	}
	
	public String redirectPaginaInexistente()
	{
		validaResponse(this.response);
		this.response.setStatus(404);
		return "redirect:/404";
	}
	
	public String redirectPaginaInexistente(HttpServletResponse response)
	{
		validaResponse(response);
		response.setStatus(404);
		return "redirect:/404";
	}
	
	public String redirectPaginaErro()
	{
		validaResponse(this.response);
		this.response.setStatus(500);
		return "redirect:/exception";
	}
	
	public String redirectPaginaErro(HttpServletResponse response)
	{
		validaResponse(response);
		response.setStatus(500);
		return "redirect:/exception";
	}
	
	public String redirectPaginaLogin()
	{
		validaResponse(this.response);
		return "redirect:/login/fomulario";
	}
	
	public String redirectPaginaLogin(HttpServletResponse response)
	{
		validaResponse(response);
		return "redirect:/login/fomulario";
	}
	
	public String redirectPagina(String pagina)
	{
		validaResponse(this.response);
		response.setStatus(200);
		return "redirect:".concat(pagina);
	}
	
	public String redirectPagina(HttpServletResponse response,String pagina)
	{
		validaResponse(response);
		response.setStatus(200);
		return "redirect:".concat(pagina);
	}
	
	private void validaResponse(HttpServletResponse response) 
	{
		if(response == null) throw new NullPointerException("O response não pode ser nulo !");
	}
	
	public void setResponse(HttpServletResponse response) 
	{
		this.response = response;
	}
	
	public HttpServletResponse getResponse() 
	{
		return response;
	}
}

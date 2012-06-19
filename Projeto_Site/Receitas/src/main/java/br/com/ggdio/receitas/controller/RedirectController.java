package br.com.ggdio.receitas.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/redirect")
public class RedirectController 
{
	@RequestMapping("/externo")
	public void redirecionar(String url,HttpServletResponse response)
	{
		try
		{
			response.sendRedirect(url);
		}
		catch(IOException e)
		{
			throw new RuntimeException(e);
		}
	}
}

package br.com.ggdio.receitas.util;

import java.util.Calendar;

import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.WebApplicationContext;

public final class ContextoSistema implements ApplicationContextAware
{
	public static final String DIRETORIO_UPLOAD_DEFINITIVO = "resources/upload/def/";
	public static final String DIRETORIO_UPLOAD_TEMPORARIO = "resources/upload/temp/";
	private ServletContext servletContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException 
	{
		this.servletContext = ((WebApplicationContext)applicationContext).getServletContext();
	}
	
	public ServletContext getServletContext() 
	{
		return this.servletContext;
	}
	
	public String getDiretorioRealAplicacao()
	{
		return this.servletContext.getRealPath("/");
	}
	
	public String getDiretorioWebAplicacao()
	{
		return this.servletContext.getContextPath()+"/";
	}
	
	public String getDiretorioRealUploadDefinitivo()
	{
		return this.servletContext.getRealPath("/")+DIRETORIO_UPLOAD_DEFINITIVO+geraDiretorioPorData();
	}
	
	public String getDiretorioRealUploadTemporario()
	{
		return this.servletContext.getRealPath("/")+DIRETORIO_UPLOAD_TEMPORARIO;
	}
	
	public String getDiretorioWebUploadDefinitivo()
	{
		return this.servletContext.getContextPath()+"/"+DIRETORIO_UPLOAD_DEFINITIVO+geraDiretorioPorData();
	}
	
	public String getDiretorioWebUploadTemporario()
	{
		return this.servletContext.getContextPath()+"/"+DIRETORIO_UPLOAD_TEMPORARIO;
	}
	
	private String geraDiretorioPorData()
	{
		final Calendar data = Calendar.getInstance();
		final int ano = data.get(Calendar.YEAR);
		final int mes = data.get(Calendar.MONTH)+1;
		return ano+"/"+mes+"/";
	}
	
}

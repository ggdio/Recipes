package br.com.ggdio.receitas.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import br.com.ggdio.receitas.arquivo.Arquivo;
import br.com.ggdio.receitas.arquivo.UploadArquivo;
import br.com.ggdio.receitas.response.Response;
import br.com.ggdio.receitas.util.ContextoSistema;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

@Controller
@RequestMapping("/upload")
public class UploadController
{
	@Autowired
	private ContextoSistema contexto;
	private XStream xstream;
	private final String PREFIXO = "upload";
	private final String SEPARADOR = "_";
	
	public UploadController() 
	{
		xstream = new XStream(new JettisonMappedXmlDriver());
		xstream.alias("response", Response.class);
		xstream.alias("arquivo", Arquivo.class);
	}
	
	//Upload definitivo
	@ResponseBody
	@RequestMapping(value="/def",method=RequestMethod.POST)
	public String uploadDefinitivo(HttpServletRequest request,HttpServletResponse response)
	{
		//Request com o arquivo
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile arquivo = multipartRequest.getFile("arquivo");
		
		//Se for nulo, retorna erro
		if(arquivo == null)
		{
			response.setStatus(500);
			return montaResponse(Response.ERRO, "O Arquivo informado e nulo", null);
		}
		
		//Diretório de Uploads
		final String diretorio = this.contexto.getDiretorioRealUploadDefinitivo();
		
		//Upload do arquivo
		final UploadArquivo uploadArquivo = new UploadArquivo(diretorio,PREFIXO,SEPARADOR);
		
		//Arquivo para retorno
		Arquivo arquivoRetorno = new Arquivo();
		try
		{
			arquivoRetorno = uploadArquivo.uploadArquivo(arquivo);
		}
		catch(Exception e)
		{
			//Retorna a resposta de erro
			response.setStatus(500);
			return montaResponse(Response.ERRO, "Ocorreu um erro durante o upload do arquivo", null);
		}
		
		//Retorna a resposta de sucesso
		final String caminhoWeb = this.contexto.getDiretorioWebUploadDefinitivo()+arquivoRetorno.getNome();
		arquivoRetorno.setCaminhoWeb(caminhoWeb);
		response.setStatus(200);
		return montaResponse(Response.SUCESSO, "O arquivo foi enviado com sucesso para o diretorio temporario do servidor", arquivoRetorno);
	}
	
	//Upload temporário
	@ResponseBody
	@RequestMapping(value="/temp",method=RequestMethod.POST)
	public String uploadTemporario(HttpServletRequest request,String teste,HttpServletResponse response)
	{
		System.out.println(request.getRequestURI());
		//Request com o arquivo
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile arquivo = multipartRequest.getFile("arquivo");
		
		//Se for nulo, retorna erro
		if(arquivo == null)
		{
			response.setStatus(500);
			return montaResponse(Response.ERRO, "O Arquivo informado e nulo", null);
		}
		
		//Diretório de Uploads
		final String diretorio = this.contexto.getDiretorioRealUploadTemporario();
		
		//Upload do arquivo
		final UploadArquivo uploadArquivo = new UploadArquivo(diretorio,PREFIXO,SEPARADOR);
		
		//Arquivo para retorno
		Arquivo arquivoRetorno = new Arquivo();
		try
		{
			arquivoRetorno = uploadArquivo.uploadArquivo(arquivo);
		}
		catch(Exception e)
		{
			//Retorna a resposta de erro
			response.setStatus(500);
			return montaResponse(Response.ERRO, "Ocorreu um erro durante o upload do arquivo", null);
		}
		
		//Retorna a resposta de sucesso
		final String caminhoWeb = this.contexto.getDiretorioWebUploadTemporario()+arquivoRetorno.getNome();
		arquivoRetorno.setCaminhoWeb(caminhoWeb);
		response.setStatus(200);
		return montaResponse(Response.SUCESSO, "O arquivo foi enviado com sucesso para o diretorio temporario do servidor", arquivoRetorno);
	}
	
	private String montaResponse(String status,String mensagem,Arquivo response)
	{
		Response<Arquivo> jsonResponse = new Response<Arquivo>();
		jsonResponse.setStatus(status);
		jsonResponse.setMensagem(mensagem);
		jsonResponse.setInformacao(response);
		return this.xstream.toXML(jsonResponse);
	}
	
}

package br.com.ggdio.receitas.arquivo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.web.multipart.MultipartFile;

public class UploadArquivo
{
	private File diretorio;
	private String prefixo;
	private String separador;
	
	public UploadArquivo(File diretorioServidor,String prefixo,String separador)
	{
		this.diretorio = diretorioServidor;
		this.prefixo = prefixo;
		this.separador = separador;
	}
	
	public UploadArquivo(String diretorioServidor,String prefixo,String separador)
	{
		this(new File(diretorioServidor),prefixo,separador);
	}
	
	public Arquivo uploadArquivo(MultipartFile arquivo)
	{
		if(arquivo == null) throw new NullPointerException("O arquivo informado é nulo");
		if(this.diretorio == null) throw new NullPointerException("O diretório informado é nulo");
		
		if(!this.diretorio.exists())
			if(!this.diretorio.mkdir())
				if(!this.diretorio.mkdirs())
					throw new IllegalArgumentException("O diretório informado é invalido !");
		
		String nomeArquivo = "";
		String extensao = "";
		
		if (arquivo.getSize() > 0)
		{
			extensao = arquivo.getOriginalFilename().substring(arquivo.getOriginalFilename().indexOf("."),arquivo.getOriginalFilename().length());
			nomeArquivo = this.prefixo+this.separador+this.identificaProximoUpload()+extensao;
			nomeArquivo = nomeArquivo.toLowerCase();
			try
			{
				InputStream is = arquivo.getInputStream();
				OutputStream os = new FileOutputStream(this.diretorio.getAbsolutePath() + "/" + nomeArquivo);
				int readBytes = 0;
				byte[] buffer = new byte[8192];
				while ((readBytes = is.read(buffer, 0, 8192)) != -1) 
				{
					os.write(buffer, 0, readBytes);
				}
				is.close();
				os.close();
			}
			catch(FileNotFoundException e)
			{
				System.err.println(e.getMessage()+"\n"+e.getCause());
				throw new RuntimeException("O Arquivo é inexistente",e);
			}
			catch(IOException e)
			{
				System.err.println(e.getMessage()+"\n"+e.getCause());
				throw new RuntimeException("Não foi possível copiar o arquivo",e);
			}
		}
		return montaArquivo(nomeArquivo, extensao);
	}
	
	private Arquivo montaArquivo(String nome,String extensao)
	{
		if("".equals(nome) || "".equals(extensao) || nome == null || extensao == null)
			return new Arquivo();
		
		final String caminho = this.diretorio.getAbsolutePath()+"/"+nome;
		final File arquivoReal = new File(caminho);
		final Calendar modificado = Calendar.getInstance();
		modificado.setTimeInMillis(arquivoReal.lastModified());
		
		Arquivo arquivo = new Arquivo();
		arquivo.setNome(nome);
		arquivo.setExtensao(extensao);
		arquivo.setCaminhoReal(caminho);
		arquivo.setTamanho(new File(caminho).length());
		arquivo.setUltimaModificacao(modificado);
		return arquivo;
	}
	
	private int identificaProximoUpload()
	{
		int ultimo = 0;
		if(this.diretorio.exists())
		{
			for(File arquivo : this.diretorio.listFiles())
			{
				try
				{
					final String nome = arquivo.getName();
					if(!nome.equals("Thumbs.db"))
					{
						String valor = nome.substring(nome.indexOf(this.separador)+separador.length(),nome.indexOf("."));
						final int posicao = Integer.parseInt(valor);
						if(posicao > ultimo)
							ultimo = posicao;
					}
				}
				catch(Exception e)
				{
					break;
				}
			}
		}
		return ++ultimo;
	}
	
	public File getDiretorio()
	{
		return this.diretorio;
	}
}

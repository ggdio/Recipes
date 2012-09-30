package br.com.ggdio.receitas.tarefa;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.ggdio.receitas.util.ContextoSistema;

public class TarefaLimpaPastaUpload implements Tarefa
{
	@Autowired
	private ContextoSistema contexto;
	
	@Override
	public void executeJob() 
	{
		String hora = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss").format(Calendar.getInstance().getTime());
		System.out.println("\n---------------------------------------------------------------");
		System.out.println("Início da limpeza da pasta temporária de uploads às "+hora+"\n");
		
		File diretorio = new File(this.contexto.getDiretorioRealUploadTemporario());
		for(File arquivo : diretorio.listFiles())
		{
			System.out.println("Removendo o arquivo: "+arquivo.getName());
			if(arquivo.delete())
				System.out.println("Arquivo deletado.\n");
		}
		
		System.out.println("\nFim da limpeza da pasta...");
		System.out.println("---------------------------------------------------------------\n");
	}

	
}

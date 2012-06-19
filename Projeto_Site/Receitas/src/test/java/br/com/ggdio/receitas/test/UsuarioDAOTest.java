package br.com.ggdio.receitas.test;


import java.util.Calendar;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import br.com.ggdio.projectutils.dao.Dao;
import br.com.ggdio.projectutils.seguranca.Criptografia;
import br.com.ggdio.receitas.dao.UsuarioDAO;
import br.com.ggdio.receitas.model.Usuario;

public class UsuarioDAOTest
{
	
	private Dao<Usuario> dao;
	
	public UsuarioDAOTest() 
	{
		this.dao = new UsuarioDAO();
	}

//	@Test
//	public void testInsertUsuarioDAO()
//	{
//		Usuario usuario = new Usuario();
//		usuario.setNome("Usuário");
//		usuario.setLogin("login");
//		usuario.setSenha(new Criptografia().gerarHash("senha"));
//		usuario.setStatus(true);
//		usuario.setUltimoAcesso(Calendar.getInstance());
//		usuario.setAdmin(false);
//		Boolean ok = true;
//		try
//		{
//			this.dao.adiciona(usuario);
//		}
//		catch(RuntimeException e)
//		{
//			ok = false;
//		}
//		Assert.assertTrue(ok);
//	}
	
	@Test
	public void testSelectUsuario()
	{
		Usuario usuario = this.dao.get(1);
		Assert.assertEquals("Usuário", usuario.getNome());
	}
	
	@Test
	public void testSelectUsuarioPorCampoSemRuntimeExceptionComEquals()
	{
		Boolean ok = true;
		try
		{
			this.dao.getPorCampo("nome", Dao.EQUALS, "Usuário");
		}
		catch(RuntimeException e)
		{
			ok = false;
		}
		Assert.assertTrue(ok);
	}
	
	@Test
	public void testSelectUsuarioPorCampoSemRuntimeExceptionComLike()
	{
		Boolean ok = true;
		try
		{
			this.dao.getPorCampo("nome", Dao.LIKE, "%suá%io");
		}
		catch(RuntimeException e)
		{
			ok = false;
		}
		Assert.assertTrue(ok);
	}
	
	@Test
	public void testSelectUsuarioPorCampoComEquals()
	{
		List<Usuario> usuarios = this.dao.getPorCampo("nome", Dao.EQUALS, "Usuário");
		Assert.assertEquals("Usuário", usuarios.get(0).getNome());
	}
	
	
	@Test
	public void testSelectUsuarioPorCampoComLike()
	{
		List<Usuario> usuarios = this.dao.getPorCampo("nome", Dao.LIKE, "%suá%io");
		Assert.assertEquals("Usuário", usuarios.get(0).getNome());
	}
	
	
	
	@Test
	public void testUpdateUsuarioSemRuntimeException()
	{
		Boolean ok = true;
		try
		{
			this.dao.altera(this.dao.get(1));
		}
		catch(RuntimeException e)
		{
			ok = false;
		}
		Assert.assertTrue(ok);
	}
	
	@Test
	public void testUpdateSenhaUsuario()
	{
		String senha = new Criptografia().gerarHash("senhaNova");
		Usuario usuario = this.dao.get(1);
		usuario.setSenha(senha);
		this.dao.altera(usuario);
		Assert.assertEquals(senha, this.dao.get(1).getSenha());
	}
	
	@Test
	public void testDeleteUsuario()
	{
		Usuario usuario = new Usuario();
		usuario.setNome("UsuárioParaDeletar ABC 123 ABC 123");
		usuario.setLogin("login");
		usuario.setSenha(new Criptografia().gerarHash("senha"));
		usuario.setStatus(true);
		usuario.setUltimoAcesso(Calendar.getInstance());
		usuario.setAdmin(false);
		this.dao.remove(usuario);
		int quantidade = this.dao.getPorCampo("nome", Dao.EQUALS, "UsuárioParaDeletar ABC 123 ABC 123").size();
		Assert.assertEquals(0, quantidade);
	}
}

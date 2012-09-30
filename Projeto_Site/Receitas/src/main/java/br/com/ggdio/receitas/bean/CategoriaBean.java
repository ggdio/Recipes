package br.com.ggdio.receitas.bean;

public class CategoriaBean 
{
	private long codigo;
	private String tipo;
	private String descricao;
	private String imagem;
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj instanceof CategoriaBean)
		{
			CategoriaBean categoria = (CategoriaBean) obj;
			if(categoria.getCodigo() == this.codigo)
				if(categoria.getTipo().equals(this.tipo))
					if(categoria.getDescricao().equals(this.descricao))
						if(categoria.getImagem().equals(this.imagem))
							return true;
		}
		return false;
	}
	
	@Override
	public String toString() 
	{
		return this.tipo;
	}
	
	public long getCodigo()
	{
		return codigo;
	}
	public String getTipo()
	{
		return tipo;
	}
	public String getDescricao()
	{
		return descricao;
	}
	public String getImagem() 
	{
		return imagem;
	}
	public void setCodigo(long codigo)
	{
		this.codigo = codigo;
	}
	public void setTipo(String tipo)
	{
		this.tipo = tipo;
	}
	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}
	public void setImagem(String imagem)
	{
		this.imagem = imagem;
	}
}

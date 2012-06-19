package br.com.ggdio.receitas.model;

public class Categoria 
{
	private long codigo;
	private String tipo;
	private String descricao;
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj instanceof Categoria)
		{
			Categoria categoria = (Categoria) obj;
			if(categoria.getCodigo() == this.codigo)
				if(categoria.getTipo().equals(this.tipo))
					if(categoria.getDescricao().equals(this.descricao))
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
}

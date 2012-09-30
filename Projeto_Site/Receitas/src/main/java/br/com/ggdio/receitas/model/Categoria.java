package br.com.ggdio.receitas.model;

public class Categoria 
{
	private long codigo;
	private String tipo;
	private String descricao;
	private String imagem;
	
	public static final String CAMPO_CODIGO = "codigo";
	public static final String CAMPO_TIPO = "tipo";
	public static final String CAMPO_DESCRICAO = "descricao";
	public static final String CAMPO_IMAGEM = "imagem";
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj instanceof Categoria)
		{
			Categoria categoria = (Categoria) obj;
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
		StringBuilder sb = new StringBuilder("Categoria: \n");
		sb.append("código:"+this.codigo);
		sb.append("tipo:"+this.tipo);
		sb.append("descrição:"+this.descricao);
		sb.append("imagem:"+this.imagem);
		return sb.toString();
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

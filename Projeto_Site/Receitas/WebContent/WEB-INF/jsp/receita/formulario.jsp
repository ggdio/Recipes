<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<!-- URL's -->
<c:url value="/receitas/${receita != null ? 'altera' : 'adiciona'}" var="urlRequest"/>
<c:url value="/receitas/lista" var="urlReceitas"/>
<c:url value="/upload/" var="urlUpload"/>
<c:url value="/resources/ckeditor/" var="urlCkeditor" />
<c:url value="/resources/img/bigLoading.gif" var="loading"/>
<c:url value="/resources/img/logo.png" var="logo"/>
<c:url value="/resources/img/info.png" var="info"/>
<c:url value="/resources/img/alerta.png" var="alerta"/>
<c:url value="/resources/img/pergunta.png" var="pergunta"/>
<c:url value="/resources/img/default.jpg" var="imgDefault"/>
<c:if test="${receita != null }">
	<c:url var="urlExibe" value="/receitas/${prefixo}${receita.titulo.replace(' ','-')}/${receita.codigo}"/>
</c:if>


<!-- HEADER -->
<c:import url="/resources/html/header.jsp">
	<c:param value="Formulário de receitas" name="title"/>
</c:import>

<center>
	<span style="font-size: x-large;" class="label label-important">Formulário - Receita</span>
</center>

<br/>
<br/>

<!-- LOADING -->
<tag:loading id="loading" align="center" width="50%"/>

<div id="formulario" hidden="hidden">
<form id="formReceita" class="well-green">
	<fieldset>
		<label for="titulo"><b>Título</b></label>
		<input value="${receita.titulo}" class="span4" type="text" id="txtTitulo" name="titulo" maxlength="300" placeholder="Título da Receita..."/><br/>
		
		<label for="descricao"><b>Descrição</b></label>
		<input value="${receita.descricao}" class="span6" type="text" id="txtDescricao" name="descricao" maxlength="300" placeholder="Descrição da Receita..."/><br/>
		
		<label for="ingredientes"><b>Ingredientes</b></label>
		<div id="ingredientes">
			<tag:popover id="btnIngredientes" texto="Adicionar um ingrediente na receita" titulo="Ingrediente" classe="btn btn-success">
				<jsp:attribute name="value">
					<i class="icon-plus icon-white"></i>
					<span>Adicionar</span>
				</jsp:attribute>
			</tag:popover>
			<br/>
			<br/>
			<c:forEach items="${receita.ingredientes}" var="ingrediente" varStatus="status">
				<div id="divIngrediente_${status.count}">
					<input value="${ingrediente}" id="ingrediente_${status.count}" class="span4" placeholder="Algum ingrediente..." type="text" name="ingrediente_${status.count}"/>&nbsp;
					<a id="btnRemoverIngrediente_${status.count}" class="btn btn-danger" style="vertical-align: top;" title="Remover Ingrediente"><i class="icon-remove-sign icon-white"></i></a>
				</div>
				<script>
					//Evento do botão remover ingrediente
					$('#btnRemoverIngrediente_${status.count}').click(function(event)
					{
						//Recupera o ID
						var id = $(this).attr('id').replace('btnRemoverIngrediente_','');
						
						//Remove o ingrediente
						$('#divIngrediente_'+id).remove();
						
						//Decrementa a quantidade
						qtdIngrediente = qtdIngrediente - 1;
					});
				</script>
			</c:forEach>
		</div>
		
		<label for="categoria"><b>Categoria</b></label>
		<tag:combobox dados="${categorias}" converter="${converter}" id="cbCategoria" name="categoria" selecionado="${receita.categoria.tipo}"/><br/>
		
		<label for="preparo"><b>Modo de Preparo</b></label>
		<textarea class="span10 pull-left" rows="50" id="txtPreparo" maxlength="3000" name="preparo" placeholder="Modo de preparo da receita...">${receita.preparo}</textarea>
		<ckeditor:replace replace="txtPreparo" basePath="${urlCkeditor}" />
		
		<label for="rendimento"><b>Rendimento</b></label>
		<tag:campoNumerico value="${receita.rendimento}" id="txtRendimento" name="tempo" classe="span1" maxlength="2" />
		
		<label for="tempo"><b>Tempo de Preparo</b></label>
		<tag:campoNumerico value="${receita.rendimento}" id="txtTempo" name="tempo" classe="span1" maxlength="3"/>
		
		<ul class="thumbnails">
		    <li class="span6">
			    <div class="well thumbnail pull-left">
			    	<p><b>Imagem</b></p>
			    	<img id="imgReceita" width="360" height="268" src="${receita != null ? receita.imagem : imgDefault}" alt="" />
				    <p/>
				    <p><a id="btnSelecionarImagem" class="btn btn-primary"><i class="icon-picture icon-white"></i>Selecionar</a></p>
			    </div>
		    </li>
		</ul>
		
		<label><b>Tags de Busca</b></label>
		<div id="tags">
			<tag:popover id="btnTags" texto="Adicionar uma tag de busca para receita" titulo="Tags" classe="btn btn-success">
				<jsp:attribute name="value">
					<i class="icon-plus icon-white"></i>
					<span>Adicionar</span>
				</jsp:attribute>
			</tag:popover>
			<br/>
			<br/>
			<c:forEach items="${receita.tags}" var="tag" varStatus="status">
				<div id="divTag_${status.count}">
					<input value="${tag}" id="tag_${status.count}" class="span4" placeholder="Insira uma tag de busca..." type="text" name="tag_${status.count}"/>&nbsp;
					<a id="btnRemoverTag_${status.count}" class="btn btn-danger" style="vertical-align: top;" title="Remover Tag"><i class="icon-remove-sign icon-white"></i></a>
				</div>
				<script>
					//Evento do botão remover ingrediente
					$('#btnRemoverTag_${status.count}').click(function(event)
					{
						//Recupera o ID
						var id = $(this).attr('id').replace('btnRemoverTag_','');
						
						//Remove o ingrediente
						$('#divTag_'+id).remove();
						
						//Decrementa a quantidade
						qtdTag = qtdTag - 1;
					});
				</script>
			</c:forEach>
		</div>
		<br/>
		<div class="form-actions"> 
			<a id="btnCadastrar" class="btn btn-primary btn-large">
				<i class="icon-ok icon-white"></i>
				<span>${receita != null ? 'Alterar' : 'Cadastrar'}</span>
			</a>
			<a class="btn btn-large" onclick="limpar()">Limpar</a>
		</div>
	</fieldset>
	<input name="codigoUsuario" id="txtCodigoUsuario" type="hidden" value="${receita.autor.codigo}"/>
	<input name="visualizacoes" id="txtVisualizacoes" type="hidden" value="${receita.visualizacoes}"/>
</form>

</div>

<tag:popup id="mensagemSucesso" component="null" imagem="${info}" titulo="Sucesso !">
	<jsp:attribute name="texto">
		<center>
			<span style="font-size: medium;" class="label label-success">Receita ${receita != null ? 'Alterada' : 'Cadastrada'} com Sucesso !</span>
		</center>
			<p/><p/>
			<a id="btnContinuarSucesso" class="btn btn-inverse">OK</a>
	</jsp:attribute>
</tag:popup>

<tag:popup id="mensagemAtencao" component="null" imagem="${alerta}" titulo="Atenção !">
	<jsp:attribute name="texto">
		<center>
			<span id="atencao" style="font-size: medium;" class="label label-warning">
				
			</span>
		</center>
			<p/><p/>
			<a id="btnContinuarAtencao" class="btn btn-inverse">OK</a>
	</jsp:attribute>
</tag:popup>

<tag:popup id="mensagemErro" component="null" imagem="${alerta}" titulo="Atenção !">
	<jsp:attribute name="texto">
		<center>
			<span style="font-size: medium;" class="label label-important">Ocorreu um erro durante o processo !</span>
		</center>
			<p/><p/>
			<a id="btnContinuarErro" class="btn btn-inverse">OK</a>
	</jsp:attribute>
</tag:popup>

<tag:popup id="popupSelecionarImagem" component="btnSelecionarImagem" imagem="${pergunta}" titulo="Seleção de Imagem">
	<jsp:attribute name="texto">		
		<form id="formUploadImagem" method="post" accept-charset="utf-8" enctype="multipart/form-data">
			<p>Selecione uma Imagem</p>
			<p><input id="txtUrlImagem" name="arquivo" size="27" type="file" accept="image/*" /></p>
			<p><input id="btnUploadImagem" name="action" class="btn btn-primary" type="submit" value="Upload"></p>
			<iframe id="uploadTarget" name="uploadTarget" src="" style="width:0;height:0;border:0px solid #fff;"></iframe>
		</form>
	</jsp:attribute>
</tag:popup>

<script type="text/javascript">
var qtdIngrediente = parseInt("${receita != null ? receita.ingredientes.size() : 0}");
var qtdTag = parseInt("${receita != null ? receita.tags.size() : 0}");
var caminhoImg = "${receita != null ? receita.imagem : imgDefault}";
var tipoUpload = "temp";
var cadastrarReceita = false;
var processando = false;
function init() 
{
	document.getElementById('formUploadImagem').onsubmit = onSubmit;
	document.getElementById("uploadTarget").onload = uploadDone;
}
function onSubmit()
{
	if(!cadastrarReceita)
		$('#imgReceita').attr('src','${loading}');
	document.getElementById('formUploadImagem').target = 'uploadTarget';
}
function uploadDone()
{
	var ret = frames['uploadTarget'].document.getElementsByTagName("body")[0].innerHTML;
	var data = eval("("+ret+")");
	caminhoImg = data.response.informacao.caminhoWeb;
	if(cadastrarReceita)
	{
		cadastrar();	
	}
	else
	{
		$('#popupSelecionarImagem').modal('hide');
		$('#imgReceita').attr('src',caminhoImg);
	}
}
window.onload = init;

$(document).ready(function()
{
	//Seta o action do upload de imagem
	$('#formUploadImagem').attr('action','${urlUpload}'+tipoUpload);
	
	//Wysi
// 	$('#txtPreparo').wysihtml5();
// 	$('#estilo').dropdown();
	
	//Botão Adicionar Ingrediente
	$('#btnIngredientes').click(function(event)
	{
		//Incrementa a qtd de ingredientes
		qtdIngrediente = qtdIngrediente + 1;
		
		//HTML
		var input = "<input id='ingrediente_"+qtdIngrediente+"' class='span4' placeholder='Algum ingrediente...' type='text' name='ingrediente_"+qtdIngrediente+"'/>";
		var botao = "<a id='btnRemoverIngrediente_"+qtdIngrediente+"' class='btn btn-danger' style='vertical-align: top;' title='Remover Ingrediente'><i class='icon-remove-sign icon-white'></i></a>";
		
		//Adiciona o campo
		$('#ingredientes').append("<div id='divIngrediente_"+qtdIngrediente+"'>"+input+"&nbsp;&nbsp;"+botao+"</div>");
		
		//Evento do botão remover ingrediente
		$('#btnRemoverIngrediente_'+qtdIngrediente).click(function(event)
		{
			//Recupera o ID
			var id = $(this).attr('id').replace('btnRemoverIngrediente_','');
			
			//Remove o ingrediente
			$('#divIngrediente_'+id).remove();
			
			//Decrementa a quantidade
			qtdIngrediente = qtdIngrediente - 1;
		});
	});
	
	//Botão Adicionar Tag
	$('#btnTags').click(function(event)
	{
		//Incrementa a qtd de ingredientes
		qtdTag = qtdTag + 1;
		
		//HTML
		var input = "<input id='tag_"+qtdTag+"' class='span4' placeholder='Insira uma tag de busca...' type='text' name='tag_"+qtdTag+"'/>";
		var botao = "<a id='btnRemoverTag_"+qtdTag+"' class='btn btn-danger' style='vertical-align: top;' title='Remover Tag'><i class='icon-remove-sign icon-white'></i></a>";
		
		//Adiciona o campo
		$('#tags').append("<div id='divTag_"+qtdTag+"'>"+input+"&nbsp;&nbsp;"+botao+"</div>");
		
		//Evento do botão remover ingrediente
		$('#btnRemoverTag_'+qtdTag).click(function(event)
		{
			//Recupera o ID
			var id = $(this).attr('id').replace('btnRemoverTag_','');
			
			//Remove o ingrediente
			$('#divTag_'+id).remove();
			
			//Decrementa a quantidade
			qtdTag = qtdTag - 1;
		});
	});
	
	//Botão Cadastrar
	$('#btnCadastrar').click(function(event)
	{
		if(processando == false)
		{
			if(valida())
			{
				processando = true;
				$('#btnCadastrar').attr('disabled',true);
				var imagem = $('#imgReceita').attr('src');
				if(imagem != "${imgDefault}" && imagem != "${receita.imagem}")
				{
					cadastrarReceita = true;
					$('form#formUploadImagem').attr('action','${urlUpload}/def/');
					$('form#formUploadImagem').submit();
				}
				else
				{
					cadastrar();
				}
			}
		}
	});
	
	//Esconde Loading/Exibe Form
	$('#loading').hide();
	$('#formulario').show();
});

function cadastrar()
{
	//Dados
	var codigoUsuario = $('#txtCodigoUsuario').val();
	var codigo = parseInt("${receita != null ? receita.codigo : 0}");
	var titulo = $('#txtTitulo').val();
	var descricao = $('#txtDescricao').val();
	var tipoCategoria = $('#cbCategoria').val();
	var preparo = CKEDITOR.instances.txtPreparo.getData();
	var rendimento = $('#txtRendimento').val() != "" ? $('#txtRendimento').val() : 0;
	var tempo = $('#txtTempo').val() != "" ? $('#txtTempo').val() : 0;
	var imagem = caminhoImg;
	var tags = new String();
	var visualizacoes = $('#txtVisualizacoes').val() != "" ? $('#txtVisualizacoes').val() : 0;
	var ingredientes = new String();
	
	//Recupera os ingredientes
	$('#ingredientes input[type=text]').each(function(i)
	{
		var valor = this.value;
		if(valor != "" && i < qtdIngrediente)
		{
			if(i+1 < qtdIngrediente)
				ingredientes += valor+",";
			else
				ingredientes += valor;
		}
	});
	
	//Recupera as tags
	$('#tags input[type=text]').each(function(i)
	{
		var valor = this.value;
		if(valor != "" && i < qtdTag)
		{
			if(i+1 < qtdTag)
				tags += valor+",";
			else
				tags += valor;
		}
	});
	
	//JSON
	var dados =
	{
			codigo : codigo,
			titulo: titulo,
			descricao: descricao,
			ingredientes: ingredientes,
			preparo: preparo,
			rendimento: rendimento,
			tempo: tempo,
			imagem: imagem,
			tags: tags,
			visualizacoes : visualizacoes,
			tipoCategoria : tipoCategoria,
			codigoUsuario : codigoUsuario
	};
	
	//AJAX para Cadastro
	$.ajax
	({
		type : 'POST',
		url : '${urlRequest}',
		data : dados,
		success: function(data,texto)
		{
			$('#loading').hide();
			$('#formulario').show();
			$('#mensagemSucesso').modal('toggle');
			$('#mensagemSucesso').modal('show');
			$('#btnContinuarSucesso').click(function(event)
			{
				redirect();
			});
			$('#mensagemSucesso').on('hidden', function () 
			{
				redirect();
			});
		},
		error: function(request,status,error)
		{
			processando = false;
			$('#btnCadastrar').attr('disabled',false);
			$('#mensagemErro').modal('toggle');
			$('#mensagemErro').modal('show');
			$('#btnContinuarErro').click(function(event)
			{
				$('#mensagemErro').modal('hide');
			});
		}
	});
}

function redirect()
{
	window.location.replace("${receita != null ? urlExibe : urlReceitas}");
}

function limpar()
{
	$('#formReceita').each(function()
	{
		this.reset();
	});
}

function valida()
{
	var titulo = $('#txtTitulo').val();
	var descricao = $('#txtDescricao').val();
	var tipoCategoria = $('#cbCategoria').val();
	var preparo = CKEDITOR.instances.txtPreparo.getData();
	var rendimento = $('#txtRendimento').val() != "" ? $('#txtRendimento').val() : 0;
	var tempo = $('#txtTempo').val() != "" ? $('#txtTempo').val() : 0;
	
	if(titulo == "")
	{
		mensagem("Informe o [TÍTULO] da receita !");
		return false;
	}
	if(descricao == "")
	{
		mensagem("Informe a [DESCRIÇÃO] da receita !");
		return false;
	}
	if(tipoCategoria == "")
	{
		mensagem("Informe a [CATEGORIA] da receita !");
		return false;
	}
	if(preparo == "")
	{
		mensagem("Informe o [MODO DE PREPARO] da receita !");
		return false;
	}
	if(rendimento == "")
	{
		mensagem("Informe o [RENDIMENTO] da receita !");
		return false;
	}
	if(tempo == "")
	{
		mensagem("Informe o [TEMPO DE PREPARO] da receita !");
		return false;
	}
	return true;
}

function mensagem(valor)
{
	$('#mensagemAtencao').modal('toggle');
	$('#mensagemAtencao').modal('show');
	$('#atencao').html(valor);
	$('#btnContinuarAtencao').click(function(event)
	{
		$('#mensagemAtencao').modal('hide');
	});
}

</script>

<br/><br/>
<!-- FOOTER -->
<c:import url="/resources/html/footer.jsp"/>
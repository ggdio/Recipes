<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<!-- URL's -->
<c:url value="/categorias/${categoria != null ? 'altera' : 'adiciona'}" var="urlRequest"/>
<c:url value="/categoria/lista" var="urlCategorias"/>
<c:url value="/upload/" var="urlUpload"/>
<c:url value="/resources/img/bigLoading.gif" var="loading"/>
<c:url value="/resources/img/logo.png" var="logo"/>
<c:url value="/resources/img/info.png" var="info"/>
<c:url value="/resources/img/alerta.png" var="alerta"/>
<c:url value="/resources/img/pergunta.png" var="pergunta"/>
<c:url value="/resources/img/default.jpg" var="imgDefault"/>

<!-- HEADER -->
<c:import url="/resources/html/header.jsp">
	<c:param value="Espaço Receitas - Formulário Categoria" name="title"/>
	<c:param value="Formuçário de categorias" name="metaDescricao"/>
</c:import>

<center>
	<span style="font-size: x-large;" class="label label-important">Formulário - Categoria</span>
</center>

<br/>
<br/>

<!-- LOADING -->
<tag:loading id="loading" align="center" width="50%"/>

<div id="formulario" hidden="hidden">
<form id="formCategoria" class="well-green">
	<fieldset>
		
		<label for="tipo"><b>Tipo</b></label>
		<input class="span4" type="text" id="txtTipo" name="tipo" placeholder="Tipo da categoria..." maxlength="100"/>
		
		<label for="descricao"><b>Descrição</b></label>
		<input class="span6" type="text" id="txtDescricao" name="descricao" placeholder="Descrição da categoria..." maxlength="300"/>
		
		<p/>
		<ul class="thumbnails">
		    <li class="span6">
			    <div class="well thumbnail pull-left">
			    	<p><b>Imagem</b></p>
			    	<img id="imgCategoria" width="360" src="${categoria != null ? categoria.imagem : imgDefault }" height="268" alt=""/>
				    <p/>
				    <p><a id="btnSelecionarImagem" class="btn btn-primary"><i class="icon-picture icon-white"></i>Selecionar</a></p>
			    </div>
		    </li>
		</ul>
		
		<div class="form-actions"> 
			<a id="btnCadastrar" class="btn btn-primary btn-large">
				<i class="icon-ok icon-white"></i>
				<span>Cadastrar</span>
			</a>
			<a class="btn btn-large" onclick="limpar()">Limpar</a>
		</div>
	</fieldset>
</form>

</div>

<tag:popup id="mensagemSucesso" component="null" imagem="${info}" titulo="Sucesso !">
	<jsp:attribute name="texto">
		<center>
			<span style="font-size: medium;" class="label label-success">Categoria cadastrada com Sucesso !</span>
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
var caminhoImg = "${categoria != null ? categoria.imagem : imgDefault}";
var tipoUpload = "temp";
var cadastrarCategoria = false;
function init() 
{
	document.getElementById('formUploadImagem').onsubmit = onSubmit;
	document.getElementById("uploadTarget").onload = uploadDone;
}
function onSubmit()
{
	if(!cadastrarCategoria)
		$('#imgCategoria').attr('src','${loading}');
	document.getElementById('formUploadImagem').target = 'uploadTarget';
}
function uploadDone()
{
	var ret = frames['uploadTarget'].document.getElementsByTagName("body")[0].innerHTML;
	var data = eval("("+ret+")");
	caminhoImg = data.response.informacao.caminhoWeb;
	if(cadastrarCategoria)
	{
		cadastrar();	
	}
	else
	{
		$('#popupSelecionarImagem').modal('hide');
		$('#imgCategoria').attr('src',caminhoImg);
	}
}
window.onload = init;

$(document).ready(function()
{
	//Seta o action do upload de imagem
	$('#formUploadImagem').attr('action','${urlUpload}'+tipoUpload);
	
	//Botão Cadastrar
	$('#btnCadastrar').click(function(event)
	{
		if(valida())
		{
			$('#btnCadastrar').attr('disabled',true);
			var imagem = $('#imgCategoria').attr('src');
			if(imagem != "${imgDefault}" && imagem != "${categoria.imagem}")
			{
				cadastrarCategoria = true;
				$('form#formUploadImagem').attr('action','${urlUpload}/def');
				$('form#formUploadImagem').submit();
			}
			else
			{
				cadastrar();
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
	var tipo = $('#txtTipo').val();
	var descricao = $('#txtDescricao').val();
	var imagem = caminhoImg;
	
	//JSON
	var dados =
	{
		tipo : tipo,
		descricao : descricao,
		imagem : imagem
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
	window.location.replace("${urlCategorias}");
}

function limpar()
{
	$('#formCategoria').each(function()
	{
		this.reset();
	});
}

function valida()
{
	//Dados
	var tipo = $('#txtTipo').val();
	var descricao = $('#txtDescricao').val();
	
	if(tipo == "")
	{
		mensagem("Informe o [TIPO] da categoria !");
		return false;
	}
	if(descricao == "")
	{
		mensagem("Informe a [DESCRIÇÃO] da categoria !");
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
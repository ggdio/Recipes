<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<!-- URL's -->
<c:url value="/resources/jquery/JQuery.js" var="jquery"/>
<c:url value="/resources/bootstrap/js/bootstrap.js" var="bootstrap_js"/>
<c:url value="/resources/bootstrap/css/bootstrap-united.css" var="bootstrap_css" />
<c:url value="/resources/img/logo.png" var="logo"/>
<c:url value="/resources/img/favicon.png" var="favicon"/>
<c:url value="/resources/img/background.jpg" var="background"/>
<c:url value="/resources/img/header.jpg" var="header"/>
<c:url value="/receitas/" var="receitas"/>
<c:url value="/receitas/pesquisa/" var="pesquisaReceitas"/>
<c:url value="/receitas/lista" var="listaReceitas"/>
<c:url value="/receitas/formulario" var="formularioReceita"/>
<c:url value="/categorias/lista" var="listaCategorias"/>
<c:url value="/categorias/formulario" var="formularioCategoria"/>
<c:url value="/servicos" var="servicos"/>
<c:url value="/informacoes" var="informacoes"/>
<c:url value="/contato" var="contato"/>
<c:url value="/login/entrar" var="login"/>
<c:url value="/login/sair" var="logout"/>
<c:url value="/arearestrita/painel" var="painelControle"/>
<c:url value="/" var="home"/>

<c:url value="/resources/img/slide/slide_a.jpg" var="slide_a"/>
<c:url value="/resources/img/slide/slide_b.png" var="slide_b"/>
<c:url value="/resources/img/slide/slide_c.jpg" var="slide_c"/>
<c:url value="/resources/img/slide/slide_d.png" var="slide_d"/>
<c:url value="/resources/img/slide/slide_e.jpg" var="slide_e"/>

<html>

<head>
	<!-- ICONE -->
	<title>
	<c:choose>
		<c:when test="${param.title != null}">${param.title}</c:when>
		<c:otherwise>Espaço Receitas</c:otherwise>
	</c:choose>
	</title>
	
	<!-- FAVICON -->
	<link type="image/png" rel="icon" href="${favicon}">
	
	<!-- CSS -->
	<link type="text/css" rel="stylesheet" href="${bootstrap_css}"/>
	
	<!-- METAS -->
	<c:choose>
		<c:when test="${param.metaDescricao != null}">
			<meta name="description" content="${param.metaDescricao}"/>
		</c:when>
		<c:otherwise>
			<meta name="description" content="O mais novo portal de acesso a novidades gastronômicas para todos os gostos. Receitas diversas e muito gostosas !."/>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${param.metaKeywords!= null}">
			<meta name="description" content="${param.metaKeywords}"/>
		</c:when>
		<c:otherwise>
			<meta name="keywords" content="receitas,receita,comida,culinaria,gostoso,saboroso,salgados,doces,bebidas,bolos,salgadinhos,panquecas,sobremesas,deliciosas,como fazer,ingredientes,receita de bolo,passo a passo"/>
		</c:otherwise>
	</c:choose>
	<meta name="expires" content="never"/>
</head>

<!-- IMPORT JQUERY+BOOTSTRAP -->
<script type="text/javascript" src="${jquery}"></script>
<script type="text/javascript" src="${bootstrap_js}"></script>

<!-- JAVASCRIPT -->
<script type="text/javascript">
	$(document).ready(function()
	{
		$('#slide_header').carousel({interval:2000,pause:false});
		$('#btnReceitas').dropdown();
		$('#btnAreaRestrita').dropdown();
		$('#testes').dropdown();
		
		$('#btnPesquisar').click(function(event)
		{
			pesquisa();
		});
		$('form#formPesquisa').submit(function(event)
		{
			event.preventDefault();
			pesquisa();
		});
		$('form#formPesquisa').bind('keydown',function(event)
		{
			var key = e.keyCode || e.which;
            if (key === 13) 
            {
            	event.preventDefault();
                pesquisa();
            }
		});
	});
	function pesquisa()
	{
		var busca = $('#txtPesquisa').val();
		if(busca != "")
		{
			busca = busca.split(" ").join("+");
			var url = "${pesquisaReceitas}"+busca;
			window.location.replace(url);
		}
	}
	function verificaLogout(url)
	{
		resposta = confirm("Deseja realmente realizar o logout ?");
		if(resposta)
		{
			document.location.href = url;
		}
	}
</script>

<body background="${background}">
	
    <!-- BARRA DE NAVEGAÇÃO -->
    <div class="navbar navbar-fixed-top">
	    <div class="navbar-inner">
		    <div class="container pull-left">
		    	<a class="brand" href="${home}"><b>Espaço Receitas</b></a>
	    	    <ul class="nav">
				    <li><a href="${home}"><i class="icon-home icon-white"></i><b>Home</b></a></li>
			    	<li class="dropdown">
					    <a id="btnReceitas" class="dropdown-toggle" data-toggle="dropdown" href="#"><b>Conteúdo</b><b class="caret"></b></a>
					    <ul class="dropdown-menu">
						    <li><a href="${listaReceitas}">Receitas</a></li>
						    <li><a href="${listaCategorias}">Categorias</a></li>
						    <li><a href="#">Dicas</a></li>
					    </ul>
				    </li>
				    <li><a href="${servicos}"><b>Serviços</b></a></li>
				    <li><a href="${informacoes}"><b>Informações</b></a></li>
				    <li><a href="${contato}"><b>Contato</b></a></li>
				    <li class="dropdown">
					    <a id="btnAreaRestrita" class="dropdown-toggle" data-toggle="dropdown" href="#"><b>Área Restrita</b><b class="caret"></b></a>
					    	<c:choose>
					    		<c:when test="${usuarioLogado == null}">
					    			<tag:formLogin classe="dropdown-menu well" inputSpans="span4"/>
					    		</c:when>
					    		<c:otherwise>
						    		<ul class="dropdown-menu">
						    			<li><a href="#">Meus Dados</a></li>
						    			<li><a href="${painelControle}">Painel de Controle</a></li>
						    			<li><a href="#" onclick="verificaLogout('${logout}')">Logout</a></li>
						    		</ul>
					    		</c:otherwise>
					    	</c:choose> 
				    </li>
			    </ul>
		    </div>
		    <form id="formPesquisa" class="pull-right">
		    	<input id="txtPesquisa" style="vertical-align: baseline;" name="pesquisa" class="search-query span3" placeholder="Pesquisar..." type="text">
		    	<a id="btnPesquisar" href="#" class="btn">Buscar</a>
		    </form>
	    </div>
    </div>
    
    <br>

    <!-- HEADER -->
    <div style="background-color: #4682B4;height: 80px;" class="page-header">
    
    	<!-- LOGOTIPO -->
	    <a class="span5 pull-left" href="${home}"><img src="${logo}" alt="Espaço Receitas" title="Espaço Receitas" width="100%" height="100px"/></a>
	    
	    <!-- SLIDE -->
		<div id="slide_header" class="carousel span4 pull-right">
			
			<!-- ITEMS -->
			<div class="carousel-inner">
					<div class="item active">
						<img src="${slide_a}" width="100%" height="96px"/>
					</div>
					<div class="item">
						<img src="${slide_b}" width="100%" height="96x"/>
					</div>
					<div class="item">
						<img src="${slide_c}" width="100%" height="96px"/>
					</div>
					<div class="item">
						<img src="${slide_d}" width="100%" height="96px"/>
					</div>
					<div class="item">
						<img src="${slide_e}" width="100%" height="96px"/>
					</div>
			</div>
		</div> 
	</div>
	
	<br><br>  
<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- URL's -->
<c:url value="/resources/jquery/JQuery.js" var="jquery"/>
<c:url value="/resources/bootstrap/js/bootstrap.js" var="bootstrap_js"/>
<c:url value="/resources/bootstrap/css/bootstrap.css" var="bootstrap_css" />
<c:url value="/resources/img/logo.png" var="logo"/>
<c:url value="/resources/img/favicon.png" var="favicon"/>
<c:url value="/resources/img/background.jpg" var="background"/>
<c:url value="/resources/img/header.jpg" var="header"/>
<c:url value="/" var="home"/>
<c:url value="/receitas/lista" var="receitas"/>
<c:url value="/servicos" var="servicos"/>
<c:url value="/informacoes" var="informacoes"/>
<c:url value="/contato" var="contato"/>
<c:url value="/login/formulario" var="areaRestrita"/>


<html>

<head>
	<!-- ICONE -->
	<title>
	<c:choose>
		<c:when test="${param.title != null}">${param.title}</c:when>
		<c:otherwise>Site de Receitas</c:otherwise>
	</c:choose>
	</title>
	
	<!-- FAVICON -->
	<link type="image/png" rel="icon" href="${favicon}">
	
	<!-- CSS -->
	<link type="text/css" rel="stylesheet" href="${bootstrap_css}"/>
</head>

<!-- IMPORT JQUERY+BOOTSTRAP -->
<script type="text/javascript" src="${jquery}"></script>
<script type="text/javascript" src="${bootstrap_js}"></script>

<!-- JAVASCRIPT -->
<script type="text/javascript">
	$(document).ready(function()
	{
		$("#btnReceitas").dropdown();
	});
</script>

<body background="${background}">
	
    <!-- BARRA DE NAVEGAÇÃO -->
    <div class="navbar navbar-fixed-top">
	    <div class="navbar-inner">
		    <div class="container pull-left">
		    	<a class="brand" href="${home}">Receitas</a>
	    	    <ul class="nav">
				    <li class="${request.requestUrl == home ? 'active' : 'inactive'}"><a href="${home}"><i class="icon-home icon-white"></i>Home</a></li>
			    	<li class="dropdown">
					    <a id="btnReceitas" class="dropdown-toggle" href="#" data-toggle="dropdown">Receitas<b class="caret"></b></a>
					    <ul class="dropdown-menu">
						    <li><a href="${receitas}">Todas as Receitas</a></li>
						    <li><a href="#">Categorias</a></li>
					    </ul>
				    </li>
				    <li><a href="${servicos}">Serviços</a></li>
				    <li><a href="${informacoes}">Informações</a></li>
				    <li><a href="${contato}">Contato</a></li>
				    <li><a href="${areaRestrita}">Área Restrita</a></li>
			    </ul>
		    </div>
		    <form class="pull-right">
		    	<input name="pesquisa" class="search-query" placeholder="Pesquisar..." type="text" class="span2">
		    	<button class="btn btn-primary">Buscar</button>
		    </form>
	    </div>
    </div>
    
    <br>
    
    <!-- HEADER -->
    <div style="background-color: #FFFFCC;" class="page-header well">
	    <img src="${logo}" width="64px" height="64px"/>
		<h2 style="display: inline;vertical-align: bottom;">Site de Receitas</h2>
	</div>
	<br><br>
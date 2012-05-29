<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- URL's -->
<c:url value="/resources/bootstrap/css/bootstrap.css" var="bootstrap_css" />
<c:url value="/resources/img/logo.png" var="logo"/>
<c:url value="/resources/img/favicon.png" var="favicon"/>
<c:url value="/resources/img/background.jpg" var="background"/>
<c:url value="/resources/img/header.jpg" var="header"/>

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

<body background="${background}">
	
    <!-- BARRA DE NAVEGAÇÃO -->
    <div class="navbar navbar-fixed-top">
	    <div class="navbar-inner">
		    <div class="container pull-left">
		    	<a class="brand" href="#">Receitas</a>
	    	    <ul class="nav">
				    <li class="active"><a href="#"><i class="icon-home icon-white"></i>Home</a></li>
				    <li><a href="#">Receitas</a></li>
				    <li><a href="#">Serviços</a></li>
				    <li><a href="#">Informações</a></li>
				    <li><a href="#">Contato</a></li>
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
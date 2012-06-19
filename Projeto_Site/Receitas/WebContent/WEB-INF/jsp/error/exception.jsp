<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<!-- URL's -->
<c:url value="/" var="inicio"/>
<c:url value="/resources/img/erro.png" var="imgErro"/>

<!-- HEADER -->
<c:import url="/resources/html/header.jsp">
	<c:param value="Receitas - Erro" name="title"/>
</c:import>

<!-- ALERTA -->
<div class="container">
	<br><br><br><br><br><br>
    <div class="hero-unit">
	    <h1>Ops, ocorreu um erro !</h1>
	    <p>Desculpe-nos pelo inconveniente, mas ocorreu um erro durante o processo.</p>
	    <p>
	    <a id="btnVoltar" class="btn btn-primary btn-large" href="${inicio}">Voltar ao Início</a>
<!-- 	    <a id="btnDetalhes" class="btn btn-warning btn-large">Ver Detalhes</a> -->
    </div>
</div>

<!-- POPUP -->
<%-- <tag:popup id="popup" component="btnDetalhes" titulo="Detalhes do Erro" imagem="${imgErro}"> --%>
<%-- 	<jsp:attribute name="texto"> --%>
<%-- 		<p>Tipo de Erro:${exception.class.simplename}</p> --%>
<%-- 		<p>Causa do Erro:${exception.cause}</p> --%>
<%-- 		<p>Mensagem:${exception.message}</p> --%>
<%-- 		<p>StackTrace:${exception.stacktrace}</p> --%>
<%-- 	</jsp:attribute> --%>
<%-- </tag:popup> --%>

<!-- FOOTER -->
<c:import url="/resources/html/footer.jsp"/>
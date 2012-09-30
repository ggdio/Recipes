<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<!-- URL's -->
<c:url value="/" var="inicio"/>

<!-- HEADER -->
<c:import url="/resources/html/header.jsp">
	<c:param value="Espaço Receitas - 404" name="title"/>
</c:import>

<!-- ALERTA -->
<div class="container">
	<br><br><br><br><br><br>
    <div class="hero-unit">
	    <h1>Página Inexistente (404)</h1>
	    <p>A página que você solicitou não existe !</p>
	    <p>
	    <a id="btnDetalhes" class="btn btn-primary btn-large" href="${inicio}">Voltar ao Início</a>
    </div>
</div>

<!-- FOOTER -->
<c:import url="/resources/html/footer.jsp"/>
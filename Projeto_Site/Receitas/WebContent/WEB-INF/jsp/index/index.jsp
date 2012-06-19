<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<!-- URL's -->
<c:url value="/resources/img/logo.png" var="logo"/>

<!-- HEADER -->
<c:import url="/resources/html/header.jsp">
	<c:param value="Receitas - Home" name="title"/>
</c:import>

<!-- ALERTA -->
<div class="container">
	<br><br><br><br><br><br>
    <div class="hero-unit">
	    <h1>Em Construção</h1>
	    <p>Em breve, um novo portal de acesso a novidades gastronõmicas para todos os gostos...</p>
	    <p>
	    <a id="btnDetalhes" class="btn btn-primary btn-large">Veja Detalhes</a>
    </div>
</div>

<!-- POPUP -->
<tag:popup id="popup" component="btnDetalhes" imagem="${logo}" titulo="Detalhes do Site">
	<jsp:attribute name="texto">
		<p>Em breve, um novo portal de acesso a novidades gastronômicas para todos os gostos...</p>
		<p>Categorias:</p>
		<ul>
			<li>Carnes</li>
			<li>Massas</li>
			<li>Pães</li>
			<li>Sopas</li>
			<li>Aperitivos</li>
			<li>Tortas</li>
			<li>Bolos</li>
			<li>Cupcakes</li>
			<li>Biscoitos</li>
		</ul>
		<p>Dentre diversos outros tipos de delícias, aguarde !</p>
	</jsp:attribute>
</tag:popup>

<!-- FOOTER -->
<c:import url="/resources/html/footer.jsp"/>
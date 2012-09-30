<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<!-- URL's -->
<c:url value="/login/entrar" var="urlLogin"/>

<!-- HEADER -->
<c:import url="/resources/html/header.jsp">
	<c:param value="Espaço Receitas - Login" name="title"/>
	<c:param value="Formulário de login no sistema" name="metaDescricao"/>
</c:import>

<br/>

<div class="container-fluid">


<c:if test="${response != null}">
	<div class="row-fluid">
		<tag:alert titulo="Atenção!" id="mensagemLogin" tipo="alert-warning" texto="${response.mensagem}" classe="span6"/>
	</div>
</c:if>

<div class="row-fluid">
	<tag:formLogin classe="well-green span6"/>
</div>

</div>


<!-- FOOTER -->
<c:import url="/resources/html/footer.jsp"/>
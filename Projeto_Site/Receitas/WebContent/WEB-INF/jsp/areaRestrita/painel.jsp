<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<!-- URL's -->
<c:url value="/receitas/adiciona" var="urlAdiciona"/>
<c:url value="/receitas/lista" var="urlReceitas"/>
<c:url value="/receitas/altera/${receita.codigo}-${receita.titulo.replaceAll(' ','-')}" var="urlAltera"/>
<c:url value="/resources/img/logo.png" var="logo"/>

<!-- HEADER -->
<c:import url="/resources/html/header.jsp">
	<c:param value="Espaço Receitas - Painel Controle" name="title"/>
	<c:param value="Painel de controle do Espaço Receitas" name="metaDescricao"/>
</c:import>

<!-- TÍTULO -->
<center>
	<span style="font-size: xx-large;" class="label label-warning">Painel de Controle</span>
</center>
<br/>

<div class="well-green">



</div>


<br/><br/>
<!-- FOOTER -->
<c:import url="/resources/html/footer.jsp"/>
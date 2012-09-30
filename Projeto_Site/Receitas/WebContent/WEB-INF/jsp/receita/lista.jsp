<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<!-- URL's -->
<c:url value="/resources/img/logo.png" var="logo"/>
<c:url value="/resources/img/loading.gif" var="loading"/>
<c:url value="/resources/img/default.jpg" var="imgDefault"/>
<c:url value="/receitas/" var="prefixo"/>

<!-- HEADER -->
<c:import url="/resources/html/header.jsp">
	<c:param value="Espaço Receitas - Lista Receitas" name="title"/>
	<c:param value="Espaço Receitas - Listagem das receitas" name="metaDescricao"/>
</c:import>

<!-- JAVASCRIPT -->
<script>
$(document).ready(function()
{
	//Esconde Loading
	$("#loading").hide();
	
	//Exibe Dados
	$("#divReceitas").show();
	
	//Exibe o alerta
	$('#alerta').show();
});
</script>

<div class="container-fluid">

<!-- LOADING -->
<tag:loading id="loading" align="center" width="50%"/><br/>

<!-- MENSAGEM DE RETORNO -->
<c:if test="${response.mensagem != null}">
	<tag:alert texto="${response.mensagem}" titulo="Pesquisa" id="alerta" tipo="${response.status == 'Sucesso' ? 'alert-success' : 'alert-warning'}" align="center" width="50%" hidden="hidden"/>
</c:if>

<!-- THUMBNAILS -->
<div id="divReceitas" hidden="hidden">
	<ul class="thumbnails">
	<c:forEach items="${response.informacao}" var="receita">
		<!-- URL's -->
		<c:set var="urlExibe" value="${prefixo}${receita.titulo.replace(' ','-')}/${receita.codigo}"/>
		
		<!-- THUMBNAIL -->
	    <li class="span4 pull-left">
		    <div class="well thumbnail">
			    <object data="${receita.imagem}" width="100%" height="180" type="image">
					<object data="${imgDefault}" width="100%" height="180" type="image"></object>
				</object>
			    <h4 class="box">${receita.titulo}</h4>
			    <p class="box">${receita.descricao}</p>
			    <p><a id="btnDetalhes_${receita.codigo}" href="${urlExibe}" class="btn btn-primary"><b>VER</b></a></p>
		    </div>
	    </li>
		
	</c:forEach>
	</ul>
</div>

</div>

<!-- FOOTER -->
<c:import url="/resources/html/footer.jsp"/>
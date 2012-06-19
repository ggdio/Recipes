<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<!-- URL's -->
<c:url value="/resources/img/logo.png" var="logo"/>
<c:url value="/resources/img/loading.gif" var="loading"/>

<!-- HEADER -->
<c:import url="/resources/html/header.jsp">
	<c:param value="Receitas - Home" name="title"/>
</c:import>

<!-- JAVASCRIPT -->
<script>
$(document).ready(function()
{
	//Esconde Loading
	$("#loading").hide();
	//Exibe Dados
	$("#divReceitas").show();
});
</script>

<!-- LOADING -->
<tag:loading id="loading" align="center" width="50%"/>

<!-- THUMBNAILS -->
<div id="divReceitas" hidden="hidden">
	<ul class="thumbnails">
<c:forEach items="${receitas}" var="receita">
	
	<!-- THUMBNAIL -->
    <li class="span4">
	    <div class="well thumbnail">
		    <img width="100%" src="http://placehold.it/260x180" alt="">
		    <h5>${receita.titulo}</h5>
		    <p>${receita.descricao}</p>
		    <p><a id="btnDetalhes_${receita.codigo}" class="btn btn-primary">VER</a></p>
	    </div>
    </li>
    
	<!-- POPUP -->
	<tag:popup id="popup_${receita.codigo}" component="btnDetalhes_${receita.codigo}" imagem="${logo}" titulo="${receita.titulo}">
		<jsp:attribute name="texto">
			<p>Rendimento: ${receita.rendimento}</p>
			<p>Categoria: ${receita.categoria.tipo}</p>
			<p>Tempo para preparo: ${receita.tempo} Minutos</p>
		</jsp:attribute>
	</tag:popup>
	
</c:forEach>
	</ul>
</div>

<!-- FOOTER -->
<c:import url="/resources/html/footer.jsp"/>
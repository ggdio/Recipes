<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<c:url value="/receitas/lista" var="urlReceitas"/>
<c:url value="/categorias/lista" var="urlCategorias"/>
<c:url value="/resources/img/default.jpg" var="imgDefault"/>
<c:url value="/receitas/" var="prefixo"/>

<!-- HEADER -->
<c:import url="/resources/html/header.jsp">
	<c:param value="Espaço Receitas - Home" name="title"/>
</c:import>

<script>
$(document).ready(function()
{
	$('#carrossel').carousel({interval:5000});
});
</script>

<div class="container-fluid">

<c:if test="${response != null}">
	<div class="row-fluid">
		<tag:alert titulo="Mensagem" id="mensagemLogin" tipo="alert-success" texto="${response.mensagem}" classe="span6"/>
	</div>
</c:if>

<div class="row-fluid">

	<!-- CENTRAL -->
	<div class="span9 well-darkgreen">
		
		<div class="row-fluid">
			<div id="carrossel" class="carousel span8">
				<span style="font-size: x-large;" class="label label-info">Receitas Recentes</span>
				<p/>
				<!-- ITEMS -->
				<div class="carousel-inner">
					<c:forEach items="${receitasRecente}" var="receita" varStatus="status">
					
						<c:set var="urlExibe" value="${prefixo}${receita.titulo.replace(' ','-')}/${receita.codigo}"/>
						<c:set var="classe" value="${status.count == 1 ? 'item active' : 'item' }"/>
						
						<div class="${classe}">
							<a href="${urlExibe}">
								<object data="${receita.imagem}" width="100%" height="400" type="image">
									<object data="${imgDefault}" width="100%" height="400" type="image"></object>
								</object>
							</a>
							<div class="carousel-caption">
								<h4>${receita.titulo}(<fmt:formatDate value="${receita.data.time}" pattern="dd/MM/yyyy"/>)</h4>
								<p>${receita.descricao}</p>
							</div>
						</div>
						
					</c:forEach>
				</div>
				<!-- SETAS DE NAVEGAÇÃO -->
				<a class="carousel-control left" href="#carrossel" data-slide="prev">&lsaquo;</a>
				<a class="carousel-control right" href="#carrossel" data-slide="next">&rsaquo;</a>
			</div>
		</div>
		
		<p/>
		
		<div class="row-fluid">
			<span style="font-size: x-large;" class="label label-important">Destaques</span>
			<p/>
			<ul class="thumbnails">
			<c:forEach items="${receitasDestaque}" var="receita">
				<!-- URL's -->
				<c:set var="urlExibe" value="${prefixo}${receita.titulo.replace(' ','-')}/${receita.codigo}"/>
				<!-- THUMBNAIL -->
			    <li class="span4" style="margin-left: 0px;">
				    <div class="thumbnail well" style="width: 280px;">
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
	
	<!-- LATERAL -->
	<div class="span3">
	
		<span style="font-size: x-large;" class="label label-success">Seções</span>
		<p/>
		<div class="well-yellow clearfix" style="margin-bottom: 15px;">
			<p><a href="${urlReceitas}" class="btn btn-primary btn-large span12">Todas as Receitas&nbsp;<i class="icon-share-alt"></i></a></p>
			<br/>
			<p><a href="${urlCategorias}" class="btn btn-info btn-large span12">Categorias&nbsp;<i class="icon-share-alt"></i></a></p>
		</div>
		
		<span style="font-size: x-large;" class="label label-important">Dicas Legais<i class="icon-exclamation-sign"></i></span>
		<p/>
		<div class="well-orange clearfix">
		    <h4>Azeite é saudável</h4>
		    <h4>Canela ajuda a emagrecer</h4>
		</div>
	</div>
</div>

</div>


<!-- FOOTER -->
<c:import url="/resources/html/footer.jsp"/>
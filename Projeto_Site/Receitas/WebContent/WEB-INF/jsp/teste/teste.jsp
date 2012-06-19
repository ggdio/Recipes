<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<!-- URL's -->
<c:url value="/resources/testes/carroA.jpg" var="a"/>
<c:url value="/resources/testes/carroB.jpg" var="b"/>
<c:url value="/resources/testes/carroC.jpg" var="c"/>
<c:url value="/resources/testes/carroD.jpg" var="d"/>
<c:url value="/resources/testes/carroE.jpg" var="e"/>

<!-- HEADER -->
<c:import url="/resources/html/header.jsp">
	<c:param value="Receitas - Home" name="title"/>
</c:import>

<!-- SLIDES -->
<div id="carrossel" class="carousel span7">
	<!-- ITEMS -->
	<div class="carousel-inner">
		<div class="item active">
			<img src="${a}" width="100%" height="345"/>
			<div class="carousel-caption">
				<h4>Titulo</h4>
				<p>Texto Texto Texto</p>
			</div>
		</div>
		<div class="item">
			<img src="${b}" width="100%" height="345"/>
			<div class="carousel-caption">
				<h4>Titulo</h4>
				<p>Texto Texto Texto</p>
			</div>
		</div> 
		<div class="item">
			<img src="${c}" width="100%" height="345"/>
			<div class="carousel-caption">
				<h4>Titulo</h4>
				<p>Texto Texto Texto</p>
			</div>
		</div> 
		<div class="item">
			<img src="${d}" width="100%" height="345"/>
			<div class="carousel-caption">
				<h4>Titulo</h4>
				<p>Texto Texto Texto</p>
			</div>
		</div>
		<div class="item">
			<img src="${e}" width="100%" height="345"/>
			<div class="carousel-caption">
				<h4>Titulo</h4>
				<p>Texto Texto Texto</p>
			</div>
		</div> 
	</div>
	<!-- SETAS DE NAVEGAÇÃO -->
	<a class="carousel-control left" href="#carrossel" data-slide="prev">&lsaquo;</a>
	<a class="carousel-control right" href="#carrossel" data-slide="next">&rsaquo;</a>
</div>

    
<script type="text/javascript">
	$(document).ready(function()
	{
		$("#carrossel").carousel({interval:2000});
	});
</script>

<!-- FOOTER -->
<c:import url="/resources/html/footer.jsp"/>
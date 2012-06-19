<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<!-- URL's -->
<c:url value="/resources/img/logo.png" var="logo"/>

<!-- HEADER -->
<c:import url="/resources/html/header.jsp">
	<c:param value="Receitas - Adicionar" name="title"/>
</c:import>

<h1 align="center">Nova Receita</h1>
<br><br>

<form class="well span10">
	<label>Título</label>
	<input class="span2" type="text" name="titulo"/><br/>
	<label>Descrição</label>
	<input class="span4" type="text" name="descricao"/><br/>
	<label>Ingredientes</label>
	<div id="ingredientes">
		<tag:popover texto="Adicionar ingrediente na receita" titulo="Ingrediente" classe="btn btn-success" value="Adicionar" id="btnIngredientes"/><br/>
	</div>
	<label>Categoria</label>
	<tag:combobox dados="${categorias}" name="categoria" span="span4"/><br/>
	<label>Preparo</label>
	<textarea id="preparo" class="span8" rows="20" name="preparo" placeholder="Texto Aqui..."></textarea><br/>
	<label>Rendimento</label>
	<input class="span2" type="text" name="rendimento"/><br/>
	<label>Tempo de Preparo</label>
	<input class="span2" type="text" name="tempo"/><br/>
	<label>Imagem</label>
	<input class="span4" type="text" name="imagem" placeholder="Foto da receita..."/><br/>
	<label>Tags</label>
	<input class="span4" type="text" name="tags" placeholder="Tags para busca..."/><br/>
</form>

<script type="text/javascript">
var qtdIngrediente = 0;

$(document).ready(function()
{
	$('#preparo').wysihtml5();
	$('#estilo').dropdown();
	$('#btnIngredientes').click(function(event)
	{
		qtdIngrediente++;
		$('#ingredientes').append("<br/><input id='ingrediente_"+qtdIngrediente+"' class='span4' placeholder='Algum ingrediente...' type='text' name='ingrediente_"+qtdIngrediente+"'/>&nbsp;&nbsp;<a class='btn btn-danger' style='vertical-align: top;' title='Remover Ingrediente'>X</a>");
	});
	$("#teste").click(function(event)
	{
		alert($("#preparo").val());
	});
});
</script>

<a id="teste" class="btn">Teste</a>
<!-- FOOTER -->
<c:import url="/resources/html/footer.jsp"/>
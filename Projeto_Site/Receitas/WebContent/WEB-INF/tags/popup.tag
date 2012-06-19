<%@ tag language="java" pageEncoding="ISO-8859-1"%>

<!-- TAGLIB's -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- ATRIBUTOS -->
<%@ attribute name="id" required="true" %>
<%@ attribute name="component" required="true" %>
<%@ attribute name="titulo" required="false" %>
<%@ attribute name="texto" required="false" %>
<%@ attribute name="rodape" required="false" %>
<%@ attribute name="imagem" required="false" %>

<!-- URL's -->
<c:url value="/resources/jquery/JQuery.js" var="jquery"/>
<c:url value="/resources/bootstrap/js/bootstrap.js" var="bootstrap_js"/>

<!-- IMPORT JQUERY+BOOTSTRAP -->
<script type="text/javascript" src="${jquery}"></script>
<script type="text/javascript" src="${bootstrap_js}"></script>


<!-- POPUP -->
<div id="${id}" class="modal hide fade">
	<div class="modal-header">
		<button class="close" data-dismiss="modal">x</button>
		<c:if test="${imagem != null}">
			<img src="${imagem}" width="40px" height="40px"/>
		</c:if>
		<h3 style="display: inline;vertical-align: bottom;">${titulo}</h3>
	</div>
	<div class="modal-body">
		<p>${texto}</p>
	</div>
	<div class="modal-footer">
		<div class="pull-left">${rodape}</div>
		<a id="btnVoltar_${id}" href="#" class="btn btn-success"><b>Voltar</b></a>
	</div>
</div>

<!-- SCRIPT -->
<script type="text/javascript">
	$(document).ready(function()
	{
		$('#${component}').click(function(event) 
		{
			$('#${id}').modal('toggle');
			$('#${id}').modal('show');
		});
		
		$('#btnVoltar_${id}').click(function(event) 
		{
			$('#${id}').modal('hide');
		});
		
	});
</script>
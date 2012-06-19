<%@ tag language="java" pageEncoding="ISO-8859-1"%>

<!-- TAGLIB's -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- ATRIBUTOS -->
<%@ attribute name="id" required="true" %>
<%@ attribute name="align" required="false" %>
<%@ attribute name="width" required="false" %>
<%@ attribute name="height" required="false" %>

<!-- URL's -->
<c:url value="/resources/jquery/JQuery.js" var="jquery"/>
<c:url value="/resources/bootstrap/js/bootstrap.js" var="bootstrap_js"/>
<c:url value="/resources/img/loading.gif" var="loading"/>

<!-- IMPORT JQUERY+BOOTSTRAP -->
<script type="text/javascript" src="${jquery}"></script>
<script type="text/javascript" src="${bootstrap_js}"></script>

<!-- HTML -->
<div align="${align}">
<div id="${id}" class="alert alert-info fade in" style="width: ${width};height: ${height};">
  <button type="button" class="close" data-dismiss="alert">×</button>
  <strong>Aguarde</strong>
  <p>Carregando...&nbsp;<img src="${loading}"/></p>
</div>
</div>

<!-- JAVASCRIPT -->
<script type="text/javascript">
	$(document).ready(function()
	{
		$("#${id}").alert();
	});
</script>

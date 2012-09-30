<%@ tag language="java" pageEncoding="ISO-8859-1"%>

<!-- TAGLIBS -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- ATRIBUTOS -->
<%@ attribute name="id" required="false" %>
<%@ attribute name="dados" required="true" type="java.util.List" %>
<%@ attribute name="converter" required="true" type="br.com.ggdio.receitas.converter.Converter" %>
<%@ attribute name="name" required="false" %>
<%@ attribute name="selecionado" required="false" type="java.lang.String" %>

<!-- URL's -->
<c:url value="/resources/jquery/JQuery.js" var="jquery"/>
<c:url value="/resources/bootstrap/js/bootstrap.js" var="bootstrap_js"/>

<!-- IMPORT JQUERY+BOOTSTRAP -->
<script type="text/javascript" src="${jquery}"></script>
<script type="text/javascript" src="${bootstrap_js}"></script>

<!-- COMBOBOX HTML -->
<select id="${id}" class="combobox" name="${name}">
	<option selected="selected">${selecionado}</option>
	<c:forEach items="${dados}" var="dado">
		<option>${converter.convertTo(dado)}</option>
	</c:forEach>
</select>

<!-- COMBOBOX JAVASCRIPT -->
<script>
$(document).ready(function()
{
  	$('.combobox').combobox();
});
</script>
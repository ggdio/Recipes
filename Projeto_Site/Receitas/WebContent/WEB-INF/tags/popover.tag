<%@ tag language="java" pageEncoding="ISO-8859-1"%>

<!-- TAGLIB's -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- ATRIBUTOS -->
<%@ attribute name="id" required="true" %>
<%@ attribute name="value" required="true" %>
<%@ attribute name="classe" required="true" %>
<%@ attribute name="titulo" required="true" %>
<%@ attribute name="texto" required="true" %>
<%@ attribute name="href" required="false" %>
<%@ attribute name="position" required="false" description="top | bottom | right | left"%>

<!-- URL's -->
<c:url value="/resources/jquery/JQuery.js" var="jquery"/>
<c:url value="/resources/bootstrap/js/bootstrap.js" var="bootstrap_js"/>

<!-- IMPORT JQUERY+BOOTSTRAP -->
<script type="text/javascript" src="${jquery}"></script>
<script type="text/javascript" src="${bootstrap_js}"></script>

<!-- HTML -->
<c:choose>
	<c:when test="${href != null}">
		<a id="${id}" data-original-title="${titulo}" href="${href}" data-content="${texto}" class="${classe}" rel="popover">${value}</a>
	</c:when>
	<c:otherwise>
		<a id="${id}" data-original-title="${titulo}" data-content="${texto}" class="${classe}" rel="popover">${value}</a>
	</c:otherwise>
</c:choose>

<!-- JAVASCRIPT -->
<script type="text/javascript">
	$(document).ready(function()
	{
		var position = '${position}';
		if(position == "")
			position = 'right';
		$('#${id}').popover({placement:position});
	});
</script>

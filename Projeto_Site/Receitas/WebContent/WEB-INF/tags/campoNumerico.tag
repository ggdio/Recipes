<%@ tag language="java" pageEncoding="ISO-8859-1"%>

<!-- TAGLIBS -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- ATRIBUTOS -->
<%@ attribute name="id" required="true" %>
<%@ attribute name="name" required="false" %>
<%@ attribute name="classe" required="false" %>
<%@ attribute name="placeholder" required="false"%>
<%@ attribute name="maxlength" required="false"%>
<%@ attribute name="value" required="false" %>

<!-- URL's -->
<c:url value="/resources/jquery/JQuery.js" var="jquery"/>

<!-- IMPORT JQUERY -->
<script type="text/javascript" src="${jquery}"></script>

<input id="${id}" name="${name}" type="text" class="${classe}" maxlength="${maxlength}" placeholder="${placeholder}" value="${value}">

<script type="text/javascript">
	$('#${id}').bind('keypress', function(evt)
	{
		var charCode = (evt.which) ? evt.which : event.keyCode;
		   if (charCode > 31 && (charCode < 48 || charCode > 57))
		   {
		      return false;
		   }
		   return true;
	});
</script>

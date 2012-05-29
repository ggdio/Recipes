<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ attribute name="id" required="true" %>
<%@ attribute name="value" required="false" type="java.util.Calendar"%>

<!-- Formatação da Data -->
<fmt:formatDate value="${value.time}" pattern="dd/MM/yyyy" var="data"/>

<!-- Campo de Texto -->
<input type="text" id="${id}" name="${id}" value="${data}" readonly/>

<!-- Datepicker(JQUERY) -->
<script type="text/javascript">
	$("#${id}").datepicker({dateFormat: 'dd/mm/yy',changeYear: 1,changeMonth: 1});
</script>
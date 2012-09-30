<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>
<c:import url="/resources/html/header.jsp"/>
<c:url value="/upload/temp" var="urlUpload"/>


<form id="formUploadImagem" method="post" accept-charset="utf-8" enctype="multipart/form-data" action="${urlUpload}">
	<input name="arquivo" id="arquivo" size="27" type="file" /><br />
	<input type="submit" name="action" value="Upload" /><br />
	<iframe id="uploadTarget" name="uploadTarget" src="" style="width:0;height:0;border:0px solid #fff;"></iframe>
</form>

<script>
function init() 
{
	document.getElementById('formUploadImagem').onsubmit = onSubmit;
	document.getElementById("uploadTarget").onload = uploadDone;
}
function onSubmit()
{
	document.getElementById('formUploadImagem').target = 'uploadTarget'; //'upload_target' is the name of the iframe
}
function uploadDone()
{
	var ret = frames['uploadTarget'].document.getElementsByTagName("body")[0].innerHTML;
	var data = eval("("+ret+")");
	alert("Status: "+data.response.status);
	alert("Mensagem: "+data.response.mensagem);
	alert("Response: "+data.response.informacao.caminhoWeb);
	alert("Response: "+data.response.informacao.caminhoReal);
}
window.onload = init;
</script>    

<!-- FOOTER -->
<c:import url="/resources/html/footer.jsp"/>
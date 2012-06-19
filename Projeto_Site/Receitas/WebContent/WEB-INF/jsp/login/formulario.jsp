<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<!-- URL's -->
<c:url value="/resources/img/logo.png" var="logo"/>

<!-- HEADER -->
<c:import url="/resources/html/header.jsp">
	<c:param value="Receitas - Área Restrita" name="title"/>
</c:import>

<!-- IMPORT SCRIPT -->
<script type="text/javascript">
function limpar(elementos) 
{
    $(elementos).find(':input').each(function() 
    {
        switch(this.type) 
        {
            case 'password':
            case 'select-multiple':
            case 'select-one':
            case 'text':
            case 'textarea':
                $(this).val('');
                break;
            case 'checkbox':
            case 'radio':
                this.checked = false;
        }
    });
}
</script>

<form id="loginForm" class="well span6">
	<label>Usuário</label>
	<input class="span3" type="text" placeholder="Digite seu login..." /><br/>
	<label>Senha</label>
	<input class="span3" type="text" placeholder="Digite sua senha..."/><br/>
	<button class="btn btn-primary">Entrar</button>
	<button class="btn" onclick="limpar(#loginForm)">Limpar</button>
</form>

<!-- FOOTER -->
<c:import url="/resources/html/footer.jsp"/>
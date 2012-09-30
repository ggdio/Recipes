<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ attribute name="classe" required="false"%>
<%@ attribute name="inputSpans" required="false"%>

<c:url value="/login/entrar" var="login"/>

<div class="${classe}">
    <form id="loginForm" method="POST" action="${login}">
		<label>Login</label>
		<input class="${inputSpans}" type="text" placeholder="Digite seu login..." name="login" />
		<label>Senha</label>
		<input class="${inputSpans}" type="password" placeholder="Digite sua senha..." name="senha"/><br/>
		<button class="btn btn-primary">Entrar</button>
		<input type="reset" class="btn" value="Limpar"/>
	</form>
</div>

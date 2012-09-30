<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<!-- URL's -->
<c:url value="/receitas/adiciona" var="urlAdiciona"/>
<c:url value="/receitas/lista" var="urlReceitas"/>
<c:url value="/receitas/altera/${receita.titulo.replace(' ','-')}/${receita.codigo}" var="urlAltera"/>
<c:url value="/resources/img/logo.png" var="logo"/>

<!-- HEADER -->
<c:import url="/resources/html/header.jsp">
	<c:param value="${receita.titulo}" name="title"/>
	<c:param value="${receita.descricao}" name="metaDescricao"/>
	<c:param value="${receita.getTags(',')}" name="metaKeywords"/>
</c:import>

<!-- TÍTULO -->
<center>
	<span style="font-size: xx-large;" class="label label-warning">${receita.titulo}</span>
</center>
<br/>

<div class="well-green">

<!-- AUTOR -->
<span style="font-size: medium;" class="label label-success">Autor:</span>&nbsp;<b>${receita.autor.nome}</b>

&nbsp;&nbsp;

<!-- DATA -->
<span style="font-size: medium;" class="label label-success">Data:</span>&nbsp;<b><fmt:formatDate value="${receita.data.time}" pattern="dd/MM/yyyy kk:mm:ss" /></b>

<a class="btn btn-danger pull-right" href="#remover"><b>Remover</b></a>
<a class="btn btn-warning pull-right" href="${urlAltera}"><b>Editar</b></a>

<br/><br/>

<!-- CATEGORIA -->
<span style="font-size: medium;" class="label label-success">Categoria:</span>&nbsp;<b>${receita.categoria.tipo}</b>


<br/>
<br/>

<!-- IMAGEM -->
<span style="font-size: medium;" class="label label-info">Imagem</span>
<p/>
<ul class="thumbnails">
    <li class="span4">
	    <div class="well thumbnail pull-left">
	    	<img width="260" height="180" src="${receita.imagem != null ? receita.imagem : 'http://placehold.it/260x180'}" alt="" />
	    </div>
    </li>
</ul>

<!-- DESCRIÇÃO -->
<span style="font-size: medium;" class="label label-info">Descrição</span>
<p/>
<p>${receita.descricao}</p>

<br/>

<!-- INGREDIENTES -->
<span style="font-size: medium;" class="label label-info">Ingredientes Necessários</span>
<p/>
<c:forEach items="${receita.ingredientes}" var="ingrediente">
	<p><span class="badge badge-warning"><i class="icon-th-large icon-white"></i></span>&nbsp;<b>${ingrediente}</b></p>
</c:forEach>
<c:if test="${receita.ingredientes.size() == 0}">
	<p><span class="badge badge-warning"><i class="icon-th-large icon-white"></i></span>&nbsp;<b>N/A</b></p>
</c:if>

<br/>

<!-- PREPARO -->
<span style="font-size: medium;" class="label label-info">Preparo</span>
<p/>
<p>${receita.preparo}</p><br/>

<br/>

</div>


<br/><br/>
<!-- FOOTER -->
<c:import url="/resources/html/footer.jsp"/>
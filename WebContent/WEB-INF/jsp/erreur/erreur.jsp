<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@include file="../header.jsp" %>

<section class="content-header">
	<h1>Gestion des erreurs</h1>
</section>

<section class="content">
	
	<div class="erreur">
		<h2>Erreur</h2>
		<jsp:useBean id="erreur" class="java.lang.Exception" type="java.lang.Exception" scope="request" />
		<p>Une erreur s'est produite : <jsp:getProperty name="erreur" property="message" /></p>
	</div>
</section>
<%@include file="../footer.jsp" %>
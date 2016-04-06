<%@page import="entidades.Departamento"%>
<%@page import="entidades.Curso"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ver curso</title>
</head>
<%@include file="messagePage.jsp" %>
<% Curso curso = (Curso)request.getAttribute("curso antigo");%>
<body>
<form action="VerCurso" method="post">
	<% try{ 
		String nome = (String) curso.getMapeadorAtributos().get("nome");
		String sigla = (String) curso.getMapeadorAtributos().get("sigla"); 
		String siglaDepartamento = (String) ((Departamento) curso.getMapeadorAtributos().get("departamento")).getMapeadorAtributos().get("sigla");
	%>
		Nome : <%=nome%>
		Sigla : <%=sigla%> <input type="hidden" name ="sigla" value = "<%=sigla%>">
		Departamento : <%=siglaDepartamento%> 
		<br>
	<% } catch (NullPointerException e)  {  } %>
	<input type="submit" name="acaoVer" value="Voltar">
</form>
</body>
</html>
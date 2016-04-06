<%@page import="entidades.Departamento"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ver departamento</title>
</head>
<%@include file="messagePage.jsp" %>
<% Departamento departamento = (Departamento) request.getAttribute("departamento antigo");%>
<body>
	<form action="VerDepartamento" method="post">
<% try{ 
	String nome = (String) departamento.getMapeadorAtributos().get("nome");
	String sigla = (String) departamento.getMapeadorAtributos().get("sigla"); %>
	Nome : <%=nome%>
	Sigla : <%=sigla%> <input type="hidden" name ="sigla" value = "<%=sigla%>">
	<br>
<% } catch (NullPointerException e)  {  } %>
<input type="submit" name="acaoVer" value="Voltar">
	</form>
</body>
</html>
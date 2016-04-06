<%@page import="entidades.Departamento"%>
<%@ page import="java.util.Collection" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Criar curso</title>
</head>
<%@include file="messagePage.jsp" %>
<% Collection<Departamento> departamentosDisponiveis = (Collection<Departamento>)request.getAttribute("departamentosDisponiveis"); %>

<body>
	<form action="CriarCurso" method="post">
	Sigla : <input type="text" name ="sigla" value = "">
	Nome : <input type="text" name ="nome" value = "">	
	Departamento : <select name ="departamento">
	<option value=""></option>
	<% for(Departamento dpto : departamentosDisponiveis){ %>
		<option value="<%=dpto.getMapeadorAtributos().get("sigla")%>"><%=dpto.getMapeadorAtributos().get("nome")%></option>
	<% } %>
	</select>
	<br>
	<input type="submit" name="acaoCriar" value="Criar">
	<input type="submit" name="acaoCriar" value="Cancelar">
	</form>
</body>

</html>
<%@page import="entidades.Curso"%>
<%@page import="entidades.Departamento"%>
<%@ page import="java.util.Collection" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Atualizar curso</title>
</head>
<%@include file="messagePage.jsp" %>
<% Curso curso = (Curso) request.getAttribute("curso antigo");%>
<% Collection<Departamento> departamentosDisponiveis = (Collection<Departamento>)request.getAttribute("departamentosDisponiveis"); %>

<body>
	<form action="AtualizarCurso" method="post">
<% try{ 
	String nome = (String) curso.getMapeadorAtributos().get("nome");
	String sigla = (String) curso.getMapeadorAtributos().get("sigla"); %>
	Nome : <input type="text" name ="nome" value = "<%=nome%>">
	Sigla : <%=sigla%> <input type="hidden" name="sigla" value="<%=sigla%>">
	Departamento : <select name ="departamento">
	<% for(Departamento dpto : departamentosDisponiveis){ %>
		<option value="<%=dpto.getMapeadorAtributos().get("sigla")%>">
		<%=dpto.getMapeadorAtributos().get("nome")%></option>
	<% } %>
	</select>	
	<br>
	<input type="submit" name="acaoAtualizar" value="Atualizar">
	<input type="submit" name="acaoAtualizar" value="Cancelar">
<% } catch (NullPointerException e)  { %>
	<input type="submit" name="acaoAtualizar" value="Voltar">
<% } %>
	</form>
</body>
</html>
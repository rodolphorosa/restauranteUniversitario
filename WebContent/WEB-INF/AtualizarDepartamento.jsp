<%@page import="entidades.Departamento"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Atualizar departamento</title>
</head>
<%@include file="messagePage.jsp" %>
<% Departamento departamento = (Departamento) request.getAttribute("departamento antigo");%>
<body>
	<form action="AtualizarDepartamento" method="post">
<% try{ 
	String nome = (String) departamento.getMapeadorAtributos().get("nome");
	String sigla = (String) departamento.getMapeadorAtributos().get("sigla");
	Long id = (Long) departamento.getMapeadorAtributos().get("id");
%>
	Sigla: <input type="text" name ="sigla" value = "<%=sigla%>" style="display:none;">
	Nome: <input type="text" name ="nome" value = "<%=nome%>">
	<br>
	<input type="submit" name="acaoAtualizar" value="Atualizar">
	<input type="submit" name="acaoAtualizar" value="Cancelar">
<% } catch (NullPointerException e)  { %>
	<input type="submit" name="acaoAtualizar" value="Voltar">
<% } %>
	</form>


</body>

</html>
<%@page import="entidades.Departamento"%>
<%@page import="entidades.Curso"%>
<%@page import="entidades.Funcionario"%>
<%@page import="entidades.Aluno"%>
<%@page import="entidades.Sexo"%>
<%@page import="entidades.Consumidor"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Visualização de Consumidor</title>
</head>
<%@include file="messagePage.jsp" %>
<%Consumidor consumidor = (Consumidor)request.getAttribute("consumidor"); %>
<body>
<div>
<form action="VerConsumidor" method="post">
	<%try { 
		String cpf = (String) consumidor.getMapeadorAtributos().get("cpf");
		String nome = (String) consumidor.getMapeadorAtributos().get("nome");
		String matricula = (String) consumidor.getMapeadorAtributos().get("matricula");
		Sexo sexo = (Sexo) consumidor.getMapeadorAtributos().get("sexo");
		int ano = (Integer) consumidor.getMapeadorAtributos().get("anoIngresso");
		String codigo = "";
		
		if (consumidor instanceof Aluno) {
			codigo = (String) ((Curso) consumidor.getMapeadorAtributos().get("curso")).getMapeadorAtributos().get("sigla");
		} else if (consumidor instanceof Funcionario) {
			codigo = (String) ((Departamento) consumidor.getMapeadorAtributos().get("departamento")).getMapeadorAtributos().get("sigla");
		}
	%>		
	<span id="nome" style="display: block; margin: 1%;">
		Nome: <%=nome %>
	</span>
	
	<span style="display: block; margin: 1%;">
		CPF: <%=cpf %>
	</span>
	
	<span style="display: block; margin: 1%;">
		Matrícula: <%=matricula %>
	</span>
	
	<span style="display: block; margin: 1%;">
		Sexo: <%=sexo.name() %>
	</span>
	
	<span style="display: block; margin: 1%;">
		Ano: <%=ano %>
	</span>
	
	<span style="display: block; margin: 1%;">
		Curso/Departamento: <%=codigo %>
	</span>
	<% } catch (NullPointerException e) { e.printStackTrace(); }%>
<input type="submit" value="Voltar" name="acaoVerConsumidor">
</form>
</div>
</body>
</html>
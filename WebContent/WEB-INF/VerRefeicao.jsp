<%@page import="entidades.Turno"%>
<%@page import="entidades.Refeicao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Visualização de Refeição</title>
</head>
<%@include file="messagePage.jsp" %>
<%Refeicao refeicao = (Refeicao)request.getAttribute("refeicao"); %>
<body>
<div>
<form action="VerRefeicao" method="post">
	<%try {
		Turno turno = (Turno) refeicao.getMapeadorAtributos().get("turno");
		String descricao = (String) refeicao.getMapeadorAtributos().get("descricao");
		String opcaoVegetariana = (String) refeicao.getMapeadorAtributos().get("opcaoVegetariana");
		String data = (String) refeicao.getMapeadorAtributos().get("data");
	%>		
	<span id="nome" style="display: block; margin: 1%;">
		Turno: <%=turno.name() %>
	</span>
	
	<span style="display: block; margin: 1%;">
		Descrição: <%=descricao %>
	</span>
	
	<span style="display: block; margin: 1%;">
		Opção Vegetariana: <%=opcaoVegetariana %>
	</span>
	
	<span style="display: block; margin: 1%;">
		Data: <%=data %>
	</span>
	<% } catch (NullPointerException e) { e.printStackTrace(); }%>
<input type="submit" value="Voltar" name="acaoVerRefeicao">
</form>
</div>
</body>
</html>
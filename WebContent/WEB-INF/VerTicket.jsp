<%@page import="entidades.Turno"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="entidades.Refeicao"%>
<%@page import="entidades.Consumidor"%>
<%@page import="entidades.Ticket"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Visualização de Ticket</title>
</head>
<%@include file="messagePage.jsp" %>
<body>
<%Ticket ticket = (Ticket)request.getAttribute("ticket"); %>
<form action="VerTicket" method="post">
	<%try {
		String matricula = (String) ((Consumidor) ticket.getMapeadorAtributos().get("consumidor")).getMapeadorAtributos().get("matricula");
		BigDecimal preco = (BigDecimal) ticket.getMapeadorAtributos().get("preco");
		Boolean estado = (Boolean) ticket.getMapeadorAtributos().get("vendido");
		Turno turno = (Turno) ((Refeicao) ticket.getMapeadorAtributos().get("refeicao")).getMapeadorAtributos().get("turno");
		String descricao = (String) ((Refeicao) ticket.getMapeadorAtributos().get("refeicao")).getMapeadorAtributos().get("descricao");
	%>		
	<span style="display: block; margin: 1%;">
		Matrícula Consumidor: <%=matricula %>
	</span>
	
	<span style="display: block; margin: 1%;">
		Preço: <%=preco %>
	</span>
	
	<span style="display: block; margin: 1%;">
		Descrição: <%=descricao %>
	</span>
	
	<span style="display: block; margin: 1%;">
		Turno: <%=turno.name() %>
	</span>
	<% } catch (NullPointerException e) { e.printStackTrace(); }%>
<input type="submit" value="Voltar" name="acaoVerTicket">
</form>
</body>
</html>
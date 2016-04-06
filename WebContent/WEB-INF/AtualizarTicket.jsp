<%@page import="entidades.Refeicao"%>
<%@page import="entidades.Turno"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="entidades.Consumidor"%>
<%@page import="entidades.Ticket"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Atualização de Ticket</title>
</head>
<body>
<%@ include file="messagePage.jsp" %>
<%Ticket ticket = (Ticket) request.getAttribute("ticket antigo"); %>
	<form action="AtualizarTicket" method="post">
	<% try {
		String matricula = (String) ((Consumidor) ticket.getMapeadorAtributos().get("consumidor")).getMapeadorAtributos().get("matricula");
		BigDecimal preco = (BigDecimal) ticket.getMapeadorAtributos().get("preco");
		Boolean pago = (Boolean) ticket.getMapeadorAtributos().get("vendido");
		Turno turno = (Turno) ((Refeicao) ticket.getMapeadorAtributos().get("refeicao")).getMapeadorAtributos().get("turno");
		String descricao = (String) ((Refeicao) ticket.getMapeadorAtributos().get("refeicao")).getMapeadorAtributos().get("descricao");
		Long id = (Long) ticket.getMapeadorAtributos().get("id");
	%>
		<input type="hidden" value="<%=id.toString() %>" name="id"/>
		
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
		<span style="display: block; margin: 1%;">
			<label for="estado" class="label" value="Estado do Pagamento"></label>
			<% if(pago) {%>
			<input type="radio" name="estado" value="Pago" checked> Pago
			<input type="radio" name="estado" value="Não pago"> Não pago
			<% } else { %>
			<input type="radio" name="estado" value="Pago"> Pago
			<input type="radio" name="estado" value="Não pago" checked> Não pago
			<% } %>			
		</span>
		<span style="display: block; margin: 1%;">
			<input type="submit" value="Atualizar" name="acaoAtualizarTicket"/>
			<input type="submit" value="Cancelar" name="acaoAtualizarTicket"/>
		</span>
		
		<% } catch (NullPointerException e) { %>
		<span style="display: block; margin: 1%;">
			<input type="submit" value="Voltar" name="acaoAtualizarTicket"/>
		</span>
		<% } %>
	</form>
</body>
</html>
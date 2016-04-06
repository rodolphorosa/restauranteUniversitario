<%@page import="entidades.Refeicao"%>
<%@page import="entidades.Consumidor"%>
<%@page import="entidades.Ticket"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listagem de tickets</title>
<style>
	
	table {	width: 100%; }
	
	.tableHeader {
		background-color: #708090;
		color: white;
		font-size: 0.9em;
		font-family: sans-serif;
	}
	
	.tableHeader th { padding: 0 1%; }
	
	.tableBody {
		text-align: center;
		font-size: 0.85em;
		font-family: sans-serif;
		padding: 0 1%;
	}
</style>
</head>
<% Collection<Ticket> tickets = (Collection<Ticket>) request.getAttribute("tickets"); %>
<body>
<div>
	<div style="display: block; margin: 1% 0%">Listagem de Tickets</div>
	<div class="content">
	<form action="ListarTickets" method="post">
		<table>
			<thead class="tableHeader">
				<tr>
					<th></th>
					<th>Matrícula</th>
					<th>Preço</th>
					<th>Situação do pagamento</th>
					<th>Turno</th>
					<th>Refeição</th>
				</tr>
			</thead>
			<tbody class="tableBody">
			<%
				for(Ticket t : tickets) {
			%>
				<tr>
					<td><input type="radio" name="id" value="<%=t.getMapeadorAtributos().get("id")%>"></td>
					<td><%=((Consumidor) t.getMapeadorAtributos().get("consumidor")).getMapeadorAtributos().get("matricula")%></td>
					<td><%=t.getMapeadorAtributos().get("preco")%></td>
					<td><%=((Boolean)t.getMapeadorAtributos().get("vendido"))? "Pago" : "Não pago"%></td>
					<td><%=((Refeicao) t.getMapeadorAtributos().get("refeicao")).getMapeadorAtributos().get("turno") %></td>
					<td><%=((Refeicao) t.getMapeadorAtributos().get("refeicao")).getMapeadorAtributos().get("descricao") %></td>
				</tr>
			<% 	} %>
			</tbody>		
		</table>
		
		<span style="display: block; margin: 1% 0%;">
		<input type="submit" value="Vender" name="acaoTicket">
		<input type="submit" value="Ver" name="acaoTicket">
		<input type="submit" value="Atualizar" name="acaoTicket">
		</span>
	</form>
	</div>
</div>
</body>
</html>
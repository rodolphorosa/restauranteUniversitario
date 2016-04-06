<%@page import="entidades.Refeicao"%>
<%@page import="java.util.Collection"%>
<%@page import="entidades.Ticket"%>
<%@page import="entidades.Funcionario"%>
<%@page import="entidades.Compra"%>
<%@page import="entidades.Aluno"%>
<%@page import="entidades.TipoConsumidor"%>
<%@page import="entidades.Consumidor"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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

<title>Finalizando Compra</title>
</head>
<%
Consumidor consumidor = (Consumidor) request.getAttribute("consumidorCompra");
Compra compra = (Compra) request.getSession().getAttribute("compra");
%>
<body>
<%@ include file="messagePage.jsp" %>
<form action="FinalizarCompra" method="post">
<%
try {
	String tipoConsumidor = null;
	String nome = (String) consumidor.getMapeadorAtributos().get("nome");
	String cpf = (String) consumidor.getMapeadorAtributos().get("cpf");
	
	if(consumidor instanceof Aluno) {
		tipoConsumidor = TipoConsumidor.ALUNO.name();
	} else if(consumidor instanceof Funcionario) {
		tipoConsumidor = TipoConsumidor.FUNCIONARIO.name();
	}	
%>
		<span style="display:block; margin: 1%;">
			CPF: <input type="hidden" name="cpf" value="<%=cpf%>"> <%=cpf%>
		</span>
		
		<span style="display: block; margin: 1%;">
			Consumidor: <%=nome%>
		</span>
		
		<span style="display: block; margin: 1%;">
			Cargo: <%=tipoConsumidor%>
		</span>
		
		<span style="display: block; margin: 1%;">
			Total da compra: <%=compra.calcularTotal()%>
		</span>
		
		<span style="display: block; margin: 1%;">
			Descrição da compra:
		</span>
		
		<table>
			<thead class="tableHeader">
				<tr>
					<th>Tipo de Ticket</th>
					<th>Turno</th>
					<th>Descrição</th>
					<th>Opção vegetariana</th>
					<th>Data</th>
					<th>Preço</th>
				</tr>
			</thead>
			<tbody class="tableBody">
			<%
				Collection<Ticket> carrinho = compra.getCarrinho();
			
				for(Ticket t : carrinho) {
			%>
				<tr>
					<td><%=t.getMapeadorAtributos().get("tipoConsumidor") %></td>
					<td><%=((Refeicao) t.getMapeadorAtributos().get("refeicao")).getMapeadorAtributos().get("turno") %></td>
					<td><%=((Refeicao) t.getMapeadorAtributos().get("refeicao")).getMapeadorAtributos().get("descricao") %></td>
					<td><%=((Refeicao) t.getMapeadorAtributos().get("refeicao")).getMapeadorAtributos().get("opcaoVegetariana") %></td>
					<td><%=((Refeicao) t.getMapeadorAtributos().get("refeicao")).getMapeadorAtributos().get("data") %></td>
					<td><%=t.getMapeadorAtributos().get("preco") %></td>
				</tr>
			<% 	} %>
			</tbody>
		</table>		
		<span style="display: block; margin: 1%;">
			<input type="submit" value="Confirmar" name="acaoFinalizar"/>
			<input type="submit" value="Cancelar" name="acaoFinalizar"/>
		</span>
		<% } catch (NullPointerException e) { %>
		<span style="display: block; margin: 1%;">
			<input type="submit" value="Voltar" name="acaoFinalizar"/>
		</span>
		<% } %>
</form>
</body>
</html>
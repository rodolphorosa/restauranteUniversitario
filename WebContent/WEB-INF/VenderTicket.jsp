<%@page import="entidades.TipoConsumidor"%>
<%@page import="entidades.Consumidor"%>
<%@ page import="java.util.Collection" %>
<%@page import="mapeadores.RefeicaoMapper"%>
<%@page import="entidades.Refeicao"%>
<%@page import="entidades.Ticket"%>
<%@page import="entidades.Compra"%>
<%@page import="mapeadores.TicketMapper"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Venda de Tickets</title>
<style>
	.left{
		display: inline-block;
		float: left;
		width: 50%;
	}
	
	.right {
		display:inline-block;
		float: right;
		width: 50%;
	}

</style>
</head>
<%
Compra compra = (Compra) request.getSession().getAttribute("compra");
Collection<Refeicao> refeicoes = (Collection<Refeicao>) request.getAttribute("refeicoes");
%>
<body>
<%@ include file="messagePage.jsp" %>
	<div>
		<div>Venda de Tickets</div>
		<div class="left">
			<form action="VenderTicket" method="post">
				<span style="display: block; margin: 1% 0%;">
				<label for="refeicoes" class="label">Refeições:</label>
				<%
					for(Refeicao r : refeicoes) {
				%>
					<span style="display: block;">
						<input type="radio" name="refeicao" value="<%=r.getMapeadorAtributos().get("id")%>"/>
						[<%=r.getMapeadorAtributos().get("turno") %>]:
						<%=r.getMapeadorAtributos().get("descricao")%>,
						<%=r.getMapeadorAtributos().get("opcaoVegetariana")%>
					</span>
				<%
					}
				%>
				</span>
				
				<span style="display: block; margin: 1% 0%;">
					<label for="quantidade">Quantidade:</label>
					<input type="number" name="quantidade" min="0" max="30" value="1"/>
				</span>
				
				<span style="display: block; margin: 1% 0%;">
					<input type="submit" value="Comprar" name="acaoVenderTicket"/>					
					<input type="submit" value="Cancelar" name="acaoVenderTicket">
				</span>
				
			</form>			
			<%@include file="SolicitarFinalizacaoCompra.jsp" %>
		</div>
		<div class="right">
			<span>Tickets comprados:</span>
			<%
				for(Ticket t : compra.getCarrinho()) {
			%>
				<span style="display: block; margin: 1%;">
				[<%=((Refeicao) t.getMapeadorAtributos().get("refeicao")).getMapeadorAtributos().get("turno") %>]:
				<%=((Refeicao) t.getMapeadorAtributos().get("refeicao")).getMapeadorAtributos().get("descricao") %>, 
				<%=((Refeicao) t.getMapeadorAtributos().get("refeicao")).getMapeadorAtributos().get("opcaoVegetariana") %>
				</span>
			<% 	} %>
		</div>
	</div>
</body>
</html>
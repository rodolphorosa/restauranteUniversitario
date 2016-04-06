<%@ page import="java.util.Collection" %>
<%@ page import="entidades.Refeicao" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listagem de Refeições</title>
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
<body>
	<div>
		<div>Listagem de Refeições</div>
		<div>
			<form action="ListarRefeicoes" method="post">
			<table>
				<thead class="tableHeader">
					<tr>
						<th></th>
						<th>Turno</th>
						<th>Descrição</th>
						<th>Opção Vegetariana</th>
						<th>Data</th>
					</tr>
				</thead>
				<tbody class="tableBody">
				<%
					Collection<Refeicao> refeicoes = (Collection<Refeicao>) request.getAttribute("refeicoes");
					for(Refeicao refeicao : refeicoes) {
				%>
					<tr>
						<td><input type="radio" name="id" value="<%=refeicao.getMapeadorAtributos().get("id")%>"></td>
						<td><%=refeicao.getMapeadorAtributos().get("turno") %></td>
						<td><%=refeicao.getMapeadorAtributos().get("descricao") %></td>
						<td><%=refeicao.getMapeadorAtributos().get("opcaoVegetariana") %></td>
						<td><%=refeicao.getMapeadorAtributos().get("data") %></td>
					</tr>
				<%  } %>
				</tbody>
			</table>			
			<input type="submit" value="Cadastrar" name="acaoRefeicao"/>
			<input type="submit" value="Atualizar" name="acaoRefeicao"/>
			<input type="submit" value="Ver" name="acaoRefeicao"/>
			</form>
		</div>
	</div>
</body>
</html>
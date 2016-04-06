<%@ page import="java.util.Collection" %>
<%@ page import="entidades.Consumidor" %>
<%@ page import="entidades.Curso" %>
<%@ page import="entidades.Departamento" %>
<%@ page import="entidades.Aluno" %>
<%@ page import="entidades.Funcionario" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista de Consumidores</title>
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
		<div><span>Lista de Consumidores</span></div>
		<div>
		<form action="ListarConsumidores" method="post">
			<table>
				<thead class="tableHeader">
					<tr>
						<th>CPF</th>
						<th>Nome</th>
						<th>Matrícula</th>
						<th>Ano de Ingresso</th>
						<th>Sexo</th>
						<th>Curso/Departamento</th>						
					</tr>
				</thead>
				<tbody class="tableBody">
				<%
				try {
					Collection<Consumidor> consumidores = (Collection<Consumidor>) request.getAttribute("consumidores");
					for(Consumidor consumidor: consumidores) {
				%>							
					<tr>
						<td>
						<input type="radio" name='cpf' value="<%=consumidor.getMapeadorAtributos().get("cpf")%>">
						<%=consumidor.getMapeadorAtributos().get("cpf") %>
						</td>
						<td><%=consumidor.getMapeadorAtributos().get("nome") %></td>
						<td><%=consumidor.getMapeadorAtributos().get("matricula") %></td>
						<td><%=consumidor.getMapeadorAtributos().get("anoIngresso") %></td>
						<td><%=consumidor.getMapeadorAtributos().get("sexo") %></td>
						
						<% if(consumidor instanceof Aluno) { %>						
						<td><%=((Curso)consumidor.getMapeadorAtributos().get("curso")).getMapeadorAtributos().get("sigla")%></td>
						<% } else if(consumidor instanceof Funcionario) { %>
						<td><%=((Departamento)consumidor.getMapeadorAtributos().get("departamento")).getMapeadorAtributos().get("sigla")%></td>
						<% } %>
					</tr>
				<% }
				} catch (NullPointerException e) {%>
				
				<% } %>	
				</tbody>
			</table>
			<input type="submit" value="Atualizar" name="acaoConsumidor"/>
			<input type="submit" value="Cadastrar" name="acaoConsumidor"/>
			<input type="submit" value="Ver" name="acaoConsumidor">
		</form>
		</div>
	</div>
</body>
</html>
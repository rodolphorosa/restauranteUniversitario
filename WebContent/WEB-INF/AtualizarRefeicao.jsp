<%@page import="entidades.Turno"%>
<%@page import="entidades.Refeicao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Atualizando Refeição</title>
</head>
<body>
<%@ include file="messagePage.jsp" %>
<%Refeicao refeicao = (Refeicao) request.getAttribute("refeicao antiga"); %>
	<form action="AtualizarRefeicao" method="post">
	<% try {
		Turno turno = (Turno) refeicao.getMapeadorAtributos().get("turno");
		String descricao = (String) refeicao.getMapeadorAtributos().get("descricao");
		String opcaoVegetariana = (String) refeicao.getMapeadorAtributos().get("opcaoVegetariana");
		String data = (String) refeicao.getMapeadorAtributos().get("data");
		Long id = (Long) refeicao.getMapeadorAtributos().get("id");
	%>
		<span style="display: block; margin: 1%;">
			Turno: <%=turno.name() %>
			<input type="hidden" name="id" value=<%=id %>>
		</span>
		
		<span style="display: block; margin: 1%;">
			<label for="descricao" class="label">Descrição:</label>
			<textarea id="descricao" rows="5" cols="50" name="descricao" maxlength="98" wrap="hard" style="display: block; margin: 1% 0%;"><%=descricao.replaceAll("\t", "").trim() %></textarea>
		</span>
		
		<span style="display: block; margin: 1%;">
			<label for="opcaoVegetariana" class="label">Opção Vegetariana:</label>
			<textarea rows="5" cols="50" name="opcaoVegetariana" maxlength="98" wrap="hard" style="display: block; margin: 1% 0%;"><%=opcaoVegetariana.replaceAll("\t", "").trim() %></textarea>
		</span>
		
		<span style="display: block; margin: 1%;">
			Data: <%=data %>
		</span>
		<span style="display: block; margin: 1%;">			
			<input type="submit" value="Atualizar" name="acaoAtualizar"/>
			<input type="submit" value="Cancelar" name="acaoAtualizar"/>
		</span>
		<% } catch (NullPointerException e) { %>
		<span style="display: block; margin: 1%;">
			<input type="submit" value="Voltar" name="acaoAtualizar"/>
		</span>
		<% } %>
	</form>
</body>
</html>
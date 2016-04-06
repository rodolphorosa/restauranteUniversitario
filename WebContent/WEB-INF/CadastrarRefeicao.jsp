<%@ page import="entidades.TipoRefeicao"%>
<%@ page import="entidades.Turno" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Refeição</title>
</head>
<body>
	<%@ include file="messagePage.jsp" %>
	<div>
		<div class="header">Cadastro de Refeição</div>
		<div class="content">
			<form action="CadastrarRefeicao" method="post">
				<span style="display: block; margin: 1%;">
					<label for="turno" class="label">Turno:</label>
					<%for(Turno turno: Turno.values()) {%>
					<input type="radio" value="<%=turno.name() %>" name="turno"/> <%=turno.name() %>
					<%}%>				
				</span>
				
				<span style="display: block; margin: 1%;">
					<label for="descricao" class="label">Descrição:</label>
					<textarea id="descricao" rows="5" cols="50" name="descricao" maxlength="98" wrap="hard" style="display: block; margin: 1% 0%;"></textarea>
				</span>
				
				<span style="display: block; margin: 1%;">
					<label for="opcaoVegetariana" class="label">Opção Vegetariana:</label>
					<textarea rows="5" cols="50" name="opcaoVegetariana" maxlength="98" wrap="hard" style="display: block; margin: 1% 0%;"></textarea>
				</span>
				
				<span style="display: block; margin: 1%;">
					<label for="dia" class="label">Data:</label>
					<input type="number" name="dia" maxlength=2 min=1 max=31 style="width: 5%; margin: 1% 0%;"/> -
					<input type="number" name="mes" maxlength=2 min=1 max=12 style="width: 5%; margin: 1% 0%;"/> -
					<input type="number" name="ano" maxlength=4 style="width: 5%; margin: 1% 0%;"/>
				</span>				
				<input type="submit" value="Cadastrar" name="acaoCadastroRefeicao"/>
				<input type="submit" value="Cancelar" name="acaoCadastroRefeicao"/>
			</form>		
		</div>
	</div>
</body>
</html>
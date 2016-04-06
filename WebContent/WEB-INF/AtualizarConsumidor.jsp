<%@ page import="entidades.Consumidor" %>
<%@ page import="entidades.Sexo" %>
<%@ page import="java.util.Calendar" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Atualizar Dados do Consumidor</title>
</head>
<%Consumidor consumidor = (Consumidor) request.getAttribute("consumidor antigo"); %>
<body>
<%@ include file="messagePage.jsp" %>
	<div>
		<div class="content">
		<form action="AtualizarConsumidor" method="post">
			<% try {
				String nome = (String) consumidor.getMapeadorAtributos().get("nome");
				String matricula = (String) consumidor.getMapeadorAtributos().get("matricula");
				Sexo sexo = (Sexo) consumidor.getMapeadorAtributos().get("sexo");
				int ano = (Integer) consumidor.getMapeadorAtributos().get("anoIngresso");
				String cpf = (String) consumidor.getMapeadorAtributos().get("cpf");
			%>
				<span id="cpf" style="display: block; margin: 1%;">
					<label for="cpf" class="label">CPF:</label>
					<input type="hidden" value="<%=cpf.trim()%>" name="cpf"/> <%=cpf %>
				</span>
			
				<span id="nome" style="display: block; margin: 1%;">
					<label for="nome" class="label">Nome:</label>
					<input type="text" value="<%=nome.trim()%>" name="nome" maxlength="50"/>
				</span>
				
				<span style="display: block; margin: 1%;">
					<label for="matricula" class="label">Matrícula:</label>
					<input type="text" value="<%=matricula.trim()%>" name="matricula" maxlength="11"/>
				</span>
				
				<span style="display: block; margin: 1%;">
					<label for="sexo" class="label">Sexo:</label>
					
					<% if(sexo.equals(Sexo.FEMININO)) { %>
										
					<input type="radio" value="<%=Sexo.MASCULINO.name()%>" name="sexo"/> <%=Sexo.MASCULINO.name() %>
					<input type="radio" value="<%=Sexo.FEMININO.name()%>" name="sexo" checked/> <%=Sexo.FEMININO.name() %>
					
					<% } else if(sexo.equals(Sexo.MASCULINO)) { %>
					
					<input type="radio" value="<%=Sexo.MASCULINO.name()%>" name="sexo" checked/> <%=Sexo.MASCULINO.name() %>
					<input type="radio" value="<%=Sexo.FEMININO.name()%>" name="sexo"/> <%=Sexo.FEMININO.name() %>
					
					<% } %>
				</span>
				
				<span style="display: block; margin: 1%;">
					<label for="anoIngresso" class="label">Ano de Ingresso:</label>
					<input type="number" value="<%=ano%>" name="anoIngresso"/>
				</span>
				
				<span>
					<input type="submit" value="Atualizar" name="acaoAtualizar"/>
					<input type="submit" value="Cancelar" name="acaoAtualizar"/>
				</span>
				
				<% } catch (NullPointerException e) { %>
				<span>
					<input type="submit" value="Voltar" name="acaoAtualizar"/>
				</span>
				<% } %>
			</form>
		</div>
	</div>
</body>
</html>
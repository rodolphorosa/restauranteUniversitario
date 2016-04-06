<%@ page import="entidades.Sexo" %>
<%@ page import="entidades.Titulo" %>
<%@ page import="entidades.TipoConsumidor" %>
<%@ page import="entidades.Curso" %>
<%@ page import="entidades.Departamento" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Collection" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Cadastro Consumidor</title>	
	<style>
		.label {
			font-weight: bold;
		}
	</style>
</head>
<body>
	<%@ include file="messagePage.jsp" %>
	<div>
		<div class="header">Cadastro Consumidor</div>
		<div class="content">
			<form action="CadastrarConsumidor" method="post">
				<span id="nome" style="display: block; margin: 1%;">
					<label for="nome" class="label">Nome:</label>
					<input type="text" value="" name="nome" maxlength="50"/>
				</span>
				
				<span style="display: block; margin: 1%;">
					<label for="cpf" class="label">CPF:</label>
					<input type="text" value="" name="cpf" maxlength="11"/>
				</span>
				
				<span style="display: block; margin: 1%;">
					<label for="matricula" class="label">Matrícula:</label>
					<input type="text" value="" name="matricula" maxlength="11"/>
				</span>
				
				<span style="display: block; margin: 1%;">
					<label for="sexo" class="label">Sexo:</label>
					
					<% 
						Sexo[] sexos = Sexo.values();
						for(Sexo s : sexos) {
					%>
					<input type="radio" value="<%=s.name()%>" name="sexo"/> <%=s.name() %>
					<% } %>
				</span>
				
				<span style="display: block; margin: 1%;">
					<label for="anoIngresso" class="label">Ano de Ingresso:</label>
					<input type="number" maxlength="4" value="2015" name="anoIngresso"/>
				</span>
				
				<span style="display: block; margin: 1%;">
					<label for="titulo" class="label">Título:</label>
					<%
						Titulo[] titulos = Titulo.values();
						for(Titulo t : titulos) {
					%>
					<input type="radio" value="<%=t.name() %>" name="titulo"/> <%=t.name() %>
					<% } %>
				</span>
				
				<span style="display: block; margin: 1%;">
					<label for="tipo" class="label">Ocupação:</label>
					<%
						TipoConsumidor[] tipos = TipoConsumidor.values();
						for(TipoConsumidor tc : tipos) {
					%>
					<input type="radio" value=<%=tc.name()%> name="tipo" onchange="onChange()"/> <%=tc.name()%>
					<% } %>
				</span>				
				
				<span style="display: block; margin: 1%;">
					<label for="listaCursos" class="label">Curso:</label>
					<select id="cursos" name="listaCursos">
						<option value=""></option>
					<%
					
						Collection<Curso> cursos = (Collection<Curso>) request.getAttribute("listaCursos");
						for(Curso curso : cursos) {
					%>
					
						<option value="<%=curso.getMapeadorAtributos().get("id")%>"><%=curso.getMapeadorAtributos().get("nome")%></option>
						
					<% } %>
					</select>
				</span>
				
				<span style="display: block; margin: 1%;">
					<label for="listaDepartamentos" class="label">Departamento:</label>
					<select id="departamentos" name="listaDepartamentos" disabled>
						<option value=""></option>
					<%
					
						Collection<Departamento> dptos = (Collection<Departamento>) request.getAttribute("listaDepartamentos");
						for(Departamento dpto : dptos) {
					%>
						<option value="<%=dpto.getMapeadorAtributos().get("id")%>"><%=dpto.getMapeadorAtributos().get("nome")%></option>
						
					<% } %>
					</select>
				</span>
				
				<span style="display: block; margin: 1% 0%;">
					<input type="submit" value="Cadastrar" name="acaoCadastroConsumidor"/>
					<input type="submit" value="Cancelar" name="acaoCadastroConsumidor"/>
				</span>
				
			</form>
		</div>		
	</div>
	<script type="text/javascript">
		function onChange() {
			var radius = document.getElementsByName("tipo");
			for(var i=0; i<radius.length; i++) {
				if(radius[i].checked) {
					var str = new String(radius[i].value);
					if(str=="<%=TipoConsumidor.ALUNO.name() %>") {
						document.getElementById("departamentos").disabled=true;
						document.getElementById("cursos").disabled=false;
					} else if (str=="<%=TipoConsumidor.FUNCIONARIO.name() %>") {
						document.getElementById("cursos").disabled=true;
						document.getElementById("departamentos").disabled=false;
					}
				}
			}
		}
	</script>
</body>
</html>
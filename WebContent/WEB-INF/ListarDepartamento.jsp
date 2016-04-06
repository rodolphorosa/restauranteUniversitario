<%@page import="entidades.Departamento"%>
<%@ page import="java.util.Collection" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Listando departamentos</title>
</head>

<body>

<%@include file="messagePage.jsp" %>

	<form action="ListarDepartamento" method="post">
		<table width="80%">
		  <tr>
		    <th>Sigla</th>
		    <th>Nome</th>
		  </tr>
		  
		  <%
			  try{
				  Collection<Departamento> departamentosDisponiveis = (Collection<Departamento>)request.getAttribute("departamentos");
				  for (Departamento departamento: departamentosDisponiveis){
		  %>
			  <tr align="center">
			    <td>
			    	<input type="radio" name='sigla' value='<%=departamento.getMapeadorAtributos().get("sigla")%>'>
			    	<%=departamento.getMapeadorAtributos().get("sigla")%>
			    </td>
			    <td><%=departamento.getMapeadorAtributos().get("nome")%></td>
			  </tr>
		  <%
				  }
			  } catch(Exception e){ }
		  %>
		</table>

		<input type="submit" name ="acaoListar" value = "Criar">
		<input type="submit" name ="acaoListar" value = "Atualizar">
		<input type="submit" name ="acaoListar" value = "Ver">
	</form>
</body>

</html>
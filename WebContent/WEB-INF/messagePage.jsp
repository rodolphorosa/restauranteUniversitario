<% if (request.getAttribute("message") != null){ %>
	<div>
		<strong><%=request.getAttribute("message") %></strong> 
	</div>
<% } %>

<% if (request.getAttribute("erro") != null){ %>
	<div style="color:red;">
		<strong><%=request.getAttribute("erro") %></strong>
	</div>
<% } %>
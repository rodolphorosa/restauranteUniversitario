package controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PaginaPrincipal")
public class PaginaPrincipal extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = (String) request.getParameter("acaoInicial");
		
		if(acao==null) {
			acao = "";
		}
		
		switch(acao) {
		case "Listar Departamento":
			listarDepartamento(request, response);
			break;
		case "Listar Curso":
			listarCurso(request, response);
			break;
		case "Listar Consumidores":
			listarConsumidores(request, response);
			break;
		case "Listar Refeicoes":
			listarRefeicoes(request, response);
			break;
		case "Listar Tickets":
			listarTickets(request, response);
			break;
		default:
			request.getRequestDispatcher("WEB-INF/PaginaPrincipal.jsp").forward(request, response);
		}
	}
	
	private void listarTickets(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("ListarTickets").forward(request, response);
	}

	private void listarRefeicoes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("ListarRefeicoes").forward(request, response);		
	}

	private void listarConsumidores(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("ListarConsumidores").forward(request, response);		
	}

	private void listarCurso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("ListarCurso").forward(request, response);		
	}

	private void listarDepartamento(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("ListarDepartamento").forward(request, response);		
	}
}
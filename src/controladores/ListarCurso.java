package controladores;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mapeadores.CursoMapper;
import entidades.Curso;

@WebServlet("/ListarCurso")
public class ListarCurso extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		listarCursos(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = (String) request.getParameter("acaoListar");
		
		if (acao == null) acao = "";
		
		switch (acao) {
			case "Criar":
				request.getRequestDispatcher("CriarCurso").forward(request,response);
				break;
			case "Atualizar":
				request.getRequestDispatcher("AtualizarCurso").forward(request,response);
				break;
			case "Ver":
				request.getRequestDispatcher("VerCurso").forward(request,response);
				break;
			case "":
			default:
				listarCursos(request,response);				
		}
	}

	
	private void listarCursos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Collection<Curso> cursos = new ArrayList<Curso>();
		try {
			cursos = CursoMapper.getInstance().encontrarTodos();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("cursos", cursos);
		request.getRequestDispatcher("WEB-INF/ListarCurso.jsp").forward(request,response);
	}

}

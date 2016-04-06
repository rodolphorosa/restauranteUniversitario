package controladores;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mapeadores.CursoMapper;
import entidades.Curso;
import excecoes.CursoInexistenteException;

@WebServlet("/VerCurso")
public class VerCurso extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String acao = (String) request.getParameter("acaoVer");
		if (acao == null)
			acao = "";

		switch (acao) {
			case "Voltar":
				request.getRequestDispatcher("ListarCurso").forward(request,response);
				break;
			default:
				Curso curso = null;
				
				try {
					String sigla = request.getParameter("sigla");				
					if(sigla == null) {
						throw new CursoInexistenteException();
					}				
					curso = CursoMapper.getInstance().encontrarPorSigla(sigla);
				
				} catch (CursoInexistenteException e) {
					request.setAttribute("erro", "Curso não foi informado!");
					request.getRequestDispatcher("WEB-INF/VerCurso.jsp").forward(request,response);
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					request.setAttribute("curso antigo", curso);
					request.getRequestDispatcher("WEB-INF/VerCurso.jsp").forward(request,response);
				}
		}
	}
}
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
import mapeadores.DepartamentoMapper;
import entidades.Curso;
import entidades.Departamento;
import excecoes.CampoObrigatorioException;
import excecoes.CursoExistenteException;
import excecoes.NomeInvalidoException;

@WebServlet("/CriarCurso")
public class CriarCurso extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = (String) request.getParameter("acaoCriar");
		Collection<Departamento> departamentosDisponiveis = new ArrayList<Departamento>();
		try {
			departamentosDisponiveis = DepartamentoMapper.getInstance().encontrarTodos();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("departamentosDisponiveis", departamentosDisponiveis);
		
		if (acao != null) {
			switch (acao) {
				case "Criar":
					criarCurso(request,response);
					break;
				default:
					request.getRequestDispatcher("ListarCurso").forward(request,response);
			}
		} else {
			request.getRequestDispatcher("WEB-INF/CriarCurso.jsp").forward(request,response);	
		}
	}

	private void criarCurso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = (String) request.getParameter("nome");
		String sigla = (String) request.getParameter("sigla");	
		
		try {
			String siglaDpto = (String) request.getParameter("departamento");			
			
			if(siglaDpto==null || siglaDpto.equals("")) {
				throw new CampoObrigatorioException("departamento");
			}
			
			if(CursoMapper.getInstance().encontrarPorSigla(sigla.trim().toUpperCase())!=null) {
				throw new CursoExistenteException();
			}
			
			Departamento departamento = DepartamentoMapper.getInstance().encontrarPorSigla(siglaDpto.trim().toUpperCase());
			Curso curso = new Curso(sigla.toUpperCase(), nome.toUpperCase(), departamento);
			CursoMapper.getInstance().adicionar(curso);
			request.setAttribute("message", "Novo departamento criado!");
			request.getRequestDispatcher("ListarCurso").forward(request,response);
		
		} catch (CampoObrigatorioException e) {
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("WEB-INF/CriarCurso.jsp").forward(request,response);
		} catch (CursoExistenteException e) {
			request.setAttribute("erro", "Sigla já está em uso");
			request.getRequestDispatcher("WEB-INF/CriarCurso.jsp").forward(request,response);
			e.printStackTrace();
		} catch (NomeInvalidoException e) {
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("WEB-INF/CriarCurso.jsp").forward(request,response);
			e.printStackTrace();
		} catch (SQLException e) {
			request.setAttribute("erro", "Não foi possível realizar a operação. Verifique os dados inseridos.");
			request.getRequestDispatcher("WEB-INF/CriarCurso.jsp").forward(request, response);
			e.printStackTrace();
		}  catch (IllegalArgumentException|IllegalAccessException e) {
			request.setAttribute("erro", "Não foi possível realizar a operação. Verifique os dados inseridos.");
			request.getRequestDispatcher("WEB-INF/CriarCurso.jsp").forward(request, response);
			e.printStackTrace();
		}		
	}
}
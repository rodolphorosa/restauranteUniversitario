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
import entidades.Atributo;
import entidades.Curso;
import entidades.Departamento;
import excecoes.CampoInalteravelException;
import excecoes.CampoInexistenteException;
import excecoes.CampoObrigatorioException;
import excecoes.CursoInexistenteException;
import excecoes.DepartamentoInexistenteException;
import excecoes.NomeInvalidoException;

@WebServlet("/AtualizarCurso")
public class AtualizarCurso extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String acao = (String) request.getParameter("acaoAtualizar");
		Collection<Departamento> departamentosDisponiveis = new ArrayList<Departamento>();
		try {
			departamentosDisponiveis = DepartamentoMapper.getInstance().encontrarTodos();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("departamentosDisponiveis", departamentosDisponiveis);
		
		if (acao == null)
			acao = "";

		switch (acao) {
			case "Cancelar":
			case "Voltar":
				request.getRequestDispatcher("ListarCurso").forward(request,response);
				break;
			case "Atualizar":
				atualizarCursoAntigo(request,response);
				break;
			default:
				Curso cursoAntigo = null;				
				try {
					String sigla = (String) request.getParameter("sigla");
					cursoAntigo = CursoMapper.getInstance().encontrarPorSigla(sigla);
					
					if(cursoAntigo == null){
						throw new CursoInexistenteException();
					}					
				} catch (CursoInexistenteException e) {
					e.printStackTrace();
					request.setAttribute("erro", "Curso não informado!");
					request.getRequestDispatcher("WEB-INF/AtualizarCurso.jsp").forward(request,response);
					
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					request.setAttribute("curso antigo",cursoAntigo);
					request.getRequestDispatcher("WEB-INF/AtualizarCurso.jsp").forward(request,response);
				}				
		}
	}
	
	
	private void atualizarCursoAntigo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = (String) request.getParameter("nome");
		String sigla = (String) request.getParameter("sigla");
		String siglaDpto = (String) request.getParameter("departamento");
		
		if (nome=="" || sigla=="" || request.getParameter("departamento") == null){
			request.setAttribute("erro", "Um curso deve conter um nome, uma sigla e um departamento");
			request.getRequestDispatcher("WEB-INF/AtualizarCurso.jsp").forward(request,response);
		} else {
			try {
				if(sigla==null || sigla.equals("")) {
					throw new CampoObrigatorioException("sigla");
				}
				
				if(siglaDpto==null || siglaDpto.equals("")) {
					throw new CampoObrigatorioException("departamento");
				}
				
				Curso curso = CursoMapper.getInstance().encontrarPorSigla(sigla.trim().toUpperCase());
				
				if(curso == null) {
					throw new CursoInexistenteException();
				}
				
				Departamento departamento = DepartamentoMapper.getInstance().encontrarPorSigla(siglaDpto.trim().toUpperCase());
				
				if(departamento == null) {
					throw new DepartamentoInexistenteException();
				}
								
				Collection<Atributo<?>> atributos = new ArrayList<Atributo<?>>();
				atributos.add(new Atributo<String>(String.class, "nome", nome.trim().toUpperCase()));
				atributos.add(new Atributo<Departamento>(Departamento.class, "departamento", departamento));
				curso.atualizar(atributos);
				
				CursoMapper.getInstance().atualizar(curso);
				request.setAttribute("message", "Novo curso criado!");
				request.getRequestDispatcher("ListarCurso").forward(request,response);
			
			} catch (CampoObrigatorioException e) {
				request.setAttribute("erro", e.getMessage());
				request.getRequestDispatcher("WEB-INF/AtualizarCurso.jsp").forward(request,response);
			} catch (CampoInalteravelException e) {
				request.setAttribute("erro", e.getMessage());
				request.getRequestDispatcher("WEB-INF/AtualizarCurso.jsp").forward(request,response);
			} catch (CampoInexistenteException e) {
				request.setAttribute("erro", "Não foi possível realizar a operação. Verifique os dados inseridos.");
				request.getRequestDispatcher("WEB-INF/AtualizarCurso.jsp").forward(request, response);
				e.printStackTrace();
			} catch (CursoInexistenteException e) {
				request.setAttribute("erro", e.getMessage());
				request.getRequestDispatcher("WEB-INF/AtualizarCurso.jsp").forward(request,response);
				e.printStackTrace();
			} catch (DepartamentoInexistenteException e) {
				request.setAttribute("erro", e.getMessage());
				request.getRequestDispatcher("WEB-INF/AtualizarCurso.jsp").forward(request,response);
				e.printStackTrace();
			} catch (NomeInvalidoException e) {
				request.setAttribute("erro", e.getMessage());
				request.getRequestDispatcher("WEB-INF/AtualizarCurso.jsp").forward(request,response);
				e.printStackTrace();
			} catch (SQLException e) {
				request.setAttribute("erro", "Não foi possível realizar a operação. Verifique os dados inseridos.");
				request.getRequestDispatcher("WEB-INF/AtualizarCurso.jsp").forward(request, response);
				e.printStackTrace();
			} catch (NoSuchFieldError|NoSuchFieldException|SecurityException|IllegalArgumentException|IllegalAccessException e) {
				request.setAttribute("erro", "Não foi possível realizar a operação. Verifique os dados inseridos.");
				request.getRequestDispatcher("WEB-INF/AtualizarCurso.jsp").forward(request, response);
				e.printStackTrace();
			}
		}
	}
}
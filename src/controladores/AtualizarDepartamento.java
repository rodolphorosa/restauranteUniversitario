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

import mapeadores.DepartamentoMapper;
import entidades.Atributo;
import entidades.Departamento;
import excecoes.CampoInalteravelException;
import excecoes.CampoInexistenteException;
import excecoes.CampoObrigatorioException;
import excecoes.DepartamentoInexistenteException;
import excecoes.NomeInvalidoException;

@WebServlet("/AtualizarDepartamento")
public class AtualizarDepartamento extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String acao = (String) request.getParameter("acaoAtualizar");
		if (acao == null)
			acao = "";

		switch (acao) {
			case "Cancelar":
			case "Voltar":
				request.getRequestDispatcher("ListarDepartamento").forward(request,response);
				break;
			case "Atualizar":
				atualizarDepartamentoAntigo(request,response);
				break;
			default:
				Departamento departamento = null;
				try {
					String sigla = (String) request.getParameter("sigla");
					if(sigla==null) {
						throw new DepartamentoInexistenteException();
					}
					departamento = DepartamentoMapper.getInstance().encontrarPorSigla(sigla.trim().toUpperCase());
					if(departamento==null) {
						throw new DepartamentoInexistenteException();
					}
					
				} catch (DepartamentoInexistenteException e) {
					request.setAttribute("erro", "Departamento não foi informado!");
					request.getRequestDispatcher("WEB-INF/AtualizarDepartamento.jsp").forward(request,response);
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					request.setAttribute("departamento antigo",departamento);
					request.getRequestDispatcher("WEB-INF/AtualizarDepartamento.jsp").forward(request,response);
				}
		}
	}
	
	
	private void atualizarDepartamentoAntigo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = (String) request.getParameter("nome");
		String sigla = (String) request.getParameter("sigla");
		
		if (nome=="" || sigla==""){
			request.setAttribute("erro", "Um departamento deve conter um nome e uma sigla");
			request.getRequestDispatcher("WEB-INF/AtualizarDepartamento.jsp").forward(request,response);
		} else {
				try {
					
					Departamento departamento = DepartamentoMapper.getInstance().encontrarPorSigla(sigla);
					
					if(departamento==null) {
						throw new DepartamentoInexistenteException();
					}
					
					Collection<Atributo<?>> atributos = new ArrayList<Atributo<?>>();
					atributos.add(new Atributo<String>(String.class, "nome", nome.trim().toUpperCase()));
					departamento.atualizar(atributos);
					DepartamentoMapper.getInstance().atualizar(departamento);
					request.getRequestDispatcher("ListarDepartamento").forward(request,response);
				
				} catch (DepartamentoInexistenteException e) {
					request.setAttribute("erro", e.toString());
					request.getRequestDispatcher("WEB-INF/AtualizarDepartamento.jsp").forward(request,response);
				} catch (CampoInalteravelException e) {
					request.setAttribute("erro", e.toString());
					request.getRequestDispatcher("WEB-INF/AtualizarDepartamento.jsp").forward(request,response);
					e.printStackTrace();
				} catch (CampoInexistenteException e) {
					request.setAttribute("erro", "Não foi possível realizar a operação. Verifique os dados inseridos.");
					request.getRequestDispatcher("WEB-INF/AtualizarDepartamento.jsp").forward(request, response);
					e.printStackTrace();
				} catch (CampoObrigatorioException e) {
					request.setAttribute("erro", e.toString());
					request.getRequestDispatcher("WEB-INF/AtualizarDepartamento.jsp").forward(request,response);
					e.printStackTrace();
				} catch (NomeInvalidoException e) {
					request.setAttribute("erro", e.toString());
					request.getRequestDispatcher("WEB-INF/AtualizarDepartamento.jsp").forward(request,response);
					e.printStackTrace();
				}catch (NoSuchFieldError|NoSuchFieldException|SecurityException|IllegalArgumentException|IllegalAccessException e) {
					request.setAttribute("erro", "Não foi possível realizar a operação. Verifique os dados inseridos.");
					request.getRequestDispatcher("WEB-INF/AtualizarDepartamento.jsp").forward(request, response);
					e.printStackTrace();
				} catch (SQLException e) {
					request.setAttribute("erro", "Não foi possível realizar a operação. Verifique os dados inseridos.");
					request.getRequestDispatcher("WEB-INF/AtualizarDepartamento.jsp").forward(request, response);
					e.printStackTrace();
				}
		}	
	}
}
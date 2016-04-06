package controladores;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mapeadores.DepartamentoMapper;
import entidades.Departamento;
import excecoes.CampoObrigatorioException;
import excecoes.DepartamentoExistenteException;
import excecoes.NomeInvalidoException;

@WebServlet("/CriarDepartamento")
public class CriarDepartamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = (String) request.getParameter("acaoCriar");
		
		if (acao != null){
			switch (acao) {
				case "Criar":
					criarDepartamento(request,response);
					break;
				default:
					request.getRequestDispatcher("ListarDepartamento").forward(request,response);
			}
		}else{
			request.getRequestDispatcher("WEB-INF/CriarDepartamento.jsp").forward(request,response);	
		}
	}

	private void criarDepartamento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = (String) request.getParameter("nome");
		String sigla = (String) request.getParameter("sigla");
		
		try {
			
			if(DepartamentoMapper.getInstance().encontrarPorSigla(sigla.trim().toUpperCase())!=null) {
				throw new DepartamentoExistenteException();
			}
			
			Departamento departamento = new Departamento(sigla.trim().toUpperCase(), nome.trim().toUpperCase());
			DepartamentoMapper.getInstance().adicionar(departamento);
			request.setAttribute("message", "Novo departamento criado!");
			request.getRequestDispatcher("ListarDepartamento").forward(request,response);
		} catch (CampoObrigatorioException e) {
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("WEB-INF/CriarDepartamento.jsp").forward(request,response);
		} catch (NomeInvalidoException e) {
			request.setAttribute("erro", e.toString());
			request.getRequestDispatcher("WEB-INF/CriarDepartamento.jsp").forward(request,response);
			e.printStackTrace();
		} catch (DepartamentoExistenteException e) {
			request.setAttribute("erro", "Sigla já está em uso!");
			request.getRequestDispatcher("WEB-INF/CriarDepartamento.jsp").forward(request,response);
			e.printStackTrace();
		} catch (IllegalArgumentException|IllegalAccessException e) {
			request.setAttribute("erro", "Não foi possível realizar a operação. Verifique os dados inseridos.");
			request.getRequestDispatcher("WEB-INF/CriarDepartamento.jsp").forward(request, response);
			e.printStackTrace();
		} catch (SQLException e) {
			request.setAttribute("erro", "Não foi possível cadastrar o departamento!");
			request.getRequestDispatcher("WEB-INF/CriarDepartamento.jsp").forward(request,response);
			e.printStackTrace();
		}
	}
}
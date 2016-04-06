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
import entidades.Departamento;

@WebServlet("/ListarDepartamento")
public class ListarDepartamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		listarDepartamentos(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = (String) request.getParameter("acaoListar");
		
		if (acao == null) acao = "";
		
		switch (acao) {
			case "Criar":
				request.getRequestDispatcher("CriarDepartamento").forward(request,response);				
				break;
			case "Atualizar":
				atualizarDepartamento(request, response);
				break;
			case "Ver":
				request.getRequestDispatcher("VerDepartamento").forward(request,response);
				break;
			case "":
			default:
				listarDepartamentos(request,response);				
		}
	}

	private void atualizarDepartamento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sigla = (String) request.getParameter("sigla");
		Departamento departamentoAntigo = null;
		try {
			departamentoAntigo = DepartamentoMapper.getInstance().encontrarPorSigla(sigla);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("departamento antigo", departamentoAntigo);
		request.getRequestDispatcher("AtualizarDepartamento").forward(request,response);
	}

	private void listarDepartamentos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Collection<Departamento> departamentos = new ArrayList<Departamento>();
		try {
			departamentos = DepartamentoMapper.getInstance().encontrarTodos();
			request.setAttribute("departamentos", departamentos);
			request.getRequestDispatcher("WEB-INF/ListarDepartamento.jsp").forward(request,response);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
}
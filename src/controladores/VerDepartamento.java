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
import excecoes.DepartamentoInexistenteException;

@WebServlet("/VerDepartamento")
public class VerDepartamento extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String acao = (String) request.getParameter("acaoVer");
		if (acao == null)
			acao = "";

		switch (acao) {
			case "Voltar":
				request.getRequestDispatcher("ListarDepartamento").forward(request,response);
				break;
			default:
			Departamento departamento = null;
			
			try {
				String sigla = (String) request.getParameter("sigla");
				if(sigla==null) {
					throw new DepartamentoInexistenteException();
				}
				departamento = DepartamentoMapper.getInstance().encontrarPorSigla(sigla);
				
			} catch (DepartamentoInexistenteException e) {
				request.setAttribute("erro", "Departamento não foi informado!");
				request.getRequestDispatcher("WEB-INF/VerDepartamento.jsp").forward(request,response);
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				request.setAttribute("departamento antigo", departamento);
				request.getRequestDispatcher("WEB-INF/VerDepartamento.jsp").forward(request,response);
			}
		}
	}
}
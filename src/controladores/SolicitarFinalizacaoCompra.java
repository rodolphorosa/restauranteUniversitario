package controladores;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mapeadores.ConsumidorMapper;
import excecoes.CampoObrigatorioException;

@WebServlet("/SolicitarFinalizacaoCompra")
public class SolicitarFinalizacaoCompra extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = (String) request.getParameter("acaoFinalizarVenda");
		
		if(acao==null) acao = "";
		
		switch(acao){
		case "Finalizar":
			comprarTickets(request, response);
			break;
		case "":
			default:
				request.getRequestDispatcher("ListarTickets").forward(request, response);
		}
	}

	private void comprarTickets(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String matricula = (String) request.getParameter("matricula");
		try {
			if(matricula==null || matricula.trim().equals("")) {
				throw new CampoObrigatorioException("matrícula");
			}
			
			if(ConsumidorMapper.getInstance().encontrarPorMatricula(matricula)==null) {
				throw new CampoObrigatorioException("matrícula");
			}
		} catch (CampoObrigatorioException e) {
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("WEB-INF/VenderTicket.jsp").forward(request, response);
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			request.getRequestDispatcher("FinalizarCompra").forward(request, response);
		}
	}
}
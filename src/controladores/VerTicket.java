package controladores;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mapeadores.TicketMapper;
import entidades.Ticket;
import excecoes.TicketNaoInformadoException;

@WebServlet("/VerTicket")
public class VerTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = (String) request.getParameter("acaoVerTicket");
		
		if(acao==null) acao="";
		
		switch(acao) {
		case "Voltar":
			listarTickets(request, response);
			break;
		case "":
			default:
				Ticket ticket = null;
				try {
					String idString = (String) request.getParameter("id");
					Long id = Long.valueOf(idString);
					ticket = TicketMapper.getInstance().encontrarPorId(id);
					if(ticket==null) {
						throw new TicketNaoInformadoException();
					}
				} catch (TicketNaoInformadoException e) {
					request.setAttribute("erro", e.getMessage());
					request.getRequestDispatcher("WEB-INF/VerTicket.jsp").forward(request, response);
					e.printStackTrace();
				} catch (NumberFormatException e) {
					request.setAttribute("erro", "Ticket não informado");
					request.getRequestDispatcher("WEB-INF/VerTicket.jsp").forward(request, response);
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					request.setAttribute("ticket", ticket);
					request.getRequestDispatcher("WEB-INF/VerTicket.jsp").forward(request, response);
				}
		}
		
	}

	private void listarTickets(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("ListarTickets").forward(request, response);
	}

}

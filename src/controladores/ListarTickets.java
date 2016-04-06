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

import mapeadores.TicketMapper;
import entidades.Compra;
import entidades.Ticket;

@WebServlet("/ListarTickets")
public class ListarTickets extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		listarTickets(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = (String) request.getParameter("acaoTicket");
		
		if(acao==null) acao = "";
		
		switch(acao) {
		case "Vender":
			venderTicket(request, response);
			break;
		case "Ver":
			verTicket(request, response);
			break;
		case "Atualizar":
			atualizarTicket(request, response);
			break;
		case "":
			default:
				listarTickets(request, response);
		}
	}
	
	private void atualizarTicket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("AtualizarTicket").forward(request, response);
	}
	
	private void verTicket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("VerTicket").forward(request, response);
	}

	private void venderTicket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Compra compra = new Compra();
		request.getSession().setAttribute("compra", compra);
		request.getRequestDispatcher("VenderTicket").forward(request, response);
	}
	
	private void listarTickets(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Collection<Ticket> tickets = new ArrayList<Ticket>();
				
		try {
			tickets = TicketMapper.getInstance().encontrarTodos();
			request.setAttribute("tickets", tickets);
			request.getRequestDispatcher("WEB-INF/ListarTickets.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
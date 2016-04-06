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
import entidades.Atributo;
import entidades.Ticket;
import excecoes.CampoInalteravelException;
import excecoes.CampoObrigatorioException;
import excecoes.TicketNaoInformadoException;

@WebServlet("/AtualizarTicket")
public class AtualizarTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = (String) request.getParameter("acaoAtualizarTicket");
		
		if(acao==null) {
			acao = "";
		}
		
		switch(acao) {
		case "Atualizar":
			atualizarTicket(request, response);
			break;
		case "Cancelar":
			request.getRequestDispatcher("ListarTickets").forward(request, response);
			break;
		case "Voltar":
			request.getRequestDispatcher("ListarTickets").forward(request, response);
			break;
			default:
				Ticket ticket = null;
				try {
					String idString = (String) request.getParameter("id");
					Long id = Long.valueOf(idString);
					ticket = TicketMapper.getInstance().encontrarPorId(id);
					if(ticket == null) {
						throw new TicketNaoInformadoException();
					}
				} catch (NumberFormatException e) {
					request.setAttribute("erro", "Ticket não informado!");
					request.getRequestDispatcher("WEB-INF/AtualizarTicket.jsp").forward(request, response);
					e.printStackTrace();
				} catch (NullPointerException e) {
					request.setAttribute("erro", "Ticket não informado");
					request.getRequestDispatcher("WEB-INF/AtualizarTicket.jsp").forward(request, response);
					e.printStackTrace();
				} catch (TicketNaoInformadoException e) {
					request.setAttribute("erro", "Ticket não informado");
					request.getRequestDispatcher("WEB-INF/AtualizarTicket.jsp").forward(request, response);
					e.printStackTrace();
				} catch (SQLException e) {
					request.setAttribute("erro", "Não foi possível realizar a operação");
					request.getRequestDispatcher("WEB-INF/AtualizarTicket.jsp").forward(request, response);
					e.printStackTrace();
				} finally {
					request.setAttribute("ticket antigo", ticket);
					request.getRequestDispatcher("WEB-INF/AtualizarTicket.jsp").forward(request, response);
				}
		}
		
	}

	private void atualizarTicket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String estadoPagemento = (String) request.getParameter("estado");
		boolean estadoPgto = false;
		if(estadoPagemento.equals("Pago")) {
			estadoPgto = true;
		} else if (estadoPagemento.equals("Não pago")) {
			estadoPgto = false;
		}
		
		Collection<Atributo<?>> atributos = new ArrayList<Atributo<?>>();
		atributos.add(new Atributo<Boolean>(Boolean.class, "vendido", estadoPgto));
		
		Long id = Long.valueOf(request.getParameter("id"));
		try {
			Ticket ticket = TicketMapper.getInstance().encontrarPorId(id);
			ticket.atualizar(atributos);
			TicketMapper.getInstance().atualizar(ticket);
			request.getRequestDispatcher("ListarTickets").forward(request, response);
		} catch (SQLException e) {
			request.setAttribute("erro", "Não foi possível realizar a operação. Verifique os dados inseridos.");
			request.getRequestDispatcher("WEB-INF/AtualizarTicket.jsp").forward(request, response);
			e.printStackTrace();
		} catch (IllegalArgumentException|IllegalAccessException|NoSuchFieldException|SecurityException e) {
			request.setAttribute("erro", "Não foi possível realizar a operação. Verifique os dados inseridos.");
			request.getRequestDispatcher("WEB-INF/AtualizarTicket.jsp").forward(request, response);
			e.printStackTrace();
		} catch (CampoObrigatorioException e) {
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("WEB-INF/AtualizarTicket.jsp").forward(request, response);
			e.printStackTrace();
		} catch (CampoInalteravelException e) {
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("WEB-INF/AtualizarTicket.jsp").forward(request, response);
			e.printStackTrace();
		}
	}

}

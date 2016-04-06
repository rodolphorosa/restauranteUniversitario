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

import mapeadores.RefeicaoMapper;
import entidades.Compra;
import entidades.Refeicao;
import entidades.Ticket;
import excecoes.CampoObrigatorioException;
import excecoes.RefeicaoNaoInformadaException;

@WebServlet("/VenderTicket")
public class VenderTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Collection<Refeicao> refeicoes = new ArrayList<Refeicao>();
		
		Compra compra = (Compra) request.getSession().getAttribute("compra");
		
		try {
			refeicoes = RefeicaoMapper.getInstance().encontrarTodos();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("compra", compra);
		request.setAttribute("refeicoes", refeicoes);
		
		String acao = (String) request.getParameter("acaoVenderTicket");
		
		if(acao!=null) {		
			switch(acao) {
				case "Comprar":
					adicionarTicket(request, response);
					break;
				case "Cancelar":
					cancelarCompra(request, response); 
					break;
				default:
					cancelarCompra(request, response);
			}
		} else {
			request.getRequestDispatcher("WEB-INF/VenderTicket.jsp").forward(request, response);
		}
	}

	private void cancelarCompra(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("ListarTickets").forward(request, response);
	}
	
	private void adicionarTicket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Compra compra = (Compra) request.getSession().getAttribute("compra");
		
		Collection<Ticket> tickets = new ArrayList<Ticket>();
		String idRefeicaoString = (String) request.getParameter("refeicao");
		Long idRefeicao = null;
		int quantidade = 0;
		
		try {
			idRefeicao = Long.valueOf(idRefeicaoString);
		} catch (NumberFormatException e) {
			request.setAttribute("erro", "Refeição não informada");
			request.getRequestDispatcher("WEB-INF/VenderTicket.jsp").forward(request, response);
			e.printStackTrace();
		} catch (NullPointerException e) {
			request.setAttribute("erro", "Refeição não informada");
			request.getRequestDispatcher("WEB-INF/VenderTicket.jsp").forward(request, response);
			e.printStackTrace();
		}
		
		try {
			quantidade = Integer.valueOf(request.getParameter("quantidade"));
		} catch (NumberFormatException e) {
			request.setAttribute("erro", "Quantidade não informada");
			request.getRequestDispatcher("WEB-INF/VenderTicket.jsp").forward(request, response);
			e.printStackTrace();
		} catch (NullPointerException e) {
			request.setAttribute("erro", "Quantidade não informada");
			request.getRequestDispatcher("WEB-INF/VenderTicket.jsp").forward(request, response);
			e.printStackTrace();
		}
		
		try {
					
			Refeicao refeicao = RefeicaoMapper.getInstance().encontrarPorId(idRefeicao);
			
			if(refeicao==null) {
				throw new RefeicaoNaoInformadaException();
			}
			
			if(quantidade<=0) {
				throw new CampoObrigatorioException("quantidade");
			}
			
			for(int i=0; i<quantidade; i++) {
				Ticket t = new Ticket(refeicao, null);
				tickets.add(t);
			}
			
		} catch (RefeicaoNaoInformadaException e) {
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("WEB-INF/VenderTicket.jsp").forward(request, response);
			e.printStackTrace();
		}  catch (CampoObrigatorioException e) {
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("WEB-INF/VenderTicket.jsp").forward(request, response);
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			request.setAttribute("erro", "Não foi possível realizar a operação. Verifique os dados inseridos.");
			request.getRequestDispatcher("WEB-INF/VenderTicket.jsp").forward(request, response);
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			request.setAttribute("erro", "Não foi possível realizar a operação. Verifique os dados inseridos.");
			request.getRequestDispatcher("WEB-INF/VenderTicket.jsp").forward(request, response);
			e.printStackTrace();
		} catch (SQLException e) {
			request.setAttribute("erro", "Não foi possível realizar a operação. Verifique os dados inseridos.");
			request.getRequestDispatcher("WEB-INF/VenderTicket.jsp").forward(request, response);
			e.printStackTrace();
		} finally {
			for(Ticket ticket : tickets) {
				compra.adicionarTicket(ticket);
			}
			
			request.getSession().setAttribute("compra", compra);
			request.getRequestDispatcher("WEB-INF/VenderTicket.jsp").forward(request, response);
		}
	}
}
package controladores;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mapeadores.ConsumidorMapper;
import mapeadores.TicketMapper;
import entidades.Compra;
import entidades.Consumidor;
import entidades.Ticket;
import excecoes.CarrinhoVazioException;
import excecoes.ConsumidorNaoIdentificadoException;
import excecoes.TicketNaoSelecionadoException;
import excecoes.TicketVendidoException;
import excecoes.VendaInconsistenteException;

@WebServlet("/FinalizarCompra")
public class FinalizarCompra extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = (String) request.getParameter("acaoFinalizar");
		
		if(acao==null) acao = "";
		
		switch(acao) {
		case "Confirmar":
			confirmarCompra(request, response);
			break;
		case "Cancelar":
			cancelarCompra(request, response);
			break;
		case "Voltar":
			cancelarCompra(request, response);
			break;
		case "":
		default:
			Consumidor consumidor = null;
			try {
				String matricula = (String) request.getParameter("matricula");
				consumidor = ConsumidorMapper.getInstance().encontrarPorMatricula(matricula);
				if(consumidor==null) {
					throw new ConsumidorNaoIdentificadoException();
				}
				Compra compra = (Compra) request.getSession().getAttribute("compra");
				compra.vincularConsumidor(consumidor);
				request.setAttribute("consumidorCompra", consumidor);
				request.getRequestDispatcher("WEB-INF/FinalizarCompra.jsp").forward(request, response);
			} catch (ConsumidorNaoIdentificadoException e) {
				request.setAttribute("erro", e.getMessage());
				request.getRequestDispatcher("WEB-INF/FinalizarCompra.jsp").forward(request, response);
				e.printStackTrace();
			} catch (NullPointerException e ) {
				request.setAttribute("erro", "Matrícula não informada");
				request.getRequestDispatcher("WEB-INF/FinalizarCompra.jsp").forward(request, response);
				e.printStackTrace();
			} catch (SQLException e) {
				request.setAttribute("erro", "Não foi possível realizar a operação. Verifique os dados inseridos.");
				request.getRequestDispatcher("WEB-INF/FinalizarCompra.jsp").forward(request, response);
				e.printStackTrace();
			}
		}		
	}

	private void cancelarCompra(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("ListarTickets").forward(request, response);
	}

	private void confirmarCompra(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cpf = (String) request.getParameter("cpf");
		try {
			Compra compra = (Compra) request.getSession().getAttribute("compra");
			if(compra.getCarrinho().size()==0) {
				throw new CarrinhoVazioException();
			}
			
			Consumidor consumidor = ConsumidorMapper.getInstance().encontrarPorCpf(cpf);
			compra.finalizarCompra(consumidor);
			for(Ticket t : compra.getCarrinho()) {
				TicketMapper.getInstance().adicionar(t);
			}
			compra.esvaziarCarrinho();
			request.getRequestDispatcher("ListarTickets").forward(request, response);
		} catch(ConsumidorNaoIdentificadoException e) {
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("WEB-INF/FinalizarCompra.jsp").forward(request, response);
			e.printStackTrace();
		} catch (VendaInconsistenteException e) {
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("WEB-INF/FinalizarCompra.jsp").forward(request, response);
			e.printStackTrace();
		} catch (TicketVendidoException e) {
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("WEB-INF/FinalizarCompra.jsp").forward(request, response);
			e.printStackTrace();
		} catch (TicketNaoSelecionadoException e) {
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("WEB-INF/FinalizarCompra.jsp").forward(request, response);
			e.printStackTrace();
		} catch (CarrinhoVazioException e) {
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("WEB-INF/FinalizarCompra.jsp").forward(request, response);
			e.printStackTrace();
		} catch (SQLException e) {
			request.setAttribute("erro", "Não foi possível realizar a operação. Verifique os dados inseridos.");
			request.getRequestDispatcher("WEB-INF/FinalizarCompra.jsp").forward(request, response);
			e.printStackTrace();
		}
		
	}
}
package controladores;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mapeadores.ConsumidorMapper;
import entidades.Consumidor;
import excecoes.ConsumidorNaoIdentificadoException;

@WebServlet("/VerConsumidor")
public class VerConsumidor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = (String) request.getParameter("acaoVerConsumidor");
		
		if(acao==null) acao = "";
		
		switch(acao) {
		case "Voltar":
			request.getRequestDispatcher("ListarConsumidores").forward(request, response);
			break;
		default:
			Consumidor consumidor = null;				
			
			try {
				String cpf = (String) request.getParameter("cpf");
				if(cpf==null) {
					throw new ConsumidorNaoIdentificadoException();
				}
				consumidor = ConsumidorMapper.getInstance().encontrarPorCpf(cpf);				
			} catch (ConsumidorNaoIdentificadoException e) {
				request.setAttribute("erro", e.getMessage());
				request.getRequestDispatcher("WEB-INF/VerConsumidor.jsp").forward(request, response);
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				request.setAttribute("consumidor", consumidor);
				request.getRequestDispatcher("WEB-INF/VerConsumidor.jsp").forward(request, response);
			}
		}
		
	}

}

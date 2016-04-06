package controladores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mapeadores.ConsumidorMapper;
import entidades.Consumidor;

@WebServlet("/ListarConsumidores")
public class ListarConsumidores extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		listarConsumidores(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = (String) request.getParameter("acaoConsumidor");
		
		if(acao==null) { acao = "";	}
		
		switch (acao) {
		case "Atualizar":
			atualizarConsumidor(request, response);
			break;
		case "Cadastrar":
			cadastrarConsumidor(request, response);
			break;
		case "Ver":
			verConsumidor(request, response);
			break;
		case "":
		default:
			listarConsumidores(request, response);
		}
	}

	private void verConsumidor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("VerConsumidor").forward(request, response);
	}

	private void atualizarConsumidor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("AtualizarConsumidor").forward(request, response);
	}
	
	private void cadastrarConsumidor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("CadastrarConsumidor").forward(request, response);
	}
	
	private void listarConsumidores(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Collection<Consumidor> consumidores = new ArrayList<Consumidor>();
		try {
			consumidores = ConsumidorMapper.getInstance().encontrarTodos();
			request.setAttribute("consumidores", consumidores);
			request.getRequestDispatcher("WEB-INF/ListarConsumidores.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
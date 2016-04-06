package controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mapeadores.ConsumidorMapper;
import entidades.Consumidor;

@WebServlet("/ConsultarConsumidor")
public class ConsultarConsumidor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ConsultarConsumidor() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acaoBuscar = (String) request.getParameter("acaoBuscar");
		
		if(acaoBuscar==null) acaoBuscar = "";
		
		switch(acaoBuscar) {
		case "Buscar":
			buscarConsumidor(request, response);
			break;
		case "":
		default:
			request.getRequestDispatcher("WEB-INF/AtualizarConsumidor.jsp").forward(request, response);
		}
	}

	private void buscarConsumidor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cpf = (String) request.getParameter("cpfBusca");
		
		if(cpf.equals("")) {
			request.setAttribute("erro", "CPF não informado!");
			request.getRequestDispatcher("WEB-INF/AtualizarConsumidor.jsp").forward(request, response);
		} else {
			try {
				Consumidor consumidor = ConsumidorMapper.getInstance().encontrarPorCpf(cpf);
				
				if(consumidor==null)  {
					request.setAttribute("erro", "CPF não está cadastrado!");
					request.getRequestDispatcher("WEB-INF/AtualizarConsumidor.jsp").forward(request, response);
				} else {
					request.getSession().setAttribute("consumidorAntigo", consumidor);
					request.getRequestDispatcher("WEB-INF/AtualizarConsumidor.jsp").forward(request, response);
				}
				
			} catch (Exception e) { 
				e.printStackTrace();
			}
		}
	}
}
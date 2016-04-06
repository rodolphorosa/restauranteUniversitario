package controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mapeadores.ConsumidorMapper;
import entidades.Consumidor;

@WebServlet("/ConsultarConsumidorCompra")
public class ConsultarConsumidorCompra extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public ConsultarConsumidorCompra() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acaoBuscar = (String) request.getParameter("acaoConsumidorCompra");
		
		if(acaoBuscar==null) acaoBuscar = "";
		
		switch(acaoBuscar) {
		case "Consultar":
			buscarConsumidor(request, response);
			break;
		case "":
		default:
			request.getRequestDispatcher("WEB-INF/FinalizarCompra.jsp").forward(request, response);
		}
	}

	private void buscarConsumidor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cpf = (String) request.getParameter("cpfCompra");
		
		if(cpf.equals("")) {
			request.setAttribute("erro", "CPF não informado!");
			request.getRequestDispatcher("WEB-INF/FinalizarCompra.jsp").forward(request, response);
		} else {
			try {
				Consumidor consumidor = ConsumidorMapper.getInstance().encontrarPorCpf(cpf);
				
				if(consumidor==null)  {
					request.setAttribute("erro", "CPF não está cadastrado!");
					request.getRequestDispatcher("WEB-INF/FinalizarCompra.jsp").forward(request, response);
				} else {
					request.getSession().setAttribute("consumidorCompra", consumidor);
					request.getRequestDispatcher("WEB-INF/FinalizarCompra.jsp").forward(request, response);
				}
				
			} catch (Exception e) { 
				e.printStackTrace();
			}
		}		
	}
}
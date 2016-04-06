package controladores;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mapeadores.RefeicaoMapper;
import entidades.Refeicao;
import excecoes.RefeicaoInexistenteException;

@WebServlet("/VerRefeicao")
public class VerRefeicao extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = (String) request.getParameter("acaoVerRefeicao");
		
		if(acao==null) acao = "";
		
		switch(acao) {
		case "Voltar":
			request.getRequestDispatcher("ListarRefeicoes").forward(request, response);
			break;
			default:
				Refeicao refeicao = null;
				try {
					String idString = (String) request.getParameter("id");
					Long id = Long.valueOf(idString);
					refeicao = RefeicaoMapper.getInstance().encontrarPorId(id);
					if(refeicao==null) {
						throw new RefeicaoInexistenteException();
					}
				} catch (RefeicaoInexistenteException e) {
					request.setAttribute("erro", "Refeição não informada!");
					request.getRequestDispatcher("WEB-INF/VerRefeicao.jsp").forward(request, response);
				} catch (NumberFormatException e) {
					request.setAttribute("erro", "Refeição não informada!");
					request.getRequestDispatcher("WEB-INF/VerRefeicao.jsp").forward(request, response);
					e.printStackTrace();
				} catch (NullPointerException e) {
					request.setAttribute("erro", "Refeição não informada!");
					request.getRequestDispatcher("WEB-INF/VerRefeicao.jsp").forward(request, response);
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					request.setAttribute("refeicao", refeicao);
					request.getRequestDispatcher("WEB-INF/VerRefeicao.jsp").forward(request, response);
				}
		}
		
	}

}

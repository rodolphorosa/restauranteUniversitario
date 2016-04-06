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
import entidades.Refeicao;

@WebServlet("/ListarRefeicoes")
public class ListarRefeicoes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		listarRefeicoes(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String acao = (String) request.getParameter("acaoRefeicao");
		
		if(acao==null) {
			acao = "";
		}
		
		switch(acao) {
		case "Atualizar":
			atualizarRefeicao(request, response);
			break;
		case "Cadastrar":
			cadastrarRefeicao(request, response);
			break;
		case "Ver":
			verRefeicao(request, response);
			break;
		case "":
		default:
			listarRefeicoes(request, response);
		}
	}
	
	private void verRefeicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("VerRefeicao").forward(request, response);
	}

	private void atualizarRefeicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("AtualizarRefeicao").forward(request, response);
	}
	
	private void cadastrarRefeicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("CadastrarRefeicao").forward(request, response);
	}
	
	private void listarRefeicoes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Collection<Refeicao> refeicoes = new ArrayList<Refeicao>();		
		try {
			refeicoes = RefeicaoMapper.getInstance().encontrarTodos();
			request.setAttribute("refeicoes", refeicoes);
			request.getRequestDispatcher("WEB-INF/ListarRefeicoes.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
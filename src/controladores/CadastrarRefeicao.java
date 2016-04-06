package controladores;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mapeadores.RefeicaoMapper;
import entidades.Refeicao;
import entidades.TipoRefeicao;
import entidades.Turno;
import excecoes.CampoObrigatorioException;
import excecoes.DescricaoInvalidaException;
import excecoes.OpcaoVegetarianaInvalidaException;
import excecoes.TamanhoDescricaoException;
import excecoes.TamanhoOpcaoVegetarianaException;
import excecoes.TurnoInconsistenteException;

@WebServlet("/CadastrarRefeicao")
public class CadastrarRefeicao extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = (String) request.getParameter("acaoCadastroRefeicao");
		
		if(acao==null) acao = "";
		
		switch(acao) {
		case "Cadastrar":
			cadastrarRefeicao(request, response);
			break;
		case "Cancelar":
			cancelarCadastro(request, response);
			break;
		case "":
		default:
			request.getRequestDispatcher("WEB-INF/CadastrarRefeicao.jsp").forward(request, response);
		}
	}

	private void cancelarCadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("ListarRefeicoes").forward(request, response);
	}
	
	private void cadastrarRefeicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Turno turno = null;
		TipoRefeicao tipo = null;
		String descricao = request.getParameter("descricao");
		String opcaoVegetariana = request.getParameter("opcaoVegetariana");
		//String data = request.getParameter("data");
		String dia = request.getParameter("dia");
		String mes = request.getParameter("mes");
		String ano = request.getParameter("ano");
		
		try {
			turno = Turno.valueOf(request.getParameter("turno"));
			tipo = turno.getTipoRefeicao();
		} catch (IllegalArgumentException e) {
		
		} catch (NullPointerException e) {
			
		}
		
		try {
			
			if(descricao.trim().length()>101) {
				throw new TamanhoDescricaoException();
			}
			
			if(opcaoVegetariana.trim().length()>101) {
				throw new TamanhoOpcaoVegetarianaException();
			}
			String data = ano + "-" + mes + "-" + dia;
			if(turno==null) throw new CampoObrigatorioException("turno");
			tipo = turno.getTipoRefeicao();
			Refeicao refeicao = new Refeicao(turno, tipo, descricao.trim().toUpperCase(), opcaoVegetariana.trim().toUpperCase(), data);
			RefeicaoMapper.getInstance().adicionar(refeicao);
			request.getRequestDispatcher("ListarRefeicoes").forward(request, response);
		} catch (CampoObrigatorioException e) {
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("WEB-INF/CadastrarRefeicao.jsp").forward(request, response);
			e.printStackTrace();
		} catch (TurnoInconsistenteException e) {
			request.setAttribute("erro", "Não foi possível realizar a operação. Verifique os dados inseridos.");
			request.getRequestDispatcher("WEB-INF/CadastrarRefeicao.jsp").forward(request, response);
			e.printStackTrace();
		}  catch (DescricaoInvalidaException e) {
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("WEB-INF/CadastrarRefeicao.jsp").forward(request, response);
			e.printStackTrace();
		} catch (OpcaoVegetarianaInvalidaException e) {
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("WEB-INF/CadastrarRefeicao.jsp").forward(request, response);
			e.printStackTrace();
		} catch (TamanhoDescricaoException e) {
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("WEB-INF/CadastrarRefeicao.jsp").forward(request, response);
			e.printStackTrace();
		} catch (TamanhoOpcaoVegetarianaException e) {
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("WEB-INF/CadastrarRefeicao.jsp").forward(request, response);
			e.printStackTrace();
		} catch (IllegalArgumentException|IllegalAccessException e) {
			request.setAttribute("erro", "Não foi possível realizar a operação. Verifique os dados inseridos.");
			request.getRequestDispatcher("WEB-INF/CadastrarRefeicao.jsp").forward(request, response);
			e.printStackTrace();
		} catch (ParseException e) {
			request.setAttribute("erro", "Data inválida");
			request.getRequestDispatcher("WEB-INF/CadastrarRefeicao.jsp").forward(request, response);
			e.printStackTrace();
		} catch (SQLException e) {
			request.setAttribute("erro", "Não foi possível realizar a operação. Verifique os dados inseridos.");
			request.getRequestDispatcher("WEB-INF/CadastrarRefeicao.jsp").forward(request, response);
			e.printStackTrace();
		} 
	}
}
package controladores;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mapeadores.RefeicaoMapper;
import entidades.Atributo;
import entidades.Refeicao;
import excecoes.CampoInalteravelException;
import excecoes.CampoInexistenteException;
import excecoes.CampoObrigatorioException;
import excecoes.DescricaoInvalidaException;
import excecoes.OpcaoVegetarianaInvalidaException;
import excecoes.RefeicaoInexistenteException;
import excecoes.TamanhoDescricaoException;
import excecoes.TamanhoOpcaoVegetarianaException;
import excecoes.TurnoInconsistenteException;

@WebServlet("/AtualizarRefeicao")
public class AtualizarRefeicao extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = (String)  request.getParameter("acaoAtualizar");
		
		if(acao == null) acao = "";
		
		switch(acao) {
		case "Atualizar":
			atualizarRefeicao(request, response);
			break;
		case "Cancelar":
			cancelarAtualizacao(request, response);
			break;
		case "Voltar":
			listarRefeicoes(request, response);
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
				request.setAttribute("erro", "Refeição não foi informada");
				request.getRequestDispatcher("WEB-INF/AtualizarRefeicao.jsp").forward(request, response);
				e.printStackTrace();
			} catch (NullPointerException e) {
				request.setAttribute("erro", "Refeição não foi informada");
				request.getRequestDispatcher("WEB-INF/AtualizarRefeicao.jsp").forward(request, response);
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				request.setAttribute("erro", "Refeição não foi informada");
				request.getRequestDispatcher("WEB-INF/AtualizarRefeicao.jsp").forward(request, response);
				e.printStackTrace();
			} catch (SQLException e) {
				request.setAttribute("erro", "Não foi possível realizar a operação. Verifique os dados inseridos.");
				request.getRequestDispatcher("WEB-INF/AtualizarRefeicao.jsp").forward(request, response);
				e.printStackTrace();
			} finally {
				request.setAttribute("refeicao antiga", refeicao);
				request.getRequestDispatcher("WEB-INF/AtualizarRefeicao.jsp").forward(request, response);
			}
		}
	}
	
	private void atualizarRefeicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long id = Long.valueOf(request.getParameter("id"));
		String descricao = (String) request.getParameter("descricao");
		String opcaoVegetariana = (String) request.getParameter("opcaoVegetariana");
		
		Collection<Atributo<?>> atributos = new ArrayList<Atributo<?>>();
		atributos.add(new Atributo<String>(String.class, "descricao", descricao.trim().toUpperCase()));
		atributos.add(new Atributo<String>(String.class, "opcaoVegetariana", opcaoVegetariana.trim().toUpperCase()));
		
		try {
			
			if(descricao.trim().length()>99) {
				throw new TamanhoDescricaoException();
			}
			
			if(opcaoVegetariana.trim().length()>99) {
				throw new TamanhoOpcaoVegetarianaException();
			}
			
			Refeicao refeicao = RefeicaoMapper.getInstance().encontrarPorId(id);
			refeicao.atualizar(atributos);
			RefeicaoMapper.getInstance().atualizar(refeicao);
			request.getRequestDispatcher("ListarRefeicoes").forward(request, response);
		} catch (CampoObrigatorioException e) {
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("WEB-INF/AtualizarRefeicao.jsp").forward(request, response);
			e.printStackTrace();
		} catch (TurnoInconsistenteException e) {
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("WEB-INF/AtualizarRefeicao.jsp").forward(request, response);
			e.printStackTrace();
		} catch (DescricaoInvalidaException e) {
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("WEB-INF/AtualizarRefeicao.jsp").forward(request, response);
			e.printStackTrace();
		} catch (OpcaoVegetarianaInvalidaException e) {
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("WEB-INF/AtualizarRefeicao.jsp").forward(request, response);
			e.printStackTrace();
		} catch (CampoInalteravelException e) {
			request.setAttribute("erro", "Não foi possível realizar a operação. Verifique os dados inseridos.");
			request.getRequestDispatcher("WEB-INF/AtualizarRefeicao.jsp").forward(request, response);
			e.printStackTrace();
		} catch (CampoInexistenteException e) {
			request.setAttribute("erro", "Não foi possível realizar a operação. Verifique os dados inseridos.");
			request.getRequestDispatcher("WEB-INF/AtualizarRefeicao.jsp").forward(request, response);
			e.printStackTrace();
		} catch (TamanhoDescricaoException e) {
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("WEB-INF/AtualizarRefeicao.jsp").forward(request, response);
			e.printStackTrace();
		} catch (TamanhoOpcaoVegetarianaException e) {
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("WEB-INF/AtualizarRefeicao.jsp").forward(request, response);
			e.printStackTrace();
		} catch (NoSuchFieldError|NoSuchFieldException|SecurityException|IllegalArgumentException|IllegalAccessException|ParseException e) {
			request.setAttribute("erro", "Não foi possível realizar a operação. Verifique os dados inseridos.");
			request.getRequestDispatcher("WEB-INF/AtualizarRefeicao.jsp").forward(request, response);
			e.printStackTrace();
		} catch (SQLException e) {
			request.setAttribute("erro", "Não foi possível realizar a operação. Verifique os dados inseridos.");
			request.getRequestDispatcher("WEB-INF/AtualizarRefeicao.jsp").forward(request, response);
			e.printStackTrace();
		}	
	}

	private void listarRefeicoes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("ListarRefeicoes").forward(request, response);		
	}

	private void cancelarAtualizacao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("ListarRefeicoes").forward(request, response);
	}
}
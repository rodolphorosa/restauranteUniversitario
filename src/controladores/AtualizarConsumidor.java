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

import mapeadores.ConsumidorMapper;
import entidades.Atributo;
import entidades.Consumidor;
import entidades.Sexo;
import excecoes.AnoIngressoException;
import excecoes.CampoInalteravelException;
import excecoes.CampoObrigatorioException;
import excecoes.ConsumidorInexistenteException;
import excecoes.CpfInvalidoException;
import excecoes.MatriculaCadastradaException;
import excecoes.MatriculaInvalidaException;
import excecoes.NomeInvalidoException;

@WebServlet("/AtualizarConsumidor")
public class AtualizarConsumidor extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
		String acaoAtualizar = (String) request.getParameter("acaoAtualizar");
		
		if(acaoAtualizar==null) {
			acaoAtualizar = "";
		}
		
		switch(acaoAtualizar) {
		case "Atualizar":
			atualizarConsumidor(request, response);
			break;
		case "Cancelar":
			cancelarAtualizacao(request, response);
			break;
		case "Voltar":
			listarConsumidores(request, response);
			break;
		default:
			Consumidor consumidor = null;
			try {
				String cpf = (String) request.getParameter("cpf");
				consumidor = ConsumidorMapper.getInstance().encontrarPorCpf(cpf);
				
				if(consumidor==null) {
					throw new ConsumidorInexistenteException();
				}
				
			} catch (ConsumidorInexistenteException e) {
				request.setAttribute("erro", "Consumidor não informado!");
				request.getRequestDispatcher("WEB-INF/AtualizarConsumidor.jsp").forward(request, response);
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				request.setAttribute("consumidor antigo", consumidor);
				request.getRequestDispatcher("WEB-INF/AtualizarConsumidor.jsp").forward(request, response);
			}
		}
	}

	private void listarConsumidores(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("ListarConsumidores").forward(request, response);
	}

	private void cancelarAtualizacao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("ListarConsumidores").forward(request, response);
	}
	
	private void atualizarConsumidor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cpf = (String) request.getParameter("cpf").trim();
		String nome = (String) request.getParameter("nome").trim();
		String matricula = (String) request.getParameter("matricula").trim();
		Sexo sexo = Sexo.valueOf(request.getParameter("sexo"));
		int ano = 0;
		
		try {
			ano = Integer.valueOf(request.getParameter("anoIngresso"));
		} catch (NumberFormatException e) {
			request.setAttribute("erro", "Campo obrigatório não informado: ano");
			request.getRequestDispatcher("WEB-INF/AtualizarConsumidor.jsp").forward(request, response);
			e.printStackTrace();
		}
		
		Collection<Atributo<?>> atributos = new ArrayList<Atributo<?>>();
		atributos.add(new Atributo<String>(String.class, "nome", nome.trim().toUpperCase()));
		atributos.add(new Atributo<String>(String.class, "matricula", matricula.trim()));
		atributos.add(new Atributo<Sexo>(Sexo.class, "sexo", sexo));
		atributos.add(new Atributo<Integer>(int.class, "anoIngresso", ano));
		
			try {
				Consumidor consumidor = ConsumidorMapper.getInstance().encontrarPorMatricula(matricula);
				
				if(consumidor!=null) {
					if(!consumidor.getMapeadorAtributos().get("cpf").equals(cpf))
						throw new MatriculaCadastradaException();
				}
				
				consumidor = ConsumidorMapper.getInstance().encontrarPorCpf(cpf);
				
				if(consumidor == null) {
					throw new ConsumidorInexistenteException();
				}				
				consumidor.atualizar(atributos);
				ConsumidorMapper.getInstance().atualizar(consumidor);
				request.getRequestDispatcher("ListarConsumidores").forward(request, response);
			} catch (IllegalArgumentException |IllegalAccessException | NoSuchFieldException | SecurityException e) {
				request.setAttribute("erro", "Não foi possível realizar a operação. Verifique os dados inseridos.");
				request.getRequestDispatcher("WEB-INF/AtualizarConsumidor.jsp").forward(request, response);
				e.printStackTrace();
			} catch (CampoInalteravelException e) {
				request.setAttribute("erro", "Não foi possível realizar a operação. Verifique os dados inseridos.");
				request.getRequestDispatcher("WEB-INF/AtualizarConsumidor.jsp").forward(request, response);
				e.printStackTrace();
			} catch (CampoObrigatorioException e) {
				request.setAttribute("erro", e.getMessage());
				request.getRequestDispatcher("WEB-INF/AtualizarConsumidor.jsp").forward(request, response);
				e.printStackTrace();
			} catch (CpfInvalidoException e) {
				request.setAttribute("erro", e.getMessage());
				request.getRequestDispatcher("WEB-INF/AtualizarConsumidor.jsp").forward(request, response);
				e.printStackTrace();
			} catch (AnoIngressoException e) {
				request.setAttribute("erro", e.getMessage());
				request.getRequestDispatcher("WEB-INF/AtualizarConsumidor.jsp").forward(request, response);
				e.printStackTrace();
			} catch (MatriculaInvalidaException e) {
				request.setAttribute("erro", e.getMessage());
				request.getRequestDispatcher("WEB-INF/AtualizarConsumidor.jsp").forward(request, response);
				e.printStackTrace();
			} catch (ConsumidorInexistenteException e) {
				request.setAttribute("erro", e.getMessage());
				request.getRequestDispatcher("WEB-INF/AtualizarConsumidor.jsp").forward(request, response);
				e.printStackTrace();
			} catch (MatriculaCadastradaException e) {
				request.setAttribute("erro", e.getMessage());
				request.getRequestDispatcher("WEB-INF/AtualizarConsumidor.jsp").forward(request, response);
				e.printStackTrace();
			} catch (NomeInvalidoException e) {
				request.setAttribute("erro", e.getMessage());
				request.getRequestDispatcher("WEB-INF/AtualizarConsumidor.jsp").forward(request, response);
				e.printStackTrace();
			} catch (SQLException e) {
				request.setAttribute("erro", "Não foi possível realizar a operação. Verifique os dados inseridos.");
				request.getRequestDispatcher("WEB-INF/AtualizarConsumidor.jsp").forward(request, response);
				e.printStackTrace();
			}
	}
}
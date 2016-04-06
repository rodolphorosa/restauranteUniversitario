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
import mapeadores.CursoMapper;
import mapeadores.DepartamentoMapper;
import entidades.Aluno;
import entidades.Consumidor;
import entidades.Curso;
import entidades.Departamento;
import entidades.Funcionario;
import entidades.Sexo;
import entidades.TipoConsumidor;
import entidades.Titulo;
import excecoes.AnoIngressoException;
import excecoes.CampoObrigatorioException;
import excecoes.CpfCadastradoException;
import excecoes.CpfInvalidoException;
import excecoes.MatriculaCadastradaException;
import excecoes.MatriculaInvalidaException;
import excecoes.NomeInvalidoException;

@WebServlet("/CadastrarConsumidor")
public class CadastrarConsumidor extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = (String) request.getParameter("acaoCadastroConsumidor");
		Collection<Departamento> dptos = new ArrayList<Departamento>();
		Collection<Curso> cursos = new ArrayList<Curso>();
		try {		
			dptos = DepartamentoMapper.getInstance().encontrarTodos();
			cursos = CursoMapper.getInstance().encontrarTodos();
		} catch (SQLException e) {
			e.printStackTrace();			
		}
		request.setAttribute("listaDepartamentos", dptos);
		request.setAttribute("listaCursos", cursos);
		
		if(acao!=null) {		
			switch (acao) {
			case "Cadastrar":
				cadastrarConsumidor(request, response);
				break;
			default:
				cancelarCadastro(request, response);
			}
		} else {
			request.getRequestDispatcher("WEB-INF/CadastrarConsumidor.jsp").forward(request, response);
		}
	}
	
	private void cadastrarConsumidor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long cursoId = (long) 0;
		Long dptoId = (long) 0;
		Sexo sexo = null;
		Titulo titulo = null;
		TipoConsumidor tipo = null;		
		String nome = (String) request.getParameter("nome").trim();
		String cpf = (String) request.getParameter("cpf").trim();
		String matricula = (String) request.getParameter("matricula").trim();
		int anoIngresso = 0;
		
		try {
			if(request.getParameter("anoIngresso").equals(""))
				throw new CampoObrigatorioException("ano de ingresso");
			anoIngresso = Integer.valueOf((String) request.getParameter("anoIngresso"));
		} catch (NumberFormatException e) {
			request.setAttribute("erro", "Ano inválido");
			request.getRequestDispatcher("WEB-INF/CadastrarConsumidor.jsp").forward(request, response);
			e.printStackTrace();
		} catch (CampoObrigatorioException e) {
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("WEB-INF/CadastrarConsumidor.jsp").forward(request, response);
			e.printStackTrace();
		}
		
		try {
			sexo = Sexo.valueOf((String)request.getParameter("sexo"));
		} catch (IllegalArgumentException e) {
			request.setAttribute("erro", "Campo obrigatório não informado: sexo");
			request.getRequestDispatcher("WEB-INF/CadastrarConsumidor.jsp").forward(request, response);
			e.printStackTrace();
		} catch (NullPointerException e) {
			request.setAttribute("erro", "Campo obrigatório não informado: sexo");
			request.getRequestDispatcher("WEB-INF/CadastrarConsumidor.jsp").forward(request, response);
			e.printStackTrace();
		}
		
		try {
			titulo = Titulo.valueOf((String)request.getParameter("titulo"));
		} catch (IllegalArgumentException e) {
			request.setAttribute("erro", "Campo obrigatório não informado: titulo");
			request.getRequestDispatcher("WEB-INF/CadastrarConsumidor.jsp").forward(request, response);
			e.printStackTrace();
		} catch (NullPointerException e) {
			request.setAttribute("erro", "Campo obrigatório não informado: titulo");
			request.getRequestDispatcher("WEB-INF/CadastrarConsumidor.jsp").forward(request, response);
			e.printStackTrace();
		}
		
		try {
			tipo = TipoConsumidor.valueOf((String)request.getParameter("tipo"));
		} catch (IllegalArgumentException e) {
			request.setAttribute("erro", "Campo obrigatório não informado: ocupação");
			request.getRequestDispatcher("WEB-INF/CadastrarConsumidor.jsp").forward(request, response);
			e.printStackTrace();
		} catch (NullPointerException e) {
			request.setAttribute("erro", "Campo obrigatório não informado: ocupação");
			request.getRequestDispatcher("WEB-INF/CadastrarConsumidor.jsp").forward(request, response);
			e.printStackTrace();
		}
		
		try {
			cursoId = Long.valueOf(request.getParameter("listaCursos").toString());
		} catch (IllegalArgumentException | NullPointerException e) {
			e.printStackTrace();
		}
		
		try {
			dptoId = Long.valueOf(request.getParameter("listaDepartamentos").toString());
		} catch (IllegalArgumentException | NullPointerException e) {
			
		}
		
		try {
			if(ConsumidorMapper.getInstance().encontrarPorCpf(cpf)!=null) {
				throw new CpfCadastradoException();
			} else if (ConsumidorMapper.getInstance().encontrarPorMatricula(matricula)!=null) {
				throw new MatriculaCadastradaException();
			}
			
			if(tipo.name().equals(TipoConsumidor.ALUNO.name())) {
				Curso curso = CursoMapper.getInstance().encontrarPorId(cursoId);
				adicionarConsumidor(request, response, new Aluno(nome.trim().toUpperCase(), cpf, matricula, sexo, anoIngresso, titulo, curso));
			} else if(tipo.name().equals(TipoConsumidor.FUNCIONARIO.name())) {
				Departamento departamento = DepartamentoMapper.getInstance().encontrarPorId(dptoId);
				adicionarConsumidor(request, response, new Funcionario(nome.trim().toUpperCase(), cpf, matricula, sexo, anoIngresso, titulo, departamento));
			}
		} catch (CampoObrigatorioException e) {
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("WEB-INF/CadastrarConsumidor.jsp").forward(request, response);
			e.printStackTrace();
		} catch (IllegalArgumentException | IllegalAccessException e) {
			request.setAttribute("erro", "Não foi possível realizar a operação. Verifique os dados inseridos.");
			request.getRequestDispatcher("WEB-INF/CadastrarRefeicao.jsp").forward(request, response);
			e.printStackTrace();
		} catch (CpfInvalidoException e) {
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("WEB-INF/CadastrarConsumidor.jsp").forward(request, response);
			e.printStackTrace();
		} catch (AnoIngressoException e) {
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("WEB-INF/CadastrarConsumidor.jsp").forward(request, response);
			e.printStackTrace();
		} catch (MatriculaInvalidaException e) {
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("WEB-INF/CadastrarConsumidor.jsp").forward(request, response);
			e.printStackTrace();
		} catch (CpfCadastradoException e) {
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("WEB-INF/CadastrarConsumidor.jsp").forward(request, response);
			e.printStackTrace();
		} catch (MatriculaCadastradaException e) {
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("WEB-INF/CadastrarConsumidor.jsp").forward(request, response);
			e.printStackTrace();
		} catch (NomeInvalidoException e) {
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("WEB-INF/CadastrarConsumidor.jsp").forward(request, response);
			e.printStackTrace();
		} catch (SQLException e) {
			request.setAttribute("erro", "Não foi possível realizar a operação. Verifique os dados inseridos.");
			request.getRequestDispatcher("WEB-INF/CadastrarRefeicao.jsp").forward(request, response);
			e.printStackTrace();
		}
	}
	
	private void cancelarCadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("ListarConsumidores").forward(request, response);
	}
	
	private void adicionarConsumidor(HttpServletRequest request, HttpServletResponse response, Consumidor consumidor) throws ServletException, IOException {		
		
		try {
			ConsumidorMapper.getInstance().adicionar(consumidor);
			request.getRequestDispatcher("ListarConsumidores").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("WEB-INF/CadastrarConsumidor.jsp").forward(request, response);
		}
	}
}
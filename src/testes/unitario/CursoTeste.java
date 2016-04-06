package testes.unitario;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entidades.Atributo;
import entidades.Curso;
import entidades.Departamento;
import excecoes.CampoInalteravelException;
import excecoes.CampoInexistenteException;
import excecoes.CampoObrigatorioException;
import excecoes.NomeInvalidoException;
import excecoes.TurnoInconsistenteException;

public class CursoTeste {
	
	private Departamento departamento;

	@Before
	public void criaDepartamento() throws CampoObrigatorioException, IllegalArgumentException, IllegalAccessException, NomeInvalidoException {
		departamento = new Departamento("Ccomp", "Departamento de Ciências da Computação");
	}
	
	@Test(expected=CampoObrigatorioException.class)
	public void campoSiglaNulo() throws CampoObrigatorioException, IllegalArgumentException, IllegalAccessException, NomeInvalidoException {
		new Curso(null, "Ciências da Computação", departamento);
	}
	
	@Test(expected=CampoObrigatorioException.class)
	public void campoSiglaVazio() throws CampoObrigatorioException, IllegalArgumentException, IllegalAccessException, NomeInvalidoException {
		new Curso("", "Ciências da Computação", departamento);
	}
	
	@Test(expected=CampoObrigatorioException.class)
	public void campoSiglaSomenteEspacos() throws CampoObrigatorioException, IllegalArgumentException, IllegalAccessException, NomeInvalidoException {
		new Curso(" \t \n \t ", "Ciências da Computação", departamento);
	}
	
	@Test(expected=CampoObrigatorioException.class)
	public void campoNomeNulo() throws CampoObrigatorioException, IllegalArgumentException, IllegalAccessException, NomeInvalidoException {
		new Curso("Ccomp", null, departamento);
	}
	
	@Test(expected=CampoObrigatorioException.class)
	public void campoNomeVazio() throws CampoObrigatorioException, IllegalArgumentException, IllegalAccessException, NomeInvalidoException {
		new Curso("Ccomp", "", departamento);
	}
	
	@Test(expected=CampoObrigatorioException.class)
	public void campoNomeSomenteEspacos() throws CampoObrigatorioException, IllegalArgumentException, IllegalAccessException, NomeInvalidoException {
		new Curso("Ccomp", " \t \n \t ", departamento);
	}
	
	@Test(expected=CampoObrigatorioException.class)
	public void campoDepartamentoNulo() throws CampoObrigatorioException, IllegalArgumentException, IllegalAccessException, NomeInvalidoException {
		new Curso("Ccomp", "Ciências da Computação", null);
	}
	
	@Test
	public void mapeadorArtibutosNulo() throws CampoObrigatorioException, IllegalArgumentException, IllegalAccessException, NomeInvalidoException {
		Curso curso = new Curso("Ccomp", "Ciências da Computação", departamento);
		
		Assert.assertNotEquals(curso.getMapeadorAtributos(), null);
	}
	
	@Test
	public void tudoCerto() throws CampoObrigatorioException, IllegalArgumentException, IllegalAccessException, NomeInvalidoException {
		
		new Curso("Ccomp", "Ciências da Computação", departamento);
	}
	
	@Test(expected = CampoInalteravelException.class)
	public void atualizarComCampoNulo() throws CampoObrigatorioException,
			TurnoInconsistenteException, CampoInalteravelException,
			CampoInexistenteException, ParseException, IllegalArgumentException, IllegalAccessException, NoSuchFieldError, NoSuchFieldException, SecurityException, NomeInvalidoException {

		Collection<Atributo<?>> atributos = new ArrayList<Atributo<?>>();
		
		atributos.add(new Atributo<String>(String.class, "sigla", null));
		
		Curso curso = new Curso("Ccomp", "Ciências da Computação", departamento);

		curso.atualizar(atributos);
	}

	@Test(expected = NoSuchFieldException.class)
	public void atualizarComCampoInexistente()
			throws CampoObrigatorioException, TurnoInconsistenteException,
			CampoInalteravelException, CampoInexistenteException, ParseException, IllegalArgumentException, IllegalAccessException, NoSuchFieldError, NoSuchFieldException, SecurityException, NomeInvalidoException {

		Collection<Atributo<?>> atributos = new ArrayList<Atributo<?>>();
		
		atributos.add(new Atributo<String>(String.class, "sigls", null));
		
		Curso curso = new Curso("Ccomp", "Ciências da Computação", departamento);

		curso.atualizar(atributos);
	}

	@Test(expected = CampoInalteravelException.class)
	public void atualizarComCampoVazio()
			throws CampoObrigatorioException, TurnoInconsistenteException,
			CampoInalteravelException, CampoInexistenteException, ParseException, IllegalArgumentException, IllegalAccessException, NoSuchFieldError, NoSuchFieldException, SecurityException, NomeInvalidoException {

		Collection<Atributo<?>> atributos = new ArrayList<Atributo<?>>();
		
		atributos.add(new Atributo<String>(String.class, "sigla", ""));
		
		Curso curso = new Curso("Ccomp", "Ciências da Computação", departamento);

		curso.atualizar(atributos);
	}
}

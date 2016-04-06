package testes.unitario;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

import entidades.Atributo;
import entidades.Departamento;
import excecoes.CampoInalteravelException;
import excecoes.CampoInexistenteException;
import excecoes.CampoObrigatorioException;
import excecoes.NomeInvalidoException;
import excecoes.TurnoInconsistenteException;

public class DepartamentoTeste {
	
	@Test(expected=CampoObrigatorioException.class)
	public void campoSiglaNulo() throws CampoObrigatorioException, IllegalArgumentException, IllegalAccessException, NomeInvalidoException {
		new Departamento(null, "Departamento de Tecnologias e Linguagens");
	}
	
	@Test(expected=CampoObrigatorioException.class)
	public void campoSiglaVazio() throws CampoObrigatorioException, IllegalArgumentException, IllegalAccessException, NomeInvalidoException {
		new Departamento("", "Departamento de Tecnologias e Linguagens");
	}
	
	@Test(expected=CampoObrigatorioException.class)
	public void campoSiglaSomenteEspacos() throws CampoObrigatorioException, IllegalArgumentException, IllegalAccessException, NomeInvalidoException {
		new Departamento("\t \n \t \t\t\n ", "Departamento de Tecnologias e Linguagens");
	}
	
	@Test(expected=CampoObrigatorioException.class)
	public void campoNomeNulo() throws CampoObrigatorioException, IllegalArgumentException, IllegalAccessException, NomeInvalidoException {
		new Departamento("DTL", null);
	}
	
	@Test(expected=CampoObrigatorioException.class)
	public void campoNomeVazio() throws CampoObrigatorioException, IllegalArgumentException, IllegalAccessException, NomeInvalidoException {
		new Departamento("DTL", "");
	}
	
	@Test(expected=CampoObrigatorioException.class)
	public void campoNomeSomenteEspacos() throws CampoObrigatorioException, IllegalArgumentException, IllegalAccessException, NomeInvalidoException {
		new Departamento("DTL", "\t \n\t \n");
	}
	
	@Test
	public void mapeadorArtibutosNulo() throws CampoObrigatorioException, IllegalArgumentException, IllegalAccessException, NomeInvalidoException {
		Departamento departamento = new Departamento("DTL", "Departamento de Tecnologias e Linguagens");
		
		Assert.assertNotEquals(departamento.getMapeadorAtributos(), null);
	}
	
	@Test
	public void tudoCerto() throws CampoObrigatorioException, IllegalArgumentException, IllegalAccessException, NomeInvalidoException {
		new Departamento("DTL", "Departamento de Tecnologias e Linguagens");
	}
	
	@Test(expected = CampoInalteravelException.class)
	public void atualizarComCampoNulo() throws CampoObrigatorioException,
			TurnoInconsistenteException, CampoInalteravelException,
			CampoInexistenteException, ParseException, IllegalArgumentException, IllegalAccessException, NoSuchFieldError, NoSuchFieldException, SecurityException, NomeInvalidoException {

		Collection<Atributo<?>> atributos = new ArrayList<Atributo<?>>();
		
		atributos.add(new Atributo<String>(String.class, "sigla", null));
		
		Departamento departamento = new Departamento("DTL", "Departamento de Tecnologias e Linguagens");

		departamento.atualizar(atributos);
	}

	@Test(expected = NoSuchFieldException.class)
	public void atualizarComCampoInexistente()
			throws CampoObrigatorioException, TurnoInconsistenteException,
			CampoInalteravelException, CampoInexistenteException, ParseException, NoSuchFieldError, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, NomeInvalidoException {

		Collection<Atributo<?>> atributos = new ArrayList<Atributo<?>>();
		
		atributos.add(new Atributo<String>(String.class, "sigls", "DTLE"));
		
		Departamento departamento = new Departamento("DTL", "Departamento de Tecnologias e Linguagens");

		departamento.atualizar(atributos);
	}

	@Test(expected = CampoObrigatorioException.class)
	public void atualizarComCampoVazio()
			throws CampoObrigatorioException, TurnoInconsistenteException,
			CampoInalteravelException, CampoInexistenteException, ParseException, NoSuchFieldError, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, NomeInvalidoException {

		Collection<Atributo<?>> atributos = new ArrayList<Atributo<?>>();
		
		atributos.add(new Atributo<String>(String.class, "sigla", ""));
		
		Departamento departamento = new Departamento("", "Departamento de Tecnologias e Linguagens");

		departamento.atualizar(atributos);
	}
}

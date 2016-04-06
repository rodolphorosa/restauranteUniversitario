package testes.unitario;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entidades.Departamento;
import entidades.Funcionario;
import entidades.Sexo;
import entidades.Titulo;
import excecoes.AnoIngressoException;
import excecoes.CampoObrigatorioException;
import excecoes.CpfInvalidoException;
import excecoes.MatriculaInvalidaException;
import excecoes.NomeInvalidoException;

public class FuncionarioTeste {
	
	private Departamento departamento;

	@Before
	public void criaDepartamento() throws CampoObrigatorioException, IllegalArgumentException, IllegalAccessException, NomeInvalidoException {
		departamento = new Departamento("DTL", "Departamento de Tecnologias e Linguagens");
	}
	
	@Test(expected=CampoObrigatorioException.class)
	public void campoNomeNulo() throws CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, IllegalArgumentException, IllegalAccessException, MatriculaInvalidaException, NomeInvalidoException {
		
		new Funcionario(null, "17499789381", "2012780027", Sexo.MASCULINO, 2012, Titulo.ESPECIALIZACAO, departamento);
	}
	
	@Test(expected=CampoObrigatorioException.class)
	public void campoNomeSomenteEspacos() throws CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, IllegalArgumentException, IllegalAccessException, MatriculaInvalidaException, NomeInvalidoException {
		
		new Funcionario(" \t\n\t\n ", "17499789381", "2012780027", Sexo.MASCULINO, 2012, Titulo.ESPECIALIZACAO, departamento);
	}
	
	@Test(expected=CampoObrigatorioException.class)
	public void campoNomeVazio() throws CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, IllegalArgumentException, IllegalAccessException, MatriculaInvalidaException, NomeInvalidoException {
		
		new Funcionario("", "17499789381", "2012780027", Sexo.MASCULINO, 2012, Titulo.ESPECIALIZACAO, departamento);
	}
	
	@Test(expected=CampoObrigatorioException.class)
	public void campoCPFNulo() throws CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, IllegalArgumentException, IllegalAccessException, MatriculaInvalidaException, NomeInvalidoException {
		
		new Funcionario("Cassiano Honorio da Silva", null, "2012780027", Sexo.MASCULINO, 2012, Titulo.ESPECIALIZACAO, departamento);
	}
	
	@Test(expected=CampoObrigatorioException.class)
	public void campoCPFSomenteEspacos() throws CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, IllegalArgumentException, IllegalAccessException, MatriculaInvalidaException, NomeInvalidoException {
		
		new Funcionario("Cassiano Honorio da Silva", " \t\n\t\n ", "2012780027", Sexo.MASCULINO, 2012, Titulo.ESPECIALIZACAO, departamento);
	}
	
	@Test(expected=CampoObrigatorioException.class)
	public void campoCPFVazio() throws CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, IllegalArgumentException, IllegalAccessException, MatriculaInvalidaException, NomeInvalidoException {
		
		new Funcionario("Cassiano Honorio da Silva", "", "2012780027", Sexo.MASCULINO, 2012, Titulo.ESPECIALIZACAO, departamento);
	}
	
	@Test(expected=CpfInvalidoException.class)
	public void campoCPFInvalido() throws CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, IllegalArgumentException, IllegalAccessException, MatriculaInvalidaException, NomeInvalidoException {
		
		new Funcionario("Cassiano Honorio da Silva", "17499289381", "2012780027", Sexo.MASCULINO, 2012, Titulo.ESPECIALIZACAO, departamento);
	}
	
	@Test(expected=CpfInvalidoException.class)
	public void campoCPFComMenosCaracteres() throws CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, IllegalArgumentException, IllegalAccessException, MatriculaInvalidaException, NomeInvalidoException {
		
		new Funcionario("Cassiano Honorio da Silva", "1749928", "2012780027", Sexo.MASCULINO, 2012, Titulo.ESPECIALIZACAO, departamento);
	}
	
	@Test(expected=CpfInvalidoException.class)
	public void campoCPFComMaisCaracteres() throws CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, IllegalArgumentException, IllegalAccessException, MatriculaInvalidaException, NomeInvalidoException {
		
		new Funcionario("Cassiano Honorio da Silva", "174997893811", "2012780027", Sexo.MASCULINO, 2012, Titulo.ESPECIALIZACAO, departamento);
	}
	
	@Test(expected=CpfInvalidoException.class)
	public void campoCPFComCaracteresNaoNumericos() throws CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, IllegalArgumentException, IllegalAccessException, MatriculaInvalidaException, NomeInvalidoException {
		
		new Funcionario("Cassiano Honorio da Silva", "94\0478\t0464", "2012780027", Sexo.MASCULINO, 2012, Titulo.ESPECIALIZACAO, departamento);
	}
	
	@Test(expected=CampoObrigatorioException.class)
	public void campoMatriculaNulo() throws CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, IllegalArgumentException, IllegalAccessException, MatriculaInvalidaException, NomeInvalidoException {
		
		new Funcionario("Cassiano Honorio da Silva", "17499789381", null, Sexo.MASCULINO, 2012, Titulo.ESPECIALIZACAO, departamento);
	}
	
	@Test(expected=CampoObrigatorioException.class)
	public void campoMatriculaSomenteEspacos() throws CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, IllegalArgumentException, IllegalAccessException, MatriculaInvalidaException, NomeInvalidoException {
		
		new Funcionario("Cassiano Honorio da Silva", "17499789381", " \t\n\t\n ", Sexo.MASCULINO, 2012, Titulo.ESPECIALIZACAO, departamento);
	}
	
	@Test(expected=CampoObrigatorioException.class)
	public void campoMatriculaVazio() throws CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, IllegalArgumentException, IllegalAccessException, MatriculaInvalidaException, NomeInvalidoException {
		
		new Funcionario("Cassiano Honorio da Silva", "17499789381", "", Sexo.MASCULINO, 2012, Titulo.ESPECIALIZACAO, departamento);
	}
	
	@Test(expected=CampoObrigatorioException.class)
	public void campoSexoNulo() throws CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, IllegalArgumentException, IllegalAccessException, MatriculaInvalidaException, NomeInvalidoException {
		
		new Funcionario("Cassiano Honorio da Silva", "17499789381", "2012780027", null, 2012, Titulo.ESPECIALIZACAO, departamento);
	}
	
	@Test(expected=AnoIngressoException.class)
	public void campoAnoZerado() throws CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, IllegalArgumentException, IllegalAccessException, MatriculaInvalidaException, NomeInvalidoException {
		
		new Funcionario("Cassiano Honorio da Silva", "17499789381", "2012780027", Sexo.MASCULINO, 000, Titulo.ESPECIALIZACAO, departamento);
	}
	
	@Test(expected=AnoIngressoException.class)
	public void campoAnoMaiorQueAtual() throws CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, IllegalArgumentException, IllegalAccessException, MatriculaInvalidaException, NomeInvalidoException {
		
		new Funcionario("Cassiano Honorio da Silva", "17499789381", "2012780027", Sexo.MASCULINO, 2016, Titulo.ESPECIALIZACAO, departamento);
	}
	
	@Test(expected=AnoIngressoException.class)
	public void campoAnoMenorQueMinimo() throws CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, IllegalArgumentException, IllegalAccessException, MatriculaInvalidaException, NomeInvalidoException {
		
		new Funcionario("Cassiano Honorio da Silva", "17499789381", "2012780027", Sexo.MASCULINO, 1899, Titulo.ESPECIALIZACAO, departamento);
	}
	
	@Test(expected=CampoObrigatorioException.class)
	public void campoTituloNulo() throws CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, IllegalArgumentException, IllegalAccessException, MatriculaInvalidaException, NomeInvalidoException {
		
		new Funcionario("Cassiano Honorio da Silva", "17499789381", "2012780027", Sexo.MASCULINO, 2012, null, departamento);
	}
	
	@Test(expected=CampoObrigatorioException.class)
	public void campoDepartamentoNulo() throws CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, IllegalArgumentException, IllegalAccessException, MatriculaInvalidaException, NomeInvalidoException {
		
		new Funcionario("Cassiano Honorio da Silva", "17499789381", "2012780027", Sexo.MASCULINO, 2015, Titulo.ESPECIALIZACAO, null);
	}
	
	@Test
	public void mapeadorArtibutosNulo() throws CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, IllegalArgumentException, IllegalAccessException, MatriculaInvalidaException, NomeInvalidoException {
		Funcionario funcionario = new Funcionario("Cassiano Honorio da Silva", "17499789381", "2012780027", Sexo.MASCULINO, 2015, Titulo.ESPECIALIZACAO, departamento);
		
		Assert.assertNotEquals(funcionario.getMapeadorAtributos(), null);
	}
	
	@Test
	public void tudoCerto() throws CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, IllegalArgumentException, IllegalAccessException, MatriculaInvalidaException, NomeInvalidoException {
		
		new Funcionario("Cassiano Honorio da Silva", "17499789381", "2012780027", Sexo.MASCULINO, 2015, Titulo.ESPECIALIZACAO, departamento);
	}
}

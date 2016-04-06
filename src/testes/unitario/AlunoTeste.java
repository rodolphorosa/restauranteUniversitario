package testes.unitario;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entidades.Aluno;
import entidades.Curso;
import entidades.Departamento;
import entidades.Sexo;
import entidades.Titulo;
import excecoes.AnoIngressoException;
import excecoes.CampoObrigatorioException;
import excecoes.CpfInvalidoException;
import excecoes.MatriculaInvalidaException;
import excecoes.NomeInvalidoException;

public class AlunoTeste {
	
	private Curso curso;

	@Before
	public void criaCurso() throws CampoObrigatorioException, IllegalArgumentException, IllegalAccessException, NomeInvalidoException {
		Departamento departamento = new Departamento("DTL", "Departamento de Tecnologias e Linguagens");
		
		curso = new Curso("Ccomp", "Ciências da Computação", departamento);
	}
	
	
	@Test(expected=CampoObrigatorioException.class)
	public void campoNomeNulo() throws CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, IllegalArgumentException, IllegalAccessException, MatriculaInvalidaException, NomeInvalidoException {
		
		new Aluno(null, "17499789381", "2012780027", Sexo.MASCULINO, 2012, Titulo.ESPECIALIZACAO, curso);
	}
	
	@Test(expected=CampoObrigatorioException.class)
	public void campoNomeSomenteEspacos() throws CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, IllegalArgumentException, IllegalAccessException, MatriculaInvalidaException, NomeInvalidoException {
		
		new Aluno(" \t\n\t\n ", "17499789381", "2012780027", Sexo.MASCULINO, 2012, Titulo.ESPECIALIZACAO, curso);
	}
	
	@Test(expected=CampoObrigatorioException.class)
	public void campoNomeVazio() throws CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, IllegalArgumentException, IllegalAccessException, MatriculaInvalidaException, NomeInvalidoException {
		
		new Aluno("", "17499789381", "2012780027", Sexo.MASCULINO, 2012, Titulo.ESPECIALIZACAO, curso);
	}
	
	@Test(expected=CampoObrigatorioException.class)
	public void campoCPFNulo() throws CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, IllegalArgumentException, IllegalAccessException, MatriculaInvalidaException, NomeInvalidoException {
		
		new Aluno("Cassiano Honorio da Silva", null, "2012780027", Sexo.MASCULINO, 2012, Titulo.ESPECIALIZACAO, curso);
	}
	
	@Test(expected=CampoObrigatorioException.class)
	public void campoCPFSomenteEspacos() throws CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, IllegalArgumentException, IllegalAccessException, MatriculaInvalidaException, NomeInvalidoException {
		
		new Aluno("Cassiano Honorio da Silva", " \t\n\t\n ", "2012780027", Sexo.MASCULINO, 2012, Titulo.ESPECIALIZACAO, curso);
	}
	
	@Test(expected=CampoObrigatorioException.class)
	public void campoCPFVazio() throws CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, IllegalArgumentException, IllegalAccessException, MatriculaInvalidaException, NomeInvalidoException {
		
		new Aluno("Cassiano Honorio da Silva", "", "2012780027", Sexo.MASCULINO, 2012, Titulo.ESPECIALIZACAO, curso);
	}
	
	@Test(expected=CpfInvalidoException.class)
	public void campoCPFInvalido() throws CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, IllegalArgumentException, IllegalAccessException, MatriculaInvalidaException, NomeInvalidoException {
		
		new Aluno("Cassiano Honorio da Silva", "17499289381", "2012780027", Sexo.MASCULINO, 2012, Titulo.ESPECIALIZACAO, curso);
	}
	
	@Test(expected=CpfInvalidoException.class)
	public void campoCPFComMenosCaracteres() throws CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, IllegalArgumentException, IllegalAccessException, MatriculaInvalidaException, NomeInvalidoException {
		
		new Aluno("Cassiano Honorio da Silva", "1749928", "2012780027", Sexo.MASCULINO, 2012, Titulo.ESPECIALIZACAO, curso);
	}
	
	@Test(expected=CpfInvalidoException.class)
	public void campoCPFComMaisCaracteres() throws CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, IllegalArgumentException, IllegalAccessException, MatriculaInvalidaException, NomeInvalidoException {
		
		new Aluno("Cassiano Honorio da Silva", "174997893811", "2012780027", Sexo.MASCULINO, 2012, Titulo.ESPECIALIZACAO, curso);
	}
	
	@Test(expected=CpfInvalidoException.class)
	public void campoCPFComCaracteresNaoNumericos() throws CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, IllegalArgumentException, IllegalAccessException, MatriculaInvalidaException, NomeInvalidoException {
		
		new Aluno("Cassiano Honorio da Silva", "94\0478\t0464", "2012780027", Sexo.MASCULINO, 2012, Titulo.ESPECIALIZACAO, curso);
	}
	
	@Test(expected=CampoObrigatorioException.class)
	public void campoMatriculaNulo() throws CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, IllegalArgumentException, IllegalAccessException, MatriculaInvalidaException, NomeInvalidoException {
		
		new Aluno("Cassiano Honorio da Silva", "17499789381", null, Sexo.MASCULINO, 2012, Titulo.ESPECIALIZACAO, curso);
	}
	
	@Test(expected=CampoObrigatorioException.class)
	public void campoMatriculaSomenteEspacos() throws CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, IllegalArgumentException, IllegalAccessException, MatriculaInvalidaException, NomeInvalidoException {
		
		new Aluno("Cassiano Honorio da Silva", "17499789381", " \t\n\t\n ", Sexo.MASCULINO, 2012, Titulo.ESPECIALIZACAO, curso);
	}
	
	@Test(expected=CampoObrigatorioException.class)
	public void campoMatriculaVazio() throws CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, IllegalArgumentException, IllegalAccessException, MatriculaInvalidaException, NomeInvalidoException {
		
		new Aluno("Cassiano Honorio da Silva", "17499789381", "", Sexo.MASCULINO, 2012, Titulo.ESPECIALIZACAO, curso);
	}
	
	@Test(expected=CampoObrigatorioException.class)
	public void campoSexoNulo() throws CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, IllegalArgumentException, IllegalAccessException, MatriculaInvalidaException, NomeInvalidoException {
		
		new Aluno("Cassiano Honorio da Silva", "17499789381", "2012780027", null, 2012, Titulo.ESPECIALIZACAO, curso);
	}
	
	@Test(expected=AnoIngressoException.class)
	public void campoAnoZerado() throws CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, IllegalArgumentException, IllegalAccessException, MatriculaInvalidaException, NomeInvalidoException {
		
		new Aluno("Cassiano Honorio da Silva", "17499789381", "2012780027", Sexo.MASCULINO, 000, Titulo.ESPECIALIZACAO, curso);
	}
	
	@Test(expected=AnoIngressoException.class)
	public void campoAnoMaiorQueAtual() throws CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, IllegalArgumentException, IllegalAccessException, MatriculaInvalidaException, NomeInvalidoException {
		
		new Aluno("Cassiano Honorio da Silva", "17499789381", "2012780027", Sexo.MASCULINO, 2016, Titulo.ESPECIALIZACAO, curso);
	}
	
	@Test(expected=AnoIngressoException.class)
	public void campoAnoMenorQueMinimo() throws CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, IllegalArgumentException, IllegalAccessException, MatriculaInvalidaException, NomeInvalidoException {
		
		new Aluno("Cassiano Honorio da Silva", "17499789381", "2012780027", Sexo.MASCULINO, 1899, Titulo.ESPECIALIZACAO, curso);
	}
	
	@Test(expected=CampoObrigatorioException.class)
	public void campoTituloNulo() throws CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, IllegalArgumentException, IllegalAccessException, MatriculaInvalidaException, NomeInvalidoException {
		
		new Aluno("Cassiano Honorio da Silva", "17499789381", "2012780027", Sexo.MASCULINO, 2012, null, curso);
	}
	
	@Test(expected=CampoObrigatorioException.class)
	public void campoCursoNulo() throws CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, IllegalArgumentException, IllegalAccessException, MatriculaInvalidaException, NomeInvalidoException {
		
		new Aluno("Cassiano Honorio da Silva", "17499789381", "2012780027", Sexo.MASCULINO, 2015, Titulo.ESPECIALIZACAO, null);
	}
	
	@Test
	public void mapeadorArtibutosNulo() throws IllegalArgumentException, IllegalAccessException, CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, MatriculaInvalidaException, NomeInvalidoException {
		Aluno aluno = new Aluno("Cassiano Honorio da Silva", "17499789381", "2012780027", Sexo.MASCULINO, 2012, Titulo.ESPECIALIZACAO, curso);
		
		Assert.assertNotEquals(aluno.getMapeadorAtributos(), null);
	}
	
	@Test
	public void tudoCerto() throws CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, IllegalArgumentException, IllegalAccessException, MatriculaInvalidaException, NomeInvalidoException {
		
		new Aluno("Cassiano Honorio da Silva", "17499789381", "2012780027", Sexo.MASCULINO, 2012, Titulo.ESPECIALIZACAO, curso);
	}
}

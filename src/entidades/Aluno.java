package entidades;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import excecoes.AnoIngressoException;
import excecoes.CampoInalteravelException;
import excecoes.CampoObrigatorioException;
import excecoes.CpfInvalidoException;
import excecoes.MatriculaInvalidaException;
import excecoes.NomeInvalidoException;


public class Aluno extends Consumidor {
	
	private MapeadorAtributos mapeador = new MapeadorAtributos();
	private Curso curso;
	
	public Aluno(long id, String nome, String cpf, String matricula, Sexo sexo,
			int anoIngresso, Titulo titulo, Curso curso) {
		super(id, nome, cpf, matricula, sexo, anoIngresso, titulo);
		this.curso = curso;
	}

	public Aluno(String nome, String cpf, String matricula, Sexo sexo,
			int anoIngresso, Titulo titulo, Curso curso) 
					throws CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, 
					MatriculaInvalidaException, IllegalArgumentException, IllegalAccessException, NomeInvalidoException {
		super(nome, cpf, matricula, sexo, anoIngresso, titulo);
		this.curso = curso;
		
		validar();
	}

	public Curso getCurso() {
		return curso;
	}
	
	@Override
	public MapeadorAtributos getMapeadorAtributos() {
		mapeador = super.getMapeadorAtributos();
		mapeador.add(new Atributo<Curso>(Curso.class, "curso", getCurso()));
		return mapeador;
	}
	
	@Override
	public void validar() 
			throws CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, 
			MatriculaInvalidaException, IllegalArgumentException, IllegalAccessException, NomeInvalidoException {
		super.validar();
		if(curso==null) {
			throw new CampoObrigatorioException("curso");
		}
	}
	
	@Override
	public void atualizar(Collection<Atributo<?>> atributos) 
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, 
			SecurityException, CampoInalteravelException, CampoObrigatorioException, CpfInvalidoException, 
			AnoIngressoException, MatriculaInvalidaException, NomeInvalidoException {
		
		super.atualizar(atributos);
		List<String> chaves = new ArrayList<String>();
		for(Atributo a : atributos) {
			chaves.add(a.getNome());
		}
		
		if(chaves.contains("curso")) {
			throw new CampoInalteravelException("curso");
		}
	}
}
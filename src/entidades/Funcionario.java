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


public class Funcionario extends Consumidor {
	
	private MapeadorAtributos mapeador = new MapeadorAtributos();
	private Departamento departamento;

	public Funcionario(String nome, String cpf, String matricula, Sexo sexo,
			int anoIngresso, Titulo titulo, Departamento departamento) 
			throws CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, MatriculaInvalidaException, 
			IllegalArgumentException, IllegalAccessException, NomeInvalidoException {
		
		super(nome, cpf, matricula, sexo, anoIngresso, titulo);
		this.departamento = departamento;
		validar();
	}	
	
	public Funcionario(long id, String nome, String cpf, String matricula,
			Sexo sexo, int anoIngresso, Titulo titulo, Departamento departamento) {
		super(id, nome, cpf, matricula, sexo, anoIngresso, titulo);
		this.departamento = departamento;
	}
	
	@Override
	public MapeadorAtributos getMapeadorAtributos() {
		mapeador = super.getMapeadorAtributos();
		mapeador.add(new Atributo<Departamento>(Departamento.class, "departamento", departamento));
		return mapeador;
	}
	
	@Override
	public void validar() throws CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, 
		MatriculaInvalidaException, IllegalArgumentException, IllegalAccessException, NomeInvalidoException {
		
		super.validar();
		if(departamento == null) {
			throw new CampoObrigatorioException("departamento");
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
		
		if(chaves.contains("departamento")) {
			throw new CampoInalteravelException("departamento");
		}
	}
}
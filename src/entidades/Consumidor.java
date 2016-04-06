package entidades;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import uteis.ValidadorCPF;
import excecoes.AnoIngressoException;
import excecoes.CampoInalteravelException;
import excecoes.CampoObrigatorioException;
import excecoes.CpfInvalidoException;
import excecoes.MatriculaInvalidaException;
import excecoes.NomeInvalidoException;

public class Consumidor implements DomainObject {
	
	@CampoObrigatorio(obrigatorio = false)
	@Alteravel(alteravel = false)
	private MapeadorAtributos mapeador = new MapeadorAtributos();	
	
	@CampoObrigatorio(obrigatorio = false)
	@Alteravel(alteravel = false)
	private Long id = (long) 0;
	
	@CampoObrigatorio(obrigatorio = true)
	@Alteravel(alteravel = true)
	private String nome;
	
	@CampoObrigatorio(obrigatorio = true)
	@Alteravel(alteravel = false)
	private String cpf;
	
	@CampoObrigatorio(obrigatorio = true)
	@Alteravel(alteravel = true)
	private String matricula;
	
	@CampoObrigatorio(obrigatorio = true)
	@Alteravel(alteravel = true)
	private Sexo sexo;
	
	@CampoObrigatorio(obrigatorio = true)
	@Alteravel(alteravel = true)
	private int anoIngresso;
	
	@CampoObrigatorio(obrigatorio = true)
	@Alteravel(alteravel = false)
	private Titulo titulo;
	
	public Consumidor(long id, String nome, String cpf,
			String matricula, Sexo sexo, int anoIngresso, Titulo titulo) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.matricula = matricula;
		this.sexo = sexo;
		this.anoIngresso = anoIngresso;
		this.titulo = titulo;
	}

	public Consumidor(String nome, String cpf, String matricula, Sexo sexo,
			int anoIngresso, Titulo titulo) {
		this.nome = nome;
		this.cpf = cpf;
		this.matricula = matricula;
		this.sexo = sexo;
		this.anoIngresso = anoIngresso;
		this.titulo = titulo;
	}

	@Override
	public MapeadorAtributos getMapeadorAtributos() {
		
		mapeador.add(new Atributo<Long>(Long.class, "id", id));
		mapeador.add(new Atributo<String>(String.class, "nome", nome));
		mapeador.add(new Atributo<String>(String.class, "cpf", cpf));
		mapeador.add(new Atributo<String>(String.class, "matricula", matricula));
		mapeador.add(new Atributo<Sexo>(Sexo.class, "sexo", sexo));
		mapeador.add(new Atributo<Integer>(int.class, "anoIngresso", anoIngresso));
		mapeador.add(new Atributo<Titulo>(Titulo.class, "titulo", titulo));
		
		return mapeador;
	}

	@Override
	public void validar() throws CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, MatriculaInvalidaException, IllegalArgumentException, IllegalAccessException, NomeInvalidoException {
		
		Class classe = this.getClass().getSuperclass();
		Field[] atributos = classe.getDeclaredFields();
		
		for(Field f : atributos) {
			f.setAccessible(true);
			CampoObrigatorio campo = f.getAnnotation(CampoObrigatorio.class);
			if(campo.obrigatorio() && f.get(this)==null) {
				throw new CampoObrigatorioException(f.getName());
			} else if (campo.obrigatorio() && ((String) f.get(this).toString()).trim().equals("")) {
				throw new CampoObrigatorioException(f.getName());
			} else if (f.getName().equals("nome")) {
				if(!nome.matches("^[\\p{L} ]+[ '-]{0,1}[\\p{L} ]+$")) {
					throw new NomeInvalidoException();
				}
			} else if(f.getName().equals("cpf")) {
				if(!ValidadorCPF.isCPFValido(cpf)) {
					throw new CpfInvalidoException();
				}
			} else if(f.getName().equals("matricula")) {
				if(!matricula.trim().matches("\\d{1,11}")) {
					throw new MatriculaInvalidaException();
				}
			} else if(f.getName().equals("anoIngresso")) {
				if((f.getInt(this)<=1970) || (f.getInt(this)>Calendar.getInstance().get(Calendar.YEAR))){
					throw new AnoIngressoException();
				}
			}
		}
	}

	@Override
	public void atualizar(Collection<Atributo<?>> atributos) 
			throws CampoInalteravelException, IllegalArgumentException, 
			IllegalAccessException, CampoObrigatorioException, CpfInvalidoException, 
			AnoIngressoException, MatriculaInvalidaException, NoSuchFieldException, SecurityException, NomeInvalidoException {
		
		List<String> chaves = new ArrayList<String>();
		for(Atributo a : atributos) {
			chaves.add(a.getNome());
		}
		
		Class classe = this.getClass().getSuperclass();
		Field[] alteraveis = classe.getDeclaredFields();
		for(Field f : alteraveis) {
			f.setAccessible(true);
			Alteravel alteravel = f.getAnnotation(Alteravel.class);
			if(!alteravel.alteravel() && chaves.contains(f.getName())) {
				throw new CampoInalteravelException(f.getName());
			}
		}
		
		for(Atributo a : atributos) {
			if(classe.getDeclaredField(a.getNome())==null) {
				throw new NoSuchFieldError(a.getNome());
			} else {
				Field f = classe.getDeclaredField(a.getNome());
				f.set(this, a.getValor());
			}
		}
		validar();
	}

	@Override
	public String toString() {
		return "Consumidor [nome=" + nome + ", cpf=" + cpf + ", matricula="
				+ matricula + ", sexo=" + sexo + ", anoIngresso=" + anoIngresso
				+ ", titulo=" + titulo + "]";
	}
}
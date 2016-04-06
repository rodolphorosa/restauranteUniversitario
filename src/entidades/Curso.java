package entidades;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import excecoes.CampoInalteravelException;
import excecoes.CampoInexistenteException;
import excecoes.CampoObrigatorioException;
import excecoes.NomeInvalidoException;

public class Curso implements DomainObject {
	
	@CampoObrigatorio(obrigatorio = false)
	@Alteravel(alteravel = false)
	private MapeadorAtributos mapeador = new MapeadorAtributos();	
	
	@CampoObrigatorio(obrigatorio = false)
	@Alteravel(alteravel = false)
	private Long id;
	
	@CampoObrigatorio(obrigatorio = true)
	@Alteravel(alteravel = false)
	private String sigla;
	
	@CampoObrigatorio(obrigatorio = true)
	@Alteravel(alteravel = true)
	private String nome;
	
	@CampoObrigatorio(obrigatorio = true)
	@Alteravel(alteravel = true)
	private Departamento departamento;

	public Curso(long id, String sigla, String nome, Departamento departamento) {
		this.id = id;
		this.sigla = sigla;
		this.nome = nome;
		this.departamento = departamento;
	}

	public Curso(String sigla, String nome, Departamento departamento) 
			throws CampoObrigatorioException, IllegalArgumentException, IllegalAccessException, NomeInvalidoException {
		this.sigla = sigla;
		this.nome = nome;
		this.departamento = departamento;
		validar();
	}

	@Override
	public MapeadorAtributos getMapeadorAtributos() {
		mapeador.add(new Atributo<Long>(Long.class, "id", id));
		mapeador.add(new Atributo<String>(String.class, "nome", nome));
		mapeador.add(new Atributo<String>(String.class, "sigla", sigla));
		mapeador.add(new Atributo<Departamento>(Departamento.class, "departamento", departamento));
		return mapeador;
	}

	@Override
	public void validar() throws CampoObrigatorioException, IllegalArgumentException, IllegalAccessException, NomeInvalidoException {		
		Class<?> classe = this.getClass();
		Field[] atributos = classe.getDeclaredFields();
		for(Field f : atributos) {			
			CampoObrigatorio campo = f.getAnnotation(CampoObrigatorio.class);
			if(campo.obrigatorio() && f.get(this)==null) {
				throw new CampoObrigatorioException(f.getName());
			} else if (campo.obrigatorio() && ((String) f.get(this).toString()).trim().equals("")) {
				throw new CampoObrigatorioException(f.getName());
			} else if (f.getName().equals("nome")) {
				if(!nome.matches("^[\\p{L} ]+[ '-]{0,1}[\\p{L} ]+$")) {
					throw new NomeInvalidoException();
				}
			}
		}
	}

	@Override
	public void atualizar(Collection<Atributo<?>> atributos) throws CampoInalteravelException, CampoInexistenteException, 
		NoSuchFieldError, NoSuchFieldException, SecurityException, IllegalArgumentException, 
		IllegalAccessException, CampoObrigatorioException, NomeInvalidoException {
		
		List<String> chaves = new ArrayList<String>();
		for(Atributo a : atributos) {
			chaves.add(a.getNome());
		}
		
		Class classe = this.getClass();
		Field[] alteraveis = classe.getDeclaredFields();
		for(Field f : alteraveis) {
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
		return "Curso [id=" + id + ", sigla=" + sigla + ", nome=" + nome
				+ ", departamento=" + departamento + "]";
	}
}
package entidades;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
// import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;

import excecoes.CampoInalteravelException;
import excecoes.CampoInexistenteException;
import excecoes.CampoObrigatorioException;
import excecoes.DescricaoInvalidaException;
import excecoes.OpcaoVegetarianaInvalidaException;
import excecoes.TurnoInconsistenteException;


public class Refeicao implements DomainObject {
	
	@CampoObrigatorio(obrigatorio = false)
	@Alteravel(alteravel = false)
	private MapeadorAtributos mapeador  = new MapeadorAtributos();
	
	@CampoObrigatorio(obrigatorio = false)
	@Alteravel(alteravel = false)
	private Long id;
	
	@CampoObrigatorio(obrigatorio = true)
	@Alteravel(alteravel = false)
	private Turno turno;	
	
	@CampoObrigatorio(obrigatorio = true)
	@Alteravel(alteravel = false)
	private TipoRefeicao tipo;
	
	@CampoObrigatorio(obrigatorio = true)
	@Alteravel(alteravel = true)	
	private String descricao;
	
	@CampoObrigatorio(obrigatorio = true)
	@Alteravel(alteravel = true)
	private String opcaoVegetariana;
	
	@CampoObrigatorio(obrigatorio = true)
	@Alteravel(alteravel = false)
	private String data;
	
	// private static SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd");
	
	public Refeicao (Turno turno, TipoRefeicao tipo, String descricao, 
			String opcaoVegetariana, String data) throws CampoObrigatorioException, TurnoInconsistenteException, 
			ParseException, IllegalArgumentException, IllegalAccessException, DescricaoInvalidaException,
			OpcaoVegetarianaInvalidaException {
		this.turno = turno;
		this.tipo = tipo;
		this.descricao = descricao;
		this.opcaoVegetariana = opcaoVegetariana;
		this.data = ((String) data).replaceAll("/", "-");
		
		validar();
	}	
	
	public Refeicao(Long id, Turno turno, TipoRefeicao tipo, String descricao, String opcaoVegetariana, String data) {
		this.id = id;
		this.turno = turno;
		this.tipo = tipo;
		this.descricao = descricao;
		this.opcaoVegetariana = opcaoVegetariana;
		this.data = data;
	}

	public BigDecimal calcularPreco(TipoConsumidor tipoConsumidor) {
		return turno.calcularPreco(tipoConsumidor);
	}

	@Override
	public MapeadorAtributos getMapeadorAtributos() {
		mapeador.add(new Atributo<Long>(long.class, "id", id));
		mapeador.add(new Atributo<Turno>(Turno.class, "turno", turno));
		mapeador.add(new Atributo<TipoRefeicao>(TipoRefeicao.class, "tipo", tipo));
		mapeador.add(new Atributo<String>(String.class, "descricao", descricao));
		mapeador.add(new Atributo<String>(String.class, "opcaoVegetariana", opcaoVegetariana));
		mapeador.add(new Atributo<String>(String.class, "data", data));
		return mapeador;
	}

	@Override
	public void validar() 
			throws CampoObrigatorioException, TurnoInconsistenteException, ParseException, 
			IllegalArgumentException, IllegalAccessException, DescricaoInvalidaException, OpcaoVegetarianaInvalidaException {
			
		Class<?> classe = this.getClass();
		Field[] atributos = classe.getDeclaredFields();
		
		for(Field f : atributos) {
			if(f.isAnnotationPresent(CampoObrigatorio.class)) {
				CampoObrigatorio campo = f.getAnnotation(CampoObrigatorio.class);
				if(campo.obrigatorio() && f.get(this)==null) {
					throw new CampoObrigatorioException(f.getName());
				} else if (campo.obrigatorio() && ((String) f.get(this).toString()).trim().equals("")) {
					throw new CampoObrigatorioException(f.getName());
				} else if (f.getName().equals("descricao")) {
					if(!descricao.matches("[\\w]*[\\W]*[\\p{L} ]+[\\w]*[\\W]*")) {
						throw new DescricaoInvalidaException();
					}
				} else if (f.getName().equals("opcaoVegatariana")) {
					if(!opcaoVegetariana.matches("[\\w]*[\\W]*[\\p{L} ]+[\\w]*[\\W]*")) {
						throw new OpcaoVegetarianaInvalidaException();
					}
				}
				else if(f.getName().equals("data")) {
					SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd");
					formatoData.setLenient(false);
					try {
						formatoData.parse(data);
					} catch (ParseException e) {
						throw e;
					}
					
				} else if(f.getName().equals("tipo")) {
					if(tipo.equals(TipoRefeicao.DESJEJUM) && !turno.equals(Turno.MANHA)) {
						throw new TurnoInconsistenteException(tipo, turno);
					} else if(tipo.equals(TipoRefeicao.ALMOCO) && !turno.equals(Turno.TARDE)) {
						throw new TurnoInconsistenteException(tipo, turno);
					} else if(tipo.equals(TipoRefeicao.JANTAR) && !turno.equals(Turno.NOITE)) {
						throw new TurnoInconsistenteException(tipo, turno);
					}
				}
			}
		}
	}

	@Override
	public void atualizar(Collection<Atributo<?>> atributos) 
			throws CampoInalteravelException, NoSuchFieldError, NoSuchFieldException, 
			SecurityException, IllegalArgumentException, IllegalAccessException, 
			CampoObrigatorioException, TurnoInconsistenteException, ParseException, 
			DescricaoInvalidaException, OpcaoVegetarianaInvalidaException,
			CampoInexistenteException {
		List<String> chaves = new ArrayList<String>();
		for(Atributo<?> a : atributos) {
			chaves.add(a.getNome());
		}
		
		Class<?> classe = this.getClass();
		Field[] alteraveis = classe.getDeclaredFields();
		for(Field f : alteraveis) {
			f.setAccessible(true);
			Alteravel alteravel = f.getAnnotation(Alteravel.class);
			if(!alteravel.alteravel() && chaves.contains(f.getName())) {
				throw new CampoInalteravelException(f.getName());
			}
		}
		
		for(Atributo<?> a : atributos) {
			if(classe.getDeclaredField(a.getNome().trim())==null) {
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
		return "Refeicao [id=" + id + ", turno=" + turno + ", tipo=" + tipo
				+ ", descricao=" + descricao + ", opcaoVegetariana="
				+ opcaoVegetariana + ", data=" + data +"]";
	}	
}
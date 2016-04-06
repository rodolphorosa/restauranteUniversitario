package entidades;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import excecoes.CampoInalteravelException;
import excecoes.CampoObrigatorioException;
import excecoes.TicketVendidoException;
import excecoes.VendaInconsistenteException;


public class Ticket implements DomainObject {
	@CampoObrigatorio(obrigatorio = false)
	@Alteravel(alteravel = false)
	private MapeadorAtributos mapeador = new MapeadorAtributos();
	
	@CampoObrigatorio(obrigatorio = false)
	@Alteravel(alteravel = false)
	private Long id;
	
	@CampoObrigatorio(obrigatorio = true)
	@Alteravel(alteravel = false)
	private Refeicao refeicao;
	
	@CampoObrigatorio(obrigatorio = false)
	@Alteravel(alteravel = false)
	private BigDecimal preco;
	
	@CampoObrigatorio(obrigatorio = false)
	@Alteravel(alteravel = false)
	private Consumidor consumidor;
	
	@CampoObrigatorio(obrigatorio = false)
	@Alteravel(alteravel = false)
	private TipoConsumidor tipoConsumidor;
	
	@CampoObrigatorio(obrigatorio = false)
	@Alteravel(alteravel = false)
	private String dataCompra;
	
	@CampoObrigatorio(obrigatorio = true)
	@Alteravel(alteravel = true)
	private boolean vendido = false;
	
	@CampoObrigatorio(obrigatorio = false)
	@Alteravel(alteravel = false)
	private static SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd");
	
	public Ticket(Refeicao refeicao, TipoConsumidor tipoConsumidor) throws CampoObrigatorioException, IllegalArgumentException, IllegalAccessException {
		this.refeicao = refeicao;
		this.tipoConsumidor = tipoConsumidor;
		validar();
		dataCompra = (String) refeicao.getMapeadorAtributos().get("data");
	}
	
	public Ticket(Long id, Refeicao refeicao, Consumidor consumidor, BigDecimal preco, String dataCompra, boolean vendido) {
		super();
		this.id = id;
		this.refeicao = refeicao;
		this.preco = preco;
		this.consumidor = consumidor;
		this.dataCompra = dataCompra;
		this.vendido = vendido;
	}
	
	public void vender(Consumidor consumidor) throws VendaInconsistenteException, TicketVendidoException {
		if(tipoConsumidor==null) {
			if(consumidor instanceof Aluno) {
				tipoConsumidor = TipoConsumidor.ALUNO;
			} else if(consumidor instanceof Funcionario) {
				tipoConsumidor = TipoConsumidor.FUNCIONARIO;
			}
		}		
		
		if (!vendido) {
			if(consumidor instanceof Aluno) {
				if(!tipoConsumidor.equals(TipoConsumidor.ALUNO)) {
					throw new VendaInconsistenteException(tipoConsumidor, TipoConsumidor.ALUNO);
				}
			} else if(consumidor instanceof Funcionario) {
				if(!tipoConsumidor.equals(TipoConsumidor.FUNCIONARIO)) {
					throw new VendaInconsistenteException(tipoConsumidor, TipoConsumidor.FUNCIONARIO);
				}
			}
			this.consumidor = consumidor;
		} else {
			throw new TicketVendidoException();
		}
	}
	
	public BigDecimal calcularPreco() {
		if(preco==null) {
			preco = refeicao.calcularPreco(tipoConsumidor);
		}
		return preco;
	}
	
	public void vincularTipoConsumidor(TipoConsumidor tipoConsumidor) {
		this.tipoConsumidor = tipoConsumidor;
		preco = refeicao.calcularPreco(tipoConsumidor);
	}
	
	public boolean foiPago() {
		return vendido;
	}

	@Override
	public MapeadorAtributos getMapeadorAtributos() {		
		mapeador.add(new Atributo<Long>(Long.class, "id", id));
		mapeador.add(new Atributo<Refeicao>(Refeicao.class, "refeicao", refeicao));
		mapeador.add(new Atributo<BigDecimal>(BigDecimal.class, "preco", preco));
		mapeador.add(new Atributo<Boolean>(boolean.class, "vendido", vendido));
		mapeador.add(new Atributo<Consumidor>(Consumidor.class, "consumidor", consumidor));
		mapeador.add(new Atributo<String>(String.class, "dataCompra", dataCompra));
		mapeador.add(new Atributo<TipoConsumidor>(TipoConsumidor.class, "tipoConsumidor", tipoConsumidor));
		return mapeador;
	}

	@Override
	public void validar() throws CampoObrigatorioException, IllegalArgumentException, IllegalAccessException {
		
		Class classe = this.getClass();
		Field[] atributos = classe.getDeclaredFields();
		for(Field f : atributos) {
			if(f.isAnnotationPresent(CampoObrigatorio.class)) {
				CampoObrigatorio campo = f.getAnnotation(CampoObrigatorio.class);
				if(campo.obrigatorio() && f.get(this)==null) {
					throw new CampoObrigatorioException(f.getName());
				} else if (campo.obrigatorio() && ((String) f.get(this).toString()).trim().equals("")) {
					throw new CampoObrigatorioException(f.getName());
				}
			}
		}		
	}

	@Override
	public void atualizar(Collection<Atributo<?>> atributos) 
			throws IllegalArgumentException, IllegalAccessException, CampoObrigatorioException, 
			NoSuchFieldException, SecurityException, CampoInalteravelException {
		List<String> chaves = new ArrayList<String>();
		for(Atributo a : atributos) {
			chaves.add(a.getNome());
		}
		
		Class classe = this.getClass();
		Field[] alteraveis = classe.getDeclaredFields();
		for(Field f : alteraveis) {
			f.setAccessible(true);
			Alteravel alteravel = f.getAnnotation(Alteravel.class);
			if(!alteravel.alteravel() && chaves.contains(f.getName())) {
				throw new CampoInalteravelException(f.getName());
			}
		}
		
		for(Atributo a : atributos) {
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
		return "Ticket [id=" + id + ", refeicao=" + refeicao + ", preco="
				+ preco + ", consumidor=" + consumidor + ", tipoConsumidor="
				+ tipoConsumidor + ", dataCompra=" + dataCompra + ", vendido="
				+ vendido + "]";
	}	
}
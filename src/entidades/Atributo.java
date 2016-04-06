package entidades;


public class Atributo<T> {
	
	private Class<T> tipo;
	private String nome;
	private T valor;

	public Atributo (Class<T> tipo, String nome, T valor) {
		this.tipo= tipo;
		this.nome = nome;
		this.valor = valor;
	}
	
	public Class<T> getTipo() {
		return tipo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public T getValor() {
		return valor;
	}
	
	@Override
	public String toString() {
		return "Atributo [name=" + nome + ", value=" + valor + "]";
	}
}
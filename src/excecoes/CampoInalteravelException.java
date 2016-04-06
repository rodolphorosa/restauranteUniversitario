package excecoes;

@SuppressWarnings("serial")
public class CampoInalteravelException extends Exception {
	
	private String campo;
	
	public CampoInalteravelException(String campo) {
		this.campo = campo;
	}
	
	public String toString() {
		return "O atributo é inalterável: " + campo;
	}
	
	@Override
	public String getMessage() {
		return toString();
	}
}
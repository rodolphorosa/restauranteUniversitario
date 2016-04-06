package excecoes;

@SuppressWarnings("serial")
public class CampoInexistenteException extends Exception {
	
	private String campo;
	
	public CampoInexistenteException(String campo) {
		this.campo = campo;
	}
	
	public String toString() {
		return "Campo inexistente: " + campo;
	}
	
	@Override
	public String getMessage() {
		return toString();
	}
}

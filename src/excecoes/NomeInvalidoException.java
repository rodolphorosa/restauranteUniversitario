package excecoes;

@SuppressWarnings("serial")
public class NomeInvalidoException extends Exception {
	
	public String toString() {
		return "Nome cont�m caracteres inv�lidos";
	}	
	
	@Override
	public String getMessage() {
		return "Nome cont�m caracteres inv�lidos";
	}

}

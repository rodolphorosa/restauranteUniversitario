package excecoes;

@SuppressWarnings("serial")
public class DescricaoInvalidaException extends Exception {
	
	public String toString() {
		return "Descri��o possui caracteres inv�lidos";
	}
	
	@Override
	public String getMessage() {
		return "Descri��o possui caracteres inv�lidos";
	}

}

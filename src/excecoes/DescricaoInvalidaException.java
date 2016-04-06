package excecoes;

@SuppressWarnings("serial")
public class DescricaoInvalidaException extends Exception {
	
	public String toString() {
		return "Descrição possui caracteres inválidos";
	}
	
	@Override
	public String getMessage() {
		return "Descrição possui caracteres inválidos";
	}

}

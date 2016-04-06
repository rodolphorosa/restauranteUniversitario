package excecoes;

@SuppressWarnings("serial")
public class NomeInvalidoException extends Exception {
	
	public String toString() {
		return "Nome contém caracteres inválidos";
	}	
	
	@Override
	public String getMessage() {
		return "Nome contém caracteres inválidos";
	}

}

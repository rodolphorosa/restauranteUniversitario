package excecoes;

@SuppressWarnings("serial")
public class MatriculaInvalidaException extends Exception {
	
	public String toString() {
		return "Matrícula inválida!";
	}
	
	@Override
	public String getMessage() {
		return toString();
	}
}
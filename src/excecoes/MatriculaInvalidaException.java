package excecoes;

@SuppressWarnings("serial")
public class MatriculaInvalidaException extends Exception {
	
	public String toString() {
		return "Matr�cula inv�lida!";
	}
	
	@Override
	public String getMessage() {
		return toString();
	}
}
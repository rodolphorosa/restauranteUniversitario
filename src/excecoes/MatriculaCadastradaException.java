package excecoes;

@SuppressWarnings("serial")
public class MatriculaCadastradaException extends Exception {
	
	public String toString() {
		return "Matr�cula j� cadastrada";
	}
	
	@Override
	public String getMessage() {
		return "Matr�cula j� cadastrada";
	}

}

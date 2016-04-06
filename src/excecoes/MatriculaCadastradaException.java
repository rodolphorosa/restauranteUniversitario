package excecoes;

@SuppressWarnings("serial")
public class MatriculaCadastradaException extends Exception {
	
	public String toString() {
		return "Matrícula já cadastrada";
	}
	
	@Override
	public String getMessage() {
		return "Matrícula já cadastrada";
	}

}

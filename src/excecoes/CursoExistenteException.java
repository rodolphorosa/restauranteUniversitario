package excecoes;

@SuppressWarnings("serial")
public class CursoExistenteException extends Exception {
	
	public String toString() {
		return "Curso j� cadastrado";
	}
	
	@Override
	public String getMessage() {
		return toString();
	}

}

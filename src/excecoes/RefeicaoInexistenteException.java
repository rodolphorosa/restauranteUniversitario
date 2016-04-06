package excecoes;

@SuppressWarnings("serial")
public class RefeicaoInexistenteException extends Exception {
	
	public String toSring() {
		return "Refeição não está cadastrada";
	}
	
	@Override
	public String getMessage() {
		return toString();
	}

}

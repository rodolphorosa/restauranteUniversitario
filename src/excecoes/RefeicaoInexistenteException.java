package excecoes;

@SuppressWarnings("serial")
public class RefeicaoInexistenteException extends Exception {
	
	public String toSring() {
		return "Refei��o n�o est� cadastrada";
	}
	
	@Override
	public String getMessage() {
		return toString();
	}

}

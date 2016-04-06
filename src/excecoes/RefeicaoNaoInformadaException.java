package excecoes;

@SuppressWarnings("serial")
public class RefeicaoNaoInformadaException extends Exception {
	
	public String toSring() {
		return "Refeição não foi informada";
	}
	
	@Override
	public String getMessage() {
		return toString();
	}

}

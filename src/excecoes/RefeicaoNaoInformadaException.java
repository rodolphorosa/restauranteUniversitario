package excecoes;

@SuppressWarnings("serial")
public class RefeicaoNaoInformadaException extends Exception {
	
	public String toSring() {
		return "Refei��o n�o foi informada";
	}
	
	@Override
	public String getMessage() {
		return toString();
	}

}

package excecoes;

@SuppressWarnings("serial")
public class TicketNaoInformadoException extends Exception {
	
	public String toString() {
		return "Ticket n�o informado";
	}
	
	@Override
	public String getMessage() {
		return toString();
	}

}

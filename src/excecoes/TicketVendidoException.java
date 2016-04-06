package excecoes;

@SuppressWarnings("serial")
public class TicketVendidoException extends Exception {
	
	public String toString() {
		return "Ticket já foi vendido!";
	}
	
	@Override
	public String getMessage() {
		return toString();		
	}
}

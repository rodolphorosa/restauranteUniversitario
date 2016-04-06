package excecoes;

@SuppressWarnings("serial")
public class TicketNaoSelecionadoException extends Exception {
	
	public String toSring() {
		return "Nenhum ticket selecionado!";
	}
	
	@Override
	public String getMessage() {
		return toString();
	}

}

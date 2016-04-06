package excecoes;

import entidades.TipoConsumidor;

@SuppressWarnings("serial")
public class VendaInconsistenteException extends Exception {	
	
	private TipoConsumidor tipoTicket;
	private TipoConsumidor tipoConsumidor;
	
	public VendaInconsistenteException(TipoConsumidor tipoTicket,
			TipoConsumidor tipoConsumidor) {
		this.tipoTicket = tipoTicket;
		this.tipoConsumidor = tipoConsumidor;
	}

	public String toString() {
		return "Não é possível vender um ticket de " + tipoTicket.name() + " para um " + tipoConsumidor.name();
	}
	
	@Override
	public String getMessage() {
		return toString();		
	}
}

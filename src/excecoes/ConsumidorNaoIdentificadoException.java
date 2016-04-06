package excecoes;

@SuppressWarnings("serial")
public class ConsumidorNaoIdentificadoException extends Exception {
	
	public String toString() {
		return "Consumidor não foi identificado!";
	}
	
	@Override
	public String getMessage() {
		return toString();
	}

}

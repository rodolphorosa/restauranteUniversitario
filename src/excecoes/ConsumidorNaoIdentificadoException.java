package excecoes;

@SuppressWarnings("serial")
public class ConsumidorNaoIdentificadoException extends Exception {
	
	public String toString() {
		return "Consumidor n�o foi identificado!";
	}
	
	@Override
	public String getMessage() {
		return toString();
	}

}

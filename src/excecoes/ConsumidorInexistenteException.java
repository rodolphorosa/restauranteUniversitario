package excecoes;

@SuppressWarnings("serial")
public class ConsumidorInexistenteException extends Exception {
	
	public String toString() {
		return "Consumidor n�o cadastrado!";
	}

}

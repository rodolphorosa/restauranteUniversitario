package excecoes;

@SuppressWarnings("serial")
public class CarrinhoVazioException extends Exception {
	
	public String toString() {
		return "N�o � poss�vel finalizar a compra sem tickets";
	}
	
	@Override
	public String getMessage() {
		return "N�o � poss�vel finalizar a compra sem tickets";
	}

}

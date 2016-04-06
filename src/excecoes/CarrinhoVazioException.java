package excecoes;

@SuppressWarnings("serial")
public class CarrinhoVazioException extends Exception {
	
	public String toString() {
		return "Não é possível finalizar a compra sem tickets";
	}
	
	@Override
	public String getMessage() {
		return "Não é possível finalizar a compra sem tickets";
	}

}

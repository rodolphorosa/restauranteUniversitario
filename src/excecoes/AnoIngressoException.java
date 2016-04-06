package excecoes;

@SuppressWarnings("serial")
public class AnoIngressoException extends Exception {
	
	public String toString() {
		return "Ano de ingresso inválido!";
	}
	
	@Override
	public String getMessage() {
		return "Ano de ingresso inválido!";
	}
}

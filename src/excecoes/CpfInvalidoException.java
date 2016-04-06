package excecoes;

@SuppressWarnings("serial")
public class CpfInvalidoException extends Exception {
	
	public String toString() {
		return "CPF Inválido";
	}
	
	@Override
	public String getMessage() {
		return toString();		
	}
}
package excecoes;

@SuppressWarnings("serial")
public class CpfInvalidoException extends Exception {
	
	public String toString() {
		return "CPF Inv�lido";
	}
	
	@Override
	public String getMessage() {
		return toString();		
	}
}
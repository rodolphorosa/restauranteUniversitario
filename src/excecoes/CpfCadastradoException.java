package excecoes;

@SuppressWarnings("serial")
public class CpfCadastradoException extends Exception {
	
	public String toString() {
		return "CPF j� cadastrado";
	}
	
	@Override
	public String getMessage() {
		return "CPF j� cadastrado";
	}

}

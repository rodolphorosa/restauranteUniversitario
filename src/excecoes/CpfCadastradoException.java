package excecoes;

@SuppressWarnings("serial")
public class CpfCadastradoException extends Exception {
	
	public String toString() {
		return "CPF já cadastrado";
	}
	
	@Override
	public String getMessage() {
		return "CPF já cadastrado";
	}

}

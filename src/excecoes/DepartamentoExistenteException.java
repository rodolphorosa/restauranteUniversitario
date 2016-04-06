package excecoes;

@SuppressWarnings("serial")
public class DepartamentoExistenteException extends Exception {
	
	public String toString() {
		return "Departamento já cadastrado!";
	}
	
	@Override
	public String getMessage() {
		return "Departamento já cadastrado!";
	}
}
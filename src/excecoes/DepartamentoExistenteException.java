package excecoes;

@SuppressWarnings("serial")
public class DepartamentoExistenteException extends Exception {
	
	public String toString() {
		return "Departamento j� cadastrado!";
	}
	
	@Override
	public String getMessage() {
		return "Departamento j� cadastrado!";
	}
}
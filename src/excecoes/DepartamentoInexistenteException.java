package excecoes;

@SuppressWarnings("serial")
public class DepartamentoInexistenteException extends Exception {
	
	public String toString() {
		return "Departamento inexistente!";
	}
}

package excecoes;

@SuppressWarnings("serial")
public class TamanhoOpcaoVegetarianaException extends Exception {
	
	public String toString() {
		return "Opção Vegetariana deve ser menor ou igual a 100";
	}
	
	@Override
	public String getMessage() {
		return toString();
	}

}

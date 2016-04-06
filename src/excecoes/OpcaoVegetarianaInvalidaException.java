package excecoes;

@SuppressWarnings("serial")
public class OpcaoVegetarianaInvalidaException extends Exception {
	
	public String toString() {
		return "Opção vegetariana possui caracteres inválidos";
	}
	
	@Override
	public String getMessage() {
		return "Opção vegetariana possui caracteres inválidos";
	}

}

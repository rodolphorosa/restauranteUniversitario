package excecoes;

@SuppressWarnings("serial")
public class OpcaoVegetarianaInvalidaException extends Exception {
	
	public String toString() {
		return "Op��o vegetariana possui caracteres inv�lidos";
	}
	
	@Override
	public String getMessage() {
		return "Op��o vegetariana possui caracteres inv�lidos";
	}

}

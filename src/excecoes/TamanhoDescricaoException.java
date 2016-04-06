package excecoes;

@SuppressWarnings("serial")
public class TamanhoDescricaoException extends Exception {
	public String toString(){
		return "Tamanho da descrição deve ser menor ou igual a 100.";
	}
	@Override
	public String getMessage(){
		return "Tamanho da descrição deve ser menor ou igual a 100.";
	}
}

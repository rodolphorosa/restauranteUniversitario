package excecoes;

@SuppressWarnings("serial")
public class TamanhoDescricaoException extends Exception {
	public String toString(){
		return "Tamanho da descri��o deve ser menor ou igual a 100.";
	}
	@Override
	public String getMessage(){
		return "Tamanho da descri��o deve ser menor ou igual a 100.";
	}
}

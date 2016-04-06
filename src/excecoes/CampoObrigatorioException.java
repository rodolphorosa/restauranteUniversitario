package excecoes;

@SuppressWarnings("serial")
public class CampoObrigatorioException extends Exception {
	
	private String nome;
	
	public CampoObrigatorioException(String nome) {
		this.nome = nome;
	}
	
	public String toString() {
		return "Campo obrigatório não informado: " + nome;
	}
	
	@Override
	public String getMessage() {
		return toString();		
	}
}
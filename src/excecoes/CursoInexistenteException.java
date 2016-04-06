package excecoes;

@SuppressWarnings("serial")
public class CursoInexistenteException extends Exception {
	
	public String toString(){
		return "Curso não cadastrado";
	}

}

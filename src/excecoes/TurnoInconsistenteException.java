package excecoes;

import entidades.TipoRefeicao;
import entidades.Turno;

@SuppressWarnings("serial")
public class TurnoInconsistenteException extends Exception {
	
	private TipoRefeicao tipo;
	private Turno turno;
	
	public TurnoInconsistenteException(TipoRefeicao tipo, Turno turno) {
		this.tipo = tipo;
		this.turno = turno;
	}
	
	public String toString() {
		return "Não é possível criar um " + tipo.name() + " no turno da " + turno.name();
	}
	
	@Override
	public String getMessage() {
		return toString();		
	}
}
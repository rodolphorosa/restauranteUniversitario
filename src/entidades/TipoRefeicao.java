package entidades;

public enum TipoRefeicao {
	DESJEJUM(Turno.MANHA), ALMOCO(Turno.TARDE), JANTAR(Turno.NOITE);
	
	private Turno turno;
	
	TipoRefeicao(Turno turno){
		this.turno = turno;
	}
	
	public Turno getTurno() {
		return turno;
	}
}
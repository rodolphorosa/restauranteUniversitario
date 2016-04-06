package entidades;

import java.math.BigDecimal;

public enum Turno {
	MANHA("3.0", "0.5"), TARDE("6.0", "1.0"), NOITE("6.0", "1.0");
	
	private BigDecimal precoFuncionario, precoAluno;
	
	private Turno(String precoFuncionario, String precoAluno) {
		this.precoFuncionario = new BigDecimal(precoFuncionario);
		this.precoAluno = new BigDecimal(precoAluno);
	}
	
	public BigDecimal calcularPreco(TipoConsumidor tipoConsumidor) {
		switch(tipoConsumidor) {
		case FUNCIONARIO:
			return precoFuncionario;
		default:
			return precoAluno;
		}
	}
	
	public TipoRefeicao getTipoRefeicao() {
		if(this.equals(Turno.MANHA)) {
			return TipoRefeicao.DESJEJUM;
		} else if(this.equals(Turno.TARDE)) {
			return TipoRefeicao.ALMOCO;
		} else if(this.equals(Turno.NOITE)) {
			return TipoRefeicao.JANTAR;
		}
		return null;
	}
}
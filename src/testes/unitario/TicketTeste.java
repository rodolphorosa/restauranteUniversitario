package testes.unitario;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import entidades.Departamento;
import entidades.Funcionario;
import excecoes.MatriculaInvalidaException;
import entidades.Refeicao;
import entidades.Sexo;
import entidades.Ticket;
import entidades.TipoConsumidor;
import entidades.TipoRefeicao;
import entidades.Titulo;
import entidades.Turno;
import excecoes.AnoIngressoException;
import excecoes.CampoObrigatorioException;
import excecoes.CpfInvalidoException;
import excecoes.DescricaoInvalidaException;
import excecoes.NomeInvalidoException;
import excecoes.OpcaoVegetarianaInvalidaException;
import excecoes.TicketVendidoException;
import excecoes.TurnoInconsistenteException;
import excecoes.VendaInconsistenteException;

public class TicketTeste {

	private Refeicao refeicao;

	@Before
	public void criaRefeicao() throws CampoObrigatorioException,
			TurnoInconsistenteException, ParseException, IllegalArgumentException, IllegalAccessException, DescricaoInvalidaException, OpcaoVegetarianaInvalidaException {
		refeicao = new Refeicao(Turno.TARDE, TipoRefeicao.ALMOCO, "Feijoada",
				"Legumada", "2015-07-23");
	}

	@Test(expected = CampoObrigatorioException.class)
	public void campoRefeicaoNulo() throws CampoObrigatorioException, IllegalArgumentException, IllegalAccessException, DescricaoInvalidaException, OpcaoVegetarianaInvalidaException {
		new Ticket(null, TipoConsumidor.ALUNO);
	}

	@Test(expected=VendaInconsistenteException.class)
	public void vendaTicketParaConsumidorErrado()
			throws CampoObrigatorioException, CpfInvalidoException,
			AnoIngressoException, VendaInconsistenteException, TicketVendidoException, IllegalArgumentException, IllegalAccessException, MatriculaInvalidaException, NomeInvalidoException {
		Ticket ticket = new Ticket(refeicao, TipoConsumidor.ALUNO);

		Departamento departamento = new Departamento("DTL",
				"Departamento de Tecnologias e Linguagens");

		Funcionario funcionario = new Funcionario("Cassiano Honorio da Silva",
				"17499789381", "2012780027", Sexo.MASCULINO, 2015,
				Titulo.ESPECIALIZACAO, departamento);
		
		ticket.vender(funcionario);

	}
}

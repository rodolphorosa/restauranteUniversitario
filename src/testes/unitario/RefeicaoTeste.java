package testes.unitario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entidades.Refeicao;
import entidades.TipoRefeicao;
import entidades.Turno;
import excecoes.AnoIngressoException;
import excecoes.CampoObrigatorioException;
import excecoes.CpfInvalidoException;
import excecoes.DescricaoInvalidaException;
import excecoes.OpcaoVegetarianaInvalidaException;
import excecoes.TurnoInconsistenteException;

public class RefeicaoTeste {

	private String data;

	@Before
	public void criaDataString() {
		data = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

	}

	@Test(expected = TurnoInconsistenteException.class)
	public void turnoERefeicaoInconsistentes()
			throws CampoObrigatorioException, TurnoInconsistenteException,
			ParseException, IllegalArgumentException, IllegalAccessException, DescricaoInvalidaException, OpcaoVegetarianaInvalidaException {

		new Refeicao(Turno.MANHA, TipoRefeicao.JANTAR, "Bacon com ovos",
				"Tofu", data);
	}

	@Test(expected = CampoObrigatorioException.class)
	public void campoTurnoNulo() throws CampoObrigatorioException,
			TurnoInconsistenteException, ParseException, IllegalArgumentException, IllegalAccessException, DescricaoInvalidaException, OpcaoVegetarianaInvalidaException {

		new Refeicao(null, TipoRefeicao.DESJEJUM, "Bacon com ovos", "Tofu",
				data);
	}

	@Test(expected = CampoObrigatorioException.class)
	public void campoTipoRefeicaoNulo() throws CampoObrigatorioException,
			TurnoInconsistenteException, ParseException, IllegalArgumentException, IllegalAccessException, DescricaoInvalidaException, OpcaoVegetarianaInvalidaException {

		new Refeicao(Turno.MANHA, null, "Bacon com ovos", "Tofu", data);
	}

	@Test(expected = CampoObrigatorioException.class)
	public void campoDescricaoNulo() throws CampoObrigatorioException,
			TurnoInconsistenteException, ParseException, IllegalArgumentException, IllegalAccessException, DescricaoInvalidaException, OpcaoVegetarianaInvalidaException {

		new Refeicao(Turno.MANHA, TipoRefeicao.DESJEJUM, null, "Tofu", data);
	}

	@Test(expected = CampoObrigatorioException.class)
	public void campoDescricaoVazio() throws CampoObrigatorioException,
			TurnoInconsistenteException, ParseException, IllegalArgumentException, IllegalAccessException, DescricaoInvalidaException, OpcaoVegetarianaInvalidaException {

		new Refeicao(Turno.MANHA, TipoRefeicao.DESJEJUM, "", "Tofu", data);
	}

	@Test(expected = CampoObrigatorioException.class)
	public void campoDescricaoSomenteEspacos()
			throws CampoObrigatorioException, TurnoInconsistenteException,
			ParseException, IllegalArgumentException, IllegalAccessException, DescricaoInvalidaException, OpcaoVegetarianaInvalidaException {

		new Refeicao(Turno.MANHA, TipoRefeicao.DESJEJUM, "  \t\n\t ", "Tofu",
				data);
	}

	@Test(expected = CampoObrigatorioException.class)
	public void campoOpcaoVegetarianaNulo() throws CampoObrigatorioException,
			TurnoInconsistenteException, ParseException, IllegalArgumentException, IllegalAccessException, DescricaoInvalidaException, OpcaoVegetarianaInvalidaException {

		new Refeicao(Turno.MANHA, TipoRefeicao.DESJEJUM, "Bacon com ovos",
				null, data);
	}

	@Test(expected = CampoObrigatorioException.class)
	public void campoOpcaoVegetarianaVazio() throws CampoObrigatorioException,
			TurnoInconsistenteException, ParseException, IllegalArgumentException, IllegalAccessException, DescricaoInvalidaException, OpcaoVegetarianaInvalidaException {

		new Refeicao(Turno.MANHA, TipoRefeicao.DESJEJUM, "Bacon com ovos", "",
				data);
	}

	@Test(expected = CampoObrigatorioException.class)
	public void campoOpcaoVegetarianaSomenteEspacos()
			throws CampoObrigatorioException, TurnoInconsistenteException,
			ParseException, IllegalArgumentException, IllegalAccessException, DescricaoInvalidaException, OpcaoVegetarianaInvalidaException {

		new Refeicao(Turno.MANHA, TipoRefeicao.DESJEJUM, "Bacon com ovos",
				"\n\t  \n \t ", data);
	}

	@Test(expected = CampoObrigatorioException.class)
	public void campoDataVazio() throws CampoObrigatorioException,
			TurnoInconsistenteException, ParseException, IllegalArgumentException, IllegalAccessException, DescricaoInvalidaException, OpcaoVegetarianaInvalidaException {

		new Refeicao(Turno.TARDE, TipoRefeicao.ALMOCO, "Feijoada", "Legumada",
				"");
	}

	@Test(expected = NullPointerException.class)
	public void campoDataNulo() throws CampoObrigatorioException,
			TurnoInconsistenteException, ParseException, IllegalArgumentException, IllegalAccessException, DescricaoInvalidaException, OpcaoVegetarianaInvalidaException {

		new Refeicao(Turno.TARDE, TipoRefeicao.ALMOCO, "Feijoada", "Legumada",
				null);
	}

	@Test(expected = CampoObrigatorioException.class)
	public void campoDataSomenteEspacos() throws CampoObrigatorioException,
			TurnoInconsistenteException, ParseException, IllegalArgumentException, IllegalAccessException, DescricaoInvalidaException, OpcaoVegetarianaInvalidaException {

		new Refeicao(Turno.TARDE, TipoRefeicao.ALMOCO, "Feijoada", "Legumada",
				" \t\n\t\n ");
	}

	@Test(expected = NullPointerException.class)
	public void calculoPrecoConsumidorNulo() throws CampoObrigatorioException,
			TurnoInconsistenteException, ParseException, IllegalArgumentException, IllegalAccessException, DescricaoInvalidaException, OpcaoVegetarianaInvalidaException {
		Refeicao refeicao = new Refeicao(Turno.TARDE, TipoRefeicao.ALMOCO,
				"Feijoada", "Legumada", data);

		refeicao.calcularPreco(null);
	}
	
	@Test
	public void mapeadorArtibutosNulo() throws CampoObrigatorioException, TurnoInconsistenteException, ParseException, IllegalArgumentException, IllegalAccessException, DescricaoInvalidaException, OpcaoVegetarianaInvalidaException {
		Refeicao refeicao = new Refeicao(Turno.TARDE, TipoRefeicao.ALMOCO, "Feijoada", "Legumada", data);
		
		Assert.assertNotEquals(refeicao.getMapeadorAtributos(), null);
	}

	@Test
	public void tudoCerto() throws CampoObrigatorioException,
			TurnoInconsistenteException, CpfInvalidoException,
			AnoIngressoException, ParseException, IllegalArgumentException, IllegalAccessException, DescricaoInvalidaException, OpcaoVegetarianaInvalidaException {

		new Refeicao(Turno.TARDE, TipoRefeicao.ALMOCO, "Feijoada", "Legumada",
				data);
	}
}

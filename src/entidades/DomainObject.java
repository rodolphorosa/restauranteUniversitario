package entidades;

import java.text.ParseException;
import java.util.Collection;

import excecoes.AnoIngressoException;
import excecoes.CampoInalteravelException;
import excecoes.CampoInexistenteException;
import excecoes.CampoObrigatorioException;
import excecoes.CpfInvalidoException;
import excecoes.DescricaoInvalidaException;
import excecoes.MatriculaInvalidaException;
import excecoes.NomeInvalidoException;
import excecoes.OpcaoVegetarianaInvalidaException;
import excecoes.TurnoInconsistenteException;

public interface DomainObject {
	
	/**Retorna um mapeador dos atributos de um classe.
	 * @return Um mapeador de atributs da classe.*/
	public MapeadorAtributos getMapeadorAtributos();	
	
	/**Realiza a valida��o dos atributos do objeto do dom�nio de acordo com as regras de neg�cio.
	 * @throws CampoObrigatorioException
	 * @throws CpfInvalidoException
	 * @throws AnoIngressoException
	 * @throws TurnoInconsistenteException
	 * @throws ParseException
	 * @throws MatriculaInvalidaException
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws NomeInvalidoException
	 * @throws DescricaoInvalidaException
	 * @throws OpcaoVegetarianaInvalidaException*/
	public void validar() 
			throws CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, 
			TurnoInconsistenteException, ParseException, MatriculaInvalidaException, IllegalArgumentException, 
			IllegalAccessException, NomeInvalidoException, DescricaoInvalidaException, OpcaoVegetarianaInvalidaException;
	
	/**Atualiza os atributos da inst�ncia de um objeto do dom�nio de acordo com as regras de neg�cio.
	 * @param atributos
	 * @throws CampoInalteravelException
	 * @throws CampoInexistenteException
	 * @throws MatriculaInvalidaException 
	 * @throws AnoIngressoException 
	 * @throws CpfInvalidoException 
	 * @throws CampoObrigatorioException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException
	 * @throws SecurityException 
	 * @throws NoSuchFieldException
	 * @throws NomeInvalidoException
	 * @throws OpcaoVegetarianaInvalidaException 
	 * @throws DescricaoInvalidaException 
	 * @throws ParseException 
	 * @throws TurnoInconsistenteException 
	 * @throws NoSuchFieldError*/
	public void atualizar(Collection<Atributo<?>> atributos) 
			throws CampoInalteravelException, CampoInexistenteException, IllegalArgumentException, 
			IllegalAccessException, CampoObrigatorioException, CpfInvalidoException, AnoIngressoException, 
			MatriculaInvalidaException, NoSuchFieldException, SecurityException, NomeInvalidoException, NoSuchFieldError, 
			TurnoInconsistenteException, ParseException, DescricaoInvalidaException, OpcaoVegetarianaInvalidaException;
}
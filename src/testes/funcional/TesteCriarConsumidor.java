package testes.funcional;

import java.io.FileInputStream;

import mapeadores.ConsumidorMapper;

import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.postgresql.PostgresqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;

import entidades.Aluno;
import entidades.Atributo;
import entidades.Consumidor;
import entidades.Curso;
import entidades.Departamento;
import entidades.Sexo;
import entidades.Titulo;

public class TesteCriarConsumidor extends DBTestCase{
	
	private FlatXmlDataSet bancoCarregado;
	
	@Before
	public void setUp() throws Exception{
		System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.postgresql.Driver" );
		System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:postgresql://localhost:5432" );
		System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "postgres" );
		System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "rodolpho" );
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, "public");
	}
	
	//Antes de executar o teste.
	protected DatabaseOperation getSetUpOperation() throws Exception{
		return DatabaseOperation.REFRESH;		
	}
	
	public void testCT11() throws Exception
	{		
		Departamento departamento = new Departamento(1,"DCC", "Departamento de Ciência da Computação");
		
		Curso curso = new Curso(2,"CC", "Ciência da Computação", departamento);
		Aluno aluno = new Aluno("Ana Paula Teixeira","56504983500" ,"02", Sexo.FEMININO, 2012, Titulo.ESPECIALIZACAO, curso);
		Consumidor consumidor = aluno;
		consumidor.getMapeadorAtributos().add(new Atributo<Curso>(Curso.class, "curso", curso));
		ConsumidorMapper.getInstance().adicionar(consumidor);
		
		
		
		IDataSet dadosSetBanco = getConnection().createDataSet();
		ITable dadosNoBanco = dadosSetBanco.getTable("consumidor");

		//remove coluna da tabela.
		ITable filteredTable1 = DefaultColumnFilter.excludedColumnsTable(dadosNoBanco, new String[]{"id"});
		//IDataSet dadosSetEsperado0 = new FlatXmlDataSetBuilder().build(new FileInputStream("src/xml/criarAlunoDataset.xml"));

		IDataSet dadosSetEsperado1 = new FlatXmlDataSetBuilder().build(new FileInputStream("src/xml/criarConsumidorDataset.xml"));
		ITable dadosEsperados1 = dadosSetEsperado1.getTable("consumidor");

		Assertion.assertEquals(dadosEsperados1, filteredTable1);
	}
	
	
	
	protected DatabaseOperation getTearDownOperation() throws Exception{
		return DatabaseOperation.DELETE_ALL;	
	}

	@Override
	protected void setUpDatabaseConfig(DatabaseConfig config){
		config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new PostgresqlDataTypeFactory());		
	}

	@Override
	protected IDataSet getDataSet() throws Exception
	{
		bancoCarregado = new FlatXmlDataSetBuilder().build(new FileInputStream("src/xml/criarConsumidorDataset.xml"));
		return bancoCarregado;
	}

}

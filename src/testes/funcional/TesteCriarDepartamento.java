package testes.funcional;

import java.io.FileInputStream;

import mapeadores.DepartamentoMapper;

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

import entidades.Departamento;

public class TesteCriarDepartamento extends DBTestCase{
	
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

	public void testCTF00() throws Exception
	{		
		Departamento departamento = new Departamento("DC", "Departamento de Ciências Contábeis");
		DepartamentoMapper.getInstance().adicionar(departamento);
		
		IDataSet dadosSetBanco = getConnection().createDataSet();
		ITable dadosNoBanco = dadosSetBanco.getTable("departamento");

		//remove coluna da tabela.
		ITable filteredTable1 = DefaultColumnFilter.excludedColumnsTable(dadosNoBanco, new String[]{"id"});

		IDataSet dadosSetEsperado1 = new FlatXmlDataSetBuilder().build(new FileInputStream("src/xml/criarDepartamentoDataset.xml"));
		ITable dadosEsperados1 = dadosSetEsperado1.getTable("departamento");

		Assertion.assertEquals(dadosEsperados1, filteredTable1);
	}
	
	public void testCTF01() throws Exception
	{		
		Departamento departamento = new Departamento("DCC", "Departamento de Ciência da Computação");
		DepartamentoMapper.getInstance().adicionar(departamento);
		
		IDataSet dadosSetBanco = getConnection().createDataSet();
		ITable dadosNoBanco = dadosSetBanco.getTable("departamento");

		//remove coluna da tabela.
		ITable filteredTable1 = DefaultColumnFilter.excludedColumnsTable(dadosNoBanco, new String[]{"id"});

		IDataSet dadosSetEsperado1 = new FlatXmlDataSetBuilder().build(new FileInputStream("src/xml/criarDepartamentoDataset.xml"));
		ITable dadosEsperados1 = dadosSetEsperado1.getTable("departamento");

		Assertion.assertEquals(dadosEsperados1, filteredTable1);
	}
	public void testCTF02() throws Exception
	{		
		Departamento departamento = new Departamento(null, "Departamento de Ciência da Computação");
		DepartamentoMapper.getInstance().adicionar(departamento);
		
		IDataSet dadosSetBanco = getConnection().createDataSet();
		ITable dadosNoBanco = dadosSetBanco.getTable("departamento");

		//remove coluna da tabela.
		ITable filteredTable1 = DefaultColumnFilter.excludedColumnsTable(dadosNoBanco, new String[]{"id"});

		IDataSet dadosSetEsperado1 = new FlatXmlDataSetBuilder().build(new FileInputStream("src/xml/criarDepartamentoDataset.xml"));
		ITable dadosEsperados1 = dadosSetEsperado1.getTable("departamento");

		Assertion.assertEquals(dadosEsperados1, filteredTable1);
	}
	
	public void testCTF03() throws Exception
	{		
		Departamento departamento = new Departamento("DCC", null);
		DepartamentoMapper.getInstance().adicionar(departamento);
		
		IDataSet dadosSetBanco = getConnection().createDataSet();
		ITable dadosNoBanco = dadosSetBanco.getTable("departamento");

		//remove coluna da tabela.
		ITable filteredTable1 = DefaultColumnFilter.excludedColumnsTable(dadosNoBanco, new String[]{"id"});

		IDataSet dadosSetEsperado1 = new FlatXmlDataSetBuilder().build(new FileInputStream("src/xml/criarDepartamentoDataset.xml"));
		ITable dadosEsperados1 = dadosSetEsperado1.getTable("departamento");

		Assertion.assertEquals(dadosEsperados1, filteredTable1);
	}
	
	public void testCTF04() throws Exception
	{		
		Departamento departamento = new Departamento("DCC", "Departamento de Ciência da Computação7");
		DepartamentoMapper.getInstance().adicionar(departamento);
		
		IDataSet dadosSetBanco = getConnection().createDataSet();
		ITable dadosNoBanco = dadosSetBanco.getTable("departamento");

		//remove coluna da tabela.
		ITable filteredTable1 = DefaultColumnFilter.excludedColumnsTable(dadosNoBanco, new String[]{"id"});

		IDataSet dadosSetEsperado1 = new FlatXmlDataSetBuilder().build(new FileInputStream("src/xml/criarDepartamentoDataset.xml"));
		ITable dadosEsperados1 = dadosSetEsperado1.getTable("departamento");

		Assertion.assertEquals(dadosEsperados1, filteredTable1);
	}
	
	public void testCTF05() throws Exception
	{		
		Departamento departamento = new Departamento("DCC", "Departamento de Ciência da Computação");
		DepartamentoMapper.getInstance().adicionar(departamento);
		
		IDataSet dadosSetBanco = getConnection().createDataSet();
		ITable dadosNoBanco = dadosSetBanco.getTable("departamento");

		//remove coluna da tabela.
		ITable filteredTable1 = DefaultColumnFilter.excludedColumnsTable(dadosNoBanco, new String[]{"id"});

		IDataSet dadosSetEsperado1 = new FlatXmlDataSetBuilder().build(new FileInputStream("src/xml/criarDepartamentoDataset.xml"));
		ITable dadosEsperados1 = dadosSetEsperado1.getTable("departamento");

		Assertion.assertEquals(dadosEsperados1, filteredTable1);
	}
		
	public void testCTF06() throws Exception
	{		
		Departamento departamento = new Departamento("DC", "Departamento de Ciência da Computação");
		DepartamentoMapper.getInstance().adicionar(departamento);
		
		IDataSet dadosSetBanco = getConnection().createDataSet();
		ITable dadosNoBanco = dadosSetBanco.getTable("departamento");

		//remove coluna da tabela.
		ITable filteredTable1 = DefaultColumnFilter.excludedColumnsTable(dadosNoBanco, new String[]{"id"});

		IDataSet dadosSetEsperado1 = new FlatXmlDataSetBuilder().build(new FileInputStream("src/xml/criarDepartamentoDataset.xml"));
		ITable dadosEsperados1 = dadosSetEsperado1.getTable("departamento");

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
		bancoCarregado = new FlatXmlDataSetBuilder().build(new FileInputStream("src/xml/criarDepartamentoDataset.xml"));
		return bancoCarregado;
	}
}

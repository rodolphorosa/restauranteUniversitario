package testes.funcional;

import java.io.FileInputStream;

import mapeadores.CursoMapper;

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

import entidades.Curso;
import entidades.Departamento;

public class TesteCriarCurso extends DBTestCase{
	
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
		Departamento departamento = new Departamento(4,"DC", "Departamento de Ciências Contábeis");
		
		Curso curso = new Curso("CC", "Ciências Contábeis", departamento);
		CursoMapper.getInstance().adicionar(curso);
		
		IDataSet dadosSetBanco = getConnection().createDataSet();
		ITable dadosNoBanco = dadosSetBanco.getTable("curso");

		//remove coluna da tabela.
		ITable filteredTable1 = DefaultColumnFilter.excludedColumnsTable(dadosNoBanco, new String[]{"id"});

		IDataSet dadosSetEsperado1 = new FlatXmlDataSetBuilder().build(new FileInputStream("src/xml/criarCursoDataset.xml"));
		ITable dadosEsperados1 = dadosSetEsperado1.getTable("curso");

		Assertion.assertEquals(dadosEsperados1, filteredTable1);
	}
	
	public void testCTF06() throws Exception
	{		
		Departamento departamento = new Departamento(1,"DCC", "Departamento de Ciência da Computação");
		
		Curso curso = new Curso("CCOMP", "Ciência da Computação", departamento);
		CursoMapper.getInstance().adicionar(curso);
		
		IDataSet dadosSetBanco = getConnection().createDataSet();
		ITable dadosNoBanco = dadosSetBanco.getTable("curso");

		//remove coluna da tabela.
		ITable filteredTable1 = DefaultColumnFilter.excludedColumnsTable(dadosNoBanco, new String[]{"id"});

		IDataSet dadosSetEsperado1 = new FlatXmlDataSetBuilder().build(new FileInputStream("src/xml/criarCursoDataset.xml"));
		ITable dadosEsperados1 = dadosSetEsperado1.getTable("curso");

		Assertion.assertEquals(dadosEsperados1, filteredTable1);
	}
	
	
	public void testCTF07() throws Exception
	{		
		Departamento departamento = new Departamento(1,"DCC", "Departamento de Ciência da Computação");
		
		Curso curso = new Curso(null, "Ciência da Computação", departamento);
		CursoMapper.getInstance().adicionar(curso);
		
		IDataSet dadosSetBanco = getConnection().createDataSet();
		ITable dadosNoBanco = dadosSetBanco.getTable("curso");

		//remove coluna da tabela.
		ITable filteredTable1 = DefaultColumnFilter.excludedColumnsTable(dadosNoBanco, new String[]{"id"});

		IDataSet dadosSetEsperado1 = new FlatXmlDataSetBuilder().build(new FileInputStream("src/xml/criarCursoDataset.xml"));
		ITable dadosEsperados1 = dadosSetEsperado1.getTable("curso");

		Assertion.assertEquals(dadosEsperados1, filteredTable1);
	}
	
	public void testCTF08() throws Exception
	{		
		Departamento departamento = new Departamento(1,"DCC", "Departamento de Ciência da Computação");
		
		Curso curso = new Curso("CCOMP", null, departamento);
		CursoMapper.getInstance().adicionar(curso);
		
		IDataSet dadosSetBanco = getConnection().createDataSet();
		ITable dadosNoBanco = dadosSetBanco.getTable("curso");

		//remove coluna da tabela.
		ITable filteredTable1 = DefaultColumnFilter.excludedColumnsTable(dadosNoBanco, new String[]{"id"});

		IDataSet dadosSetEsperado1 = new FlatXmlDataSetBuilder().build(new FileInputStream("src/xml/criarCursoDataset.xml"));
		ITable dadosEsperados1 = dadosSetEsperado1.getTable("curso");

		Assertion.assertEquals(dadosEsperados1, filteredTable1);
	}
	
	public void testCTF09() throws Exception
	{		
		//Departamento departamento = new Departamento(1,"DCC", "Departamento de Ciência da Computação");
		
		Curso curso = new Curso("CCOMP", "Ciência da Computação", null);
		CursoMapper.getInstance().adicionar(curso);
		
		IDataSet dadosSetBanco = getConnection().createDataSet();
		ITable dadosNoBanco = dadosSetBanco.getTable("curso");

		//remove coluna da tabela.
		ITable filteredTable1 = DefaultColumnFilter.excludedColumnsTable(dadosNoBanco, new String[]{"id"});

		IDataSet dadosSetEsperado1 = new FlatXmlDataSetBuilder().build(new FileInputStream("src/xml/criarCursoDataset.xml"));
		ITable dadosEsperados1 = dadosSetEsperado1.getTable("curso");

		Assertion.assertEquals(dadosEsperados1, filteredTable1);
	}
	
	public void testCTF10() throws Exception
	{		
		Departamento departamento = new Departamento(1,"DCC", "Departamento de Ciência da Computação");
		
		Curso curso = new Curso("CCOMP", "Ciência da Computação9", departamento);
		CursoMapper.getInstance().adicionar(curso);
		
		IDataSet dadosSetBanco = getConnection().createDataSet();
		ITable dadosNoBanco = dadosSetBanco.getTable("curso");

		//remove coluna da tabela.
		ITable filteredTable1 = DefaultColumnFilter.excludedColumnsTable(dadosNoBanco, new String[]{"id"});

		IDataSet dadosSetEsperado1 = new FlatXmlDataSetBuilder().build(new FileInputStream("src/xml/criarCursoDataset.xml"));
		ITable dadosEsperados1 = dadosSetEsperado1.getTable("curso");

		Assertion.assertEquals(dadosEsperados1, filteredTable1);
	}
	
	public void testCT11() throws Exception
	{		
		Departamento departamento = new Departamento(1,"DCC", "Departamento de Ciência da Computação");
		
		Curso curso = new Curso("CC", "Ciência da Computação", departamento);
		CursoMapper.getInstance().adicionar(curso);
		
		IDataSet dadosSetBanco = getConnection().createDataSet();
		ITable dadosNoBanco = dadosSetBanco.getTable("curso");

		//remove coluna da tabela.
		ITable filteredTable1 = DefaultColumnFilter.excludedColumnsTable(dadosNoBanco, new String[]{"id"});

		IDataSet dadosSetEsperado1 = new FlatXmlDataSetBuilder().build(new FileInputStream("src/xml/criarCursoDataset.xml"));
		ITable dadosEsperados1 = dadosSetEsperado1.getTable("curso");

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
		bancoCarregado = new FlatXmlDataSetBuilder().build(new FileInputStream("src/xml/criarCursoDataset.xml"));
		return bancoCarregado;
	}

}

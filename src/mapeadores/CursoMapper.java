package mapeadores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import entidades.Curso;
import entidades.Departamento;

public class CursoMapper implements Mapper<Curso> {
	
	private static CursoMapper instance;
	
	private Connection connection;
	
	private CursoMapper() {
		connection = ConnectionFactory.getInstance().getConnection();
	}

	@Override
	public synchronized Curso encontrarPorId(Long id) throws SQLException {
		
		Curso curso = null;

		String sql = "SELECT id, sigla, nome, departamento_id FROM curso WHERE id = ?";
		
		long idCurso = 0;
		String siglaCurso = "";
		String nomeCurso = "";
		long idDepartamento = 0;
		Departamento departamento = null;
		
		PreparedStatement statement;
		statement = connection.prepareStatement(sql);
		statement.setLong(1, id);
		ResultSet resultado = statement.executeQuery();
		while(resultado.next()) {
			idCurso = resultado.getLong("id");
			siglaCurso = resultado.getString("sigla");
			nomeCurso = resultado.getString("nome");
			idDepartamento = resultado.getLong("departamento_id");
			departamento = (Departamento) DepartamentoMapper.getInstance().encontrarPorId(idDepartamento);
			curso = new Curso(idCurso, siglaCurso, nomeCurso, departamento);
		}		
		statement.close();		
		return curso;
	}
	
	public synchronized Curso encontrarPorSigla(String sigla) throws SQLException {
		Curso curso = null;
		
		String sql = "SELECT id, sigla, nome, departamento_id FROM curso WHERE sigla = ?";
		
		long idCurso = 0;
		String nomeCurso = "";
		long idDepartamento = 0;
		Departamento departamento = null;
		
		PreparedStatement statement;
		statement = connection.prepareStatement(sql);
		statement.setString(1, sigla);
		ResultSet resultado = statement.executeQuery();
		while(resultado.next()) {
			idCurso = resultado.getLong("id");
			nomeCurso = resultado.getString("nome");
			idDepartamento = resultado.getLong("departamento_id");
			departamento = (Departamento) DepartamentoMapper.getInstance().encontrarPorId(idDepartamento);
			curso = new Curso(idCurso, sigla, nomeCurso, departamento);
		}
		
		statement.close();		
		return curso;
	}

	@Override
	public synchronized void adicionar(Curso curso) throws SQLException {
		
		String sql = "INSERT INTO curso (sigla, nome, departamento_id) VALUES (?,?,?)";
				
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, (String) curso.getMapeadorAtributos().get("sigla"));
		statement.setString(2, (String) curso.getMapeadorAtributos().get("nome"));
		statement.setLong(3, (long) ((Departamento) curso.getMapeadorAtributos().get("departamento")).getMapeadorAtributos().get("id"));
		statement.execute();
		statement.close();
	}

	@Override
	public synchronized void atualizar(Curso curso) throws SQLException {		
		String sql = "UPDATE curso SET sigla = ?, nome = ?, departamento_id = ? WHERE id = ?";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, (String) curso.getMapeadorAtributos().get("sigla"));
		stmt.setString(2, (String) curso.getMapeadorAtributos().get("nome"));		
		stmt.setLong(3, (long) ((Departamento) curso.getMapeadorAtributos().get("departamento")).getMapeadorAtributos().get("id"));
		stmt.setLong(4, (long) curso.getMapeadorAtributos().get("id"));
		stmt.execute();
		stmt.close();		
	}

	@Override
	public synchronized void deletar(Curso curso) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public synchronized Collection<Curso> encontrarTodos() throws SQLException {
		
		Collection<Curso> cursos = new ArrayList<Curso>();
		
		String sql = "SELECT c.id as curso_id, c.sigla as curso_sigla, c.nome as curso_nome, "
				+ "d.id as departamento_id, d.sigla as departamento_sigla, d.nome as departamento_nome "
				+ "FROM curso c "
				+ "INNER JOIN departamento d ON c.departamento_id = d.id "
				+ "ORDER BY curso_sigla";
			
		PreparedStatement stmt = connection.prepareStatement(sql);			
		ResultSet results = stmt.executeQuery();
		
		while(results.next()) {
			
			long idCurso = results.getLong("curso_id");
			String siglaCurso = results.getString("curso_sigla");
			String nomeCurso = results.getString("curso_nome");
			long idDepartamento = results.getLong("departamento_id");
			String siglaDepartamento = results.getString("departamento_sigla");
			String nomeDepartamento = results.getString("departamento_nome");
			
			Departamento departamento = new Departamento(idDepartamento, siglaDepartamento, nomeDepartamento);
			
			Curso curso = new Curso(idCurso, siglaCurso, nomeCurso, departamento);
			cursos.add(curso);
		}
		
		stmt.close();
		return cursos;
	}
	
	public static synchronized CursoMapper getInstance() {
		if(instance == null) {
			instance = new CursoMapper();
		}
		return instance;
	}
}
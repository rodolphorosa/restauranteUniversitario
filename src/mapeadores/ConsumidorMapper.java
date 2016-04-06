package mapeadores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import entidades.Aluno;
import entidades.Consumidor;
import entidades.Curso;
import entidades.Departamento;
import entidades.Funcionario;
import entidades.Sexo;
import entidades.TipoConsumidor;
import entidades.Titulo;

public class ConsumidorMapper implements Mapper<Consumidor> {
	
	private static ConsumidorMapper instance;
	private static Connection connection;
	
	private ConsumidorMapper() {
		connection = ConnectionFactory.getInstance().getConnection();
	}

	@Override
	public synchronized Consumidor encontrarPorId(Long id) throws SQLException {
		
		Consumidor consumidor = null;
		
		String sql = "SELECT c.id AS id, c.cpf AS cpf, c.tipo AS tipo, c.nome AS nome, "
					+ "c.sexo AS sexo, c.matricula AS matricula, c.ano_ingresso AS ano, c.titulo AS titulo, "
					+ "CASE WHEN c.tipo='"+TipoConsumidor.ALUNO+"' THEN (select curso_id FROM aluno WHERE id = ?) "
					+ "WHEN c.tipo='"+TipoConsumidor.FUNCIONARIO+"' THEN (select departamento_id FROM funcionario WHERE id = ?) "
					+ "END AS fk_id "
					+ "FROM consumidor c "
					+ "WHERE c.id = ?";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setLong(1, id);
		stmt.setLong(2, id);
		stmt.setLong(3, id);
		
		ResultSet resultado = stmt.executeQuery();
		
		while(resultado.next()) {
			
			String nome = resultado.getString("nome");
			String cpf = resultado.getString("cpf");
			String matricula = resultado.getString("matricula");
			Sexo sexo = Sexo.valueOf(resultado.getString("sexo"));
			int anoIngresso = resultado.getInt("ano");
			Titulo titulo = Titulo.valueOf(resultado.getString("titulo"));
			Long fk_id = resultado.getLong("fk_id");
			TipoConsumidor tipoConsumidor = TipoConsumidor.valueOf(resultado.getString("tipo"));
			
			if(tipoConsumidor.equals(TipoConsumidor.ALUNO)) {
				Curso curso = CursoMapper.getInstance().encontrarPorId(fk_id);
				consumidor = new Aluno(id, nome, cpf, matricula, sexo, anoIngresso, titulo, curso);
			} else {
				Departamento departamento = DepartamentoMapper.getInstance().encontrarPorId(fk_id);
				consumidor = new Funcionario(id, nome, cpf, matricula, sexo, anoIngresso, titulo, departamento);
			}
		}
		stmt.close();
		
		return consumidor;
	}
	
	public synchronized Consumidor encontrarPorCpf(String cpf) throws SQLException {		
		Consumidor consumidor = null;
		
		String sql = "SELECT c.id AS id, c.cpf AS cpf, c.tipo AS tipo, c.nome AS nome, "
					+ "c.sexo AS sexo, c.matricula AS matricula, c.ano_ingresso AS ano, c.titulo AS titulo, "
					+ "CASE WHEN c.tipo='"+TipoConsumidor.ALUNO+"' THEN (select curso_id FROM aluno WHERE cpf = ?) "
					+ "WHEN c.tipo='"+TipoConsumidor.FUNCIONARIO+"' THEN (select departamento_id FROM funcionario WHERE cpf = ?) "
					+ "END AS fk_id "
					+ "FROM consumidor c "
					+ "WHERE c.cpf = ?";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, cpf);
		stmt.setString(2, cpf);
		stmt.setString(3, cpf);
		
		ResultSet resultado = stmt.executeQuery();
		
		while(resultado.next()) {
			
			Long id = resultado.getLong("id");
			String nome = resultado.getString("nome");
			String matricula = resultado.getString("matricula");
			Sexo sexo = Sexo.valueOf(resultado.getString("sexo"));
			int anoIngresso = resultado.getInt("ano");
			Titulo titulo = Titulo.valueOf(resultado.getString("titulo"));
			Long fk_id = resultado.getLong("fk_id");
			TipoConsumidor tipoConsumidor = TipoConsumidor.valueOf(resultado.getString("tipo"));
			
			if(tipoConsumidor.equals(TipoConsumidor.ALUNO)) {
				Curso curso = CursoMapper.getInstance().encontrarPorId(fk_id);
				consumidor = new Aluno(id, nome, cpf, matricula, sexo, anoIngresso, titulo, curso);
			} else {
				Departamento departamento = DepartamentoMapper.getInstance().encontrarPorId(fk_id);
				consumidor = new Funcionario(id, nome, cpf, matricula, sexo, anoIngresso, titulo, departamento);
			}
		}
		stmt.close();		
		return consumidor;	
	}
	
	public synchronized Consumidor encontrarPorMatricula(String matricula) throws SQLException {		
		Consumidor consumidor = null;
		
		String sql = "SELECT c.id AS id, c.cpf AS cpf, c.tipo AS tipo, c.nome AS nome, "
					+ "c.sexo AS sexo, c.matricula AS matricula, c.ano_ingresso AS ano, c.titulo AS titulo, "
					+ "CASE WHEN c.tipo='"+TipoConsumidor.ALUNO+"' THEN (select curso_id FROM aluno WHERE cpf = c.cpf) "
					+ "WHEN c.tipo='"+TipoConsumidor.FUNCIONARIO+"' THEN (select departamento_id FROM funcionario WHERE cpf = c.cpf) "
					+ "END AS fk_id "
					+ "FROM consumidor c "
					+ "WHERE c.matricula = ?";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, matricula);
		
		ResultSet resultado = stmt.executeQuery();
		
		while(resultado.next()) {
			
			Long id = resultado.getLong("id");
			String nome = resultado.getString("nome");
			String cpf = resultado.getString("cpf");
			Sexo sexo = Sexo.valueOf(resultado.getString("sexo"));
			int anoIngresso = resultado.getInt("ano");
			Titulo titulo = Titulo.valueOf(resultado.getString("titulo"));
			Long fk_id = resultado.getLong("fk_id");
			TipoConsumidor tipoConsumidor = TipoConsumidor.valueOf(resultado.getString("tipo"));
			
			if(tipoConsumidor.equals(TipoConsumidor.ALUNO)) {
				Curso curso = CursoMapper.getInstance().encontrarPorId(fk_id);
				consumidor = new Aluno(id, nome, cpf, matricula, sexo, anoIngresso, titulo, curso);
			} else {
				Departamento departamento = DepartamentoMapper.getInstance().encontrarPorId(fk_id);
				consumidor = new Funcionario(id, nome, cpf, matricula, sexo, anoIngresso, titulo, departamento);
			}
		}
		stmt.close();
		return consumidor;
	}

	@Override
	public synchronized void adicionar(Consumidor consumidor) throws SQLException {
		
		String sqlConsumidor = "INSERT INTO consumidor (cpf, tipo, nome, matricula, sexo, ano_ingresso, titulo) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement stmtConsumidor = connection.prepareStatement(sqlConsumidor);
		
		stmtConsumidor.setString(1, (String) consumidor.getMapeadorAtributos().get("cpf"));
		stmtConsumidor.setString(3, (String) consumidor.getMapeadorAtributos().get("nome"));
		stmtConsumidor.setString(4, (String) consumidor.getMapeadorAtributos().get("matricula"));
		stmtConsumidor.setString(5, ((Sexo) consumidor.getMapeadorAtributos().get("sexo")).name());
		stmtConsumidor.setInt(6, (int) consumidor.getMapeadorAtributos().get("anoIngresso"));
		stmtConsumidor.setString(7, ((Titulo) consumidor.getMapeadorAtributos().get("titulo")).name());
		
		if(consumidor instanceof Aluno) {
			stmtConsumidor.setString(2, TipoConsumidor.ALUNO.name());
			stmtConsumidor.execute();
			AlunoMapper.getInstance().adicionar((Aluno)consumidor);
		} else {
			stmtConsumidor.setString(2, TipoConsumidor.FUNCIONARIO.name());
			stmtConsumidor.execute();
			FuncionarioMapper.getInstance().adicionar((Funcionario)consumidor);
		}		
		
		stmtConsumidor.close();
	}

	@Override
	public synchronized void atualizar(Consumidor consumidor) throws SQLException {
		String sql = "UPDATE consumidor SET nome = ?, matricula = ?, sexo = ?, ano_ingresso = ? WHERE cpf = ?";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, (String) consumidor.getMapeadorAtributos().get("nome"));
		stmt.setString(2, (String) consumidor.getMapeadorAtributos().get("matricula"));
		stmt.setString(3, consumidor.getMapeadorAtributos().get("sexo").toString());
		stmt.setInt(4, (int) consumidor.getMapeadorAtributos().get("anoIngresso"));
		stmt.setString(5, (String) consumidor.getMapeadorAtributos().get("cpf"));
		stmt.execute();
		stmt.close();
	}

	@Override
	public synchronized void deletar(Consumidor consumidor) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public synchronized Collection<Consumidor> encontrarTodos() throws SQLException {
		Collection<Consumidor> consumidores = new ArrayList<Consumidor>();
		
		String sql = "SELECT c.id AS id, c.cpf AS cpf, c.nome AS nome, c.matricula AS matricula, "
					+ "c.ano_ingresso AS ano, c.sexo AS sexo, c.titulo AS titulo, c.tipo AS tipo,"
					+ "CASE WHEN c.tipo = 'FUNCIONARIO' THEN f.departamento_id "
					+ "WHEN c.tipo = 'ALUNO' THEN a.curso_id "
					+ "END AS fk_id "
					+ "FROM consumidor c "
					+ "LEFT JOIN aluno a ON a.id = c.id "
					+ "LEFT JOIN funcionario f ON f.id = c.id";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet resultados = stmt.executeQuery();
		
		while(resultados.next()) {
			
			TipoConsumidor tipoConsumidor = TipoConsumidor.valueOf(resultados.getString("tipo"));
			Long id = resultados.getLong("id");
			String nome = resultados.getString("nome");
			String cpf = resultados.getString("cpf");
			String matricula = resultados.getString("matricula");
			Sexo sexo = Sexo.valueOf(resultados.getString("sexo"));
			int anoIngresso = resultados.getInt("ano");
			Titulo titulo = Titulo.valueOf(resultados.getString("titulo"));
			Long fk_id = resultados.getLong("fk_id");
			
			if(tipoConsumidor.equals(TipoConsumidor.ALUNO)) {
				Curso curso = CursoMapper.getInstance().encontrarPorId(fk_id);
				Consumidor aluno = new Aluno(id, nome, cpf, matricula, sexo, anoIngresso, titulo, curso);
				consumidores.add(aluno);
			} else {
				Departamento departamento = DepartamentoMapper.getInstance().encontrarPorId(fk_id);
				Consumidor funcionario = new Funcionario(id, nome, cpf, matricula, sexo, anoIngresso, titulo, departamento);
				consumidores.add(funcionario);
			}			
		}		
		return consumidores;
	}
	
	public static synchronized ConsumidorMapper getInstance() {
		if(instance == null) {
			instance = new ConsumidorMapper();
		}
		return instance;
	}
}
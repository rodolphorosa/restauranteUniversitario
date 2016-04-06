package mapeadores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

import entidades.Aluno;
import entidades.Curso;

public class AlunoMapper implements Mapper<Aluno> {
	
	private static AlunoMapper instance;	
	private static Connection connection;
	
	private AlunoMapper() {
		connection = ConnectionFactory.getInstance().getConnection();
	}

	@Override
	public synchronized Aluno encontrarPorId(Long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized void adicionar(Aluno aluno) throws SQLException {
		String sql = "INSERT INTO aluno (id, cpf, curso_id) VALUES ((SELECT id FROM consumidor WHERE cpf = ?), ?,?)";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, (String) aluno.getMapeadorAtributos().get("cpf"));
		stmt.setString(2, (String) aluno.getMapeadorAtributos().get("cpf"));
		stmt.setLong(3, (long) ((Curso) aluno.getMapeadorAtributos().get("curso")).getMapeadorAtributos().get("id"));
		
		
		stmt.execute();
		stmt.close();		
	}

	@Override
	public synchronized void atualizar(Aluno aluno) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public synchronized void deletar(Aluno aluno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public synchronized Collection<Aluno> encontrarTodos() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static synchronized AlunoMapper getInstance() {
		if(instance==null) {
			instance = new AlunoMapper();
		}
		return instance;
	}
}
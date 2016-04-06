package mapeadores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

import entidades.Departamento;
import entidades.Funcionario;

public class FuncionarioMapper implements Mapper<Funcionario> {
	
	private static FuncionarioMapper instance;
	private static Connection connection;
	
	public FuncionarioMapper() {
		connection = ConnectionFactory.getInstance().getConnection();
	}

	@Override
	public synchronized Funcionario encontrarPorId(Long id)	throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized void adicionar(Funcionario funcionario) throws SQLException {
		String sql = "INSERT INTO funcionario (id, cpf, departamento_id) VALUES ((SELECT id FROM consumidor WHERE cpf = ?), ?,?)";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		stmt.setString(1, (String) funcionario.getMapeadorAtributos().get("cpf"));
		stmt.setString(2, (String) funcionario.getMapeadorAtributos().get("cpf"));
		stmt.setLong(3, (long) ((Departamento) funcionario.getMapeadorAtributos().get("departamento")).getMapeadorAtributos().get("id"));
		
		stmt.execute();
		stmt.close();
		
	}

	@Override
	public synchronized void atualizar(Funcionario funcionario) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public synchronized void deletar(Funcionario funcionario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public synchronized Collection<Funcionario> encontrarTodos() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static synchronized FuncionarioMapper getInstance() {
		if(instance==null) {
			instance = new FuncionarioMapper();
		}
		return instance;
	}
}
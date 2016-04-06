package mapeadores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import entidades.Departamento;

public class DepartamentoMapper implements Mapper<Departamento> {
	
	private static DepartamentoMapper instance;
	private static Connection connection;
	
	private DepartamentoMapper() {
		connection = ConnectionFactory.getInstance().getConnection();
	}
	
	@Override
	public synchronized Departamento encontrarPorId(Long id) throws SQLException {
		Departamento departamento = null;
		String sql = "SELECT id, sigla, nome FROM departamento WHERE id = ?";
		String siglaDepartamento = "";
		String nomeDepartamento = "";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, id);
		ResultSet resultados = statement.executeQuery();
		while(resultados.next()) {			
			siglaDepartamento = resultados.getString("sigla");
			nomeDepartamento = resultados.getString("nome");
			departamento = new Departamento(id, siglaDepartamento, nomeDepartamento);
		}
		statement.close();
		return departamento;
	}
	
	public synchronized Departamento encontrarPorSigla(String sigla) throws SQLException {
		Departamento departamento = null;
		String sql = "SELECT id, sigla, nome FROM departamento WHERE sigla = ?";
		Long idDepartamento = null;
		String nomeDepartamento = "";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, sigla);
		ResultSet resultados = statement.executeQuery();
		while(resultados.next()) {
			idDepartamento = Long.valueOf(resultados.getString("id"));
			nomeDepartamento = resultados.getString("nome");
			departamento = new Departamento(idDepartamento, sigla, nomeDepartamento);
		}
		statement.close();
		return departamento;
	}

	@Override
	public synchronized void adicionar(Departamento departamento) throws SQLException {
		String sql = "INSERT INTO departamento (sigla, nome) VALUES (?, ?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, (String) departamento.getMapeadorAtributos().get("sigla"));
		stmt.setString(2, (String) departamento.getMapeadorAtributos().get("nome"));
		stmt.execute();
		stmt.close();
	}

	@Override
	public synchronized void atualizar(Departamento departamento) throws SQLException {
		String sql = "UPDATE departamento SET sigla = ?, nome = ? WHERE id = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, (String) departamento.getMapeadorAtributos().get("sigla"));
		stmt.setString(2, (String) departamento.getMapeadorAtributos().get("nome"));
		stmt.setLong(3, (long) departamento.getMapeadorAtributos().get("id"));
		stmt.execute();
		stmt.close();		
	}

	@Override
	public synchronized void deletar(Departamento departamento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public synchronized Collection<Departamento> encontrarTodos() throws SQLException {
		
		Collection<Departamento> departamentos = new ArrayList<Departamento>();
		
		String sql = "SELECT id, sigla, nome FROM departamento";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet results = stmt.executeQuery();
		
		while(results.next()) {
			
			long idDepartamento = results.getLong("id");
			String siglaDepartamento = results.getString("sigla");
			String nomeDepartamento = results.getString("nome");
			
			Departamento departamento = new Departamento(idDepartamento, siglaDepartamento, nomeDepartamento);
			
			departamentos.add(departamento);
		}
		
		stmt.close();
		return departamentos;
	}
	
	public static synchronized DepartamentoMapper getInstance() {
		if(instance==null) {
			instance = new DepartamentoMapper();
		}
		return instance;
	}
}
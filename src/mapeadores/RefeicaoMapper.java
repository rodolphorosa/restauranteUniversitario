package mapeadores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import entidades.Refeicao;
import entidades.TipoRefeicao;
import entidades.Turno;

public class RefeicaoMapper implements Mapper<Refeicao> {
	
	private static RefeicaoMapper instance;
	private static Connection connection;
	
	private RefeicaoMapper() {
		connection = ConnectionFactory.getInstance().getConnection();
	}

	@Override
	public synchronized Refeicao encontrarPorId(Long id) throws SQLException {
		
		Refeicao refeicao = null;
		
		String sql = "SELECT id, tipo, turno, descricao, opcao_vegetariana, data FROM refeicao "
					+ "WHERE refeicao.id = ?";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setLong(1, id);
		ResultSet resultado = stmt.executeQuery();
		
		while(resultado.next()) {
			TipoRefeicao tipo = TipoRefeicao.valueOf(resultado.getString("tipo"));
			Turno turno = Turno.valueOf(resultado.getString("turno"));
			String descricao = resultado.getString("descricao");
			String opcaoVegetariana = resultado.getString("opcao_vegetariana");
			String data = resultado.getString("data");
			
			refeicao = new Refeicao(id, turno, tipo, descricao, opcaoVegetariana, data);
		}		
		stmt.close();		
		return refeicao;
	}

	@Override
	public synchronized void adicionar(Refeicao refeicao) throws SQLException {
		String sql = "INSERT INTO refeicao (tipo, turno, descricao, opcao_vegetariana, data) "
					+ "VALUES (?, ?, ?, ?, ?)";
		
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, refeicao.getMapeadorAtributos().get("tipo").toString());
		stmt.setString(2, refeicao.getMapeadorAtributos().get("turno").toString());
		stmt.setString(3, (String) refeicao.getMapeadorAtributos().get("descricao"));
		stmt.setString(4, (String) refeicao.getMapeadorAtributos().get("opcaoVegetariana"));
		stmt.setDate(5, java.sql.Date.valueOf((String) refeicao.getMapeadorAtributos().get("data")));
		
		stmt.execute();
		stmt.close();
		
	}

	@Override
	public synchronized void atualizar(Refeicao refeicao) throws SQLException {

		String sql = "UPDATE refeicao SET descricao = ?, opcao_vegetariana = ? "
					+ "WHERE refeicao.id = ?";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, (String) refeicao.getMapeadorAtributos().get("descricao"));
		stmt.setString(2, (String) refeicao.getMapeadorAtributos().get("opcaoVegetariana"));
		stmt.setLong(3, (long) refeicao.getMapeadorAtributos().get("id"));
		
		stmt.execute();
		stmt.close();
	}

	@Override
	public synchronized void deletar(Refeicao refeicao) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public synchronized Collection<Refeicao> encontrarTodos() throws SQLException {
		Collection<Refeicao> refeicoes = new ArrayList<Refeicao>();
		
		String sql = "SELECT id, tipo, turno, descricao, opcao_vegetariana, data FROM refeicao";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet resultados = stmt.executeQuery();
		
		while(resultados.next()) {			
			Long id = resultados.getLong("id");
			TipoRefeicao tipo = TipoRefeicao.valueOf(resultados.getString("tipo"));
			Turno turno = Turno.valueOf(resultados.getString("turno"));
			String descricao = resultados.getString("descricao");
			String opcaoVegetariana = resultados.getString("opcao_vegetariana");
			String data = resultados.getString("data");
			
			Refeicao refeicao = new Refeicao(id, turno, tipo, descricao, opcaoVegetariana, data);
			refeicoes.add(refeicao);
		}
		
		stmt.close();		
		return refeicoes;
	}
	
	public static RefeicaoMapper getInstance() {
		if(instance==null) {
			instance = new RefeicaoMapper();
		}
		return instance;
	}
}
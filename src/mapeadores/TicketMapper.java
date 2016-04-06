package mapeadores;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import entidades.Consumidor;
import entidades.Refeicao;
import entidades.Ticket;

public class TicketMapper implements Mapper<Ticket> {
	
	private static TicketMapper instance;
	private static Connection connection;
	
	private TicketMapper() {
		connection = ConnectionFactory.getInstance().getConnection();
	}
	

	@Override
	public synchronized Ticket encontrarPorId(Long id) throws SQLException {
		Ticket ticket = null;
		
		String sql = "SELECT id, refeicao_id, consumidor_id, preco, data_compra, situacao_compra "
				+ "FROM ticket WHERE id = ?";
	
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setLong(1, id);
		ResultSet resultados = stmt.executeQuery();
		while(resultados.next()) {
			Long refeicao_id = Long.valueOf(resultados.getString("refeicao_id"));
			Long consumidor_id = Long.valueOf(resultados.getString("consumidor_id"));
			BigDecimal preco = new BigDecimal(resultados.getString("preco"));
			String dataCompra = resultados.getString("data_compra");
			boolean vendido = resultados.getBoolean("situacao_compra");
			
			Refeicao refeicao = RefeicaoMapper.getInstance().encontrarPorId(refeicao_id);
			Consumidor consumidor = ConsumidorMapper.getInstance().encontrarPorId(consumidor_id);
			
			ticket = new Ticket(id, refeicao, consumidor, preco, dataCompra, vendido);
		}
		stmt.close();
		return ticket;
	}

	@Override
	public synchronized void adicionar(Ticket ticket) throws SQLException {

		String sql = "INSERT INTO ticket (refeicao_id, consumidor_id, preco, data_compra, situacao_compra) "
					+ "VALUES (?, ?, ?, ?, ?)";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		stmt.setLong(1, (long) ((Refeicao) ticket.getMapeadorAtributos().get("refeicao")).getMapeadorAtributos().get("id"));
		stmt.setLong(2, (long) ((Consumidor) ticket.getMapeadorAtributos().get("consumidor")).getMapeadorAtributos().get("id"));
		stmt.setBigDecimal(3, new BigDecimal(ticket.getMapeadorAtributos().get("preco").toString()));
		stmt.setDate(4, java.sql.Date.valueOf((String) ticket.getMapeadorAtributos().get("dataCompra")));
		stmt.setBoolean(5, (boolean) ticket.getMapeadorAtributos().get("vendido"));
		
		stmt.execute();
		stmt.close();
		
	}

	@Override
	public synchronized void atualizar(Ticket ticket) throws SQLException {
		String sql = "UPDATE ticket SET situacao_compra = ? WHERE id = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setBoolean(1, (Boolean) ticket.getMapeadorAtributos().get("vendido"));
		stmt.setLong(2, (Long) ticket.getMapeadorAtributos().get("id"));
		stmt.execute();
		stmt.close();		
	}

	@Override
	public synchronized void deletar(Ticket ticket) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public synchronized Collection<Ticket> encontrarTodos() throws SQLException {
		Collection<Ticket> tickets = new ArrayList<Ticket>();
		
		String sql = "SELECT id, refeicao_id, consumidor_id, preco, data_compra, situacao_compra "
					+ "FROM ticket";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet resultados = stmt.executeQuery();
		
		while(resultados.next()) {
			Long id = Long.valueOf(resultados.getString("id"));
			Long refeicao_id = Long.valueOf(resultados.getString("refeicao_id"));
			Long consumidor_id = Long.valueOf(resultados.getString("consumidor_id"));
			BigDecimal preco = new BigDecimal(resultados.getString("preco"));
			String dataCompra = resultados.getString("data_compra");
			boolean vendido = resultados.getBoolean("situacao_compra");
			
			Refeicao refeicao = RefeicaoMapper.getInstance().encontrarPorId(refeicao_id);
			Consumidor consumidor = ConsumidorMapper.getInstance().encontrarPorId(consumidor_id);
			
			Ticket ticket = new Ticket(id, refeicao, consumidor, preco, dataCompra, vendido);
			tickets.add(ticket);
		}
		stmt.close();
		return tickets;
	}
	
	public synchronized static TicketMapper getInstance() {
		if(instance==null){
			instance = new TicketMapper();
		}
		return instance;
	}

}

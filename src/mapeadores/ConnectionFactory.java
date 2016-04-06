package mapeadores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	//private static String url = "jdbc:derby:comp3;create=true";
	
	private static ConnectionFactory instance;
	
	private static String url = "jdbc:postgresql://localhost/?user=postgres&password=rodolpho";
	
	private ConnectionFactory() {
		try {
			//Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void criarBanco() {
		Connection connection;
		try {
			connection = DriverManager.getConnection(url);
			
			try {
				
			} catch (Exception e) {
				
			} finally {
				
			}
			
		} catch (SQLException e) {
			
		}
	}
	
	public Connection getConnection() {
		Connection connection;
		try {
			connection = DriverManager.getConnection(url);
			
			return connection;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static synchronized ConnectionFactory getInstance() {
		if(instance == null) {
			instance = new ConnectionFactory();
		}
		return instance;
	}
}
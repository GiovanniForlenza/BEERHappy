package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DriverManagerConnectionPool {
	private static List<Connection> freeDbConnection;
	static {

		freeDbConnection = new LinkedList<Connection>();

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch (ClassNotFoundException e){
			System.out.println("DB connection not found " + e.getMessage());
		}

	}
	public DriverManagerConnectionPool (){
		freeDbConnection = new LinkedList<Connection>();
	}
	private synchronized static Connection createDBConnection() throws SQLException {
		Connection newConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/beerhappy", "root", "Amministratore");
/*
		Connection newConnection = null;
		String ip = "localhost";
		String port = "3306";
		String db = "storage?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String username = "beerhappy";
		String password = "admin";

		System.out.println("prima di effettuare le connessione");

		newConnection = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + db, username, password);
*/
		newConnection.setAutoCommit(false);
		return newConnection;
	}
	public static synchronized Connection getConnection() throws SQLException {
		Connection connection;

		if (!freeDbConnection.isEmpty()) {
			connection = (Connection) freeDbConnection.get(0);
			freeDbConnection.remove(0);

			try {
				if (connection.isClosed())
					connection = getConnection();
			} catch (SQLException e) {
				connection.close();
				connection = getConnection();
			}
		} else {
			connection = createDBConnection();
		}

		return connection;
	}
	public synchronized static void releaseConnection(Connection connection) throws SQLException {
		if(connection != null)
			freeDbConnection.add(connection);
	}
}

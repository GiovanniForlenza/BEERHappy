package model;

import entity.Utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModelSecurity implements Security{

	@Override
	public void addUser(Utente utente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO utente" + "(nome, cognome, email, password) values (?,?,?,?)";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);

			preparedStatement.setString(1, utente.getNome());
			preparedStatement.setString(2, utente.getCognome());
			preparedStatement.setString(3, utente.getEmail());
			preparedStatement.setString(4, utente.getPassword());

			//print sql query
			//System.out.println("addUser: "+ preparedStatement.toString());

			preparedStatement.executeUpdate();
			System.out.println("Insert Query update");
			connection.commit();
		}finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

	@Override
	public boolean controlloAccesso (String email, String password) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "SELECT * FROM utente WHERE email = ? AND password = ?";
		Utente utente = new Utente();


		try{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				utente.setNome(resultSet.getString("nome"));
				utente.setCognome(resultSet.getString("cognome"));
				utente.setEmail(resultSet.getString("email"));
				utente.setPassword(resultSet.getString("password"));
			}

			System.out.println(resultSet.toString());
			return controlloUtente(utente, email, password);
		}finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

	public boolean controlloEmailRegistrazione(Utente utente) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "SELECT * FROM utente WHERE email = ?";
		try{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, utente.getEmail());

			ResultSet resultSet = preparedStatement.executeQuery();

			String email = null;

			while (resultSet.next())
				email = resultSet.getString("email");

			if(utente.getEmail().equals(email)){
				return false;
			}
			else {
				return true;
			}
		}finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

	public boolean controlloUtente (Utente utente, String email, String password){

		if(utente.getEmail() == null || utente.getPassword() == null) {
			return false;
		}else {
			return true;
		}
	}
}

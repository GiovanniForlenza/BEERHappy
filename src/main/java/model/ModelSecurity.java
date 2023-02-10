package model;

import entity.Prodotto;
import entity.Utente;
import entity.UtenteBO;

import javax.swing.*;
import java.awt.dnd.DragGestureRecognizer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

public class ModelSecurity implements Security {
	Utente utente = new Utente();

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


			preparedStatement.executeUpdate();
			System.out.println("Insert Query update");
			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

	public Utente loginUtente(String email, String password) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "SELECT * FROM utente WHERE email = ? AND password = ?";
		Utente utente = null;
		ResultSet resultSet;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				utente=new Utente();
				utente.setNome(resultSet.getString("nome"));
				utente.setCognome(resultSet.getString("cognome"));
				utente.setEmail(resultSet.getString("email"));
				utente.setPassword(resultSet.getString("password"));
			}
			if (preparedStatement != null)
				preparedStatement.close();
		}finally {
			DriverManagerConnectionPool.releaseConnection(connection);
		}
		return utente;
	}

	public boolean controlloEmailRegistrazione(Utente utente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "SELECT * FROM utente WHERE email = ?";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, utente.getEmail());

			ResultSet resultSet = preparedStatement.executeQuery();

			String email = null;

			while (resultSet.next())
				email = resultSet.getString("email");

			if (utente.getEmail().equals(email)) {
				return false;
			} else {
				return true;
			}
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}



	public void removeUser(Utente utente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		System.out.println("Qui ci arrivo");
		String query = "delete from utente WHERE email = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, utente.getEmail());

			System.out.println("removeUser:" + preparedStatement.toString());
			preparedStatement.executeUpdate();
			connection.commit();


		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

	}

	public boolean cercaUtente(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "select * from utente WHERE email = ?";
		boolean trovato=false;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next())
				trovato=true;
			if (preparedStatement != null)
				preparedStatement.close();
		} finally {
			DriverManagerConnectionPool.releaseConnection(connection);
		}
		return trovato;
	}
	public String recuperoPassword(String email) throws SQLException {
		String passwordMomentanea=null;
		if(cercaUtente(email)){
			System.out.println("L'e-mail la trova:"+email);

			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ModelSecurity ms=new ModelSecurity();
			passwordMomentanea= ms.generaPassword();
			System.out.println(passwordMomentanea);
			String query = "update utente set password = ? where email = ?";
			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, passwordMomentanea);
				preparedStatement.setString(2, email);

				preparedStatement.executeUpdate();
			} finally {
				if (preparedStatement != null)
					preparedStatement.close();
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return passwordMomentanea;
	}
	public String generaPassword() {
		String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
		String numbers = "0123456789";

		String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;
		StringBuilder sb = new StringBuilder();
		Random random = new Random();

		int length = 10;

		for (int i = 0; i < length; i++) {
			int index = random.nextInt(alphaNumeric.length());
			char randomChar = alphaNumeric.charAt(index);
			sb.append(randomChar);
		}

		String randomString = sb.toString();
		return randomString;
	}
	public UtenteBO loginAmministratore(String email, String password) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement=null;
		String query = "SELECT * FROM utenteBO WHERE email = ? AND password = ?";
		UtenteBO utenteBO = null;
		ResultSet rs;


		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			rs = preparedStatement.executeQuery();

			if (rs.next()) {
				utenteBO=new UtenteBO();
				utenteBO.setEmail(rs.getString("email"));
				utenteBO.setPassword(rs.getString("password"));
				utenteBO.setRuolo(rs.getInt("ruolo"));
			}
			if (preparedStatement != null)
				preparedStatement.close();
		} finally {
			DriverManagerConnectionPool.releaseConnection(connection);
		}
		return  utenteBO;
	}

	public boolean cercaUtenteBO(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "select * from utenteBO WHERE email = ?";
		boolean trovato=false;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next())
				trovato=true;
			if (preparedStatement != null)
				preparedStatement.close();
		} finally {
			DriverManagerConnectionPool.releaseConnection(connection);
		}
		return trovato;
	}

	public String recuperoPasswordUtenteBO(String email) throws SQLException {
		String passwordMomentanea=null;
		if(cercaUtenteBO(email)){
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			System.out.println(passwordMomentanea);
			String query = "update utenteBO set password = ? where email = ?";
			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, passwordMomentanea);
				preparedStatement.setString(2, email);

				preparedStatement.executeUpdate();

				if (preparedStatement != null)
					preparedStatement.close();
				DriverManagerConnectionPool.releaseConnection(connection);
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return passwordMomentanea;
	}
	public void modificaDati(Utente utente, String nome, String cognome) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "UPDATE utente SET nome = ?, cognome = ?, email = ?, password = ? WHERE email = ?";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, nome);
			preparedStatement.setString(2, cognome);
			preparedStatement.setString(3, utente.getEmail());
			preparedStatement.setString(4, utente.getPassword());
			preparedStatement.setString(5, utente.getEmail());

			preparedStatement.executeUpdate();
			connection.commit();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

	}

	public void cambioPassword(Utente utente, String password) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "UPDATE utente SET nome = ?, cognome = ?, email = ?, password = ? WHERE email = ?";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, utente.getNome());
			preparedStatement.setString(2, utente.getCognome());
			preparedStatement.setString(3, utente.getEmail());
			preparedStatement.setString(4, password);
			preparedStatement.setString(5, utente.getEmail());

			preparedStatement.executeUpdate();
			connection.commit();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

	}
}
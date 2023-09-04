package model.dao;

import model.bean.Utente;
import model.bean.UtenteBO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class UserModel{
	Utente utente = new Utente();

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public void addUser(Utente utente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO utente" + "(nome, cognome, email, password) values (?,?,?,?)";

		if(utente != null){
			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL);

				preparedStatement.setString(1, utente.getNome());
				preparedStatement.setString(2, utente.getCognome());
				preparedStatement.setString(3, utente.getEmail());
				preparedStatement.setString(4, utente.getPassword());


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
	}

	public Utente loginUtente(String email, String password) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "SELECT * FROM utente WHERE email = ? AND password = ?";
		Utente utente = null;
		ResultSet resultSet;

		if(email != null && password != null) {
			if(controlloFormatoUtente(email,password)) {
				try {
					connection = DriverManagerConnectionPool.getConnection();
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, email);
					preparedStatement.setString(2, password);

					resultSet = preparedStatement.executeQuery();

					if (resultSet.next()) {
						utente = new Utente();
						utente.setNome(resultSet.getString("nome"));
						utente.setCognome(resultSet.getString("cognome"));
						utente.setEmail(resultSet.getString("email"));
						utente.setPassword(resultSet.getString("password"));
					}
					return utente;
				} finally {
					try {
						if (preparedStatement != null)
							preparedStatement.close();
					} finally {
						DriverManagerConnectionPool.releaseConnection(connection);
					}
				}
			}
			return null;
		}
		return null;
	}

	public boolean controlloEmailRegistrazione(Utente utente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "SELECT * FROM utente WHERE email = ?";
		if(utente != null) {
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
		return true;
	}



	public void removeUser(Utente utente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "delete from utente WHERE email = ?";

		if(utente != null) {
			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(query);

				preparedStatement.setString(1, utente.getEmail());

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

	}

	public boolean cercaUtente(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "select * from utente WHERE email = ?";
		boolean trovato=false;
		if(email != null) {
			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, email);
				ResultSet rs = preparedStatement.executeQuery();

				if (rs.next()) {
					trovato = true;
				}
				return trovato;

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
		}
		return false;
	}
	public String recuperoPassword(String email) throws SQLException {
		String passwordMomentanea=null;
		if(cercaUtente(email)){
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			UserModel ms=new UserModel();
			passwordMomentanea= ms.generaPassword();
			String query = "update utente set password = ? where email = ?";
			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, passwordMomentanea);
				preparedStatement.setString(2, email);

				preparedStatement.executeUpdate();
				connection.commit();
				return passwordMomentanea;
			} catch (SQLException e){
				e.printStackTrace();
			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
		}
		return null;
	}
	public static String generaPassword() {
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

		if (email != null && password != null) {
			if(controlloFormatoAmministratore(email, password)){
				try {
					connection = DriverManagerConnectionPool.getConnection();
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, email);
					preparedStatement.setString(2, password);
					rs = preparedStatement.executeQuery();

					if (rs.next()) {
						utenteBO = new UtenteBO();
						utenteBO.setEmail(rs.getString("email"));
						utenteBO.setPassword(rs.getString("password"));
						utenteBO.setRuolo(rs.getInt("ruolo"));
					}

					return utenteBO;
				} finally {
					try {
						if (preparedStatement != null)
							preparedStatement.close();
					} finally {
						DriverManagerConnectionPool.releaseConnection(connection);
					}
				}
			}
			return null;
		}
		return null;
	}

	public boolean cercaUtenteBO(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "select * from utenteBO WHERE email = ?";
		boolean trovato=false;
		if(email != null) {
			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, email);
				ResultSet rs = preparedStatement.executeQuery();

				if (rs.next()) {
					trovato = true;
				}

				return trovato;

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
		}
		return false;

	}

	public Utente modificaDati(Utente utente, String nome, String cognome) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "UPDATE utente SET nome = ?, cognome = ?, email = ?, password = ? WHERE email = ?";
		Utente user = utente;
		if(utente != null && nome != null && cognome != null) {
			if(controlloFormatoModifica(nome, cognome)) {
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

					user.setNome(nome);
					user.setCognome(cognome);

					return user;

				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						if (preparedStatement != null)
							preparedStatement.close();
					} finally {
						DriverManagerConnectionPool.releaseConnection(connection);
					}
				}
			}
			return null;
		}
		return null;
	}

	public boolean cambioPassword(Utente utente, String password) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "UPDATE utente SET nome = ?, cognome = ?, email = ?, password = ? WHERE email = ?";

		if(utente != null && password != null) {
			if(password.length() >= 8 && password.length() <= 30) {
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
					return true;
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						if (preparedStatement != null)
							preparedStatement.close();
					} finally {
						DriverManagerConnectionPool.releaseConnection(connection);
					}
				}
			}
			return false;
		}
		return false;
	}

	public ArrayList<UtenteBO> recuperoUtentiBO() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement=null;
		String query = "SELECT * FROM utenteBO WHERE ruolo!=4";
		ArrayList<UtenteBO> utenti=new ArrayList<>();
		ResultSet rs;

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(query);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				UtenteBO utenteBO=new UtenteBO();
				utenteBO.setEmail(rs.getString("email"));
				utenteBO.setPassword(rs.getString("password"));
				utenteBO.setRuolo(rs.getInt("ruolo"));
				utenti.add(utenteBO);
			}

			return  utenti;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

		return null;
	}

	public void updateRoles(UtenteBO utenteBO, int ruolo) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "update utenteBO set ruolo = ? where email = ?";

		if(utenteBO != null && ruolo > 0 && ruolo < 5) {
			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, ruolo);
				preparedStatement.setString(2, utenteBO.getEmail());

				preparedStatement.executeUpdate();
				connection.commit();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
		}

	}
	public void deleteUtenteBO(UtenteBO utenteBO) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "delete from utenteBO where email=?";
		if(utenteBO != null) {
			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, utenteBO.getEmail());

				preparedStatement.executeUpdate();
				connection.commit();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
		}
	}

	public boolean addUserBO(UtenteBO utenteBO) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO utenteBO" + "(email, password, ruolo) values (?,?,?)";
		if(utenteBO != null) {
			if(controlloFormatoAmministratore(utenteBO.getEmail(), utenteBO.getPassword())) {
				try {
					connection = DriverManagerConnectionPool.getConnection();
					preparedStatement = connection.prepareStatement(insertSQL);

					preparedStatement.setString(1, utenteBO.getEmail());
					preparedStatement.setString(2, utenteBO.getPassword());
					preparedStatement.setString(3, utenteBO.getRuolo() + "");

					preparedStatement.executeUpdate();
					connection.commit();

					return true;

				} finally {
					try {
						if (preparedStatement != null)
							preparedStatement.close();
					} finally {
						DriverManagerConnectionPool.releaseConnection(connection);
					}
				}
			}
			return false;
		}
		return false;
	}

	public boolean controlloFormatoAmministratore (String email, String password){
		boolean inputEmail = email.matches(".*@beerhappy\\.it$");
		boolean inputPassword = password.length() >= 8;

		if(inputEmail && inputPassword)
			return true;

		return false;
	}

	public boolean controlloFormatoUtente (String email, String password){

		boolean inputEmail = email.matches("/^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$/");
		boolean inputPassword = password.length() >= 8;

		if(!inputEmail && inputPassword)
			return true;

		return false;
	}

	public boolean controlloFormato(Utente utente) {

		boolean inputNome = utente.getNome().matches("^[a-zA-Z]+$");
		boolean inputCognome = utente.getCognome().matches("^[a-zA-Z]+$");
		boolean inputEmail = utente.getEmail().matches("/^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$/");
		boolean inputPassword = utente.getPassword().length() >= 8 && utente.getPassword().length() <= 30;

		if (utente.getNome() != "" && utente.getCognome() != "" && utente.getEmail() != "" && utente.getPassword() != ""){
			if (inputNome && inputCognome && !inputEmail && inputPassword) {
				return true;
			}
		}

		return false;
	}

	public boolean controlloFormatoModifica(String nome, String cognome){

		boolean inputName = nome.matches("^[a-zA-Z]+$");
		boolean inputSurname = cognome.matches("^[a-zA-Z]+$");

		if(inputName && inputSurname)
			return true;

		return false;
	}

}
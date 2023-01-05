package model;

import entity.Prodotto;
import entity.Utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class ModelSecurity implements Security{
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
		}else if(utente.getEmail().equals(email) && utente.getPassword().equals(password)){
			setUtente(utente);
			return true;
		}else
			return false;
	}

	public Utente getUtente(){
		return utente;
	}

	public void setUtente(Utente utente){
		this.utente = utente;
	}

	public Collection<Prodotto> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<Prodotto> customers = new LinkedList<Prodotto>();

		String selectSQL = "SELECT * FROM beer";


		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			System.out.println("doRetrieveAll:" + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next()) {
				Prodotto bean = new Prodotto();

				bean.setNome(rs.getString("nome"));
				bean.setBirrificio(rs.getString("birrificio"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setFormato(rs.getString("formato"));
				bean.setQuantitaDisp(rs.getInt("quantita"));
				bean.setPrezzo(rs.getString("prezzo"));
				customers.add(bean);
			}
		} finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

		return customers;
	}

}

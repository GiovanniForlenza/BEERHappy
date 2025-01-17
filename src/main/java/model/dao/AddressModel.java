package model.dao;

import model.bean.Indirizzo;
import model.bean.Utente;

import java.sql.*;
import java.util.ArrayList;

public class AddressModel {

	public Indirizzo doRetrieveByKey(int key) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Indirizzo bean = new Indirizzo();

		String selectSQL = "SELECT * FROM indirizzo WHERE indirizzoID = ?";
		if(key > 0){
			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1, key);

				ResultSet rs = preparedStatement.executeQuery();

				while(rs.next()) {
					bean.setID(rs.getInt("indirizzoID"));
					bean.setCivico(rs.getInt("civico"));
					bean.setCitta(rs.getString("citta"));
					bean.setTelefono(rs.getString("telefono"));
					bean.setVia(rs.getString("via"));
					bean.setCap(rs.getString("cap"));
				}

				return bean;
			} finally {
				try {
					if(preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
		}
		return null;

	}
	public Boolean doDelete(int indirizzoID) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String deleteSQL = "DELETE FROM indirizzo WHERE indirizzoID = ?";
		if(indirizzoID > 0){
			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(deleteSQL);

				preparedStatement.setInt(1, indirizzoID);
				preparedStatement.executeUpdate();

				connection.commit();
				return true;
			}finally {
				try {
					if(preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
		}
		return false;

	}
	public ArrayList<Indirizzo> recuperoIndirizzo(Utente utente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "select * from indirizzo where email = ?";
		ArrayList<Indirizzo> address = new ArrayList<Indirizzo>();
		if (utente.getEmail() != null){
			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, utente.getEmail());
				ResultSet resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {
					Indirizzo indirizzo = new Indirizzo();
					indirizzo.setID(resultSet.getInt("indirizzoID"));
					indirizzo.setVia(resultSet.getString("via") + "  " + resultSet.getString("civico"));
					indirizzo.setCitta(resultSet.getString("citta"));
					indirizzo.setTelefono(resultSet.getString("telefono"));
					indirizzo.setCivico(resultSet.getInt("civico"));
					indirizzo.setCap(resultSet.getString("cap"));

					address.add(indirizzo);
				}

				return address;

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

	public Indirizzo aggiuntaIndirizzo(Indirizzo indirizzo, Utente utente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO indirizzo" + "(via, civico, citta, telefono, cap, email) values (?,?,?,?,?,?)";

		if(indirizzo != null && utente != null) {
			if(controlloFormato(indirizzo)){
				try {
					connection = DriverManagerConnectionPool.getConnection();
					preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
					preparedStatement.setString(1, indirizzo.getVia());
					preparedStatement.setInt(2, indirizzo.getCivico());
					preparedStatement.setString(3, indirizzo.getCitta());
					preparedStatement.setString(4, indirizzo.getTelefono());
					preparedStatement.setString(5, indirizzo.getCap());
					preparedStatement.setString(6, utente.getEmail());


					preparedStatement.executeUpdate();
					ResultSet rs = preparedStatement.getGeneratedKeys();

					if (rs.next()) {
						indirizzo.setID(Integer.parseInt(rs.getString(1)));
					}

					connection.commit();
				} finally {
					try {
						if (preparedStatement != null)
							preparedStatement.close();
					} finally {
						DriverManagerConnectionPool.releaseConnection(connection);
						return indirizzo;
					}
				}
			}
			return null;
		}
		return null;
	}

	public boolean controlloFormato(Indirizzo indirizzo){

		boolean inputVia = indirizzo.getVia().matches("^[a-zA-Z\\s]+$");
		boolean inputCivico = indirizzo.getCivico() <= 999 && indirizzo.getCivico() > 0;
		boolean inputCitta = indirizzo.getCitta().matches("^[a-zA-Z]+$");
		boolean inputTelefono = indirizzo.getTelefono().matches("^[0-9]+$") && indirizzo.getTelefono().length() == 10;
		boolean inputCAP = indirizzo.getCap().matches("^[0-9]+$") && indirizzo.getCap().length() == 5;


		if(indirizzo.getVia() == "" || indirizzo.getCivico() == 0 || indirizzo.getCitta() == "" ||
		indirizzo.getTelefono() == "" || indirizzo.getCap() == "")
			return false;

		if(inputVia && inputCitta && inputCivico && inputTelefono && inputCAP)
			return true;

		return false;
	}

}

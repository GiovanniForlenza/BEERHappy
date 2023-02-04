package model;

import entity.Indirizzo;
import entity.Utente;

import java.sql.*;
import java.util.ArrayList;

public class AddressModel {

	public Indirizzo doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Indirizzo bean = new Indirizzo();

		String selectSQL = "SELECT * FROM indirizzo WHERE indirizzoID = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			System.out.println("doRetrieveByKey: "+ preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next()) {
				bean.setID(rs.getInt("indirizzoID"));
				bean.setCivico(rs.getInt("civico"));
				bean.setCitta(rs.getString("citta"));
				bean.setTelefono(rs.getString("telefono"));
				bean.setVia(rs.getString("via"));
				bean.setCap(rs.getString("cap"));
			}

			System.out.println(bean);
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
	public void doDelete(int indirizzoID) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String deleteSQL = "DELETE FROM indirizzo WHERE indirizzoID = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);

			preparedStatement.setInt(1, indirizzoID);
			System.out.println("doDelete: "+ preparedStatement.toString());
			preparedStatement.executeUpdate();

			connection.commit();

		}finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

	}
	public ArrayList<Indirizzo> recuperoIndirizzo(Utente utente) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "select * from indirizzo where email = ?";
		ArrayList<Indirizzo> address = new ArrayList<Indirizzo>();

		try{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, utente.getEmail());
			System.out.println("Recovery address:" + preparedStatement.toString());
			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next())
			{
				Indirizzo indirizzo= new Indirizzo();
				indirizzo.setID(resultSet.getInt("indirizzoID"));
				indirizzo.setVia(resultSet.getString("via") +"  "+ resultSet.getString("civico"));
				indirizzo.setCitta(resultSet.getString("citta"));
				indirizzo.setTelefono(resultSet.getString("telefono"));
				indirizzo.setCivico(resultSet.getInt("civico"));
				indirizzo.setCap(resultSet.getString("cap"));

				address.add(indirizzo);
			}

			return address;

		}finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

	public Indirizzo aggiuntaIndirizzo(Indirizzo indirizzo, Utente utente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO indirizzo" + "(via, civico, citta, telefono, cap, email) values (?,?,?,?,?,?)";


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
			ResultSet rs=preparedStatement.getGeneratedKeys();

			if(rs.next()) {
				indirizzo.setID(Integer.parseInt(rs.getString(1)));
			}

			System.out.println("Insert Query update");
			System.out.print("L'id della nuova carta è: "+indirizzo.getID());

			System.out.println("Insert Query update");
			System.out.print("L'id del nuovo indirizzo è: "+indirizzo.getID());
			connection.commit();
		}finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
				return indirizzo;
			}
		}
	}

}

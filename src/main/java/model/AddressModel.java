package model;

import entity.Indirizzo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressModel {

	public Indirizzo doRetrieveByKey(String citta, String via) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Indirizzo bean = new Indirizzo();

		String selectSQL = "SELECT * FROM indirizzo WHERE citta = ? and via = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			preparedStatement.setString(1,citta);
			preparedStatement.setString(2,via);
			//preparedStatement.setInt(1, Integer.parseInt(code));

			System.out.println("doRetrieveByKey: "+ preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next()) {
				bean.setId(rs.getInt("indirizzoID"));
				bean.setCitta(rs.getString("citta"));
				bean.setTelefono(rs.getString("telefono"));
				bean.setVia(rs.getString("via"));
				bean.setCap(rs.getString("cap"));
			}

			System.out.println(bean);
		} finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

		return bean;
	}
	public void doDelete(Indirizzo address) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String deleteSQL = "DELETE FROM indirizzo WHERE indirizzoID = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, address.getId());
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

}

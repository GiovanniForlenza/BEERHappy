package model;

import entity.Prodotto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductModel {

	public Prodotto doRetrieveByKey(String nome, String birrificio, String formato) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Prodotto bean = new Prodotto();

		String selectSQL = "SELECT * FROM prodotto WHERE nome = ? AND birrificio= ? AND formato = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, nome);
			preparedStatement.setString(2, birrificio);
			preparedStatement.setString(3, formato);

			System.out.println("doRetrieveByKey: "+ preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next()) {
				bean.setNome(rs.getString("nome"));
				bean.setBirrificio(rs.getString("birrificio"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setFormato(rs.getString("formato"));
				bean.setQuantitaDisp(rs.getInt("quantita"));
				bean.setPrezzo(rs.getString("prezzo"));
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
	public void doDelete(Prodotto product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String deleteSQL = "DELETE FROM prodotto WHERE id = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			//TODO add getId() been Prodotto
			//preparedStatement.setInt(1, product.getId());
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

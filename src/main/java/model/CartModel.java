package model;

import entity.Prodotto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CartModel {

	public void doUpdate(Prodotto prodotto) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String updateSQL = "UPDATE prodotto SET nome = ?, birrificio = ?, descrizione = ?, formato = ?, quantita = ?, prezzo = ?, pathImage = ?" +
				" WHERE nome = ? and birrificio = ? and formato = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);

			preparedStatement.setString(1, prodotto.getNome());
			preparedStatement.setString(2, prodotto.getBirrificio());
			preparedStatement.setString(3, prodotto.getDescrizione());
			preparedStatement.setString(4, prodotto.getFormato());
			preparedStatement.setInt(5, prodotto.getQuantitaDisp());
			preparedStatement.setDouble(6, prodotto.getPrezzo());
			preparedStatement.setString(7, "");
			preparedStatement.setString(8, prodotto.getNome());
			preparedStatement.setString(9, prodotto.getBirrificio());
			preparedStatement.setString(10, prodotto.getFormato());

			System.out.println("doUpdate: "+ preparedStatement.toString());
			preparedStatement.executeUpdate();

			connection.commit();

		}finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

}

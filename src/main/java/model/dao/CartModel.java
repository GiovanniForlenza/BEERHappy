package model.dao;

import model.bean.Carrello;
import model.bean.Prodotto;
import model.bean.Utente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CartModel {

	public void salvaCarrello(String email , Prodotto prodotto) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO prodottoNelCarrello" + "(email, nome, birrificio, quantita) values (?,?,?,?)";

		if(email != null && prodotto.getNome() != null && prodotto.getBirrificio() != null){
			try{
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL);
				preparedStatement.setString(1, email);
				preparedStatement.setString(2, prodotto.getNome());
				preparedStatement.setString(3, prodotto.getBirrificio());
				preparedStatement.setInt(4, prodotto.getQuantita());

				preparedStatement.executeUpdate();
				connection.commit();
			}
			finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
		}
	}
	public void svuotaCarrello(Utente utente) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "delete from prodottoNelCarrello where email = ?";

		if(utente.getEmail() != null){
			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, utente.getEmail());
				preparedStatement.executeUpdate();
				connection.commit();
			}
			finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
		}
	}
	public Carrello recuperoCarrello(Utente utente, Carrello cartSession) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ProductModel productModel = new ProductModel();
		String query = "select * from prodottoNelCarrello where email = ?";

		Carrello carrello = new Carrello();

		if (utente.getEmail() != null){
			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, utente.getEmail());

				ResultSet resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {
					String nome = resultSet.getString("nome");
					String birrificio = resultSet.getString("birrificio");
					String quantita = resultSet.getString("quantita");
					Prodotto prodotto;
					prodotto = productModel.doRetrieveByKey(nome, birrificio);
					prodotto.setQuantita(Integer.parseInt(quantita));
					carrello.addProdotti(prodotto);
				}

				if(cartSession != null && cartSession.getProdotti() != null){
					int i = 0;
					while(i < cartSession.getProdotti().size()){
						carrello.addProdotti(cartSession.getProdotti().get(i));
						i++;
					}
				}

				return carrello;

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

	public boolean doUpdate(Prodotto prodotto) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String updateSQL = "UPDATE prodotto SET nome = ?, birrificio = ?, descrizione = ?, formato = ?, quantita = ?, prezzo = ?, pathImage = ?" +
				" WHERE nome = ? and birrificio = ? and formato = ?";
		if(prodotto != null) {
			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(updateSQL);

				preparedStatement.setString(1, prodotto.getNome());
				preparedStatement.setString(2, prodotto.getBirrificio());
				preparedStatement.setString(3, prodotto.getDescrizione());
				preparedStatement.setString(4, prodotto.getFormato());
				preparedStatement.setInt(5, prodotto.getQuantitaDisp());
				preparedStatement.setDouble(6, prodotto.getPrezzo());
				preparedStatement.setString(7, prodotto.getPathImage());
				preparedStatement.setString(8, prodotto.getNome());
				preparedStatement.setString(9, prodotto.getBirrificio());
				preparedStatement.setString(10, prodotto.getFormato());

				preparedStatement.executeUpdate();

				connection.commit();
				return true;
			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
		}
		return false;
	}

}

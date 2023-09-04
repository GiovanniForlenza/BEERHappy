package model.dao;

import model.bean.Prodotto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductModel {

	public Prodotto doRetrieveByKey(String nome, String birrificio) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Prodotto bean = new Prodotto();

		String selectSQL = "SELECT * FROM prodotto WHERE nome = ? AND birrificio= ?";
		if(nome != null && birrificio != null ) {
			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, nome);
				preparedStatement.setString(2, birrificio);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					bean.setNome(rs.getString("nome"));
					bean.setBirrificio(rs.getString("birrificio"));
					bean.setDescrizione(rs.getString("descrizione"));
					bean.setFormato(rs.getString("formato"));
					bean.setQuantitaDisp(rs.getInt("quantita"));
					bean.setPrezzo(rs.getFloat("prezzo"));
					bean.setPathImage(rs.getString("pathImage"));
				}

				return bean;
			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
		}
		else {
			return null;
		}
	}
	public void doDelete(Prodotto product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String deleteSQL = "DELETE FROM prodotto WHERE nome = ? AND birrificio= ? AND formato = ?";

		if(product != null) {
			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(deleteSQL);
				preparedStatement.setString(1, product.getNome());
				preparedStatement.setString(2, product.getBirrificio());
				preparedStatement.setString(3, product.getFormato());
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

	public ArrayList<Prodotto> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<Prodotto> prodotti = new ArrayList<>();

		String selectSQL = "SELECT * FROM prodotto";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Prodotto bean = new Prodotto();

				bean.setNome(rs.getString("nome"));
				bean.setBirrificio(rs.getString("birrificio"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setFormato(rs.getString("formato"));
				bean.setQuantitaDisp(rs.getInt("quantita"));
				bean.setPrezzo(rs.getFloat("prezzo"));
				bean.setPathImage(rs.getString("pathImage"));
				prodotti.add(bean);
			}

			return prodotti;

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

	}

	public ArrayList<Prodotto> doRetrieveProducts(String name) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<Prodotto> prodotti = new ArrayList<>();

		String selectSQL = "SELECT * FROM prodotto where nome like ?";

		if(name != null) {
			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);

				preparedStatement.setString(1, "%" + name + "%");

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					Prodotto bean = new Prodotto();

					bean.setNome(rs.getString("nome"));
					bean.setBirrificio(rs.getString("birrificio"));
					bean.setDescrizione(rs.getString("descrizione"));
					bean.setFormato(rs.getString("formato"));
					bean.setQuantitaDisp(rs.getInt("quantita"));
					bean.setPrezzo(rs.getFloat("prezzo"));
					bean.setPathImage(rs.getString("pathImage"));
					prodotti.add(bean);
				}
				return prodotti;

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
}

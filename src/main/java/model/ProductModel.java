package model;

import entity.Prodotto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

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
				bean.setPrezzo(rs.getDouble("prezzo"));
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

		String deleteSQL = "DELETE FROM prodotto WHERE nome = ? AND birrificio= ? AND formato = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, product.getNome());
			preparedStatement.setString(2, product.getBirrificio());
			preparedStatement.setString(3, product.getFormato());
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

	public ArrayList<Prodotto> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<Prodotto> prodotti = new ArrayList<>();

		String selectSQL = "SELECT * FROM prodotto";


		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			System.out.println("doRetrieveAll:" + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Prodotto bean = new Prodotto();

				bean.setNome(rs.getString("nome"));
				bean.setBirrificio(rs.getString("birrificio"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setFormato(rs.getString("formato"));
				bean.setQuantitaDisp(rs.getInt("quantita"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				prodotti.add(bean);
			}
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

		return prodotti;
	}

	public ArrayList<Prodotto> doRetrieveProducts(String name) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<Prodotto> prodotti = new ArrayList<>();

		String selectSQL = "SELECT * FROM prodotto where nome like ?";


		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			preparedStatement.setString(1, "%" + name + "%");

			System.out.println("doRetrieveAll:" + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Prodotto bean = new Prodotto();

				bean.setNome(rs.getString("nome"));
				bean.setBirrificio(rs.getString("birrificio"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setFormato(rs.getString("formato"));
				bean.setQuantitaDisp(rs.getInt("quantita"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				prodotti.add(bean);
			}
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
		return prodotti;
	}
}

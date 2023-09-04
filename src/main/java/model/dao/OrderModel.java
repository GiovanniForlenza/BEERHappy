package model.dao;

import model.bean.*;
import model.bean.Ordine;
import model.bean.Prodotto;
import model.bean.Stato;
import model.bean.Utente;

import java.sql.*;
import java.util.ArrayList;

public class OrderModel {
	public Ordine aggiuntaOrdine(Ordine ordine, Utente utente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO ordine (email, data, stato, via, civico, citta, telefono, prezzo) values (?, ?, ?, ?, ?, ?, ?, ?)";

		if(ordine != null && utente != null) {
			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, utente.getEmail());
				preparedStatement.setDate(2, Date.valueOf(ordine.getDataOrdine()));
				preparedStatement.setString(3, ordine.getStato().name());
				preparedStatement.setString(4, ordine.getVia());
				preparedStatement.setInt(5, ordine.getCivico());
				preparedStatement.setString(6, ordine.getCitta());
				preparedStatement.setString(7, ordine.getTelefono());
				preparedStatement.setDouble(8, ordine.getPrezzo());

				preparedStatement.executeUpdate();
				ResultSet rs = preparedStatement.getGeneratedKeys();

				if (rs.next()) {
					ordine.setIdOrdine(Integer.parseInt(rs.getString(1)));
				}


				connection.commit();
				return ordine;

			} catch (Exception e) {
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

	public ArrayList<Ordine> recuperoOrdini(Utente utente) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "select * from ordine where email = ?";
		ArrayList<Ordine> order = new ArrayList<Ordine>();

		if(utente != null) {
			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, utente.getEmail());
				ResultSet resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {
					Ordine ordine = new Ordine();
					ordine.setIdOrdine(resultSet.getInt("ordineID"));
					ordine.setDataOrdine(resultSet.getString("data"));
					ordine.setStato(Stato.valueOf(resultSet.getString("stato")));
					ordine.setPrezzo(resultSet.getInt("prezzo"));
					ordine.setVia(resultSet.getString("via"));
					ordine.setCivico(resultSet.getInt("civico"));
					ordine.setCitta(resultSet.getString("citta"));
					ordine.setTelefono(resultSet.getString("telefono"));

					order.add(ordine);
				}


				String queryProdotti = "select * from prodottoOrdinato where ordineID = ?";

				preparedStatement = connection.prepareStatement(queryProdotti);
				for (int i = 0; i < order.size(); i++) {
					preparedStatement.setInt(1, order.get(i).getIdOrdine());
					resultSet = preparedStatement.executeQuery();

					while (resultSet.next()) {
						Prodotto prodottoOrdinato = new Prodotto();
						prodottoOrdinato.setNome(resultSet.getString("nome"));
						prodottoOrdinato.setBirrificio(resultSet.getString("birrificio"));
						prodottoOrdinato.setDescrizione(resultSet.getString("descrizione"));
						prodottoOrdinato.setFormato(resultSet.getString("formato"));
						prodottoOrdinato.setQuantita(resultSet.getInt("quantita"));
						prodottoOrdinato.setPrezzo(resultSet.getFloat("prezzo"));
						prodottoOrdinato.setPathImage(resultSet.getString("pathImage"));

						order.get(i).addProdotto(prodottoOrdinato);
					}
				}

				return order;

			} catch (Exception e) {
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

	public void aggiuntaProdottiOrdine(Prodotto prodottoOrdinato, Ordine ordine) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO prodottoOrdinato (nome, birrificio, descrizione, formato, quantita, prezzo, ordineID, pathImage) values (?, ?, ?, ?, ?, ?, ?,?)";

		if(prodottoOrdinato != null && ordine != null) {
			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL);
				preparedStatement.setString(1, prodottoOrdinato.getNome());
				preparedStatement.setString(2, prodottoOrdinato.getBirrificio());
				preparedStatement.setString(3, prodottoOrdinato.getDescrizione());
				preparedStatement.setString(4, prodottoOrdinato.getFormato());
				preparedStatement.setInt(5, prodottoOrdinato.getQuantita());
				preparedStatement.setDouble(6, prodottoOrdinato.getPrezzo());
				preparedStatement.setInt(7, ordine.getIdOrdine());
				preparedStatement.setString(8, "");

				preparedStatement.executeUpdate();

				connection.commit();
			} catch (Exception e) {
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

	public ArrayList<Ordine> recuperoOrdini() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "select * from ordine";
		ArrayList<Ordine> order = new ArrayList<Ordine>();
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Ordine ordine = new Ordine();
				ordine.setIdOrdine(resultSet.getInt("ordineID"));
				ordine.setDataOrdine(resultSet.getString("data"));
				ordine.setStato(Stato.valueOf(resultSet.getString("stato")));
				ordine.setPrezzo(resultSet.getInt("prezzo"));
				ordine.setVia(resultSet.getString("via"));
				ordine.setCivico(resultSet.getInt("civico"));
				ordine.setCitta(resultSet.getString("citta"));
				ordine.setTelefono(resultSet.getString("telefono"));
				OrderModel om=new OrderModel();
				ordine.setProdotti(om.recuperoProdotti(ordine));
				order.add(ordine);
			}

			return order;

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return null;
	}

	public ArrayList<Prodotto> recuperoProdotti(Ordine ordine) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String queryProdotti = "select * from prodottoOrdinato where ordineID = ?";
		ArrayList<Prodotto> prodotti = new ArrayList<>();
		if(ordine != null) {
			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(queryProdotti);
				preparedStatement.setInt(1, ordine.getIdOrdine());
				ResultSet resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {
					Prodotto prodottoOrdinato = new Prodotto();
					prodottoOrdinato.setNome(resultSet.getString("nome"));
					prodottoOrdinato.setBirrificio(resultSet.getString("birrificio"));
					prodottoOrdinato.setDescrizione(resultSet.getString("descrizione"));
					prodottoOrdinato.setFormato(resultSet.getString("formato"));
					prodottoOrdinato.setQuantita(resultSet.getInt("quantita"));
					prodottoOrdinato.setPrezzo(resultSet.getFloat("prezzo"));
					prodottoOrdinato.setPathImage(resultSet.getString("pathImage"));

					prodotti.add(prodottoOrdinato);
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

	public void updateState(String stato, Ordine ordine) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "update ordine set stato=? where ordineID=?";
		if(stato != null && ordine != null) {
			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL);
				preparedStatement.setString(1, stato);
				preparedStatement.setInt(2, ordine.getIdOrdine());
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
}

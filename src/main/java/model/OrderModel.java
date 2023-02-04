package model;

import entity.Indirizzo;
import entity.Ordine;
import entity.ProdottoOrdinato;
import entity.Utente;

import java.sql.*;
import java.util.ArrayList;

public class OrderModel {


	public Ordine aggiuntaOrdine(Ordine ordine) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO ordine (email, data, stato, via, civico, citta, telefono, prezzo) values (?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, ordine.getEmail());
			preparedStatement.setString(2, "");
			preparedStatement.setString(3, ordine.getStato());
			preparedStatement.setString(4, ordine.getVia());
			preparedStatement.setInt(5, ordine.getCivico());
			preparedStatement.setString(6, ordine.getCitta());
			preparedStatement.setString(7, ordine.getTelefono());
			preparedStatement.setDouble(8, ordine.getPrezzo());

			preparedStatement.executeUpdate();
			ResultSet rs=preparedStatement.getGeneratedKeys();

			if(rs.next()) {
				ordine.setIdOrdine(Integer.parseInt(rs.getString(1)));
			}

			System.out.println("Insert Query update");
			connection.commit();
		}catch (Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
				return ordine;
			}
		}
	}

	public ArrayList<Ordine> recuperoOrdini(Utente utente) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "select * from ordine where email = ?";
		ArrayList<Ordine> order = new ArrayList<Ordine>();

		try{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, utente.getEmail());
			System.out.println("Recovery order:" + preparedStatement.toString());
			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next())
			{
				Ordine ordine= new Ordine();
				ordine.setIdOrdine(resultSet.getInt("ordineID"));
				ordine.setEmail(resultSet.getString("email"));
				ordine.setDataOrdine(resultSet.getString("data"));
				ordine.setStato(resultSet.getString("stato"));
				ordine.setPrezzo(resultSet.getInt("prezzo"));
				ordine.setVia(resultSet.getString("via"));
				ordine.setCivico(resultSet.getInt("civico"));
				ordine.setCitta(resultSet.getString("citta"));
				ordine.setTelefono(resultSet.getString("telefono"));

				order.add(ordine);
			}


			String queryProdotti = "select * from prodottoOrdinato where ordineID = ?";
			ArrayList<ProdottoOrdinato> prodotto = new ArrayList<>();

			preparedStatement = connection.prepareStatement(queryProdotti);
			for(int i = 0; i < order.size(); i++) {
				preparedStatement.setInt(1, order.get(i).getIdOrdine());
				resultSet = preparedStatement.executeQuery();

				while(resultSet.next()){
					ProdottoOrdinato prodottoOrdinato = new ProdottoOrdinato();
					prodottoOrdinato.setOrdineID(resultSet.getInt("ordineID"));
					prodottoOrdinato.setNome(resultSet.getString("nome"));
					prodottoOrdinato.setBirrificio(resultSet.getString("birrificio"));
					prodottoOrdinato.setDescrizione(resultSet.getString("descrizione"));
					prodottoOrdinato.setFormato(resultSet.getString("formato"));
					prodottoOrdinato.setQuantitaSelezionata(resultSet.getInt("quantita"));
					prodottoOrdinato.setPrezzoProdotto(resultSet.getDouble("prezzo"));
					prodottoOrdinato.setPathImage(resultSet.getString("pathImage"));

					prodotto.add(prodottoOrdinato);
				}
				order.get(i).setProdotti(prodotto);
			}

		}catch (Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return order;
	}

	public void aggiuntaProdottiOrdine(ProdottoOrdinato prodottoOrdinato) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO prodottoOrdinato (nome, birrificio, descrizione, formato, quantita, prezzo, ordineID, pathImage) values (?, ?, ?, ?, ?, ?, ?,?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, prodottoOrdinato.getNome());
			preparedStatement.setString(2, prodottoOrdinato.getBirrificio());
			preparedStatement.setString(3, prodottoOrdinato.getDescrizione());
			preparedStatement.setString(4, prodottoOrdinato.getFormato());
			preparedStatement.setInt(5, prodottoOrdinato.getQuantitaSelezionata());
			preparedStatement.setDouble(6, prodottoOrdinato.getPrezzoProdotto());
			preparedStatement.setInt(7, prodottoOrdinato.getOrdineID());
			preparedStatement.setString(8,"");

			preparedStatement.executeUpdate();

			System.out.println("Insert Query update");
			connection.commit();
		}catch (Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
}

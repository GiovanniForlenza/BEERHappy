package model;

import entity.Carta;
import entity.Prodotto;
import entity.Utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CatalogoModel {
    public ArrayList<Prodotto> recuperoProdotti() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = "select * from prodotto";
        ArrayList<Prodotto> prodotti = new ArrayList<Prodotto>();

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Prodotto prodotto = new Prodotto();
                prodotto.setNome(resultSet.getString("nome"));
                prodotto.setBirrificio(resultSet.getString("birrificio"));
                prodotto.setDescrizione(resultSet.getString("descrizione"));
                prodotto.setFormato(resultSet.getString("formato"));
                prodotto.setQuantitaDisp(resultSet.getInt("quantita"));
                prodotto.setPrezzo(resultSet.getDouble("prezzo"));
                prodotto.setPathImage(resultSet.getString("pathImage"));
                prodotti.add(prodotto);
            }
            return prodotti;
        }finally {
            if(preparedStatement!=null)
                preparedStatement.close();
            DriverManagerConnectionPool.releaseConnection(connection);
        }
    }
}

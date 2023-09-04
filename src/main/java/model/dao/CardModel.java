package model.dao;
import model.bean.Carta;
import model.bean.Utente;

import java.sql.*;
import java.util.ArrayList;

public class CardModel {

    public Carta doRetrieveByKey(int key) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Carta bean = new Carta();

        String selectSQL = "SELECT * FROM carta WHERE cartaID = ?";
        if(key > 0) {
            try {
                connection = DriverManagerConnectionPool.getConnection();
                preparedStatement = connection.prepareStatement(selectSQL);
                preparedStatement.setInt(1, key);


                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    bean.setId(rs.getInt("cartaID"));
                    bean.setnCata(rs.getString("numero"));
                    bean.setIntestatario(rs.getString("titolare"));
                    bean.setCvv(rs.getInt("cvv"));
                    bean.setDataScadenza(rs.getString("dataScadenza"));
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
        return null;

    }
    public ArrayList<Carta> recuperoCarte(Utente utente) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = "select * from carta where email = ?";
        ArrayList<Carta> cards = new ArrayList<Carta>();
        if (utente != null){
            try {
                connection = DriverManagerConnectionPool.getConnection();
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, utente.getEmail());

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Carta carta = new Carta();
                    carta.setId(resultSet.getInt("cartaID"));
                    carta.setnCata(resultSet.getString("numero"));
                    carta.setIntestatario(resultSet.getString("titolare"));
                    carta.setCvv(resultSet.getInt("cvv"));
                    carta.setDataScadenza(resultSet.getString("dataScadenza"));
                    cards.add(carta);
                }
                return cards;
            } finally {
                preparedStatement.close();
            }
        }
        return null;
    }
    public boolean doDelete(int cartaID) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String deleteSQL = "DELETE FROM carta WHERE cartaID = ?";
        if(cartaID > 0) {
            try {
                connection = DriverManagerConnectionPool.getConnection();
                preparedStatement = connection.prepareStatement(deleteSQL);

                preparedStatement.setInt(1, cartaID);
                preparedStatement.executeUpdate();

                connection.commit();
                return true;
            } finally {
                try {
                    if (preparedStatement != null)
                        preparedStatement.close();
                } finally {
                    DriverManagerConnectionPool.releaseConnection(connection);
                }
            }
        }
        return false;
    }

    public Carta aggiuntaCarta(Carta carta, Utente utente) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String insertSQL = "INSERT INTO carta(email, numero, cvv, titolare, dataScadenza) values (?,?,?,?,?);";
        if(carta != null && utente != null){
            if(controlloFormato(carta)) {
                try {
                    connection = DriverManagerConnectionPool.getConnection();
                    preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
                    preparedStatement.setString(1, utente.getEmail());
                    preparedStatement.setString(2, carta.getnCata());
                    preparedStatement.setInt(3, carta.getCvv());
                    preparedStatement.setString(4, carta.getIntestatario());
                    preparedStatement.setString(5, carta.getDataScadenza());

                    preparedStatement.executeUpdate();
                    ResultSet rs = preparedStatement.getGeneratedKeys();
                    if (rs.next()) {
                        carta.setId(Integer.parseInt(rs.getString(1)));
                    }

                    connection.commit();
                    return carta;
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
        return null;
    }

    public boolean controlloFormato(Carta carta){
        boolean inputIntestatario =  carta.getIntestatario().matches("^[A-Za-z\\s]+$");
        boolean inputNumeroCarta = carta.getnCata().matches("^\\d{16}$");
        boolean inputCvv = (carta.getCvv() < 1000) && carta.getCvv() > 99;

        if(inputIntestatario && inputNumeroCarta && inputCvv)
            return true;

        return false;
    }
}
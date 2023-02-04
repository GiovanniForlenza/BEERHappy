package model;
import entity.Carta;
import entity.Indirizzo;
import entity.Utente;

import java.sql.*;
import java.util.ArrayList;

public class CardModel {

    public Carta doRetrieveByKey(int code) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Carta bean = new Carta();

        String selectSQL = "SELECT * FROM carta WHERE cartaID = ?";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, code);

            System.out.println("doRetrieveByKey: "+ preparedStatement.toString());
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                bean.setId(rs.getInt("cartaID"));
                bean.setnCata(rs.getString("numero"));
                bean.setIntestatario(rs.getString("titolare"));
                bean.setCvv(rs.getInt("cvv"));
                bean.setDataScadenza(rs.getString("dataScadenza"));
            }

            System.out.println(bean);
            return bean;
        } finally {
            try {
                if(preparedStatement != null)
                    preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }


    }
    public ArrayList<Carta> recuperoCarte(Utente utente) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = "select * from carta where email = ?";
        ArrayList<Carta> cards = new ArrayList<Carta>();

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, utente.getEmail());
            System.out.println("Recovery cards:" + preparedStatement.toString());
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
        }finally {
            preparedStatement.close();
        }
        }
    public void doDelete(int cartaID) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String deleteSQL = "DELETE FROM carta WHERE cartaID = ?";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(deleteSQL);

            preparedStatement.setInt(1, cartaID);
            System.out.println("doDelete: " + preparedStatement.toString());
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

    public Carta aggiuntaCarta(Carta carta, Utente utente) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String insertSQL = "INSERT INTO carta(email, numero, cvv, titolare, dataScadenza) values (?,?,?,?,?);";
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
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
                return carta;
            }
        }
    }
    }
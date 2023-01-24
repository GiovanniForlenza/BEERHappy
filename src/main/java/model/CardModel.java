package model;
import entity.Carta;
import entity.Utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CardModel {

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
        String insertSQL = "INSERT INTO carta"+"(email, numero, cvv, titolare) values (?,?,?,?);";


        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL);

            preparedStatement.setString(1, utente.getEmail());
            preparedStatement.setString(2, carta.getnCata());
            preparedStatement.setInt(3, carta.getCvv());
            preparedStatement.setString(4, carta.getIntestatario());



            preparedStatement.executeUpdate();
            ResultSet rs=preparedStatement.getGeneratedKeys();
            rs.next();
            carta.setId(rs.getInt(1));
            System.out.println("Insert Query update");
            System.out.print("L'id della nuova carta è: "+carta.getId());
            connection.commit();
        }finally {
            try {
                if(preparedStatement != null)
                    preparedStatement.close();
            }finally {
                DriverManagerConnectionPool.releaseConnection(connection);
                return carta;
            }
        }
    }
    }
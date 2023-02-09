package model;

import entity.Prodotto;

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

    public void addProduct(Prodotto prodotto){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = "insert into prodotto(nome, birrificio, descrizione, formato, quantita, prezzo, pathImage) values (?,?,?,?,?,?,?)";
        ArrayList<Prodotto> prodotti = new ArrayList<Prodotto>();

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, prodotto.getNome());
            preparedStatement.setString(2, prodotto.getBirrificio());
            preparedStatement.setString(3, prodotto.getDescrizione());
            preparedStatement.setString(4, prodotto.getFormato());
            preparedStatement.setInt(5, prodotto.getQuantitaDisp());
            preparedStatement.setDouble(6, prodotto.getPrezzo());
            preparedStatement.setString(7, prodotto.getPathImage());

            preparedStatement.executeUpdate();
            connection.commit();
            if(preparedStatement!=null)
                preparedStatement.close();
            DriverManagerConnectionPool.releaseConnection(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   public void deleteProduct(Prodotto prodotto){

   }

   public void updateProduct(Prodotto oldProduct, Prodotto newProduct){
       Connection connection = null;
       PreparedStatement preparedStatement = null;
       String query = "update prodotto set nome=?, birrificio=?, descrizione=?, formato=?, quantita=?, prezzo=?, pathImage=? where nome=? and birrificio=?";

       try {
           connection = DriverManagerConnectionPool.getConnection();
           preparedStatement = connection.prepareStatement(query);
           preparedStatement.setString(1, newProduct.getNome());
           preparedStatement.setString(2, newProduct.getBirrificio());
           preparedStatement.setString(3, newProduct.getDescrizione());
           preparedStatement.setString(4, newProduct.getFormato());
           preparedStatement.setInt(5, newProduct.getQuantitaDisp());
           preparedStatement.setDouble(6, newProduct.getPrezzo());
           preparedStatement.setString(7, newProduct.getPathImage());
           preparedStatement.setString(8, oldProduct.getNome());
           preparedStatement.setString(9, oldProduct.getBirrificio());

           preparedStatement.executeUpdate();
           connection.commit();
           if(preparedStatement!=null)
               preparedStatement.close();
           DriverManagerConnectionPool.releaseConnection(connection);

       } catch (SQLException e) {
           e.printStackTrace();
       }
   }

   public boolean searchProductByKey(String nome, String birrificio) {
       Connection connection = null;
       PreparedStatement preparedStatement = null;
       String query = "select * from where nome=? and birrificio=?";
       boolean flag = false;

       try {
           connection = DriverManagerConnectionPool.getConnection();
           preparedStatement = connection.prepareStatement(query);
           preparedStatement.setString(1, nome);
           preparedStatement.setString(2, birrificio);
           ResultSet rs = preparedStatement.executeQuery();
           if (rs.next()) {
               flag = true;
           }
           if (preparedStatement != null)
               preparedStatement.close();
           DriverManagerConnectionPool.releaseConnection(connection);

       } catch (SQLException e) {
           e.printStackTrace();
       }
       return flag;
   }
}


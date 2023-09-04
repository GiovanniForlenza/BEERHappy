package model.dao;

import model.bean.Prodotto;

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

            while (resultSet.next()) {Prodotto prodotto = new Prodotto();
                prodotto.setNome(resultSet.getString("nome"));
                prodotto.setBirrificio(resultSet.getString("birrificio"));
                prodotto.setDescrizione(resultSet.getString("descrizione"));
                prodotto.setFormato(resultSet.getString("formato"));
                prodotto.setQuantitaDisp(resultSet.getInt("quantita"));
                prodotto.setPrezzo(resultSet.getFloat("prezzo"));
                prodotto.setPathImage(resultSet.getString("pathImage"));
                prodotti.add(prodotto);
            }
            return prodotti;
        }finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
    }

    public boolean addProduct(Prodotto prodotto) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = "insert into prodotto(nome, birrificio, descrizione, formato, quantita, prezzo, pathImage) values (?,?,?,?,?,?,?)";

        if(prodotto != null){
            if(controlloFormato(prodotto)) {
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
                    return true;
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
            return false;
        }
        return false;
    }



   public boolean updateProduct(Prodotto oldProduct, Prodotto newProduct) throws SQLException {
       Connection connection = null;
       PreparedStatement preparedStatement = null;
       String query = "update prodotto set nome=?, birrificio=?, descrizione=?, formato=?, quantita=?, prezzo=?, pathImage=? where nome=? and birrificio=?";

       if(oldProduct != null && newProduct != null) {
           if(controlloFormato(newProduct)){
               try {
                   connection = DriverManagerConnectionPool.getConnection();
                   preparedStatement = connection.prepareStatement(query);
                   preparedStatement.setString(1, newProduct.getNome());
                   preparedStatement.setString(2, newProduct.getBirrificio());
                   preparedStatement.setString(3, newProduct.getDescrizione());
                   preparedStatement.setString(4, newProduct.getFormato());
                   preparedStatement.setInt(5, newProduct.getQuantitaDisp());
                   preparedStatement.setDouble(6, newProduct.getPrezzo());
                   preparedStatement.setString(7, oldProduct.getPathImage());
                   preparedStatement.setString(8, oldProduct.getNome());
                   preparedStatement.setString(9, oldProduct.getBirrificio());

                   preparedStatement.executeUpdate();
                   connection.commit();

                   return true;
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
           return false;
       }
       return false;
   }

   public boolean searchProductByKey(String nome, String birrificio) throws SQLException {
       Connection connection = null;
       PreparedStatement preparedStatement = null;
       String query = "select * from prodotto where nome = ? and birrificio = ?";
       boolean flag = false;
       if(nome != null && birrificio != null) {
           try {
               connection = DriverManagerConnectionPool.getConnection();
               preparedStatement = connection.prepareStatement(query);
               preparedStatement.setString(1, nome);
               preparedStatement.setString(2, birrificio);
               ResultSet rs = preparedStatement.executeQuery();

               if (rs.next()) {
                   flag = true;
               }


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
           return flag;
       }
       return false;
   }

   public boolean controlloFormato(Prodotto prodotto){

        boolean inputNome = prodotto.getNome().matches("^[a-zA-Z0-9\\s]+$");
        boolean inputBirrificio = prodotto.getBirrificio().length() > 3 && prodotto.getBirrificio().length() < 30;
        boolean inputFormato = prodotto.getFormato().matches("^[a-zA-Z0-9\\s]+$");
        boolean inputDescrizione = prodotto.getDescrizione().length() < 1000;
        boolean inputQuantita = prodotto.getQuantitaDisp() >= 1;

        String stringValue = String.valueOf(prodotto.getPrezzo());
        boolean inputPrezzo = prodotto.getPrezzo() > 0 && stringValue.matches("\\d+\\.\\d+");;

        //boolean inputImage = prodotto.getPathImage().matches("^(https?|ftp)://[^\\s/$.?#].[^\\s]*$");

        if(prodotto.getNome() == "" || prodotto.getBirrificio() == "" || prodotto.getFormato() == ""
                || prodotto.getQuantitaDisp() == 0 || prodotto.getPrezzo() == 0)
            return false;

        if(inputNome && inputBirrificio && inputFormato && inputDescrizione && inputQuantita && inputPrezzo)
            return true;

        return false;
   }
}


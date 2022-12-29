package control;

import java.sql.*;

public class MyJDBC {
    public static void main(String[] args) {
        /*
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/beerhappy", "root", "admin");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from utente");

            while(resultSet.next()){
                System.out.println(resultSet.getString("nome"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }*/

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/beerhappy", "root", "admin");
            Statement statement = connection.createStatement();
            PreparedStatement pstm;

            pstm = connection.prepareStatement("INSERT INTO utente" + "(id, nome, cognome, email, password) values (?,?,?,?,?)");

            pstm.setString(1,"2");
            pstm.setString(2, "Ugo");
            pstm.setString(3, "buco");
            pstm.setString(4, "ugobuco99@gmail.com");
            pstm.setString(5, "meglioilbucocheugo");
            pstm.execute();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

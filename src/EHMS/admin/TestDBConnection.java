package EHMS.admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDBConnection {
    public static void main(String[] args) {
    	String url = "jdbc:mysql://localhost:3306/healthcaremangaementsystem?serverTimezone=UTC";
    	String uname = "root";
    	String pass = "";


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, uname, pass);
            if (connection != null) {
                System.out.println("Conexão estabelecida com sucesso!");
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Driver JDBC não encontrado.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao conectar ao banco de dados.");
        }
    }
}

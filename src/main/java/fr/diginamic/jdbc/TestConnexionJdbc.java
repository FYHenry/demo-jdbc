package fr.diginamic.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnexionJdbc {
    private static final String unix_socket = "jdbc:mariadb://localhost/";
    private static final String ip_port = "jdbc:mariadb://127.0.0.1:3306/";
    public static void main(String[] args) throws SQLException{
            try(Connection connection = DriverManager.getConnection(unix_socket + "compta",
                    "root",
                    "temporaire")){
            System.out.println("Ça marche !");
                System.out.println(connection);
        }
    }
}

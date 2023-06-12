package fr.diginamic.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.ResourceBundle;

public class TestConnexionJdbc {
    private static final String unix_socket;
    private static final String user;
    static {
        final ResourceBundle rb = ResourceBundle.getBundle("database");
        unix_socket = rb.getString("ADDON_SCHEME") +
                rb.getString("ADDON_HOST") +
                rb.getString("ADDON_DB");
        user = rb.getString("ADDON_USER");
    }
    public static void main(String[] args) throws SQLException{
        System.out.println("Mot de passe :");
        Scanner scanner = new Scanner(System.in);
        final String pwd = scanner.nextLine();
        try(Connection connection =
                    DriverManager.getConnection(unix_socket,
                            user,
                    pwd)){
                System.out.println("Ça marche !");
                System.out.println(connection);
        }
    }
}

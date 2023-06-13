package fr.diginamic.jdbc;

import fr.diginamic.jdbc.entites.Fournisseur;

import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class TestSelect {
    private static final String unix_socket;
    private static final String user;

    static {
        final ResourceBundle rb = ResourceBundle.getBundle("database");
        unix_socket = rb.getString("ADDON_SCHEME") +
                rb.getString("ADDON_HOST") +
                rb.getString("ADDON_DB");
        user = rb.getString("ADDON_USER");
    }

    public static void main(String[] args) throws SQLException {
        System.out.println("Mot de passe :");
        Scanner scanner = new Scanner(System.in);
        final String pwd = scanner.nextLine();
        final String query = "SELECT * FROM FOURNISSEUR";
        try (Connection connection =
                     DriverManager.getConnection(unix_socket,
                             user,
                             pwd);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            System.out.println("Ça marche !");
            System.out.println(connection);
            System.out.println(listFournisseurs(resultSet));
        }
    }
    private static ArrayList<Fournisseur> listFournisseurs(ResultSet resultSet)
            throws SQLException{
        final ArrayList<Fournisseur> fournisseurs = new ArrayList<>();
        while(resultSet.next()){
            fournisseurs.add(new Fournisseur(resultSet.getInt("ID"),
                    resultSet.getString("NOM")));
        }
        return fournisseurs;
    }
}
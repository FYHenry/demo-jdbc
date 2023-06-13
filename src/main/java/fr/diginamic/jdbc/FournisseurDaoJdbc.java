package fr.diginamic.jdbc;

import fr.diginamic.jdbc.dao.FournisseurDao;
import fr.diginamic.jdbc.entites.Fournisseur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FournisseurDaoJdbc implements FournisseurDao {
    private static final String unix_socket;
    private static final String user;
    private final String password;
    static {
        final ResourceBundle rb = ResourceBundle.getBundle("database");
        unix_socket = rb.getString("ADDON_SCHEME") +
                rb.getString("ADDON_HOST") +
                rb.getString("ADDON_DB");
        user = rb.getString("ADDON_USER");
    }
    public FournisseurDaoJdbc(String password){
        this.password = password;
    }
    @Override
    public List<Fournisseur> extraire() {
        final String query = "SELECT * FROM FOURNISSEUR";
        try (final Connection connection =
                     DriverManager.getConnection(unix_socket,
                             user,
                             this.password);
             final Statement statement = connection.createStatement();
             final ResultSet resultSet = statement.executeQuery(query)) {
            return listFournisseurs(resultSet);
        } catch(SQLException ex) {
            System.err.println(ex.getLocalizedMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void insert(Fournisseur fournisseur) {
        try (final Connection connection =
                     DriverManager.getConnection(unix_socket,
                             user,
                             this.password);
             final Statement statement = connection.createStatement()) {
            final String query = String.format("INSERT INTO FOURNISSEUR" +
                    " (ID, NOM) VALUES (%d, '%s')",
                    fournisseur.getId(),
                    fournisseur.getNom());
            int insertNbr = statement.executeUpdate(query);
            System.out.printf("Lignes insérées dans la table FOURNISSEUR : %d",
                    insertNbr);
        } catch(SQLException ex) {
            System.err.println(ex.getLocalizedMessage());
        }

    }

    @Override
    public int update(String ancienNom, String nouveauNom) {
        try (final Connection connection =
                     DriverManager.getConnection(unix_socket,
                             user,
                             this.password);
             final Statement statement = connection.createStatement()) {
            final String query = String.format("UPDATE FOURNISSEUR" +
                            " SET NOM='%s' WHERE NOM='%s'",
                    nouveauNom,
                    ancienNom);
            int insertNbr = statement.executeUpdate(query);
            System.out.printf("Lignes remplacées dans la table FOURNISSEUR : %d",
                    insertNbr);
            return insertNbr;
        } catch(SQLException ex) {
            System.err.println(ex.getLocalizedMessage());
            return 0;
        }
    }

    @Override
    public boolean delete(Fournisseur fournisseur) {
        try (final Connection connection =
                     DriverManager.getConnection(unix_socket,
                             user,
                             this.password);
             final Statement statement = connection.createStatement()) {
            final String query = String.format("DELETE FROM FOURNISSEUR" +
                    " WHERE ID=%d AND NOM='%s'",
                    fournisseur.getId(),
                    fournisseur.getNom());
            return statement.execute(query);
        } catch(SQLException ex) {
            System.err.println(ex.getLocalizedMessage());
            return false;
        }
    }

    private static List<Fournisseur> listFournisseurs(ResultSet resultSet)
            throws SQLException{
        final ArrayList<Fournisseur> fournisseurs = new ArrayList<>();
        while(resultSet.next()){
            fournisseurs.add(new Fournisseur(resultSet.getInt("ID"),
                    resultSet.getString("NOM")));
        }
        return fournisseurs;
    }
}

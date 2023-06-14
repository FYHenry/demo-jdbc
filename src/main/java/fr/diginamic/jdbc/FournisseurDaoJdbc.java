package fr.diginamic.jdbc;

import fr.diginamic.jdbc.dao.FournisseurDao;
import fr.diginamic.jdbc.entites.Fournisseur;

import java.sql.*;
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
    public void insert(Fournisseur fournisseur){
        final String query = "INSERT INTO FOURNISSEUR (ID, NOM) VALUES (?, ?)";
        try (final Connection connection =
                     DriverManager.getConnection(unix_socket,
                             user,
                             this.password);
             final PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, fournisseur.getId());
            ps.setString(2, fournisseur.getNom());
            System.out.printf("Lignes insérées dans la table FOURNISSEUR : %d",
                    ps.executeUpdate());
        } catch(SQLException ex) {
            System.err.println(ex.getLocalizedMessage());
        }

    }

    @Override
    public int update(String ancienNom, String nouveauNom) {
        final String query = "UPDATE FOURNISSEUR SET NOM=? WHERE NOM=?";
        try (final Connection connection =
                     DriverManager.getConnection(unix_socket,
                             user,
                             this.password);
             final PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, nouveauNom);
            ps.setString(2, ancienNom);
            final int INSERT_NBR = ps.executeUpdate();
            System.out.printf("Lignes remplacées dans " +
                            "la table FOURNISSEUR : %d\n",
                    INSERT_NBR);
            return INSERT_NBR;
        } catch(SQLException ex) {
            System.err.println(ex.getLocalizedMessage());
            return 0;
        }
    }

    @Override
    public boolean delete(Fournisseur fournisseur) {
        final String query = "DELETE FROM FOURNISSEUR WHERE ID=? AND NOM=?";
        try (final Connection connection =
                     DriverManager.getConnection(unix_socket,
                             user,
                             this.password);
             final PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, fournisseur.getId());
            ps.setString(2, fournisseur.getNom());
            return ps.execute();
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

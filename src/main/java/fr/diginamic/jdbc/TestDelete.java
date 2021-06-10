package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.mariadb.jdbc.Driver;

/**
 * Classe qui se connecte à la base de données compta et insére un nv
 * fournisseur.
 * 
 * @author RichardBONNAMY
 *
 */
public class TestDelete {

	/**
	 * Point d'entrée de l'application
	 * 
	 * @param args paramètre non utilisé dans cette application
	 */
	public static void main(String[] args) {
		Connection conn = null;
		Statement stat = null;
		try {
			DriverManager.registerDriver(new Driver());
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/compta", "root", "");
			stat = conn.createStatement();

			int nb = stat.executeUpdate("DELETE FROM FOURNISSEUR WHERE NOM='La Maison des Peintures'");
			System.out.println("Nombre de lignes supprimées: " + nb);

		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Une exception grave s'est produite. L'application va s'arrêter.");
		} finally {
			try {
				if (stat != null) {
					stat.close();
				}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		}

	}

}

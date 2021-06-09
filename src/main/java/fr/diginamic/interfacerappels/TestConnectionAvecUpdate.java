package fr.diginamic.interfacerappels;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.mariadb.jdbc.Driver;

public class TestConnectionAvecUpdate {

	public static void main(String[] args) throws SQLException {

		// 1 - je charge le driver
		DriverManager.registerDriver(new Driver());

		// 2 - je créé une connexion
		Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/compta", "root", "");
		// opérations 3 à N

		Statement stat = conn.createStatement();
		int nb = stat.executeUpdate("INSERT into FOURNISSEUR (NOM) VALUES ('France Matériaux')");
		if (nb != 0) {
			System.out.println("L'insertion s'est bien passée.");
		}

		nb = stat.executeUpdate("UPDATE FOURNISSEUR SET nom='France Matériaux' where id=5");
		System.out.println("Nombre de lignes modifiées:" + nb);

		stat.close(); // Indispensable pour certaines bases de données. Ex: Oracle

		// N+1 - je ferme la connexion après usage, ce qui envoie à la base un ordre de
		// fin d'utilisation de la connexion.
		conn.close(); // Indispensable pour certaines bases de données. Ex: Oracle
	}

}

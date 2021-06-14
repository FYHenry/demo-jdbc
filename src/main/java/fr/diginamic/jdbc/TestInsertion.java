package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.mariadb.jdbc.Driver;

public class TestInsertion {

	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement stat = null;
		try {
			DriverManager.registerDriver(new Driver());
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/compta", "root", "");
			stat = conn.prepareStatement("INSERT INTO FOURNISSEUR (NOM) VALUES (?)");
			stat.setString(1, "La Maison de la Peinture");

			int nb = stat.executeUpdate();
			System.out.println("Nombre de lignes insérées: " + nb);

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

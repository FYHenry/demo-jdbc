package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.mariadb.jdbc.Driver;

import fr.diginamic.entites.Fournisseur;

public class TestSelect {

	public static void main(String[] args) {

		ArrayList<Fournisseur> fournisseurs = new ArrayList<>();

		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		try {
			DriverManager.registerDriver(new Driver());
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/compta", "root", "");
			stat = conn.createStatement();

			res = stat.executeQuery("SELECT * FROM FOURNISSEUR");
			while (res.next()) {
				int id = res.getInt("ID");
				String nom = res.getString("NOM");

				Fournisseur fournisseur = new Fournisseur(id, nom);
				fournisseurs.add(fournisseur);
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Une exception grave s'est produite. L'application va s'arrêter.");
		} finally {
			try {
				if (res != null) {
					res.close();
				}
				if (stat != null) {
					stat.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		}

		for (Fournisseur fournisseur : fournisseurs) {
			System.out.println(fournisseur);
		}

	}

}

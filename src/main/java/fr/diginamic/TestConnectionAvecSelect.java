package fr.diginamic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.mariadb.jdbc.Driver;

import fr.diginamic.entites.Article;

public class TestConnectionAvecSelect {

	public static void main(String[] args) {

		ArrayList<Article> listeArticles = new ArrayList<>();

		Connection conn = null;
		Statement stat = null;
		ResultSet result = null;

		try {
			// 1 - je charge le driver
			DriverManager.registerDriver(new Driver());

			// 2 - je créé une connexion
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/compta", "root", "");
			// opérations 3 à N

			stat = conn.createStatement();

			result = stat.executeQuery("SELECT * FROM ARTICLE WHERE REF LIKE 'A%'");
			while (result.next()) {
				Integer id = result.getInt("id");
				String designation = result.getString("DESIGNATION");
				String reference = result.getString("REF");

				Article article = new Article(id, designation, reference);
				listeArticles.add(article);
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				result.close();
				stat.close();
				conn.close();
			} catch (SQLException e) {
				System.err.println("Erreur lors de la fermeture des ressources d'accès à la BDD");
			}
		}

		for (Article article : listeArticles) {
			System.out.println(article.getDesignation());
		}
	}

}

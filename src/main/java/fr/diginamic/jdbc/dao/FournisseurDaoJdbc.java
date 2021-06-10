package fr.diginamic.jdbc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.diginamic.entites.Fournisseur;
import fr.diginamic.jdbc.ConnectionMgr;

public class FournisseurDaoJdbc implements FournisseurDao {

	public void insert(Fournisseur fournisseur) {
		Connection conn = null;
		Statement stat = null;
		try {
			conn = ConnectionMgr.getConnection();
			stat = conn.createStatement();

			int nb = stat.executeUpdate("INSERT INTO FOURNISSEUR (ID, NOM) VALUES (" + fournisseur.getId() + ", '"
					+ fournisseur.getNom() + "')");
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

	@Override
	public List<Fournisseur> extraire() {
		ArrayList<Fournisseur> fournisseurs = new ArrayList<>();

		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		try {
			conn = ConnectionMgr.getConnection();
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
		return fournisseurs;
	}

	@Override
	public int update(String ancienNom, String nouveauNom) {
		Connection conn = null;
		Statement stat = null;
		try {
			conn = ConnectionMgr.getConnection();
			stat = conn.createStatement();

			int nb = stat
					.executeUpdate("UPDATE FOURNISSEUR SET NOM='" + nouveauNom + "' WHERE NOM='" + ancienNom + "'");
			System.out.println("Nombre de lignes modifiées: " + nb);

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

		return 0;
	}

	@Override
	public boolean delete(Fournisseur fournisseur) {
		Connection conn = null;
		Statement stat = null;
		try {
			conn = ConnectionMgr.getConnection();
			stat = conn.createStatement();

			int nb = stat.executeUpdate("DELETE FROM FOURNISSEUR WHERE NOM='La Maison des Peintures'");
			if (nb > 0) {
				return true;
			}

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
		return false;
	}
}

package fr.diginamic.jdbc;

import java.util.List;

import fr.diginamic.entites.Fournisseur;
import fr.diginamic.jdbc.dao.FournisseurDao;
import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;

public class TestSelectAvecDao {

	public static void main(String[] args) {

		FournisseurDao dao = new FournisseurDaoJdbc();

		List<Fournisseur> fournisseurs = dao.extraire();

		for (Fournisseur fournisseur : fournisseurs) {
			System.out.println(fournisseur);
		}

	}

}

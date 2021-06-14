package fr.diginamic.jdbc;

import fr.diginamic.entites.Fournisseur;
import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;

public class TestInsertionAvecDao {

	public static void main(String[] args) {

		FournisseurDaoJdbc dao = new FournisseurDaoJdbc();
		Fournisseur fournisseur = new Fournisseur("La Maison de la Peinture");

		dao.insert(fournisseur);
	}

}

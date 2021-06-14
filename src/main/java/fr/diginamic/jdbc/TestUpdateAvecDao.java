package fr.diginamic.jdbc;

import fr.diginamic.jdbc.dao.FournisseurDao;
import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;

public class TestUpdateAvecDao {

	public static void main(String[] args) {
		FournisseurDao dao = new FournisseurDaoJdbc();
		dao.update("La Maison de la Peinture", "La Maison des Peintures");

	}

}

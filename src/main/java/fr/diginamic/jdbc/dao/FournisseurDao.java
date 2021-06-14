package fr.diginamic.jdbc.dao;

import java.util.List;

import fr.diginamic.entites.Fournisseur;

/**
 * DAO Fournisseur
 * 
 * @author DIGINAMIC
 *
 */
public interface FournisseurDao {

	/**
	 * Extrait la liste des fournisseurs
	 * 
	 * @return List
	 */
	List<Fournisseur> extraire();

	/**
	 * Insère un nouveau fournisseur
	 * 
	 * @param fournisseur nv fournisseur
	 */
	void insert(Fournisseur fournisseur);

	/**
	 * Recherche en base le fournisseur avec l'ancien nom et remplace ce nom par le
	 * nouveau nom. Retourne le nb de lignes modifiées.
	 * 
	 * @param ancienNom  ancien nom
	 * @param nouveauNom nouveau nom
	 * @return int
	 */
	int update(String ancienNom, String nouveauNom);

	/**
	 * Supprime le fournisseur passé en paramètre. Retourne si oui ou non le
	 * fournisseur a bien été supprimé.
	 * 
	 * @param fournisseur fournisseur
	 * @return boolean
	 */
	boolean delete(Fournisseur fournisseur);

	/**
	 * Extrait le fournisseur dont le nom est passé en paramètre
	 * 
	 * @param nom nom du fournisseur
	 * @return {@link Fournisseur}
	 */
	Fournisseur extraireParNom(String nom);

}
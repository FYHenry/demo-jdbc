package fr.diginamic.entites;

/**
 * Classe qui représente un fournisseur
 * 
 * @author DIGINAMIC
 *
 */
public class Fournisseur {

	/** Identifiant du fournisseur */
	private int id;

	/** Nom du fournisseur */
	private String nom;

	/**
	 * Constructeur
	 * 
	 * @param id  identifiant du fournisseur
	 * @param nom nom du fournisseur
	 */
	public Fournisseur(int id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}

	@Override
	public String toString() {
		return id + " : " + nom;
	}

	/**
	 * Getter
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter
	 * 
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter
	 * 
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Setter
	 * 
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
}

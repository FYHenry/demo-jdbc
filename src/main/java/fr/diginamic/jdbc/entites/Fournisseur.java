package fr.diginamic.jdbc.entites;

public class Fournisseur {
    private final int ID;
    private final String nom;
    public Fournisseur(int id, String nom){
        this.ID = id;
        this.nom = nom;
    }
    public String toString(){
        return String.format("{%d, %s}", this.ID, this.nom);
    }

    public int getId() {
        return ID;
    }

    public String getNom() {
        return nom;
    }
}

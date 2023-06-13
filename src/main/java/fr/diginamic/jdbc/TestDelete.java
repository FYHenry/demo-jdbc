package fr.diginamic.jdbc;

import fr.diginamic.jdbc.entites.Fournisseur;

import java.util.Scanner;

public class TestDelete {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.println("Mot de passe :");
        final FournisseurDaoJdbc fDJ = new FournisseurDaoJdbc(scanner.next());
        if(fDJ.delete(new Fournisseur(4, "Maison des peintures"))) {
            System.out.println("Ça aurait pu retourner être un « ResultSet ».");
        } else {
            System.out.println("Ça aurait pu retourner un « Integer ».");
        }
    }
}
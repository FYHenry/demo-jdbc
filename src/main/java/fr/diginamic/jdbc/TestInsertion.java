package fr.diginamic.jdbc;

import fr.diginamic.jdbc.entites.Fournisseur;

import java.util.Scanner;

public class TestInsertion {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.println("Mot de passe :");
        final FournisseurDaoJdbc fDJ = new FournisseurDaoJdbc(scanner.next());
        fDJ.insert(new Fournisseur(4, "Maison de la peinture"));
    }
}

package fr.diginamic.jdbc;

import fr.diginamic.jdbc.entites.Fournisseur;

import java.util.Scanner;

public class TestUpdate {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.println("Mot de passe :");
        final FournisseurDaoJdbc fDJ = new FournisseurDaoJdbc(scanner.next());
        System.out.printf("Nombre de subtitutions : %d\n",
                fDJ.update("Maison de la peinture",
                "Maison des peintures"));
    }
}

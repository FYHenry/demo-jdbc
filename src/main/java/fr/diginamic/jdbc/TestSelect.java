package fr.diginamic.jdbc;

import java.util.Scanner;

public class TestSelect {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.println("Mot de passe :");
        final FournisseurDaoJdbc fDJ = new FournisseurDaoJdbc(scanner.next());
        System.out.println(fDJ.extraire());
    }
}
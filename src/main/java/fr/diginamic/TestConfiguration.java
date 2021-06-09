package fr.diginamic;

import java.util.Iterator;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class TestConfiguration {

	public static void main(String[] args) throws ConfigurationException {

		Configurations configurations = new Configurations();

		// Chargement des 2 configurations: properties et XML
		Configuration confprops = configurations.properties("configuration.properties");
		Configuration confXML = configurations.xml("database.xml");

		// Boucle de parcours de la conf properties
		Iterator<String> iterkeys = confprops.getKeys();
		while (iterkeys.hasNext()) { // tant qu'on n'est pas au bout cela retourne true
			String key = iterkeys.next(); // Je retourne l'élément courant et j'avance d'un cran
			String valeur = confprops.getString(key);

			System.out.println(key + " : " + valeur);
		}

		System.out.println("------------------------------------------");

		// Boucle de parcours de la conf XML
		iterkeys = confXML.getKeys();
		while (iterkeys.hasNext()) { // tant qu'on n'est pas au bout cela retourne true
			String key = iterkeys.next(); // Je retourne l'élément courant et j'avance d'un cran
			String valeur = confXML.getString(key);

			System.out.println(key + " : " + valeur);
		}
	}

}

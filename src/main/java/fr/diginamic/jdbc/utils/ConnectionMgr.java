package fr.diginamic.jdbc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.mariadb.jdbc.Driver;

/**
 * Cette classe fournit une méthode qui retourne une Connection à la base de
 * données. IMPORTANT: les paramètres d'accès à la base de données sont dans le
 * fichier database.xml
 * 
 * @author DIGINAMIC
 *
 */
public class ConnectionMgr {

	/** configuration XML */
	private static Configuration configuration;
	/** url */
	private static String url;
	/** user */
	private static String user;
	/** pwd */
	private static String pwd;

	/**
	 * Retourne une connexion à la base de données
	 * 
	 * @return {@link Connection}
	 */
	public static Connection getConnection() {

		// On regarde si l'objet configuration est null ou pas
		if (configuration == null) {

			// S'il est null, on invoque la méthode ci-dessous pour charger la configuration
			// du fichier database.xml ainsi que le driver JDBC
			loadConfiguration();
		}

		try {
			return DriverManager.getConnection(url, user, pwd);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Cette méthode charge le driver JDBC ainsi que le fichier database.xml. Elle
	 * n'est exécutée qu'une seule fois.
	 */
	private static void loadConfiguration() {

		try {
			// Chargement du Driver
			DriverManager.registerDriver(new Driver());
		} catch (SQLException e) {
			throw new RuntimeException("Impossible de trouver le driver JDBC.");
		}

		try {
			// Chargement du fichier XML
			Configurations configurations = new Configurations();
			configuration = configurations.xml("database.xml");

			url = configuration.getString("database.url");
			user = configuration.getString("database.user");
			pwd = configuration.getString("database.pwd");

		} catch (ConfigurationException e) {
			throw new RuntimeException(e);
		}

	}
}

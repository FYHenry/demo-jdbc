package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.mariadb.jdbc.Driver;

public class ConnectionMgr {

	private static Configuration configuration;
	private static String url;
	private static String user;
	private static String pwd;

	public static Connection getConnection() {
		if (configuration == null) {
			loadConfiguration();
		}
		try {
			return DriverManager.getConnection(url, user, pwd);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private static void loadConfiguration() {

		try {
			DriverManager.registerDriver(new Driver());
		} catch (SQLException e) {
			throw new RuntimeException("Impossible de trouver le driver JDBC.");
		}

		try {
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

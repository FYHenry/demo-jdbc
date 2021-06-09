package fr.diginamic;

import java.util.Enumeration;
import java.util.ResourceBundle;

public class TestProperties {

	public static void main(String[] args) {
		ResourceBundle conf = ResourceBundle.getBundle("configuration");

		Enumeration<String> enumeration = conf.getKeys();
		while (enumeration.hasMoreElements()) {
			String key = enumeration.nextElement();
			System.out.println(key + " : " + conf.getString(key));
		}

	}
}

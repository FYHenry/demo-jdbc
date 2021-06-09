package fr.diginamic.interfacerappels;

/**
 * Addition est une implémentation d'Operation
 * 
 * @author DIGINAMIC
 *
 */
public class Addition implements Operation {

	@Override
	public int exec(int a, int b) {

		return a + b;
	}

	@Override
	public void doSth() {

	}
}

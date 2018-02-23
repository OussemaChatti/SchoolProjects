package de.NilsReichardt.Auktionshaus;

public class Person {
	private String zName;

	public Person(String pName) {
		setName(pName);
	}

	// Getter & Setter
	public void setName(String pName) {
		zName = pName;
	}

	public String getName() {
		return zName;
	}
}

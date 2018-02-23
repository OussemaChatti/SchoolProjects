package de.NilsReichardt.Auktionshaus;

public class Gebot {
	int zBetrag;
	Person zKenntPerson;

	public Gebot(int pBetrag, Person pPerson) {
		personKennenlernen(pPerson);
		setBetrag(pBetrag);
	}

	// Objekt Person kennenlernen
	public void personKennenlernen(Person pPerson) {
		zKenntPerson = pPerson;
	}

	// Getter & Setter
	public int getBetrag() {
		return zBetrag;
	}

	public void setBetrag(int pBetrag) {
		// Überprüfen, dass der Parameter auch über 0 ist
		if (pBetrag > 0) {
			zBetrag = pBetrag;
		} else {
			zBetrag = 0;
		}
	}

	public Person getPerson() {
		return zKenntPerson;
	}
}

package de.NilsReichardt.VerflixteSiebenV1;

public class Spieler {

	private int zEinsatz;	// Einsatz des Spielers
	private String zName;	// Name des Spielers
	private int zPunkte;	// Summe der Punkte vom dem Spieler

	// Konstruktor für Objekte der Klasse Spieler
	public Spieler(String pName) {
		setName(pName);
	}

	// Getter & Setter
	public int getEinsatz() {
		return zEinsatz;
	}
	public void setEinsatz(int pEinsatz) {
		if (pEinsatz > 0) {
			zEinsatz = pEinsatz;
		} else {
			zEinsatz = 0;
		}
	}

	public String getName() {
		return zName;
	}
	public void setName(String pName) {
		zName = pName;
	}

	public int getPunkte() {
		return zPunkte;
	}
	public void setPunkte(int pPunkte) {
		if (pPunkte > 0) {
			zPunkte = pPunkte;
		} else {
			zPunkte = 0;
		}
	}
	public void addPunkte(int pPunkte) {
		if (pPunkte + zPunkte > 0) {
			zPunkte += pPunkte;
		} else {
			zPunkte = 0;
		}
	}

}

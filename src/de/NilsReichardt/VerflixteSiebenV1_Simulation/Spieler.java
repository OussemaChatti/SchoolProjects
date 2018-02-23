package de.NilsReichardt.VerflixteSiebenV1_Simulation;

public class Spieler {

	private int zPunkte;    // Summe der Punkte vom dem Spieler

	// Getter & Setter
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

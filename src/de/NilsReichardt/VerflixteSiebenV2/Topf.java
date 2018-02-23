package de.NilsReichardt.VerflixteSiebenV2;

import javax.swing.*;

public class Topf {

	private int zInhalt;

	public int getInhalt() {
		return zInhalt;
	}

	private void setInhalt(int pInhalt) {
		if (pInhalt > 0) {
			zInhalt = pInhalt;
		} else {
			zInhalt = 0;
		}
	}

	public void einzahlen(int pEinsatz) {
		setInhalt(getInhalt() + pEinsatz);
	}

	public int auszahlen(int pAuszahlMenge) {
		// Prüfe, ob angeforderte Menge im Topf ist
		if ((getInhalt() - pAuszahlMenge) >= 0) {
			setInhalt(getInhalt() - pAuszahlMenge);
			return pAuszahlMenge;
		} else {
			JOptionPane.showMessageDialog(new JFrame(), "Der angegebene Betrag ist nicht im Topf vorhanden", "Zu wenig im Topf...", JOptionPane.INFORMATION_MESSAGE);
			return 0;
		}
	}

}

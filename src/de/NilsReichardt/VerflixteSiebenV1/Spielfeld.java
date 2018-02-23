package de.NilsReichardt.VerflixteSiebenV1;

import java.util.concurrent.ThreadLocalRandom;

public class Spielfeld {

	// Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
	private Spieler spieler1;
	private Spieler spieler2;

	private int zVersucheStartspieler;
	private int zWuerfelAnzahl;

	// Konstruktor für Objekte der Klasse Spielfeld
	public Spielfeld(String pSpieler1_Name, String pSpieler2_Name) {
		spieler1 = new Spieler(pSpieler1_Name);
		spieler2 = new Spieler(pSpieler2_Name);

		setWuerfelAnzahl(2);    // Anzahl der Würfel festlegen
	}

	/**
	 * @param pVersucheStartspieler Anzahl der Versuche des Startspielers
	 */
	public void spielen(int pVersucheStartspieler, int pEinsatzSpieler1, int pEinsatzSpieler2) {
		// System.out.println("");
		System.out.println("SPIEL WIRD GESTARTET...");
		System.out.println("Anzahl der Würfel: " + getWuerfelAnzahl());
		System.out.println("");

		// Einsatz der Spieler speichern
		spieler1.setEinsatz(pEinsatzSpieler1);
		spieler2.setEinsatz(pEinsatzSpieler2);
		int hEinsatz = spieler1.getEinsatz() + spieler2.getEinsatz();

		// Würfelversuche speichern - Warum in Setter speichern? siehe Vorteil unter Getter & Setter
		setVersucheStartspieler(pVersucheStartspieler);

		// Spieler spielen lassen
		System.out.println(spieler1.getName() + " fängt an.");
		wuerfeln(spieler1);
		System.out.println("Nun ist " + spieler2.getName() + " an der Reihe");
		wuerfeln(spieler2);

		//Gewinner herausfinden und ausgeben#
		System.out.println("---");
		System.out.println("SPIEL WIRD BEENDET...");
		if (spieler1.getPunkte() > spieler2.getPunkte()) {
			System.out.println(getWinnerMessage(spieler1, hEinsatz));
		} else {
			if (spieler1.getPunkte() < spieler2.getPunkte()) {
				System.out.println(getWinnerMessage(spieler2, hEinsatz));
			} else {
				System.out.println("Unentschieden! Beide Spieler haben " + spieler1.getPunkte() + "!");
			}
		}
	}

	/**
	 * @param pSpieler         Spieler, welcher gerade würfelt
	 */
	private void wuerfeln(Spieler pSpieler) {
		boolean hSiebenGewuerfelt = false; // Boolean für gewürfelte Sieben

		for (int i = 0; i < getVersucheStartspieler() && !hSiebenGewuerfelt; i++) {
			int hWuerfelzahl = 0; // Würfel auf 0 setzten

			// Würfeln...
			for (int j = 0; j < getWuerfelAnzahl(); j++) {
				hWuerfelzahl += getRandomNumber(6); // Zu hWuerfelzahl die gewürfelte Zahl addieren
			}

			System.out.println("Gewürfelte Zahl (Versuch: " + (i + 1) + "): " + hWuerfelzahl); // Gewürfelte Zahl ausgeben

			// Prüfen, ob eine 7 gewürfel worden ist.
			if (hWuerfelzahl == 7) {
				pSpieler.setPunkte(pSpieler.getPunkte() - 7);
				System.out.println(pSpieler.getName() + " hat eine Sieben gewürfel!");
				System.out.println("Somit wechselt das Würfelrecht");
				hSiebenGewuerfelt = true;
			} else {
				pSpieler.addPunkte(hWuerfelzahl);   // Gewürfelte Zahl zu den Punkten addieren
			}

			System.out.println("Punkte von " + pSpieler.getName() + ": " + pSpieler.getPunkte());
		}

		System.out.println("");
	}

	private int getRandomNumber(int pGrenze) {
		return ThreadLocalRandom.current().nextInt(pGrenze) + 1;    // Random Zahlen generieren und um 1 addieren
	}

	// -- Getter und Setter --
	// Warum Getter & Setter? Mit dem Setter ist es möglich, die Eingabe zu kontrollieren (z.B. keine negativen Zahlen) und Regeln für die Eingabe festzulegen
	private int getVersucheStartspieler() {
		return zVersucheStartspieler;
	}
	private void setVersucheStartspieler(int pVersucheStartspieler) {
		if (pVersucheStartspieler > 0) {
			zVersucheStartspieler = pVersucheStartspieler;
		} else {
			zVersucheStartspieler = 0;
		}
	}

	// Message für Gewinner bekommen --> Vorteil: Keine doppelte Zeile und schnelleres ändern
	private String getWinnerMessage(Spieler pSpieler, int pEinsatz) {
		String hMessage = pSpieler.getName() + " hat mit " + pSpieler.getPunkte() + " Punkten gewonnen und bekommt " + pEinsatz + " Bitcoins! Herzlichen Glückwunsch!";
		return hMessage;
	}
	private int getWuerfelAnzahl() {
		return zWuerfelAnzahl;
	}
	private void setWuerfelAnzahl(int pWuerfelAnzahl) {
		if (pWuerfelAnzahl > 1 && pWuerfelAnzahl < 8) {
			zWuerfelAnzahl = pWuerfelAnzahl;
		} else {
			zWuerfelAnzahl = 1;
		}
	}

}

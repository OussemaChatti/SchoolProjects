package de.NilsReichardt.VerflixteSiebenV1_Simulation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class Spielfeld {

	// Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
	private Spieler spieler1;

	private int zWuerfelVersuche;
	private int zWuerfelAnzahl;

	private int[] test = new int[1500]; // Anzahl an Würfelversuche
	private long anzahlSimulation = 1000000l;   // 1 Mio.

	private String txtFileName = "ausgabe.txt";
	private String csvFileName = "ausgabe.csv";

	/**
	 * Konstruktor für Objekte der Klasse Spielfeld
	 */
	public Spielfeld() {
		spieler1 = new Spieler();

		setWuerfelAnzahl(2);    // Anzahl der Würfel festlegen
	}

	public void spielen() {
		long starttime = System.nanoTime(); // Startzeit in Nanosekunden ermitteln
		System.out.println("Starte Simulation (Durchläufe: " + anzahlSimulation + ") mit " + getWuerfelAnzahl() + " Würfeln und " + test.length + " Versuchen...");

		clearFile(txtFileName); // Text-Datei leeren
		clearFile(csvFileName); // CSV-Datei leeren

		// Spieler spielen lassen
		for (long i = 0; i < anzahlSimulation; i++) {
			wuerfeln(spieler1);
		}

		writeFile(txtFileName, "Simulation (Durchläufe: " + anzahlSimulation + ") mit " + getWuerfelAnzahl() + " Würfeln und " + test.length + " Versuchen:");
		for (int i = 0; i < test.length; i++) {
			// System.out.println((i+1) + ";" + test[i]);
			writeFile(txtFileName, (i + 1) + ";" + test[i]);
			writeFile(csvFileName, (i + 1) + ";" + test[i]);
		}

		long endtime = System.nanoTime();   // Endzeit in Nanosekunden ermitteln
		double elapsedtime = (double) (endtime - starttime) / 1000000000; // Verstrichene Zeit ermitteln
		writeFile(txtFileName, "Verstrichene Zeit in Sekunden: " + elapsedtime);
		System.out.println("Simulation beendet. Verstrichene Zeit in Sekunden: " + elapsedtime); // Verstrichene Zeit ausgeben
	}

	/**
	 * @param pSpieler         Spieler, welcher gerade würfelt
	 */
	public void wuerfeln(Spieler pSpieler) {
		boolean hSiebenGewuerfelt = false; // Boolean für gewürfelte Sieben

		// Länge des Arrays zählen
		for (int i = 0; i < test.length && !hSiebenGewuerfelt; i++) {
			int hWuerfelzahl = 0; // Würfel auf 0 setzten

			// Würfeln...
			for (int j = 0; j < getWuerfelAnzahl(); j++) {
				hWuerfelzahl += getRandomNumber(6); // Zu hWuerfelzahl die gewürfelte Zahl addieren
			}

			// Prüfen, ob eine 7 gewürfel worden ist.
			if (hWuerfelzahl == 7) {
				pSpieler.setPunkte(pSpieler.getPunkte() - 7);
				hSiebenGewuerfelt = true;

				test[i]++;
			} else {
				pSpieler.addPunkte(hWuerfelzahl);   // Gewürfelte Zahl zu den Punkten addieren
			}
		}
	}

	public int getRandomNumber(int pGrenze) {
		return ThreadLocalRandom.current().nextInt(pGrenze) + 1;    // Random Zahlen generieren und um 1 addieren
	}

	// -- Getter und Setter --
	// Warum Getter & Setter? Mit dem Setter ist es möglich, die Eingabe zu kontrollieren (z.B. keine negativen Zahlen) und Regeln für die Eingabe festzulegen
	public int getWuerfelAnzahl() {
		return zWuerfelAnzahl;
	}

	public void setWuerfelAnzahl(int pWuerfelAnzahl) {
		if (pWuerfelAnzahl > 1 && pWuerfelAnzahl < 8) {
			zWuerfelAnzahl = pWuerfelAnzahl;
		} else {
			zWuerfelAnzahl = 1;
		}
	}

	// In Datei schreiben
	public void writeFile(String pFileName, String pText) {
		try {
			FileWriter fw = new FileWriter(pFileName, true);
			BufferedWriter bw = new BufferedWriter(fw);

			bw.write(pText);    // Text in Datei schreiben
			bw.write(System.lineSeparator());   // Neue Zeile erzeugen

			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Datei leeren
	public void clearFile(String pFileName) {
		try {
			FileWriter fw = new FileWriter(pFileName);
			BufferedWriter bw = new BufferedWriter(fw);

			bw.write("");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

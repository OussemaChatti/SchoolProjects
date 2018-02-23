package de.NilsReichardt.Auktionshaus;

public class Posten {
	int zMindestgebot;
	int zNummer;
	String zBeschreibung;

	Gebot zKenntGebot;

	public Posten(int pNummer, String pBeschreibung, int pMindestgebot) {
		setNummer(pNummer);
		setBeschreibung(pBeschreibung);
		setMindestgebot(pMindestgebot);
	}

	// Objekt Gebot kennenlernen
	public void gebotKennenlernen(Gebot pGebot) {
		zKenntGebot = pGebot;
	}

	// Gibt Gebot zurück
	public Gebot getAktuellesGebot() {
		return zKenntGebot;
	}

	// Getter & Setter
	public void setMindestgebot(int pMindestgebot) {
		if (pMindestgebot > 0) {
			zMindestgebot = pMindestgebot;
		} else {
			zMindestgebot = 0;
		}
	}

	public int getMindestgebot() {
		return zMindestgebot;
	}

	public void setNummer(int pNummer) {
		// Prüfen, ob Nummer > 0
		if (pNummer > 0) {
			zNummer = pNummer;
		} else {
			zNummer = 0;
		}
	}

	public void setBeschreibung(String pBeschreibung) {
		zBeschreibung = pBeschreibung;
	}
	public String getBeschreibung() {
		return zBeschreibung;
	}
}

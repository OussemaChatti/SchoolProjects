package de.NilsReichardt.Auktionshaus;

public class Auktionshaus {

	// Posten
	private Posten zHatPosten1;
	private Posten zHatPosten2;
	private Posten zHatPosten3;
	private Posten zHatPosten4;
	private Posten zHatPosten5;

	private String fehlerTAG = "[FEHLER]"; // Fehler Tag

	public Auktionshaus() {
		zHatPosten1 = new Posten(1, "Das ist ein Test. #1", 50);
		zHatPosten2 = new Posten(2, "Das ist ein Test. #2", 10);
		zHatPosten3 = new Posten(3, "Das ist ein Test. #3", 25);
		zHatPosten4 = new Posten(4, "Das ist ein Test. #4", 30);
		zHatPosten5 = new Posten(5, "Das ist ein Test. #5", 60);

		// Auktion starten...
		System.out.println("=== AUKTIONSSTART === ");

		// Beschreibungen ausgeben
		for(int i = 1; i < 6; i++) {
			System.out.println("Beschreibung für Posten #" + i + ": " +getPosten(i).getBeschreibung());
		}
		System.out.println("");

		// Text für Gebote
		System.out.println("=== GEBOTE ===");
	}

	public void auktionsende() {
		System.out.println("");
		System.out.println("=== AUKTIONSENDE ===");
		for (int i = 1; i < 6; i++) {
			if (getPosten(i).getAktuellesGebot() == null) {
				System.out.println("Posten #" + i + " wurde nicht ersteigert!");
			} else {
				System.out.println("Posten #" + i + " wurde von " + getPosten(i).getAktuellesGebot().getPerson().getName()
						+ " für " + getPosten(i).getAktuellesGebot().getBetrag() + " Münzen ersteigert.");
			}
		}
	}

	public void bieteFuer(int pPostennummer, Person pBieter, int pBetrag) {
		boolean hIstGebotZulaessig = false;

		// Prüfen, ob schon jemand für diesen Posten geboten hat
		if (getPosten(pPostennummer).getAktuellesGebot() == null) {

			// Prüfen, ob Mindestgebot höher als pBetrag ist
			if (getPosten(pPostennummer).getMindestgebot() > pBetrag) {
				// pBetrag ist kleiner als Mindestgebot --> Fehlermedlung ausgeben
				System.out.println(fehlerTAG + " Der Betrag von " + pBieter.getName() + " für das Gebot des Postens #" + pPostennummer + " liegt nicht über dem Mindestgebot."
						+ " Das Mindestgebot beträgt " + getPosten(pPostennummer).getMindestgebot() + " Münzen. Sein Betrag betrug " + pBetrag + " Münzen.");
			} else {
				// pBetrag ist höher als Mindestgebot --> Also neues Gebot
				hIstGebotZulaessig = true;
			}

		} else {

			// Prüfen, ob Betrag für aktuelles Gebot größer als pBetrag ist
			if (getPosten(pPostennummer).getAktuellesGebot().getBetrag() > pBetrag) {
				// pBetrag ist kleiner --> Fehlermeldung ausgeben
				System.out.println(fehlerTAG + " Der höhste Betrag für das Gebot des Postens #" + pPostennummer
						+ " beträgt " + getPosten(pPostennummer).getAktuellesGebot().getBetrag() + "."
						+ " Du musst also mehr für diesen Posten bieten.");
			} else {
				// pBetrag ist höher --> Also neues Gebot
				hIstGebotZulaessig = true;
			}

		}

		// Gebot ist berechtigt
		if (hIstGebotZulaessig) {
			getPosten(pPostennummer).gebotKennenlernen(new Gebot(pBetrag, pBieter));
			System.out.println(pBieter.getName() + " hat für den Posten #" + pPostennummer + " ein neues Gebot abgegeben: " + pBetrag + " Münzen.");
		}
	}

	public Posten getPosten(int pNummer) {
		// Posten zurückgeben
		if (pNummer == 1) return zHatPosten1;
		else if (pNummer == 2) return zHatPosten2;
		else if (pNummer == 3) return zHatPosten3;
		else if (pNummer == 4) return zHatPosten4;
		else if (pNummer == 5) return zHatPosten5;
		else {
			// Zahl ist nicht 1-5
			System.out.println(fehlerTAG + " Den Posten " + pNummer + " gibt es nicht!");
			return null;
		}
	}
}

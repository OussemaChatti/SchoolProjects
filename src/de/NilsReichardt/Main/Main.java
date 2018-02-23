package de.NilsReichardt.Main;

import de.NilsReichardt.Auktionshaus.Auktionshaus;
import de.NilsReichardt.Auktionshaus.Person;
import de.NilsReichardt.VerflixteSiebenV1.Spielfeld;
import de.NilsReichardt.VerflixteSiebenV2.Spieler;
import de.NilsReichardt.VerflixteSiebenV2.Topf;
import de.NilsReichardt.VerflixteSiebenV2.Wuerfel;

public class Main {

	public static void main(String[] args) {

		auktionshaus();
//		verflixteSiebenV1();
//		verflixteSiebenV2();
//		verflixteSiebenV1_Simulation();

	}

	private static void auktionshaus() {

		System.out.println("Project: Auktionshaus");

		Auktionshaus aHaus = new Auktionshaus();
		Person person1 = new Person("Peter");
		Person person2 = new Person("Olaf");
		Person person3 = new Person("Mr. X");
		Person person4 = new Person("Günter");

		aHaus.bieteFuer(1, person1, 234);
		aHaus.bieteFuer(2, person2, 23);
		aHaus.bieteFuer(1, person2, 543);
		aHaus.bieteFuer(4, person3, 93);
		aHaus.bieteFuer(5, person4, 3);

		aHaus.auktionsende();

	}

	private static void verflixteSiebenV1() {

		System.out.println("Project: VerflixteSieben (v1) ");

		Spielfeld spielfeld = new Spielfeld("Nils", "Kai");
		spielfeld.spielen(12, 6, 7);

	}

	private static void verflixteSiebenV2() {

		// Einrichtung
		Topf topf1 = new Topf();
		Wuerfel wuerfel1 = new Wuerfel();
		Wuerfel wuerfel2 = new Wuerfel();
		Spieler spieler1 = new Spieler(50, "Max", topf1, wuerfel1, wuerfel2);
		Spieler spieler2 = new Spieler(40, "Leon", topf1, wuerfel1, wuerfel2);
		spieler1.anderenSpielerKennenlernen(spieler2);
		spieler2.anderenSpielerKennenlernen(spieler1);

		// === RUNDE 1: Startet ===
		spieler1.setEinsatz(10); // Einsatz für die Runde setzen
		spieler2.setEinsatz(25); // Einsatz für die Runde setzen
		spieler1.setWuerfelrecht(true); // Würfel zu Spieler 1

		Util.emptyLines(1);

		for(int i = 0; i < 5; i++) {
			if(spieler1.hatWuerfelrecht()) {
				spieler1.wuerfeln();
			}
		}

		spieler1.setWuerfelrecht(false);
		spieler2.setWuerfelrecht(true);

		for(int i = 0; i < 5; i++) {
			if(spieler2.hatWuerfelrecht()) {
				spieler2.wuerfeln();
			}
		}

		spieler1.rundeBeenden();
		// === RUNDE 1: Beendet ===

	}

	private static void verflixteSiebenV1_Simulation() {

		de.NilsReichardt.VerflixteSiebenV1_Simulation.Spielfeld spielfeld = new de.NilsReichardt.VerflixteSiebenV1_Simulation.Spielfeld();
		spielfeld.spielen();

	}

}

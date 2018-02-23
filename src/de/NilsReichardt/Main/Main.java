package de.NilsReichardt.Main;

import de.NilsReichardt.Auktionshaus.Auktionshaus;
import de.NilsReichardt.Auktionshaus.Person;

public class Main {

	public static void main(String[] args) {

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

}

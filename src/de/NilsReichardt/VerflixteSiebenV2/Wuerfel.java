package de.NilsReichardt.VerflixteSiebenV2;

import java.util.concurrent.ThreadLocalRandom;

public class Wuerfel {

	public int rollen()
	{
		return ThreadLocalRandom.current().nextInt(6) + 1; // Random Number
	}

}

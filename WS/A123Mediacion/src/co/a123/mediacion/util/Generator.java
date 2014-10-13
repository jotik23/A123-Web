package co.a123.mediacion.util;

import java.util.Random;

public class Generator {

	static Random random = new Random();
	
	
	public static String getRandomMessageId() {
		return "m-" + Long.toString(random.nextLong());
	}
}

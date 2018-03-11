package model;

import java.util.Random;

public class Pagantes {
	public int geraPagantesAleatorio() {
		Random rand = new Random();
		return rand.nextInt(50000) + 5000;
	}
}

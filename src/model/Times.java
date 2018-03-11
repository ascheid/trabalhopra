package model;

import java.util.Random;

public class Times {
	
	private String[] times = { "Flamengo", "Grêmio", "São Paulo", "Santos",
			"Internacional", "Vitória", "Atlético Paranense",
			"Atlético Mineiro", "Santos", "Fluminense", "Criciuma", "JEC",
			"Juventos", "Real Madrid", "Bayern" };

	public String[] getTimes() {
		return times;
	}

	public void setTimes(String[] times) {
		this.times = times;
	}
	
	public String[] getTimeAleatorio() {
		Random rand = new Random();
		int t1 = rand.nextInt(15);
		int t2 = rand.nextInt(15);
		while(t1 == t2) {
			t2 = rand.nextInt(15);
		}
		String[] t = new String[2];
		t[0] = times[t1];
		t[1] = times[t2];
		return t;
	}
}

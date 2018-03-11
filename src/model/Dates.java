package model;

import java.util.Random;

public class Dates {
	public String geraDataAleatoria() {
		int dia, mes;
		Random random = new Random();
		mes = random.nextInt(12) + 1;
		if (mes == 2)
			dia = random.nextInt(28) + 1;
		else if (mes % 2 == 0)
			dia = random.nextInt(30) + 1;
		else
			dia = random.nextInt(31) + 1;
		return dia + "/" + mes + "/2018";
	}
}

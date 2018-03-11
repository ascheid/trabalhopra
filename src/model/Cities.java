package model;

import java.util.Random;

public class Cities {
	private String[] cidades = { "Joinville", "Blumenau", "Florianopolis",
			"Curitiba", "Maringa", "Porto Alegre", "Sao Paulo",
			"Rio de Janeiro", "Santos", "Belo Horizonte", "Manaus",
			"Campo Grande", "Salvador", "Santa Maria", "Vitória" };

	public String[] getCidades() {
		return cidades;
	}

	public void setCidades(String[] cidades) {
		this.cidades = cidades;
	}
	
	public String geraCidadeAleatoria() {
		Random r = new Random();
		return cidades[r.nextInt(15)];
	}
}

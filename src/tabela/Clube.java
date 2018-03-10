package tabela;

public class Clube {

	private int pontos;
	public String nome;
	private int golsMarcados;
	private int golsSofridos;
	private int saldoGols;
	
	public Clube(String nome) {
		this.nome = nome;		
	}
	
	public int consultarPontos() {
		return pontos;
	}

	public void adicionaPontos(int pontos) {
		this.pontos += pontos;
	}

	public int consultaGolsMarcados() {
		return golsMarcados;
	}

	public void adicionaGolsMarcados(int golsMarcados) {
		this.golsMarcados += golsMarcados;
	}

	public int consultaGolsSofridos() {
		return golsSofridos;
	}

	public void adicionaGolsSofridos(int golsSofridos) {
		this.golsSofridos = golsSofridos;
	}

	public int consultaSaldoGols() {
		return this.golsMarcados - this.golsSofridos;
	}

}

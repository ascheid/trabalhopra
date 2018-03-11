/**
 * 
 */
package tabela;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;

public class Tabela {

	// Lista de cidades
	static String[] cidades = { "Joinville", "Blumenau", "Florianopolis",
			"Curitiba", "Maringa", "Porto Alegre", "Sao Paulo",
			"Rio de Janeiro", "Santos", "Belo Horizonte", "Manaus",
			"Campo Grande", "Salvador", "Santa Maria", "Vitória" };
	static String[] times = { "Flamengo", "Grêmio", "São Paulo", "Santos",
			"Internacional", "Vitória", "Atlético Paranense",
			"Atlético Mineiro", "Santos", "Fluminense", "Criciuma", "JEC",
			"Juventos", "Real Madrid", "Bayern" };
/*
	public static void main(String[] args) throws IOException {

		final long startTime = System.nanoTime();
		
		Scanner in = new Scanner(System.in);
		int numRegistros = 0;

		//System.out.println("Entre com o numero de registros do campeonato.");
		//numRegistros = in.nextInt();

		in.close();

		criarTimes();
		//iniciarJogos(numRegistros);
		recuperarJogos();
		final long duration = (System.nanoTime() - startTime) / 1000000000;

		System.out.println(duration);
	}
*/
	public static String dataJogo() {

		String data = "";
		int dia, mes;

		Random rand = new Random();

		mes = rand.nextInt(12) + 1;
		if (mes == 2) {
			dia = rand.nextInt(28) + 1;
		}
		dia = rand.nextInt(31) + 1;

		data = dia + "/" + mes + "/2018";

		return data;

	}

	public static int pagantes() {
		Random rand = new Random();
		return rand.nextInt(30000) + 5000;
	}

	public static String cidade() {
		Random rand = new Random();
		return cidades[rand.nextInt(15) + 0];
	}

	public static String[] criarTimes() {

		Random rand = new Random();
		String time1 = times[rand.nextInt(15) + 0];
		String time2 = times[rand.nextInt(15) + 0];
		String times[] = { time1, time2 };

		return times;
	}

	/**
	 * @return inicia as partidas do campeonato
	 */
	public static void iniciarJogos(int numRegistros) {
		Path p = Paths.get("./logfile.txt");
		System.out.println("Iniciando");
		try (OutputStream out = new BufferedOutputStream(
			      Files.newOutputStream(p, StandardOpenOption.CREATE))) {
		for (int i = 0; i < 2000000; i++) {

			String[] times = criarTimes();

			int golsTimeCasa = 0, golsTimeFora = 0;

			// Gera alteatoriamente o gol dos times na partida de 0 a 5
			Random rand = new Random();
			golsTimeCasa = rand.nextInt(5) + 0;
			golsTimeFora = rand.nextInt(5) + 0;

			/*System.out.println(times[0] + " (" + golsTimeCasa + ") x ("
					+ golsTimeFora + ") " + times[1] + " | " + dataJogo()
					+ " | Publico Pagante: " + pagantes() + " | " + cidade());*/
			String line = times[0] + " (" + golsTimeCasa + ") x ("
					+ golsTimeFora + ") " + times[1] + " | " + dataJogo()
					+ " | Publico Pagante: " + pagantes() + " | " + cidade() + "\r\n";
			byte[] streamOutput = line.getBytes();
			
			
				      out.write(streamOutput, 0, streamOutput.length);
				    

		}
		} catch (IOException x) {
		      System.err.println(x);
		    }
		System.out.println("Fim");
	}
	
	public static void recuperarJogos() throws IOException, IOException {
		
		try(BufferedReader br = new BufferedReader(new FileReader("./logfile.txt"))) {
		    for(int i=0; i<2000000;i++ ) {
		        System.out.println(br.readLine());
		    }
		    // line is not visible here.
		}
	}

	public static void consultarTabela(Clube[] clubes) {

		for (int i = 1; i < clubes.length; i++) {
			for (int j = 0; j < i; j++) {
				if (clubes[i].consultarPontos() > clubes[j].consultarPontos()) {
					Clube temp = clubes[i];
					clubes[i] = clubes[j];
					clubes[j] = temp;
				}
				if (clubes[i].consultarPontos() == clubes[j].consultarPontos()
						&& clubes[i].consultaSaldoGols() > clubes[j]
								.consultaSaldoGols()) {
					Clube temp = clubes[i];
					clubes[i] = clubes[j];
					clubes[j] = temp;
				}
			}
		}
		for (int i = 1; i <= clubes.length; i++) {
			System.out.println(i + "a posição: " + clubes[i - 1].nome + " com "
					+ clubes[i - 1].consultarPontos() + " e "
					+ clubes[i - 1].consultaSaldoGols() + " de saldo de gols.");
		}
	}

	public static void melhorAtaque(Clube[] clubes) {
		int maior = 0, menor = 0;
		for (int i = 0; i < clubes.length; i++) {
			if (clubes[i].consultaGolsMarcados() > maior) {
				maior = clubes[i].consultaGolsMarcados();
			}
		}
		for (int j = 0; j < clubes.length; j++) {
			if (clubes[j].consultaGolsMarcados() < menor) {
				menor = clubes[j].consultaGolsMarcados();
			}
		}
		for (int i = 0; i < clubes.length; i++) {
			if (clubes[i].consultaGolsMarcados() == maior) {
				System.out.println("O melhor ataque do campeonato é dO time "
						+ clubes[i].nome + " com "
						+ clubes[i].consultaGolsMarcados() + " pontos");
			}
		}
	}
}

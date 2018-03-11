package control;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;

import model.Cities;
import model.Dates;
import model.Pagantes;
import model.Times;
import utils.LogHandler;
import view.Janela;

public class FileHandler {
	private Path p = Paths.get("./bancodeinformacoes.txt");
	private LogHandler log = new LogHandler();
	private OutputStream out;

	public FileHandler() {
		try {
			out = new BufferedOutputStream(Files.newOutputStream(p, StandardOpenOption.CREATE));
			log.log("Criando o arquivo de informacoes");
		} catch (IOException e) {
			log.log("Problema ao abrir o arquivo do banco de informacoes");
			e.printStackTrace();
		}
	}

	public void criarBancoDeDados(int numRegistros) throws IOException {
		Random r = new Random();
		int golsTimeCasa;
		int golsTimeFora;
		String[] times;
		String line;

		Times t = new Times();
		Pagantes p = new Pagantes();
		Cities c = new Cities();
		Dates d = new Dates();

		byte[] streamOutput;
		log.log("Iniciando processo de geracao de informacoes no banco!");
		long start = System.currentTimeMillis();
		long totalBytes = 0;
		if (numRegistros == 0) {
			do {
				golsTimeCasa = r.nextInt(5);
				golsTimeFora = r.nextInt(5);
				times = t.getTimeAleatorio();
				line = times[0] + " (" + golsTimeCasa + ") x (" + golsTimeFora + ") " + times[1] + " | "
						+ d.geraDataAleatoria() + " | Publico Pagante: " + p.geraPagantesAleatorio() + " | "
						+ c.geraCidadeAleatoria() + "\r\n";
				streamOutput = line.getBytes();
				out.write(streamOutput, 0, streamOutput.length);
				totalBytes = totalBytes + streamOutput.length;
				if ((start - System.currentTimeMillis()) % 3000 == 0) {
					log.log("Escrevendo o arquivo... Total parcial: " + totalBytes / 1000000 + "Mb!");
					Janela.textArea.update(Janela.textArea.getGraphics());
					Janela.scrollPane.revalidate();
					Janela.scrollPane.repaint();
				}
			} while (totalBytes < 1073741824);
		} else {
			for (int i = 0; i < numRegistros; i++) {
				golsTimeCasa = r.nextInt(5);
				golsTimeFora = r.nextInt(5);
				times = t.getTimeAleatorio();
				line = times[0] + " (" + golsTimeCasa + ") x (" + golsTimeFora + ") " + times[1] + " | "
						+ d.geraDataAleatoria() + " | Publico Pagante: " + p.geraPagantesAleatorio() + " | "
						+ c.geraCidadeAleatoria() + "\r\n";
				streamOutput = line.getBytes();
				out.write(streamOutput, 0, streamOutput.length);
				totalBytes = totalBytes + streamOutput.length;
				if ((start - System.currentTimeMillis()) % 3000 == 0) {
					log.log("Escrevendo o arquivo... Total parcial: " + totalBytes / 1000000 + "Mb!");
					Janela.textArea.update(Janela.textArea.getGraphics());
					Janela.scrollPane.revalidate();
					Janela.scrollPane.repaint();
				}
			}
		}
		Long conclusao = (System.currentTimeMillis() - start) / 1000;
		log.log("Finalizado processo de geracao de informacoes no banco. Foi escrito: " + totalBytes / 1000000
				+ "Mb. Tempo de excucao: " + conclusao + "s.");

	}

	public String recuperarBancoDeDados(int numRegistros) {
		long start = System.currentTimeMillis();
		log.log("Iniciando processo de recuperacao de informacoes no banco de dados!");
		String line = "";
		long totalBytes = 0;
		try (BufferedReader br = new BufferedReader(new FileReader("./bancodeinformacoes.txt"))) {
			if (numRegistros == 0) {
				do {
					line = br.readLine();
					totalBytes += line.getBytes().length;
					log.log(line);
					if ((start - System.currentTimeMillis()) % 3000 == 0) {
						log.log("Escrevendo o arquivo... Total parcial: " + totalBytes / 1000000 + "Mb!");
						Janela.textArea.update(Janela.textArea.getGraphics());
						Janela.scrollPane.revalidate();
						Janela.scrollPane.repaint();
					}
				} while (line != null);
			} else {
				for (int i = 0; i < numRegistros; i++) {
					line = br.readLine();
					totalBytes += line.getBytes().length;
					if ((start - System.currentTimeMillis()) % 3000 == 0) {
						log.log("Escrevendo o arquivo... Total parcial: " + totalBytes / 1000000 + "Mb!");
						Janela.textArea.update(Janela.textArea.getGraphics());
						Janela.scrollPane.revalidate();
						Janela.scrollPane.repaint();
					}
					if (line != null)
						log.log(line);
				}
			}

		} catch (FileNotFoundException e) {
			log.log(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			log.log(e.toString());
			e.printStackTrace();
		}

		Long conclusao = (System.currentTimeMillis() - start) / 1000;
		log.log("Finalizado processo de recuperacao de informacoes no banco. Tempo de execucao: " + conclusao + "s.");
		return conclusao.toString();
	}
}

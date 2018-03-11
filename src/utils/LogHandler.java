package utils;

import java.util.Date;

import view.Janela;

public class LogHandler {

	private Date data;

	public void log(String s) {
		data = new Date();
		String d = "[" + data.toString() + "] = " + s;
		Janela.textArea.append(d + "\n");
		Janela.contentPane.revalidate();
		Janela.contentPane.repaint();
	}
}

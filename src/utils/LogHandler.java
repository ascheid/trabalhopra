package utils;

import java.util.Date;

import view.Janela;

public class LogHandler {
	
	private Date data;
	
	
	public String log(String s) {
		data = new Date();
		String d = "[" + data.toString() + "] = " + s;
		System.out.println(d);
		Janela.textArea.append(d + "\n");
		Janela.textArea.update(Janela.textArea.getGraphics());
		Janela.scrollPane.update(Janela.scrollPane.getGraphics());
		return d;
	}
}

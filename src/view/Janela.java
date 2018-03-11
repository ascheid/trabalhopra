package view;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import control.FileHandler;
import utils.LogHandler;

public class Janela extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField textField;
	public static JTextArea textArea = new JTextArea();
	public static JScrollPane scrollPane = new JScrollPane(textArea);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					Janela frame = new Janela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Janela() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Janela.class.getResource("/image/02_Soccer-512.png")));
		setTitle("Times de Futebol");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 839, 456);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		textArea.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

		textArea.setEditable(false);
		textArea.setBounds(20, 56, 793, 351);

		JButton btnRecuperar = new JButton("Ler Times");
		btnRecuperar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String line;
				LogHandler log = new LogHandler();
				try (BufferedReader br = new BufferedReader(new FileReader("./bancodeinformacoes.txt"))) {
					do {
						line = br.readLine();
						textArea.append(line);
					} while (line != null);
				} catch (FileNotFoundException f) {
					log.log(f.toString());
					f.printStackTrace();
				} catch (IOException c) {
					log.log(c.toString());
					c.printStackTrace();
				}

			}
		});
		btnRecuperar.setIcon(new ImageIcon(Janela.class.getResource("/image/usb-memory.png")));
		btnRecuperar.setBounds(162, 11, 109, 23);
		contentPane.add(btnRecuperar);

		JButton btnGerar = new JButton("Salvar Times");
		btnGerar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FileHandler fileHandler = new FileHandler();
				try {
					String t = textField.getText();
					if (t.equals("") || t.equals("0")) {
						textArea.append(fileHandler.criarBancoDeDados(0));
					} else
						textArea.append(fileHandler.criarBancoDeDados(Integer.parseInt(textField.getText())));
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnGerar.setIcon(new ImageIcon(Janela.class.getResource("/image/hard-disk.png")));
		btnGerar.setBounds(20, 11, 132, 23);
		contentPane.add(btnGerar);

		JLabel lblNewLabel = new JLabel("v1.0 - Trabalho de PRA");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(687, 15, 126, 14);
		contentPane.add(lblNewLabel);

		JSeparator separator = new JSeparator();
		separator.setBounds(20, 45, 793, 2);
		contentPane.add(separator);

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setToolTipText("Entre com o numero de registros");
		textField.setBounds(423, 12, 69, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Quantidade de Registros: ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(281, 15, 132, 14);
		contentPane.add(lblNewLabel_1);
		scrollPane.setAutoscrolls(true);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		scrollPane.setBounds(20, 58, 793, 349);
		contentPane.add(scrollPane);
		scrollPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { textArea }));
	}
}

package una.ac.cr.laberinto.gui;
import una.ac.cr.laberinto.modelo.Laberinto;

import javax.swing.*;

public class LaberintoFrame extends JFrame {
	private LaberintoPanel laberintoPanel;

	public LaberintoFrame(Laberinto laberinto) {
		laberintoPanel = new LaberintoPanel(laberinto);
		add(laberintoPanel);

		setTitle("Laberinto");
		setSize(800, 600); // Establece el tamaño de la ventana según tus necesidades
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // Centra la ventana en la pantalla
		setVisible(true);
	}
}

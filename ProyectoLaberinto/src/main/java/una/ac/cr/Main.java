package una.ac.cr;

import una.ac.cr.laberinto.algoritmos.AlgoritmoGeneracion;
import una.ac.cr.laberinto.gui.laberinto.LaberintoFrame;
import una.ac.cr.laberinto.gui.main.MainFrame;
import una.ac.cr.laberinto.modelo.Laberinto;

import javax.swing.*;

public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			// Crea la ventana principal
			MainFrame mainFrame = new MainFrame();
			mainFrame.setVisible(true);
		});
	}
}

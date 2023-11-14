package una.ac.cr;

import una.ac.cr.laberinto.algoritmos.AlgoritmoGeneracion;
import una.ac.cr.laberinto.gui.laberinto.LaberintoFrame;
import una.ac.cr.laberinto.modelo.Laberinto;

import javax.swing.*;

public class Main {
	public static void main(String[] args) {
		int filas = 10; // Define el número de filas de tu laberinto
		int columnas = 10; // Define el número de columnas de tu laberinto
		AlgoritmoGeneracion algoritmoGeneracion = new AlgoritmoGeneracion(filas, columnas);
		Laberinto laberinto = algoritmoGeneracion.getLaberinto();

		laberinto.mostrarNodos();

		SwingUtilities.invokeLater(() -> {
			new LaberintoFrame(laberinto);
		});
	}
}

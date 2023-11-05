package una.ac.cr.laberinto.gui;
import una.ac.cr.laberinto.modelo.Laberinto;
import una.ac.cr.laberinto.modelo.Nodo;

import javax.swing.*;
import java.awt.*;

public class LaberintoPanel extends JPanel {
	private Laberinto laberinto;

	public LaberintoPanel(Laberinto laberinto) {
		this.laberinto = laberinto;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		int cellWidth = getWidth() / laberinto.getColumnas();
		int cellHeight = getHeight() / laberinto.getFilas();

		for (int i = 0; i < laberinto.getFilas(); i++) {
			for (int j = 0; j < laberinto.getColumnas(); j++) {
				Nodo nodo = laberinto.getNodo(i, j);
				int x = j * cellWidth;
				int y = i * cellHeight;

				// Dibujar celdas
				g.setColor(Color.WHITE);
				g.fillRect(x, y, cellWidth, cellHeight);

				// Dibujar paredes
				g.setColor(Color.BLACK);
				if (nodo.getArriba() == null) {
					g.drawLine(x, y, x + cellWidth, y);
				}
				if (nodo.getIzquierda() == null) {
					g.drawLine(x, y, x, y + cellHeight);
				}
				if (nodo.getDerecha() == null) {
					g.drawLine(x + cellWidth, y, x + cellWidth, y + cellHeight);
				}
				if (nodo.getAbajo() == null) {
					g.drawLine(x, y + cellHeight, x + cellWidth, y + cellHeight);
				}

				// Verificar si el nodo es la entrada o la salida y pintarlo de un color diferente
				if (i == laberinto.getNodoEntrada().getFila() && j == laberinto.getNodoEntrada().getColumna()) {
					g.setColor(Color.GREEN); // Color de la entrada
					g.fillRect(x, y, cellWidth, cellHeight);
				} else if (i == laberinto.getNodoSalida().getFila() && j == laberinto.getNodoSalida().getColumna()) {
					g.setColor(Color.RED); // Color de la salida
					g.fillRect(x, y, cellWidth, cellHeight);
				}

			}
		}
	}
}

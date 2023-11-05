package una.ac.cr.laberinto.gui;
import una.ac.cr.laberinto.modelo.Laberinto;
import una.ac.cr.laberinto.modelo.Nodo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LaberintoPanel extends JPanel {
	private Laberinto laberinto;
	private LaberintoModel model;
	private LaberintoController controller;
	private int cellWidth;
	private int cellHeight;

	public LaberintoPanel(Laberinto laberinto) {
		this.laberinto = laberinto;
		this.model = new LaberintoModel(laberinto, this);
		this.controller = new LaberintoController(this.model);
		agregarKeyListener();
	}

	private void agregarKeyListener() {
		// Agregar el KeyListener del controlador al LaberintoPanel
		this.addKeyListener(controller.getKeyAdapter());

		// Necesitas establecer el enfoque en el panel para que pueda recibir eventos de teclado
		setFocusable(true);
		requestFocusInWindow();
	}
	@Override
	public void repaint() {
		System.out.println("Repintando");
		// Vuelve a dibujar el panel
		super.repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		cellWidth = getWidth() / laberinto.getColumnas();
		cellHeight = getHeight() / laberinto.getFilas();

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
					g.fillOval(x + cellWidth / 4, y + cellHeight / 4, cellWidth / 2, cellHeight / 2);
				} else if (i == laberinto.getNodoSalida().getFila() && j == laberinto.getNodoSalida().getColumna()) {
					g.setColor(Color.RED); // Color de la salida
					g.fillOval(x + cellWidth / 4, y + cellHeight / 4, cellWidth / 2, cellHeight / 2);
				}

				// Verificar si el nodo es la posición del jugador y pintarlo de un color diferente
				if (nodo.esVisitado()) {
					g.setColor(Color.BLUE); // Color del jugador
					g.fillOval(x + cellWidth / 4, y + cellHeight / 4, cellWidth / 2, cellHeight / 2);
				}

				if (model.getJugador().equals(laberinto.getNodoSalida())) {
					g.setColor(Color.RED);
					g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
					g.drawString("¡Ganaste!", 100, 100);
				}
			}
		}
	}
}

package una.ac.cr.laberinto.gui.laberinto;
import una.ac.cr.laberinto.modelo.Laberinto;
import una.ac.cr.laberinto.modelo.Nodo;

import javax.swing.*;
import java.awt.*;

public class LaberintoPanel extends JPanel {
	private Laberinto laberinto;
	private LaberintoModel model;
	private LaberintoController controller;
	private int cellWidth;
	private int cellHeight;
	private boolean zoomer;

	public LaberintoPanel(Laberinto laberinto) {
		this.laberinto = laberinto;
		this.model = new LaberintoModel(laberinto, this);
		this.controller = new LaberintoController(this.model, this);
		agregarKeyListener();
		agregarMouseWheelListener();
	}

	public void setLaberinto(Laberinto laberinto) {
		this.laberinto = laberinto;
		this.repaint();
	}

	private void agregarKeyListener() {
		// Agregar el KeyListener del controlador al LaberintoPanel
		this.addKeyListener(controller.getKeyAdapter());
		// Necesitas establecer el enfoque en el panel para que pueda recibir eventos de teclado
		setFocusable(true);
		requestFocusInWindow();
	}

	private void agregarMouseWheelListener() {
		// Para hacer zoom con la rueda del mouse
		this.addMouseWheelListener(controller.getMouseWheelListener());
	}
	@Override
	public void repaint() {	// casi que innecesario
		System.out.println("Repintando");
		// Vuelve a dibujar el panel
		super.repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		cellWidth = getWidth() / laberinto.getColumnas();
		cellHeight = getHeight() / laberinto.getFilas();

		if(zoomer){
			g2d.scale(controller.getZoomFactor(), controller.getZoomFactor());
			zoomer = false;
		}

		for (int i = 0; i < laberinto.getFilas(); i++) {
			for (int j = 0; j < laberinto.getColumnas(); j++) {
				Nodo nodo = laberinto.getNodo(i, j);
				int x = j * cellWidth;
				int y = i * cellHeight;

				// Dibujar celdas
				g2d.setColor(Color.WHITE);
				g2d.fillRect((int) (x), (int) (y), (int) (cellWidth), (int) (cellHeight));


				// Dibujar paredes con un grosor mayor
				g2d.setStroke(new BasicStroke(4)); // Grosor de la línea = 4 pixel
				// Dibujar paredes
				g2d.setColor(new Color(198, 89, 17));
				if (nodo.getArriba() == null) {
					g2d.drawLine(x, y, x + cellWidth, y);
				}
				if (nodo.getIzquierda() == null) {
					g2d.drawLine(x, y, x, y + cellHeight);
				}
				if (nodo.getDerecha() == null) {
					g2d.drawLine(x + cellWidth, y, x + cellWidth, y + cellHeight);
				}
				if (nodo.getAbajo() == null) {
					g2d.drawLine(x, y + cellHeight, x + cellWidth, y + cellHeight);
				}


				// Verificar si el nodo es la posición del jugador y pintarlo de un color diferente
				if (nodo.esVisitado()) {
					g2d.setColor(new Color(28, 83, 119)); // Color del jugador
					g2d.fillOval(x + cellWidth / 4, y + cellHeight / 4, cellWidth / 2, cellHeight / 2);
				}

				if (nodo.esCaminoIncorrecto()) {
					g2d.setColor(new Color(106, 106, 106)); // Color del jugador
					g2d.fillOval(x + cellWidth / 4, y + cellHeight / 4, cellWidth / 2, cellHeight / 2);
				}

				if (model.getJugador().equals(laberinto.getNodoSalida())) {
					g2d.setColor(new Color(184, 15, 10));
					g2d.setFont(new Font("TimesRoman", Font.PLAIN, 50));
					g2d.drawString("¡Ganaste!", 100, 100);
				}
				// Verificar si el nodo es la entrada o la salida y pintarlo de un color diferente
				if (i == laberinto.getNodoEntrada().getFila() && j == laberinto.getNodoEntrada().getColumna()) {
					g2d.setColor(new Color(89, 198, 17)); // Color de la entrada
					g2d.fillOval(x + cellWidth / 4, y + cellHeight / 4, cellWidth / 2, cellHeight / 2);
				} else if (i == laberinto.getNodoSalida().getFila() && j == laberinto.getNodoSalida().getColumna()) {
					g2d.setColor(new Color(184, 15, 10)); // Color de la salida
					g2d.fillOval(x + cellWidth / 4, y + cellHeight / 4, cellWidth / 2, cellHeight / 2);
				}
			}
		}
	}

	public LaberintoController getController() {
		return controller;
	}

	public void setZoomer(boolean b) {
		zoomer = b;
	}
}

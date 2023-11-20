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
		int modo = controller.getMetodoDibujo();
		if (modo == 0) {
			metodoDeDibujoParedes(g, modo);
		} else if (modo == 1) {
			metodoDeDibujoParedes(g, modo);
		} else if (modo == 2) {
			metodoDeDibujoCamino(g);
		}
		else {
			System.out.println("Modo de dibujo no válido");
		}
	}


	public void metodoDeDibujoParedes(Graphics g, int opcionDibujo) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		cellWidth = getWidth() / laberinto.getColumnas();
		cellHeight = getHeight() / laberinto.getFilas();

		if(zoomer){
			g2d.scale(controller.getZoomFactor(), controller.getZoomFactor());
			ajustarVistaZoom();
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
				x = j * cellWidth + cellWidth / 2;
				y = i * cellHeight + cellHeight / 2;
				if (nodo.esVisitado() || nodo.esCaminoIncorrecto()) {

					// Dibujar camino con líneas o círculos según la opción
					if (opcionDibujo == 0) {
						g2d.setColor(nodo.esVisitado() ? new Color(28, 83, 119) : new Color(106, 106, 106));
						g2d.setStroke(new BasicStroke(2));
						// Verificar y dibujar conexiones
						if (nodo.getArriba() != null) {
							g2d.drawLine(x, y, x, y - cellHeight);
						}
						if (nodo.getDerecha() != null) {
							g2d.drawLine(x, y, x + cellWidth, y);
						}
						if (nodo.getAbajo() != null) {
							g2d.drawLine(x, y, x, y + cellHeight);
						}
						if (nodo.getIzquierda() != null) {
							g2d.drawLine(x, y, x - cellWidth, y);
						}
					} else {
						g2d.setColor(nodo.esVisitado() ? new Color(28, 83, 119) : new Color(106, 106, 106));
						g2d.fillOval(x - cellWidth / 4, y - cellHeight / 4, cellWidth / 2, cellHeight / 2);
					}
				}

				if (model.getJugador().equals(laberinto.getNodoSalida())) {
					g2d.setColor(new Color(184, 15, 10));
					g2d.setFont(new Font("TimesRoman", Font.PLAIN, 50));
					g2d.drawString("¡Ganaste!", 100, 100);
				}

				// Verificar si el nodo es la entrada o la salida y pintarlo de un color diferente
				if (i == laberinto.getNodoEntrada().getFila() && j == laberinto.getNodoEntrada().getColumna()) {
					g2d.setColor(new Color(89, 198, 17)); // Color de la entrada
					g2d.fillOval(x - cellWidth / 4, y - cellHeight / 4, cellWidth / 2, cellHeight / 2);
				} else if (i == laberinto.getNodoSalida().getFila() && j == laberinto.getNodoSalida().getColumna()) {
					g2d.setColor(new Color(0, 255, 255)); // Color de la salida
					g2d.fillOval(x - cellWidth / 4, y - cellHeight / 4, cellWidth / 2, cellHeight / 2);
				}

			}
		}
	}

	public void metodoDeDibujoCamino(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		cellWidth = getWidth() / laberinto.getColumnas();
		cellHeight = getHeight() / laberinto.getFilas();

		if(zoomer){
			g2d.scale(controller.getZoomFactor(), controller.getZoomFactor());
			ajustarVistaZoom();
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

				x = j * cellWidth + cellWidth / 2;
				y = i * cellHeight + cellHeight / 2;

				// Dibujar camino
				g2d.setColor(new Color(128, 128, 128)); // Azul más brillante para el camino
				g2d.setStroke(new BasicStroke(2)); // Grosor de la línea = 2 pixel

				// Verificar y dibujar conexiones
				if (nodo.getArriba() != null) {
					g2d.drawLine(x, y, x, y - cellHeight);
				}
				if (nodo.getDerecha() != null) {
					g2d.drawLine(x, y, x + cellWidth, y);
				}
				if (nodo.getAbajo() != null) {
					g2d.drawLine(x, y, x, y + cellHeight);
				}
				if (nodo.getIzquierda() != null) {
					g2d.drawLine(x, y, x - cellWidth, y);
				}

				int radio = 10; // Tamaño del radio del círculo
				int bordeAncho = 2;

				g2d.setColor(new Color(0, 0, 0)); // Color del borde (negro en este caso)
				g2d.setStroke(new BasicStroke(bordeAncho)); // Grosor del borde
				g2d.drawOval(x - radio / 2 - bordeAncho / 2, y - radio / 2 - bordeAncho / 2, radio + bordeAncho, radio + bordeAncho);


				// Dibujar círculo en el centro de la celda
				g2d.setColor(new Color(255, 69, 0)); // Naranja para el círculo
				g2d.fillOval(x - radio / 2, y - radio / 2, radio, radio);

				// Verificar si el nodo es la posición del jugador y pintarlo de un color diferente
				if (nodo.esVisitado()) {
					g2d.setColor(new Color(0, 0, 255)); // Naranja más claro para el jugador
					g2d.fillOval(x - radio / 2, y - radio / 2, radio, radio);
				}

				if (nodo.esCaminoIncorrecto()) {
					g2d.setColor(new Color(128, 128, 128)); // Gris para el camino incorrecto
					g2d.fillOval(x - radio / 2, y - radio / 2, radio, radio);
				}

				if (model.getJugador().equals(laberinto.getNodoSalida())) {
					g2d.setColor(new Color(255, 0, 0)); // Rojo para el mensaje de "Ganaste"
					g2d.setFont(new Font("TimesRoman", Font.PLAIN, 50));
					g2d.drawString("¡Ganaste!", 100, 100);
				}

				// Verificar si el nodo es la entrada o la salida y pintarlo de un color diferente
				if (i == laberinto.getNodoEntrada().getFila() && j == laberinto.getNodoEntrada().getColumna()) {
					g2d.setColor(new Color(89, 198, 17)); // Verde para la entrada
					g2d.fillOval(x - radio / 2, y - radio / 2, radio, radio);
				} else if (i == laberinto.getNodoSalida().getFila() && j == laberinto.getNodoSalida().getColumna()) {
					g2d.setColor(new Color(0, 255, 255)); // Color de la salida
					g2d.fillOval(x - radio / 2, y - radio / 2, radio, radio);
				}
			}
		}
	}

	private void ajustarVistaZoom() {	// para agregar los scrollbars
//		 Ajustar el tamaño de la vista en función del factor de zoom
		Dimension currentSize = getSize();
		Dimension newSize = new Dimension((int) (getWidth() * controller.getZoomFactor()),
				(int) (getHeight() * controller.getZoomFactor()));

		if (!currentSize.equals(newSize)) {
			setPreferredSize(newSize);
			revalidate();
		}

		// Ajustar las barras de desplazamiento si es necesario
		if (getParent() instanceof JViewport) {
			JViewport viewport = (JViewport) getParent();
			Dimension extentSize = viewport.getExtentSize();
			Dimension viewSize = getSize();
			Point viewPosition = viewport.getViewPosition();

			int maxX = Math.max(0, newSize.width - extentSize.width);
			int maxY = Math.max(0, newSize.height - extentSize.height);

			// Ajustar la posición de la vista si es necesario
			if (viewPosition.x > maxX) {
				viewPosition.x = maxX;
			}

			if (viewPosition.y > maxY) {
				viewPosition.y = maxY;
			}

			viewport.setViewPosition(viewPosition);
			revalidate();
		}
	}


	public LaberintoController getController() {
		return controller;
	}

	public void setZoomer(boolean b) {
		zoomer = b;
	}

	public void setMetodoDibujo(int modo) {
		controller.setMetodoDibujo(modo);
		repaint();
	}
}

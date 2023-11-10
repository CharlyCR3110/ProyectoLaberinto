package una.ac.cr.laberinto.gui;

import una.ac.cr.laberinto.algoritmos.AlgoritmoSolucion;
import una.ac.cr.laberinto.modelo.Laberinto;
import una.ac.cr.laberinto.modelo.Nodo;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class LaberintoController {
	private LaberintoModel model;

	public LaberintoController(LaberintoModel model) {
		this.model = model;
	}

	public KeyAdapter getKeyAdapter() {
		return new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				moverJugador(e);
				// Vuelve a dibujar el panel después de que el usuario se mueva
				repaint();
			}
		};
	}

	private void moverJugador(KeyEvent keyEvent) {
		if (keyEvent.getKeyCode() == KeyEvent.VK_UP) {
			model.moverJugador("ARRIBA");
		} else if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
			model.moverJugador("ABAJO");
		} else if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
			model.moverJugador("IZQUIERDA");
		} else if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
			model.moverJugador("DERECHA");
		} else if (keyEvent.getKeyCode() == KeyEvent.VK_SPACE) {
			System.out.println("Resolviendo laberinto");
			if (model.getJugador() != model.getLaberinto().getNodoEntrada()) {
				System.out.println("No se puede resolver el laberinto si el jugador no está en la entrada");
				return;
			}
			model.getAlgoritmoSolucion().resolverLaberinto();
			model.setJugador(model.getLaberinto().getNodoSalida());
		} else if (keyEvent.getKeyCode() == 82) { // Reinicio del laberinto
			System.out.println("Reiniciando laberinto");
			model.getLaberinto().reiniciarLaberinto();
			model.setJugador(model.getLaberinto().getNodoEntrada());
		} else {
			System.out.println("Tecla no válida");
		}
	}

	private void repaint() {
		model.getPanel().repaint();
	}
}

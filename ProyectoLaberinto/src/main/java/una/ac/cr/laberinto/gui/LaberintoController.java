package una.ac.cr.laberinto.gui;

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
				model.moverJugador(e);
				// Vuelve a dibujar el panel después de que el usuario se mueva
				repaint();
			}
		};
	}

	private void repaint() {
		model.getPanel().repaint();
	}
}

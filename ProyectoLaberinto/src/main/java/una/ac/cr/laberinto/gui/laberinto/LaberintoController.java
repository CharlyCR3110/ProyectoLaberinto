package una.ac.cr.laberinto.gui.laberinto;

import una.ac.cr.laberinto.modelo.Laberinto;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseWheelListener;

public class LaberintoController {
	private LaberintoModel model;
	private LaberintoPanel view;
	public LaberintoController(LaberintoModel model, LaberintoPanel view) {
		this.model = model;
		this.view = view;
	}

	public double getZoomFactor() {
		return model.getZoomFactor();
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
			reiniciar();
		} else {
			System.out.println("Tecla no válida");
		}
	}


	public void reiniciar() {
		model.getLaberinto().reiniciarLaberinto();
		model.setJugador(model.getLaberinto().getNodoEntrada());
	}

	private void repaint() {
		view.repaint();
	}

	public void setLaberinto(Laberinto laberinto) {
		model.setLaberinto(laberinto);
		view.setLaberinto(laberinto);
	}

	public MouseWheelListener getMouseWheelListener() {
		return e -> {
			int notches = e.getWheelRotation();
			if (notches < 0) {
				// Zoom in
				setZoomFactor(1.1 * model.getZoomFactor());
				view.repaint();
			} else {
				// Zoom out
				setZoomFactor(0.9 * model.getZoomFactor());
				view.repaint();
			}
			repaint();
		};
	}

	public void setZoomFactor(double factor){
		if(factor < getZoomFactor()){
			model.setZoomFactor(getZoomFactor()/1.1);
		}
		else{
			model.setZoomFactor(factor);
		}
		view.setZoomer(true);
	}
}

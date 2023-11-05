package una.ac.cr.laberinto.gui;

import una.ac.cr.laberinto.modelo.Laberinto;
import una.ac.cr.laberinto.modelo.Nodo;

import java.awt.event.KeyEvent;

public class LaberintoModel {
	private Laberinto laberinto;
	private Nodo jugador; // Representa la posición del jugador en el laberinto
	private LaberintoPanel panel;
	public LaberintoModel(Laberinto laberinto, LaberintoPanel panel) {
		this.laberinto = laberinto;
		this.panel = panel;
		this.jugador = laberinto.getNodoEntrada(); // El jugador comienza en el nodo de entrada
	}

	public Nodo getJugador() {
		return jugador;
	}

	public void moverJugador(KeyEvent keyEvent) {
		if (keyEvent.getKeyCode() == KeyEvent.VK_UP) {
			if (jugador.getArriba() != null) {
				jugador.marcarVisitado();
				jugador = jugador.getArriba();
			} else {
				System.out.println("No se puede mover hacia arriba");
				return;
			}
		} else if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
			if (jugador.getAbajo() != null) {
				jugador.marcarVisitado();
				jugador = jugador.getAbajo();
			} else {
				System.out.println("No se puede mover hacia abajo");
				return;
			}
		} else if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
			if (jugador.getIzquierda() != null) {
				jugador.marcarVisitado();
				jugador = jugador.getIzquierda();
			} else {
				System.out.println("No se puede mover hacia la izquierda");
				return;
			}
		} else if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (jugador.getDerecha() != null) {
				jugador.marcarVisitado();
				jugador = jugador.getDerecha();
			} else {
				System.out.println("No se puede mover hacia la derecha");
				return;
			}
		} else {
			System.out.println("Tecla no válida");
			return;
		}
	}

	public LaberintoPanel getPanel() {
		return this.panel;
	}
}

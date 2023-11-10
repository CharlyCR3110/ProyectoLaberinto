package una.ac.cr.laberinto.gui;

import una.ac.cr.laberinto.algoritmos.AlgoritmoSolucion;
import una.ac.cr.laberinto.modelo.Laberinto;
import una.ac.cr.laberinto.modelo.Nodo;

import java.awt.event.KeyEvent;
import java.util.List;

public class LaberintoModel {
	private Laberinto laberinto;
	private AlgoritmoSolucion algoritmoSolucion;
	private Nodo jugador; // Representa la posición del jugador en el laberinto
	private LaberintoPanel panel;
	public LaberintoModel(Laberinto laberinto, LaberintoPanel panel) {
		this.laberinto = laberinto;
		this.panel = panel;
		this.algoritmoSolucion = new AlgoritmoSolucion(laberinto);
		this.jugador = laberinto.getNodoEntrada(); // El jugador comienza en el nodo de entrada
	}

	public Nodo getJugador() {
		return jugador;
	}

	public void moverJugador(KeyEvent keyEvent) {
		if (keyEvent.getKeyCode() == KeyEvent.VK_UP) {
			if (jugador.getArriba() != null) {
				jugador = jugador.getArriba();
				jugador.marcarVisitado();
			} else {
				System.out.println("No se puede mover hacia arriba");
				return;
			}
		} else if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
			if (jugador.getAbajo() != null) {
				jugador = jugador.getAbajo();
				jugador.marcarVisitado();
			} else {
				System.out.println("No se puede mover hacia abajo");
				return;
			}
		} else if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
			if (jugador.getIzquierda() != null) {
				jugador = jugador.getIzquierda();
				jugador.marcarVisitado();
			} else {
				System.out.println("No se puede mover hacia la izquierda");
				return;
			}
		} else if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (jugador.getDerecha() != null) {
				jugador = jugador.getDerecha();
				jugador.marcarVisitado();
			} else {
				System.out.println("No se puede mover hacia la derecha");
				return;
			}
		} else if(keyEvent.getKeyCode() == KeyEvent.VK_SPACE) {
			System.out.println("Resolviendo laberinto");
			algoritmoSolucion.resolverLaberinto();
			jugador = laberinto.getNodoSalida();
			return;
		} else if(keyEvent.getKeyCode() == 82) {	// Reinicio del laberinto
			System.out.println("Reiniciando laberinto");
			laberinto.reiniciarLaberinto();
			jugador = laberinto.getNodoEntrada();
			return;

		}else {
			System.out.println("Tecla no válida");
			return;
		}
	}

	public LaberintoPanel getPanel() {
		return this.panel;
	}
}

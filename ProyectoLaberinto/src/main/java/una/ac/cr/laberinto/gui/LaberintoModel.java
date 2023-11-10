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

	public LaberintoPanel getPanel() {
		return this.panel;
	}

	public Laberinto getLaberinto() {
		return laberinto;
	}

	public AlgoritmoSolucion getAlgoritmoSolucion() {
		return algoritmoSolucion;
	}

	public void moverJugador(String direccion) {
		if (direccion == "ARRIBA") {
			if (jugador.getArriba() != null) {
				jugador = jugador.getArriba();
				jugador.marcarVisitado();
			}
		} else if (direccion == "DERECHA") {
			if (jugador.getDerecha() != null) {
				jugador = jugador.getDerecha();
				jugador.marcarVisitado();
			}
		} else if (direccion == "ABAJO") {
			if (jugador.getAbajo() != null) {
				jugador = jugador.getAbajo();
				jugador.marcarVisitado();
			}
		} else if (direccion == "IZQUIERDA") {
			if (jugador.getIzquierda() != null) {
				jugador = jugador.getIzquierda();
				jugador.marcarVisitado();
			}
		} else {
			System.out.println("Dirección no válida");
		}
	}

	public void setJugador(Nodo nodo) {
		this.jugador = nodo;
	}
}

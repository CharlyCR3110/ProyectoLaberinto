package una.ac.cr.laberinto.gui.laberinto;

import una.ac.cr.laberinto.algoritmos.AlgoritmoSolucion;
import una.ac.cr.laberinto.modelo.Laberinto;
import una.ac.cr.laberinto.modelo.Nodo;

import java.util.Objects;

public class LaberintoModel {
	private Laberinto laberinto;
	private AlgoritmoSolucion algoritmoSolucion;
	private Nodo jugador; // Representa la posición del jugador en el laberinto
	public LaberintoModel(Laberinto laberinto, LaberintoPanel panel) {
		this.laberinto = laberinto;
		this.algoritmoSolucion = new AlgoritmoSolucion(laberinto);
		this.jugador = laberinto.getNodoEntrada(); // El jugador comienza en el nodo de entrada
	}

	public Nodo getJugador() {
		return jugador;
	}
	public Laberinto getLaberinto() {
		return laberinto;
	}
	public void setLaberinto(Laberinto laberinto) {
		this.laberinto = laberinto;
	}

	public AlgoritmoSolucion getAlgoritmoSolucion() {
		return algoritmoSolucion;
	}

	public void moverJugador(String direccion) {
		if (Objects.equals(direccion, "ARRIBA")) {	// objeto.equals("string") es más seguro que ==, lo mismo pero el IDE mandaba warning
			if (jugador.getArriba() != null) {
				jugador = jugador.getArriba();
				jugador.marcarVisitado();
			}
		} else if (Objects.equals(direccion, "DERECHA")) {
			if (jugador.getDerecha() != null) {
				jugador = jugador.getDerecha();
				jugador.marcarVisitado();
			}
		} else if (Objects.equals(direccion, "ABAJO")) {
			if (jugador.getAbajo() != null) {
				jugador = jugador.getAbajo();
				jugador.marcarVisitado();
			}
		} else if (Objects.equals(direccion, "IZQUIERDA")) {
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

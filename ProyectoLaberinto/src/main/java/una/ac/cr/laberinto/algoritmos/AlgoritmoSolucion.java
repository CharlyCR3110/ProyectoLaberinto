package una.ac.cr.laberinto.algoritmos;

import una.ac.cr.laberinto.modelo.Laberinto;
import una.ac.cr.laberinto.modelo.Nodo;

import java.util.ArrayList;
import java.util.List;

public class AlgoritmoSolucion {
	Laberinto laberinto;

	List<Nodo> pasos = new ArrayList<>(); // Lista para guardar los pasos que se van dando y poder mostrarlos en la GUI

	public AlgoritmoSolucion(Laberinto laberinto) {
		this.laberinto = laberinto;
	}

	public void resolverLaberinto() {
		resolverLaberintoRecursivo(laberinto.getNodoEntrada());
	}
	private boolean resolverLaberintoRecursivo(Nodo nodoActual) {
		// Si llegamos a la salida, hemos encontrado una solución
		if (nodoActual.equals(laberinto.getNodoSalida())) {
			return true;
		}

		// Marcar el nodo actual como visitado
		nodoActual.marcarVisitado();

		// Intentar moverse en todas las direcciones posibles
		if (nodoActual.getArriba() != null && !nodoActual.getArriba().esVisitado() && resolverLaberintoRecursivo(nodoActual.getArriba())) {
			return true;
		}
		if (nodoActual.getAbajo() != null && !nodoActual.getAbajo().esVisitado() && resolverLaberintoRecursivo(nodoActual.getAbajo())) {
			return true;
		}
		if (nodoActual.getIzquierda() != null && !nodoActual.getIzquierda().esVisitado() && resolverLaberintoRecursivo(nodoActual.getIzquierda())) {
			return true;
		}
		if (nodoActual.getDerecha() != null && !nodoActual.getDerecha().esVisitado() && resolverLaberintoRecursivo(nodoActual.getDerecha())) {
			return true;
		}

		// Si no encontramos una solución desde este nodo, retrocedemos
		nodoActual.marcarNoVisitado();
		nodoActual.marcarCaminoIncorrecto();
		return false;
	}
}

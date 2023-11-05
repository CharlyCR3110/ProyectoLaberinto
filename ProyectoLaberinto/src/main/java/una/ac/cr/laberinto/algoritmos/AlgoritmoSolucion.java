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
			pasos.add(nodoActual);
			return true;
		}

		// Marcar el nodo actual como visitado
		nodoActual.marcarVisitado();

		// Intentar moverse en todas las direcciones posibles
		if (nodoActual.getArriba() != null && !nodoActual.getArriba().esVisitado() && resolverLaberintoRecursivo(nodoActual.getArriba())) {
			pasos.add(nodoActual);
			return true;
		}
		if (nodoActual.getAbajo() != null && !nodoActual.getAbajo().esVisitado() && resolverLaberintoRecursivo(nodoActual.getAbajo())) {
			pasos.add(nodoActual);
			return true;
		}
		if (nodoActual.getIzquierda() != null && !nodoActual.getIzquierda().esVisitado() && resolverLaberintoRecursivo(nodoActual.getIzquierda())) {
			pasos.add(nodoActual);
			return true;
		}
		if (nodoActual.getDerecha() != null && !nodoActual.getDerecha().esVisitado() && resolverLaberintoRecursivo(nodoActual.getDerecha())) {
			pasos.add(nodoActual);
			return true;
		}

		// Si no encontramos una solución desde este nodo, retrocedemos
		nodoActual.marcarNoVisitado();
		nodoActual.marcarCaminoIncorrecto();
		return false;
	}


	public List<Nodo> getPasos() {
		// se devuelve la lista de pasos al revés para que se muestre en orden
		List<Nodo> pasosAlReves = new ArrayList<>();
		for (int i = pasos.size() - 1; i >= 0; i--) {
			pasosAlReves.add(pasos.get(i));
		}
		return pasosAlReves;
	}
}

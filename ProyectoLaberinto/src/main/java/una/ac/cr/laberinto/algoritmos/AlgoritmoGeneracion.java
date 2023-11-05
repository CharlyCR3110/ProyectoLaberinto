package una.ac.cr.laberinto.algoritmos;

import una.ac.cr.laberinto.modelo.Laberinto;
import una.ac.cr.laberinto.modelo.Nodo;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class AlgoritmoGeneracion {
	private int filas;
	private int columnas;
	private Nodo[][] nodos;
	private Nodo nodoEntrada;
	private Nodo nodoSalida;

	public AlgoritmoGeneracion(int filas, int columnas) {
		this.filas = filas;
		this.columnas = columnas;
		this.nodos = new Nodo[filas][columnas];
		inicializarNodos();
		generarLaberinto();
	}

	private void inicializarNodos() {
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				nodos[i][j] = new Nodo(i, j);
			}
		}
	}

	private void generarLaberinto() {
		carvePassagesFrom(0, 0);	// Comenzar en la esquina superior izquierda
		nodoEntrada = nodos[0][0];
		// logica para marcar el nodo de salida
		nodoSalida = nodos[filas - 1][columnas - 1];
	}

	private void carvePassagesFrom(int fila, int columna) {
		List<Integer> direcciones = new ArrayList<>(List.of(1, 2, 3, 4));
		Collections.shuffle(direcciones);

		// Marcar nodo como visitado
		nodos[fila][columna].marcarVisitado();


		for (Integer direccion : direcciones) {
			int nuevaFila = fila;
			int nuevaColumna = columna;

			switch (direccion) {
				case 1: // Norte
					nuevaFila = fila - 1;
					break;
				case 2: // Sur
					nuevaFila = fila + 1;
					break;
				case 3: // Este
					nuevaColumna = columna + 1;
					break;
				case 4: // Oeste
					nuevaColumna = columna - 1;
					break;
			}

			if (nuevaFila >= 0 && nuevaFila < filas && nuevaColumna >= 0 && nuevaColumna < columnas
					&& nodos[nuevaFila][nuevaColumna] != null && !nodos[nuevaFila][nuevaColumna].esVisitado()) {
				if (direccion == 1) {
					nodos[fila][columna].setArriba(nodos[nuevaFila][nuevaColumna]);
					nodos[nuevaFila][nuevaColumna].setAbajo(nodos[fila][columna]);
				} else if (direccion == 2) {
					nodos[fila][columna].setAbajo(nodos[nuevaFila][nuevaColumna]);
					nodos[nuevaFila][nuevaColumna].setArriba(nodos[fila][columna]);
				} else if (direccion == 3) {
					nodos[fila][columna].setDerecha(nodos[nuevaFila][nuevaColumna]);
					nodos[nuevaFila][nuevaColumna].setIzquierda(nodos[fila][columna]);
				} else if (direccion == 4) {
					nodos[fila][columna].setIzquierda(nodos[nuevaFila][nuevaColumna]);
					nodos[nuevaFila][nuevaColumna].setDerecha(nodos[fila][columna]);
				}

				carvePassagesFrom(nuevaFila, nuevaColumna);
			}
		}
	}

	public Nodo nodoAleatorio() {
		int fila = (int) (Math.random() * filas);
		int columna = (int) (Math.random() * columnas);
		return nodos[fila][columna];
	}
	public Laberinto getLaberinto() {
		Laberinto laberinto = new Laberinto(filas, columnas);
		reiniciarVisitas();	// se reinician las visitas para que el algoritmo de resolución funcione correctamente
		laberinto.setNodos(nodos);
		laberinto.setNodoEntrada(nodoAleatorio());
		laberinto.setNodoSalida(nodoAleatorio());
		return laberinto;
	}

	public void reiniciarVisitas() {
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas ; j++) {
				nodos[i][j].marcarNoVisitado();
			}
		}
	}
}

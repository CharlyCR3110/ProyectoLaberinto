package una.ac.cr.laberinto.modelo;

public class Nodo {
	private int fila;
	private int columna;
	private boolean visitado;

	private Nodo arriba;
	private Nodo derecha;
	private Nodo abajo;
	private Nodo izquierda;

	public Nodo(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
		this.visitado = false;
	}

	public int getFila() {
		return fila;
	}

	public int getColumna() {
		return columna;
	}

	public boolean esVisitado() {
		return visitado;
	}

	public void marcarVisitado() {
		visitado = true;
	}

	@Override
	public String toString() {
		return "(" + fila + ", " + columna + ")";
	}

	public void setDerecha(Nodo nodo) {
		this.derecha = nodo;
	}

	public void setIzquierda(Nodo nodo) {
		this.izquierda = nodo;
	}

	public void setArriba(Nodo nodo) {
		this.arriba = nodo;
	}

	public void setAbajo(Nodo nodo) {
		this.abajo = nodo;
	}

	public Nodo getArriba() {
		return arriba;
	}

	public Nodo getDerecha() {
		return derecha;
	}

	public Nodo getAbajo() {
		return abajo;
	}

	public Nodo getIzquierda() {
		return izquierda;
	}

}


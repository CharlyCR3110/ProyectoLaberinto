package una.ac.cr.laberinto.modelo;

public class Nodo {
	private int fila;
	private int columna;
	private boolean visitado;

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
}


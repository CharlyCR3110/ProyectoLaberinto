package una.ac.cr.laberinto.modelo;

public class Nodo {
	private int fila;
	private int columna;
	private boolean visitado;
	private boolean caminoIncorrecto;

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
	public boolean esCaminoIncorrecto() {
		return caminoIncorrecto;
	}

	public void marcarVisitado() {
		visitado = true;
	}

	public void marcarNoVisitado() {
		visitado = false;
	}

	public void marcarCaminoIncorrecto() {
		caminoIncorrecto = true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Nodo: ");
		sb.append(fila);
		sb.append(",");
		sb.append(columna);
		sb.append(" Visitado: ");
		sb.append(visitado);
		sb.append(" Direcciones posibles: ");
		if (arriba != null) {
			sb.append("Arriba -- ");
		}
		if (derecha != null) {
			sb.append("Derecha -- ");
		}
		if (abajo != null) {
			sb.append("Abajo -- ");
		}
		if (izquierda != null) {
			sb.append("Izquierda -- ");
		}
		sb.append("\n");
		return sb.toString();
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

	public boolean isVacio() {
		return arriba == null && derecha == null && abajo == null && izquierda == null;
	}

	public void setDefault() {
		this.visitado = false;
		this.caminoIncorrecto = false;
	}

	public boolean esPared() {
		return arriba == null && derecha == null && abajo == null && izquierda == null;
	}
}


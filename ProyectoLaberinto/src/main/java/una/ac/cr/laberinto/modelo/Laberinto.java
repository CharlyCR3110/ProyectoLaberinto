package una.ac.cr.laberinto.modelo;

public class Laberinto {
	private int filas;
	private int columnas;
	private Nodo[][] nodos;
	private Nodo nodoEntrada;
	private Nodo nodoSalida;

	public static String[] DESCRIPCION_MODO = {"Guías", "Bloque", "Línea"};
	// int[] MODOS = {MODO_GUIA, MODO_BLOQUE, MODO_LINEA};
	int MODO_BLOQUE = 0;
	int MODO_GUIA = -1;
	int MODO_LINEA = 1;
	public Laberinto(int filas, int columnas) {
		this.filas = filas;
		this.columnas = columnas;
		this.nodos = new Nodo[filas][columnas];
		inicializarNodos();
	}

	private void inicializarNodos() {
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				nodos[i][j] = new Nodo(i, j);
			}
		}
	}
	public int getFilas() {
		return filas;
	}

	public int getColumnas() {
		return columnas;
	}

	public Nodo getNodo(int fila, int columna) {
		return nodos[fila][columna];
	}

	public void setNodos(Nodo[][] nodos) {
		this.nodos = nodos;
	}
	public Nodo getNodoEntrada() {
		return nodoEntrada;
	}

	public void setNodoEntrada(Nodo nodoEntrada) {
		this.nodoEntrada = nodoEntrada;
	}

	public Nodo getNodoSalida() {
		return nodoSalida;
	}

	public void setNodoSalida(Nodo nodoSalida) {
		this.nodoSalida = nodoSalida;
	}

	public void mostrarNodos() {
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas ; j++) {
				System.out.print(nodos[i][j].toString());
			}
			System.out.println();
		}
	}

	public void reiniciarLaberinto() {
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas ; j++) {
				nodos[i][j].setDefault();
			}
			System.out.println();
		}
	}
}

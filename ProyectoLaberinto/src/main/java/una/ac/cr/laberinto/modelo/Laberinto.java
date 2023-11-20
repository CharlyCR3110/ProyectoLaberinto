package una.ac.cr.laberinto.modelo;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import una.ac.cr.laberinto.utils.LocalDateAdapter;

import java.time.LocalDate;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Laberinto {
	private int filas;
	private int columnas;

	@XmlElement(name = "nodos")
	private Nodo[][] nodos;
	private Nodo nodoEntrada;
	private Nodo nodoSalida;

	public static String[] DESCRIPCION_MODO = {"Original", "Paredes y Circulos","Caminos"};
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	private LocalDate fechaDeCreacion;
	@XmlElement(name = "nombre")
	private String nombreLaberinto;

	public Laberinto(int filas, int columnas) {
		this.filas = filas;
		this.columnas = columnas;
		this.nodos = new Nodo[filas][columnas];
		inicializarNodos();
	}

	public Laberinto() {
		this(10, 10);
	}

	public Laberinto(int filas, int columnas, LocalDate fechaDeCreacion) {
		this.filas = filas;
		this.columnas = columnas;
		this.fechaDeCreacion = fechaDeCreacion;
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
		}
	}

	public LocalDate getFechaDeCreacion() {
		return this.fechaDeCreacion;
	}

	public void setNombre(String nombre) {
		this.nombreLaberinto = nombre;
	}

	public String getNombre() {
		return nombreLaberinto;
	}
}

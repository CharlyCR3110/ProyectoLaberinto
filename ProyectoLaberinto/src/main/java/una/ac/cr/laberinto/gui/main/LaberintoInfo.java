package una.ac.cr.laberinto.gui.main;

import una.ac.cr.laberinto.modelo.Laberinto;

import java.time.LocalDate;

public class LaberintoInfo {
	private String nombre;
	private LocalDate fechaDeCreacion;
	private Laberinto laberinto;

	public LaberintoInfo(Laberinto laberinto) {
		this.nombre = laberinto.getNombre();
		this.fechaDeCreacion = laberinto.getFechaDeCreacion();
		this.laberinto = laberinto;
	}

	public String getNombre() {
		return nombre;
	}

	public Laberinto getLaberinto() {
		return laberinto;
	}

	public LocalDate getFechaDeCreacion() {
		return fechaDeCreacion;
	}
}

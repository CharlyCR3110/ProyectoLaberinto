package una.ac.cr.laberinto.gui.main;

import una.ac.cr.laberinto.gui.laberinto.LaberintoFrame;
import una.ac.cr.laberinto.modelo.Laberinto;

import java.time.LocalDate;

public class LaberintoInfo {
	private String nombre;
	private LocalDate fechaDeCreacion;
	private Laberinto laberinto;
	private LaberintoFrame laberintoFrame;

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


	public void setLaberintoFrame(LaberintoFrame laberintoFrame) {
		this.laberintoFrame = laberintoFrame;
	}

	public LaberintoFrame getLaberintoFrame() {
		return laberintoFrame;
	}
}

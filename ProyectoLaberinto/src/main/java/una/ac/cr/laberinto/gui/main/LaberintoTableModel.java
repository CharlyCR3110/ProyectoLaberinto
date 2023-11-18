package una.ac.cr.laberinto.gui.main;

import una.ac.cr.laberinto.modelo.Laberinto;

import javax.swing.table.AbstractTableModel;
import java.util.List;

// Modelo de la tabla
public class LaberintoTableModel extends AbstractTableModel {
	private List<LaberintoInfo> laberintos;
	private String[] columnNames = {"Nombre", "Fecha de Creación", "Tamaño (Filas x Columnas)"};

	public void setLaberintos(List<LaberintoInfo> laberintos) {
		this.laberintos = laberintos;
	}

	@Override
	public int getRowCount() {
		return laberintos != null ? laberintos.size() : 0;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		LaberintoInfo laberintoInfo = laberintos.get(rowIndex);
		switch (columnIndex) {
			case 0:
				return laberintoInfo.getNombre();
			case 1:
				return laberintoInfo.getFechaDeCreacion();
			case 2:
				Laberinto laberinto = laberintoInfo.getLaberinto();
				return laberinto.getFilas() + " x " + laberinto.getColumnas();
			default:
				return null;
		}
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}
}
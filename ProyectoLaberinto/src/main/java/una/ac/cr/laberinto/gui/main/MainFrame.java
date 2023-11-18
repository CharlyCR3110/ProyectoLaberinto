package una.ac.cr.laberinto.gui.main;

import una.ac.cr.laberinto.algoritmos.AlgoritmoGeneracion;
import una.ac.cr.laberinto.gui.laberinto.LaberintoFrame;
import una.ac.cr.laberinto.modelo.Laberinto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class MainFrame extends JFrame {

	private MainFrameController controller;

	// lista para la información de los laberintos abiertos
	private List<LaberintoInfo> laberintosAbiertos = new ArrayList<>();

	// Modelo de la tabla
	private LaberintoTableModel laberintoTableModel = new LaberintoTableModel();

	public MainFrame(MainFrameController controller) {
		this.controller = controller;
		initializeUI();
	}

	private void initializeUI() {
		setTitle("Laberinto App");
		setIconImage(new ImageIcon("src/main/resources/images/main_view_icon.jpeg").getImage());
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel mainPanel = new JPanel(new BorderLayout());

		JButton nuevoLaberintoButton = createNuevoLaberintoButton();
		JButton recuperarLaberintoButton = createRecuperarLaberintoButton();

		JPanel buttonPanel = createButtonPanel(nuevoLaberintoButton, recuperarLaberintoButton);

		// Crear la tabla y agregarla al panel principal
		JTable laberintoTable = new JTable(laberintoTableModel);
		JScrollPane tableScrollPane = new JScrollPane(laberintoTable);

		laberintoTable.getSelectionModel().addListSelectionListener(e -> {
			int selectedRow = laberintoTable.getSelectedRow();
			if (selectedRow >= 0) {
				LaberintoInfo selectedLaberinto = laberintosAbiertos.get(selectedRow);
				if (selectedLaberinto != null) {
					selectedLaberinto.getLaberintoFrame().toFront();
				}
			}
		});

		mainPanel.add(tableScrollPane, BorderLayout.CENTER);	// Agregar la tabla al panel principal
		mainPanel.add(buttonPanel, BorderLayout.NORTH);	// Agregar el panel de botones al panel principal

		add(mainPanel);
	}

	private JButton createNuevoLaberintoButton() {
		JButton nuevoLaberintoButton = new JButton("Nuevo Laberinto");
		nuevoLaberintoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleNuevoLaberintoButtonClick();
			}
		});
		return nuevoLaberintoButton;
	}

	private JButton createRecuperarLaberintoButton() {
		JButton recuperarLaberintoButton = new JButton("Recuperar Laberinto");
		recuperarLaberintoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleRecuperarLaberintoButtonClick();
			}
		});
		return recuperarLaberintoButton;
	}

	private JPanel createButtonPanel(JButton nuevoLaberintoButton, JButton recuperarLaberintoButton) {
		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(nuevoLaberintoButton);
		buttonPanel.add(recuperarLaberintoButton);
		return buttonPanel;
	}

	private JPanel createDimensionPanel() {
		JPanel dimensionPanel = new JPanel(new GridLayout(6, 3));
		dimensionPanel.add(new JLabel("Nombre:"));
		dimensionPanel.add(new JTextField());
		dimensionPanel.add(new JLabel("Filas:"));
		dimensionPanel.add(new JTextField());
		dimensionPanel.add(new JLabel("Columnas:"));
		dimensionPanel.add(new JTextField());
		return dimensionPanel;
	}

	private boolean isValidDimensions(int filas, int columnas) {
		return filas >= 4 && columnas >= 4;
	}

	private Laberinto createLaberinto(String nombre, int filas, int columnas) {
		AlgoritmoGeneracion algoritmoGeneracion = new AlgoritmoGeneracion(filas, columnas);
		Laberinto laberinto = algoritmoGeneracion.getLaberinto();
		laberinto.setNombre(nombre);
		return laberinto;
	}

	private void showLaberintoFrame(Laberinto laberinto) {
		LaberintoFrame laberintoFrame = new LaberintoFrame(laberinto, this.controller);
		laberintoFrame.setVisible(true);
		LaberintoInfo laberintoInfo = new LaberintoInfo(laberinto);
		laberintoInfo.setLaberintoFrame(laberintoFrame);
		laberintosAbiertos.add(laberintoInfo);
		laberintoTableModel.setLaberintos(laberintosAbiertos);
		laberintoTableModel.fireTableDataChanged();
	}

	private void handleNuevoLaberintoButtonClick() {
		JPanel dimensionPanel = createDimensionPanel();

		int result = JOptionPane.showConfirmDialog(MainFrame.this, dimensionPanel, "Nuevo Laberinto",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		if (result == JOptionPane.OK_OPTION) {
			try {
				JTextField nombreField = (JTextField) dimensionPanel.getComponent(1);
				JTextField filasField = (JTextField) dimensionPanel.getComponent(3);
				JTextField columnasField = (JTextField) dimensionPanel.getComponent(5);

				int filas = Integer.parseInt(filasField.getText().trim());
				int columnas = Integer.parseInt(columnasField.getText().trim());

				if (!isValidDimensions(filas, columnas)) {
					JOptionPane.showMessageDialog(MainFrame.this, "Por favor, introduce números mayores o iguales a 4.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				String nombre = nombreField.getText().trim();
				Laberinto laberinto = createLaberinto(nombre, filas, columnas);

				if (this.controller.guardar(nombre, laberinto)) {
					JOptionPane.showMessageDialog(MainFrame.this, "Laberinto creado y guardado con éxito.", "Información", JOptionPane.INFORMATION_MESSAGE);
					showLaberintoFrame(laberinto);
				} else {
					JOptionPane.showMessageDialog(MainFrame.this, "Error al crear y guardar el laberinto.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(MainFrame.this, "Por favor, introduce números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void handleRecuperarLaberintoButtonClick() {
		Laberinto laberinto = this.controller.recuperar();
		if (laberinto != null) {
			LaberintoFrame laberintoFrame = new LaberintoFrame(laberinto, this.controller);
			laberintoFrame.setVisible(true);

			System.out.println(laberinto.getNombre());
			// Agregar información del laberinto a la lista
			LaberintoInfo laberintoInfo = new LaberintoInfo(laberinto);
			// Setea la referencia a la ventana del laberinto en LaberintoInfo
			laberintoInfo.setLaberintoFrame(laberintoFrame);

			laberintosAbiertos.add(laberintoInfo);
			// Actualizar el modelo de la tabla
			laberintoTableModel.setLaberintos(laberintosAbiertos);
			laberintoTableModel.fireTableDataChanged();
		} else {
			JOptionPane.showMessageDialog(this, "No se recuperó ningún laberinto", "Informacion", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void removeLaberintoInfo(LaberintoInfo laberintoInfo) {
		// primer elemento cuyo nombre sea igual al nombre del laberinto
		Optional<LaberintoInfo> laberintoToRemove = laberintosAbiertos.stream()
				.filter(l -> l.getNombre().equals(laberintoInfo.getNombre()) && l.getFechaDeCreacion().equals(laberintoInfo.getFechaDeCreacion()))
				.findFirst();

		// si encuentra el laberinto, lo elimina de la lista y actualiza el modelo de la tabla
		laberintoToRemove.ifPresent(l -> {
			laberintosAbiertos.remove(l);
			laberintoTableModel.setLaberintos(laberintosAbiertos);
			laberintoTableModel.fireTableDataChanged();
		});
	}

}

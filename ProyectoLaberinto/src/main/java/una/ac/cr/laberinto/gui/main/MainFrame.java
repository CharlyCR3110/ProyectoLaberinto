package una.ac.cr.laberinto.gui.main;

import una.ac.cr.laberinto.algoritmos.AlgoritmoGeneracion;
import una.ac.cr.laberinto.gui.archivo.FileChooserFrame;
import una.ac.cr.laberinto.gui.laberinto.LaberintoFrame;
import una.ac.cr.laberinto.modelo.Laberinto;
import una.ac.cr.laberinto.utils.JAXBUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

	private MainFrameController controller;

	public MainFrame(MainFrameController controller) {
		this.controller = controller;
		initializeUI();
	}

	private void initializeUI() {
		setTitle("Laberinto App");
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel mainPanel = new JPanel(new BorderLayout());

		JButton nuevoLaberintoButton = createNuevoLaberintoButton();
		JButton recuperarLaberintoButton = createRecuperarLaberintoButton();

		JPanel buttonPanel = createButtonPanel(nuevoLaberintoButton, recuperarLaberintoButton);

		mainPanel.add(buttonPanel, BorderLayout.NORTH);

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

	private void handleNuevoLaberintoButtonClick() {
		JPanel dimensionPanel = new JPanel(new GridLayout(6, 3));
		JLabel nombreLabel = new JLabel("Nombre:");
		JLabel filasLabel = new JLabel("Filas:");
		JLabel columnasLabel = new JLabel("Columnas:");
		JTextField nombreField = new JTextField();
		JTextField filasField = new JTextField();
		JTextField columnasField = new JTextField();

		dimensionPanel.add(nombreLabel);
		dimensionPanel.add(nombreField);

		dimensionPanel.add(filasLabel);
		dimensionPanel.add(filasField);

		dimensionPanel.add(columnasLabel);
		dimensionPanel.add(columnasField);

		// Muestra el cuadro de dialogo para obtener las dimensiones del laberinto
		int result = JOptionPane.showConfirmDialog(MainFrame.this, dimensionPanel, "Nuevo Laberinto",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		// Revisa el resultado del cuadro de diálogo y crea un nuevo laberinto (si es posible)
		if (result == JOptionPane.OK_OPTION) {
			try {
				int filas = Integer.parseInt(filasField.getText().trim());
				int columnas = Integer.parseInt(columnasField.getText().trim());

				if (filas < 4 || columnas < 4) {
					JOptionPane.showMessageDialog(MainFrame.this, "Por favor, introduce números mayores o iguales a 4.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				AlgoritmoGeneracion algoritmoGeneracion = new AlgoritmoGeneracion(filas, columnas);
				Laberinto laberinto = algoritmoGeneracion.getLaberinto();

				if ( this.controller.guardar(nombreField.getText().trim(), laberinto) ) {
					JOptionPane.showMessageDialog(MainFrame.this, "Laberinto creado y guardado con éxito.", "Información", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(MainFrame.this, "Error al crear y guardar el laberinto.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				// Crea y muestra la nueva ventana del laberinto
				LaberintoFrame laberintoFrame = new LaberintoFrame(laberinto);
				laberintoFrame.setVisible(true);
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(MainFrame.this, "Por favor, introduce números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void handleRecuperarLaberintoButtonClick() {
		Laberinto laberinto = this.controller.recuperar();
		if (laberinto != null) {
			LaberintoFrame laberintoFrame = new LaberintoFrame(laberinto);
			laberintoFrame.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(this, "No se recuperó ningún laberinto", "Informacion", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}

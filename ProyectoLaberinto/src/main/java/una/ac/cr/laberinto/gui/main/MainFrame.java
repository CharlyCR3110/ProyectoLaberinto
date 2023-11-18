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


public class MainFrame extends JFrame {
	public MainFrame() {
		// Configura la ventana principal
		setTitle("Laberinto App");
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Crea un panel para contener los componentes
		JPanel mainPanel = new JPanel(new BorderLayout());

		// Crea un botón para generar un nuevo laberinto
		JButton nuevoLaberintoButton = new JButton("Nuevo Laberinto");
		nuevoLaberintoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel dimensionPanel = new JPanel(new GridLayout(2, 2));
				JLabel filasLabel = new JLabel("Filas:");
				JLabel columnasLabel = new JLabel("Columnas:");
				JTextField filasField = new JTextField();
				JTextField columnasField = new JTextField();

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

						// Crea y muestra la nueva ventana del laberinto
						LaberintoFrame laberintoFrame = new LaberintoFrame(laberinto);
						laberintoFrame.setVisible(true);
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(MainFrame.this, "Por favor, introduce números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		// Crea un botón para recuperar un laberinto desde un archivo
		JButton recuperarLaberintoButton = new JButton("Recuperar Laberinto");
		recuperarLaberintoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Se llama al método recuperar() para recuperar el laberinto desde un XML
				Laberinto laberinto = recuperar();
				if (laberinto != null) {
					// Crea y muestra la nueva ventana del laberinto
					LaberintoFrame laberintoFrame = new LaberintoFrame(laberinto);
					laberintoFrame.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(MainFrame.this, "No se recuperó ningún laberinto", "Informacion", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		// Agrega los botones al panel principal
		mainPanel.add(nuevoLaberintoButton, BorderLayout.EAST);
		mainPanel.add(recuperarLaberintoButton, BorderLayout.WEST);

		// Agrega el panel principal a la ventana
		add(mainPanel);
	}

	private Laberinto recuperar() {
		FileChooserFrame fileChooserFrame = new FileChooserFrame();
		String ruta = fileChooserFrame.showFileChooser();
		try {
			if (ruta != null) {
				Laberinto laberinto = JAXBUtil.cargarLaberinto(ruta);
				return laberinto;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al cargar el laberinto");
		}
		return null;
	}
}
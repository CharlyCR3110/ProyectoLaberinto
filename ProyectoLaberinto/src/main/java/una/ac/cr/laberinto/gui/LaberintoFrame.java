package una.ac.cr.laberinto.gui;
import una.ac.cr.laberinto.modelo.Laberinto;
import una.ac.cr.laberinto.utils.JAXBUtil;
import una.ac.cr.laberinto.utils.PathChecker;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LaberintoFrame extends JFrame {
	private LaberintoPanel laberintoPanel;
	private static final double[] OPCIONES_ZOOM = {0.25, 0.50, 1.0, 1.5, 2.0, 4.0};
	private JComboBox<String> comboZoom;
	private JComboBox<String> comboModo;
	private JSlider controlZoom;
	private Laberinto laberinto;

	public LaberintoFrame(Laberinto laberinto) {
		this.laberinto = laberinto;
		inicializar();
	}

	
	private void inicializar() {
		ajustarComponentes(getContentPane());
		setTitle("Laberinto");
		setResizable(true);
		setMinimumSize(new Dimension(640, 480));
		setSize(800, 600);

		setLocationRelativeTo(null); // Centra la ventana en la pantalla
		setVisible(true);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void ajustarComponentes(Container c) {
		c.setLayout(new BorderLayout());

		JPanel d = new JPanel();
		d.setLayout(new FlowLayout(FlowLayout.RIGHT));
		d.add(new JLabel("Zoom: "));

		String[] descripcionZoom = new String[OPCIONES_ZOOM.length];
		for (int i = 0; i < OPCIONES_ZOOM.length; i++) {
			descripcionZoom[i] = String.format("%3.1f%%", 100.0 * OPCIONES_ZOOM[i]);
		}

		d.add(comboZoom = new JComboBox<>(descripcionZoom));
		d.add(new JLabel("Modo: "));
		d.add(comboModo = new JComboBox<>(Laberinto.DESCRIPCION_MODO));

		d.add(new JButton("Jugar") {
			{
				addActionListener((ActionEvent e) -> {
					laberintoPanel.requestFocusInWindow();
				});
			}
		});

		d.add(new JButton("Guardar") {	// guarda el laberinto en un archivo
			{
				addActionListener((ActionEvent e) -> {
					guardar();
				});
			}
		});



		c.add(BorderLayout.PAGE_START, d);
		c.add(BorderLayout.CENTER,
				new JScrollPane(
						laberintoPanel = new LaberintoPanel(laberinto),
						JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
						JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
				));

		c.add(BorderLayout.LINE_END, controlZoom
				= new JSlider(JSlider.VERTICAL, 125, 8000,
				(int) (((Double) laberintoPanel.getEscala()) * 100.0 * 10)));

//		comboZoom.addActionListener((ActionEvent e) -> {
//			ajustarZoom();
//		});
//		comboModo.addActionListener((ActionEvent e) -> {
//			ajustarModo();
//		});
//		controlZoom.addChangeListener((ChangeEvent e) -> {
//			panelPrincipal.setEscala(controlZoom.getValue() / (100.0 * 10));
//		});
	}


	private void guardar() {
		laberintoPanel.getController().reiniciar();
		laberintoPanel.repaint();
		String nombre = JOptionPane.showInputDialog("Nombre del laberinto");

		if (!PathChecker.isNameValid(nombre)) {
			JOptionPane.showMessageDialog(this, "El nombre del laberinto no es válido \n" +
					"El nombre solo puede contener letras y números");
			return;
		} else if (!PathChecker.isValid(nombre)) {
			JOptionPane.showMessageDialog(this, "Parece que ya existe un laberinto con ese nombre");
			return;
		}


		if (JAXBUtil.guardarLaberinto(laberinto, nombre) == 1) {
			JOptionPane.showMessageDialog(this, "Laberinto guardado correctamente");
		} else {
			JOptionPane.showMessageDialog(this, "Error al guardar el laberinto");
		}
	}
}

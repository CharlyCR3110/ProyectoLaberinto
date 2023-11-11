package una.ac.cr.laberinto.gui;
import una.ac.cr.laberinto.modelo.Laberinto;

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
}

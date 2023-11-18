package una.ac.cr.laberinto.gui.laberinto;

import una.ac.cr.laberinto.gui.main.LaberintoInfo;
import una.ac.cr.laberinto.gui.main.MainFrameController;
import una.ac.cr.laberinto.modelo.Laberinto;
import una.ac.cr.laberinto.utils.JAXBUtil;
import una.ac.cr.laberinto.utils.PathChecker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LaberintoFrame extends JFrame {
	private LaberintoPanel laberintoPanel;
	private JComboBox<String> comboZoom;
	private JComboBox<String> comboModo;
	private JSlider controlZoom;
	private Laberinto laberinto;
	MainFrameController mainFrameController;

	public LaberintoFrame(Laberinto laberinto, MainFrameController mainFrameController) {
		this.laberinto = laberinto;
		this.mainFrameController = mainFrameController;
		inicializar(laberinto.getNombre());
	}

	
	private void inicializar(String title) {
		ajustarComponentes(getContentPane());
		setTitle(title);
		setIconImage(new ImageIcon("src/main/resources/images/maze.jpeg").getImage());
		setResizable(true);
		setMinimumSize(new Dimension(640, 480));
		setSize(800, 600);

		setLocationRelativeTo(null); // Centra la ventana en la pantalla
		setVisible(true);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				handleLaberintoFrameClosing();
			}
		});

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private void ajustarComponentes(Container c) {
		c.setLayout(new BorderLayout());

		JPanel d = new JPanel();
		d.setLayout(new FlowLayout(FlowLayout.RIGHT));

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
						JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
						JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS
				)
		);


//		comboModo.addActionListener((ActionEvent e) -> {
//			ajustarModo();
//		});
	}
	private void guardar() {
		laberintoPanel.getController().reiniciar();
		laberintoPanel.repaint();
		String nombre = JOptionPane.showInputDialog("Nombre del laberinto");

		if (!esNombreValido(nombre)) { return; }

		if (JAXBUtil.guardarLaberinto(laberinto, nombre) == 1) {
			JOptionPane.showMessageDialog(this, "Laberinto guardado correctamente");
		} else {
			JOptionPane.showMessageDialog(this, "Error al guardar el laberinto");
		}
	}

	private boolean esNombreValido(String nombre) {
		if (nombre == null || nombre.isEmpty()) {
			JOptionPane.showMessageDialog(this, "El nombre del laberinto no puede estar vacío");
			return false;
		}  else if (!PathChecker.isNameValid(nombre)) {
			JOptionPane.showMessageDialog(this, "El nombre del laberinto no es válido \n" +
					"El nombre solo puede contener letras y números");
			return false;
		} else if (!PathChecker.isValid(nombre)) {
			JOptionPane.showMessageDialog(this, "Parece que ya existe un laberinto con ese nombre");
			return false;
		}
		return true;
	}


	private void handleLaberintoFrameClosing() {
		LaberintoInfo laberintoInfo = new LaberintoInfo(laberinto);
		mainFrameController.handleLaberintoFrameClose(laberintoInfo);
	}
}

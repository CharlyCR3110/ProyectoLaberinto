package una.ac.cr.laberinto.gui.archivo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FileChooserFrame extends JFrame {
	public FileChooserFrame() {
		super("Elegir un laberinto");

		JButton openButton = new JButton("Buscar un laberinto");

		openButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showFileChooser();
			}
		});

		getContentPane().add(openButton);
		setSize(300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public String showFileChooser() {
		JFileChooser fileChooser = new JFileChooser();

		String directorioProyecto = System.getProperty("user.dir");
		// Construir la ruta completa del archivo XML
		String ruta = directorioProyecto + "/src/main/resources/laberintos/";
		// Directorio inicial
		fileChooser.setCurrentDirectory(new File(ruta));

		// unicamente archivos XML
		fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
			public boolean accept(File f) {
				return f.getName().toLowerCase().endsWith(".xml") || f.isDirectory();
			}

			public String getDescription() {
				return "XML Files (*.xml)";
			}
		});

		// Deshabilitar la navegación a otras carpetas
		fileChooser.setFileHidingEnabled(true);
		fileChooser.setControlButtonsAreShown(false);
		fileChooser.setAcceptAllFileFilterUsed(false);

		int result = fileChooser.showOpenDialog(this);


		// Establecer modo de solo lectura
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFolder = fileChooser.getSelectedFile();
			return selectedFolder.getPath();
		}

		return null;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new FileChooserFrame().setVisible(true);
			}
		});
	}
}

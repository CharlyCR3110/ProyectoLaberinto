package una.ac.cr.laberinto.gui.main;

import una.ac.cr.laberinto.gui.archivo.FileChooserFrame;
import una.ac.cr.laberinto.modelo.Laberinto;
import una.ac.cr.laberinto.utils.JAXBUtil;

import javax.swing.*;

public class MainFrameController {
	MainFrame mainFrame;	// view
//	MainFrameModel mainFrameModel;	// model

	public MainFrameController() {
		this.mainFrame = new MainFrame(this);
		// this.mainFrameModel = mainFrameModel;
	}

	public Laberinto recuperar() {
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

	public boolean guardar(String nombre, Laberinto laberinto) {
		if (JAXBUtil.guardarLaberinto(laberinto, nombre) == 1) {
			return true;
		} else {
			return false;
		}
	}

	public void handleLaberintoFrameClose(LaberintoInfo laberintoInfo) {
		mainFrame.removeLaberintoInfo(laberintoInfo);
	}

	public MainFrame getMainFrame() {
		return mainFrame;
	}
}

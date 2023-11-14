package una.ac.cr.laberinto.utils;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

// path de ruta de archivos no del laberinto :c
public class PathChecker {
	public static boolean isValid(String nombreArchivo) {
		String directorioProyecto = System.getProperty("user.dir");
		String rutaDirectorio = directorioProyecto + "/src/main/resources/laberintos/";

		// Verificar si ya existe un archivo con el mismo nombre en el directorio
		File directorio = new File(rutaDirectorio);
		File[] archivos = directorio.listFiles();

		if (archivos != null) {
			for (File archivo : archivos) {
				if (archivo.isFile() && archivo.getName().startsWith(nombreArchivo)) {
					System.out.println("Ya existe un archivo con el nombre especificado.");
					return false;
				}
			}
		}

		System.out.println("El archivo es válido.");
		return true;
	}

	public static boolean isNameValid(String nombreArchivo) {
		// verifica si el nombre del archivo es válido (no contiene caracteres especiales)
		return nombreArchivo.matches("^[a-zA-Z0-9]+$");
	}
}

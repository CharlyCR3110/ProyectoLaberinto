package una.ac.cr.laberinto.utils;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import una.ac.cr.laberinto.modelo.Laberinto;


import java.io.File;

public class JAXBUtil {
	public static void guardarLaberinto(Laberinto laberinto, String nombreArchivo) {
		try {
			// Crear el contexto JAXB
			JAXBContext context = JAXBContext.newInstance(Laberinto.class);

			// Crear el marshaller
			Marshaller marshaller = context.createMarshaller();

			// Configurar la salida para que sea legible
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			// Escribir el Laberinto a un archivo XML
			marshaller.marshal(laberinto, new File(nombreArchivo));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public static Laberinto cargarLaberinto(String nombreArchivo) {
		try {
			// Crear el contexto JAXB
			JAXBContext context = JAXBContext.newInstance(Laberinto.class);
			// Crear el unmarshaller
			Unmarshaller unmarshaller = context.createUnmarshaller();
			// Leer el Laberinto desde el archivo XML
			return (Laberinto) unmarshaller.unmarshal(new File(nombreArchivo));
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
	}
}

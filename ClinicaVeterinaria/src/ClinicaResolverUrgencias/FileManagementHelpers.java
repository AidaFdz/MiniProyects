package ClinicaResolverUrgencias;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FileManagementHelpers {

	public static void main(String[] args) {
		final File File = null;
	}

	// Metodo crear un documento con datos aleatorios
	public static void rellenar(int total) {
		FileWriter fichero = null;
		PrintWriter pw = null;
		try {
			fichero = new FileWriter("C:\\temp\\Clinica\\test.txt");
			pw = new PrintWriter(fichero);
			for (int i = 0; i < total; i++) {
				objetoPaciente usuario = new objetoPaciente();
				pw.println("Usuario " + i + usuario.toString());
			}
			System.out.println("Proceso de rellenado realizado con exito.");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Nuevamente aprovechamos el finally para
				// asegurarnos que se cierra el fichero.
				if (null != fichero)
					fichero.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	// Método Insertar por copia
	static void copiar_documento(File origen, File destino) {
		try {
			InputStream in = new FileInputStream(origen);
			OutputStream out = new FileOutputStream(destino);

			byte[] buf = new byte[1024];
			int len;

			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			System.out.println("Proceso de copia realizado con exito.");
			in.close();
			out.close();
		} catch (IOException e) {
			System.out.println("Algo ha ocurrido en la ejecición de copiar.");
			e.printStackTrace();
		}
	}

	// Método para contar palabras
	void cont_words(File archivo) {
		try {

			String linea;
			FileReader fr = new FileReader(archivo);
			BufferedReader br = new BufferedReader(fr);

			int cont = 0;

			// Vamos a ir recorriendo el documento línea a línea hasta que sea null (es
			// decir, que no haya niguna más
			while ((linea = br.readLine()) != null) {

				for (int i = 0; i < linea.length(); i++) {
					if (i == 0) {
						if (linea.charAt(i) != ' ')
							cont++;
					} else {
						if (linea.charAt(i - 1) == ' ') {
							if (linea.charAt(i) != ' ') {
								cont++;
							}
						}
					}
				}
			}
			System.out.println("Son " + cont + " palabras");

			fr.close();
		} catch (IOException cont) {
			System.out.println(cont);

		}
	}

	/*
	 * Método de lectura de archivo. Hemos optado por que al usuario se le muestre
	 * el original o la copia, de modo que sea como sea pueda tener el contenido del
	 * documento
	 */
	void lectura_doc(File datos, File origen) {
		File archivoLectura = datos.exists() ? datos : origen;

		if (archivoLectura.exists()) {
			try (FileReader fr = new FileReader(archivoLectura); BufferedReader br = new BufferedReader(fr)) {
				String linea;
				while ((linea = br.readLine()) != null) {
					System.out.println(linea);
				}
			} catch (IOException e) {
				System.out.println("Ha ocurrido un error al leer el archivo.");
				e.printStackTrace();
			}
		} else {
			System.out.println("El archivo no se encuentra. Por favor, seleccione otra opción.");
		}
	}

	/*
	 * Método de lectura de archivo. Hemos optado por que al usuario se le muestre
	 * el original o la copia, de modo que sea como sea pueda tener el contenido del
	 * documento
	 */
	void lectura_doc(File datos) {

		if (datos.exists()) {
			try (FileReader fr = new FileReader(datos); BufferedReader br = new BufferedReader(fr)) {
				String linea;
				while ((linea = br.readLine()) != null) {
					System.out.println(linea);
				}
			} catch (IOException e) {
				System.out.println("Ha ocurrido un error al leer el archivo.");
				e.printStackTrace();
			}
		} else {
			System.out.println("El archivo no se encuentra para realizar la lectura .");
		}
	}

	// Método para mover de un sitio a otro los archivos
	void mover(File origen) {

		File destino = new File("C:\\temp\\destino.txt");
		try {
			if (destino.createNewFile()) {
				System.out.println("Archivo creado: " + destino.getName());
			} else {
				System.out.println("El archivo datos ya existe.");
			}
			// Imprimimos la ruta del archivo y hacemos un 2x1, de esta forma comprobamos
			// que esta
			// dónde está y que todo es como debe
			System.out.println("Archivo datos: " + destino.getAbsolutePath());
		}

		catch (IOException e) {
			System.out.println("Algo ha ocurrido en el proceso de creación de los archivos.");
			e.printStackTrace();
		}
		// Fin del Try-Catch de creado del archivo

		try {
			Files.move(origen.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
			System.out.println("El archivo se ha movido de forma satisfactoria.");
		} catch (IOException e) {
			System.out.println("El archivo no se ha podido mover. Error: " + e);
			e.printStackTrace();
		}
	}
}
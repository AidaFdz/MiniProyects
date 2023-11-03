package ejercicioDOM;

//Hacemos los import necesarios para trabar con el proyecto
import java.util.*;
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* Escribe un programa en Java que lea y analice el fichero marcadores.xml.
El programa debe listar la jerarquía y estructura del XML, mostrando los
niveles y los nodos en cada nivel.
Específicamente, tu programa debe identificar y listar todos los nodos
"competicion", mostrando sus atributos "id" y "nombre".
Además de mostrar la información por terminal, crea un nuevo archivo XML
que contenga la salida del análisis y asegúrate de que este archivo esté
formateado para una correcta visualización.

Buscar todos los resultados de un equipo a elegir. 
Para ello se debe solicitar por terminal el equipo.*/

public class ejercicioMarcadoresDom {

	public static void main(String[] args) {
//que harta me tienen 2,0
		// Comenzamos con el tratamiento de las posibles excepciones al cargar el
		// archivo
		try {
			// Cargar el archivo XML
			File archivoXML = new File("C/temp/marcadores.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(archivoXML);

			// Normalizar el documento
			doc.getDocumentElement().normalize();

			// Método para obtener la lista de nodos del documento XML
			NodeList nodeList = doc.getDocumentElement().getChildNodes();

			// Llamar a una función recursiva para mostrar la jerarquía
			// Función disponible en los métodos custom creados para el desarrollo del
			// programa
			mostrarJerarquia(nodeList, 0);

			// Solicitar el nombre del equipo por terminal
			Scanner scanner = new Scanner(System.in);
			System.out.print("Introduce el nombre del equipo a buscar: ");
			String equipoABuscar = scanner.nextLine();

			// Llamamos a la función para buscar resultados del equipo
			buscarResultadosPorEquipo(nodeList, equipoABuscar);
		} /* Fin del Try */catch (Exception e) {
			e.printStackTrace();
		}

	}// Fin del main

// METODOS ____________________________________________________________________________________
	// Función recursiva para mostrar la jerarquía del XML
	public static void mostrarJerarquia(NodeList nodeList, int nivel) {
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node nodo = nodeList.item(i);

			// Mostrar el nombre del nodo y el nivel actual
			System.out.println("marcador LVL " + nivel + ": " + nodo.getNodeName());

			// Llamar recursivamente a la función para los nodos hijos
			if (nodo.hasChildNodes()) {
				mostrarJerarquia(nodo.getChildNodes(), nivel + 1);
			}
		}
	} //Fin de la función de mostrar la jerarquía


// Función recursiva para mostrar la jerarquía del XML
	public static void mostrarCompeticiones(NodeList nodeList, int nivel) {
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node nodo = nodeList.item(i);

			// Mostrar el nombre del nodo si coincide con competición
			if (nodo.getNodeName() equals "competiciones") {
			System.out.println("competiciones LVL " + nivel + ": " + nodo.getNodeName() + " " + nodo.getNodeId ());

			// Llamar recursivamente a la función auxiliar anterior para los nodos hijos y los mostramos
			if (nodo.hasChildNodes()) {
				mostrarJerarquia(nodo.getChildNodes(), nivel + 1);
			}
			}
		}//Fin del bucle for
	}//Fin del metodo de mostrar las competiciones

	public static void buscarResultadosPorEquipo(NodeList nodeList, String equipoABuscar) {
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node nodo = nodeList.item(i);

			// Verificar si el nodo es un partido de fútbol y contiene el equipo buscado
			if (nodo.getNodeType() == Node.ELEMENT_NODE && nodo.getNodeName().equals("partido")) {
				NodeList hijosPartido = nodo.getChildNodes();
				String equipoLocal = "";
				String equipoVisitante = "";

				// Recorrer los nodos hijos para obtener los equipos del partido
				for (int j = 0; j < hijosPartido.getLength(); j++) {
					Node hijo = hijosPartido.item(j);
					if (hijo.getNodeType() == Node.ELEMENT_NODE) {
						if (hijo.getNodeName().equals("local")) {
							equipoLocal = hijo.getTextContent();
						} else if (hijo.getNodeName().equals("visitante")) {
							equipoVisitante = hijo.getTextContent();
						}
					}
				}

				// Verificar si el equipo buscado participó en el partido
				if (equipoLocal.equals(equipoABuscar) || equipoVisitante.equals(equipoABuscar)) {
					System.out.println("Resultado del partido:");
					System.out.println("Local: " + equipoLocal);
					System.out.println("Visitante: " + equipoVisitante);
					// Por dios que no se me tenga en cuenta que no tengo ni puñetera idea del tema
				}
			}

			// Llamar recursivamente a la función para los nodos hijos a ver si hay equipos
			// Viendo la estructura del propio XML vemos que en el nodo padre casi nunca
			// aparecen esots datos
			if (nodo.hasChildNodes()) {
				buscarResultadosPorEquipo(nodo.getChildNodes(), equipoABuscar);
			}
		}
	}// Fin del Método de buscar resultados por equipos

}
package PackageHospital;

public class AdministracionKassSA {
	/*
	 * Realizado por: Juan Carlos González
	 * Ayudado por: Aida Fernández
	 * 
	 * Correcciones sobre las explciaciones de Aida:
	 * - El código de Aida da error cuando se intenta tomar un elemento de una cola vacía
	 * - Este cógdigo genera siempre antes de que se consuma todo
	 * - Eliminamos la complicación de las funciones estadísticas que pueden fallar para crear los objetos
	 * 
	 */}
/*
	public static void main(String[] args) {
		// Este es el main que va a gestionar los hilos de las urgencias
		urgenciasKass colaur = new urgenciasKass();
		// Creamos el hilo de las entradas de los pacientes y le damos las
		// funcionalidades
		Thread pacientes = new Thread(() -> {
			try {
				colaur.producirUrgencia();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}

		});
		// Creamos el hilo de los pacientes que se van de alta y le damos las
		// funcionalidades
		Thread altas = new Thread(() -> {
			try {
				colaur.resolverUrgencia();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}

		});

		//De esta forma se inicializan los hilos
		pacientes.start();
		altas.start();

	}
}
*/
package AidaFdzFilosofos;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AidaFdzCenita {

	public static void main(String[] args) {
		// A la cena han sido invitados 11 filósofos, siempre vienen 5, pero los otros 6
		// son un poco informales, así que cada vez que se convoca la cena aparece quien
		// quiere
		int numFilosofos = (int) Math.floor(Math.random() * 11 + 5);
		Lock[] palitos = new Lock[numFilosofos];

	        // Inicializar los tenedores
	        for (int i = 0; i < numFilosofos; i++) {
	            palitos[i] = new ReentrantLock();
	        }

	        // Crear filósofos
	        Thread[] filosofos = new Thread[numFilosofos];
	        for (int i = 0; i < numFilosofos; i++) {
	            Lock tenedorIzquierdo = palitos[i];
	            Lock tenedorDerecho = palitos[(i + 1) % numFilosofos];
	            filosofos[i] = new Thread(new Filosofos(i, tenedorIzquierdo, tenedorDerecho));
	        }

	        // Iniciar los hilos de los filósofos
	        for (int i = 0; i < numFilosofos; i++) {
	            //filosofos[i].start();
	        	 filosofos[i].run();
	        }

	        // Inicializar el conjunto de turnos
	        synchronized (Filosofos.class) {
	            for (int i = 0; i < numFilosofos; i++) {
	                Filosofos.turnos.add(i);
	            }
	            palitos.notifyAll();// Notificar a los filósofos que pueden comenzar
	        }
	    }
	}
		
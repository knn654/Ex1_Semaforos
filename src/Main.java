

import java.util.concurrent.Semaphore;


public class Main {
public static Semaphore semaforo;
	public static void main(String[] args) {
		
		int totalCompradores = 300;
		int maxAcessos = 1;
		semaforo = new Semaphore(maxAcessos);
		
		for (int i = 0; i < totalCompradores; i++) {
			int qtdIngressos = (int) (Math.random() * 4) + 1;
			Thread compradores = new Threads(i, semaforo, qtdIngressos);
			compradores.start();
		}
	}
}
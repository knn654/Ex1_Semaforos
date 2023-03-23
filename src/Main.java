

import java.util.concurrent.Semaphore;


public class Main {
public static Semaphore semaforo;
	/* 
Um grande show acontecerá no Brasil, em uma casa com capacidade para 100 pessoas.
A venda será feita excluisvamente pelo sistema.
Simultaneamente, 300 pessoas, no primeiro instante acessam o sistema de compra.
*/
	public static void main(String[] args) {
		
		int totalCompradores = 300;
		int maxAcessos = 1;
		semaforo = new Semaphore(maxAcessos);
		
		for (int i = 0; i < totalCompradores; i++) {
			int qtdIngressos = (int) (Math.random() * 3) + 1;
			Thread compradores = new Threads(i, semaforo, qtdIngressos);
			compradores.start();
		}
	}
}
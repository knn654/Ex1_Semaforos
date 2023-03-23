import java.util.concurrent.Semaphore;

public class Threads extends Thread {
	private int numComprador;
	private Semaphore semaforo;
	static int estoque = 100;
	private int qtdIngressos;
	public Threads(int numComprador, Semaphore semaforo, int qtdIngressos) {
		this.numComprador = numComprador;
		this.semaforo = semaforo;
		this.qtdIngressos = qtdIngressos;
	}
	
	public void run() {
		comprarIngresso();
	}
	
	public void comprarIngresso() {
		System.out.println("Logando no sistema... ");
		int tempoEspera = (int) (Math.random() * 150) + 50;
		try {
			Thread.sleep(tempoEspera);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (tempoEspera > 100) {
			System.out.println("Você levou timeout no login usuário #" + numComprador + ", tempo total: " + tempoEspera + "ms");
		} else {
			System.out.println("Usuário " + numComprador + " logado! Quantidade de ingressos: " + qtdIngressos);
			try {
				semaforo.acquire();
				efetuarCompra();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				semaforo.release();
			}
		}
		
		
	}
	
	public void efetuarCompra() {
		int tempoEspera = (int) (Math.random() * 200) + 100;
		try {
			Thread.sleep(tempoEspera);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (tempoEspera >= 250) {
			System.out.println("Você levou timeout na compra, usuário #" + numComprador + ", tempo total: " + tempoEspera + "ms");
		} else {
			if (qtdIngressos <= estoque) {
				estoque = estoque - qtdIngressos;
				System.out.println("Parabéns usuário #" + numComprador + ", você acabou de comprar " + qtdIngressos + " ingressos! Estoque: " + estoque);
			} else {
				System.out.println("Quantidade de ingressos desejada (" + qtdIngressos +") menor do que a disponível, usuário #" + numComprador);
			}
		}
	}
}

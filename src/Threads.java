import java.util.concurrent.Semaphore;

/* As pessoas podem comprar de 1 a 4 ingressos por compra, sendo que isso é uma condição aleatória.
Os passos para a compra são:
1) Login no sistema: Processo que pode demorar de 50 ms a 2 s, sendo que, se o tempo passar de 1s,
ao final do tempo de espera de login, o comprador recebe uma mensagem de timeout e, por não
conseguir fazer o login, não poderá fazer a compra.

2) Processo de compra: Processo que pode demorar de 1 s a 3 s, sendo que, se o tempo passar de
2,5s, ao final do tempo de espera da compra, o comprador recebe uma mensagem de final de tempo
de sessão e, por estourar o tempo de sessão, não poderá fazer a compra.
3) Validação da compra: O sistema deve verificar se há ingressos suficientes para finalizar a
compra. Se houver, faz a compra e subtrai do total de ingressos disponíveis. O sistema comunica a
venda da quantidade de ingressos para o usuário e a quantidade de ingressos ainda disponíveis. Se
não houver a totalidade dos ingressos disponibiliados, o comprador recebe mensagem sobre a
indisponibilidade dos ingressos e, como não é possível fracionar a quantidade pedida, este perde a
possibilidade de compra na sessão.
Simular em Java essa situação
* O processo de validação da compra, apenas 1 comprador por vez,
pois a finalização do processo depende da disponibilidade de
ingressos.
** Os processos de Login e escolha da quantidade de ingressos, por
não depender de mais nada, devem ser feito simultaneamente
entre todos os compradores.
*/

public class Threads extends Thread {
	private int numComprador;
	private Semaphore semaforo;
	static int estoque = 100;
	public Threads(int numComprador, Semaphore semaforo) {
		this.numComprador = numComprador;
		this.semaforo = semaforo;
	}
	
	public void run() {
		try {
			semaforo.acquire();
			comprarIngresso();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
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
			System.out.println("Você levou timeout usuário #" + numComprador);
		} else {
			System.out.println("Usuário " + numComprador + " logado");
			efetuarCompra();
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
			System.out.println("Você levou timeout, usuário #" + numComprador);
		} else {
			int ingressos = (int) (Math.random() * 3) + 1;
			if (ingressos <= estoque) {
				System.out.println("Parabéns usuário #" + numComprador + ", você acabou de comprar " + ingressos + " ingressos!");
			} else {
				System.out.println("Quantidade de ingressos desejada menor do que a disponível.");
			}
		}
	}
}

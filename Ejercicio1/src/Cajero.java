import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Cajero implements Runnable {
	private int id;
	private int tiempoMaximoPorCliente;
	private int maximaEspera; // tiempo máximo que estaremos sin atender antes de cerrar caja.
	private BlockingQueue q;
	
	public Cajero(int id, int tiempoPorCliente, int maximaEspera, BlockingQueue q) {
		super();
		this.maximaEspera = maximaEspera;
		this.tiempoMaximoPorCliente = tiempoMaximoPorCliente;
		this.id = id;
		this.q = q;
	}

	@Override
	public void run() {
		//creamos variables cliente , tiempo cajero y random
		Cliente cliente;
		int tiempoCajero;
		Random rnd=new Random();
		
		// TODO: mientras hayan clientes...
		

		try{
			// sacamos un cliente de la cola, imprimimos "CAJERO X ATENDIENDO CLIENTE Y"
	while((cliente= (Cliente)q.poll(maximaEspera, TimeUnit.SECONDS)) != null){
		
		System.out.println("CAJERO "+ this.id+ " ATENDIENDO CLIENTE "+ cliente.getId());
		// esperamos un tiempo aleatorio entre 1segundo y tiempoMaximoPorCliente
				// AYUDA: (int)(rnd.nextDouble() * tiempoMaximoPorCliente + 1);
		tiempoCajero=(int)(rnd.nextDouble() * tiempoMaximoPorCliente + 1);
		//dormimos el hilo y pasamos el tiempo a Millis
		Thread.sleep(tiempoCajero*1000);
		// esperamos y imprimimos "CAJERO X FINALIZA CON CLIENTE Y. ATENDIDO EN T SEGUNDOS"
		// donde T es el tiempo que ha tardado en esperar en la cola + ser atendido.
		System.out.println("CAJERO "+this.id+" FINALIZA CON CLIENTE "+cliente.getId()+
				" ATENDIDO EN "+tiempoCajero+ " segundos");
	}
		}catch(InterruptedException e){
			System.out.println("Interrumpido");
		}
		
			
		// Si estamos más de "maximaEspera" segundos sin que hayan clientes imprimimos "CAJERO X CERRANDO."
		System.out.println("CAJERO "+this.id+" CERRANDO.");
	}

	

}

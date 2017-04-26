import java.util.concurrent.BlockingQueue;

public class GeneradorClientes implements Runnable {
	private int clientesIniciales;
	private int clientesPorSegundo;
	private long tiempoMaximo;
	private BlockingQueue<Cliente> q;
	private int numCliente=0;
	
	public GeneradorClientes(int clientesIniciales, int clientesPorSegundo, long tiempoMaximo, BlockingQueue q) {
		super();
		this.clientesIniciales = clientesIniciales;
		this.clientesPorSegundo = clientesPorSegundo;
		this.tiempoMaximo = tiempoMaximo;
		this.q = q;
		
	}
	
	@Override
	public void run() {
		long tiempoInicial = System.nanoTime();
		// TODO: generamos "clientesIniciales" y los encolamos
		generarClientes(this.clientesIniciales);
		// mientras no nos pasemos del tiempoMaximo 
		while ((System.nanoTime() - tiempoInicial) < tiempoMaximo*1000000000) {
			// TODO: esperar y generar cliente según "clientesPorSegundo".
			System.out.println("GENERANDO CLIENTES");
			
			//llamamos al m�todo generarClientes
			generarClientes(this.clientesPorSegundo);
			
			try {
				//dormimos el hilo con el tiempo clientesPorSegundo
				Thread.sleep(this.clientesPorSegundo*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// TODO: Imprimir "Cerrando supermercado, ya no entran más clientes."
		System.out.println("Cerrando el supermercado, ya no entran m�s clientes");
	}
	//creamos un m�todo para crear clientes y lo llamaremos desde el run
	public void generarClientes(int num){
		for(int i=0;i<num;i++){
			Cliente c=new Cliente(numCliente);
			numCliente=numCliente+1;
			
			try {
				//a�adimos los clientes a la blockingQueue q
				q.put(c);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}


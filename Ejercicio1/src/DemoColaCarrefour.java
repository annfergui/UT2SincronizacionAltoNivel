import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class DemoColaCarrefour {
	public static void main(String[] args) throws InterruptedException {
		// TODO: creamos nuestra BloquingQueue
		ArrayList<Thread> threads=new ArrayList<>();
		BlockingQueue<Cliente> q = new ArrayBlockingQueue<Cliente>(1000);
		
		// TODO: Creamos y arrancamos el generador de clientes. Por ejemplo:
		// 30 clientes iniciales
		// 2 clientes nuevos por segundo
		// 100 segundos antes de cerrar las puertas del super...
		GeneradorClientes generador=new GeneradorClientes(7,2,5,q);
		//creamos el hilo que generar� clientes
		Thread generadorThread=new Thread(generador);
		//inicialimos el hilo 
		generadorThread.start();
		
		// 3 cajeros para atender
		for(int i=0; i<3; i++) {
			// TODO: Creamos y arrancamos los cajeros en sus Threads
			threads.add(new Thread(new Cajero(i,5,10,q)));
			threads.get(i).start();
		}
		
		// TODO: Imprimir cada 2segundos el tamaño de la cola
		//creamos un thread Runnable
		Thread checkqueue=new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				//comprobamos que el hilo actual no est� interrumpido
				while(!Thread.currentThread().isInterrupted()){
					try {
						//dormimos el hilo 2 swgundos
						Thread.sleep(500);
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
				}
					System.out.println("CLIENTES EN COLA:"+ q.size());
			}
				
			}	
		});
		//Iniciamos el thread
		checkqueue.start();
		
		//hacemos un bucle que recorrer� el ArrayList de hilos para suspenderlos
		for(Thread thread:threads){
			
			thread.join();
		}
		//interrumpimos el hilo
		checkqueue.interrupt();
		//suspendemos la ejecuci�n del hilo esperando a que el hilo que esperamos finalice 
		//su ejcuci�n
		checkqueue.join();
		
		System.out.println("SUPERMERCADO CERRADO");
		// TODO: Esperamos a que se vayan los clientes y cajeros para cerrar el super
		// y imprimimos "SUPERMERCADO CERRADO."
		
		
		
		
		
	}
}


public class Cliente {
	private int id;
	private long tiempoInicial;
	private long tiempoFinal;

	public Cliente(int id) {
		super();
		this.id = id;
		this.tiempoInicial = System.nanoTime();
	}
	
	private void setTiempoFinal() {
		this.tiempoFinal = System.nanoTime();
	}
	
	public long getTiempoEspera() {
		if (this.tiempoFinal == 0L) {
			setTiempoFinal();
		}
		return this.tiempoFinal - this.tiempoInicial;
	
	
	}
//a�adimos m�todo getId para poder imprimir el id del cliente
	public int getId() {
		return id;
	}
	
	
	
}

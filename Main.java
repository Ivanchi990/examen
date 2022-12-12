package productor_consumidor;

public class Main 
{

	public static void main(String[] args) 
	{
		Reservado reservado = new Reservado();
		
		Productor productor = new Productor(reservado);
		Consumidor consumidor = new Consumidor(reservado);
		
		productor.start();
		consumidor.start();
	}
}

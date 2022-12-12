package productor_consumidor;

public class Consumidor extends Thread
{
	Reservado reservado;
	
	public Consumidor(Reservado reservado)
	{
		this.reservado = reservado;
	}
	
	
	public void run()
	{	
		for(int i = 0; i <= 5; i++)
		{	
			reservado.consume();
			
			System.out.println("Un placer serviros adios " + i);
		}
	}
}

package productor_consumidor;

public class Productor extends Thread
{
	Reservado reservado;
	
	public Productor(Reservado reservado)
	{
		this.reservado = reservado;
	}
	
	
	public void run()
	{
		for(int i = 0; i <= 5; i++)
		{
			try
			{
				Thread.sleep(1000);
				
				reservado.produce();
				
				System.out.println("Atendiendo a un grupo de clientes " + i);
			}
				catch(InterruptedException e)
			{
				System.err.println(e.getMessage());
			}
		}
	}
}

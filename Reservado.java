package productor_consumidor;

import java.util.LinkedList;

public class Reservado 
{
	private LinkedList<Object> list;
	private final int MAX_SIZE;
	private int ocupadas;
	
	private boolean estaLleno;
	private boolean estaVacio;
	
	public Reservado()
	{
		list = new LinkedList<>();
		MAX_SIZE = 3;
		ocupadas = 0;
		estaLleno = false;
		estaVacio = true;
	}
	
	
	public synchronized void produce()
	{
		while(estaLleno == true)
		{
			try 
			{
				wait();
			} 
				catch (InterruptedException e) 
			{
				System.err.println(e.getMessage());
			}
		}
		
		list.add(new Object());
		
		if(list.size() == MAX_SIZE)
		{
			estaLleno = true;
			estaVacio = false;
		}
		
		notifyAll();
	}
	
	public synchronized void consume()
	{
		while(estaVacio == true)
		{
			try 
			{
				wait();
			} 
				catch (InterruptedException e) 
			{
				System.err.println(e.getMessage());
			}
		}
		
		list.remove();
		
		if(list.size() == 0)
		{
			estaVacio = true;
			estaLleno = false;
		}
		
		notifyAll();
	}
}

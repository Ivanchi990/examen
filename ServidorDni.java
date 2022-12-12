//FUNCIONA

package servidor_dni;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorDni
{
	public static void main(String args[])
	{
		try 
		{
			ServerSocket servidor = new ServerSocket(8000);
			
			System.out.println("El servidor esta encendido");
			
			while(true)
			{
				Socket cliente = servidor.accept();
				
				HiloCliente hiloCLiente = new HiloCliente(cliente);
				
				hiloCLiente.start();
			}
		} 
			catch (IOException e) 
		{
			System.err.println(e.getMessage());
		}
	}
}

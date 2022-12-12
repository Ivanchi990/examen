package servidor_dni;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HiloCliente extends Thread
{
	Socket s;
	
	public HiloCliente(Socket s)
	{
		this.s = s;
	}
	
	
	public void run()
	{
		try 
		{
			DataInputStream dis = new DataInputStream(s.getInputStream());
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			
			dos.writeBoolean(comprobarDni(dis.readUTF()));
			
			dis.close();
			dos.close();
			s.close();
		} 
			catch (IOException e) 
		{
			System.err.println(e.getMessage());
		}
	}
	
	
	public boolean comprobarDni(String dni)
	{
		if(dni.length() == 9)
		{
			char letras[] = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
			
			char prueba[] = dni.toCharArray();
			String num = "";
			
			for(int i = 0; i < 8; i++)
			{
				if(Character.isDigit(prueba[i]))
				{
					String car = prueba[i] + "";
					num = num + Integer.parseInt(car);
				}
				else
				{
					return false;
				}
			}
			
			int resto = Integer.valueOf(num) % 23;
			
			if(letras[resto] == (Character.toUpperCase(prueba[8])))
			{
				return true;
			}
		}
		
		return false;
	}
}

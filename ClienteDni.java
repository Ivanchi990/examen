package servidor_dni;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClienteDni 
{

	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Introduce tu dni:\n");
		String dni = sc.nextLine();
		
		try 
		{
			Socket s = new Socket("localhost", 8000);
			
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			DataInputStream dis = new DataInputStream(s.getInputStream());
			
			dos.writeUTF(dni);
			
			System.out.println("EL dni es: " + dis.readBoolean());
			
			dos.close();
			dis.close();
			s.close();
		} 
			catch (IOException e)
		{
			System.err.println(e.getMessage());
		}
	}
}

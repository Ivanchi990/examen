package aad.ex.ivan_fernandez;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import aad.ex.conexion.ConexionSingleton;

public class Funciones 
{
	public Funciones()
	{
		
	}

	
	public void anadirSesiones(ArrayList<Sesion> sesiones)
	{
		String insert = "INSERT INTO Sesiones(idSala, idPelicula, hora, idioma) VALUES(?, ?, ?, ?);";
		
		Connection conexion = ConexionSingleton.getConnection();
		
		try 
		{
			conexion.setAutoCommit(false);
			
			PreparedStatement ps = null;
			
			for(Sesion s: sesiones)
			{
				ps = conexion.prepareStatement(insert);
				
				ps.setLong(1, s.getIdSala());
				ps.setLong(2, s.getIdPelicula());
				ps.setString(3, s.getHora());
				ps.setString(4, s.getIdioma());
				
				ps.execute();
			}
			
			conexion.commit();
		} 
			catch (SQLException e) 
		{
			System.err.println(e.getMessage());
			
			try 
			{
				conexion.rollback();
			} 
				catch (SQLException e1) 
			{
				System.err.println(e1.getMessage());
			}
		}
	}
	
	
	public void eliminarPelicula(long idPelicula)
	{
		String deleteSesion = "DELETE FROM Sesiones WHERE idPelicula = ?;";
		String deletePelicula = "DELETE FROM Peliculas WHERE idPelicula = ?;";
		
		Connection conexion = ConexionSingleton.getConnection();
		
		try 
		{
			conexion.setAutoCommit(false);
			
			PreparedStatement ps;
			
			ps = conexion.prepareStatement(deleteSesion);
			ps.setLong(1, idPelicula);
			
			ps.execute();
			
			ps = conexion.prepareStatement(deletePelicula);
			ps.setLong(1, idPelicula);
			
			ps.execute();
			
			conexion.commit();
		} 
			catch (SQLException e) 
		{
			System.err.println(e.getMessage());
			
			try 
			{
				conexion.rollback();
			} 
				catch (SQLException e1) 
			{
				System.err.println(e1.getMessage());
			}
		}
	}
	
	
	public Sala obtenerSala(long idSala)
	{
		String select = "SELECT * FROM Salas WHERE idSala = ?;";
		
		Connection conexion = ConexionSingleton.getConnection();
		
		try 
		{
			conexion.setAutoCommit(true);
			
			PreparedStatement ps = conexion.prepareStatement(select);
			ps.setLong(1, idSala);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				 return new Sala(idSala, rs.getInt(2), rs.getString(3));
			}
		} 
			catch (SQLException e) 
		{
			System.err.println(e.getMessage());
		}
		
		return null;
	}
	
	
	public Pelicula obtenerPelicula(long idPelicula)
	{
		String select = "SELECT * FROM Peliculas WHERE idPelicula = ?;";
		
		Connection conexion = ConexionSingleton.getConnection();
		
		try 
		{
			conexion.setAutoCommit(true);
			
			PreparedStatement ps = conexion.prepareStatement(select);
			ps.setLong(1, idPelicula);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				 return new Pelicula(idPelicula, rs.getString(2), rs.getString(3));
			}
		} 
			catch (SQLException e) 
		{
			System.err.println(e.getMessage());
		}
		
		return null;
	}
	
	
	public Boolean modificarSesion(Sesion s)
	{
		String update = "UPDATE Sesiones SET idSala = ?, idPelicula = ?, hora = ?, idioma = ? WHERE idSesion = ?;";
		
		Connection conexion = ConexionSingleton.getConnection();
		
		try
		{
			conexion.setAutoCommit(true);
			
			PreparedStatement ps = conexion.prepareStatement(update);
			
			ps.setLong(1, s.getIdSala());
			ps.setLong(2, s.getIdPelicula());
			ps.setString(3, s.getHora());
			ps.setString(4, s.getIdioma());
			ps.setLong(5, s.getIdSesion());
			
			int rows = ps.executeUpdate();
			
			if(rows > 0)
			{
				return true;
			}
		} 
		catch (SQLException e) 
		{
			System.err.println(e.getMessage());
		}
		
		return false;
	}
	
	
	public void exportarPeliculas(String nombreFichero)
	{	
		String select = "SELECT * FROM Peliculas;";
		
		Connection conexion = ConexionSingleton.getConnection();
		
		try 
		{
			conexion.setAutoCommit(true);
			
			PreparedStatement ps = conexion.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(nombreFichero)));
			
			while(rs.next())
			{
				Pelicula peli = new Pelicula(rs.getLong(1), rs.getString(2), rs.getString(3));
				
				bw.write(peli.toCSV());
			}
			
			bw.close();
		} 
			catch (IOException | SQLException e) 
		{
			System.err.println(e.getMessage());
		}
	}
}

//		Funciones funciones = new Funciones();
//		
//		ArrayList<Sesion> sesiones = new ArrayList<>();
//		
//		sesiones.add(new Sesion(1, 11, "2022-12-02 14:30:00", "Rumano"));
//		sesiones.add(new Sesion(2, 11, "2022-12-02 15:30:00", "Pitinglish"));
//		sesiones.add(new Sesion(3, 11, "2022-12-02 16:30:00", "Sevillano"));
//		sesiones.add(new Sesion(2, 11, "2022-12-02 17:30:00", "Hungaro"));
//		sesiones.add(new Sesion(1, 11, "2022-12-02 18:30:00", "Griego"));
//		
		
//		funciones.anadirSesiones(sesiones);
		
		
//		funciones.eliminarPelicula(11);
		
//		System.out.println(funciones.obtenerSala(2));

//		System.out.println(funciones.obtenerPelicula(2));
		
//		Sesion s = new Sesion(1, 2, 1, "2022-12-02 10:00:00", "Rumanosky");
		
//		System.out.println(funciones.modificarSesion(s));
		
//		funciones.exportarPeliculasACsv("/home/alumno/peliculas.txt");

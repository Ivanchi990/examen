package aad.ex.ivan_fernandez;

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
		String insert = "INSERT INTO Sesiones VALUES(?, ?, ?, ?);";
		
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
			
			if(ps.execute())
			{
				ps = conexion.prepareStatement(deletePelicula);
				ps.setLong(1, idPelicula);
				
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
		
		
		return false;
	}
	
	
	public void exportarPeliculas(String nombreFichero)
	{
		
	}
}

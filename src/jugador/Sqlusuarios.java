package jugador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Modelo.ConexionDB;
//esta es mi clase para insertar y actualizar los usuarios a la base de datos
public class Sqlusuarios extends Conexion {
	//aqui instanciamos la clase conexiondb y le pasamos los parametros correspondientes
	ConexionDB conexion=new ConexionDB("localhost","usuarios","root","");
	//creamos un preparedstatemente y lo ponemos en null para luego poder pasarle las consultas
	PreparedStatement ps = null;
	//aqui llamaos al get conexion y lo guardamos en una variable tipo Connection
	Connection con =conexion.getConexion();
	
//este el el metodo para regustrar
	public boolean registrar(jugador usr){
		
		
		//insertamos en un string la query para insertar el jugador
		String sql = "INSERT INTO usuarios (id,nombre,apellidos,edad,pais,genero,score,contraseña)"+ "VALUES(?,?,?,?,?,?,?,?)";
		
		try {
			//creamos el preparedstatement 
			ps = con.prepareStatement(sql);
			//aqui recogemos los datos que queremos insertar en la base de datos
			//cada numero representa un signo de interrogacion en values dentro del string sql para mantener el orden de 
			//las inserciones
			ps.setInt(1, usr.getId());
			ps.setString(2, usr.getNombre());
			ps.setString(3, usr.getApellidos());
			ps.setInt(4, usr.getEdad());
			ps.setString(5, usr.getPais());
			ps.setString(6, usr.getGenero());
			ps.setInt(7, usr.getScore());
			ps.setString(8, usr.getContraseña());
			ps.execute();
			
			//si se conecta correctamente nos devuelve un true
			return true;
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			//si no nos devuelve un true nos devuelve un log con el error
			Logger.getLogger(Sqlusuarios.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
		
		
		
	}
	//este ees el metodo para actualizaer los usuarios a la base de datos haremos exactamente los mismo que arriba
	//solo que ahora cambiaremos la conslta para que en vez de regustrar actualice los datos del jugador 
	public boolean actualizar(jugador usr){
		int id=usr.getId();
		
		String sql="UPDATE usuarios SET usuarios.nombre=?, usuarios.apellidos=?, usuarios.edad=?, usuarios.pais=?, usuarios.genero=?, usuarios.score=?, usuarios.contraseña=? WHERE usuarios.id=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, usr.getNombre());
			ps.setString(2, usr.getApellidos());
			ps.setInt(3, usr.getEdad());
			ps.setString(4, usr.getPais());
			ps.setString(5, usr.getGenero());
			ps.setInt(6, usr.getScore());
			ps.setString(7, usr.getContraseña());
			ps.setInt(8, usr.getId());
			ps.execute();
			
			
			return true;
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			Logger.getLogger(Sqlusuarios.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
				
	}
}

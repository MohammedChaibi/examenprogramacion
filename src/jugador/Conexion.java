package jugador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//clase conexio
public class Conexion {
	//declaramos las variables que contendran los datos de la base de datos cusuario contraseña base de datos host y la conexion
	private String _usuario="root";
	private String _pwd= "";
	private static String _bd="usuarios";
	static String _url = "jdbc:mysql://localhost:3306/"+_bd;
	private Connection conn = null;
	
	//creamos un metodo getter para que nos devuelva la conexion
	
	public Connection getConexion() {
		
		try{
			//aqui le decimos que nos coja el driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			//aqui en la variable de tipo Connection hacemos un get connection con el driver manager y le pasmos los parametros para conectarse a la base de datos
			conn = DriverManager.getConnection(_url, _usuario, _pwd);
			//aqui ponemos un if para que si la conexion no es nula que nos imprima por pantalla que se ha conectado correctamente 
			if(conn != null)
			{
				System.out.println("Conexion a base de datos "+_url+" . . . Ok");
			}
		}
		//con la excepcion vemos si no se ha podido conectar
		catch(SQLException ex)
		{
			System.out.println("Hubo un problema al intentar conecarse a la base de datos "+_url);
		}
		catch(ClassNotFoundException ex)
		{ 
			System.out.println(ex);
		}	
		//y aqui nos devuelve la conexion
		return conn;
	}
}

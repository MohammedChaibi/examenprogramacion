package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;

import jugador.jugador;

public class JugadorDB {
	
	//private Jugador jugador;
	
	//Conexion a la base de datos
	private Statement orden = null;
	private ResultSet rs=null;
	private Connection conexion;

    
	
	//Constructor: recoge la conexión establecida para la base de datos
	public JugadorDB(Connection c) {
		this.conexion=c;
	}
	
	//Método que permite insertar un usuario en la base de datos
	//devolverá el número de identificador de usuario
	public int insertarUsuario(jugador jugador){
			int id=0;
			try{
				orden = conexion.createStatement();
			    String sql = "INSERT INTO player (nombre,apellidos,edad,puntos) " +
			                   "VALUES ('"+jugador.getNombre()+"', '"+jugador.getApellidos()+"', '"
			                  +jugador.getEdad()+"','"+jugador.getScore()+"')";
			    orden.executeUpdate(sql);
			    //System.out.println("Usuario registrado con exito");
			    //Una vez introducido el jugador buscamos su id. recorriendo la lista hasta
			    //la última posición, que es donde se habrá insertado el nuevo usuario.
				orden = conexion.createStatement();
			    sql = "SELECT id FROM player ORDER BY ID DESC LIMIT 1";
			    rs = orden.executeQuery(sql);
			    //Cogemos el id de los usuarios por orden hasta llegar al último
			    while(rs.next()){
			    	  id=rs.getInt("id");
			    }
			    }catch(SQLException se){
				      //Se produce un error con la consulta
				      se.printStackTrace();
			   }catch(Exception e){
				      //Se produce cualquier otro error
				      e.printStackTrace();
				      
			   }finally{
				      //Cerramos los recursos
				      try{
				         if(orden!=null)
				        	 orden.close();
				      }catch(SQLException se){
				    	  se.printStackTrace();}
				      try{
				         if(conexion!=null)
				        	 conexion.close();
				      	 }catch(SQLException se){
				         se.printStackTrace();
				      }//end finally try
			   }//end try
		    return id;
	}

	//Método que permite buscar un si un jugador está ya en la BBDD
			public boolean buscarUsuario(jugador jugador){
				String nombre=jugador.getNombre();
				String apellido1=jugador.getApellidos();
				
				int edad=jugador.getEdad();
				boolean repetido=false;
				ResultSet rs;
				try{
				  orden = conexion.createStatement();
			      String sql = "SELECT id,nombre, apellido1, apellido2, edad FROM player WHERE nombre='"+nombre+"' AND apellido1='"+apellido1+"' AND edad='"+edad+"'";
			      rs = orden.executeQuery(sql);
			      System.out.println("Buscamos jugadores repetidos\n");
			      //Cogemos los usuarios y recorremos la BBDD mientras haya datos
			      while(rs.next()){
			    	  repetido=true;
				      System.out.println("Jugador ya está en BBDD\n");
			      }
			      //Debemos cerrar la conexion
			      rs.close();
			      return repetido;
				}catch(SQLException se){
					      //Se produce un error con la consulta
					      se.printStackTrace();
					      return repetido;
				}catch(Exception e){
					      //Se produce cualquier otro error
					      e.printStackTrace();
					      return repetido;
				}finally{
				      //Cerramos los recursos
				      try{
				         if(orden!=null)
				        	 orden.close();
				      }catch(SQLException se){
				      }
				      try{
				         if(conexion!=null)
				        	 conexion.close();
				      	 }catch(SQLException se){
				         se.printStackTrace();
				      	 }//end finally try
				}
		}	
	
	//Método que permite buscar un jugador en la base de datos devolviendolos en un JComboBox
	//Busca los jugadores por el nombre y devuelve todos los que tienen el mismo nombre

			public void buscarUsuario(String nombreBuscar,JComboBox jc){
				ResultSet rs;
				try{
					orden = conexion.createStatement();
					  /*Si quisiésemos que devolviese todos los usuarios de la BBDD en el COMBOX haríamos
					   * String sql = "SELECT id,nombre, apellido1, apellido2, edad FROM usuarios";
					   * y eliminaríamos el input de nombreBuscar en el método
					   */
				      String sql = "SELECT id,nombre, apellido1, apellido2, edad, puntos FROM player WHERE nombre='"+nombreBuscar+"'";
				      rs = orden.executeQuery(sql);
					//Cogemos los usuarios
					      while(rs.next()){
					    	  jugador u=new jugador(); //Se generará un usuario por cada coincidencia
					    	  u.setId(rs.getInt("id"));
						      u.setNombre(rs.getString("nombre"));
						      u.setApellidos(rs.getString("apellido1"));
						      
						      u.setEdad(rs.getInt("edad"));
						      u.setScore(rs.getInt("puntos"));
						      //Añadimos el usuario encontrado al JComboBox
						      //El usuario no "será destruido" al salir del método porque se almacena en un objeto
						      //superior que lo guarda, que es el jc (un objeto ComboBox)
						      jc.addItem(u);
						      //Comprobación por monitor
						      System.out.println("Coincidencias: "+u.toString()+"\n");
					      }
					    //Debemos cerrar la conexion
					      rs.close();
						}catch(SQLException se){
							      //Se produce un error con la consulta
							      se.printStackTrace();
						}catch(Exception e){
							      //Se produce cualquier otro error
							      e.printStackTrace();
						}finally{
						      //Cerramos los recursos
						      try{
						         if(orden!=null)
						        	 orden.close();
						      }catch(SQLException se){
						      }
						      try{
						         if(conexion!=null)
						        	 conexion.close();
						      	 }catch(SQLException se){
						         se.printStackTrace();
						      	 }//end finally try
						}
			}
			
	
	//Método que permite buscar y actualizar los datos de un jugador en la base de datos con un preparedStatement
	//No se modifican los puntos.
	public void actualizarUsuario(jugador jugador){
		try{
		  orden = conexion.createStatement();
		// create the java mysql update preparedstatement
	      String sql = "UPDATE player " +
                       "SET nombre = ?"+
                       ",apellido1 = ?"+
                       ",edad = ? "+
	    		       "WHERE id = "+jugador.getId();
	      PreparedStatement preparedStmt = conexion.prepareStatement(sql);
	      preparedStmt.setString(1, jugador.getNombre());
	      preparedStmt.setString(2, jugador.getApellidos());
	      
	      preparedStmt.setInt(3, jugador.getEdad());
	 
	      // se ejecuta la consulta
	      preparedStmt.executeUpdate();
		}catch(SQLException se){
			      //Se produce un error con la consulta
			      se.printStackTrace();
		}catch(Exception e){
			      //Se produce cualquier otro error
			      e.printStackTrace();
		}finally{
		      //Cerramos los recursos
		      try{
		         if(orden!=null)
		        	 orden.close();
		      }catch(SQLException se){
		      }
		      try{
		         if(conexion!=null)
		        	 conexion.close();
		      	 }catch(SQLException se){
		         se.printStackTrace();
		      	 }//end finally try
		}
	}
	
	//Método que permite buscar actualizar los puntos de un jugador en la base de datos con un preparedStatement
	
	public void actualizarUsuarioPuntos(jugador jugador){
		try{
		  orden = conexion.createStatement();
		// create the java mysql update preparedstatement
	      String sql = "UPDATE player " +
                       "SET puntos = ? "+
	    		       "WHERE id = "+jugador.getId();
	      PreparedStatement preparedStmt = conexion.prepareStatement(sql);
	      preparedStmt.setInt(1, jugador.getScore());

	      // se ejecuta la consulta
	      preparedStmt.executeUpdate();
		}catch(SQLException se){
			      //Se produce un error con la consulta
			      se.printStackTrace();
		}catch(Exception e){
			      //Se produce cualquier otro error
			      e.printStackTrace();
		}finally{
		      //Cerramos los recursos
		      try{
		         if(orden!=null)
		        	 orden.close();
		      }catch(SQLException se){
		      }
		      try{
		         if(conexion!=null)
		        	 conexion.close();
		      	 }catch(SQLException se){
		         se.printStackTrace();
		      	 }//end finally try
		}		
	}
}

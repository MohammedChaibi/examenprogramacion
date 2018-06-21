package jugador;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Juego.Juego;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Djugador extends JPanel {

	//declaramos los textfields aqui 
	private JTextField tf_nombre;
	private JTextField tf_apellidos;
	private JTextField tf_genero;
	private JTextField tf_pais;
	private JTextField tf_edad;
	private Vprincipal principal;
	private JTextField tf_score;
	//y aqui las clases
	private login log;
	private Juego game;
	private jugador player;
	private Djugador referencia;
	

	//en el constructor le pasamos los objetos jugador Vprincipal y Juego
	public Djugador(jugador jugador, Vprincipal p,Juego juego) {
		//y aqui les damos el valor que pasamos dentro del constructor
		this.player = jugador;
		this.game=juego;
		this.referencia=this;
		
		
		
		setBounds(100, 100, 839, 640);
		setLayout(null);
		//aqui declaramos los jlabels
		JLabel lbl_logo = new JLabel("");
		lbl_logo.setBounds(83, 43, 70, 67);
		add(lbl_logo);
		
		JLabel lbl_nombre = new JLabel("Nombre");
		lbl_nombre.setBounds(220, 43, 77, 21);
		add(lbl_nombre);
		
		JLabel lbl_apellido = new JLabel("Apellidos");
		lbl_apellido.setBounds(220, 75, 77, 21);
		add(lbl_apellido);
		
		JLabel lbl_genero = new JLabel("Genero");
		lbl_genero.setBounds(220, 107, 77, 21);
		add(lbl_genero);
		
		JLabel lbl_pais = new JLabel("Pais");
		lbl_pais.setBounds(220, 139, 77, 21);
		add(lbl_pais);
		
		JLabel lbl_edad = new JLabel("Edad");
		lbl_edad.setBounds(220, 171, 77, 21);
		add(lbl_edad);
		
		JLabel lbl_score = new JLabel("Score");
		lbl_score.setBounds(220, 215, 77, 21);
		add(lbl_score);
		//aqui tenemos los campos de texto
		tf_nombre = new JTextField();
		tf_nombre.setBounds(295, 43, 155, 21);
		add(tf_nombre);
		tf_nombre.setColumns(10);
		
		tf_apellidos = new JTextField();
		tf_apellidos.setColumns(10);
		tf_apellidos.setBounds(295, 75, 155, 21);
		add(tf_apellidos);
		
		tf_genero = new JTextField();
		tf_genero.setColumns(10);
		tf_genero.setBounds(295, 107, 155, 21);
		add(tf_genero);
		 
		tf_pais = new JTextField();
		tf_pais.setColumns(10);
		tf_pais.setBounds(295, 139, 155, 21);
		add(tf_pais);
		
		tf_edad = new JTextField();
		tf_edad.setColumns(10);
		tf_edad.setBounds(295, 171, 155, 21);
		add(tf_edad);
		
		tf_score = new JTextField();
		tf_score.setColumns(10);
		tf_score.setBounds(295, 215, 155, 21);
		add(tf_score);
		//aqui le decimos que no deje editar el textfield
		tf_score.setEnabled(true);
		//aqui declaramos un boton para actualizar los atributos del jugador
		JButton btnActualizar = new JButton("Actualizar");
		//creamos el action listener
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//aqui tengo comentada la conexion al sql porque no me funciona
				//Sqlusuarios con=new Sqlusuarios();
				//instanciamos el jugador
				player = new jugador();
				//creamos unas variables para almacenar el contenido de los campos de texto
				String nombre = tf_nombre.getText();
				String apellido = tf_apellidos.getText();
				int edad = (Integer.parseInt(tf_edad.getText()));
				String pais = tf_pais.getText();
				String genero=tf_genero.getText();
				int score=(Integer.parseInt(tf_score.getText()));
				
				//aqui le pasamos los atributos recogidos a la clase jugador donde almacenara todos sus atributos
				player.setNombre(nombre);
				player.setApellidos(apellido);
				player.setEdad(edad);
				player.setPais(pais);
				player.setGenero(genero);
				player.setScore(score);
				game.setJugadorr(player);
				referencia.setJugador(player);
				
				//aqui comento la parte de actualizar el regustro en la base de datos porque no me va la base de datos
				/**if(con.actualizar(player)){
					JOptionPane.showMessageDialog(null, "Registro actualizado");
				}else{
					JOptionPane.showMessageDialog(null, "Error al actualizar");
				}*/
				
			}
		});
		btnActualizar.setBounds(301, 305, 130, 33);
		add(btnActualizar);
		
		//aqui llamamos al metodo setjugador que introducira los cambios de los atributos del jugador si se han realizado cambios
		setJugador(jugador);
			}
	
	//aqui creamos el metodo setjugador que insertara los nuevos atributos que saca de la clase jugador y los pone en los campos de texto
	public void setJugador(jugador jugador){
		this.player=jugador;
		tf_nombre.setText(jugador.getNombre());
		tf_edad.setText(String.valueOf(jugador.getEdad()));
		tf_apellidos.setText(jugador.getApellidos());
		tf_pais.setText(jugador.getPais());
		tf_genero.setText(jugador.getGenerotxt());
		tf_score.setText(String.valueOf(jugador.getScore()));
		
	}
	
	
}

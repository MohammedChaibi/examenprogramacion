package jugador;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Juego.Juego;
import Modelo.ConexionDB;
import Modelo.JugadorDB;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;


public class login extends JFrame {
//aqui decalramos todas las variables de los campos de texto labes y buttons y tambien las clases
	private JPanel contentPane;
	private JTextField txt_nombre;
	private JTextField txt_apellido;
	private JTextField txt_edad;
	private JTextField txt_pais;
	JRadioButton sex_hombre;
	JRadioButton sex_mujer;
	private ButtonGroup botones = new ButtonGroup();
	private JPasswordField txt_password;
	private JPasswordField txt_confpass;
	private login referencia;
	private Juego v_juego;
	private Djugador datos;
	private Vprincipal VP;
	private jugador player;
	//aqui creamos las clases para posteriormente poder conectarnos a la base de datos
	  private ConexionDB db;
      private Connection conexion; //Conexión
      private boolean connected =false; //Conexión con éxito

	//aqui tenemos el constructor del login
	public login() {
		//referencia es una variable que almacena esta misma clase para que se pueda acceder a ella desde dentro de botones etc
		this.referencia=this;
		player=new jugador();
		v_juego=new Juego(this,VP,player,datos);
		VP=new Vprincipal(referencia,player);
		setTitle("user login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 456, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//aqui estan los campos de texto y sus configuraciones
		txt_nombre = new JTextField();
		txt_nombre.setBounds(132, 36, 155, 20);
		contentPane.add(txt_nombre);
		txt_nombre.setColumns(10);
		
		txt_apellido = new JTextField();
		txt_apellido.setColumns(10);
		txt_apellido.setBounds(132, 92, 155, 20);
		contentPane.add(txt_apellido);
		
		txt_edad = new JTextField();
		//con este key listener queremos conseguir que solo se puedan intrudcir numero en el campo de texto edad
		//si se introduce otra cosa que no sean listener no lo insertara y sonara un "beep"
		txt_edad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char c = arg0.getKeyChar();
				if (!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_DELETE)){
					getToolkit().beep();
					arg0.consume();
				}
			}
		});
		txt_edad.setColumns(10);
		txt_edad.setBounds(132, 148, 155, 20);
		contentPane.add(txt_edad);
		
		txt_pais = new JTextField();
		txt_pais.setColumns(10);
		txt_pais.setBounds(132, 204, 155, 20);
		contentPane.add(txt_pais);
		
		txt_password = new JPasswordField();
		txt_password.setBounds(132, 260, 155, 20);
		contentPane.add(txt_password);
		
		txt_confpass = new JPasswordField();
		txt_confpass.setBounds(132, 307, 155, 20);
		contentPane.add(txt_confpass);
		//aqui estan los labels 
		JLabel lblNombre = new JLabel("NOMBRE");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setBounds(132, 11, 155, 14);
		contentPane.add(lblNombre);
		
		JLabel lblApellidos = new JLabel("APELLIDOS");
		lblApellidos.setHorizontalAlignment(SwingConstants.CENTER);
		lblApellidos.setBounds(132, 67, 155, 14);
		contentPane.add(lblApellidos);
		
		JLabel lblEdad = new JLabel("EDAD");
		lblEdad.setHorizontalAlignment(SwingConstants.CENTER);
		lblEdad.setBounds(132, 123, 155, 14);
		contentPane.add(lblEdad);
		
		JLabel lblPais = new JLabel("Pais");
		lblPais.setHorizontalAlignment(SwingConstants.CENTER);
		lblPais.setBounds(132, 179, 155, 14);
		contentPane.add(lblPais);
		//aqui tenemos el radio button para el sexo hombre
		sex_hombre = new JRadioButton("Hombre");
		botones.add(sex_hombre);
		sex_hombre.setBounds(145, 377, 109, 23);
		contentPane.add(sex_hombre);
		//y este es para el sexo mujer
		sex_mujer = new JRadioButton("Mujer");
		botones.add(sex_mujer);
		sex_mujer.setBounds(145, 403, 109, 23);
		contentPane.add(sex_mujer);
		//aqui creamos un button group para que solo se pueda seleccionar un sexo
		botones = new ButtonGroup();
		//squi le pasamos los botones que queremos añadir al grupo
		botones.add(sex_hombre);
		botones.add(sex_mujer);
		
		JLabel lblSexo = new JLabel("SEXO");
		lblSexo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSexo.setBounds(132, 349, 155, 14);
		contentPane.add(lblSexo);
		//este es el boto enviar o regustrar
		JButton btnEnviar = new JButton("enviar");
		//aqui esta el listener
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//instanciamos la clase jugador 
				 player = new jugador();
				//creamos las variable tipo string para recoger los datos de los campos de texto
				String pass = new String(txt_password.getPassword());
				String confPass = new String(txt_confpass.getPassword());
				String nombre = txt_nombre.getText();
				String apellido = txt_apellido.getText();
				int edad = (Integer.parseInt(txt_edad.getText()));
				String pais = txt_pais.getText();
				String nuevoPass = Hash.sha1(pass);
				
				
				//con este if creamos una condicion para asegurarnos de que se selecciona un sexo
				if(sex_hombre.isSelected() || sex_mujer.isSelected()){
				//aqui comprobamos si las contraseñas coincide si nos nombres estan vacios y si se ha insertado un numero
				//en vez de una letra
				if(pass.equals(confPass) && jugador.nombreValido(nombre) && jugador.edadValido(edad) && jugador.paisValido(pais)&& jugador.apellidoValido(apellido) && jugador.esNumero(nombre) && jugador.esNumero(apellido) && jugador.esNumero(pais) && referencia.passValido(pass) && referencia.confPassvalido(confPass))   {
					
					
					//aqui insertamos los datos recogidos de los campos de texto en la clase jugador
					player.setId(jugador.getNextId());
					player.setNombre(nombre);
					player.setApellidos(apellido);
					player.setEdad(edad);
					player.setPais(pais);
					player.setContraseña(nuevoPass);
						
					//con esta condicion vemos que sexo se ha seleccionado para luego insertar el string correspondiente
					//al sexo seleccionado en la clase de jugador
					if (sex_hombre.isSelected()){
						player.setGenero(jugador.GTHOMBRE);
					} else if (sex_mujer.isSelected()){
						player.setGenero(jugador.GTMUJER);
					}
					
					
					//instanciamos las clases juego y djugador que almacenan el juego y el perfl del jugadir
					v_juego=new Juego(referencia,VP,player,datos);
					datos=new Djugador(player,VP,v_juego);
					//le pasamos a los metodos setjugador del juego y perfil los datos recogidos de la clase  jugador
					v_juego.setJugadorr(player);
					datos.setJugador(player);
					VP=new Vprincipal(referencia,player);
					//instanciamos la clase de ventana principal y la hacemos visible
					
					VP.setVisible(true);
					//hacemos la ventana del login invisible
					referencia.setVisible(false);
					
					//inicializamos la conexion con la base de datos
					Conectar();
					//instanciamos la clase jugador y llamamos al metodo que insertara los atributos de la clase 
					//jugador en la base de datos
					JugadorDB udb=new JugadorDB(referencia.conexion);
					udb.insertarUsuario(player);
					//aqui haremos las comprobacions para saber si el login ha sido correcto o no y si no enviar el mensaje
					//de error correspondiente
				}else if (!jugador.nombreValido(nombre)){
					JOptionPane.showMessageDialog(null, "El campo nombre esta vacio");
					
				}else if (!jugador.edadValido(edad)){
					JOptionPane.showMessageDialog(null, "La edad es entre 3 y 200");
				}else if (!jugador.apellidoValido(apellido)){
					JOptionPane.showMessageDialog(null, "El campo apellido esta vacio");
				}
				else if (!jugador.paisValido(pais)){
					JOptionPane.showMessageDialog(null, "El campo Pais esta vacio");
				}else if (!jugador.esNumero(nombre)){
					JOptionPane.showMessageDialog(null, "No se permiten numeros en el campo nombre");
				}else if (!jugador.esNumero(apellido)){
					JOptionPane.showMessageDialog(null, "No se permiten numeros en el campo apellido");
				}else if (!jugador.esNumero(pais)){
					JOptionPane.showMessageDialog(null, "No se permiten numero en el campo pais");
				}else  if(!pass.equals(confPass)){
					
						JOptionPane.showMessageDialog(null, "Las contraseñas con coinciden");
					
				
					
					}else if(!referencia.passValido(pass)){
						JOptionPane.showMessageDialog(null, "La contraseña debe tener un minimo de dos caracteres");
					}
				}else{
					JOptionPane.showMessageDialog(null, "Seleccione su sexo");
				}
				
	
			}
		});
		btnEnviar.setBounds(156, 433, 89, 23);
		contentPane.add(btnEnviar);
		
		JButton btnSalir = new JButton("salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnSalir.setBounds(156, 467, 89, 23);
		contentPane.add(btnSalir);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasea.setBounds(132, 235, 155, 14);
		contentPane.add(lblContrasea);
		
		
		
		JLabel lblConfirmarContrasela = new JLabel("Confirmar Contrase\u00F1a");
		lblConfirmarContrasela.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirmarContrasela.setBounds(132, 291, 155, 14);
		contentPane.add(lblConfirmarContrasela);
		
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 440, 501);
		contentPane.add(label);
		
		
		
	}
	//con este metodo inicializamos la conecxion a la base de datos
    private void Conectar(){
        //Conexión con la BBDD
        //Creamos nuestro objeto para el manejo de la base de datos
        try{
            //en mi caso accedo a través de la dirección "localhost", a la BBDD "players", como "root" y password ""
           //En tu caso puede ser diferente
        	//aqui insertamos los datos para conectar a la base de datos instanciando la clase conexiondb
            db=new ConexionDB("usuarios.cquqckmqwbxn.us-east-2.rds.amazonaws.com:3306","usuarios","admin","mypasswordmypassword");
            //Establecemos la conexion
            connected=db.connectDB();
            //Asignamos con el getter la conexion establecida
            conexion=db.getConexion();
        }
        catch(Exception e)
        {
        System.out.println("hubo un problema al conectar con la base de datos");    
        }
    
    }
    //esto es para saber si los campos contraseña y confirmar contraseña estan vacios
	public static boolean passValido(String pass){
		return(!(pass.trim().isEmpty()));
	}
	public static boolean confPassvalido(String conf){
		return(!(conf.trim().isEmpty()));
	}
	
	
	
}

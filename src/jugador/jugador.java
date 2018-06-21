package jugador;

public class jugador {

	//aqui declaramos las propiedades del jugador
		private int id;
		private String nombre;
		private String apellidos;
		private String genero;
		private String pais;
		private String contraseña;
		private int edad;
		private int score;
		
		//contador para crear jugadores (id)
		private static int IDjugador=0;
		
		//Constantes para los maximos y minimos valores numericos
		public static final int MIN_ID = 1;
		public static final int MAX_ID = 999;
		public static final int MIN_EDAD = 3;
		public static final int MAX_EDAD = 120;
		public static final int MIN_SCORE = 0;
		public static final int MAX_SCORE = 999;
		
	
		
		// Constants for gender texts
		public static final String NOGENEROTXT = "";
		public static final String GTHOMBRE = "Hombre";
		public static final String GTMUJER = "Mujer";
		//este es el constructor en el llamamos al metodo init que nos inicializara los atributos del jugador
		public jugador() {
			init();
		}
		
		public void init() {
			this.id = 0;
			this.nombre = "";
			this.apellidos = "";
			this.edad = 0;
			this.score = 0;
			this.genero = NOGENEROTXT;
			
		}
		
		// incrementar el contador del id jugador
		public static int getNextId() {
			IDjugador++;
			return IDjugador;
		}
		
		// comprobar si la id es valida
		public static boolean idValido (int id) {
			if (id >= MIN_ID && id <= MAX_ID) {
				return true;
			} else {
				return false;
			}
		}
		
		//comprobar si nombre es valido
		public static boolean nombreValido (String nombre) {
			return (!(nombre.trim().isEmpty()));
		}
		
		//comprobar si apellido es valido
		public static boolean apellidoValido(String apellido) {
			return (!(apellido.trim().isEmpty()));
		}
		
		
		//comprobar si pais es valido 
		public static boolean paisValido(String pais){
			return (!(pais.trim().isEmpty()));
			
		}
		//comprobar si edad es valido
		public static boolean edadValido (int edad) {
			if (edad >= MIN_EDAD && edad <= MAX_EDAD) {
				return true;
			} else {
				return false;
			}
		}
		
		//comrobar si genero es valido
		public static boolean generovalido (String genero) {
			if (genero.equals(NOGENEROTXT) || 
				genero.equals(GTHOMBRE) ||
				genero.equals(GTMUJER)) {
				return true;
			} else {
				return false;
			}
		}
		
		//comprobar si nombre, apellido o pais tienen un numero
		public static boolean esNumero(String str){
			for (char c : str.toCharArray()){
				if(Character.isDigit(c))return false;
			}
			return true;
		}
		
		
		//aqui empezamos los getters y setters de cada propiedad del jugador
		public int getId(){
			return id;
		}
		
		public void setId (int id) {
			this.id = id;
		}
		public String getNombre(){
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		
		public String getApellidos() {
			return apellidos;
		}
		
		public void setApellidos(String apellido) {
			this.apellidos = apellido;
		}
		public String getPais(){
			return pais;
		}
		public void setPais(String pais){
			this.pais = pais;
		}
		
		public int getEdad() {
			return edad;
		}
		
		public void setEdad(int edad) {
			if (edad > 0) {
				this.edad = edad;
			}
			
		}
		public String getContraseña(){
			return contraseña;
		}
		public void setContraseña(String pass){
			this.contraseña = pass;
		}
		
		
		public int getScore () {
			return score;
		}
		
		public void setScore (int score) {
			if (score >= 0) {
				this.score = score;
			}
		}
		public String getGenero () {
			return genero;
		}
		public void setGenero (String genero) {
			if (genero.equals(NOGENEROTXT) || 
					genero.equals(GTHOMBRE) ||
					genero.equals(GTMUJER)) {
				this.genero = genero;
			}
		}
		
	
		public String getGenerotxt() {
			String generotxt="";
			switch (genero) {
				case (jugador.GTHOMBRE):
					generotxt = jugador.GTHOMBRE;
				break;
				case (jugador.GTMUJER):
					generotxt = jugador.GTMUJER;
				break;
				default:
					generotxt="";
					
			}
			return generotxt;
		}
}

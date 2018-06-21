package principal;

import java.awt.EventQueue;

import Juego.Juego;
import jugador.Djugador;

import jugador.jugador;
import jugador.login;
//esta es la clase principal que ejecutara el programa

public class principal {
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				//creamos el objeto login y lo ponemos en visible
				login Login=new login();
				Login.setVisible(true);
				
			}
			
		});
		
		

	}

}

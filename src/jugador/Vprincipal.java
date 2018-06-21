package jugador;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Juego.Juego;

import java.awt.CardLayout;
//esta es una ventana del tipo cardlayout que nos permetira cambiar entre ventanas sin abrir una nueva ventana
public class Vprincipal extends JFrame {
	//declaramos el jpanel de la cardloyoutae pane
	private JPanel cardLayoutPane;
	//declaramos las clases juego y djugador que es el perfil del jugaodr
	private Juego game;
	private Djugador perfil;
	
	//añadimos el objeto jugador
	private jugador player;
	private boolean tocajuego=true;
	
	//login
	private login Login;
	//declaramos unas variables estaticas que luego usaremos en al crear los contentpane de las clases juego y djugador
	private static final String V1="v1";
	private static final String V2="v2";
	//aqui creamos los menus necesarios
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmJuego;
	private JMenuItem mntmPerfil;
	


	
	//este es el constructor de la clase le pasamos los objetos login y jugadotr
	
	public Vprincipal(login Login,jugador player) {
		
		this.Login=Login;
		this.player=player;
		tocajuego=false;
		
		
		
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 945, 641);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		//aqui creamos los menus
		mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);
		mntmJuego = new JMenuItem("Juego");
		mnNewMenu.add(mntmJuego);
		mntmJuego.setEnabled(false);
		//aqui creamos el action listener del  boton del item del menu 
		mntmJuego.addActionListener(new ActionListener() {
			

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//aqui le ddecimos que el juego se ponga en visible y que el perfil se ponga en invisible
				//tambien le decimos que el item del menu juego se desactble y se active el boton del menu del perfil
				
				game.setVisible(true);
				perfil.setVisible(false);
				mntmJuego.setEnabled(false);
				mntmPerfil.setEnabled(true);
				
			}
		});
		
		
		
		mntmPerfil = new JMenuItem("Perfil");
		mnNewMenu.add(mntmPerfil);
		mntmPerfil.addActionListener(new ActionListener() {
			

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				perfil.setVisible(true);
				game.setVisible(false);
				mntmJuego.setEnabled(true);
				mntmPerfil.setEnabled(false);
				
			}
		});
		
		
		
		
		
		cardLayoutPane = new JPanel();
		cardLayoutPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(cardLayoutPane);
		cardLayoutPane.setLayout(new CardLayout(0, 0));
		
		//aqui instanciamos los objetos juego y perfil de las clases Juego y Djugador para añadir sus 
		//jpanels al cardloyout de esta clase y asi poder cambiar de una a otra
		game = new Juego(Login,this,player,perfil);
		cardLayoutPane.add(game, V1);
		
		perfil = new Djugador(player, this,game);
		cardLayoutPane.add(perfil, V2);
		
		
		
		
		
		
		
		
		
		
		
		
	}

}

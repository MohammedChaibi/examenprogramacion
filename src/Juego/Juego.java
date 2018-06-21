package Juego;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import jugador.Djugador;

import jugador.Vprincipal;
import jugador.jugador;
import jugador.login;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Juego extends JPanel {

	
	
	//private Djugador DatosJugador=new Djugador();
	
	

	//Array de dados 
	private ImageIcon[] dados3=new ImageIcon[3];
	private ImageIcon[] dados6=new ImageIcon[6];
	private ImageIcon[] dados12=new ImageIcon[12];
	private ImageIcon dadoGris;
	private Random dado=new Random();
	
	
	//Almacenamos los numeros aleatorios necesarios para despues realizar el calculo
	private int[] numerosAlmDados3=new int[3];
	private int[] numerosAlmDados6=new int[2];
	private int numerosAlmDados12;
	
	//Clase que almacena el jugador, datos y la referencia a la ventana principal y login
	private jugador player;
	private Djugador datos;
	private Vprincipal ventana;
	private login login;
	private Juego game;
	JLabel lbl_jugador;
	public JLabel lbl_puntuacion;
	
	//JLabel de las imagenes
	private JLabel dado1,dado2,dado3;
	private JLabel dado4,dado5;
	private JLabel dado6;
	
	
	//variables para  realizar el control del programa
	private boolean tocaNumero=true;
	private int operacion=0;
	private int numerosIntroducidos=0;
	private boolean esSuma=true;
	
	//botones 
	private JButton btn_Mdice,btn_mas,btn_menos,btn_repetir;
	
	//texfield del resultado
	private JTextField resultado;
	private JLabel lbl_resultado,lbl_resultCo;
	
	/**
	//el menu para elegir entre el juego o datos de jugador
	public JMenuBar menuBar_1;
	public JMenu Menu;
	public JMenuItem mntmJuego;
	
	
	*/
	
	//constructor
	public Juego(login Login,Vprincipal Ventana,jugador Jugador,Djugador perfil){
		this.login=Login;
		this.ventana=Ventana;
		this.player=Jugador;
		this.datos=perfil;
		this.game=this;
		setLayout(null);
		setBounds(100, 100, 954, 598);
		
		setLayout(null);
		
		resultado = new JTextField();
		resultado.setBounds(579, 159, 246, 29);
		add(resultado);
		resultado.setColumns(10);
		resultado.setEditable(false);
		dado1 = new JLabel("");
		dado1.setBounds(40, 21, 150, 150);
		add(dado1);
		
		dado2 = new JLabel("");
		dado2.setBounds(187, 21, 150, 150);
		add(dado2);
		
		dado3 = new JLabel("");
		dado3.setBounds(334, 21, 150, 150);
		add(dado3);
		
		dado4 = new JLabel("");
		dado4.setBounds(40, 165, 150, 150);
		add(dado4);
		
		dado5 = new JLabel("");
		dado5.setBounds(187, 165, 150, 150);
		add(dado5);
		
		dado6 = new JLabel("");
		dado6.setBounds(40, 330, 173, 173);
		add(dado6);
		
		btn_mas = new JButton("+");
		
		btn_mas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!tocaNumero){
					resultado.setText(resultado.getText()+"+");
					tocaNumero=true;
					esSuma=true;
				}
			}
		});
		btn_mas.setBounds(579, 97, 111, 23);
		add(btn_mas);
		
		btn_menos = new JButton("-");
		btn_menos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!tocaNumero){
					resultado.setText(resultado.getText()+"-");
					tocaNumero=true;
					esSuma=false;
				}
			}
		});
		btn_menos.setBounds(720, 97, 111, 23);
		add(btn_menos);
		
		btn_Mdice = new JButton("MATHDICE");
		btn_Mdice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				player=new jugador();
				datos=new Djugador(player,ventana,game);
				if(numerosIntroducidos>1){
					
					
					btn_repetir.setEnabled(true);
					
					
					
						if((numerosAlmDados12+1)==operacion){
						
						
						
						//marcar que el resultado es correcto
						lbl_resultCo.setText("Resultado Correcto");
						//desactivamos boton mathdice para no sumar mas puntos
						
						player.setScore(player.getScore()+100);
						lbl_puntuacion.setText(String.valueOf(player.getScore()));
						
						int score=(Integer.parseInt(lbl_puntuacion.getText()));	
						String nombre=(lbl_jugador.getText());
						
						player.setScore(score);
						player.setNombre(nombre);
						
						
						game.setJugadorr(player);
						datos.setJugador(player);
						btn_Mdice.setEnabled(false);
					}else{
						lbl_resultCo.setText("Resultado incorrecto");
						//desactivamos el boton mathdice para forzar repetir
						btn_Mdice.setEnabled(false);
					}
					
					
				}
			}
		});
		btn_Mdice.setBounds(579, 199, 246, 43);
		add(btn_Mdice);
		
		btn_repetir = new JButton("Repetir");
		btn_repetir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				inibotones();
				btn_Mdice.setEnabled(true);
				btn_repetir.setEnabled(false);
			}
		});
		btn_repetir.setBounds(579, 272, 246, 43);
		add(btn_repetir);
		
		lbl_jugador = new JLabel("");
		
		lbl_jugador.setBounds(579, 21, 246, 23);
		add(lbl_jugador);
		
		lbl_puntuacion = new JLabel("");
		lbl_puntuacion.setBounds(579, 55, 246, 23);
		add(lbl_puntuacion);
		
		lbl_resultado = new JLabel("");
		lbl_resultado.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_resultado.setBounds(563, 519, 295, 29);
		add(lbl_resultado);
		
		lbl_resultCo = new JLabel("");
		lbl_resultCo.setBounds(579, 330, 285, 173);
		add(lbl_resultCo);
		
		
		inibotones();
		setJugadorr(player);
		
	}
	
	public void setJugadorr(jugador Jugador){
		this.player=Jugador;
		lbl_jugador.setText(Jugador.getNombre());
		lbl_puntuacion.setText(String.valueOf(Jugador.getScore()));
		
		
	}

	
	
	
	
	private void setOperacion(int num){
		numerosIntroducidos++;
		if(numerosIntroducidos>1){
			if(esSuma) operacion=operacion+num;
			else operacion=operacion-num;
			
		}else{
			operacion=num;
		}
	}
	
	
	public void inibotones(){
		//metemos las imagenes en las arrays
		for (int i=0; i<dados3.length; i++){
			dados3[i]=new ImageIcon(getClass().getResource("../imagenes/dado"+String.valueOf(i+1)+"_3.png"));
		}
		for (int i=0;i<dados6.length;i++){
			dados6[i]=new ImageIcon(getClass().getResource("../imagenes/dado"+String.valueOf(i+1)+"_6.png"));
		}
		for (int i=0;i<dados12.length;i++){
			dados12[i]=new ImageIcon(getClass().getResource("../imagenes/dadodoce_"+String.valueOf(i+1)+".png"));
		}
		dadoGris=new ImageIcon(getClass().getResource("../imagenes/dadogris.png"));
		//ahora vamos a colocar los dados de 3 caras
		for (int i=0;i<numerosAlmDados3.length;i++)
		//para llamar al metodo random que guardamos en dado hacemos esto para que ponga las imagenes de forma aleatoria
		numerosAlmDados3[i]=dado.nextInt(3);
			dado1.setIcon(dados3[numerosAlmDados3[0]]);//con el seticon insertamos las imagenes a los labels
			dado1.setName("1");
			dado2.setIcon(dados3[numerosAlmDados3[1]]);
			dado2.setName("2");
			dado3.setIcon(dados3[numerosAlmDados3[2]]);
			dado3.setName("3");
			//ahora añadimos los mouse listeners para saber cuando se hace click en cada jlabel
			dado1.addMouseListener(new ListenerDados());
			dado2.addMouseListener(new ListenerDados());
			dado3.addMouseListener(new ListenerDados());
			
		for (int i=0;i<numerosAlmDados6.length;i++)
		numerosAlmDados6[i]=dado.nextInt(6);
			dado4.setIcon(dados6[numerosAlmDados6[0]]);
			dado4.setName("4");
			dado5.setIcon(dados6[numerosAlmDados6[1]]);
			dado5.setName("5");
			dado4.addMouseListener(new ListenerDados());
			dado5.addMouseListener(new ListenerDados());
			
			numerosAlmDados12=dado.nextInt(12);
			dado6.setIcon(dados12[numerosAlmDados12]);
			//RESETEAMOS CAJAS
			resultado.setText("");
			
			
			tocaNumero=true;
			operacion=0;
			numerosIntroducidos=0;
			esSuma=true;
			
			
			
	}
	
	private class ListenerDados implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			
			JLabel dado=(JLabel)e.getSource();
			int numeroDado=Integer.valueOf(dado.getName());
			
			if (tocaNumero){
				if(numeroDado<4)
					resultado.setText(resultado.getText()+String.valueOf(numerosAlmDados3[numeroDado-1]+1));
					
					
					
				else 
					resultado.setText(resultado.getText()+String.valueOf(numerosAlmDados6[numeroDado-4]+1));
					
				
				dado.removeMouseListener(this);
				dado.setIcon(dadoGris);
				tocaNumero=false;
				
				if(numeroDado<4)
					setOperacion(numerosAlmDados3[numeroDado-1]+1);
				else
					setOperacion(numerosAlmDados6[numeroDado-4]+1);
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}

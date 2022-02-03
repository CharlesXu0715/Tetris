import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FenetreTetris extends JFrame implements KeyListener{

	private Audio musique;
	private Audio sonTourne;
	private Audio sonTranslate;
	private Audio sonPerdu;
	
	private JPanel mainPanel;

	private plateau p;
	private JPanel panelDeScore; 
	private JPanel plateauDeJeu;
	private boolean perdu;
	private JPanel panelDroite;
	private JPanel panelPieceSuivante;
	private boolean pause;
	private int temps;
	private int dizaineLigne;
	private PanelPause panelPause;
	private boolean son;
	private int fois;
	
	public FenetreTetris(){
		musique= new Audio("son/Tetris.wav");
		musique.jouerBoucle();
		
		sonPerdu= new Audio("son/perdu.wav");
		sonTranslate= new Audio("son/line.wav");
		sonTourne= new Audio("son/selection.wav");
		
		p = new plateau();
		perdu=p.getPerdu();
		
		pause=false;
		
		son = true;
		
		temps=1000;
		
		fois=0;
		
		dizaineLigne=0;
		
		this.setTitle("Mini-projet - Tetris");
		this.setSize(600,600);
		this.setLocation(300,200);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addKeyListener(this);
		
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(0,0,0)); // fond du panel écran de couleur gris 
		mainPanel.setLayout( new FlowLayout() ); // utilisation d'un FlowLayout comme layout manager
		
		this.add(mainPanel);
		
	// Panel dans lequel il y aura la représenation du jeu
		plateauDeJeu = new PanelJeu(p); 
		plateauDeJeu.setPreferredSize( new Dimension( this.getHeight()/2 , this.getHeight() ) ); 
		
		panelDroite = new JPanel();// Utilise par défaut un BorderLayout
		panelDroite.setBackground(new Color(214,136,82));
		panelDroite.setPreferredSize(new Dimension((int)(this.getHeight()/3.5),this.getHeight()));
		
		mainPanel.add(plateauDeJeu);
		mainPanel.add(panelDroite);
	
	// Panel contenant le score et le nombre de lignes complétées
		panelDeScore = new PanelScore(p); 
		panelDeScore.setPreferredSize(new Dimension((int)(this.getHeight()/3), this.getHeight()/3 ));
		
	// Panel contenant une representation de la pièce suivante
		panelPieceSuivante = new PanelPieceSuivante(p); 
		panelPieceSuivante.setPreferredSize(new Dimension((int)(this.getHeight()/3), this.getHeight()/3 )); 
		
	// Panel indiquant lorsque l'état du jeu est en pause et lorsqu'on a perdu
		panelPause = new PanelPause(); 
		panelPause.setPreferredSize(new Dimension((int)(this.getHeight()/3), this.getHeight()/3 )); 
		
		panelDroite.add(panelDeScore);
		panelDroite.add(panelPieceSuivante);
		panelDroite.add(panelPause);
	
	// Méthode permettant d'ajuster correctement la taille de la fenêtre en fonction des composants qu'elle contient
		this.pack();
		this.setVisible(true);
	// Timer qui s'active en fonction de la valeur de la variable temps
		new Timer(temps, new ActionListener(){ 
		
		/***
		 * Méthode qui permet de proposer au joueur l'option d'augmenter la vitesse de la chute des blocs.
		 * Cette option du jeu n'est appliquée que toutes les 10 lignes éliminées, au bout d'un certain temps. 
		 * La viariable "temps" est alors mise à jour, et c'est ce qui permet d'augmenter la difficulté du jeu.
		 * Cette condition ne s'active pas lorsque le jeu est en pause, 
		 * Cette ne s'active que lorsque la partie n'est pas perdue, alors le bloc tombe.
		 ***/
            public void actionPerformed(ActionEvent e) {	
				if( p.getNbrLigne() >= dizaineLigne+10) { 
						dizaineLigne+=10;
						if(temps>200){
							temps=temps-(int)(Math.pow(2.5,dizaineLigne/10)); 
						}
						System.out.println(temps);
				}
				
				if( pause == false ) { 
					perdu=p.getPerdu();
					if( !perdu ){ 
						p.fall();	
					}
				}
				if(perdu){
					panelPause.setPerdu(true);
					if(fois==0){
						fois+=1;
						musique.arreter();
						sonPerdu.jouer();
					}
				}
				repaint();
            }
        }).start();
        
        p.nouveau();
		
	}
	/***
	 * Après avoir mis des écouteur sur nos touches de clavier, nous allons associer des actions à excecuter aux touches qui nous intéressent.
	 * Dans notre cas, les touches directionnelles qui vont appeler d'autres fonctions lorsqu'on appuie dessus. 
	 * Nous utilisons ce type de méthode pour recréer le principe d'une console avec les touches directionnelles du clavier qui vont donc diriger la conduite des blocs.
	 * Nous utilisons aussi la touche "échap" pour la mise en pause et la réinitialisation de la partie.
	 * Enfin, la touche "S" pour "son" nous permettra de mettre en marche la musique ou de l'arrêter.
	 ***/
	public void keyPressed(KeyEvent e){
		if (pause ==false ){
			if (e.getKeyCode() == KeyEvent.VK_LEFT){
				if(son==true){
					sonTranslate.jouer();
				}
				p.gauche();
				repaint();
			}
			else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
				if(son==true){
					sonTranslate.jouer();
				}
				p.droite();
				plateauDeJeu.repaint();
			}
			else if (e.getKeyCode() == KeyEvent.VK_UP){
				if(son==true){
					sonTourne.jouer();
				}
				p.tourner();
				repaint();
			}
			else if (e.getKeyCode() == KeyEvent.VK_DOWN){
				p.fall();
				repaint();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE && perdu == false){ 
			pause=!pause;
			panelPause.setPause(pause);
		} else if(e.getKeyCode() == KeyEvent.VK_ESCAPE && perdu == true  ){
			p.reinitialiser();
			fois=0;
			dizaineLigne=0;
			panelPause.setPerdu(false);
			musique.jouerBoucle();
		}
		if(e.getKeyCode() == KeyEvent.VK_S){
			if(son==true){
				musique.arreter();
			} else {
				musique.jouerBoucle();
			}
			son=!son;
			p.setSon(son);
		}
	}

    public void keyReleased(KeyEvent e) {
   
         
    }
 
 

    public void keyTyped(KeyEvent e) {
       
         
    }

}



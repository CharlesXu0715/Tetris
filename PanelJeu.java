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

public class PanelJeu extends JPanel {
	
	public plateau p;

	
	public PanelJeu( plateau p ){
		this.p = p;
	}
    
    /***
     * La méthode paint de cette classe nous permet de travailler sur la fenetre de notre jeu, et plus précisément sur le plateau de jeu.
     * Selon l'organisation de notre tableau:
     * - Les emplacements qui contiennent des cases des bordures et les blocs qui ne tombent plus (déjà placés dans le bas du plateau) sont colorés en noir.
     * - Les emplacement qui correspondent aux pièces mobiles qui sont en train de tombées sont colorées en bleu.
     * - Les emplacements qui ne contiennent pas de pièces ni ne font partie de la bordure sont en marron clair (144,89,51).
     ***/
   public void paint ( Graphics g){
		int[][] tab=p.getTableau();
		int c=0;
		for( int i=0; i<tab.length; i++){
			for( int j=0; j<tab[i].length; j++){
				c=Math.abs(tab[i][j]);
				/*if( tab[i][j] == 0 ){
					g.setColor( new Color(144,89,51));
				}
				
				else if( tab[i][j] == 1 ){
					g.setColor(Color.black);
				}
				
				else if( tab[i][j] == 2 ){
					g.setColor(Color.blue);
				}*/
				switch (c)
				{
					case 0:
					g.setColor(new Color(144,89,51));
					break;
					case 1:
					g.setColor(new Color(143,188,143));
					break;
					case 2:
					g.setColor(new Color(50,205,50));
					break;
					case 3:
					g.setColor(new Color(205,105,180));
					break;
					case 4:
					g.setColor(new Color(20,33,189));
					break;
					case 5:
					g.setColor(new Color(138,20,189));
					break;
					case 6:
					g.setColor(new Color(189,20,66));
					break;
					case 7:
					g.setColor(new Color(255,215,0));
					break;
				}
				g.fillRect( (this.getHeight()/20)*j , (this.getHeight()/20)*i , (this.getHeight()/20), (this.getHeight()/20));
				g.setColor(Color.black);
				g.drawRect( (this.getHeight()/20)*j , (this.getHeight()/20)*i , (this.getHeight()/20), (this.getHeight()/20));
				
			}
		}
	}
}

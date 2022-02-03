import java.awt.Font;
import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.*;

public class PanelPause extends JPanel{
	
	private Color couleur = new Color(57,70,116);
	private boolean pause;
	private boolean perdu;
	
	public PanelPause(){
		pause=false;
		perdu=false;
	}
	/***
	 * Dans cette méthode paint, nous travaillons sur le graphisme de notre fenêtre de jeu. 
	 * La menu sur la droite proposera et expliquera les fonctionnalités suivantes: 
	 * - Mettre le jeu en pause ou le reprendregrâce à la touche échap, 
	 * - Mettre le son en pause ou le reprendre avec la touche s
	 * Lorsque le jeu est en pause, un message est affiché pour signaler l'état du jeu.
	 ***/
	public void paint ( Graphics g){
		g.setColor(couleur);
		if(pause){
			g.setFont(new Font("TimesRoman", Font.BOLD, 40)); 
			g.drawString("PAUSE !",20,100);
		} else if (perdu) {
			g.setFont(new Font("TimesRoman", Font.BOLD, 40)); 
			g.drawString("PERDU !",20,100);
			g.setFont(new Font("TimesRoman", Font.BOLD, 12)); 
			g.drawString("appuyez sur echap pour",20,140);
			g.drawString("commencer une nouvelle partie",20,160);
		} else {
			g.setFont(new Font("TimesRoman", Font.BOLD, 15)); 
			g.drawString("Appuyez sur echap pour ",20,100);
			g.drawString("mettre la partie en pause.",20,115);
			g.drawString("Appuyez sur s pour ",20,145);
			g.drawString("desactiver ou activer ",20,160);
			g.drawString("le son",20,175);
		}
	}
	
	public void setPause(boolean p){
		this.pause=p;
	}
	
	public void setPerdu(boolean p){
		this.perdu=p;
	}
}

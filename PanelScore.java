import java.awt.Font;
import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Color;

public class PanelScore extends JPanel {
	
	private plateau p;
	private Color couleur = new Color(41,85,112);
	private int score;
	private int ligne;
	
	public PanelScore( plateau p ){
		
		this.p = p;//initialise plateau pour pouvoir obtenir le score
		score=0;
		ligne=0;

	}
	/***
	 * Nous travaillons sur le graphisme de notre fenêtre de jeu.
	 * La barre d'état sur le côté droit indique et met à jour le score et le nombre de lignes éliminées tout au long du jeu.
	 ***/
	public void paint ( Graphics g){
		g.setColor(couleur);
		g.setFont(new Font("TimesRoman", Font.BOLD, 20)); // on choisit la police, le style de la police et sa taille
		score=p.getScore();
		g.drawString("Score : "+String.valueOf(score),20,20);
		ligne=p.getNbrLigne();
		g.drawString("Ligne : "+ligne,20,80);
	}
}

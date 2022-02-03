import java.awt.Font;
import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Color;

public class PanelPieceSuivante extends JPanel {
	
	private plateau p;
	private Color couleur = new Color(41,85,112);
	private Bloc pieceSuivante;
	private int x;
	private int y;
	private int cote;
	private int[] chemin;
	
	public PanelPieceSuivante( plateau p ){
		
		this.p = p;
		this.cote=30;
	}
	/***
	 * Dans la méthode paint de cette classe, nous travaillons sur le graphique de notre interface de jeu.
	 * La barre de droite qui accompagne notre plateau présente l'état du jeu avec l'annonce de la pièce suivante qui rentrera dans le plateau.
	 * Nous avons donc permis l'affichage de la pièce suivante à l'aide de la méthode paint.
	 * Pour appeler la pièce suivante: chaque pièce est un objet de type bloc. Ainsi, il a pour propriété son "chemin" qui lui permet d'avoir une forme fixe.
	 * Le chemin est un tableau qui contient la composition 2D d'un bloc en terme valeur d'un tableau 1D à l'aide de valeur prédéfinies. Nous utilisons la méthode "positionne" pour l'exploiter.
	 ***/
	public void paint ( Graphics g){
		pieceSuivante=p.getPieceSuivante();
		
		int type = pieceSuivante.typeBloc;
		
		x=75;
		y=90;
		g.setColor(couleur);
		g.setFont(new Font("TimesRoman", Font.BOLD, 20)); 
		g.drawString("Prochaine piece : "+pieceSuivante.typeBloc,20,20);
		g.setColor(choixCouleur(type));
		g.fillRect(x,y,cote,cote);
		g.setColor(Color.black);
		g.drawRect(x,y,cote,cote);
		
		int[] chemin = pieceSuivante.getCheminInitial();
		
		for(int i=pieceSuivante.centre-2; i>-1; i--){
			positionne(chemin[i]);
			g.setColor(choixCouleur(type));
			g.fillRect(x,y,cote,cote);
			g.setColor(Color.black);
			g.drawRect(x,y,cote,cote);
		}
		
		x=75;
		y=90;
		
		for(int i=pieceSuivante.centre-1; i<chemin.length; i++){
			positionne(chemin[i]);
			g.setColor(choixCouleur(type));
			g.fillRect(x,y,cote,cote);
			g.setColor(Color.black);
			g.drawRect(x,y,cote,cote);
		}
	}

/***
 * La méthode positionne permet de traduire nos valeurs réelles dans le tableau chemin en positions sur un quadrillage 2D, dons un tableau 2D.
 * Nous avons assigné une valeur à chaque direction (8 directions cardinales au total) en fonction d'un centre que nous derterminons pour chaque objet différent (nos 7 différents blocs).
 * Graphiquement, un bloc est constitué de 4 carrés agencés de manière à créer une pièce.
 * Chaque chemin bloc est constitué de 3 valeurs qui reprennent la position de 3 carrés en fonction du centre, lui-même un carré.
 * Ainsi à l'aide du codage des directions, nous pouvons placer un premier carré qui est le centre, et les trois autres autour de celui-ci, formant ainsi une pièce.
 ***/
	private void positionne(int c){
		switch(c){
			case 1 :
			this.y-=cote;
			break;
			case 2 :
			this.y-=cote;
			this.x+=cote;
			break;
			case 3 :
			this.x+=cote;
			break;
			case 4 :
			this.y+=cote;
			this.x+=cote;
			break;
			case 5 :
			this.y+=cote;
			break;
			case 6 :
			this.y+=cote;
			this.x-=cote;
			break;
			case 7 :
			this.x-=cote;
			break;
			case 8 :
			this.y-=cote;
			this.x-=cote;
			break;
		}
	}
	
	private Color choixCouleur(int c){
		switch (c)
				{
					case 0:
					return new Color(144,89,51);
					
					case 1:
					return new Color(143,188,143);
					
					case 2:
					return new Color(50,205,50);
					
					case 3:
					return new Color(205,105,180);
					
					case 4:
					return new Color(20,33,189);
					
					case 5:
					return new Color(138,20,189);
					
					case 6:
					return new Color(189,20,66);
					
					case 7:
					return new Color(255,215,0);
					
				}
		return Color.black;
	}
}

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

public class FenetreMenu extends JFrame implements ActionListener{
// Les attributs
		private JLabel affLogo;
		private JLabel TexteExplications;
		private JButton demarrage;
		private JButton explications;
		private FenetreTetris f;
	
	public FenetreMenu(){
		
		this.setTitle("Menu de jeu"); 
		this.setSize(400,400);
		this.setLocationRelativeTo(null);//Permet de centrer la fenêtre
		this.setLocation(300,200);
		this.setLayout(null);// Permet de placer nos composants dans la fenêtre
	
	// JLabel dans lequel va se trouver le logo de notre jeu (image png)
		affLogo = new JLabel(new ImageIcon("image/logo2.png")); 
		affLogo.setBounds(0,130,390,100);
		
	// JButton qui permet de faire apparaître les consignes du jeu
		explications = new JButton("Besoin d'explications ?");
		explications.setBounds(125,260,150,50);
		explications.setBackground(Color.gray);
		explications.addActionListener(this);
		
	// JButton pour passer à la fenêtre de jeu
		demarrage = new JButton("Commencer !");
		demarrage.setBounds(120,50,150,50);
		demarrage.setBackground(Color.green);
		demarrage.addActionListener(this);
		
		this.add(demarrage);
		this.add(explications);
		this.add(affLogo);
		
		this.setVisible(true);
		
		
		
	}
	/***
	 * Après avoir appuyé sur le boutton "demarrage", la fenetre "Menu du jeu" se ferme grâce à "this.dispose();" et la fenêtre de jeu s'ouvre
	 * Après avoir appuyé sur le boutton "explications" la fenêtre actuelle de "Menu du jeu" se ferme grâce à "this.dispose();", puis elle est mise à jour avec un nouveau JLabel, puis elle se ré-ouvre.
	 ***/ 
	public void actionPerformed (ActionEvent e){
		if (e.getSource()== demarrage){
			this.dispose();
			f = new FenetreTetris();
            f.setVisible(true);
		}
		if (e.getSource()== explications){
			this.dispose();
			this.setSize(400,550);
			TexteExplications = new JLabel("Alors, comment ça marche ?"); 
			TexteExplications.setBounds(50,305,300,200);
			TexteExplications.setBackground(Color.white);
			TexteExplications.setText("<html><body>Vous allez jouer au jeu " + "<font size=\"+1\">Tetris</font> ! Ce jeu consiste \u00E0 organiser des pi\u00E8ces de formes al\u00E9atoires pour former des lignes horizontales pleines alors que les pi\u00E8ces sont en train de tomber. Lorsqu'une ligne est pleine, alors celle-ci dispara\u00EEt et vous gagner des points. Lorsque vos pi\u00E8ces atteignent le plafond, alors vous avez perdu. Pour jouer appuyez sur la fl\u00E8che du haut pour tourner la pi\u00E8ce, sur les fl\u00E8ches gauches et droites pour translater la pi\u00E8ce sur la gauche et la droite, enfin appuyez sur la fl\u00E8che du bas si vous souhaitez faire tomber la pi\u00E8ce plus rapidemment.");      
            this.add(TexteExplications);
            this.setVisible(true);
        }
	}
}


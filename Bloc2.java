public class Bloc2 extends Bloc {
	private int[] chemin;
	private int[] cheminInitial;

	// Organisation initiale de notre pièce Bloc2
	public Bloc2(){
		super(2, 1);
		int[] c = {3,1,7};
		this.chemin = c;
		
		int[] c2 = {3,1,7};
		this.cheminInitial=c2;
	}
	
	// Méthode qui lui permet de tourner à 90° en gardant sa forme
	public void rotation(){
	}

	// Retourne en organisation actuelle, l'oganisation initiale des carrés autour du centre de la pièce
	public void reinitialiser(){
		chemin = cheminInitial;	
	}

	// Récupère le l'oganisation actuelle des carrés autour du centre de la pièce
	public int[] getChemin(){
		return chemin;
	}
	
	// Récupère le l'oganisation initiale des carrés autour du centre de la pièce
	public int[] getCheminInitial(){
		return cheminInitial;
	}
	
	// Récupère les coordonées du centre de la pièce
	public int[] getPos(){
		int[] pos = {1,4};
		return pos;
	}
}

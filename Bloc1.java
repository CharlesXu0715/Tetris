public class Bloc1 extends Bloc {
	private int[] chemin;
	private int[] cheminInitial;
	
	
	// Organisation initiale de notre pièce Bloc1
	public Bloc1(){
		super(1, 2);
		int[] c = {7,3,3};
		this.chemin = c;
		
		int[] c2 = {7,3,3};
		this.cheminInitial=c2;
	}
	// Méthode qui lui permet de tourner à 90° en gardant sa forme
	public void rotation(){
		for(int i=0; i<chemin.length; i++){
			if(chemin[i]+2 >= 9){ 
				chemin[i]=chemin[i]-6;
			}
			else{ 
				chemin[i]+=2; 
			}
		}
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
		int[] pos = {0,5};
		return pos;
	}
}

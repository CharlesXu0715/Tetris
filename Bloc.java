public abstract class Bloc {
	public int typeBloc;
	public int centre;

	/***
	* Nous créons notre classe mère dans laquelle nous introduisons les propriétés que nos objets vont présenter.
	* La méhode "rotation" pour permettra de tourner la pièce par rapport à son centre.
	* La méthode "réinitialiser" nous permettra d'afficher notre pièce dans l'organisation initiale que nous définissions lors de la création.
	* La méthode getChemin nous permettra de récupérer de l'organisation la pièce: position des carrés en fonction du centre.
	* Ex: la barre de 4 carrés peut être affichée verticalement ou horizontalement, alors elle aura un Chemin différent. Ainsi par rapport au centre, la position des carrés change.
	* La méthode getCheminInitial nous permettra de récupérer son organisation de création.
	* La méthode getPos récupère la position du centre.
	***/
	public Bloc (int typeBloc, int centre){
		this.typeBloc = typeBloc;
		this.centre = centre;

	}
	
	public abstract void rotation();
	
	public abstract void reinitialiser();
	
	public String toString(){
		return "Le bloc est le bloc numero "+typeBloc;
	}

	public abstract int[] getChemin();
	
	public abstract int[] getCheminInitial();
	
	public abstract int[] getPos(); // coordonnées du centre de la pièce dans le tableau

}

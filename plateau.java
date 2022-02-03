import java.util.ArrayList;

public class plateau{
	private ArrayList<Bloc> listeBloc=new ArrayList<Bloc>();
	private Bloc1 b1 = new Bloc1();
	private Bloc2 b2 = new Bloc2(); 
	private Bloc3 b3 = new Bloc3();
	private Bloc4 b4 = new Bloc4(); 
	private Bloc5 b5 = new Bloc5(); 
	private Bloc6 b6 = new Bloc6(); 
	private Bloc7 b7 = new Bloc7();  
	private int score;
	private int[][] tableauPlateau;
	private Bloc pieceCourante;
	private Bloc pieceSuivante;
	private int alea;
	private int x0; //centre
	private int y0;
	private int num; //num de centre (1-4)
	private int[] d;
	private int[][] pos;
	private boolean perdu;
	private int nbrLigne;
	private Audio sonTomber;
	private Audio sonEffacer; 
	private boolean son;
	private int couleur;
	
// Constructeur de la classe plateau 
	public plateau(){ 
		
		sonTomber= new Audio("son/fall.wav");
		sonEffacer= new Audio("son/clear.wav");
		
		perdu=false;
		
		son=true;
		
		listeBloc.add(b1);
		listeBloc.add(b2);
		listeBloc.add(b3);
		listeBloc.add(b4);
		listeBloc.add(b5);
		listeBloc.add(b6);
		listeBloc.add(b7);
		
		nbrLigne=0;
		tableauPlateau=new int[20][10];	//0-19,0-9
		//dans la tableau, Un nombre positif représente la carre fixe,
		//tandis qu'un nombre négatif représente le bloc en mouvement
		//et 0 indique que la carre est vide.
		x0=0;	
		y0=0;
		num=0;	
		d=new int[3]; //direction (1-8)
		pos=new int[4][2];	//positions des cubes, 4 car il y a 4 cubes, et 2 pour les positions x ( 0 ) & y ( 1 )
		for (int i=0;i<20;i++)
		{
			for (int j=0;j<10;j++)
			{
				tableauPlateau[i][j]=0;
			}
		}
		alea = (int)(Math.random()*7);
		pieceSuivante=listeBloc.get( alea );
	}

	/***
	 * Méthode qui insère une nouvelle pièce dans le plateau
	 * Elle permet de déterminer une pièce aléatoire entre la pièce 1 et la pièce 7 avec la fonction Math.random()*7 qui nous donnera un nombre entier entre 0 et 6, donc 7 valeurs au total.
	 ***/
	public void nouveau(){ 

		int alea = (int)(Math.random()*7);

		listeBloc.get( alea ).reinitialiser();
		
		pieceCourante=pieceSuivante;
		couleur=pieceCourante.typeBloc;	//obtenir couleur de la piece courante
		
		
		pieceSuivante=listeBloc.get( alea );
		
		num=pieceCourante.centre;
		d=pieceCourante.getChemin();
		
		int[]posCentre=pieceCourante.getPos();
		
		x0=posCentre[0];
		y0=posCentre[1];
		placer(false);
	}
	
/***
 * Méthode permettant de changer le score
 * Le calcul du score se fait en fonction du nombre de lignes éliminées silmutanément
 ***/
	public void score(int nbrLigne){ 
		if (nbrLigne>0)
		{
			score+=10*Math.pow(3,nbrLigne);
		}
	}
	
/***
 * Méthode qui permet de détecter d'éventuelles lignes pleines, et d'actualiser le tableauPlateau en conséquence
 * Une première boucle sert à déterter les lignes pleines.
 * Si les lignes sont pleines alors le tableau de jeu est mis à jour.
 ***/
	public void clean(){ 
		int lignePleine=0;
		boolean j= true;
		int i=tableauPlateau.length-1;	
		
		while(i >- 1){
			j=true;
			
			for( int k=0; k<tableauPlateau[i].length; k++){
				if(tableauPlateau[i][k]==0){
					j=false;
					i--;
					break;
				}
			}
			if(j==true){
				lignePleine+=1;

				for( int k=0; k<tableauPlateau[i].length; k++){
					tableauPlateau[i][k]=0;
				}
				
				
				for(int k=i; k>0; k--){//boucles qui mettent à jour le tableau
					for(int l=0; l<tableauPlateau[k].length; l++){
						tableauPlateau[k][l]=tableauPlateau[k-1][l];
					}
				}
				for(int l=0; l<tableauPlateau[i].length; l++){
					tableauPlateau[0][l]=0;
				}
			}
		}
		if(lignePleine>0){
			if(son==true){
					sonEffacer.jouer();
				}
		}
		nbrLigne+=lignePleine;
		score(lignePleine);

	}
	
	/*** 
	 * Méthode qui translate la pièce courante vers le bas
	 * Une fois que la pièce courante ne peut plus translater vers le bas, alors elle devient une pièce "figée"
	 ***/
	public void fall() 
	{
		if (detecter(5))
		{
			x0++;
			placer(true);
		}
		else
		{
			if(son==true){
					sonTomber.jouer();
				}
			
			if(detecter(1)){
				int x,y;
				for (int i=0;i<4;i++)// boucle qui permet de transformer la pièce courante en pièce "figée"
				{
					x=pos[i][0];
					y=pos[i][1];
					tableauPlateau[x][y]*=-1;
					pos[i][0]=0;
					pos[i][1]=0;
				}
				clean();
				nouveau();
			}
			else{
				this.perdu=true;
			}
			
		}
	}

	/***
	* Méthode permettant de translater la pièce courante vers la gauche 
	***/
	public void gauche() 
	{
		if (detecter(7))
		{
			//tableauPlateau[x0][y0]=0;
			y0--;
			placer(true);
		}
	}
	
	/***
	* Méthode permettant de translater la pièce courante vers la gauche 
	***/
	public void droite()
	{
		if (detecter(3))
		{
			//tableauPlateau[x0][y0]=0;
			y0++;
			placer(true);
		}
	}
	/***
	 * Méthode permettant d'appliquer une rotation de 90° à la pièce courante 
	 ***/
	public void tourner() 
	{
		if (detecter(9))
		{
			pieceCourante.rotation();
			d=pieceCourante.getChemin();
			placer(true);
		}
	}
	
	/***
	 * Méthode permettant de tester la possibilité d'appeler les méthodes: d'apparition d'une nouvelle pièce, de rotation, de translation
	 * Méthode teste si les méthodes peuvent être appelées et effectuées sans rentrer en contradiction avec les pièces figées et les bordures du jeu
	 ***/
	public boolean detecter(int n)
	{
		int x,y,xd=0,yd=0,xc=0,yc=0;
		boolean f=true;
		int[] dtest=new int[3];
		int[][] ptest=new int[4][2];	//test postion
		if (n==9)
		{
			
			for (int i=0;i<3;i++)
			{
				dtest[i]=d[i];
			}
			for(int i=0; i<dtest.length; i++){
				if(dtest[i]+2 >= 9){ 
					dtest[i]=dtest[i]-6;
				}
				else{ 
					dtest[i]+=2; 
				}
			}
		}
		else if (n==1) //possible de placer une nouvelle piece
		{
			
			dtest=pieceSuivante.getChemin();
			int[]posCentre=pieceSuivante.getPos();
			xc=posCentre[0];
			yc=posCentre[1];
			x=xc;
			y=yc;
			
			for (int i=num-1;i<3;i++)
			{
				switch (dtest[i])
				{
					case 1:
					x--;
					break;
					case 3:
					y++;
					break;
					case 5:
					x++;
					break;
					case 7:
					y--;
					break;
					case 2:
					x--;
					y++;
					break;
					case 4:
					x++;
					y++;
					break;
					case 6:
					x++;
					y--;
					break;
					case 8:
					x--;
					y--;
					break;
				}
				ptest[i+1][0]=x;
				ptest[i+1][1]=y;
			}
			x=xc;
			y=yc;
			for (int i=num-2;i>=0;i--)
			{
				switch (dtest[i])
				{
					case 1:
					x--;
					break;
					case 3:
					y++;
					break;
					case 5:
					x++;
					break;
					case 7:
					y--;
					break;
					case 2:
					x--;
					y++;
					break;
					case 4:
					x++;
					y++;
					break;
					case 6:
					x++;
					y--;
					break;
					case 8:
					x--;
					y--;
					break;
				}
				ptest[i][0]=x;
				ptest[i][1]=y;
			}
		}
		else if (n==3) //droite
		{
			yd=1;
		}
		else if (n==5) //bas
		{
			xd=1;
		}
		else if (n==7) //gauche
		{
			yd=-1;
		}
		if (n!=9&&n!=1)
		{
			for(int i=0;i<4;i++) // boucle qui teste la possibilité de faire un mouvement
			{
				x=pos[i][0];
				y=pos[i][1];
				if ( (x+xd>19||y+yd<0||y+yd>9))
				{
					f=false;
				}
				else if ( (tableauPlateau[x+xd][y+yd]>0))
				{
					f=false;
				}
			}
		}
		else if (n==1)
		{
			for(int i=0;i<4;i++)
			{
				x=ptest[i][0];
				y=ptest[i][1];
				System.out.println(x+","+y);
				if ( (tableauPlateau[x][y]!=0))
				{
					f=false;
				}
			}
		}
		else
		{
			//int[][] ptest=new int[4][2];	//test postion
			x=x0;
			y=y0;
			ptest[num-1][0]=x0;
			ptest[num-1][1]=y0;
			for (int i=num-1;i<3;i++)
			{
				switch (dtest[i])
				{
					case 1:
					x--;
					break;
					case 3:
					y++;
					break;
					case 5:
					x++;
					break;
					case 7:
					y--;
					break;
					case 2:
					x--;
					y++;
					break;
					case 4:
					x++;
					y++;
					break;
					case 6:
					x++;
					y--;
					break;
					case 8:
					x--;
					y--;
					break;
				}
				ptest[i+1][0]=x;
				ptest[i+1][1]=y;
			}
			x=x0;
			y=y0;
			for (int i=num-2;i>=0;i--)
			{
				switch (dtest[i])
				{
					case 1:
					x--;
					break;
					case 3:
					y++;
					break;
					case 5:
					x++;
					break;
					case 7:
					y--;
					break;
					case 2:
					x--;
					y++;
					break;
					case 4:
					x++;
					y++;
					break;
					case 6:
					x++;
					y--;
					break;
					case 8:
					x--;
					y--;
					break;
				}
				ptest[i][0]=x;
				ptest[i][1]=y;		
			
			}
			for (int i=0;i<4;i++)
			{
				x=ptest[i][0];
				y=ptest[i][1];
				if ( (x<0||x>19||y<0||y>9))
				{
					f=false;
				}
				else if ( (tableauPlateau[x][y]>0))
				{
					f=false;
				}
			}
		}
		return f;
	}

	/***
	 * Méthode qui permet de tester si la pièce a changé ou si elle a changé de sens, puis permet de l'afficher
	 ***/
	public void placer(boolean a) // 'a' permet de savoir si c'est une nouvelle pièce qui doit être placée ou pas
	{
		int x,y;

		if (a) //true: ce n'est pas une nouvelle piece, donc il faut effacer les cubes précédents
		{
			for (int i=0;i<4;i++)
			{
				x=pos[i][0];
				y=pos[i][1];
				tableauPlateau[x][y]=0;
			}
		}
		x=x0;
		y=y0;
		tableauPlateau[x][y]=couleur*-1;
		pos[num-1][0]=x;
		pos[num-1][1]=y;
		for (int i=num-1;i<3;i++)
		{
			switch (d[i])
			{
				case 1:
				x--;
				break;
				case 3:
				y++;
				break;
				case 5:
				x++;
				break;
				case 7:
				y--;
				break;
				case 2:
				x--;
				y++;
				break;
				case 4:
				x++;
				y++;
				break;
				case 6:
				x++;
				y--;
				break;
				case 8:
				x--;
				y--;
				break;
			}
			pos[i+1][0]=x;
			pos[i+1][1]=y;
			tableauPlateau[x][y]=couleur*-1;
		}
		x=x0;
		y=y0;
		for (int i=num-2;i>=0;i--)
		{
			switch (d[i])
			{
				case 1:
				x--;
				break;
				case 3:
				y++;
				break;
				case 5:
				x++;
				break;
				case 7:
				y--;
				break;
				case 2:
				x--;
				y++;
				break;
				case 4:
				x++;
				y++;
				break;
				case 6:
				x++;
				y--;
				break;
				case 8:
				x--;
				y--;
				break;
			}
			pos[i][0]=x;
			pos[i][1]=y;	
			tableauPlateau[x][y]=couleur*-1;
			
		}
	}	
	
	public int[][] getTableau(){ //getter de la variable tableauPlateau
		int [][] tab = this.tableauPlateau;
		return tab;
	}
	
	public int getScore(){ // getter de la variable score
		return this.score;
	}
	
	public boolean getPerdu(){// getter de la variable perdu
		return this.perdu;
	}
	
	public int getNbrLigne(){ // getter de la variable nbrLigne
		return this.nbrLigne;
	}
	
	public Bloc getPieceSuivante(){ //getter de la variable pieceSuivante
		return this.pieceSuivante;
	}

// Méthode mettant les différentes variables à leur état initial pour reinitialiser l'objet
	public void reinitialiser(){ 
		for (int i=0;i<20;i++)
		{
			for (int j=0;j<10;j++)
			{
				this.tableauPlateau[i][j]=0;
			}
		}
		this.score=0;
		this.nbrLigne=0;
		this.perdu=false;
		pieceSuivante=listeBloc.get((int)(Math.random()*7));
		nouveau();
	}
	
	public void setSon(boolean son){
		this.son=son;
	}
}

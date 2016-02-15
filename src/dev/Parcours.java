package dev;

/**
 * Classe modelisant un parcours, c'est a dire un ensemble de groupes de soins a realiser.
 * 
 */
public class Parcours {

	protected int nombreDeSoins;
	protected int nombreDeGroupes;
	protected GroupeSoins[] groupeSoins;
	protected String numeroParcours;
	
	/***
	 * Constructeur d'un parcours identifie par un indice donne,et constitue a partir d'un tableau de groupe de soins. 
	 * @param groupesSoins le tableau de groupes de soins
	 * @param indice l'indice du parcours
	 */
	public Parcours(GroupeSoins[] groupesSoins, String numero){
		this.nombreDeGroupes = groupesSoins.length;
		this.nombreDeSoins=0;
		for(GroupeSoins g : groupesSoins)
			this.nombreDeSoins+=g.getNombreDeSoins();
		this.groupeSoins = groupesSoins;
		this.numeroParcours = numero;
	}
	
	/**
	 * Initialise le parcours avec un tableau vide et un indice negatif a -1
	 */
	public Parcours(){
		this(new GroupeSoins[0], "-1");
	}

	/**
	 * @return le nombre de groupes de soins dans le parcours 
	 */
	public int getNombreDeGroupes() {
		return nombreDeGroupes;
	}
	
	/**
	 * 
	 * @return le tableau de groupe de soins du parcours
	 */
	public GroupeSoins[] getGroupeSoins() {
		return groupeSoins;
	}
	
	/**
	 * @return le nombre total de soins du parcours
	 */
	public int getNombreDeSoins(){
		return this.nombreDeSoins;
	}
	
	/** 
	 * @param indiceParcours le nouvel indice du parcours
	 */
	public void setNumeroParcours(String numeroParcours) {
		this.numeroParcours = numeroParcours;
	}
	/**
	 * @return le numero du parcours
	 */
	public String getNumeroParcours() {
		return numeroParcours;
	}
	
	/**
	 * Ajoute a la fin du parcours un groupe de soins
	 * @param groupeSoins le groupe de soins a ajouter
	 */
	public void ajoutGroupeSoins(GroupeSoins groupeSoins){
		int n= this.groupeSoins.length;
		GroupeSoins[] groupeSoinsNew = new GroupeSoins[n+1];
		for (int i = 0; i < n; i++) {
			groupeSoinsNew[i]=this.groupeSoins[i];
		}
		groupeSoinsNew[n]=groupeSoins;
		this.groupeSoins = groupeSoinsNew;
	}
}

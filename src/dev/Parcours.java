package dev;

public class Parcours {

	protected int nombreDeSoins;
	protected int nombreDeGroupes;
	protected GroupeSoins[] groupeSoins;
	protected String numeroParcours;
	
	/***
	 * Constructeur d'un parcours identifi� par un indice donn�,et constitu� � partir d'un tableau de groupe de soins. 
	 * @param groupesSoins
	 * @param indice
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
	 * Initialise le parcours avec un tableau vide et un indice n�gatif � -1
	 */
	public Parcours(){
		this(new GroupeSoins[0], "-1");
	}

	/**
	 * Retourne le nombre de groupes de soins dans le parcours 
	 * @return
	 */
	public int getNombreDeGroupes() {
		return nombreDeGroupes;
	}
	
	/**
	 * Retourne le tableau de groupe de soins du parcours
	 * @return
	 */
	public GroupeSoins[] getGroupeSoins() {
		return groupeSoins;
	}
	
	/**
	 * Retourne le nombre total de soins du parcours
	 * @return
	 */
	public int getNombreDeSoins(){
		return this.nombreDeSoins;
	}
	
	/**
	 * Mets � jour le numero du parcours 
	 * @param indiceParcours
	 */
	public void setNumeroParcours(String numeroParcours) {
		this.numeroParcours = numeroParcours;
	}
	/**
	 * Retourne le numero du paarcours
	 * @return
	 */
	public String getNumeroParcours() {
		return numeroParcours;
	}
	
	/**
	 * Ajoute � la fin du parcours un groupe de soins
	 * @param groupeSoins2
	 */
	public void ajoutGroupeSoins(GroupeSoins groupeSoins2){
		int n= this.groupeSoins.length;
		GroupeSoins[] groupeSoinsNew = new GroupeSoins[n+1];
		for (int i = 0; i < n; i++) {
			groupeSoinsNew[i]=this.groupeSoins[i];
		}
		groupeSoinsNew[n]=groupeSoins2;
		this.groupeSoins = groupeSoinsNew;
	}
}

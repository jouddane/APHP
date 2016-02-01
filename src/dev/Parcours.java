package dev;

public class Parcours {

	protected int nombreDeSoins;
	protected int nombreDeGroupes;
	protected GroupeSoins[] groupeSoins;
	protected int indiceParcours;
	
	public Parcours(GroupeSoins[] groupesSoins, int indice){
		this.nombreDeGroupes = groupesSoins.length;
		this.nombreDeSoins=0;
		for(GroupeSoins g : groupesSoins)
			this.nombreDeSoins+=g.getNombreDeSoins();
		this.groupeSoins = groupesSoins;
		this.indiceParcours = indice;
	}
	
	public Parcours(){
		this(new GroupeSoins[0], -1);
	}

	public int getNombreDeGroupes() {
		return nombreDeGroupes;
	}

	public GroupeSoins[] getGroupeSoins() {
		return groupeSoins;
	}
	
	public int getNombreDeSoins(){
		return this.nombreDeSoins;
	}
	
	public void setIndiceParcours(int indiceParcours) {
		this.indiceParcours = indiceParcours;
	}
	
	public int getIndiceParcours() {
		return indiceParcours;
	}
	
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

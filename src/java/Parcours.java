package java;

public class Parcours {

	protected int nombreDeSoins;
	protected int nombreDeGroupes;
	protected GroupeSoins[] groupeSoins;
	
	public Parcours(GroupeSoins[] groupesSoins){
		this.nombreDeGroupes = groupesSoins.length;
		this.nombreDeSoins=0;
		for(GroupeSoins g : groupesSoins)
			this.nombreDeSoins+=g.getNombreDeSoins();
		this.groupeSoins = groupesSoins;
	}
	
	public Parcours(){
		this(new GroupeSoins[0]);
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
}

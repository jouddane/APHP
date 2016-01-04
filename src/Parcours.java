
public class Parcours {

	private int nombreDeSoins;
	private Groupe_Soins[] groupeSoins;
	
	public Parcours(Groupe_Soins[] groupeSoins){
		this.nombreDeSoins = groupeSoins.length;
		this.groupeSoins = groupeSoins;
	}
	
	public Parcours(){
		this(new Groupe_Soins[0]);
	}

	public int getNombreDeSoins() {
		return nombreDeSoins;
	}

	public Groupe_Soins[] getGroupeSoins() {
		return groupeSoins;
	}
	
	
}

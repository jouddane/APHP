
public class Groupe_Soins {

	private Soin[] soins;
	
	public Groupe_Soins(Soin[] soins){
		this.soins = soins;
	}
	
	public Soin getSoin(int indice){
		return soins[indice];
	}
	
	public void setSoin(int indice, Soin soin){
		this.soins[indice] = soin;
	}
	
	public int debut(){
		int debut = Integer.MAX_VALUE;
		for(Soin s : soins){
			if(s.getDebut() < debut)
				debut = s.getDebut();
		}
		return debut;
	}
	
	public int fin(){
		int fin = Integer.MIN_VALUE;
		for(Soin s : soins){
			if(s.getDebut() + s.getDuree() > fin)
				fin = s.getDebut() + s.getDuree();
		}
		return fin;
	}
}

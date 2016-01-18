package dev;

public class GroupeSoinsAffecte extends GroupeSoins{

	private int debut;
	private boolean realise;
	private boolean enCours;
	
	public GroupeSoinsAffecte(GroupeSoins groupeSoins) {
		super(groupeSoins.soins);
		this.soins = (SoinAffecte[])(groupeSoins.soins);
		this.debut = Integer.MAX_VALUE;
		this.realise = false;
		this.enCours = false;
	}	
	
	public GroupeSoinsAffecte(GroupeSoins soinsAffectes, int debut) {
		super(soinsAffectes.getSoins());
		this.debut = debut;
	}
	
	public boolean estRealise() {
		return realise;
	}

	public void setRealise(boolean realise) {
		this.realise = realise;
	}

	public boolean estEnCours() {
		return enCours;
	}

	public void setEnCours(boolean enCours) {
		this.enCours = enCours;
	}

	public SoinAffecte[] getSoinsAffectes() {
		return (SoinAffecte[])(this.getSoins());
	}

	public void setSoinsAffectes(SoinAffecte[] soins) {
		this.setSoins(soins);
	}
	
	public int getDebut(){
		if(this.debut == Integer.MAX_VALUE){
			int temp = Integer.MAX_VALUE;
			for(SoinAffecte s : this.getSoinsAffectes()){
				if(s.getDebut() < temp)
					temp = s.getDebut();
			}
			this.debut = temp;
		}
		return this.debut;
	}
	
	public void setDebut(int debut){
		this.debut = debut;
	}
	
	/*
	public int debut(){
		int debut = Integer.MAX_VALUE;
		for(SoinAffecte s : this.getSoinsAffectes()){
			if(s.getDebut() < debut)
				debut = s.getDebut();
		}
		return debut;
	}
	*/
	
	public int fin(){
		int fin = Integer.MIN_VALUE;
		for(SoinAffecte s : this.getSoinsAffectes()){
			if(s.getDebut() + s.getDuree() > fin)
				fin = s.getDebut() + s.getDuree();
		}
		return fin;
	}
	
}

package java;

public class SoinAffecte extends Soin{

	private int debut;
	private boolean realise;
	private boolean enCours;
	
	public SoinAffecte(Soin s, int debut){
		super(s.getDuree());
		this.debut = debut;
		this.realise = false;
		this.enCours = false;
	}
	
	public int getDebut(){
		return this.debut;
	}

	public boolean estRealise() {
		return realise;
	}

	public boolean estEnCours() {
		return enCours;
	}

	public void setDebut(int debut) {
		this.debut = debut;
	}

	public void setRealise(boolean realise) {
		this.realise = realise;
	}

	public void setEnCours(boolean enCours) {
		this.enCours = enCours;
	}
}

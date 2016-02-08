package choco;
import java.util.ArrayList;

public class Etat {

	private ArrayList<CoupleIndiceSoinDuree> listPossibilite;
	private int etatInt;
	
	public Etat(){
		this.listPossibilite = new ArrayList<>();
		this.etatInt = -1;
	}
	
	public Etat(ArrayList<CoupleIndiceSoinDuree> listPossibilite, int etatInt){
		this.listPossibilite= listPossibilite;
		this.etatInt = etatInt;
	}
	
	public Etat(Etat etat){
		this((ArrayList<CoupleIndiceSoinDuree>) etat.getListPossibilite().clone(), etat.getEtatInt());
	}
	
	public ArrayList<CoupleIndiceSoinDuree> getListPossibilite() {
		return listPossibilite;
	}

	public void setListPossibilite(ArrayList<CoupleIndiceSoinDuree> listPossibilite) {
		this.listPossibilite = listPossibilite;
	}

	
	
	public int getEtatInt() {
		return etatInt;
	}

	public void setEtatInt(int etatInt) {
		this.etatInt = etatInt;
	}

	public void remove(int possibilite){
		int i=0;
		int n= this.getListPossibilite().size();
		while((i<n)&&(this.getListPossibilite().get(i).getIndiceSoin()!=possibilite)){
			i++;
		}
		this.getListPossibilite().remove(i);
		this.getListPossibilite().trimToSize();
	}
	
	public Etat etatSuivant(int possibiliteUtilisee){
		Etat etatSuivant = new Etat(this);
		//doit avoir l'ajout d"intermediaire ici
		etatSuivant.remove(possibiliteUtilisee);
		etatSuivant.getListPossibilite().trimToSize();
		return etatSuivant;
	}
	
	public Etat etatsSuivant(int possibiliteUtilisee){
		Etat etatSuivant = new Etat(this);
		//doit avoir l'ajout d"intermediaire ici
		etatSuivant.remove(possibiliteUtilisee);
		return etatSuivant;
	}
	
	
	
	public boolean estUnEtatFinal(){
		int n = this.getListPossibilite().size();
		return n==1;
	}
	

	
	
}

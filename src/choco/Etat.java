package choco;
import java.util.ArrayList;

public class Etat {

	private ArrayList<CoupleIndiceSoinDuree> listPossibilite;
	
	
	public Etat(ArrayList<CoupleIndiceSoinDuree> listPossibilite){
		this.listPossibilite= listPossibilite;
	}
	
	public Etat(Etat etat){
		this(etat.getListPossibilite());
	}
	
	public ArrayList<CoupleIndiceSoinDuree> getListPossibilite() {
		return listPossibilite;
	}

	public void setListPossibilite(ArrayList<CoupleIndiceSoinDuree> listPossibilite) {
		this.listPossibilite = listPossibilite;
	}

	public void remove(int possibilite){
		int i=0;
		int n= this.getListPossibilite().size();
		while((i<n)&&(this.getListPossibilite().get(i).getIndiceSoin()!=possibilite)){
			i++;
		}
		this.getListPossibilite().remove(i);
	}
	
	public Etat etatSuivant(int possibiliteUtilisee){
		Etat etatSuivant = new Etat(this);
		//doit avoir l'ajout d"intermediaire ici
		etatSuivant.remove(possibiliteUtilisee);
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

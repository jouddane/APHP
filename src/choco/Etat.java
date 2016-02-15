package choco;
import java.util.ArrayList;

/**
 * Classe representant les Etats composant l'automate. Un etat correpond a un ensemble de soins realisables au sein d'un groupe, c'est a dire pouvant etre utilise comme transition entre etat et les etats qui lui succedent 
 *
 */
public class Etat {

	//Liste des soins realisables, c'est a dire pouvant etre utilise comme transition entre etat et les etats qui lui succedent 
	private ArrayList<CoupleIndiceSoinDuree> listPossibilite;
	//Valeur entiere reprensentant la place de l'etat dans l'automate. Cette valeur sera definie par un appel de la methode state() de la classe FinietAutomaton
	private int etatInt;
	
	/**
	 * Constructeur par defaut de l'objet Etat. Initialise l'etat avec une liste de possibilites (soins realisables) vide et une valeur de -1 a etatInt.
	 */
	public Etat(){
		this.listPossibilite = new ArrayList<>();
		this.etatInt = -1;
	}
	
	/**
	 * Constructeur de l'objet Etat. Initialise listPossibilite et etatInt par les valeurs des parametres
	 * @param listPossibilite ensemble des soins pouvant servir de transition de l'etat aux etats successeurs
	 * @param etatInt valeur entiere reprensentant la place de l'etat dans l'automate. Cette valeur sera definie par un appel de la methode state() de la classe FinietAutomaton
	 */
	public Etat(ArrayList<CoupleIndiceSoinDuree> listPossibilite, int etatInt){
		this.listPossibilite= listPossibilite;
		this.etatInt = etatInt;
	}
	
	/**
	 * Constructeur par copie de l'objet etat
	 * @param etat l'etat que l'on souhaite copier
	 */
	public Etat(Etat etat){
		this((ArrayList<CoupleIndiceSoinDuree>) etat.getListPossibilite().clone(), etat.getEtatInt());
	}
	
	/**
	 * getter de la variable d'instance listPossibilite
	 * @return 	listPossibilite : Liste des soins realisables, c'est a dire pouvant etre utilise comme transition entre etat et les etats qui lui succedent 
	 */
	public ArrayList<CoupleIndiceSoinDuree> getListPossibilite() {
		return listPossibilite;
	}

	/**
	 * setter de la variable d'instance
	 * @param listPossibilite Liste des soins realisables, c'est a dire pouvant etre utilise comme transition entre etat et les etats qui lui succedent 
	 */
	public void setListPossibilite(ArrayList<CoupleIndiceSoinDuree> listPossibilite) {
		this.listPossibilite = listPossibilite;
	}

	/**
	 * getter de la variable d'instance etatInt
	 * @return etatInt Valeur entiere reprensentant la place de l'etat dans l'automate. Cette valeur sera definie par un appel de la methode state() de la classe FinietAutomaton
	 */
	public int getEtatInt() {
		return etatInt;
	}

	/**
	 * setter de la variable d'instance etatInt
	 * @param etatInt Valeur entiere reprensentant la place de l'etat dans l'automate. Cette valeur sera definie par un appel de la methode state() de la classe FinietAutomaton
	 */
	public void setEtatInt(int etatInt) {
		this.etatInt = etatInt;
	}

	/**
	 * supprime de listPossibilite, le coupleIndiceSoinDuree dont l'indiceSoin est egal a possibilite
	 * @param possibilite indice du soin que l'on souhaite enlever de l'etat
	 */
	public void remove(int possibilite){
		int i=0;
		int n= this.getListPossibilite().size();
		while((i<n)&&(this.getListPossibilite().get(i).getIndiceSoin()!=possibilite)){
			i++;
		}
		this.getListPossibilite().remove(i);
		this.getListPossibilite().trimToSize();
	}
	
	/**
	 * Permet d'obtenir les etats de l'automates succedant a l'etat this
	 * @param possibiliteUtilisee indice du soin qui servira de transition pour le passage de l'etat a un etat qui lui succede
	 * @return etat succedant a l'etat this, obtenu par la transition possibiliteUtilisee
	 */
	public Etat etatSuivant(int possibiliteUtilisee){
		Etat etatSuivant = new Etat(this);
		etatSuivant.remove(possibiliteUtilisee);
		etatSuivant.getListPossibilite().trimToSize();
		return etatSuivant;
	}

}

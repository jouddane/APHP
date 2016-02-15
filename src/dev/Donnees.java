package dev;

/**
 * Classe permettant d'initialiser toutes les donnees du probleme.
 * Elle est necessaire a la creation d'un objet de type Probleme
 * 
 */
public class Donnees {

	private Patient[] patients;
	private Parcours[] parcours;
	private Ressource[] ressources;
	private int nPeriodes;
	private int HOuverture;
	private int HFermeture;
	private int A_MAX;
	private int A_MIN;
	
	/**
	 * Constructeur par defaut initialisant toutes les donnees a 0
	 */
	public Donnees() {
		this.patients = new Patient[0];
		this.parcours = new Parcours[0];
		this.ressources = new Ressource[0];
		this.nPeriodes = 0;
		HOuverture = 0;
		HFermeture = 0;
		A_MAX = 0;
		A_MIN = 0;
	}
	
	/**
	 * @return le tableau contenant tous les patiens de la journee
	 */
	public Patient[] getPatients() {
		return patients;
	}
	/**
	 * @param patients le nouveau le tableau des patients de la journee
	 */
	public void setPatients(Patient[] patients) {
		this.patients = patients;
	}
	
	/** 
	 * @return le tableau des patients de la journee
	 */
	public Parcours[] getParcours() {
		return parcours;
	}
	
	/**
	 * @param parcours le nouveau tableau des parcours de la journee
	 */
	public void setParcours(Parcours[] parcours) {
		this.parcours = parcours;
	}
	
	/**
	 * @return les quantites de ressources disponibles quand aucun soin n'est prevu
	 */
	public Ressource[] getRessources() {
		return ressources;
	}
	
	/**
	 * @param ressources les nouvelles quantites de ressources disponibles quand aucun soin n'est prevu
	 */
	public void setRessources(Ressource[] ressources) {
		this.ressources = ressources;
	}
	
	/**
	 * Discretise le temps en nPeriodes.
	 * Pour discretiser en periodes de 5 minutes : 
	 * 12 * 5 minutes = 1 heure.
	 * 24 heures = 1 journee.
	 * Donc nPeriodes = 12*24.
	 * @param nPeriodes le nombre de periodes
	 */
	public void setNPeriodes(int nPeriodes) {
		this.nPeriodes = nPeriodes;
	}
	
	/**
	 * @return le nombre de periodes dans la journee
	 */
	public int getnPeriodes() {
		return nPeriodes;
	}
	
	/**
	 * Mets a jour l'heure de fermeture de la journee en minutes. 
	 * Ex pour 20h : 20*60
	 * @param hFermeture l'heure de fermeture de la journee en minutes
	 */
	public void setHFermeture(int hFermeture) {
		HFermeture = hFermeture;
	}
	
	/**
	 * @return l'heure de fermeture de la journee en minutes
	 */
	public int getHFermeture() {
		return HFermeture;
	}
	
	/**
	 * Mets a jour l'heure d'ouverture de la journee en minutes.
	 * Ex pour 8h : 8*60
	 * @param hOuverture l'heure d'ouverture de la journee en minutes
	 */
	public void setHOuverture(int hOuverture) {
		HOuverture = hOuverture;
	}
	
	/**
	 * @return l'heure d'ouverture de la journee en minutes
	 */
	public int getHOuverture() {
		return HOuverture;
	}
	
	/**
	 * @param a_MAX le temps d'attente maximum entre deux soins en min
	 */
	public void setA_MAX(int a_MAX) {
		A_MAX = a_MAX;
	}
	
	/**
	 * @return le temps d'attente maximum entre deux soins en min
	 */
	public int getA_MAX() {
		return A_MAX;
	}
	
	/**
	 * @param a_MIN le temps d'attente minimum entre deux soins en min
	 */
	public void setA_MIN(int a_MIN) {
		A_MIN = a_MIN;
	}
	
	/**
	 * @return le temps d'attente minimum entre deux soins en min
	 */
	public int getA_MIN() {
		return A_MIN;
	}
	
	/**
	 * Ajoute un patient a la fin de la liste de patients de la journee
	 * @param patient le patient a ajouter
	 */
	public void ajoutPatient(Patient patient){
		int n = this.patients.length;
		Patient[] patientsNew = new Patient[n+1];
		for (int i = 0; i < n; i++) {
			patientsNew[i]=this.patients[i];
		}
		patientsNew[n]=patient;
		this.patients=patientsNew;
	}
	
	/**
	 * Ajoute une ressource a la fin de la liste des ressources de la journee
	 * @param ressource la ressource a ajouter
	 */
	public void ajoutRessource(Ressource ressource){
		int n = this.ressources.length;
		Ressource[] ressourcesNew = new Ressource[n+1];
		for (int i = 0; i < n; i++) {
			ressourcesNew[i]=this.ressources[i];
		}
		ressourcesNew[n]=ressource;
		this.ressources=ressourcesNew;
	}
	
	/**
	 * Ajoute un parcours a la fin de la liste de parcours de la journee
	 * @param parcours le parcours a ajouter
	 */
	public void ajoutParcours(Parcours parcours){
		int n = this.parcours.length;
		Parcours[] parcoursNew = new Parcours[n+1];
		for (int i = 0; i < n; i++) {
			parcoursNew[i]=this.parcours[i];
		}
		parcoursNew[n]=parcours;
		this.parcours=parcoursNew;
	}
	
	/**
	 * @param nom le nom de la ressource 
	 * @return l'indice de la ressource dont le nom correspond au nom en parametre si elle existe, -1 sinon
	 */
	public int getRessourceNom(String nom){
		int i=0;
		while((i<this.getRessources().length)&&(this.getRessources()[i].getNom()!=nom)){
			i++;
		}
		if(i<this.getRessources().length){
			return i;
		}
		else{
			return -1;
		}
	}
	
	/**
	 * @param nom  le numero du parcours
	 * @return l'indice du parcours dont le numero correspond au numero en parametre si il existe, -1 sinon
	 */
	public int getParcours(String numero){
		int i=0;
		while((i<this.getParcours().length)&&(!this.getParcours()[i].getNumeroParcours().equals(numero))){
			i++;
		}
		if(i<this.getRessources().length){
			return i;
		}
		else{
			return -1;
		}
	}
}

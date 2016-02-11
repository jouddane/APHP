package dev;

//Cr�ation de donn�es pour un jour donn�
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
	 * Constructeur initialisant toutes les donn�es � 0
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
	 * Retourne le tableau contenant tous les patiens de la journee
	 * @return
	 */
	public Patient[] getPatients() {
		return patients;
	}
	/**
	 * Mets � jour le tableau des patients de la journ�e
	 * @param patients
	 */
	public void setPatients(Patient[] patients) {
		this.patients = patients;
	}
	
	/**
	 * Retourne le tableau des parcours de la journ�e 
	 * @return
	 */
	public Parcours[] getParcours() {
		return parcours;
	}
	
	/**
	 * Mets � jour le tableau des parcours de la journ�e
	 * @param parcours
	 */
	public void setParcours(Parcours[] parcours) {
		this.parcours = parcours;
	}
	
	/**
	 * Retourne les ressources disponibles tout au long de la journ�e
	 * @return
	 */
	public Ressource[] getRessources() {
		return ressources;
	}
	
	/**
	 * Mets � jour les ressources disponibles tout au long de la journ�e
	 * @param ressources
	 */
	public void setRessources(Ressource[] ressources) {
		this.ressources = ressources;
	}
	
	/**
	 * Discretise le temps en nPeriodes
	 * @param nPeriodes
	 */
	public void setNPeriodes(int nPeriodes) {
		this.nPeriodes = nPeriodes;
	}
	
	/**
	 * retourne le nombre de periodes de la journ�e en minutes
	 * @return
	 */
	public int getnPeriodes() {
		return nPeriodes;
	}
	
	/**
	 * Mets � jour l'heure de fermeture de la journ�e en minutes ex 20h : 20*60
	 * @param hFermeture
	 */
	public void setHFermeture(int hFermeture) {
		HFermeture = hFermeture;
	}
	
	/**
	 * Retourne l'heure de fermeture de la journ�e en minutes
	 * @return
	 */
	public int getHFermeture() {
		return HFermeture;
	}
	
	/**
	 * Mets � jour l'heure d'ouverture de la journ�e en minutes : ex 8h = 8*60
	 * @param hOuverture
	 */
	public void setHOuverture(int hOuverture) {
		HOuverture = hOuverture;
	}
	
	/**
	 * Retourne l'heure d'ouverture de la journ�e en minutes
	 * @return
	 */
	public int getHOuverture() {
		return HOuverture;
	}
	
	/**
	 * Mets � jour le temps d'attente maximum entre deux soins : en min
	 * @param a_MAX
	 */
	public void setA_MAX(int a_MAX) {
		A_MAX = a_MAX;
	}
	
	/**
	 * Retourne le temps d'attente maximum entre deux soins : en min
	 * @return
	 */
	public int getA_MAX() {
		return A_MAX;
	}
	
	/**
	 * Mets � jour le temps d'attente minimum entre deux soins : en min
	 * @param a_MIN
	 */
	public void setA_MIN(int a_MIN) {
		A_MIN = a_MIN;
	}
	
	/**
	 * Retourne le temps d'attente minimum entre deux soins : en min
	 * @return
	 */
	public int getA_MIN() {
		return A_MIN;
	}
	
	/**
	 * Ajoute un patient � la fin de la liste de patients de la journee
	 * @param patient
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
	 * Ajoute une ressource � la fin de la liste des ressources de la journee
	 * @param ressource
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
	 * Ajoute un parcours � la fin de la liste de parcours de la journ�e
	 * @param parcours2
	 */
	public void ajoutParcours(Parcours parcours2){
		int n = this.parcours.length;
		Parcours[] parcoursNew = new Parcours[n+1];
		for (int i = 0; i < n; i++) {
			parcoursNew[i]=this.parcours[i];
		}
		parcoursNew[n]=parcours2;
		this.parcours=parcoursNew;
	}
	
	/**
	 * Retourne l'indice de la ressource dont le nom correspont � nom si elle existe -1 sinon
	 * @param nom
	 * @return
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
	 * Retourne l'indice du parcours dont le numero correspont à numero si il existe -1 sinon
	 * @param nom
	 * @return
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

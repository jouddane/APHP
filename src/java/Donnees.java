package java;

//Propre a un jour ?
public class Donnees {

	private Patient[] patients;
	private Parcours[] parcours;
	private Ressource[] ressources;
	private int nPeriodes;
	private int HOuverture;
	private int HFermeture;
	private int A_MAX;
	private int A_MIN;
	
	
	public Patient[] getPatients() {
		return patients;
	}

	public void setPatients(Patient[] patients) {
		this.patients = patients;
	}

	public Parcours[] getParcours() {
		return parcours;
	}

	public void setParcours(Parcours[] parcours) {
		this.parcours = parcours;
	}

	public Ressource[] getRessources() {
		return ressources;
	}

	public void setRessources(Ressource[] ressources) {
		this.ressources = ressources;
	}

	public void setNPeriodes(int nPeriodes) {
		this.nPeriodes = nPeriodes;
	}
	
	public int getnPeriodes() {
		return nPeriodes;
	}
	
	public void setHFermeture(int hFermeture) {
		HFermeture = hFermeture;
	}
	
	public int getHFermeture() {
		return HFermeture;
	}
	
	public void setHOuverture(int hOuverture) {
		HOuverture = hOuverture;
	}
	
	public int getHOuverture() {
		return HOuverture;
	}
	
	public void setA_MAX(int a_MAX) {
		A_MAX = a_MAX;
	}
	
	public int getA_MAX() {
		return A_MAX;
	}
	
	public void setA_MIN(int a_MIN) {
		A_MIN = a_MIN;
	}
	
	public int getA_MIN() {
		return A_MIN;
	}
	
	public void ajoutPatient(Patient patient){
		int n = this.patients.length;
		Patient[] patientsNew = new Patient[n+1];
		for (int i = 0; i < n; i++) {
			patientsNew[i]=this.patients[i];
		}
		patientsNew[n]=patient;
		this.patients=patientsNew;
	}
	
	public void ajoutRessource(Ressource ressource){
		int n = this.ressources.length;
		Ressource[] ressourcesNew = new Ressource[n+1];
		for (int i = 0; i < n; i++) {
			ressourcesNew[i]=this.ressources[i];
		}
		ressourcesNew[n]=ressource;
		this.ressources=ressourcesNew;
	}
	
	public void ajoutParcours(Parcours parcours2){
		int n = this.parcours.length;
		Parcours[] parcoursNew = new Parcours[n+1];
		for (int i = 0; i < n; i++) {
			parcoursNew[i]=this.parcours[i];
		}
		parcoursNew[n]=parcours2;
		this.parcours=parcoursNew;
	}
	
}

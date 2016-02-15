package dev;

/**
 * Classe represantant un patient
 * 
 */
public class Patient {

    /**
     * Le parcours affecte au patient
     */
	private ParcoursAffecte parcours;
	
	/**
	 * La date de rendez-vous du patient pour le parcours
	 */
	private Date dateRDV;
	
	/**
	 * Constructeur creant un patient d'apres un parcours et une date de rendez-vous
	 * @param parcours le parcours a affecter au patient
	 * @param dateRDV la date de rendez-vous pour le parcours
	 */
	public Patient(Parcours parcours, Date dateRDV){
		this.parcours = new ParcoursAffecte(parcours);
		this.dateRDV = dateRDV;
	}
	
	/**
	 * @return la date de rendez-vous
	 */
	public Date getDateRDV(){
		return this.dateRDV;
	}
	
	/**
	 * @return le parcours affecte au patient
	 */
	public Parcours getParcours(){
		return this.parcours;
	}
	
	/**
	 * Met a jour le parcours affecte au patient
	 * @param parcours le parcours a affecter au patient
	 */
	public void setParcours(ParcoursAffecte parcours){
		this.parcours = parcours;
	}
	
	/**
	 * Met a jour la date de rendez-vous du patient
	 * @param dateRDV la date de rendez-vous du patient
	 */
	public void setDateRDV(Date dateRDV){
		this.dateRDV = dateRDV;
	}
}

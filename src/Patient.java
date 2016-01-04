
public class Patient {

	private Parcours parcours;
	private Date dateRDV;
	
	public Patient(Parcours parcours, Date dateRDV){
		this.parcours = parcours;
		this.dateRDV = dateRDV;
	}
	
	public Date getDateRDV(){
		return this.dateRDV;
	}
	
	public Parcours getParcours(){
		return this.parcours;
	}
	
	public void setParcours(Parcours parcours){
		this.parcours = parcours;
	}
	
	public void setDateRDV(Date dateRDV){
		this.dateRDV = dateRDV;
	}
}

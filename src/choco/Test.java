package choco;

import java.util.ArrayList;

import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.constraints.IntConstraintFactory;

import dev.Donnees;
import dev.GroupeSoins;
import dev.Parcours;
import dev.Probleme;
import dev.Ressource;
import dev.Soin;
import dev.CoupleStringInt;

public class Test {

	public static void main(String[] args) {
		
		
		//1. Initialisation des donnees a disposition des clients
		Donnees donnees = new Donnees();
		int nPeriodes =60*24;
		
		donnees.setNPeriodes(nPeriodes);
		donnees.setA_MAX(60);
		donnees.setA_MIN(5);
		donnees.setHFermeture(20*60);
		donnees.setHOuverture(6*60);
		
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "HDJ Obesite", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Box", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Box prelevement", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Hors HDJ", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Bureau CS", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Box soin", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "salle pansement", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "explorations fonctionnelles", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "pièce isolée avec fauteuil", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "bureau sommeil", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "salle etp groupe", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "salle avec lit", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "HDJ chimio", 10));
		
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "IDE Obesité", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "IDE", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "nutritioniste", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Diététicien", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Psychologue", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Interne obésité", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Médecin hépato", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "externe", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "IDE cardio", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "cardiologue", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "orthoptiste", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "diabétologue", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "podologue", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "IDE pansement", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "médecin", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "IDE pompe insuline", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "IDE insulinothérapie", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "médecin sommeil", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "IDE sommeil", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "prestataire", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "IDE chimio", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Generaliste", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Soignant", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Selon profil(P20)", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Kine", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "agent accueil", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "neurologue", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Neuropsy", 10));
		
		int nRessources = donnees.getRessources().length;
		
		int[] ressourcesNecessaires = new int[nRessources];
		ressourcesNecessaires[donnees.getRessourceNom("A")]=2;
		ressourcesNecessaires[donnees.getRessourceNom("B")]=1;
		
		ArrayList<CoupleStringInt> list = new ArrayList<>();
		list.add(new CoupleStringInt("A", 10));
		list.add(new CoupleStringInt("B", 100));
		
		ArrayList<CoupleStringInt> listRessourceCapacite1 = new ArrayList<>();
		listRessourceCapacite1.add(new CoupleStringInt("IDE obesite", 1));
		listRessourceCapacite1.add(new CoupleStringInt("HDJ Obesite", 1));	 
		Soin Consultation = Soin.creerSoin(donnees, listRessourceCapacite1, "Consultation", 20);
		
		ArrayList<CoupleStringInt> listRessourceCapacite2 = new ArrayList<>();	
		listRessourceCapacite2.add(new CoupleStringInt("IDE", 1));	
		listRessourceCapacite2.add(new CoupleStringInt("Box", 1));	 	 	 	 	 	
		Soin ECG = Soin.creerSoin(donnees, listRessourceCapacite2, "ECG", 15);
		
		ArrayList<CoupleStringInt> listRessourceCapacite3 = new ArrayList<>();	
		listRessourceCapacite3.add(new CoupleStringInt("IDE", 1));	
		listRessourceCapacite3.add(new CoupleStringInt("Box prelevement", 1));	 	 	 	 	 	
		Soin BilanBiologique = Soin.creerSoin(donnees, listRessourceCapacite3, "Bilan biologique", 15);
		
		ArrayList<CoupleStringInt> listRessourceCapacite4 = new ArrayList<>();	 	 	 	 	
		Soin EchoHepathique = Soin.creerSoin(donnees, listRessourceCapacite4, "Echo hepathique", 15);
		
		ArrayList<CoupleStringInt> listRessourceCapacite5 = new ArrayList<>();	
		listRessourceCapacite5.add(new CoupleStringInt("IDE obesite", 1));	
		listRessourceCapacite5.add(new CoupleStringInt("HDJ Obesite", 1));	
		Soin Calorimetrie = Soin.creerSoin(donnees, listRessourceCapacite5, "Calorimetrie", 30);
		
		ArrayList<CoupleStringInt> listRessourceCapacite6 = new ArrayList<>();	
		listRessourceCapacite6.add(new CoupleStringInt("Psychologue", 1));	
		listRessourceCapacite6.add(new CoupleStringInt("Bureau CS", 1));	 	 	 	 	 	
		Soin EntretienPsy = Soin.creerSoin(donnees, listRessourceCapacite6, "Entretien psy", 40);
		
		ArrayList<CoupleStringInt> listRessourceCapacite7 = new ArrayList<>();	
		listRessourceCapacite7.add(new CoupleStringInt("IDE obesite", 1));	
		listRessourceCapacite7.add(new CoupleStringInt("HDJ Obesite", 1));	 	 	 	 	 	
		Soin EntretienInfirmier = Soin.creerSoin(donnees, listRessourceCapacite7, "Entretien Infirmier", 30);
		
		ArrayList<CoupleStringInt> listRessourceCapacite8 = new ArrayList<>();	
		listRessourceCapacite8.add(new CoupleStringInt("Dieteticien", 1));	
		listRessourceCapacite8.add(new CoupleStringInt("HDJ Obesite", 1));	 	 	 	 	 	
		Soin EntretienDiet = Soin.creerSoin(donnees, listRessourceCapacite8, "Entretien diet", 60);
		
		ArrayList<CoupleStringInt> listRessourceCapacite9 = new ArrayList<>();	
		listRessourceCapacite9.add(new CoupleStringInt("Nutritionniste", 1));	
		listRessourceCapacite9.add(new CoupleStringInt("HDJ Obesite", 1));	 	 	 	 	 	
		Soin Synthese = Soin.creerSoin(donnees, listRessourceCapacite9, "Synthese", 30);
		
		ArrayList<CoupleStringInt> listRessourceCapacite10 = new ArrayList<>();	
		listRessourceCapacite10.add(new CoupleStringInt("IDE obesite", 1));	
		listRessourceCapacite10.add(new CoupleStringInt("HDJ obesite", 1));	 	 	 	 	 	
		Soin RDVParamedical = Soin.creerSoin(donnees, listRessourceCapacite10, "RDV paramedical", 20);
		
		
		ArrayList<CoupleStringInt> listRessourceCapacite12 = new ArrayList<>();	 	 	 	 	 	 	 	
		Soin TOGD = Soin.creerSoin(donnees, listRessourceCapacite12, "TOGD", 20);
	
		ArrayList<CoupleStringInt> listRessourceCapacite14 = new ArrayList<>();	 	 	 	 	 	 	 	
		Soin collation = Soin.creerSoin(donnees, listRessourceCapacite14, "collation", 0);
		
		ArrayList<CoupleStringInt> listRessourceCapacite15 = new ArrayList<>();	
		listRessourceCapacite15.add(new CoupleStringInt("Interne obesite", 1));	
		listRessourceCapacite15.add(new CoupleStringInt("HDJ obesite", 1));	 	 	 	
		Soin BilanAntropometrique = Soin.creerSoin(donnees, listRessourceCapacite15, "Bilan antropometrique", 60);
		
		ArrayList<CoupleStringInt> listRessourceCapacite19 = new ArrayList<>();	
		listRessourceCapacite19.add(new CoupleStringInt("nutritioniste", 1));	
		listRessourceCapacite19.add(new CoupleStringInt("HDJ obesite", 1));	 	 	 	
		Soin synthese = Soin.creerSoin(donnees, listRessourceCapacite19, "synthese", 30);
		
		ArrayList<CoupleStringInt> listRessourceCapacite20 = new ArrayList<>();
		listRessourceCapacite20.add(new CoupleStringInt("Interne Obesite", 1));
		listRessourceCapacite20.add(new CoupleStringInt("HDJ obesite", 1));	 	 	 	
		Soin BilanAnthropometrique = Soin.creerSoin(donnees, listRessourceCapacite20, "Bilan anthropometrique", 60);
		
		ArrayList<CoupleStringInt> listRessourceCapacite21 = new ArrayList<>();
		listRessourceCapacite21.add(new CoupleStringInt("Medecin Hepato", 1));	
		listRessourceCapacite21.add(new CoupleStringInt("HDJ obesite", 1));	 	 	 	 
		Soin Fibroscan = Soin.creerSoin(donnees, listRessourceCapacite21, "Fibroscan", 10);
		
		Soin S1G1P1 = Soin.creerSoin(donnees, list , "S1G1P1", 10);
		
		//2. Creation du probleme mathematique associee
		Probleme aResoudre = new Probleme(donnees);
		
		//3. Creation de l'outil de resolution du probleme
		Resolution resolution = new Resolution(aResoudre);
		
		//4. Lancement de la resolution d probleme
		resolution.resout();
		
		//5. Affichage de la solution (a implementer)
	}
	
}

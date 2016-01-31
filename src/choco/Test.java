package choco;

import java.util.ArrayList;

import org.chocosolver.solver.variables.Variable;

import dev.Donnees;
import dev.GroupeSoins;
import dev.Parcours;
import dev.Patient;
import dev.Probleme;
import dev.Ressource;
import dev.Soin;
import dev.CoupleStringInt;
import dev.Date;

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
		
		//Ajout ressources matérielles
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "HDJ Obesite", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Box", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Box Prelevement", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Hors HDJ", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Bureau CS", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Box Soin", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Salle Pansement", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Explorations Fonctionnelles", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Piece Isolee Avec Fauteuil", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Bureau Sommeil", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Salle ETP Groupe", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Salle Avec Lit", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "HDJ Chimio", 10));

		//Ajout ressources humaines
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "IDE Obesite", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "IDE", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Nutritioniste", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Dieteticien", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Psychologue", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Interne Obesite", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Medecin Hepato", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Externe", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "IDE Cardio", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Cardiologue", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Orthoptiste", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Diabetologue", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Podologue", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "IDE Pansement", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Medecin", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "IDE Pompe Insuline", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "IDE Insulinotherapie", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Medecin Sommeil", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "IDE Sommeil", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Prestataire", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "IDE Chimio", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Generaliste", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Soignant", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Selon Profil", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Kine", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Agent Accueil", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Neurologue", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Neuropsy", 10));
		
		ArrayList<CoupleStringInt> listRessourceCapacite2 = new ArrayList<>();	
		listRessourceCapacite2.add(new CoupleStringInt("IDE", 1));	
		listRessourceCapacite2.add(new CoupleStringInt("Box", 1));	 	 	 	 	 	
		Soin ECG = Soin.creerSoin(donnees, listRessourceCapacite2, "ECG", 15);
		
		ArrayList<CoupleStringInt> listRessourceCapacite3 = new ArrayList<>();	
		listRessourceCapacite3.add(new CoupleStringInt("IDE", 1));	
		listRessourceCapacite3.add(new CoupleStringInt("Box Prelevement", 1));	 	 	 	 	 	
		Soin BilanBiologique = Soin.creerSoin(donnees, listRessourceCapacite3, "Bilan Biologique", 15);
		
		ArrayList<CoupleStringInt> listRessourceCapacite4 = new ArrayList<>();	 	 	 	 	
		Soin EchoHepathique = Soin.creerSoin(donnees, listRessourceCapacite4, "Echo Hepathique", 15);
		
		ArrayList<CoupleStringInt> listRessourceCapacite5 = new ArrayList<>();	
		listRessourceCapacite5.add(new CoupleStringInt("IDE Obesite", 1));	
		listRessourceCapacite5.add(new CoupleStringInt("HDJ Obesite", 1));	
		Soin Calorimetrie = Soin.creerSoin(donnees, listRessourceCapacite5, "Calorimetrie", 30);
		
		ArrayList<CoupleStringInt> listRessourceCapacite6 = new ArrayList<>();	
		listRessourceCapacite6.add(new CoupleStringInt("Psychologue", 1));	
		listRessourceCapacite6.add(new CoupleStringInt("Bureau CS", 1));	 	 	 	 	 	
		Soin EntretienPsy = Soin.creerSoin(donnees, listRessourceCapacite6, "Entretien Psy", 40);
		
		ArrayList<CoupleStringInt> listRessourceCapacite7 = new ArrayList<>();	
		listRessourceCapacite7.add(new CoupleStringInt("IDE Obesite", 1));	
		listRessourceCapacite7.add(new CoupleStringInt("HDJ Obesite", 1));	 	 	 	 	 	
		Soin EntretienInfirmier = Soin.creerSoin(donnees, listRessourceCapacite7, "Entretien Infirmier", 30);
		
		ArrayList<CoupleStringInt> listRessourceCapacite8 = new ArrayList<>();	
		listRessourceCapacite8.add(new CoupleStringInt("Dieteticien", 1));	
		listRessourceCapacite8.add(new CoupleStringInt("HDJ Obesite", 1));	 	 	 	 	 	
		Soin EntretienDiet = Soin.creerSoin(donnees, listRessourceCapacite8, "Entretien Diet", 60);
		
		ArrayList<CoupleStringInt> listRessourceCapacite10 = new ArrayList<>();	
		listRessourceCapacite10.add(new CoupleStringInt("IDE Obesite", 1));	
		listRessourceCapacite10.add(new CoupleStringInt("HDJ Obesite", 1));	 	 	 	 	 	
		Soin RDVParamedical = Soin.creerSoin(donnees, listRessourceCapacite10, "RDV Paramedical", 20);
			
		ArrayList<CoupleStringInt> listRessourceCapacite11 = new ArrayList<>();	 	 	 	 	 	 	 	
		Soin TOGD = Soin.creerSoin(donnees, listRessourceCapacite11, "TOGD", 20);
	
		ArrayList<CoupleStringInt> listRessourceCapacite12 = new ArrayList<>();	 	 	 	 	 	 	 	
		Soin collation = Soin.creerSoin(donnees, listRessourceCapacite12, "Collation", 0);
		
		ArrayList<CoupleStringInt> listRessourceCapacite13 = new ArrayList<>();	
		listRessourceCapacite13.add(new CoupleStringInt("Nutritioniste", 1));	
		listRessourceCapacite13.add(new CoupleStringInt("HDJ Obesite", 1));	 	 	 	
		Soin synthese = Soin.creerSoin(donnees, listRessourceCapacite13, "Synthese", 30);
		
		ArrayList<CoupleStringInt> listRessourceCapacite14 = new ArrayList<>();
		listRessourceCapacite14.add(new CoupleStringInt("Interne Obesite", 1));
		listRessourceCapacite14.add(new CoupleStringInt("HDJ Obesite", 1));	 	 	 	
		Soin BilanAnthropometrique = Soin.creerSoin(donnees, listRessourceCapacite14, "Bilan Anthropometrique", 60);
		
		ArrayList<CoupleStringInt> listRessourceCapacite15 = new ArrayList<>();
		listRessourceCapacite15.add(new CoupleStringInt("Medecin Hepato", 1));	
		listRessourceCapacite15.add(new CoupleStringInt("HDJ Obesite", 1));	 	 	 	 
		Soin Fibroscan = Soin.creerSoin(donnees, listRessourceCapacite15, "Fibroscan", 10);
		
		
		// On crée le Parcours 1
		Soin[] G1P1S = {RDVParamedical};
		GroupeSoins G1P1 = new GroupeSoins(G1P1S);
		Soin[] G2P1S = {ECG, BilanBiologique, EchoHepathique, Calorimetrie};
		GroupeSoins G2P1 = new GroupeSoins(G2P1S);
		Soin[] G3P1S = {collation};
		GroupeSoins G3P1 = new GroupeSoins(G3P1S);
		Soin[] G4P1S = {EntretienPsy, EntretienInfirmier, EntretienDiet};
		GroupeSoins G4P1 = new GroupeSoins(G4P1S);
		Soin[] G5P1S = {synthese};
		GroupeSoins G5P1 = new GroupeSoins(G5P1S);
		GroupeSoins[] P1G = {G1P1, G2P1, G3P1, G4P1, G5P1};
		Parcours P1  = new Parcours(P1G, 0);
		
		// On crée le Parcours 2
		Soin[] G1P2S = {RDVParamedical};
		GroupeSoins G1P2 = new GroupeSoins(G1P2S);
		Soin[] G2P2S = {BilanBiologique, TOGD, Calorimetrie};
		GroupeSoins G2P2 = new GroupeSoins(G2P2S);
		Soin[] G3P2S = {collation};
		GroupeSoins G3P2 = new GroupeSoins(G3P2S);
		Soin[] G4P2S = {BilanAnthropometrique};
		GroupeSoins G4P2 = new GroupeSoins(G4P2S);
		Soin[] G5P2S = {EntretienPsy, EntretienInfirmier, EntretienDiet};
		GroupeSoins G5P2 = new GroupeSoins(G5P2S);
		Soin[] G6P2S = {synthese};
		GroupeSoins G6P2 = new GroupeSoins(G6P2S);
		GroupeSoins[] P2G = {G1P2, G2P2, G3P2, G4P2, G5P2, G6P2};
		Parcours P2  = new Parcours(P2G, 1);
		
		// On crée le Parcours 3
		Soin[] G1P3S = {RDVParamedical};
		GroupeSoins G1P3 = new GroupeSoins(G1P3S);
		Soin[] G2P3S = {BilanBiologique, EchoHepathique, Calorimetrie};
		GroupeSoins G2P3 = new GroupeSoins(G2P3S);
		Soin[] G3P3S = {collation};
		GroupeSoins G3P3 = new GroupeSoins(G3P3S);
		Soin[] G4P3S = {BilanAnthropometrique, Fibroscan};
		GroupeSoins G4P3 = new GroupeSoins(G4P3S);
		Soin[] G5P3S = {EntretienPsy, EntretienInfirmier, EntretienDiet};
		GroupeSoins G5P3 = new GroupeSoins(G5P3S);
		Soin[] G6P3S = {synthese};
		GroupeSoins G6P3 = new GroupeSoins(G6P3S);
		GroupeSoins[] P3G = {G1P3, G2P3, G3P3, G4P3, G5P3, G6P3};
		Parcours P3  = new Parcours(P3G, 2);
		
		donnees.ajoutParcours(P1);
		donnees.ajoutParcours(P2);
		donnees.ajoutParcours(P3);
		
		donnees.ajoutPatient(new Patient(P1, new Date(25,1,2016)));
		donnees.ajoutPatient(new Patient(P2, new Date(25,1,2016)));
		donnees.ajoutPatient(new Patient(P3, new Date(25,1,2016)));
		donnees.ajoutPatient(new Patient(P1, new Date(25,1,2016)));
		donnees.ajoutPatient(new Patient(P2, new Date(25,1,2016)));
		
		//2. Creation du probleme mathematique associee
		Probleme aResoudre = new Probleme(donnees);
		
		//3. Creation de l'outil de resolution du probleme
		Resolution resolution = new Resolution(aResoudre);
		
		//4. Lancement de la resolution d probleme
		Variable[] vars = resolution.resout();
		System.out.println("valeurlol : " + vars[0].toString());
		
		//5. Affichage de la solution (a implementer)
	}
	
}

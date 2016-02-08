package choco;

import java.util.ArrayList;

import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.VF;

import dev.Donnees;
import dev.GroupeSoins;
import dev.Parcours;
import dev.Patient;
import dev.Probleme;
import dev.Ressource;
import dev.Soin;
import maths.Solution;
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

		//Ajout ressources humaines
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "IDE Obesite", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "IDE", 10));
		donnees.ajoutRessource(Ressource.RessourceConstante(nPeriodes, "Nutritionniste", 10));
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


		ArrayList<CoupleStringInt> listRessourceCapacite2 = new ArrayList<>();
		listRessourceCapacite2.add(new CoupleStringInt("IDE", 1));
		listRessourceCapacite2.add(new CoupleStringInt("Box", 1));	 	 	
		Soin ECG15 = Soin.creerSoin(donnees, listRessourceCapacite2, "ECG15", 15);

		ArrayList<CoupleStringInt> listRessourceCapacite3 = new ArrayList<>();
		listRessourceCapacite3.add(new CoupleStringInt("IDE", 1));
		listRessourceCapacite3.add(new CoupleStringInt("Box Prelevement", 1));	 	 
		Soin BilanBiologique15 = Soin.creerSoin(donnees, listRessourceCapacite3, "Bilan Biologique15", 15);

		ArrayList<CoupleStringInt> listRessourceCapacite4 = new ArrayList<>();	 
		listRessourceCapacite4.add(new CoupleStringInt("Hors HDJ",1));	 	 	
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
		Soin EntretienDiet60 = Soin.creerSoin(donnees, listRessourceCapacite8, "Entretien Diet60", 60);

		ArrayList<CoupleStringInt> listRessourceCapacite9 = new ArrayList<>();
		listRessourceCapacite9.add(new CoupleStringInt("Nutritionniste", 1));
		listRessourceCapacite9.add(new CoupleStringInt("HDJ Obesite", 1));	 	 
		Soin SyntheseNutriHDJOb = Soin.creerSoin(donnees, listRessourceCapacite9, "SyntheseNutriHDJOb", 30);

		ArrayList<CoupleStringInt> listRessourceCapacite10 = new ArrayList<>();
		listRessourceCapacite10.add(new CoupleStringInt("IDE Obesite", 1));
		listRessourceCapacite10.add(new CoupleStringInt("HDJ Obesite", 1));	 	 
		Soin RDVParamedical20 = Soin.creerSoin(donnees, listRessourceCapacite10, "RDV Paramedical20", 20);


		//System.out.println("done");

		ArrayList<CoupleStringInt> listRessourceCapacite11 = new ArrayList<>();	 
		listRessourceCapacite11.add(new CoupleStringInt("Hors HDJ",1));	 	 	
		Soin TOGD = Soin.creerSoin(donnees, listRessourceCapacite11, "TOGD", 20);

		ArrayList<CoupleStringInt> listRessourceCapacite12 = new ArrayList<>();	 	 	 
		Soin Collation = Soin.creerSoin(donnees, listRessourceCapacite12, "Collation", 0);

		ArrayList<CoupleStringInt> listRessourceCapacite13 = new ArrayList<>();
		listRessourceCapacite13.add(new CoupleStringInt("Interne Obesite", 1));
		listRessourceCapacite13.add(new CoupleStringInt("HDJ Obesite", 1));	 	 
		Soin BilanAnthropometrique = Soin.creerSoin(donnees, listRessourceCapacite13, "Bilan Anthropometrique", 60);

		ArrayList<CoupleStringInt> listRessourceCapacite14 = new ArrayList<>();
		listRessourceCapacite14.add(new CoupleStringInt("Medecin Hepato", 1));
		listRessourceCapacite14.add(new CoupleStringInt("HDJ Obesite", 1));	 	 
		Soin Fibroscan = Soin.creerSoin(donnees, listRessourceCapacite14, "Fibroscan", 10);

		ArrayList<CoupleStringInt> listRessourceCapacite15 = new ArrayList<>();
		listRessourceCapacite15.add(new CoupleStringInt("IDE", 1));
		listRessourceCapacite15.add(new CoupleStringInt("Bureau CS", 1));	 	 
		Soin RDVParamedical15 = Soin.creerSoin(donnees, listRessourceCapacite15, "RDV Paramedical15", 15);



		ArrayList<CoupleStringInt> listRessourceCapacite16 = new ArrayList<>();	 
		listRessourceCapacite16.add(new CoupleStringInt("Hors HDJ",1));	 	 	
		Soin ScannerAbdo = Soin.creerSoin(donnees, listRessourceCapacite16, "Scanner Abdo", 20);

		ArrayList<CoupleStringInt> listRessourceCapacite17 = new ArrayList<>();
		listRessourceCapacite17.add(new CoupleStringInt("Medecin Hepato", 1));
		listRessourceCapacite17.add(new CoupleStringInt("Bureau CS", 1));	 	 
		Soin EntretienHepato = Soin.creerSoin(donnees, listRessourceCapacite17, "Entretien Hepato", 45);

		ArrayList<CoupleStringInt> listRessourceCapacite18 = new ArrayList<>();
		listRessourceCapacite18.add(new CoupleStringInt("IDE", 1));
		listRessourceCapacite18.add(new CoupleStringInt("Box Soin", 1));	 	 
		Soin TraitementRemicade = Soin.creerSoin(donnees, listRessourceCapacite18, "Traitement Remicade", 120);

		ArrayList<CoupleStringInt> listRessourceCapacite19 = new ArrayList<>();
		listRessourceCapacite19.add(new CoupleStringInt("Nutritionniste", 1));
		listRessourceCapacite19.add(new CoupleStringInt("Bureau CS", 1));	 	 
		Soin SyntheseNutriCS = Soin.creerSoin(donnees, listRessourceCapacite19, "SyntheseNutriCS", 30);

		ArrayList<CoupleStringInt> listRessourceCapacite20 = new ArrayList<>();
		listRessourceCapacite20.add(new CoupleStringInt("Externe", 1));
		listRessourceCapacite20.add(new CoupleStringInt("Box Soin", 1));	 	 
		Soin PonctionAscite = Soin.creerSoin(donnees, listRessourceCapacite20, "Ponction Ascite", 180);

		ArrayList<CoupleStringInt> listRessourceCapacite21 = new ArrayList<>();
		listRessourceCapacite21.add(new CoupleStringInt("IDE", 1));
		listRessourceCapacite21.add(new CoupleStringInt("Box Soin", 1));	 	 
		Soin SoinsPonction2h15 = Soin.creerSoin(donnees, listRessourceCapacite21, "Soins Ponction2h15", 135);

		ArrayList<CoupleStringInt> listRessourceCapacite22 = new ArrayList<>();
		listRessourceCapacite22.add(new CoupleStringInt("IDE", 1));
		listRessourceCapacite22.add(new CoupleStringInt("Box Soin", 1));	 	 
		Soin InjectionFerinject = Soin.creerSoin(donnees, listRessourceCapacite22, "Injection Ferinject", 60);

		ArrayList<CoupleStringInt> listRessourceCapacite23 = new ArrayList<>();
		listRessourceCapacite23.add(new CoupleStringInt("IDE", 1));
		listRessourceCapacite23.add(new CoupleStringInt("Box Prelevement", 1));	 	 
		Soin Prelevement25 = Soin.creerSoin(donnees, listRessourceCapacite23, "Prelevement25", 25);

		ArrayList<CoupleStringInt> listRessourceCapacite24 = new ArrayList<>();
		listRessourceCapacite24.add(new CoupleStringInt("IDE Cardio", 1));
		listRessourceCapacite24.add(new CoupleStringInt("Box Soin", 1));
		listRessourceCapacite24.add(new CoupleStringInt("Cardiologue", 1));	 	
		Soin PoseHolter = Soin.creerSoin(donnees, listRessourceCapacite24, "Pose Holter", 15);

		ArrayList<CoupleStringInt> listRessourceCapacite25 = new ArrayList<>();
		listRessourceCapacite25.add(new CoupleStringInt("Orthoptiste", 1));
		listRessourceCapacite25.add(new CoupleStringInt("Bureau CS", 1));	 	 
		Soin Retinographie = Soin.creerSoin(donnees, listRessourceCapacite25, "Retinographie", 15);

		ArrayList<CoupleStringInt> listRessourceCapacite26 = new ArrayList<>();	 
		listRessourceCapacite26.add(new CoupleStringInt("Hors HDJ", 1));	 	 	
		Soin EchodopplerTSAetMI = Soin.creerSoin(donnees, listRessourceCapacite26, "Echodoppler TSA et MI", 30);

		ArrayList<CoupleStringInt> listRessourceCapacite27 = new ArrayList<>();	 
		listRessourceCapacite27.add(new CoupleStringInt("Hors HDJ", 1));	 	 	
		Soin ScannerDesCorronaires = Soin.creerSoin(donnees, listRessourceCapacite27, "Scanner Des Corronaires", 10);

		ArrayList<CoupleStringInt> listRessourceCapacite28 = new ArrayList<>();	 
		listRessourceCapacite28.add(new CoupleStringInt("Hors HDJ", 1));	 	 	
		Soin ScintigraphieMyocardique = Soin.creerSoin(donnees, listRessourceCapacite28, "Scintigraphie Myocardique", 30);
		//System.out.println("done");
		ArrayList<CoupleStringInt> listRessourceCapacite29 = new ArrayList<>();	 
		listRessourceCapacite29.add(new CoupleStringInt("Hors HDJ", 1));	 	 	
		Soin Injection = Soin.creerSoin(donnees, listRessourceCapacite29, "Injection", 40);

		ArrayList<CoupleStringInt> listRessourceCapacite30 = new ArrayList<>();
		listRessourceCapacite30.add(new CoupleStringInt("Diabetologue", 1));
		listRessourceCapacite30.add(new CoupleStringInt("Bureau CS", 1));	 	 
		Soin RDVMedical40 = Soin.creerSoin(donnees, listRessourceCapacite30, "RDV Medical40", 40);

		ArrayList<CoupleStringInt> listRessourceCapacite31 = new ArrayList<>();
		listRessourceCapacite31.add(new CoupleStringInt("Dieteticien", 1));
		listRessourceCapacite31.add(new CoupleStringInt("Bureau CS", 1));	 	 
		Soin EntretienDiet30 = Soin.creerSoin(donnees, listRessourceCapacite31, "Entretien Diet30", 30);

		ArrayList<CoupleStringInt> listRessourceCapacite32 = new ArrayList<>();
		listRessourceCapacite32.add(new CoupleStringInt("Diabetologue", 1));
		listRessourceCapacite32.add(new CoupleStringInt("Bureau CS", 1));	 	 
		Soin RDVMedical35 = Soin.creerSoin(donnees, listRessourceCapacite32, "RDV Medical35", 35);

		ArrayList<CoupleStringInt> listRessourceCapacite33 = new ArrayList<>();
		listRessourceCapacite33.add(new CoupleStringInt("IDE", 1));
		listRessourceCapacite33.add(new CoupleStringInt("Box Prelevement", 1));	 	 
		Soin Prelevement15 = Soin.creerSoin(donnees, listRessourceCapacite33, "Prelevement15", 15);

		ArrayList<CoupleStringInt> listRessourceCapacite34 = new ArrayList<>();	 
		listRessourceCapacite34.add(new CoupleStringInt("Hors HDJ", 1));	 	 	
		Soin ScannerTMDPiedRadios = Soin.creerSoin(donnees, listRessourceCapacite34, "Scanner TMD Pied + Radios", 40);

		ArrayList<CoupleStringInt> listRessourceCapacite35 = new ArrayList<>();	 
		listRessourceCapacite35.add(new CoupleStringInt("Hors HDJ", 1));	 	 	
		Soin DopplerDesArteresDesMI = Soin.creerSoin(donnees, listRessourceCapacite35, "Doppler Des Arteres Des MI", 30);

		ArrayList<CoupleStringInt> listRessourceCapacite36 = new ArrayList<>();
		listRessourceCapacite36.add(new CoupleStringInt("Podologue", 1));
		listRessourceCapacite36.add(new CoupleStringInt("IDE Pansement", 1));
		listRessourceCapacite36.add(new CoupleStringInt("Medecin", 1));
		listRessourceCapacite36.add(new CoupleStringInt("Salle Pansement", 1));	
		Soin SoinPansementMesuresIPS = Soin.creerSoin(donnees, listRessourceCapacite36, "Soin, Pansement, Mesures IPS", 40);
		//System.out.println("done");
		ArrayList<CoupleStringInt> listRessourceCapacite37 = new ArrayList<>();
		listRessourceCapacite37.add(new CoupleStringInt("IDE Pompe Insuline", 1));
		listRessourceCapacite37.add(new CoupleStringInt("Diabetologue", 1));
		listRessourceCapacite37.add(new CoupleStringInt("Bureau CS", 1));	 	
		Soin PosePompeEtOuHolter = Soin.creerSoin(donnees, listRessourceCapacite37, "Pose Pompe Et/Ou Holter", 30);

		ArrayList<CoupleStringInt> listRessourceCapacite38 = new ArrayList<>();
		listRessourceCapacite38.add(new CoupleStringInt("Medecin Sommeil", 1));
		listRessourceCapacite38.add(new CoupleStringInt("Bureau CS", 1));	 	 
		Soin RDVMedical20BurCS = Soin.creerSoin(donnees, listRessourceCapacite38, "RDV Medical20BurCS", 20);

		ArrayList<CoupleStringInt> listRessourceCapacite39 = new ArrayList<>();
		listRessourceCapacite39.add(new CoupleStringInt("Psychologue", 1));
		listRessourceCapacite39.add(new CoupleStringInt("Bureau CS", 1));	 	 
		Soin ConsultationPsy20 = Soin.creerSoin(donnees, listRessourceCapacite39, "Consultation Psy20", 20);

		ArrayList<CoupleStringInt> listRessourceCapacite40 = new ArrayList<>();
		listRessourceCapacite40.add(new CoupleStringInt("IDE Sommeil", 1));
		listRessourceCapacite40.add(new CoupleStringInt("Explorations Fonctionnelles", 1));	 
		Soin Examens30 = Soin.creerSoin(donnees, listRessourceCapacite40, "Examens30", 30);
		//System.out.println("done");
		ArrayList<CoupleStringInt> listRessourceCapacite41 = new ArrayList<>();
		listRessourceCapacite41.add(new CoupleStringInt("IDE Sommeil", 1));
		listRessourceCapacite41.add(new CoupleStringInt("Piece Isolee Avec Fauteuil", 1));	 
		Soin Appareillage60 = Soin.creerSoin(donnees, listRessourceCapacite41, "Appareillage60", 60);
		//System.out.println("done");
		ArrayList<CoupleStringInt> listRessourceCapacite42 = new ArrayList<>();
		listRessourceCapacite42.add(new CoupleStringInt("Medecin Sommeil", 1));
		listRessourceCapacite42.add(new CoupleStringInt("Bureau Sommeil", 1));	 	 
		Soin RDVMedical20BurSom = Soin.creerSoin(donnees, listRessourceCapacite42, "RDV Medical20BurSom", 20);

		ArrayList<CoupleStringInt> listRessourceCapacite43 = new ArrayList<>();
		listRessourceCapacite43.add(new CoupleStringInt("Medecin Sommeil", 1));
		listRessourceCapacite43.add(new CoupleStringInt("Psychologue", 1));
		listRessourceCapacite43.add(new CoupleStringInt("Prestataire", 1));
		listRessourceCapacite43.add(new CoupleStringInt("Salle ETP Groupe", 1));	
		Soin ETPSom = Soin.creerSoin(donnees, listRessourceCapacite43, "ETPSom", 90);

		ArrayList<CoupleStringInt> listRessourceCapacite44 = new ArrayList<>();
		listRessourceCapacite44.add(new CoupleStringInt("IDE Sommeil", 1));
		listRessourceCapacite44.add(new CoupleStringInt("Prestataire", 1));
		listRessourceCapacite44.add(new CoupleStringInt("Salle Avec Lit", 1));	 	
		Soin Appareillage45 = Soin.creerSoin(donnees, listRessourceCapacite44, "Appareillage45", 45);

		ArrayList<CoupleStringInt> listRessourceCapacite45 = new ArrayList<>();
		listRessourceCapacite45.add(new CoupleStringInt("IDE", 1));
		listRessourceCapacite45.add(new CoupleStringInt("Box Prelevement", 1));	 	 
		Soin BilanBiologique20 = Soin.creerSoin(donnees, listRessourceCapacite45, "Bilan Biologique20", 20);
		ArrayList<CoupleStringInt> listRessourceCapacite46 = new ArrayList<>();
		listRessourceCapacite46.add(new CoupleStringInt("IDE", 1));
		listRessourceCapacite46.add(new CoupleStringInt("Box", 1));	 	 	
		Soin ECG10 = Soin.creerSoin(donnees, listRessourceCapacite46, "ECG10", 10);

		ArrayList<CoupleStringInt> listRessourceCapacite47 = new ArrayList<>();	 	 	 
		Soin TestDEfforts = Soin.creerSoin(donnees, listRessourceCapacite47, "Test D'Efforts", 25);

		ArrayList<CoupleStringInt> listRessourceCapacite48 = new ArrayList<>();
		listRessourceCapacite48.add(new CoupleStringInt("Selon Profil", 1));
		listRessourceCapacite48.add(new CoupleStringInt("Bureau CS", 1));	 	 
		Soin Consultations = Soin.creerSoin(donnees, listRessourceCapacite48, "Consultations", 30);

		ArrayList<CoupleStringInt> listRessourceCapacite49 = new ArrayList<>();	 
		listRessourceCapacite49.add(new CoupleStringInt("Hors HDJ", 1));	 	 
		Soin ExplorationsFonctionnellesOuMorphologiques = Soin.creerSoin(donnees, listRessourceCapacite49, "Explorations Fonctionnelles Ou Morphologiques", 40);

		ArrayList<CoupleStringInt> listRessourceCapacite50 = new ArrayList<>();
		listRessourceCapacite50.add(new CoupleStringInt("Cardiologue", 1));
		listRessourceCapacite50.add(new CoupleStringInt("Bureau CS", 1));	 	 
		Soin Bilan = Soin.creerSoin(donnees, listRessourceCapacite50, "Bilan", 30);

		ArrayList<CoupleStringInt> listRessourceCapacite51 = new ArrayList<>();
		listRessourceCapacite51.add(new CoupleStringInt("Kine", 1));
		listRessourceCapacite51.add(new CoupleStringInt("Bureau CS", 1));	 	 
		Soin TestFonctionnel = Soin.creerSoin(donnees, listRessourceCapacite51, "Test Fonctionnel", 20);

		ArrayList<CoupleStringInt> listRessourceCapacite52 = new ArrayList<>();	 
		listRessourceCapacite52.add(new CoupleStringInt("Hors HDJ", 1));	 	 
		Soin IRM40 = Soin.creerSoin(donnees, listRessourceCapacite52, "IRM40", 40);

		ArrayList<CoupleStringInt> listRessourceCapacite53 = new ArrayList<>();	 
		listRessourceCapacite53.add(new CoupleStringInt("Hors HDJ", 1));	 	 
		Soin EchoCardiaque = Soin.creerSoin(donnees, listRessourceCapacite53, "Echo Cardiaque", 20);

		ArrayList<CoupleStringInt> listRessourceCapacite54 = new ArrayList<>();
		listRessourceCapacite54.add(new CoupleStringInt("Cardiologue", 1));
		listRessourceCapacite54.add(new CoupleStringInt("Bureau CS", 1));	 	 
		Soin ExamenClinique30 = Soin.creerSoin(donnees, listRessourceCapacite54, "Examen Clinique30", 30);

		ArrayList<CoupleStringInt> listRessourceCapacite55 = new ArrayList<>();
		listRessourceCapacite55.add(new CoupleStringInt("IDE Cardio", 1));
		listRessourceCapacite55.add(new CoupleStringInt("Bureau CS", 1));	 	 
		Soin ETPCard = Soin.creerSoin(donnees, listRessourceCapacite55, "ETPCard", 30);

		ArrayList<CoupleStringInt> listRessourceCapacite56 = new ArrayList<>();	 
		listRessourceCapacite56.add(new CoupleStringInt("Hors HDJ", 1));	 	 
		Soin MedecineNucleaire = Soin.creerSoin(donnees, listRessourceCapacite56, "Medecine Nucleaire", 30);

		ArrayList<CoupleStringInt> listRessourceCapacite57 = new ArrayList<>();
		listRessourceCapacite57.add(new CoupleStringInt("Agent Accueil", 1));	 	 	 
		Soin RDVAccueil = Soin.creerSoin(donnees, listRessourceCapacite57, "RDV Accueil", 5);

		ArrayList<CoupleStringInt> listRessourceCapacite58 = new ArrayList<>();
		listRessourceCapacite58.add(new CoupleStringInt("IDE", 1));
		listRessourceCapacite58.add(new CoupleStringInt("Box Prelevement", 1));	 	 
		Soin Prelevement20 = Soin.creerSoin(donnees, listRessourceCapacite58, "Prelevement20", 20);

		ArrayList<CoupleStringInt> listRessourceCapacite59 = new ArrayList<>();	 
		listRessourceCapacite59.add(new CoupleStringInt("Hors HDJ", 1));	 	 
		Soin IRM45 = Soin.creerSoin(donnees, listRessourceCapacite59, "IRM45", 45);

		ArrayList<CoupleStringInt> listRessourceCapacite60 = new ArrayList<>();	 
		listRessourceCapacite60.add(new CoupleStringInt("Hors HDJ", 1));	 	 
		Soin ARM = Soin.creerSoin(donnees, listRessourceCapacite60, "ARM", 15);

		ArrayList<CoupleStringInt> listRessourceCapacite61 = new ArrayList<>();	 
		listRessourceCapacite61.add(new CoupleStringInt("Hors HDJ", 1));	 	 
		Soin EchodopplerTSA = Soin.creerSoin(donnees, listRessourceCapacite61, "Echodoppler TSA", 20);

		ArrayList<CoupleStringInt> listRessourceCapacite62 = new ArrayList<>();
		listRessourceCapacite62.add(new CoupleStringInt("Neurologue", 1));
		listRessourceCapacite62.add(new CoupleStringInt("Box Soin", 1));	 	 
		Soin ETT = Soin.creerSoin(donnees, listRessourceCapacite62, "ETT", 20);

		ArrayList<CoupleStringInt> listRessourceCapacite63 = new ArrayList<>();
		listRessourceCapacite63.add(new CoupleStringInt("IDE Cardio", 1));
		listRessourceCapacite63.add(new CoupleStringInt("Box Soin", 1));	 	 
		Soin Holter = Soin.creerSoin(donnees, listRessourceCapacite63, "Holter", 30);

		ArrayList<CoupleStringInt> listRessourceCapacite64 = new ArrayList<>();
		listRessourceCapacite64.add(new CoupleStringInt("Cardiologue", 1));
		listRessourceCapacite64.add(new CoupleStringInt("Bureau CS", 1));	 	 
		Soin BilanCardiaque = Soin.creerSoin(donnees, listRessourceCapacite64, "Bilan Cardiaque", 20);

		ArrayList<CoupleStringInt> listRessourceCapacite65 = new ArrayList<>();
		listRessourceCapacite65.add(new CoupleStringInt("Neurologue", 1));
		listRessourceCapacite65.add(new CoupleStringInt("Bureau CS", 1));	 	 
		Soin SyntheseNeuro = Soin.creerSoin(donnees, listRessourceCapacite65, "SyntheseNeuro", 30);

		ArrayList<CoupleStringInt> listRessourceCapacite66 = new ArrayList<>();
		listRessourceCapacite66.add(new CoupleStringInt("Neurologue", 1));
		listRessourceCapacite66.add(new CoupleStringInt("Bureau CS", 1));	 	 
		Soin ExamenClinique15 = Soin.creerSoin(donnees, listRessourceCapacite66, "Examen Clinique15", 15);

		ArrayList<CoupleStringInt> listRessourceCapacite67 = new ArrayList<>();	 
		listRessourceCapacite67.add(new CoupleStringInt("Hors HDJ", 1));	 	 
		Soin EEG = Soin.creerSoin(donnees, listRessourceCapacite67, "EEG", 40);

		ArrayList<CoupleStringInt> listRessourceCapacite68 = new ArrayList<>();	 
		listRessourceCapacite68.add(new CoupleStringInt("Hors HDJ", 1));	 	 
		Soin ScintigraphieCerebrale = Soin.creerSoin(donnees, listRessourceCapacite68, "Scintigraphie Cérébrale", 40);

		ArrayList<CoupleStringInt> listRessourceCapacite69 = new ArrayList<>();
		listRessourceCapacite69.add(new CoupleStringInt("Generaliste", 1));
		listRessourceCapacite69.add(new CoupleStringInt("Box Soin", 1));	 	 
		Soin PonctionLombaire = Soin.creerSoin(donnees, listRessourceCapacite69, "Ponction Lombaire", 15);

		ArrayList<CoupleStringInt> listRessourceCapacite70 = new ArrayList<>();
		listRessourceCapacite70.add(new CoupleStringInt("IDE", 1));
		listRessourceCapacite70.add(new CoupleStringInt("Box Soin", 1));	 	 
		Soin SoinsPonction3h = Soin.creerSoin(donnees, listRessourceCapacite70, "Soins Ponction3h", 180);

		ArrayList<CoupleStringInt> listRessourceCapacite71 = new ArrayList<>();
		listRessourceCapacite71.add(new CoupleStringInt("Neuropsy", 1));
		listRessourceCapacite71.add(new CoupleStringInt("Bureau CS", 1));	 	 
		Soin ConsultationNeuropsy = Soin.creerSoin(donnees, listRessourceCapacite71, "Consultation Neuropsy", 180);

		ArrayList<CoupleStringInt> listRessourceCapacite72 = new ArrayList<>();
		listRessourceCapacite72.add(new CoupleStringInt("Psychologue", 1));
		listRessourceCapacite72.add(new CoupleStringInt("Bureau CS", 1));	 	 
		Soin ConsultationPsy30 = Soin.creerSoin(donnees, listRessourceCapacite72, "Consultation Psy30", 30);	 	 



		// On crée le Parcours 1
		Soin[] G1P1S = {RDVParamedical20};
		GroupeSoins G1P1 = new GroupeSoins(G1P1S);
		Soin[] G2P1S = {ECG15, BilanBiologique15, Calorimetrie,EchoHepathique };
		GroupeSoins G2P1 = new GroupeSoins(G2P1S);
		Soin[] G3P1S = {Collation};
		GroupeSoins G3P1 = new GroupeSoins(G3P1S);
		Soin[] G4P1S = {EntretienPsy, EntretienInfirmier, EntretienDiet60};
		GroupeSoins G4P1 = new GroupeSoins(G4P1S);
		Soin[] G5P1S = {SyntheseNutriHDJOb};
		GroupeSoins G5P1 = new GroupeSoins(G5P1S);
		GroupeSoins[] P1G = {G1P1, G2P1, G3P1, G4P1, G5P1};
		Parcours P1  = new Parcours(P1G, 1);

		// On crée le Parcours 2
		Soin[] G1P2S = {RDVParamedical20};
		GroupeSoins G1P2 = new GroupeSoins(G1P2S);
		Soin[] G2P2S = {BilanBiologique15, TOGD, Calorimetrie};
		GroupeSoins G2P2 = new GroupeSoins(G2P2S);
		Soin[] G3P2S = {Collation};
		GroupeSoins G3P2 = new GroupeSoins(G3P2S);
		Soin[] G4P2S = {BilanAnthropometrique};
		GroupeSoins G4P2 = new GroupeSoins(G4P2S);
		Soin[] G5P2S = {EntretienPsy, EntretienInfirmier, EntretienDiet60};
		GroupeSoins G5P2 = new GroupeSoins(G5P2S);
		Soin[] G6P2S = {SyntheseNutriHDJOb};
		GroupeSoins G6P2 = new GroupeSoins(G6P2S);
		GroupeSoins[] P2G = {G1P2, G2P2, G3P2, G4P2, G5P2, G6P2};
		Parcours P2  = new Parcours(P2G, 2);

		// On crée le Parcours 3
		Soin[] G1P3S = {RDVParamedical20};
		GroupeSoins G1P3 = new GroupeSoins(G1P3S);
		Soin[] G2P3S = {BilanBiologique15, EchoHepathique, Calorimetrie};
		GroupeSoins G2P3 = new GroupeSoins(G2P3S);
		Soin[] G3P3S = {Collation};
		GroupeSoins G3P3 = new GroupeSoins(G3P3S);
		Soin[] G4P3S = {BilanAnthropometrique, Fibroscan};
		GroupeSoins G4P3 = new GroupeSoins(G4P3S);
		Soin[] G5P3S = {EntretienPsy, EntretienInfirmier, EntretienDiet60};
		GroupeSoins G5P3 = new GroupeSoins(G5P3S);
		Soin[] G6P3S = {SyntheseNutriHDJOb};
		GroupeSoins G6P3 = new GroupeSoins(G6P3S);
		GroupeSoins[] P3G = {G1P3, G2P3, G3P3, G4P3, G5P3, G6P3};
		Parcours P3  = new Parcours(P3G, 3);

		// On crée le Parcours 4
		Soin[] G1P4S = {RDVParamedical15};
		GroupeSoins G1P4 = new GroupeSoins(G1P4S);
		Soin[] G2P4S = {ECG15, Calorimetrie};
		GroupeSoins G2P4 = new GroupeSoins(G2P4S);
		Soin[] G3P4S = {Collation};
		GroupeSoins G3P4= new GroupeSoins(G3P4S);
		Soin[] G4P4S = {ScannerAbdo, BilanAnthropometrique, Fibroscan};
		GroupeSoins G4P4 = new GroupeSoins(G4P4S);
		Soin[] G5P4S = {EntretienInfirmier, EntretienDiet60};
		GroupeSoins G5P4 = new GroupeSoins(G5P4S);
		Soin[] G6P4S = {SyntheseNutriHDJOb};
		GroupeSoins G6P4 = new GroupeSoins(G6P4S);
		GroupeSoins[] P4G = {G1P4, G2P4, G3P4, G4P4, G5P4, G6P4};
		Parcours P4  = new Parcours(P4G, 4);

		// On crée le Parcours 5
		Soin[] G1P5S = {RDVParamedical15};
		GroupeSoins G1P5 = new GroupeSoins(G1P5S);
		Soin[] G2P5S = {BilanBiologique15, EchoHepathique};
		GroupeSoins G2P5 = new GroupeSoins(G2P5S);
		Soin[] G3P5S = {Collation};
		GroupeSoins G3P5 = new GroupeSoins(G3P5S);
		Soin[] G4P5S = {Fibroscan};
		GroupeSoins G4P5 = new GroupeSoins(G4P5S);
		Soin[] G5P5S = {EntretienHepato};
		GroupeSoins G5P5 = new GroupeSoins(G5P5S);
		GroupeSoins[] P5G = {G1P5, G2P5, G3P5, G4P5, G5P5};
		Parcours P5  = new Parcours(P5G, 5);

		// On crée le Parcours 6
		Soin[] G1P6S = {RDVParamedical15};
		GroupeSoins G1P6 = new GroupeSoins(G1P6S);
		Soin[] G2P6S = {BilanBiologique15};
		GroupeSoins G2P6 = new GroupeSoins(G2P6S);
		Soin[] G3P6S = {Collation};
		GroupeSoins G3P6 = new GroupeSoins(G3P6S);
		Soin[] G4P6S = {EntretienHepato};
		GroupeSoins G4P6 = new GroupeSoins(G4P6S);
		Soin[] G5P6S = {TraitementRemicade};
		GroupeSoins G5P6 = new GroupeSoins(G5P6S);
		Soin[] G6P6S = {SyntheseNutriCS};
		GroupeSoins G6P6 = new GroupeSoins(G6P6S);
		GroupeSoins[] P6G = {G1P6, G2P6, G3P6, G4P6, G5P6, G6P6};
		Parcours P6  = new Parcours(P6G, 6);


		//Pas de parcours 7 fourni


		// On crée le Parcours 8
		Soin[] G1P8S = {RDVParamedical15};
		GroupeSoins G1P8 = new GroupeSoins(G1P8S);
		Soin[] G2P8S = {BilanBiologique15};
		GroupeSoins G2P8 = new GroupeSoins(G2P8S);
		Soin[] G3P8S = {Collation};
		GroupeSoins G3P8 = new GroupeSoins(G3P8S);
		Soin[] G4P8S = {EntretienHepato};
		GroupeSoins G4P8 = new GroupeSoins(G4P8S);
		Soin[] G5P8S = {PonctionAscite};
		GroupeSoins G5P8 = new GroupeSoins(G5P8S);
		Soin[] G6P8S = {SoinsPonction2h15};
		GroupeSoins G6P8 = new GroupeSoins(G6P8S);
		Soin[] G7P8S = {SyntheseNutriCS};
		GroupeSoins G7P8 = new GroupeSoins(G7P8S);
		GroupeSoins[] P8G = {G1P8, G2P8, G3P8, G4P8, G5P8, G6P8, G7P8};
		Parcours P8  = new Parcours(P8G, 8);

		// On crée le Parcours 9
		Soin[] G1P9S = {RDVParamedical15};
		GroupeSoins G1P9 = new GroupeSoins(G1P9S);
		Soin[] G2P9S = {BilanBiologique15};
		GroupeSoins G2P9 = new GroupeSoins(G2P9S);
		Soin[] G3P9S = {Collation};
		GroupeSoins G3P9 = new GroupeSoins(G3P9S);
		Soin[] G4P9S = {EntretienHepato};
		GroupeSoins G4P9 = new GroupeSoins(G4P9S);
		Soin[] G5P9S = {InjectionFerinject};
		GroupeSoins G5P9 = new GroupeSoins(G5P9S);
		Soin[] G6P9S = {SyntheseNutriCS};
		GroupeSoins G6P9 = new GroupeSoins(G6P9S);
		GroupeSoins[] P9G = {G1P9, G2P9, G3P9, G4P9, G5P9, G6P9};
		Parcours P9  = new Parcours(P9G, 9);

		// On crée le Parcours 10
		Soin[] G1P10S = {Prelevement25};
		GroupeSoins G1P10 = new GroupeSoins(G1P10S);
		Soin[] G2P10S = {Collation};
		GroupeSoins G2P10 = new GroupeSoins(G2P10S);
		Soin[] G3P10S = {ECG15, PoseHolter, Retinographie, EchodopplerTSAetMI, ScannerDesCorronaires, ScintigraphieMyocardique, Injection};
		GroupeSoins G3P10 = new GroupeSoins(G3P10S);
		Soin[] G4P10S = {RDVMedical40};
		GroupeSoins G4P10 = new GroupeSoins(G4P10S);
		GroupeSoins[] P10G = {G1P10, G2P10, G3P10, G4P10};
		Parcours P10  = new Parcours(P10G, 10);

		// On crée le Parcours 7
		/*		Soin[] G1P7S = {RDVParamedical20};
		GroupeSoins G1P7 = new GroupeSoins(G1P7S);
		Soin[] G2P7S = {BilanBiologique15, EchoHepathique, Calorimetrie};
		GroupeSoins G2P7 = new GroupeSoins(G2P7S);
		Soin[] G3P7S = {Collation};
		GroupeSoins G3P7 = new GroupeSoins(G3P7S);
		Soin[] G4P7S = {BilanAnthropometrique, Fibroscan};
		GroupeSoins G4P7 = new GroupeSoins(G4P7S);
		Soin[] G5P7S = {EntretienPsy, EntretienInfirmier, EntretienDiet60};
		GroupeSoins G5P7 = new GroupeSoins(G5P7S);
		Soin[] G6P7S = {Synthese};
		GroupeSoins G6P7 = new GroupeSoins(G6P7S);
		GroupeSoins[] P7G = {G1P7, G2P7, G3P7, G4P7, G5P7, G6P7};
		Parcours P7  = new Parcours(P7G, 7);

		// On crée le Parcours 7
		Soin[] G1P7S = {RDVParamedical20};
		GroupeSoins G1P7 = new GroupeSoins(G1P7S);
		Soin[] G2P7S = {BilanBiologique15, EchoHepathique, Calorimetrie};
		GroupeSoins G2P7 = new GroupeSoins(G2P7S);
		Soin[] G3P7S = {Collation};
		GroupeSoins G3P7 = new GroupeSoins(G3P7S);
		Soin[] G4P7S = {BilanAnthropometrique, Fibroscan};
		GroupeSoins G4P7 = new GroupeSoins(G4P7S);
		Soin[] G5P7S = {EntretienPsy, EntretienInfirmier, EntretienDiet60};
		GroupeSoins G5P7 = new GroupeSoins(G5P7S);
		Soin[] G6P7S = {Synthese};
		GroupeSoins G6P7 = new GroupeSoins(G6P7S);
		GroupeSoins[] P7G = {G1P7, G2P7, G3P7, G4P7, G5P7, G6P7};
		Parcours P7  = new Parcours(P7G, 7);

		// On crée le Parcours 7
		Soin[] G1P7S = {RDVParamedical20};
		GroupeSoins G1P7 = new GroupeSoins(G1P7S);
		Soin[] G2P7S = {BilanBiologique15, EchoHepathique, Calorimetrie};
		GroupeSoins G2P7 = new GroupeSoins(G2P7S);
		Soin[] G3P7S = {Collation};
		GroupeSoins G3P7 = new GroupeSoins(G3P7S);
		Soin[] G4P7S = {BilanAnthropometrique, Fibroscan};
		GroupeSoins G4P7 = new GroupeSoins(G4P7S);
		Soin[] G5P7S = {EntretienPsy, EntretienInfirmier, EntretienDiet60};
		GroupeSoins G5P7 = new GroupeSoins(G5P7S);
		Soin[] G6P7S = {Synthese};
		GroupeSoins G6P7 = new GroupeSoins(G6P7S);
		GroupeSoins[] P7G = {G1P7, G2P7, G3P7, G4P7, G5P7, G6P7};
		Parcours P7  = new Parcours(P7G, 7);

		// On crée le Parcours 7
		Soin[] G1P7S = {RDVParamedical20};
		GroupeSoins G1P7 = new GroupeSoins(G1P7S);
		Soin[] G2P7S = {BilanBiologique15, EchoHepathique, Calorimetrie};
		GroupeSoins G2P7 = new GroupeSoins(G2P7S);
		Soin[] G3P7S = {Collation};
		GroupeSoins G3P7 = new GroupeSoins(G3P7S);
		Soin[] G4P7S = {BilanAnthropometrique, Fibroscan};
		GroupeSoins G4P7 = new GroupeSoins(G4P7S);
		Soin[] G5P7S = {EntretienPsy, EntretienInfirmier, EntretienDiet60};
		GroupeSoins G5P7 = new GroupeSoins(G5P7S);
		Soin[] G6P7S = {Synthese};
		GroupeSoins G6P7 = new GroupeSoins(G6P7S);
		GroupeSoins[] P7G = {G1P7, G2P7, G3P7, G4P7, G5P7, G6P7};
		Parcours P7  = new Parcours(P7G, 7);

		// On crée le Parcours 7
		Soin[] G1P7S = {RDVParamedical20};
		GroupeSoins G1P7 = new GroupeSoins(G1P7S);
		Soin[] G2P7S = {BilanBiologique15, EchoHepathique, Calorimetrie};
		GroupeSoins G2P7 = new GroupeSoins(G2P7S);
		Soin[] G3P7S = {Collation};
		GroupeSoins G3P7 = new GroupeSoins(G3P7S);
		Soin[] G4P7S = {BilanAnthropometrique, Fibroscan};
		GroupeSoins G4P7 = new GroupeSoins(G4P7S);
		Soin[] G5P7S = {EntretienPsy, EntretienInfirmier, EntretienDiet60};
		GroupeSoins G5P7 = new GroupeSoins(G5P7S);
		Soin[] G6P7S = {Synthese};
		GroupeSoins G6P7 = new GroupeSoins(G6P7S);
		GroupeSoins[] P7G = {G1P7, G2P7, G3P7, G4P7, G5P7, G6P7};
		Parcours P7  = new Parcours(P7G, 7);

		// On crée le Parcours 7
		Soin[] G1P7S = {RDVParamedical20};
		GroupeSoins G1P7 = new GroupeSoins(G1P7S);
		Soin[] G2P7S = {BilanBiologique15, EchoHepathique, Calorimetrie};
		GroupeSoins G2P7 = new GroupeSoins(G2P7S);
		Soin[] G3P7S = {Collation};
		GroupeSoins G3P7 = new GroupeSoins(G3P7S);
		Soin[] G4P7S = {BilanAnthropometrique, Fibroscan};
		GroupeSoins G4P7 = new GroupeSoins(G4P7S);
		Soin[] G5P7S = {EntretienPsy, EntretienInfirmier, EntretienDiet60};
		GroupeSoins G5P7 = new GroupeSoins(G5P7S);
		Soin[] G6P7S = {Synthese};
		GroupeSoins G6P7 = new GroupeSoins(G6P7S);
		GroupeSoins[] P7G = {G1P7, G2P7, G3P7, G4P7, G5P7, G6P7};
		Parcours P7  = new Parcours(P7G, 7);

		// On crée le Parcours 7
		Soin[] G1P7S = {RDVParamedical20};
		GroupeSoins G1P7 = new GroupeSoins(G1P7S);
		Soin[] G2P7S = {BilanBiologique15, EchoHepathique, Calorimetrie};
		GroupeSoins G2P7 = new GroupeSoins(G2P7S);
		Soin[] G3P7S = {Collation};
		GroupeSoins G3P7 = new GroupeSoins(G3P7S);
		Soin[] G4P7S = {BilanAnthropometrique, Fibroscan};
		GroupeSoins G4P7 = new GroupeSoins(G4P7S);
		Soin[] G5P7S = {EntretienPsy, EntretienInfirmier, EntretienDiet60};
		GroupeSoins G5P7 = new GroupeSoins(G5P7S);
		Soin[] G6P7S = {Synthese};
		GroupeSoins G6P7 = new GroupeSoins(G6P7S);
		GroupeSoins[] P7G = {G1P7, G2P7, G3P7, G4P7, G5P7, G6P7};
		Parcours P7  = new Parcours(P7G, 7);

		// On crée le Parcours 7
		Soin[] G1P7S = {RDVParamedical20};
		GroupeSoins G1P7 = new GroupeSoins(G1P7S);
		Soin[] G2P7S = {BilanBiologique15, EchoHepathique, Calorimetrie};
		GroupeSoins G2P7 = new GroupeSoins(G2P7S);
		Soin[] G3P7S = {Collation};
		GroupeSoins G3P7 = new GroupeSoins(G3P7S);
		Soin[] G4P7S = {BilanAnthropometrique, Fibroscan};
		GroupeSoins G4P7 = new GroupeSoins(G4P7S);
		Soin[] G5P7S = {EntretienPsy, EntretienInfirmier, EntretienDiet60};
		GroupeSoins G5P7 = new GroupeSoins(G5P7S);
		Soin[] G6P7S = {Synthese};
		GroupeSoins G6P7 = new GroupeSoins(G6P7S);
		GroupeSoins[] P7G = {G1P7, G2P7, G3P7, G4P7, G5P7, G6P7};
		Parcours P7  = new Parcours(P7G, 7);

		// On crée le Parcours 7
		Soin[] G1P7S = {RDVParamedical20};
		GroupeSoins G1P7 = new GroupeSoins(G1P7S);
		Soin[] G2P7S = {BilanBiologique15, EchoHepathique, Calorimetrie};
		GroupeSoins G2P7 = new GroupeSoins(G2P7S);
		Soin[] G3P7S = {Collation};
		GroupeSoins G3P7 = new GroupeSoins(G3P7S);
		Soin[] G4P7S = {BilanAnthropometrique, Fibroscan};
		GroupeSoins G4P7 = new GroupeSoins(G4P7S);
		Soin[] G5P7S = {EntretienPsy, EntretienInfirmier, EntretienDiet60};
		GroupeSoins G5P7 = new GroupeSoins(G5P7S);
		Soin[] G6P7S = {Synthese};
		GroupeSoins G6P7 = new GroupeSoins(G6P7S);
		GroupeSoins[] P7G = {G1P7, G2P7, G3P7, G4P7, G5P7, G6P7};
		Parcours P7  = new Parcours(P7G, 7);

		// On crée le Parcours 7
		Soin[] G1P7S = {RDVParamedical20};
		GroupeSoins G1P7 = new GroupeSoins(G1P7S);
		Soin[] G2P7S = {BilanBiologique15, EchoHepathique, Calorimetrie};
		GroupeSoins G2P7 = new GroupeSoins(G2P7S);
		Soin[] G3P7S = {Collation};
		GroupeSoins G3P7 = new GroupeSoins(G3P7S);
		Soin[] G4P7S = {BilanAnthropometrique, Fibroscan};
		GroupeSoins G4P7 = new GroupeSoins(G4P7S);
		Soin[] G5P7S = {EntretienPsy, EntretienInfirmier, EntretienDiet60};
		GroupeSoins G5P7 = new GroupeSoins(G5P7S);
		Soin[] G6P7S = {Synthese};
		GroupeSoins G6P7 = new GroupeSoins(G6P7S);
		GroupeSoins[] P7G = {G1P7, G2P7, G3P7, G4P7, G5P7, G6P7};
		Parcours P7  = new Parcours(P7G, 7);

		// On crée le Parcours 7
		Soin[] G1P7S = {RDVParamedical20};
		GroupeSoins G1P7 = new GroupeSoins(G1P7S);
		Soin[] G2P7S = {BilanBiologique15, EchoHepathique, Calorimetrie};
		GroupeSoins G2P7 = new GroupeSoins(G2P7S);
		Soin[] G3P7S = {Collation};
		GroupeSoins G3P7 = new GroupeSoins(G3P7S);
		Soin[] G4P7S = {BilanAnthropometrique, Fibroscan};
		GroupeSoins G4P7 = new GroupeSoins(G4P7S);
		Soin[] G5P7S = {EntretienPsy, EntretienInfirmier, EntretienDiet60};
		GroupeSoins G5P7 = new GroupeSoins(G5P7S);
		Soin[] G6P7S = {Synthese};
		GroupeSoins G6P7 = new GroupeSoins(G6P7S);
		GroupeSoins[] P7G = {G1P7, G2P7, G3P7, G4P7, G5P7, G6P7};
		Parcours P7  = new Parcours(P7G, 7);

		// On crée le Parcours 7
		Soin[] G1P7S = {RDVParamedical20};
		GroupeSoins G1P7 = new GroupeSoins(G1P7S);
		Soin[] G2P7S = {BilanBiologique15, EchoHepathique, Calorimetrie};
		GroupeSoins G2P7 = new GroupeSoins(G2P7S);
		Soin[] G3P7S = {Collation};
		GroupeSoins G3P7 = new GroupeSoins(G3P7S);
		Soin[] G4P7S = {BilanAnthropometrique, Fibroscan};
		GroupeSoins G4P7 = new GroupeSoins(G4P7S);
		Soin[] G5P7S = {EntretienPsy, EntretienInfirmier, EntretienDiet60};
		GroupeSoins G5P7 = new GroupeSoins(G5P7S);
		Soin[] G6P7S = {Synthese};
		GroupeSoins G6P7 = new GroupeSoins(G6P7S);
		GroupeSoins[] P7G = {G1P7, G2P7, G3P7, G4P7, G5P7, G6P7};
		Parcours P7  = new Parcours(P7G, 7);

		// On crée le Parcours 7
		Soin[] G1P7S = {RDVParamedical20};
		GroupeSoins G1P7 = new GroupeSoins(G1P7S);
		Soin[] G2P7S = {BilanBiologique15, EchoHepathique, Calorimetrie};
		GroupeSoins G2P7 = new GroupeSoins(G2P7S);
		Soin[] G3P7S = {Collation};
		GroupeSoins G3P7 = new GroupeSoins(G3P7S);
		Soin[] G4P7S = {BilanAnthropometrique, Fibroscan};
		GroupeSoins G4P7 = new GroupeSoins(G4P7S);
		Soin[] G5P7S = {EntretienPsy, EntretienInfirmier, EntretienDiet60};
		GroupeSoins G5P7 = new GroupeSoins(G5P7S);
		Soin[] G6P7S = {Synthese};
		GroupeSoins G6P7 = new GroupeSoins(G6P7S);
		GroupeSoins[] P7G = {G1P7, G2P7, G3P7, G4P7, G5P7, G6P7};
		Parcours P7  = new Parcours(P7G, 7);

		// On crée le Parcours 7
		Soin[] G1P7S = {RDVParamedical20};
		GroupeSoins G1P7 = new GroupeSoins(G1P7S);
		Soin[] G2P7S = {BilanBiologique15, EchoHepathique, Calorimetrie};
		GroupeSoins G2P7 = new GroupeSoins(G2P7S);
		Soin[] G3P7S = {Collation};
		GroupeSoins G3P7 = new GroupeSoins(G3P7S);
		Soin[] G4P7S = {BilanAnthropometrique, Fibroscan};
		GroupeSoins G4P7 = new GroupeSoins(G4P7S);
		Soin[] G5P7S = {EntretienPsy, EntretienInfirmier, EntretienDiet60};
		GroupeSoins G5P7 = new GroupeSoins(G5P7S);
		Soin[] G6P7S = {Synthese};
		GroupeSoins G6P7 = new GroupeSoins(G6P7S);
		GroupeSoins[] P7G = {G1P7, G2P7, G3P7, G4P7, G5P7, G6P7};
		Parcours P7  = new Parcours(P7G, 7);

		// On crée le Parcours 7
		Soin[] G1P7S = {RDVParamedical20};
		GroupeSoins G1P7 = new GroupeSoins(G1P7S);
		Soin[] G2P7S = {BilanBiologique15, EchoHepathique, Calorimetrie};
		GroupeSoins G2P7 = new GroupeSoins(G2P7S);
		Soin[] G3P7S = {Collation};
		GroupeSoins G3P7 = new GroupeSoins(G3P7S);
		Soin[] G4P7S = {BilanAnthropometrique, Fibroscan};
		GroupeSoins G4P7 = new GroupeSoins(G4P7S);
		Soin[] G5P7S = {EntretienPsy, EntretienInfirmier, EntretienDiet60};
		GroupeSoins G5P7 = new GroupeSoins(G5P7S);
		Soin[] G6P7S = {Synthese};
		GroupeSoins G6P7 = new GroupeSoins(G6P7S);
		GroupeSoins[] P7G = {G1P7, G2P7, G3P7, G4P7, G5P7, G6P7};
		Parcours P7  = new Parcours(P7G, 7);

		// On crée le Parcours 7
		Soin[] G1P7S = {RDVParamedical20};
		GroupeSoins G1P7 = new GroupeSoins(G1P7S);
		Soin[] G2P7S = {BilanBiologique15, EchoHepathique, Calorimetrie};
		GroupeSoins G2P7 = new GroupeSoins(G2P7S);
		Soin[] G3P7S = {Collation};
		GroupeSoins G3P7 = new GroupeSoins(G3P7S);
		Soin[] G4P7S = {BilanAnthropometrique, Fibroscan};
		GroupeSoins G4P7 = new GroupeSoins(G4P7S);
		Soin[] G5P7S = {EntretienPsy, EntretienInfirmier, EntretienDiet60};
		GroupeSoins G5P7 = new GroupeSoins(G5P7S);
		Soin[] G6P7S = {Synthese};
		GroupeSoins G6P7 = new GroupeSoins(G6P7S);
		GroupeSoins[] P7G = {G1P7, G2P7, G3P7, G4P7, G5P7, G6P7};
		Parcours P7  = new Parcours(P7G, 7);
		 */		
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
		Integer[][][] solution = resolution.resout();
		for(int i=0; i< aResoudre.getnPatients(); i++){
			for (int j = 0; j < aResoudre.getnG_i()[aResoudre.getP_i()[i]-1]; j++) {
				for (int k = 0; k < aResoudre.getnS_ij()[aResoudre.getP_i()[i]-1][j]; k++) {
					System.out.println("X["+i+"]["+j+"]["+k+"] = "+solution[i][j][k]);
				}
			}
		}
		Solution verifierSol = new Solution(solution, aResoudre);
		System.out.println("Ouverture? "+verifierSol.verifieContrainteHeureOuverture());
		System.out.println("Fermeture? "+verifierSol.verifieContrainteHeureFermeture());
		System.out.println("Precedence? "+verifierSol.verifieContraintePrecedenceGroupe());
		System.out.println("Capacite max? "+verifierSol.verifieContrainteRessources());

		//5. Affichage de la solution (a implementer)
	}

}

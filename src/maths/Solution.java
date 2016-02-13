package maths;

import java.util.ArrayList;

import dev.Probleme;

public class Solution {
	
	private Integer[][][] Xsol;
	private Probleme aResoudre;
	
	public Solution(Integer[][][] xsol, Probleme aResoudre) {
		Xsol = xsol;
		this.aResoudre = aResoudre;
	}

	public boolean verifieContrainteHeureFermeture(){
		boolean verifieContrainteHeureFermeture=true;
		int i=0;
		while(verifieContrainteHeureFermeture&&(i < this.aResoudre.getnPatients())){
			int j=0;
			while(verifieContrainteHeureFermeture&&(j < this.aResoudre.getnG_i()[this.aResoudre.getP_i()[i]])){
				int k=0;
				while(verifieContrainteHeureFermeture&&(k < this.aResoudre.getnS_ij()[this.aResoudre.getP_i()[i]][j])){
					verifieContrainteHeureFermeture=verifieContrainteHeureFermeture&&(Xsol[i][j][k]+this.aResoudre.getL_ijk()[this.aResoudre.getP_i()[i]][j][k]<=this.aResoudre.getHFermeture());
					k++;
				}
				j++;
			}	
			i++;
		}
		return verifieContrainteHeureFermeture;
	}
	
	public boolean verifieContrainteHeureOuverture(){
		boolean verifieContrainteHeureOuverture=true;
		int i=0;
		while(verifieContrainteHeureOuverture&&(i < this.aResoudre.getnPatients())){
			int j=0;
			while(verifieContrainteHeureOuverture&&(j < this.aResoudre.getnG_i()[this.aResoudre.getP_i()[i]])){
				int k=0;
				while(verifieContrainteHeureOuverture&&(k < this.aResoudre.getnS_ij()[this.aResoudre.getP_i()[i]][j])){
					verifieContrainteHeureOuverture=verifieContrainteHeureOuverture&&(Xsol[i][j][k]>=this.aResoudre.getHOuverture());
					k++;
				}
				j++;
			}
			i++;
		}
		return verifieContrainteHeureOuverture;
	}

	public boolean verifieContraintePrecedenceGroupe(){
		boolean verifieContraintePrecedenceGroupe = true;
		int i=0;
		while(verifieContraintePrecedenceGroupe&&(i < this.aResoudre.getnPatients())){
			int j=0;
			while(verifieContraintePrecedenceGroupe&&(j < this.aResoudre.getnG_i()[this.aResoudre.getP_i()[i]]-1)){
				int k=0;
				while(verifieContraintePrecedenceGroupe&&(k < this.aResoudre.getnS_ij()[this.aResoudre.getP_i()[i]][j])){
					int u=0;
					while(verifieContraintePrecedenceGroupe&&(u < this.aResoudre.getnS_ij()[this.aResoudre.getP_i()[i]][j+1])){
						verifieContraintePrecedenceGroupe=verifieContraintePrecedenceGroupe&&(Xsol[i][j][k]+this.aResoudre.getL_ijk()[this.aResoudre.getP_i()[i]][j][k]<=Xsol[i][j+1][u]);
						u++;
					}
					k++;
				}
				j++;
			}
			i++;
		}
		return verifieContraintePrecedenceGroupe;
	}
	
	
	public boolean verifieContrainteRessources(){
		boolean verifieContrainteRessources = true;
		int p=360;
		while(verifieContrainteRessources&&(p<this.aResoudre.getnPeriodes())){
			ArrayList<Integer[]>[] S_i = new ArrayList[this.aResoudre.getnPatients()];
			for (int i = 0; i < this.aResoudre.getnPatients(); i++) {
				S_i[i] = new ArrayList<Integer[]>(); 
				for (int j = 0; j < this.aResoudre.getnG_i()[this.aResoudre.getP_i()[i]]; j++) {
					for (int k = 0; k < this.aResoudre.getnS_ij()[this.aResoudre.getP_i()[i]][j]; k++) {
						if((Xsol[i][j][k]+this.aResoudre.getL_ijk()[this.aResoudre.getP_i()[i]][j][k]>p)&&(Xsol[i][j][k]<=p)){
							S_i[i].add(new Integer[]{j,k});
						}
					} 
				}
			}
			int[] ressourceUtilisee_r = new int[this.aResoudre.getnRessources()];
			int r=0;
			while(verifieContrainteRessources&&(r < this.aResoudre.getnRessources())){
				for (int i = 0; i < this.aResoudre.getnPatients(); i++) {
					for (Integer[] soinEnCours : S_i[i]) {
						ressourceUtilisee_r[r] = ressourceUtilisee_r[r]+this.aResoudre.getQ_ijkr()[this.aResoudre.getP_i()[i]][soinEnCours[0]][soinEnCours[1]][r];
					}
				}
				verifieContrainteRessources=verifieContrainteRessources&&(ressourceUtilisee_r[r]<=this.aResoudre.getCp_ij()[r][p]);
				//if(p<600 && ressourceUtilisee_r[r]!=0)System.out.println("p = "+p+", r = "+r+" : "+ressourceUtilisee_r[r]);
				r++;
			}
			p++;
		}
		return verifieContrainteRessources;
	}
	
	public int g(int x){
		int g=Integer.MAX_VALUE;
		if(x>=0){
			g=x;
		}
		return g;
	}
	
	public int min(int[][] x){
		int min =Integer.MAX_VALUE;
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < x[0].length; j++) {
				if(x[i][j]<min){
					min=x[i][j];
				}
			}
		}
		return min;
	}
	
	
	public boolean verifieContrainteAttentePatientsMin(){
		boolean verifieContrainteAttentePatientsMin = true;
		int i=0;
		while(verifieContrainteAttentePatientsMin&&(i < this.aResoudre.getnPatients())){
			int j=0;
			while(verifieContrainteAttentePatientsMin&&(j < this.aResoudre.getnG_i()[this.aResoudre.getP_i()[i]]-1)){
				int k=0;
				while(verifieContrainteAttentePatientsMin&& (k < this.aResoudre.getnS_ij()[this.aResoudre.getP_i()[i]][j])){
					int [][] attente = new int[2][];
					for (int u = 0; u < attente.length; u++) {
						attente[u] = new int[this.aResoudre.getnS_ij()[this.aResoudre.getP_i()[i]][j+u]] ;
						for (int m = 0; m < this.aResoudre.getnS_ij()[this.aResoudre.getP_i()[i]][j+u]; m++) {
							attente[u][m]=g(Xsol[i][j+u][m]-(Xsol[i][j][k]+this.aResoudre.getL_ijk()[this.aResoudre.getP_i()[i]][j][k]));
						}
					}
					verifieContrainteAttentePatientsMin=verifieContrainteAttentePatientsMin&&(min(attente))>=this.aResoudre.getA_MIN();
					k++;
				}
				j++;
			}
			i++;
		}
		return verifieContrainteAttentePatientsMin;
	}
	
	public boolean verifieContrainteAttentePatientsMax(){
		boolean verifieContrainteAttentePatientsMax = true;
		int i=0;
		while(verifieContrainteAttentePatientsMax&&(i < this.aResoudre.getnPatients())){
			int j=0;
			while(verifieContrainteAttentePatientsMax&&(j < this.aResoudre.getnG_i()[this.aResoudre.getP_i()[i]]-1)){
				int k=0;
				while(verifieContrainteAttentePatientsMax&& (k < this.aResoudre.getnS_ij()[this.aResoudre.getP_i()[i]][j])){
					int [][] attente = new int[2][];
					for (int u = 0; u < attente.length; u++) {
						attente[u] = new int[this.aResoudre.getnS_ij()[this.aResoudre.getP_i()[i]][j+u]] ;
						for (int m = 0; m < this.aResoudre.getnS_ij()[this.aResoudre.getP_i()[i]][j+u]; m++) {
							attente[u][m]=g(Xsol[i][j+u][m]-(Xsol[i][j][k]+this.aResoudre.getL_ijk()[this.aResoudre.getP_i()[i]][j][k]));
						}
					}
					verifieContrainteAttentePatientsMax=verifieContrainteAttentePatientsMax&&(min(attente))<=this.aResoudre.getA_MAX();
					k++;
				}
				j++;
			}
			i++;
		}
		return verifieContrainteAttentePatientsMax;
	}
	
	
	public boolean verifieContraintes(){
		boolean verifieContraintes= true;
		if(verifieContraintes){
			verifieContraintes=verifieContraintes&&this.verifieContrainteHeureFermeture();
			if(verifieContraintes){
				verifieContraintes=verifieContraintes&&this.verifieContrainteHeureOuverture();
				if(verifieContraintes){
					verifieContraintes=verifieContraintes&&this.verifieContraintePrecedenceGroupe();
					if(verifieContraintes){
						verifieContraintes=verifieContraintes&&this.verifieContrainteRessources();
						if(verifieContraintes){
							verifieContraintes=verifieContraintes&&this.verifieContrainteAttentePatientsMin();
							if(verifieContraintes){
								verifieContraintes=verifieContraintes&&this.verifieContrainteAttentePatientsMax();

							}
						}
					}
				}
			}
		}
		return verifieContraintes;
	}
	
}

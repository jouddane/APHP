package maths;

import java.util.ArrayList;

import dev.Probleme;

public class Solution {
	
	private Integer[][][] Xsol;
	private Probleme aResoudre;
	
	public boolean verifieContrainteHeureFermeture(){
		boolean verifieContrainteHeureFermeture=true;
		for (int i = 0; i < this.aResoudre.getnPatients(); i++) {
			for (int j = 0; j < this.aResoudre.getnG_i()[this.aResoudre.getP_i()[i]]; j++) {
				for (int k = 0; k < this.aResoudre.getnS_ij()[this.aResoudre.getP_i()[i]][j]; k++) {
					verifieContrainteHeureFermeture=verifieContrainteHeureFermeture&&(Xsol[i][j][k]+this.aResoudre.getL_ijk()[this.aResoudre.getP_i()[i]][j][k]<=this.aResoudre.getHFermeture());
				}
			}
		}
		return verifieContrainteHeureFermeture;
	}
	
	public boolean verifieContrainteHeureOuverture(){
		boolean verifieContrainteHeureOuverture=true;
		for (int i = 0; i < this.aResoudre.getnPatients(); i++) {
			for (int j = 0; j < this.aResoudre.getnG_i()[this.aResoudre.getP_i()[i]]; j++) {
				for (int k = 0; k < this.aResoudre.getnS_ij()[this.aResoudre.getP_i()[i]][j]; k++) {
					verifieContrainteHeureOuverture=verifieContrainteHeureOuverture&&(Xsol[i][j][k]>=this.aResoudre.getHOuverture());
				}
			}
		}
		return verifieContrainteHeureOuverture;
	}

	public boolean verifieContraintePrecedenceGroupe(){
		boolean verifieContraintePrecedenceGroupe = true;
		for (int i = 0; i < this.aResoudre.getnPatients(); i++) {
			for (int j = 0; j < this.aResoudre.getnG_i()[this.aResoudre.getP_i()[i]]-1; j++) {
				for (int k = 0; k < this.aResoudre.getnS_ij()[this.aResoudre.getP_i()[i]][j]; k++) {
					for (int u = 0; u < this.aResoudre.getnS_ij()[this.aResoudre.getP_i()[i]][j+1]; u++) {
						verifieContraintePrecedenceGroupe=verifieContraintePrecedenceGroupe&&(Xsol[i][j][k]+this.aResoudre.getL_ijk()[this.aResoudre.getP_i()[i]][j][k]<=Xsol[i][j+1][u]);
					}
				}
			}
		}
		return verifieContraintePrecedenceGroupe;
	}
	
	
	public boolean verifieContrainteRessources(){
		boolean verifieContrainteRessources = true;
		for (int p = 0; p < this.aResoudre.getnPeriodes(); p++) {
			ArrayList<Integer[][]>[] S_i = new ArrayList[this.aResoudre.getnPatients()];
			for (int i = 0; i < this.aResoudre.getnPatients(); i++) {
				S_i[i] = new ArrayList(); 
				for (int j = 0; j < this.aResoudre.getnG_i()[this.aResoudre.getP_i()[i]]; j++) {
					for (int k = 0; k < this.aResoudre.getnS_ij()[this.aResoudre.getP_i()[i]][j]; k++) {
						if((Xsol[i][j][k]+this.aResoudre.getL_ijk()[this.aResoudre.getP_i()[i]][j][k]>=p)&&(Xsol[i][j][k]<=p)){
							S_i[i].add(int[][]{j,k});
						}
					} 
				}
			}
			int sum=0;
			for(couples : ){
				sum=sum+;
			}
		}
		return false;
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
		for (int i = 0; i < this.aResoudre.getnPatients(); i++) {
			for (int j = 0; j < this.aResoudre.getnG_i()[this.aResoudre.getP_i()[i]]-1; j++) {
				for (int k = 0; k < this.aResoudre.getnS_ij()[this.aResoudre.getP_i()[i]][j]; k++) {
					int [][] attente = new int[2][];
					for (int u = 0; u < attente.length; u++) {
						attente[u] = new int[this.aResoudre.getnS_ij()[this.aResoudre.getP_i()[i]][j+u]] ;
						for (int m = 0; m < this.aResoudre.getnS_ij()[this.aResoudre.getP_i()[i]][j+u]; m++) {
							attente[u][m]=g(Xsol[i][j+u][m]-(Xsol[i][j][k]+this.aResoudre.getL_ijk()[this.aResoudre.getP_i()[i]][j][k]));
						}
					}
					verifieContrainteAttentePatientsMin=verifieContrainteAttentePatientsMin&&(min(attente))>=this.aResoudre.getA_MIN();
				}
			}
		}
		return verifieContrainteAttentePatientsMin;
	}
	
	public boolean verifieContrainteAttentePatientsMax(){
		boolean verifieContrainteAttentePatientsMax = true;
		for (int i = 0; i < this.aResoudre.getnPatients(); i++) {
			for (int j = 0; j < this.aResoudre.getnG_i()[this.aResoudre.getP_i()[i]]-1; j++) {
				for (int k = 0; k < this.aResoudre.getnS_ij()[this.aResoudre.getP_i()[i]][j]; k++) {
					int [][] attente = new int[2][];
					for (int u = 0; u < attente.length; u++) {
						attente[u] = new int[this.aResoudre.getnS_ij()[this.aResoudre.getP_i()[i]][j+u]] ;
						for (int m = 0; m < this.aResoudre.getnS_ij()[this.aResoudre.getP_i()[i]][j+u]; m++) {
							attente[u][m]=g(Xsol[i][j+u][m]-(Xsol[i][j][k]+this.aResoudre.getL_ijk()[this.aResoudre.getP_i()[i]][j][k]));
						}
					}
					verifieContrainteAttentePatientsMax=verifieContrainteAttentePatientsMax&&(min(attente))<=this.aResoudre.getA_MAX();
				}
			}
		}
		return verifieContrainteAttentePatientsMax;
	}
	
	
	
	
	
	
	
}

package fr.gtm.domaine;

public abstract class Compte {
	
	private int idCompte;
	private String numCompte;
	private float solde;
	private String dateCreation;
//	private boolean isActive;
	private int isActive;
	
	
	public int getIdCompte() {
		return idCompte;
	}
	public void setIdCompte(int idCompte) {
		this.idCompte = idCompte;
	}
	public String getNumCompte() {
		return numCompte;
	}
	public void setNumCompte(String numCompte) {
		this.numCompte = numCompte;
	}
	public float getSolde() {
		return solde;
	}
	public void setSolde(float solde) {
		this.solde = solde;
	}
	public String getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}
//	public boolean isActive() {
//		return isActive;
//	}
//	public void setActive(boolean isActive) {
//		this.isActive = isActive;
//	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	
	
	
}

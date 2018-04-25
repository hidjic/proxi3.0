package fr.gtm.domaine;

public class CompteEpargne extends Compte {
	
	private float taux;

	public float getTaux() {
		return taux;
	}

	public void setTaux(float taux) {
		this.taux = taux;
	}

	@Override
	public String toString() {
		return "CompteEpargne [taux=" + taux + ", isActive="+this.getIsActive()+"]";
	}
	
	
	
}

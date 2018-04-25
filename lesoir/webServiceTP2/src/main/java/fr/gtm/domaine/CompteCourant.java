package fr.gtm.domaine;

public class CompteCourant extends Compte {
	
	private float decouvert;

	
	// CONSTRUCTOR
	public CompteCourant() {
		super();
		this.decouvert = 0;
	}

	// GETTER ET SETTER
	public float getDecouvert() {
		return decouvert;
	}

	public void setDecouvert(float decouvert) {
		this.decouvert = decouvert;
	}

	@Override
	public String toString() {
		return "CompteCourant [decouvert=" + decouvert + ", isActive="+this.getIsActive()+"]";
	}
	
	
	
}

package fr.gtm.domaine;

public class User extends Personne {
	
	private int idUser;
	private String email;
	private String adresse;
	private String codePostal;
	private String ville;
	private int idConseiller;
	private CompteCourant unCC;
	private CompteEpargne unCE;
	
	
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public int getIdConseiller() {
		return idConseiller;
	}
	public void setIdConseiller(int idConseiller) {
		this.idConseiller = idConseiller;
	}
	public CompteCourant getUnCC() {
		return unCC;
	}
	public void setUnCC(CompteCourant unCC) {
		this.unCC = unCC;
	}
	public CompteEpargne getUnCE() {
		return unCE;
	}
	public void setUnCE(CompteEpargne unCE) {
		this.unCE = unCE;
	}
	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", nom="+this.getNom()+", prenom="+this.getPrenom()+", email=" + email + ", adresse=" + adresse + ", codePostal=" + codePostal
				+ ", ville=" + ville + ", idConseiller=" + idConseiller + ", unCC=" + unCC + ", unCE=" + unCE + "]";
	}
	
	
	
	
	
}

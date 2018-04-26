package fr.gtm.presentation;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.gtm.domaine.Compte;
import fr.gtm.domaine.CompteCourant;
import fr.gtm.domaine.CompteEpargne;
import fr.gtm.domaine.User;
import fr.gtm.service.ServiceUser;

@ManagedBean(name = "userBean", eager = true)
@SessionScoped
public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private User userSession = new User();
	private Compte compteDebit = null;
	private Compte compteCredit = null;
	private double montant = 0;
	private char typeCompte;

	// Constructeur
	public UserBean() {
		super();
	}

	// getters-Setters
	public User getUserSession() {
		return userSession;
	}

	public char getTypeCompte() {
		return typeCompte;
	}

	public void setTypeCompte(char typeCompte) {
		this.typeCompte = typeCompte;
	}

	public void setUserSession(User userSession) {
		this.userSession = userSession;
	}

	public Compte getCompteDebit() {
		return compteDebit;
	}

	public void setCompteDebit(Compte compteDebit) {
		this.compteDebit = compteDebit;
	}

	public Compte getCompteCredit() {
		return compteCredit;
	}

	public void setCompteCredit(Compte compteCredit) {
		this.compteCredit = compteCredit;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	
	// =-=-=-=-=-=-=-=-=Methodes creation user=-=-=-=-=-=-=-=-=
	public String creationUser(int pIdConseiller) {
		
		System.out.println("Bean-méthode : creationUser user : " + this.userSession + "pIdConseiller : " + pIdConseiller);
		this.userSession.setIdConseiller(pIdConseiller);
		User userGot = new User();
		userGot = new ServiceUser().creationUser(this.userSession);
		System.out.println("BEan-méthode : creationUser userGOTTTT : " + userGot);

		if (userGot.getIdUser() > 0) {
			return "creationUserGood";
		}
		return "creationUserFalse";
	}

	public String getUser(User pUser) {
		this.userSession = pUser;
		System.out.println("méthode : getUser login : " + this.userSession);
		return "infosClient";
	}// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=


	// =-=-=-=-=-=-=-=-=Methodes activer compteBancaire=-=-=-=-=-=-=-=-=

	public String activerCompteCourant() {

		CompteCourant compteGot = new CompteCourant();
		// Appel couche service
		compteGot = new ServiceUser().activerCompteCourant(this.userSession.getUnCC());

		if (compteGot.getIdCompte() > 0) {
			// mis à jour du compte
			this.userSession.setUnCC(compteGot);
			System.out.println(
					"méthode : activerCompteCourant -- compte isActive : " + this.userSession.getUnCC().getIsActive());
		}

		return "listeClient";
	}

	public String activerCompteEpargne() {

		CompteEpargne compteGot = new CompteEpargne();
		// Appel couche service
		compteGot = new ServiceUser().activerCompteEpargne(this.userSession.getUnCE());
		if (compteGot.getIdCompte() > 0) {
			// mis à jour du compte
			this.userSession.setUnCE(compteGot);
		}
		System.out.println(
				"méthode : activerCompteEpargne -- compte isActive : " + this.userSession.getUnCC().getIsActive());
		return "listeClient";
	}
	// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

	// =-=-=-=-=-=-=-=-=Methodes show page debit compte=-=-=-=-=-=-=-=-=
	public String showDebitPage(char pTypeCompte) {

		this.typeCompte = pTypeCompte;

		if (pTypeCompte == 'C') {
			this.compteDebit = this.userSession.getUnCC();
			System.out.println(
					"méthode : showDebitPage -- typeCompte : " + typeCompte + " user.compteCourant : " +this.userSession.getUnCC()  + " compte débiteur : " + this.compteDebit);
			return "debitCompte";
		} else if (pTypeCompte == 'E') {
			this.compteDebit = this.userSession.getUnCE();
			System.out.println(
					"méthode : showDebitPage -- typeCompte : " + typeCompte + " user.compteEpargne : " +this.userSession.getUnCE()  + " compte débiteur : " + this.compteDebit);
			return "debitCompte";
		}
		
		return "infosClientFalse";
	}// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

	// =-=-=-=-=-=-=-=-=Methodes show page credit compte=-=-=-=-=-=-=-=-=
	public String showCreditPage(char pTypeCompte) {

		this.typeCompte = pTypeCompte;

		if (pTypeCompte == 'C') {
			this.compteCredit = this.userSession.getUnCC();
			System.out.println(
					"méthode : showCreditPage -- typeCompte : " + typeCompte + " user.compteCouant : " +this.userSession.getUnCC() + " compte crédit : " + this.compteCredit);
			return "creditCompte";
		} else if (pTypeCompte == 'E') {
			this.compteCredit = this.userSession.getUnCE();
			System.out.println(
					"méthode : showCreditPage -- typeCompte : " + typeCompte + " user.compteCouant : " +this.userSession.getUnCE() + " compte crédit : " + this.compteCredit);
			return "creditCompte";
		}

		return "infosClientFalse";
	}

	// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

	// =-=-=-=-=-=-=-=-=Methodes Debit Credit compteBancaire=-=-=-=-=-=-=-=-=
	public String debitCompte() {

		if (this.typeCompte == 'C') {
			CompteCourant compteGot = new CompteCourant();
			// Appel couche service
			compteGot = new ServiceUser().debitCompteCourant(this.compteDebit, (float) this.montant);

			if (compteGot.getIdCompte() > 0) {
				// mis à jour du compte
				this.userSession.setUnCC(compteGot);
			}
			System.out.println("méthode : debitCompte -- compte : " + this.userSession.getUnCC() + " solde : "+ this.userSession.getUnCC().getSolde() + " typeCompte : " + this.typeCompte);
			return "infosClientGood";
		} else if (this.typeCompte == 'E') {
			CompteEpargne compteGot = new CompteEpargne();
			// Appel couche service
			compteGot = new ServiceUser().debitCompteEpargne(this.compteDebit, (float) this.montant);

			if (compteGot.getIdCompte() > 0) {
				// mis à jour du compte
				this.userSession.setUnCE(compteGot);
			}
			System.out.println("méthode : debitCompteCourant -- compte : " + this.userSession.getUnCE() + " solde : "+ this.userSession.getUnCC().getSolde() + " typeCompte : " + this.typeCompte);
			return "infosClientGood";
		}

		return "infosClientFalse";
	}

	public String creditCompte() {
		System.out.println("méthode : creditCompte -- compte : " + this.compteCredit + " typeCompte : " + this.typeCompte);
		if (this.typeCompte == 'C') {
			CompteCourant compteGot = new CompteCourant();
			// Appel couche service
			compteGot = new ServiceUser().creditCompteCourant(this.compteCredit, (float) this.montant);

			if (compteGot.getIdCompte() > 0) {
				// mis à jour du compte
				this.userSession.setUnCC(compteGot);
			}
			System.out.println("méthode : creditCompte -- compte : " + this.userSession.getUnCE() + " solde : "+ this.userSession.getUnCC().getSolde() + " typeCompte : " + this.typeCompte);
			return "infosClientGood";
		} else if (this.typeCompte == 'E') {
			CompteEpargne compteGot = new CompteEpargne();
			// Appel couche service
			compteGot = new ServiceUser().creditCompteEpargne(this.compteCredit, (float) this.montant);

			if (compteGot.getIdCompte() > 0) {
				// mis à jour du compte
				this.userSession.setUnCE(compteGot);
			}
			System.out.println("méthode : debitCompteCourant -- compte : " + this.userSession.getUnCC() + " solde : "+ this.userSession.getUnCC().getSolde() + " typeCompte : " + this.typeCompte);
			return "infosClientGood";
		}
		return "infosClientFalse";
	}

	public String debitCompteEparggne() {

		CompteEpargne compteGot = new CompteEpargne();
		// Appel couche service
		compteGot = new ServiceUser().debitCompteEpargne(this.compteDebit, (float) this.montant);

		if (compteGot.getIdCompte() > 0) {
			// mis à jour du compte
			this.userSession.setUnCE(compteGot);
		}
		System.out.println("méthode : debitCompteCourant -- compte : " + this.userSession.getUnCC());
		return "infosClient";
	}

	public String creditCompteEparggne() {

		CompteEpargne compteGot = new CompteEpargne();
		// Appel couche service
		compteGot = new ServiceUser().debitCompteEpargne(this.compteDebit, (float) this.montant);

		if (compteGot.getIdCompte() > 0) {
			// mis à jour du compte
			this.userSession.setUnCE(compteGot);
		}
		System.out.println("méthode : debitCompteCourant -- compte : " + this.userSession.getUnCC());
		return "infosClient";
	}// =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

}

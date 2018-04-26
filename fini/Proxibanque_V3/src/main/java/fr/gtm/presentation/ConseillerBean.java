package fr.gtm.presentation;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import fr.gtm.domaine.Conseiller;
import fr.gtm.domaine.User;
import fr.gtm.service.ServiceConseiller;

@ManagedBean(name = "conseillerBean", eager = true)
@SessionScoped
public class ConseillerBean implements Serializable {

	// Déclaration des propriétés
	private Conseiller conseillerSession = new Conseiller();
	// private String errorMessage;

	// ===================Constructeurs==========================
	public ConseillerBean() {
		super();
	}
	// =============================================================================

	// ================================Getters setters=============================
	public Conseiller getConseillerSession() {
		return conseillerSession;
	}

	public void setConseillerSession(Conseiller conseillerSession) {
		this.conseillerSession = conseillerSession;
	}

	// ============================================================================

	/**
	 * @return retourne le compte renvoyé de la bdd. si l'id de Conseiller est superieur à  0
	 *         la requete bdd n'a pas retourné d'exception.
	 * 
	 * 
	 *             Cette méthode permet d'accéder à la couche service afin de
	 *             retourner le conseiller ayant le login et le password rentrés par
	 *             l'utilisateur.
	 */
	public String authentification() throws JsonParseException, JsonMappingException, IOException {
		System.out.println("méthode : authentification login : " + this.conseillerSession.getLogin() + " mdp : "
				+ this.conseillerSession.getPwd());

		// Declaration de variables locales
		Conseiller conseillerGot = new Conseiller();

		// Appel couche service
		conseillerGot = new ServiceConseiller().authentification(this.conseillerSession);

		if (conseillerGot.getIdConseiller() > 0) {
			this.conseillerSession = conseillerGot;
			System.out.println("listeClient" + this.conseillerSession.getListU());
			return "listeClients";
		}
		this.conseillerSession = new Conseiller();
		return "loginFalse";
	}

	public String logout() {
		this.conseillerSession = new Conseiller();
		System.out.println("conseillerBean méthode : logout login : " + this.conseillerSession.getLogin() + " mdp : "
				+ this.conseillerSession.getPwd());
		return "login";
	}

	public String showListClients() throws JsonParseException, JsonMappingException, IOException {
		System.out.println("méthode : showListeClient login : " + this.conseillerSession.getLogin() + " mdp : "
				+ this.conseillerSession.getPwd());

		this.conseillerSession = new ServiceConseiller().authentification(this.conseillerSession);
		return "listeClients";
	}

	public String showCreationClient() {
		System.out.println("méthode : showcreationClient login : " + this.conseillerSession.getLogin() + " mdp : "
				+ this.conseillerSession.getPwd());
		return "creationClient";
	}

	public String updateUser(User pUser) {
		System.out.println("méthode : updateUser login : " + pUser);
		return "listeClients";
	}

	public String deleteUser(User pUser) {
		System.out.println("méthode : deleteUser login : " + pUser);
		return "listeClients";
	}

}

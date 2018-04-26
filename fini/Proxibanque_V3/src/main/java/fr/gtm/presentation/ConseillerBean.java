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

	// D�claration des propri�t�s
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
	 * @return retourne le compte renvoy� de la bdd. si l'id de Conseiller est superieur �  0
	 *         la requete bdd n'a pas retourn� d'exception.
	 * 
	 * 
	 *             Cette m�thode permet d'acc�der � la couche service afin de
	 *             retourner le conseiller ayant le login et le password rentr�s par
	 *             l'utilisateur.
	 */
	public String authentification() throws JsonParseException, JsonMappingException, IOException {
		System.out.println("m�thode : authentification login : " + this.conseillerSession.getLogin() + " mdp : "
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
		System.out.println("conseillerBean m�thode : logout login : " + this.conseillerSession.getLogin() + " mdp : "
				+ this.conseillerSession.getPwd());
		return "login";
	}

	public String showListClients() throws JsonParseException, JsonMappingException, IOException {
		System.out.println("m�thode : showListeClient login : " + this.conseillerSession.getLogin() + " mdp : "
				+ this.conseillerSession.getPwd());

		this.conseillerSession = new ServiceConseiller().authentification(this.conseillerSession);
		return "listeClients";
	}

	public String showCreationClient() {
		System.out.println("m�thode : showcreationClient login : " + this.conseillerSession.getLogin() + " mdp : "
				+ this.conseillerSession.getPwd());
		return "creationClient";
	}

	public String updateUser(User pUser) {
		System.out.println("m�thode : updateUser login : " + pUser);
		return "listeClients";
	}

	public String deleteUser(User pUser) {
		System.out.println("m�thode : deleteUser login : " + pUser);
		return "listeClients";
	}

}

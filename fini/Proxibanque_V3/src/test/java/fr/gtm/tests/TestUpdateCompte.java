package fr.gtm.tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.gtm.domaine.Compte;
import fr.gtm.domaine.CompteCourant;
import fr.gtm.domaine.CompteEpargne;
import fr.gtm.service.ServiceUser;

public class TestUpdateCompte {

	public CompteCourant compteCourant = new CompteCourant();
	public CompteEpargne compteEpargne = new CompteEpargne();
	public CompteEpargne compteRecueBdd = null;
	public float montant = 0.0f;
	public ServiceUser monServiceUser = new ServiceUser();
	//Variable liées à la connexion au Webservice===========
	public String input = null;
	public ObjectMapper mapper = new ObjectMapper(); // create once, reuse
	public Client jerseyClient = Client.create();
	//public WebResource webResource = jerseyClient
	//		.resource("http://192.168.1.103:8080/webServiceProxiBanque/webapi/comptewebservice/putCompteEpargne");
	public WebResource webResource = jerseyClient
			.resource("http://localhost:8080/webServiceProxiBanque/webapi/comptewebservice/putCompteEpargne");
	//=====================================================
	
	@Before
	public void setUp() throws Exception {

		//instanciation des comptes utilisées
		compteCourant = new CompteCourant();
		compteEpargne = new CompteEpargne();
		float montant = 0.0f;
		
		//On affecte certaines valeurs clées des comptes
		this.compteCourant.setSolde(-1000);
		this.compteCourant.setIdCompte(3);
		this.compteCourant.setNumCompte("compteCourantVideDeStephane");
		this.compteEpargne.setSolde(1000);
		this.compteEpargne.setIdCompte(4);
		this.compteEpargne.setNumCompte("compteEpargneVideDeStephane");
		System.out.println(">> declanche before : creation compte");
	}



	/**
	 * Méthode testant la méthode debitCompteEpargne de la couche service (update des information du compte)
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testDebitCompteEpargne() {

		// =============premier test=========================
		this.montant=300.0f;
		//Appel méthode changement du solde
		monServiceUser.debitCompte(this.compteEpargne, this.montant);
		
		try {
			input = mapper.writeValueAsString(this.compteEpargne);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(" Json à envoyer après mapping : " + input);
		ClientResponse response = webResource.type("application/json").put(ClientResponse.class, input);
		
		System.out.println("Output from Server .... \n");
		String JSonOutput = response.getEntity(String.class);
		System.out.println("flux JSON reçu :" + JSonOutput);
		System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-");


		// On transforme flux JSON en objet Conseiller
		try {
			this.compteRecueBdd = mapper.readValue(JSonOutput, CompteEpargne.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(this.compteRecueBdd);
		
		Assert.assertEquals(700.0, this.compteRecueBdd.getSolde(), 0.0f);
		// =============second test=========================
	}

	
	/**
	 * Méthode testant la méthode creditCompteEpargne de la couche service (update des information du compte)
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testCreditCompteEpargne() {

		// =============premier test=========================
		this.montant=300.0f;
		//Appel méthode changement du solde
		monServiceUser.creditCompte(this.compteEpargne, this.montant);
		
		try {
			input = mapper.writeValueAsString(this.compteEpargne);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(" Json à envoyer après mapping : " + input);
		ClientResponse response = webResource.type("application/json").put(ClientResponse.class, input);
		
		System.out.println("Output from Server .... \n");
		String JSonOutput = response.getEntity(String.class);
		System.out.println("flux JSON reçu :" + JSonOutput);
		System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-");


		// On transforme flux JSON en objet Conseiller
		try {
			this.compteRecueBdd = mapper.readValue(JSonOutput, CompteEpargne.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(this.compteRecueBdd);
		
		Assert.assertEquals(1300.0, this.compteRecueBdd.getSolde(), 0.0f);
		// =============second test=========================
	}
	
	
	
	@After
	public void tearDown() throws Exception {
		this.compteCourant = null;
		this.compteEpargne = null;
		float montant = 0.0f;
		this.monServiceUser = null;
		System.out.println(">> declanche apres chaque test");
	}


}

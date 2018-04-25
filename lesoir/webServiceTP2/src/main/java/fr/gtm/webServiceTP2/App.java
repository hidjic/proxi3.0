package fr.gtm.webServiceTP2;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import fr.gtm.domaine.CompteCourant;
import fr.gtm.domaine.Conseiller;
import fr.gtm.domaine.User;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
/*		
		try {

			Client client = Client.create();

//			WebResource webResource = client.resource("http://localhost:8080/webServiceTP1/service/json/metallica/get");
			WebResource webResource = client.resource("http://localhost:8080/webServiceProxiBanque/webapi/userwebservice");

			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			String output = response.getEntity(String.class);

			System.out.println("Output from Server .... \n");
			System.out.println(output);

		} catch (Exception e) {

			e.printStackTrace();

		}
		
*/

		try {

			Client client = Client.create();

//			WebResource webResource = client.resource("http://localhost:8080/webServiceTP1/service/json/metallica/post");
//			WebResource webResource = client.resource("http://192.168.1.105:8080/jerseyjson/webapi/json/post");

//			String input = "{\"singer\":\"Test Group\",\"title\":\"Test title\"}";
//
//			ClientResponse response = webResource.type("application/json")
//			   .post(ClientResponse.class, input);
//
//			if (response.getStatus() != 201) {
//				throw new RuntimeException("Failed : HTTP error code : "
//				     + response.getStatus());
//			}
//			System.out.println(response.getType());
//			System.out.println("Output from Server .... \n");
//			String output = response.getEntity(String.class);
//			System.out.println(output);
			
			ObjectMapper mapper = new ObjectMapper();
			Conseiller c = new Conseiller();
	    	c.setLogin("test");
	    	c.setPwd("123");
			String jsonInString = mapper.writeValueAsString(c);
//			String jsonInStringtest = "{\"nom\":null,\"prenom\":null,\"idConseiller\":0,\"login\":\"test\",\"pwd\":\"123\"}";			
			WebResource webResource = client.resource("http://localhost:8080/webServiceProxiBanque/webapi/conseillerresource");			
			ClientResponse response = webResource.type("application/json").post(ClientResponse.class, jsonInString);
	    	
			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "
				     + response.getStatus());
			}
			
			String output = response.getEntity(String.class);
			System.out.println("output...Conseiller...");
			System.out.println(output);
			Conseiller res = mapper.readValue(output, Conseiller.class);
			System.out.println(res);
			
			// le conseiller est maintenant complet
			User u = new User();
			u = res.getListU().get(0);
			System.out.println(u);
			u.setNom("Toto");
			u.setPrenom("test");
			String putUser = mapper.writeValueAsString(u);
			webResource = client.resource("http://localhost:8080/webServiceProxiBanque/webapi/userwebservice");
			response = webResource.type("application/json").put(ClientResponse.class, putUser);
			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "
				     + response.getStatus());
			}
			String outputUser = response.getEntity(String.class);
			System.out.println("output...User...");
			System.out.println(outputUser);
			User resUser = mapper.readValue(outputUser, User.class);
			System.out.println(resUser);
			
			// travail sur les comptes
			System.out.println("pour le compte courant...");
			CompteCourant cc = new CompteCourant();
			cc = u.getUnCC();
//			cc.setActive(false);
			cc.setIsActive(0);
			System.out.println(cc);
			String putCC = mapper.writeValueAsString(cc);
			System.out.println(putCC);
			webResource = client.resource("http://localhost:8080/webServiceProxiBanque/webapi/comptewebservice/putCompteCourant");
			response = webResource.type("application/json").put(ClientResponse.class, putCC);
			System.out.println(response.getStatus());
			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "
				     + response.getStatus());
			}
			String outputCC = response.getEntity(String.class);
			System.out.println("output...Compte courant...");
			System.out.println(outputCC);
			CompteCourant resCC = mapper.readValue(outputCC, CompteCourant.class);
			System.out.println(resCC);
			
		  } catch (Exception e) {

			e.printStackTrace();

		  }

	}
}

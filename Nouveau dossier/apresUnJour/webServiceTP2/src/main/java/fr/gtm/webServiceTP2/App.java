package fr.gtm.webServiceTP2;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

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
//			Conseiller c = new Conseiller();
//	    	c.setLogin("test");
//	    	c.setPwd("123");
//			String jsonInString = mapper.writeValueAsString(c);
//			String jsonInStringtest = "{\"nom\":null,\"prenom\":null,\"idConseiller\":0,\"login\":\"test\",\"pwd\":\"123\"}";			
//			WebResource webResource = client.resource("http://localhost:8080/webServiceProxiBanque/webapi/conseillerresource");			
//			ClientResponse response = webResource.type("application/json").post(ClientResponse.class, jsonInStringtest);
			
	    	User u = new User();
	    	u.setNom("Boivin");
	    	u.setPrenom("Stéphane");
	    	u.setIdConseiller(1);
	    	System.out.println("coté client...");
	    	System.out.println(u);
	    	String userInJson = mapper.writeValueAsString(u);
//	    	String userInJsontest = "{\"nom\":\"Boivin\",\"prenom\":\"Stéphane\",\"idUser\":0,\"email\":null,\"adresse\":null,\"codePostal\":null,\"ville\":null,\"idConseiller\":1,\"unCC\":null,\"unCE\":null}";	
//	    	System.out.println(userInJsontest);
	    	WebResource webResource = client.resource("http://localhost:8080/webServiceProxiBanque/webapi/userwebservice");
	    	System.out.println("avant response...");
	    	ClientResponse response = webResource.type("application/json").post(ClientResponse.class, userInJson);
	    	System.out.println(response.getStatus());
	    	
			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "
				     + response.getStatus());
			}
			
			String output = response.getEntity(String.class);
			System.out.println("output...");
			System.out.println(output);
//			Conseiller res = mapper.readValue(output, Conseiller.class);
			User ures = mapper.readValue(output, User.class);
			System.out.println(ures);
			
			
			
			
			
		  } catch (Exception e) {

			e.printStackTrace();

		  }

	}
}

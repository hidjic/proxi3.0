package fr.gtm.webServiceProxiBanque;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.gtm.dao.ConseillerDao;
import fr.gtm.dao.UserDao;
import fr.gtm.domaine.Conseiller;
import fr.gtm.domaine.User;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("conseillerresource")
public class ConseillerWebService {
	
	private ConseillerDao cdao = new ConseillerDao();
	private UserDao udao = new UserDao();
	private ObjectMapper mapper = new ObjectMapper();
	private String errorJson = "Il y a une erreur JSON...";
	
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getIt() {
    	return "ya get it...";
    }
	
    /**
     * Methode @post qui reçoi un conseiller avec seulement son login et mot de passe pour l'authenfication
     * @param json = chaine de caractere de type json de l'objet Conseiller
     * @return = le conseiller identifier avec la liste de ces clients
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response postIt(String json) {
    	System.out.println("debut post conseillerresource...");
    	System.out.println(json);
    	String jsonConseiller = "";
    	Conseiller c = new Conseiller();
    	try {
    		Conseiller conseiller = this.mapper.readValue(json, Conseiller.class);
    		System.out.println(conseiller);
    		c = this.cdao.getConseiller(conseiller);
    		System.out.println(c);
    		List<User> listUser = new ArrayList<User>();
    		listUser = this.udao.getAllUserByConseiller(c);
    		c.setListU(listUser);
    		System.out.println(c);
			jsonConseiller = this.mapper.writeValueAsString(c);
			System.out.println(jsonConseiller);
		} catch (JsonProcessingException e) {
			System.out.println(errorJson);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(errorJson);
			e.printStackTrace();
		}
    	System.out.println("fin post conseillerresource...");
        return Response.status(201).entity(jsonConseiller).build();
    }
}

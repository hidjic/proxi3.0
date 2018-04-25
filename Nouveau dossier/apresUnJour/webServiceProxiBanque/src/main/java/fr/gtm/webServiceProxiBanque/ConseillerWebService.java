package fr.gtm.webServiceProxiBanque;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.gtm.dao.ConseillerDao;
import fr.gtm.domaine.Conseiller;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("conseillerresource")
public class ConseillerWebService {
	
	private ConseillerDao cdao = new ConseillerDao();
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
	
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response postIt(String json) {
//    	System.out.println("debut post...");
//    	System.out.println(json);
    	String jsonConseiller = "";
    	Conseiller c = new Conseiller();
    	try {
    		Conseiller conseiller = this.mapper.readValue(json, Conseiller.class);
//    		System.out.println(conseiller);
    		c = this.cdao.getConseiller(conseiller);
//    		System.out.println(c);
			jsonConseiller = this.mapper.writeValueAsString(c);
//			System.out.println(jsonConseiller);
		} catch (JsonProcessingException e) {
			System.out.println(errorJson);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(errorJson);
			e.printStackTrace();
		}
//    	System.out.println("fin post...");
        return Response.status(201).entity(jsonConseiller).build();
    }
}

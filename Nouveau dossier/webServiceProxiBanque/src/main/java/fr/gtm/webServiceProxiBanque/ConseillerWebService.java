package fr.gtm.webServiceProxiBanque;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.gtm.dao.ConseillerDao;
import fr.gtm.domaine.Conseiller;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("conseillerresource")
public class ConseillerWebService {
	
	private ConseillerDao cdao = new ConseillerDao();
	
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getIt() {
    	
    	Conseiller c = new Conseiller();
    	c.setLogin("test");
    	c.setPwd("123");
    	c = this.cdao.getConseiller(c);
    	System.out.println(c);
    	
        return "Got it!"+c;
    }
}

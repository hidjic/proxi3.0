package fr.gtm.webServiceProxiBanque;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.gtm.dao.CompteCourantDao;
import fr.gtm.dao.CompteEpargneDao;
import fr.gtm.domaine.CompteCourant;
import fr.gtm.domaine.CompteEpargne;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("comptewebservice")
public class CompteWebService {

	private CompteCourantDao ccdao = new CompteCourantDao();
	private CompteEpargneDao cedao = new CompteEpargneDao();
	private ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * Method handling HTTP GET requests. The returned object will be sent to the
	 * client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getIt() {
		return "ya get it... in comptewebservice...";
	}
	
	@PUT
	@Path("/putCompteCourant")
	@Produces(MediaType.APPLICATION_JSON)
	public Response putItCourant(String jsonCompteCourant) {
		System.out.println("dans le compte courant put...");
		String resJsonCompte = "";
		boolean updateCompte = false;
		try {
			System.out.println(jsonCompteCourant);
			CompteCourant cc = this.mapper.readValue(jsonCompteCourant, CompteCourant.class);
			updateCompte = this.ccdao.updateCompteCourant(cc);
			System.out.println(updateCompte);
			if(updateCompte) {
				resJsonCompte = this.mapper.writeValueAsString(cc);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(resJsonCompte);
		System.out.println("fin du put du compte sourant...");
		return Response.status(201).entity(resJsonCompte).build();
	}
	
	@PUT
	@Path("/putCompteEpargne")
	@Produces(MediaType.APPLICATION_JSON)
	public Response putItEpargne(String jsonCompteEpargne) {
		String resJsonCompte = "";
		boolean updateCompte = false;
		try {
			CompteEpargne ce = this.mapper.readValue(jsonCompteEpargne, CompteEpargne.class);
			updateCompte = this.cedao.updateCompteEpargne(ce);
			if(updateCompte) {
				resJsonCompte = this.mapper.writeValueAsString(ce);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Response.status(201).entity(resJsonCompte).build();
	}
	
}

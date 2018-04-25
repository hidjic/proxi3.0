package fr.gtm.webServiceProxiBanque;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.gtm.dao.CompteCourantDao;
import fr.gtm.dao.CompteEpargneDao;
import fr.gtm.dao.UserDao;
import fr.gtm.domaine.CompteCourant;
import fr.gtm.domaine.CompteEpargne;
import fr.gtm.domaine.User;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("userwebservice")
public class UserWebService {

	private UserDao udao = new UserDao();
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
//		String resJsonUser = "";
//		try {
//			String userInJsontest = "{\"nom\":\"Boivin\",\"prenom\":\"Stéphane\",\"idUser\":0,\"email\":null,\"adresse\":null,\"codePostal\":null,\"ville\":null,\"idConseiller\":1,\"unCC\":null,\"unCE\":null}";
//			User leUser = new User();
//			User u = this.mapper.readValue(userInJsontest, User.class);
//			System.out.println(u);
//			leUser = udao.create(u);
//			if (leUser.getIdUser() > 0) {
//				CompteCourant cc = new CompteCourant();
//				CompteEpargne ce = new CompteEpargne();
//				int idCC = this.ccdao.create(leUser);
//				int idCE = this.cedao.create(leUser);
//				if (idCC > 0) {
//					cc = this.ccdao.getCompteCourant(leUser);
//					leUser.setUnCC(cc);
//				}
//				if (idCE > 0) {
//					ce = this.cedao.getCompteEpargne(leUser);
//					leUser.setUnCE(ce);
//				}
//				// parse en json
//				resJsonUser = this.mapper.writeValueAsString(leUser);
//				System.out.println(resJsonUser);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		return "ya get it... in userwebservice...";
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response postIt(String jsonUser) {
		System.out.println("coté webservice...");
		System.out.println(jsonUser);
		String resJsonUser = "";
		User leUser = new User();

		try {
			User u = this.mapper.readValue(jsonUser, User.class);
			System.out.println(u);
			leUser = udao.create(u);
			if (leUser.getIdUser() > 0) {
				CompteCourant cc = new CompteCourant();
				CompteEpargne ce = new CompteEpargne();
				int idCC = this.ccdao.create(leUser);
				int idCE = this.cedao.create(leUser);
				if (idCC > 0) {
					cc = this.ccdao.getCompteCourant(leUser);
					leUser.setUnCC(cc);
				}
				if (idCE > 0) {
					ce = this.cedao.getCompteEpargne(leUser);
					leUser.setUnCE(ce);
				}
				// parse en json
				resJsonUser = this.mapper.writeValueAsString(leUser);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(201).entity(resJsonUser).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public String putIt(User unUser) {
		return "put it in userwebservice...";
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public String deleteIt(User unUser) {
		return "delete it in userwebservice...";
	}

}

package fr.gtm.webServiceProxiBanque;

import java.io.IOException;

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
	public Response getIt() {
		String resJsonUser = "";
		try {
			User u = new User();
			u.setIdUser(1);
			System.out.println(u);
			u = this.udao.getUser(u);
			System.out.println(u);
			resJsonUser = this.mapper.writeValueAsString(u);
			System.out.println(resJsonUser);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Response.status(201).entity(resJsonUser).build();
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
			leUser = this.udao.create(u);
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
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Response.status(201).entity(resJsonUser).build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response putIt(String jsonUser) {
		System.out.println("dans le put...");
		System.out.println(jsonUser);
		String resJsonUser = "";
		boolean updateUser = false;
		try {
			User u = this.mapper.readValue(jsonUser, User.class);
			System.out.println(u);
			updateUser = this.udao.updateUser(u);
			System.out.println(updateUser);
			if(updateUser) {
				resJsonUser = this.mapper.writeValueAsString(u);
				System.out.println(resJsonUser);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("dans le put... fin...");
		return Response.status(201).entity(resJsonUser).build();
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteIt(String jsonUser) {
		String resJsonUser = "";
		boolean deleteUser = false;
		try {
			User u = this.mapper.readValue(jsonUser, User.class);
			deleteUser = this.udao.deleteUser(u);
			if(deleteUser) {
				User badUser = new User();
				badUser.setIdUser(-1);
				resJsonUser = this.mapper.writeValueAsString(badUser);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Response.status(201).entity(resJsonUser).build();
	}

}

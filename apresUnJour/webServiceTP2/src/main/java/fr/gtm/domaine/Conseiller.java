package fr.gtm.domaine;

import java.util.ArrayList;
import java.util.List;

public class Conseiller extends Personne{
	
	private int idConseiller;
	private String login;
	private String pwd;
	private List<User> listU = new ArrayList<User>();
	
	public int getIdConseiller() {
		return idConseiller;
	}
	public void setIdConseiller(int idConseiller) {
		this.idConseiller = idConseiller;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public List<User> getListU() {
		return listU;
	}
	public void setListU(List<User> listU) {
		this.listU = listU;
	}
	@Override
	public String toString() {
		return "Conseiller [idConseiller=" + idConseiller + ", login=" + login + ", pwd=" + pwd + ", nom="+this.getNom()+", prenom="+this.getPrenom()+"]";
	}
	
	
	
	
	
	
}

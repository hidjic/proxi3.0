package fr.gtm.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author stephane
 *	Classe qui permet de connecter la couche dao à la base de donnée MySQL
 */
public class ConnectionDao {
	
	private String url = "jdbc:mysql://localhost/proxibanquedb";
	private String login = "root";
	private String psw = "root";
	private Connection cn = null;
	
	/**
	 * Methode de connection à la base de donnée MySQL qui retourne une connexion
	 * @return 
	 */
	public Connection connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.cn = DriverManager.getConnection(url, login, psw);
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return this.cn;
	}
	
	/**
	 * Permet d'avoir la date du jour pour la creation des compte lors de leur creation
	 * en base de données
	 * @return une chaine de caractere sous la forme jj-mm-aaaa
	 */
	public String dateConneted() {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		Date today = Calendar.getInstance().getTime();
		String reportDate = df.format(today);
		return reportDate;
	}
	
}

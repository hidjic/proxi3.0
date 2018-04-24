package fr.gtm.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
	
}

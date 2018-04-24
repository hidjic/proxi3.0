package fr.gtm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.gtm.domaine.CompteCourant;
import fr.gtm.domaine.User;

public class CompteCourantDao {
	
	private ConnectionDao cdao = new ConnectionDao();
	private Connection cn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private String sql;
	private String errorSql = "error dans le sql";
	
	public Boolean create(User unUser) {
		boolean result = false;
		this.cn = this.cdao.connect();
		try {
			this.sql = "INSERT INTO compte (numCompte, dateCreation, idClient)"
					+ " VALUES(?,?,?)";
			this.ps = this.cn.prepareStatement(this.sql);
			this.ps.setString(1, "numero de compte");
			this.ps.setString(2, "");
			this.ps.setInt(3, unUser.getIdUser());
			int res = this.ps.executeUpdate();
			result = res > 0 ? true : false;
		} catch (SQLException e) {
			System.out.println(this.errorSql);
			e.printStackTrace();
		}
		return result;
	}
	
}

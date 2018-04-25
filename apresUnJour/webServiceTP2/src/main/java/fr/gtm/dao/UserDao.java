package fr.gtm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.gtm.domaine.Conseiller;
import fr.gtm.domaine.User;

public class UserDao {
	
	private ConnectionDao cdao = new ConnectionDao();
	private Connection cn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private String sql;
	private String errorSql = "error dans le sql";
	
	public boolean create(User unUser) {
		boolean result = false;
		this.cn = this.cdao.connect();
		try {
			this.sql = "INSERT INTO client (nom,prenom,email,adresse,codePostal,ville,idConseiler)"
					+ " VALUES(?,?,?,?,?,?,?)";
			this.ps = this.cn.prepareStatement(this.sql);
			this.ps.setString(1, unUser.getNom());
			this.ps.setString(2, unUser.getPrenom());
			this.ps.setString(3, unUser.getEmail());
			this.ps.setString(4, unUser.getAdresse());
			this.ps.setString(5, unUser.getCodePostal());
			this.ps.setString(6, unUser.getVille());
			this.ps.setInt(7, unUser.getIdConseiller());
			int res = this.ps.executeUpdate();
			result = res > 0 ? true : false; 
		} catch (SQLException e) {
			System.out.println(this.errorSql);
			e.printStackTrace();
		}
		return result;
	}
	
	public User getUser(User unUser) {
		User leUser = new User();
		this.cn = this.cdao.connect();
		try {
			this.sql = "SELECT * FROM client WHERE idClient = ?";
			this.ps = this.cn.prepareStatement(this.sql);
			this.ps.setInt(1, unUser.getIdUser());
			this.rs = this.ps.executeQuery();
			if(this.rs.first()) {
				leUser.setIdUser(this.rs.getInt("idClient"));
				leUser.setNom(this.rs.getString("nom"));
				leUser.setPrenom(this.rs.getString("prenom"));
				leUser.setEmail(this.rs.getString("email"));
				leUser.setAdresse(this.rs.getString("adresse"));
				leUser.setCodePostal(this.rs.getString("codePostal"));
				leUser.setVille(this.rs.getString("ville"));
				leUser.setIdConseiller(this.rs.getInt("idConseiller"));
			}
		} catch (SQLException e) {
			System.out.println(this.errorSql);
			e.printStackTrace();
		}
		return leUser;
	}
	
	public boolean updateUser(User unUser) {
		boolean result = false;
		this.cn = this.cdao.connect();
		try {
			this.sql = "UPDATE client SET nom = ?, prenom = ?, email = ?, adresse = ?, codePostal = ?, ville = ?, idConseiler = ?"
					+ " WHERE idClient = ?";
			this.ps = this.cn.prepareStatement(this.sql);
			this.ps.setString(1, unUser.getNom());
			this.ps.setString(2, unUser.getPrenom());
			this.ps.setString(3, unUser.getEmail());
			this.ps.setString(4, unUser.getAdresse());
			this.ps.setString(5, unUser.getCodePostal());
			this.ps.setString(6, unUser.getVille());
			this.ps.setInt(7, unUser.getIdConseiller());
			this.ps.setInt(8, unUser.getIdUser());
			int res = this.ps.executeUpdate();
			result = res > 0 ? true : false; 
		} catch (SQLException e) {
			System.out.println(this.errorSql);
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean deleteUser(User unUser) {
		boolean result = false;
		this.cn = this.cdao.connect();
		try {
			this.sql = "DELETE FROM client WHERE idClient = ?";
			this.ps = this.cn.prepareStatement(this.sql);
			this.ps.setInt(1, unUser.getIdUser());
			int res = this.ps.executeUpdate();
			result = res > 0 ? true : false; 
		} catch (SQLException e) {
			System.out.println(this.errorSql);
			e.printStackTrace();
		}
		return result;
	}
	
	public List<User> getAllUser(){
		List<User> listUser = new ArrayList<User>();
		return listUser;
	}
	
	public List<User> getAllUserByConseiller(Conseiller leConseiller){
		List<User> listUser = new ArrayList<User>();
		this.cn = this.cdao.connect();
		try {
			this.sql = "SELECT * FROM client WHERE idConseiller = ?";
			this.ps = this.cn.prepareStatement(this.sql);
			this.ps.setInt(1, leConseiller.getIdConseiller());
			this.rs = this.ps.executeQuery();
			while(this.rs.next()) {
				User unUser = new User();
				unUser.setIdUser(this.rs.getInt("idClient"));
				unUser.setNom(this.rs.getString("nom"));
				unUser.setPrenom(this.rs.getString("prenom"));
				unUser.setEmail(this.rs.getString("email"));
				unUser.setAdresse(this.rs.getString("adresse"));
				unUser.setCodePostal(this.rs.getString("codePostal"));
				unUser.setVille(this.rs.getString("ville"));
				unUser.setIdConseiller(this.rs.getInt("idConseiller"));
				listUser.add(unUser);
			}
		} catch (SQLException e) {
			System.out.println(this.errorSql);
			e.printStackTrace();
		}
		return listUser;
	}
	
}

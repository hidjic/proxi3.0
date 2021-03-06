package fr.gtm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.gtm.domaine.CompteCourant;
import fr.gtm.domaine.User;

public class CompteCourantDao {
	
	private ConnectionDao cdao = new ConnectionDao();
	private Connection cn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private String sql;
	private String errorSql = "error dans le sql";
	
	/**
	 * Permet la creation d'un compte courant
	 * @param unUser = pour l'idClient necessaire en base de données
	 * @return l'id du compte qui vient d'etre créé
	 */
	public Integer create(User unUser) {
		int last_inserted_id = 0;
//		boolean result = false;
		this.cn = this.cdao.connect();
		String dateToday = this.cdao.dateConneted();
		CompteCourant cc = new CompteCourant();
		try {
			this.sql = "INSERT INTO compte (numCompte, dateCreation, decouvert, idClient)"
					+ " VALUES(?,?,?,?)";
			this.ps = this.cn.prepareStatement(this.sql, Statement.RETURN_GENERATED_KEYS);
			this.ps.setString(1, "numero de compte");
			this.ps.setString(2, dateToday);
			this.ps.setFloat(3, cc.getDecouvert());
			this.ps.setInt(4, unUser.getIdUser());
//			int res = this.ps.executeUpdate();
//			result = res > 0 ? true : false;
			this.ps.executeUpdate();
			this.rs = this.ps.getGeneratedKeys();
			if(this.rs.next()) {
				last_inserted_id = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println(this.errorSql);
			e.printStackTrace();
		}
		return last_inserted_id;
	}
	
	/**
	 * Permet la récupération d'un compte courant d'un client
	 * @param unUser = pour avoir l'idClient
	 * @return le compte courant
	 */
	public CompteCourant getCompteCourant(User unUser) {
		CompteCourant unCC = new CompteCourant();
		this.cn = this.cdao.connect();
		try {
			this.sql = "SELECT * FROM compte WHERE idClient = ? AND taux is null";
			this.ps = this.cn.prepareStatement(this.sql);
			this.ps.setInt(1, unUser.getIdUser());
			this.rs = this.ps.executeQuery();
			if(this.rs.first()) {
				unCC.setIdCompte(this.rs.getInt("idCompte"));
				unCC.setNumCompte(this.rs.getString("numCompte"));
				unCC.setDateCreation(this.rs.getString("dateCreation"));
				unCC.setSolde(this.rs.getFloat("solde"));
				unCC.setDecouvert(this.rs.getFloat("decouvert"));
//				boolean active = this.rs.getInt("isActive") == 1 ? true : false;
//				unCC.setActive(active);
				unCC.setIsActive(this.rs.getInt("isActive"));
			}
		} catch (SQLException e) {
			System.out.println(this.errorSql);
			e.printStackTrace();
		}
		return unCC;
	}
	
	/**
	 * Pemet de mettre a jours certaine valeur de l'objet CompteCourant en base de donnée
	 * Ne peut pas modifier idCompte, le numero de compte, la date de création et idClient
	 * @param unCC
	 * @return true ou false
	 */
	public Boolean updateCompteCourant(CompteCourant unCC) {
		boolean res = false;
		this.cn = this.cdao.connect();
		try {
			this.sql = "UPDATE compte SET isActive = ?, solde = ?, decouvert = ? WHERE idCompte = ?";
			this.ps = this.cn.prepareStatement(this.sql);
//			int compteActif = unCC.isActive() ? 1 : 0;
//			this.ps.setInt(1, compteActif);
			this.ps.setInt(1, unCC.getIsActive());
			this.ps.setFloat(2, unCC.getSolde());
			this.ps.setFloat(3, unCC.getDecouvert());
			this.ps.setInt(4, unCC.getIdCompte());
			int r = this.ps.executeUpdate();
			res = r > 0 ? true : false;
		} catch (SQLException e) {
			System.out.println(this.errorSql);
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * Permet de supprimer un courant courant dans la base de données
	 * @param unCC = pour avoir l'idCompte
	 * @return true ou false
	 */
	public boolean daleteCompteCourant(CompteCourant unCC) {
		boolean result = false;
		this.cn = this.cdao.connect();
		try {
			this.sql = "DELETE FROM compte WHERE idCompte = ?";
			this.ps = this.cn.prepareStatement(this.sql);
			this.ps.setInt(1, unCC.getIdCompte());
			int res = this.ps.executeUpdate();
			result = res > 0 ? true : false; 
		} catch (SQLException e) {
			System.out.println(this.errorSql);
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * Methode prevu dans la conception met pas encore implementé
	 * @param cc
	 * @param newDecouvert
	 * @return
	 */
	public CompteCourant modificationDecouvert(CompteCourant cc,float newDecouvert) {
		return null;
	}
}

package fr.gtm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.gtm.domaine.CompteEpargne;
import fr.gtm.domaine.User;

public class CompteEpargneDao {
	
	private ConnectionDao cdao = new ConnectionDao();
	private Connection cn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private String sql;
	private String errorSql = "error dans le sql";
	
	/**
	 * Permet la creation d'un compte epargne
	 * @param unUser = pour l'idClient necessaire en base de données
	 * @return idCompte du compte epargne qui vient d'etre créé
	 */
	public Integer create(User unUser) {
		int last_inserted_id = 0;
		this.cn = this.cdao.connect();
		String dateToday = this.cdao.dateConneted();
		CompteEpargne ce = new CompteEpargne();
		try {
			this.sql = "INSERT INTO compte (numCompte, dateCreation, taux, idClient)"
					+ " VALUES(?,?,?,?)";
			this.ps = this.cn.prepareStatement(this.sql);
			this.ps.setString(1, "numero de compte");
			this.ps.setString(2, dateToday);
			this.ps.setFloat(3, ce.getTaux());
			this.ps.setInt(4, unUser.getIdUser());
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
	 * Permet la récupération d'un compte epargne d'un client
	 * @param unUser = pour avoir l'idClient
	 * @return le compte epargne
	 */
	public CompteEpargne getCompteEpargne(User unUser) {
		CompteEpargne ce = new CompteEpargne();
		this.cn = this.cdao.connect();
		try {
			this.sql = "SELECT * FROM compte WHERE idClient = ? AND decouvert is null";
			this.ps = this.cn.prepareStatement(this.sql);
			this.ps.setInt(1, unUser.getIdUser());
			this.rs = this.ps.executeQuery();
			if(this.rs.first()) {
				ce.setIdCompte(this.rs.getInt("idCompte"));
				ce.setNumCompte(this.rs.getString("numCompte"));
				ce.setDateCreation(this.rs.getString("dateCreation"));
				ce.setSolde(this.rs.getFloat("solde"));
				ce.setTaux(this.rs.getFloat("taux"));
//				boolean active = this.rs.getInt("isActive") == 1 ? true : false;
//				ce.setActive(active);
				ce.setIsActive(this.rs.getInt("isActive"));
			}
		} catch (SQLException e) {
			System.out.println(this.errorSql);
			e.printStackTrace();
		}
		return ce;
	}
	
	/**
	 * Pemet de mettre a jours certaine valeur de l'objet CompteEpargne en base de donnée
	 * Ne peut pas modifier idCompte, le numero de compte, la date de création et idClient
	 * @param ce
	 * @return true ou false
	 */
	public Boolean updateCompteEpargne(CompteEpargne ce) {
		boolean res = false;
		this.cn = this.cdao.connect();
		try {
			this.sql = "UPDATE compte SET isActive = ?, solde = ?, taux = ? WHERE idCompte = ?";
			this.ps = this.cn.prepareStatement(this.sql);
//			int compteActif = ce.isActive() ? 1 : 0;
//			this.ps.setInt(1, compteActif);
			this.ps.setInt(1, ce.getIsActive());
			this.ps.setFloat(2, ce.getSolde());
			this.ps.setFloat(3, ce.getTaux());
			this.ps.setInt(4, ce.getIdCompte());
			int r = this.ps.executeUpdate();
			res = r > 0 ? true : false;
		} catch (SQLException e) {
			System.out.println(this.errorSql);
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * Permet de supprimer un courant epargne dans la base de données
	 * @param ce = pour avoir l'idCompte
	 * @return true ou false
	 */
	public boolean daleteCompteEpargne(CompteEpargne ce) {
		boolean result = false;
		this.cn = this.cdao.connect();
		try {
			this.sql = "DELETE FROM compte WHERE idCompte = ?";
			this.ps = this.cn.prepareStatement(this.sql);
			this.ps.setInt(1, ce.getIdCompte());
			int res = this.ps.executeUpdate();
			result = res > 0 ? true : false; 
		} catch (SQLException e) {
			System.out.println(this.errorSql);
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * Methode preuv dans la conception mais pas encore implementé
	 * @param ce
	 * @param newTaux
	 * @return
	 */
	public CompteEpargne modificationTaux(CompteEpargne ce, float newTaux) {
		return null;
	}
}

package fr.gtm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.gtm.domaine.Conseiller;

/**
 * @author stephane
 *	Classe gérant la couche dao du conseiller
 */
public class ConseillerDao {
	
	private ConnectionDao cdao = new ConnectionDao();
	private Connection cn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private String sql;
	private String errorSql = "error dans le sql";
	
	/**
	 * Methode pour obtenir un conseiller
	 * @param unC (avec dans l'objet seulement le login et le mot de passe)
	 * @return	un objet Conseiller complet
	 */
	public Conseiller getConseiller(Conseiller unC) {
		Conseiller leConseiller = new Conseiller();
		this.cn = this.cdao.connect();
		try {
			this.sql = "SELECT * FROM conseiller WHERE login = ? AND pwd = ?";
			this.ps = this.cn.prepareStatement(this.sql);
			this.ps.setString(1, unC.getLogin());
			this.ps.setString(2, unC.getPwd());
			this.rs = this.ps.executeQuery();
			if(this.rs.first()) {
				leConseiller.setIdConseiller(this.rs.getInt("idConseiller"));
				leConseiller.setNom(this.rs.getString("nom"));
				leConseiller.setPrenom(this.rs.getString("prenom"));
				leConseiller.setLogin(unC.getLogin());
				leConseiller.setPwd(unC.getPwd());
			}
		} catch (SQLException e) {
			System.out.println(this.errorSql);
			e.printStackTrace();
		}
		return leConseiller;
	}
	
}

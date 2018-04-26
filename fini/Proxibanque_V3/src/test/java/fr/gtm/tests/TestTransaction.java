package fr.gtm.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.gtm.domaine.CompteCourant;
import fr.gtm.service.ServiceUser;

public class TestTransaction {

	private CompteCourant monCompteDebiteur;
	private CompteCourant monCompteCrediteur;
	private ServiceUser monServiceUser;
	private float somme;

	@Before
	public void setUp() throws Exception {
		this.monCompteDebiteur = new CompteCourant();
		this.monCompteCrediteur = new CompteCourant();
		this.monServiceUser = new ServiceUser();
		System.out.println(">> declanche before : creation compte");
	}

	/**
	 * @param monCompteDebiteur
	 *            : corresond au compte d�biteur
	 * @param monCompteCrediteur
	 *            : correspond au compte cr�diteur
	 * @param somme
	 *            : montant de la transaction
	 * @param monServiceUser
	 *            : instancier pour appeler les m�thodes d�bit et Cr�dit de la
	 *            couche service (classe : ServiceUser)
	 * 
	 *            Test les m�thodes d�bit et Cr�dit afin de v�rifier si les soldes
	 *            des comptes cr�diteur et d�biteur sont coorectes apr�s la
	 *            transaction
	 */

	@SuppressWarnings("deprecation")
	@Test
	public void testDebit() {

		// =============premier test=========================
		this.monCompteDebiteur.setSolde(1000.0f);
		this.somme = 200.0f;
		this.monServiceUser.debitCompte(this.monCompteDebiteur, somme);
		System.out.println(this.monCompteDebiteur.getSolde());

		Assert.assertEquals(800.0, monCompteDebiteur.getSolde(), 0.0f);

		// =============second test=========================
		this.monCompteDebiteur.setSolde(500.0f);
		this.somme = 600.0f;
		this.monServiceUser.debitCompte(this.monCompteDebiteur, somme);
		System.out.println(this.monCompteDebiteur.getSolde());

		Assert.assertEquals(-100.0, monCompteDebiteur.getSolde(), 0.0f);

	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testCredit() {

		// =============premier test=========================
		this.monCompteCrediteur.setSolde(1000.0f);
		this.somme = 200.0f;
		this.monServiceUser.creditCompte(this.monCompteCrediteur, somme);
		System.out.println(this.monCompteCrediteur.getSolde());

		Assert.assertEquals(1200.0, monCompteCrediteur.getSolde(), 0.0f);

		// =============second test=========================
		this.monCompteDebiteur.setSolde(-500.0f);
		this.somme = 600.0f;
		this.monServiceUser.creditCompte(this.monCompteCrediteur, somme);
		System.out.println(this.monCompteCrediteur.getSolde());
	}
	
	

	@After
	public void tearDown() throws Exception {
		this.monCompteDebiteur = null;
		this.monCompteCrediteur = null;
		this.monServiceUser = null;
		System.out.println(">> declanche apres chaque test");
	}

}

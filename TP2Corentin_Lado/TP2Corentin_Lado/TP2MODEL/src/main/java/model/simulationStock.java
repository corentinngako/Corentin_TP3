package model;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Classe pour la gestion des threads de Producteur et Consommateur IL GERE 6
 * PRODUCTION ET 3 CONSOMMATION EN FONCTION DES DELAI DE PRODUCTION ET DE
 * CONCOMMATION ET DE LA QUANTITE A PRODUIRE
 * 
 * @author NGAKOKCM et LADO
 *
 */
public class simulationStock {
	private int QTE_CONSOMMEE = 150;
	private final long PERIODE_CONSOMMATION = 50;
	private final static long PERIODE_PRODUCTION_UN = 102;
	public final static int ITERATION_ANNUELLE = 52;

	private Reserve reserve;
	private ScheduledThreadPoolExecutor stpeProd;
	private ScheduledThreadPoolExecutor stpeCons;
	private long delaiProducteur;
	private long delaiConsommateur;
	private int qteProduite;

	public int getQTE_CONSOMMEE() {
		return QTE_CONSOMMEE;
	}

	public void setQTE_CONSOMMEE(int qTE_CONSOMMEE) {
		QTE_CONSOMMEE = qTE_CONSOMMEE;
	}

	public simulationStock(long delaiProducteur, long delaiConsommateur, int qteProduite) {
		this.stpeProd = new ScheduledThreadPoolExecutor(6);
		this.stpeCons = new ScheduledThreadPoolExecutor(3);
		this.reserve = new Reserve(stpeProd, stpeCons);
		this.delaiProducteur = delaiProducteur;
		this.delaiConsommateur = delaiConsommateur;
		this.qteProduite = qteProduite;
	}

	/**
	 * Démarre les gestionnaires de thread de consommateur et producteur
	 * 
	 */
	public void start() {
		this.createConsommateur();
		this.createProducteur();
	}

	/**
	 * Classe qui arrête les gestionnaires de thread de consommateure et producteur
	 * 
	 */
	public void stop() {

		this.stpeProd.shutdownNow();
		this.stpeCons.shutdownNow();
		if (stpeProd.isShutdown() && stpeCons.isShutdown())
			reserve.getBd().libererBD();
	}

	/**
	 * 3 tâches périodiques de Producteur.
	 * 
	 */
	private void createProducteur() {
		Producteur[] prodsUn = new Producteur[6];
		for (int i = 0; i < 6; i++) {
			prodsUn[i] = new Producteur(this.reserve, qteProduite, delaiProducteur, "Production");
			this.stpeProd.scheduleAtFixedRate(prodsUn[i], prodsUn[i].getDelai(), delaiProducteur,
					TimeUnit.MILLISECONDS);
		}
	}

	/**
	 * Part 6 tâches périodiques de Consommateur.
	 * 
	 */
	private void createConsommateur() {
		Consommateur[] cons = new Consommateur[3];
		for (int i = 0; i < 3; i++) {
			cons[i] = new Consommateur(this.reserve, QTE_CONSOMMEE, PERIODE_PRODUCTION_UN, "Consommation");
			this.stpeCons.scheduleAtFixedRate(cons[i], cons[i].getDelai(), PERIODE_CONSOMMATION, TimeUnit.MILLISECONDS);
		}
	}

	/**
	 * retourne la réserve associée à la classe.
	 * 
	 * @return Reserve reserve
	 */
	public Reserve getReserve() {
		return reserve;
	}

	/**
	 * retourne la base de donnée associée à la classe.
	 * 
	 * @return AccesBaseDeDonnee la base de donnée
	 */
	public AccesBD getBaseDeDonnee() {
		return reserve.getBd();
	}

}

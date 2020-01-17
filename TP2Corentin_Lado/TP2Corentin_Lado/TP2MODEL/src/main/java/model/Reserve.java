package model;

import java.sql.SQLException;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * classe qui coordonne les operations de production et de consommation
 * 
 * @author NGAKOKCM et LADO
 *
 */
public class Reserve extends Thread {
	private int STOCK_DEFAUT = 500; // stock initial = 500
	private int stock;
	private Observateur obs;
	private int comptAnnee;
	private IntegerProperty cptProperty;
	private InfoReserve infoReserve;
	private AccesBD bd;
	private ScheduledThreadPoolExecutor stpeProd;
	private ScheduledThreadPoolExecutor stpeCons;

	/**
	 *  constructeur de la classe
	 * @param stpeProd
	 * @param stpeCons
	 */
	public Reserve(ScheduledThreadPoolExecutor stpeProd, ScheduledThreadPoolExecutor stpeCons) {
		this.stock = STOCK_DEFAUT;
		this.infoReserve = new InfoReserve(STOCK_DEFAUT);
		this.bd = new AccesBD();
		this.stpeProd = stpeProd;
		this.stpeCons = stpeCons;
		this.comptAnnee = 0;
		this.cptProperty = new SimpleIntegerProperty(0);
	}
	/**
	 * methode pour retirer une quantite du stock
	 * @param val
	 * @param poolThread
	 */

	public synchronized void consommer(int val, String poolThread) {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			if (stpeProd.isShutdown() || stpeCons.isShutdown()) {

			} else {
				e.printStackTrace();
			}
		}
		comptAnnee++;
		cptProperty.setValue(comptAnnee);

		if (comptAnnee <= simulationStock.ITERATION_ANNUELLE) {
			stock -= val;
			val = -1 * val;
			CalculReserve calRes = new CalculReserve(infoReserve);
			calRes.calculer(stock, poolThread, val);
		}
		if (!stpeCons.isShutdown()) {
			try {
				bd.insererBD(infoReserve);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Platform.runLater(() -> {
				obs.getEtatStock(infoReserve);
			});
		}
	}
	/**
	 * methode pour ajouter une quantité au stock
	 * @param val
	 * @param poolThread
	 */

	public synchronized void produire(int val, String poolThread) {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			if (stpeProd.isShutdown() || stpeCons.isShutdown()) {

			} else {
				e.printStackTrace();
			}
		}
		comptAnnee++;
		cptProperty.setValue(comptAnnee);

		if (comptAnnee <= simulationStock.ITERATION_ANNUELLE) {
			stock += val;
			CalculReserve calcul = new CalculReserve(infoReserve);
			calcul.calculer(val, poolThread, val);
		}

		if (!stpeProd.isShutdown()) {
			try {
				bd.insererBD(infoReserve);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Platform.runLater(() -> {
				obs.getEtatStock(infoReserve);
			});
		}
	}

	

	public IntegerProperty getIntegerProperty() {
		return cptProperty;
	}



	public AccesBD getBd() {
		return this.bd;
	}

	
	public void ajouterObser(Observateur obs) {
		this.obs = obs;
	}
}
package model;

/**
 * classe pour calculer les effets du stock
 * 
 * @author NGAKOKCM et LADO
 *
 */

public class CalculReserve {

	final static int PENALITE = 20;
	final static int COUTSTOCK = 5;
	private InfoReserve infoReserve;

	public CalculReserve(InfoReserve infoReserve) {
		this.infoReserve = infoReserve;
	}

	/**
	 * calcul du stock a l'aide des threads
	 * @param stock
	 * @param poolThread
	 * @param val
	 */
	public void calculer(int stock, String poolThread, int val) {
		infoReserve.setPool(poolThread);
		infoReserve.setNbMvt(infoReserve.getNbMvt() + 1);
		infoReserve.setStock(stock);
		infoReserve.setDelta(val);
		ruptureStock();
	}

	/**
	 * calcul le nombre de ruptures
	 */
	public void ruptureStock() {
		if (infoReserve.getStock() < 0) {
			int cptRupture = infoReserve.getCptRupture() + 1;
			infoReserve.setCptRupture(cptRupture);
			infoReserve.setQteRupture(infoReserve.getStock() * -1);
		} else {
			infoReserve.setQteRupture(0);
		}
		calculPenalite();
	}

	/*
	 * calcul le montant de la penalités en cas de rupture
	 */
	public void calculPenalite() {
		if (infoReserve.getQteRupture() > 0) {
			infoReserve.setPenaliteCumule(infoReserve.getQteRupture() * PENALITE);
		}
		calculQteMoyenne();

	}

/*
 * calcule la quantité moyenne du stock
 */
	public void calculQteMoyenne() {
		if (infoReserve.getStock() < 0) {
			infoReserve.setStock(0);
		}
		infoReserve.setStockCumule(infoReserve.getStockCumule() + infoReserve.getStock());
		infoReserve.setQteMoyenne(infoReserve.getStockCumule() / infoReserve.getNbMvt());
		calculCoutStockMoyen();

	}

/**
 * calcule le cout moyen du stock
 */
	public void calculCoutStockMoyen() {
		infoReserve.setCoutStockMoyen(infoReserve.getQteMoyenne() * COUTSTOCK);
		calculTotalCout();
	}

/**
 * calcul le cout total du stock
 */
	public void calculTotalCout() {
		infoReserve.setCoutTotal(infoReserve.getCoutStockMoyen() + infoReserve.getPenaliteCumule());

	}

}

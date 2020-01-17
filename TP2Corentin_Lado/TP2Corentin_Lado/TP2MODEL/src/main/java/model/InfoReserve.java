package model;

/**
 * classe qui enregistre les information de la reserve
 * 
 * @author NGAKOKCM et LADO
 *
 */
public class InfoReserve {
	// attribut de la classe

	private int nbMvt = 0; // le nombre de mouvements de stock
	// private int idSimulation = 1 ;// le numero de la simulation
	private String pool; // le pool de thread
	private int delta = 0; // la quantite produite ou consommee
	private int stock = 0; // le stock
	private int cptRupture = 0; // nombre de fois ou il y a rupture de stock
	private int qteRupture = 0; // la quantite des rupture de stock
	private int penaliteCumule = 0; // la penalite cumulée
	private int qteMoyenne = 0; // la quantité moyenne de stock
	private int coutStockMoyen = 0; // le cout du stock moyen
	private int coutTotal = 0; // total annuel de la gestion des stocks
	private int stockCumule = 0; // total du stock cumule

	/**
	 * constructeur de la classe
	 * @param nbMvt
	 * @param pool
	 * @param delta
	 * @param stock
	 * @param cptRupture
	 * @param qteRupture
	 * @param penaliteCumule
	 * @param qteMoyenne
	 * @param coutStockMoyen
	 * @param coutTotal
	 * @param stockCumule
	 */
	public InfoReserve(int nbMvt, String pool, int delta, int stock, int cptRupture, int qteRupture, int penaliteCumule,
			int qteMoyenne, int coutStockMoyen, int coutTotal, int stockCumule) {

		this.nbMvt = nbMvt;
		this.pool = pool;
		this.delta = delta;
		this.stock = stock;
		this.cptRupture = cptRupture;
		this.qteRupture = qteRupture;
		this.penaliteCumule = penaliteCumule;
		this.qteMoyenne = qteMoyenne;
		this.coutStockMoyen = coutStockMoyen;
		this.coutTotal = coutTotal;
		this.stockCumule = stockCumule;
	}
	
	
/**
 * constructeur avec seulement le stock
 * @param stock
 */
	public InfoReserve(int stock) {
		this.nbMvt = 0;
		this.pool = "";
		this.delta = 0;
		this.stock = stock;
		this.cptRupture = 0;
		this.qteRupture = 0;
		this.penaliteCumule = 0;
		this.qteMoyenne = 0;
		this.coutStockMoyen = 0;
		this.coutTotal = 0;
		this.stockCumule = 0;
	}

	public int getNbMvt() {
		return nbMvt;
	}

	public void setNbMvt(int nbMvt) {
		this.nbMvt = nbMvt;
	}

	public String getPool() {
		return pool;
	}

	public void setPool(String pool) {
		this.pool = pool;
	}

	public int getDelta() {
		return delta;
	}

	public void setDelta(int delta) {
		this.delta = delta;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getCptRupture() {
		return cptRupture;
	}

	public void setCptRupture(int cptRupture) {
		this.cptRupture = cptRupture;
	}

	public int getQteRupture() {
		return qteRupture;
	}

	public void setQteRupture(int qteRupture) {
		this.qteRupture = qteRupture;
	}

	public int getPenaliteCumule() {
		return penaliteCumule;
	}

	public void setPenaliteCumule(int penaliteCumule) {
		this.penaliteCumule = penaliteCumule;
	}

	public int getQteMoyenne() {
		return qteMoyenne;
	}

	public void setQteMoyenne(int qteMoyenne) {
		this.qteMoyenne = qteMoyenne;
	}

	public int getCoutStockMoyen() {
		return coutStockMoyen;
	}

	public void setCoutStockMoyen(int coutStockMoyen) {
		this.coutStockMoyen = coutStockMoyen;
	}

	public int getCoutTotal() {
		return coutTotal;
	}

	public void setCoutTotal(int coutTotal) {
		this.coutTotal = coutTotal;
	}

	public int getStockCumule() {
		return stockCumule;
	}

	public void setStockCumule(int stockCumule) {
		this.stockCumule = stockCumule;
	}

}

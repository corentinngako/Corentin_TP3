package vue;

/**
 *  classe qui affiche les information de la simulation dans la tabviee
 * @author NGAKOKCM et LADO
 *
 */

public class AfficheTableView {

	// attribut de la classe
	private int num;
	private String nomThread;
	private int delta;
	private int stock;
	private int cptRupture;
	private int qrs;
	private int perte;
	private int qteMoy;
	private int coutStockMoyen;
	private int coutTotal;
	
	/**
	 * constructeur de la classe AfficheTableView
	 * @param num
	 * @param nomThread
	 * @param delta
	 * @param stock
	 * @param cptRupture
	 * @param qrs
	 * @param perte
	 * @param qteMoy
	 * @param coutStockMoyen
	 * @param coutTotal
	 */
	public AfficheTableView(int num, String nomThread, int delta, int stock,
			int cptRupture, int qrs, int perte, int qteMoy,
			int coutStockMoyen, int coutTotal) {
		
		this.num = num;
		this.nomThread = nomThread;
		this.delta = delta;
		this.stock = stock;
		this.cptRupture =cptRupture;
		this.qrs = qrs;
		this.perte = perte;
		this.qteMoy = qteMoy;
		this.coutStockMoyen = coutStockMoyen;
		this.coutTotal = coutTotal;
	}

	/*
	 *  getter et setter
	 */
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getNomThread() {
		return nomThread;
	}

	public void setNomThread(String nomThread) {
		this.nomThread = nomThread;
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

	public int getQrs() {
		return qrs;
	}

	public void setQrs(int qrs) {
		this.qrs = qrs;
	}

	public int getPerte() {
		return perte;
	}

	public void setPerte(int perte) {
		this.perte = perte;
	}

	public int getQteMoy() {
		return qteMoy;
	}

	public void setQteMoy(int qteMoy) {
		this.qteMoy = qteMoy;
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
	
		
}

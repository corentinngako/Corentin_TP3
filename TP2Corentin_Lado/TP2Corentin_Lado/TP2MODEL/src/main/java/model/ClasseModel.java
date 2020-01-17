package model;

/**
 * Classe modele des classes Producteur et Consommateur EN PARAMETRE LA RESERVE
 * ,LA QUANTITE ,LE DELAI ET LE TYPE( PRODUCTEUR OU CONSOMMATEUR)
 * 
 * @author NGAKOKCM et LADO
 */
public class ClasseModel extends Thread {

	private int qte;
	private Reserve reserve;
	private long delaiInitial;

	public ClasseModel(Reserve reserve, int qte, long delai, String name) {
		super(name);
		this.reserve = reserve;
		this.qte = qte;
		this.delaiInitial = delai;
	}

	/**
	 * 
	 * @return Reserve instance de Reserve
	 */
	public Reserve getReserve() {
		return this.reserve;
	}

	/**
	 * 
	 * @return int quantité produite
	 */
	public int getQte() {
		return this.qte;
	}

	/**
	 * 
	 * @return long délai initial
	 */
	public long getDelai() {
		return this.delaiInitial;
	}
}

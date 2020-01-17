package model;

/**
 * classe qui gerer une consommation
 * 
 * @author NGAKOKCM et LADO
 *
 */
public class Consommateur extends ClasseModel {
	
/**
 * constructeur Consommateur
 * @param reserve
 * @param qteConsommee
 * @param delaicons
 * @param name
 */
	public Consommateur(Reserve reserve, int qteConsommee, long delaicons, String name) {
		super(reserve, qteConsommee, delaicons, name);
	}

	@Override
	public void run() {
		this.getReserve().consommer(this.getQte(), this.getName());
	}
}

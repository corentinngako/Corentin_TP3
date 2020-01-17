package model;

/**
 * classe qui gere une production
 * 
 * @author NGAKOKCM et LADO
 *
 */

public class Producteur extends ClasseModel {
	public Producteur(Reserve reserve, int qteProduite, long delaiprod, String name) {
		super(reserve, qteProduite, delaiprod, name);
	}

	@Override
	public void run() {
		this.getReserve().produire(this.getQte(), this.getName());
	}
}
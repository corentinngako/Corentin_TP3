package vue;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * classe pour afficher les alertes
 * 
 * @author NGAKOKCM et LADO
 *
 */
public class AfficheAlerte {
	
	/**
	 *  methode pour afficher les erreurs
	 * @param message
	 */
	
	public static void afficherErreur(String message) {
		Alert alerte = new Alert(AlertType.ERROR);
		alerte.setHeaderText(message);
		alerte.showAndWait();
	}
	
	/*
	 * methode qui affiche a Propos
	 */
	public static void afficherAPropos() {
		Alert alerte = new Alert(AlertType.INFORMATION);
		alerte.setTitle("À propos");
		alerte.setHeaderText("Ceci est une application développée dans le cadre du cours POO 4");
		alerte.setContentText("Corentin NK & Lado S ");
		alerte.showAndWait();
	}
	
	/**
	 * message pour afficher des messages
	 * @param message
	 */
	public static void afficherMessage(String message) {
		Alert alerte = new Alert(AlertType.INFORMATION);
		alerte.setTitle("Message");
		alerte.setHeaderText(message);
		alerte.showAndWait();	
	}

}

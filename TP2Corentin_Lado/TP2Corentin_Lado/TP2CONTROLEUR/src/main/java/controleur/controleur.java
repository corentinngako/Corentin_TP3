package controleur;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * TP2-CORENTIN & LADO
 *
 */
public class controleur extends Application {
	FXMLcontroleur fxcontrolleur;
	private AnchorPane root;

	@Override
	public void start(Stage primaryStage) throws Exception {

		root = FXMLLoader.load(getClass().getResource("../TP2.fxml"));
		Scene scene = new Scene(root, 650, 850);
		primaryStage.setScene(scene);
		primaryStage.setTitle("TP1_Corentin_Lado");
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);

	}

}

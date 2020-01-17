
package controleur;

/**
 * TP2-CORENTIN & LADO
 *
 */
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.InfoReserve;
import model.Observateur;
import model.Reserve;
import model.simulationStock;
import vue.AfficheAlerte;
import vue.AfficheTableView;
import vue.ObservateurVue;
/**
 * classe FXML comtroleur ,c est elle qui gere toutes les cas d utilisations du projet
 * @author 1892431
 *
 */
public class FXMLcontroleur implements Initializable, ObservateurVue, Observateur {

	private simulationStock simul;

	@FXML
	private TextField tempsConso;

	@FXML
	private TextField qtelivrée;

	@FXML
	private Button btnDemarrer;

	@FXML
	private Button btnArreter;

	@FXML
	private TextField tempsFab;

	@FXML
	private TextField qteprod;

	@FXML
	private Button btnRAZ;

	@FXML
	private TextField qteStock;

	@FXML
	private TextField rupStock;

	@FXML
	private TextField tolPen;

	@FXML
	private TextField StockMoy;

	@FXML
	private TextField coutMoy;

	@FXML
	private TableView<AfficheTableView> resultTab;

	@FXML
	private TableColumn<AfficheTableView, Integer> colNum;

	@FXML
	private TableColumn<AfficheTableView, String> colTx;

	@FXML
	private TableColumn<AfficheTableView, Integer> colDelta;

	@FXML
	private TableColumn<AfficheTableView, Integer> colStock;

	@FXML
	private TableColumn<AfficheTableView, Integer> colRs;

	@FXML
	private TableColumn<AfficheTableView, Integer> colQRS;

	@FXML
	private TableColumn<AfficheTableView, Integer> colP;

	@FXML
	private TableColumn<AfficheTableView, Integer> colQteMoye;

	private ObservateurVue obs;

	private ObservableList<AfficheTableView> listeItems;

	private Reserve r = new Reserve(null, null);
	

	@FXML
	void Arretter(ActionEvent event) {

		Platform.runLater(() -> {
			initialButons(true);
		});
		if (simul != null) {
			System.exit(0);
		}

	}

	@FXML
	void Demarer(ActionEvent event) throws ClassNotFoundException {
		
		if (validerSaisie()) {
			resetTableView();

			Platform.runLater(() -> {
				initialButons(false);
			});
			simul = new simulationStock(getDelaiProducteur(), getDelaiConsommateur(), getQuantiteProd());
			simul.getReserve().ajouterObser(this);
			simul.getBaseDeDonnee().setBd();
			simul.start();
			gererSimulation();
		} else {
			AfficheAlerte.afficherErreur("saisie invalide");
			resetTextField();
		}

	}

	@FXML
	void remmettreAZero(ActionEvent event) {
		resetTextField();
		resetTableView();

	}

	/**
	 * reinitialiser les champ tableView
	 */
	public void resetTableView() {
		listeItems.removeAll(listeItems);

	}

	/**
	 * reiniatiliser les champ textField
	 */
	public void resetTextField() {
		tempsConso.setText(null);
		tempsFab.setText(null);
		qteStock.setText(null);
		qteprod.setText(null);
		coutMoy.setText(null);
		rupStock.setText(null);
		StockMoy.setText(null);
		tolPen.setText(null);

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		qtelivrée.setText("150");
		qtelivrée.setDisable(true);
		listeItems = FXCollections.observableArrayList();
		gestionTableView();

	}

	/**
	 * gestion du tableview 
	 */
	public void gestionTableView() {
		resultTab.setItems(listeItems);
		colNum.setCellValueFactory(new PropertyValueFactory<>("num"));
		colTx.setCellValueFactory(new PropertyValueFactory<>("nomThread"));
		colDelta.setCellValueFactory(new PropertyValueFactory<>("delta"));
		colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
		colRs.setCellValueFactory(new PropertyValueFactory<>("cptRupture"));
		colQRS.setCellValueFactory(new PropertyValueFactory<>("qrs"));
		colP.setCellValueFactory(new PropertyValueFactory<>("perte"));
		colQteMoye.setCellValueFactory(new PropertyValueFactory<>("qteMoy"));

		resultTab.getSelectionModel().selectedItemProperty().addListener((o, oldValue, newValue) -> {
			if (newValue != null) {
				qteStock.setText("" + newValue.getStock());
				rupStock.setText("" + newValue.getCptRupture());
				tolPen.setText("" + newValue.getPerte());
				StockMoy.setText("" + newValue.getQteMoy());
				coutMoy.setText("" + newValue.getCoutStockMoyen());
			}
		});

		resultTab.getItems().addListener(new ListChangeListener<AfficheTableView>() {
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends AfficheTableView> c) {
				while (c.next()) {
					if (c.wasAdded()) {
						AfficheTableView item = listeItems.get(c.getFrom());
						qteStock.setText("" + item.getStock());
						rupStock.setText("" + item.getCptRupture());
						tolPen.setText("" + item.getPerte());
						StockMoy.setText("" + item.getQteMoy());
						coutMoy.setText("" + item.getCoutStockMoyen());
					}
				}
			}
		});

	}
	/**
	 * mettre a jour la listeView a chaque modification
	 * @param tabV
	 */

	public void miseAjourListeView(AfficheTableView tabV) {
		listeItems.add(tabV);
	}

	/**
	 * 
	 * @return le delai de consommateur
	 */
	public Long getDelaiConsommateur() {
		return Long.valueOf(tempsConso.getText());
	}

	/**
	 *  
	 * @return le delai de production
	 */
	public Long getDelaiProducteur() {
		return Long.valueOf(tempsFab.getText());
	}

	/**
	 * les quantites produites
	 * @return la quantités du produit
	 */
	public int getQuantiteProd() {
		return Integer.valueOf(qteprod.getText());
	}

	@FXML
	void AfficherApropos(ActionEvent event) {
		AfficheAlerte.afficherAPropos();

	}

	/**
	 * quitter l application via le menu deroulant
	 * @param event
	 */
	@FXML
	void quitterApp(ActionEvent event) {
		Platform.exit();
		System.exit(0);

	}

	/**
	 * Ajouter un observateur dans la vue
	 * @param obs
	 */
	public void ajouterObservateur(ObservateurVue obs) {
		this.obs = obs;
	}
	/**
	 * Active ou desactive les bouttons
	 * @param b
	 */

	public void initialButons(boolean b) {
		btnDemarrer.setDisable(!b);
		btnArreter.setDisable(b);

	}

	/**
	 * valider les saisie
	 * @return valide si la saisie est valide
	 */
	public boolean validerSaisie() {
		boolean valide = true;
		try {
			Long.valueOf(tempsConso.getText());
			Long.valueOf(tempsFab.getText());
			Long.valueOf(qteprod.getText());
		} catch (NumberFormatException e) {
			valide = false;
		}
		return valide;
	}

	@Override
	public void stopApp() {

		initialButons(true);
		simul.stop();

	}

	// demarrer la simulation
	@Override
	public void demarrerApp() {
		if (validerSaisie()) {
			resetTableView();

			Platform.runLater(() -> {
				initialButons(false);
			});
			simul = new simulationStock(getDelaiProducteur(), getDelaiConsommateur(), getQuantiteProd());
			simul.getReserve().ajouterObser(this);
			try {
				simul.getBaseDeDonnee().setBd();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			simul.start();
			gererSimulation();
		} else {
			AfficheAlerte.afficherErreur("saisie invalide");
			resetTextField();
		}
	}

	public void gererSimulation() {
		simul.getReserve().getIntegerProperty().addListener((o, oldValue, newValue) -> {
			if (newValue.intValue() == simulationStock.ITERATION_ANNUELLE + 1) {
				this.stopApp();
				Platform.runLater(() -> {
					initialButons(true);
					 AfficheAlerte.afficherMessage("Fin de la simulation , vous pouvez passer a la production!!");
				});
			}
		});
	}

	@Override
	public void getEtatStock(InfoReserve infoReserve) {
		AfficheTableView tabV = new AfficheTableView(infoReserve.getNbMvt(), infoReserve.getPool(),
				infoReserve.getDelta(), infoReserve.getStock(), infoReserve.getCptRupture(),
				infoReserve.getQteRupture(), infoReserve.getPenaliteCumule(), infoReserve.getQteMoyenne(),
				infoReserve.getCoutStockMoyen(), infoReserve.getCoutTotal());
		miseAjourListeView(tabV);

	}
}

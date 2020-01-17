package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
/*
 * classe permettant de se connecter a la Base de données
 * 
 * @author Corentin Ngako & Lado 
 */

public class AccesBD {
	private Connection connec = null;
	private Integer annee = Calendar.getInstance().get(Calendar.YEAR);

	String url = "jdbc:mysql://localhost/tp2_simulation_stock";
	String login = "root";
	String passwd = "root";

	/*
	 * methode pour se connecter a la BD
	 */
	public void connecterBD() {

		try {
			connec = DriverManager.getConnection(url, login, passwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// methode pour liberer la BD

	public void libererBD() {
		if (connec != null) {
			try {
				connec.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// methode pour trouver la derniere année dans la BD
	public void setBd() throws ClassNotFoundException {
		Statement st = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connec = DriverManager.getConnection(url, login, passwd);
			// création d'un statement
			st = connec.createStatement();
			// requete
			String queryAnnee = "select * from annee";

			// execution de la requete
			rs = st.executeQuery(queryAnnee);
			// parcours des lignes extraites du Resultset
			while (rs.next()) {

				this.annee = rs.getInt("LibAnnee");

			}

			if (this.annee != null)
				this.annee++;

			String query = " insert into annee values(?)";
			PreparedStatement preparedSt = connec.prepareStatement(query);
			preparedSt.setInt(1, this.annee);
			preparedSt.execute();

		} catch (SQLException e) {
			System.out.println(e.toString());
		}
	}

// inserer la la BD l etat de la reserve
	public void insererBD(InfoReserve etat) throws SQLException {

		Statement st = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connec = DriverManager.getConnection(url, login, passwd);
			st = connec.createStatement();
			String q = " truncate table simulation";
			st.execute(q);
			String query = " insert into simulation (idSimulation, idThread"
					+ ",Qte, Stock, Nombre_RS, Qte_RS, Penalité, QteMoy, Annee)"
					+ " values (etat.getNbMvt(), etat.getPool(), etat.getDelta(), etat.getStock(), "
					+ "etat.getCptRupture(), etat.getQteRupture(), etat.getPenaliteCumule(), "
					+ "etat.getQteMoyenne(), this.annee)";

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connec.close();
		st.close();
	}

}

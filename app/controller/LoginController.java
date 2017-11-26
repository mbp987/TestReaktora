package app.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import app.database.DBConnector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController {
	@FXML
	private AnchorPane login_window;

	@FXML
	private TextField tf_login;

	@FXML
	private PasswordField pf_pass;

	@FXML
	private TextField tf_pass;

	@FXML
	private Button btn_login;

	@FXML
	private Button btn_show;

	// Nie pobrane z SceneBuilder'a
	static boolean flag = true;
	static String name;
	Connection db;
	static String role;
	
	@FXML
	void buttonLogin(MouseEvent event) throws SQLException {
		Statement stmt = db.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT role FROM login WHERE login = '" + tf_login.getText() + "' AND pass = '" + pf_pass.getText() + "';");
		if (rs.next()) {
			role = rs.getString("role");
			name = tf_login.getText();
			try {
				Stage stage = new Stage();
				Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/view/TableView.fxml"));
				Scene scene = new Scene(parent);
				stage.setScene(scene);
				stage.setTitle("Login completed");
				stage.show();
				((Node) (event.getSource())).getScene().getWindow().hide();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Alert a_help = new Alert(AlertType.ERROR);
			a_help.setHeaderText("B³¹d logowania");
			a_help.setContentText("B³êdne has³o lub login");
			a_help.setTitle("Nale¿y podaæ poprawne dane logowania");
			a_help.showAndWait();
		}
	}

	@FXML
	void showPass(MouseEvent event) {
		// Czytam z passwordField
		if (flag == true) {
			String pf_text = pf_pass.getText();
			pf_pass.setVisible(false);
			tf_pass.setVisible(true);
			tf_pass.setText(pf_text);
			flag = false;
			btn_show.setText("hide");
		} else {
			String pf_text = tf_pass.getText();
			pf_pass.setVisible(true);
			tf_pass.setVisible(false);
			pf_pass.setText(pf_text);
			flag = true;
			btn_show.setText("show");
		}
	}
	
	public void initialize() {
		db = DBConnector.getConnection();
	}
}

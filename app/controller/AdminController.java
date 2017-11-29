package app.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import app.database.DBConnector;
import app.model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class AdminController {

	@FXML
	private AnchorPane admin_general_view;

	@FXML
	private Button btn_users;

	@FXML
	private Button btn_questions;

	@FXML
	private Button btn_results;

	@FXML
	private AnchorPane view_users;

	@FXML
	private Button btn_new;

	@FXML
	private Button btn_edit;

	@FXML
	private Button btn_delete;

	@FXML
	private Button btn_showUsers;

	@FXML
	private Button btn_commit;

	@FXML
	private Button btn_filterUsers;

	@FXML
	private Label lbl_addUser;

	@FXML
	private Label lbl_editUser;

	@FXML
	private Label lbl_filterUser;

	@FXML
	private TableView<Users> tbl_users;

	@FXML
	private TableColumn<Users, String> col_login;

	@FXML
	private TableColumn<Users, String> col_haslo;

	@FXML
	private TableColumn<Users, String> col_imie;

	@FXML
	private TableColumn<Users, String> col_nazwisko;

	@FXML
	private TableColumn<Users, String> col_grupa;

	@FXML
	private TableColumn<Users, String> col_rola;

	@FXML
	private TableColumn<Users, String> col_timestamp;

	@FXML
	private AnchorPane tbl_edit;

	@FXML
	private TextField tf_login;

	@FXML
	private TextField tf_haslo;

	@FXML
	private TextField tf_imie;

	@FXML
	private TextField tf_nazwisko;

	@FXML
	private TextField tf_grupa;

	@FXML
	private ComboBox<String> combo_rola;

	@FXML
	private Button btn_editSave;

	@FXML
	private Button btn_editCancel;

	@FXML
	private Button btn_newUser;

	@FXML
	private AnchorPane tbl_filter;

	@FXML
	private TextField tf_loginFiltr;

	@FXML
	private TextField tf_hasloFiltr;

	@FXML
	private TextField tf_imieFiltr;

	@FXML
	private TextField tf_nazwiskoFiltr;

	@FXML
	private TextField tf_grupaFiltr;

	@FXML
	private ComboBox<String> combo_rolaFiltr;

	@FXML
	private Button btn_filter;

	@FXML
	private Button btn_filterCancel;

	// Dodane spoza scheletona
	ObservableList<String> rola = FXCollections.observableArrayList("user", "admin");
	Connection conn = DBConnector.getConnection();
	public ObservableList<Users> data;

	// G��wne menu:
	@FXML
	void actionUsers(MouseEvent event) {
		view_users.setVisible(true);

	}

	@FXML
	void actionQuestions(MouseEvent event) {
		view_users.setVisible(false);

	}

	@FXML
	void actionResults(MouseEvent event) {
		view_users.setVisible(false);

	}

	// Wewn�trz anchor_users:
	@FXML
	void actionDeleteUser(MouseEvent event) throws SQLException {
		lbl_filterUser.setVisible(false);
		lbl_addUser.setVisible(false);
		lbl_editUser.setVisible(false);
		Users selectedItem = tbl_users.getSelectionModel().getSelectedItem();
		if (selectedItem != null) {
			String login = selectedItem.getLogin();
			String sql = "DELETE FROM uzytkownicy WHERE login = '" + login + "'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			actionShowUsers(event);
		}
	}

	@FXML
	void actionEditUser(MouseEvent event) {
		tbl_filter.setVisible(false);
		lbl_filterUser.setVisible(false);
		lbl_addUser.setVisible(false);
		lbl_editUser.setVisible(true);
		btn_editSave.setVisible(true);
		btn_newUser.setVisible(false);
		Users selectedItem = tbl_users.getSelectionModel().getSelectedItem();
		if (selectedItem != null) {
			tbl_edit.setVisible(true);
			btn_newUser.setVisible(false);
			btn_editSave.setVisible(true);
			tf_login.setDisable(true);
			tf_login.setText(selectedItem.getLogin());
			tf_haslo.setText(selectedItem.getHaslo());
			tf_imie.setText(selectedItem.getImie());
			tf_nazwisko.setText(selectedItem.getNazwisko());
			tf_grupa.setText(selectedItem.getGrupa());
			combo_rola.setValue(selectedItem.getRola());
		}
	}

	@FXML
	void actionEditSave(MouseEvent event) throws SQLException {
		String sql = "UPDATE uzytkownicy SET haslo = '" + tf_haslo.getText() + "', imie = '" + tf_imie.getText() + "', nazwisko = '" + tf_nazwisko.getText()
				+ "', grupa = '" + tf_grupa.getText() + "', rola = '" + combo_rola.getValue() + "' WHERE login = '" + tf_login.getText() + "'";
		System.out.println(sql);
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.executeUpdate();
		tbl_edit.setVisible(false);
		btn_editSave.setVisible(false);
		actionShowUsers(event);
	}

	@FXML
	void actionEditCancel(MouseEvent event) {
		tbl_edit.setVisible(false);
	}

	@FXML
	void actionNewUser(MouseEvent event) throws SQLException {
		tbl_filter.setVisible(false);
		tbl_edit.setVisible(true);
		lbl_filterUser.setVisible(false);
		lbl_addUser.setVisible(true);
		lbl_editUser.setVisible(false);
		btn_editSave.setVisible(false);
		btn_newUser.setVisible(true);
		tf_login.setDisable(false);
		tf_login.clear();
		tf_haslo.clear();
		tf_imie.clear();
		tf_nazwisko.clear();
		tf_grupa.clear();
		combo_rola.setValue(null);
	}

	@FXML
	void actionSaveNew(MouseEvent event) throws SQLException {
		String login, haslo, imie, nazwisko, grupa, rola;
		while (true) {
			if (tf_login.getText() != null) {
				login = tf_login.getText();
				String sql = "SELECT login FROM uzytkownicy WHERE login = '" + login + "'";
				ResultSet rs = conn.createStatement().executeQuery(sql);
				try {
					rs.next();
					if (rs.getString(1) != null) {
						Alert view_error = new Alert(AlertType.ERROR);
						view_error.setContentText("Taki login ju� istnieje w bazie, zmie� login");
						view_error.setHeaderText("B��d!");
						view_error.setTitle("Okno b��du");
						view_error.showAndWait();
						break;
					}
				} catch (Exception e) {
				}
			} else {
				Alert view_error = new Alert(AlertType.ERROR);
				view_error.setContentText("Nie wpisa�e� loginu");
				view_error.setHeaderText("B��d!");
				view_error.setTitle("Okno b��du");
				view_error.showAndWait();
				break;
			}
			if (tf_haslo.getText() != null) {
				haslo = tf_haslo.getText();
			} else {
				Alert view_error = new Alert(AlertType.ERROR);
				view_error.setContentText("Nie wpisa�e� has�a");
				view_error.setHeaderText("B��d!");
				view_error.setTitle("Okno b��du");
				view_error.showAndWait();
				break;
			}
			if (tf_imie.getText() != null) {
				imie = tf_imie.getText();
			} else {
				Alert view_error = new Alert(AlertType.ERROR);
				view_error.setContentText("Nie poda�e� imienia");
				view_error.setHeaderText("B��d!");
				view_error.setTitle("Okno b��du");
				view_error.showAndWait();
				break;
			}
			if (tf_nazwisko.getText() != null) {
				nazwisko = tf_nazwisko.getText();
			} else {
				Alert view_error = new Alert(AlertType.ERROR);
				view_error.setContentText("Nie poda�e� imienia");
				view_error.setHeaderText("B��d!");
				view_error.setTitle("Okno b��du");
				view_error.showAndWait();
				break;
			}
			if (tf_grupa.getText() != null) {
				grupa = tf_grupa.getText();
			} else {
				Alert view_error = new Alert(AlertType.ERROR);
				view_error.setContentText("Nie poda�e� grupy");
				view_error.setHeaderText("B��d!");
				view_error.setTitle("Okno b��du");
				view_error.showAndWait();
				break;
			}
			if (combo_rola.getValue() != null) {
				rola = combo_rola.getValue();
			} else {
				Alert view_error = new Alert(AlertType.ERROR);
				view_error.setContentText("Nie wybra�e� roli");
				view_error.setHeaderText("B��d!");
				view_error.setTitle("Okno b��du");
				view_error.showAndWait();
				break;
			}
			String sql = "INSERT INTO uzytkownicy (login, haslo, imie, nazwisko, grupa, rola) VALUES ('" + login + "', '" + haslo + "', '" + imie + "', '"
					+ nazwisko + "', '" + grupa + "', '" + rola + "')";
			System.out.println(sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			actionShowUsers(event);
			break;
		}
	}

	@FXML
	void actionCommit(MouseEvent event) throws SQLException {
		Users selectedItem = tbl_users.getSelectionModel().getSelectedItem();
		if (selectedItem != null) {
			String login_selected = selectedItem.getLogin();
			String haslo_selected = selectedItem.getHaslo();
			String imie_selected = selectedItem.getImie();
			String nazwisko_selected = selectedItem.getNazwisko();
			String grupa_selected = selectedItem.getGrupa();
			String rola_selected = selectedItem.getRola();
			String sql = "UPDATE uzytkownicy SET haslo = '" + haslo_selected + "', imie = '" + imie_selected + "', nazwisko = '" + nazwisko_selected
					+ "', grupa = '" + grupa_selected + "', rola = '" + rola_selected + "' WHERE login = '" + login_selected + "'";
			System.out.println(sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			actionShowUsers(event);
		}
	}

	@FXML
	void actionShowUsers(MouseEvent event) throws SQLException {
		lbl_filterUser.setVisible(false);
		lbl_addUser.setVisible(false);
		lbl_editUser.setVisible(false);
		tbl_edit.setVisible(false);
		data = FXCollections.observableArrayList();
		ResultSet rs = conn.createStatement().executeQuery("select * from uzytkownicy");
		while (rs.next()) {
			data.add(new Users(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
		}
		tbl_users.setItems(null);
		tbl_users.setItems(data);
	}

	@FXML
	void actionFilterUsers(MouseEvent event) {
		tbl_edit.setVisible(false);
		tbl_filter.setVisible(true);
		lbl_filterUser.setVisible(true);
		lbl_addUser.setVisible(false);
		lbl_editUser.setVisible(false);
	}

	@FXML
	void actionFilter(MouseEvent event) throws SQLException {
		PreparedStatement pstm = null;
		data = FXCollections.observableArrayList();
		String login = "";
		String haslo = "";
		String imie = "";
		String nazwisko = "";
		String grupa = "";
		String rola = "";

		if (!Objects.isNull(tf_loginFiltr)) {
			login = tf_loginFiltr.getText();
		}
		if (!Objects.isNull(tf_hasloFiltr)) {
			haslo = tf_hasloFiltr.getText();
		}
		if (!Objects.isNull(tf_imieFiltr)) {
			imie = tf_imieFiltr.getText();
		}
		if (!Objects.isNull(tf_nazwiskoFiltr)) {
			nazwisko = tf_nazwiskoFiltr.getText();
		}
		if (!Objects.isNull(tf_grupaFiltr)) {
			grupa = tf_grupaFiltr.getText();
		}
		if (combo_rolaFiltr.getValue() != null) {
			rola = combo_rolaFiltr.getValue();
		}
		// Budowa zapytania do filtrowania u�ytkownik�w:
		String sql = "SELECT * FROM uzytkownicy WHERE 1 = 1";
		if (!login.isEmpty()) {
			sql += " AND login = '" + login + "'";
		}
		if (!haslo.isEmpty()) {
			sql += " AND haslo = '" + haslo + "'";
		}
		if (!imie.isEmpty()) {
			sql += " AND imie = '" + imie + "'";
		}
		if (!nazwisko.isEmpty()) {
			sql += " AND nazwisko = '" + nazwisko + "'";
		}
		if (!grupa.isEmpty()) {
			sql += " AND grupa = '" + grupa + "'";
		}
		if (!rola.isEmpty()) {
			sql += " AND rola = '" + rola + "'";
		}
		System.out.println(sql);
		pstm = conn.prepareStatement(sql);
		ResultSet rs = conn.createStatement().executeQuery(sql);
		while (rs.next()) {
			data.add(new Users(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
		}
		tbl_users.setItems(null);
		tbl_users.setItems(data);
	}

	@FXML
	void actionFilterCancel(MouseEvent event) throws SQLException {
		tf_loginFiltr.clear();
		tf_hasloFiltr.clear();
		tf_imieFiltr.clear();
		tf_nazwiskoFiltr.clear();
		tf_grupaFiltr.clear();
		combo_rolaFiltr.setValue(null);
		actionShowUsers(event);
	}

	public void initialize() {
		System.out.println("initialize");
		tbl_edit.setVisible(false);
		combo_rola.setItems(rola);
		combo_rolaFiltr.setItems(rola);
		col_login.setCellValueFactory(new PropertyValueFactory<Users, String>("login"));
		col_haslo.setCellValueFactory(new PropertyValueFactory<Users, String>("haslo"));
		col_haslo.setCellFactory(TextFieldTableCell.forTableColumn());
		col_haslo.setOnEditCommit(new EventHandler<CellEditEvent<Users, String>>() {
			@Override
			public void handle(CellEditEvent<Users, String> t) {
				((Users) t.getTableView().getItems().get(t.getTablePosition().getRow())).setHaslo(t.getNewValue());
			}
		});
		col_imie.setCellValueFactory(new PropertyValueFactory<Users, String>("imie"));
		col_imie.setCellFactory(TextFieldTableCell.forTableColumn());
		col_imie.setOnEditCommit(new EventHandler<CellEditEvent<Users, String>>() {
			@Override
			public void handle(CellEditEvent<Users, String> t) {
				((Users) t.getTableView().getItems().get(t.getTablePosition().getRow())).setImie(t.getNewValue());
			}
		});
		col_nazwisko.setCellValueFactory(new PropertyValueFactory<Users, String>("nazwisko"));
		col_nazwisko.setCellFactory(TextFieldTableCell.forTableColumn());
		col_nazwisko.setOnEditCommit(new EventHandler<CellEditEvent<Users, String>>() {
			@Override
			public void handle(CellEditEvent<Users, String> t) {
				((Users) t.getTableView().getItems().get(t.getTablePosition().getRow())).setNazwisko(t.getNewValue());
			}
		});
		col_grupa.setCellValueFactory(new PropertyValueFactory<Users, String>("grupa"));
		col_grupa.setCellFactory(TextFieldTableCell.forTableColumn());
		col_grupa.setOnEditCommit(new EventHandler<CellEditEvent<Users, String>>() {
			@Override
			public void handle(CellEditEvent<Users, String> t) {
				((Users) t.getTableView().getItems().get(t.getTablePosition().getRow())).setGrupa(t.getNewValue());
			}
		});
		col_rola.setCellValueFactory(new PropertyValueFactory<Users, String>("rola"));
		col_rola.setCellFactory(TextFieldTableCell.forTableColumn());
		col_rola.setOnEditCommit(new EventHandler<CellEditEvent<Users, String>>() {
			@Override
			public void handle(CellEditEvent<Users, String> t) {
				((Users) t.getTableView().getItems().get(t.getTablePosition().getRow())).setRola(t.getNewValue());
			}
		});
		col_timestamp.setCellValueFactory(new PropertyValueFactory<Users, String>("czas"));
		col_timestamp.setCellFactory(TextFieldTableCell.forTableColumn());
		col_timestamp.setOnEditCommit(new EventHandler<CellEditEvent<Users, String>>() {
			@Override
			public void handle(CellEditEvent<Users, String> t) {
				((Users) t.getTableView().getItems().get(t.getTablePosition().getRow())).setCzas(t.getNewValue());
			}
		});
	}

}

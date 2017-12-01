package app.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import app.database.DBConnector;
import app.model.Questions;
import app.model.Results;
import app.model.Users;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import util.Util;

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
	private Button btn_logout;

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

	// Anchor questions
	@FXML
	private AnchorPane view_questions;

	@FXML
	private Button btn_showQuestions;

	@FXML
	private Button btn_addQuestion;

	@FXML
	private Button btn_editQuestion;

	@FXML
	private Button btn_deleteQuestion;

	@FXML
	private Button btn_commitQuestion;

	@FXML
	private TableView<Questions> tbl_questions;

	@FXML
	private TableColumn<Questions, Integer> col_questionId;

	@FXML
	private TableColumn<Questions, String> col_questionLang;

	@FXML
	private TableColumn<Questions, String> col_question;

	@FXML
	private TableColumn<Questions, String> col_answear1;

	@FXML
	private TableColumn<Questions, String> col_answear2;

	@FXML
	private TableColumn<Questions, String> col_answear3;

	@FXML
	private TableColumn<Questions, String> col_answear4;

	@FXML
	private TableColumn<Questions, String> col_correctAnswear;

	@FXML
	private TableColumn<Questions, String> col_questionTimestamp;

	@FXML
	private AnchorPane tbl_questionEdit;

	@FXML
	private TextField tf_questionID;

	@FXML
	private ComboBox<String> combo_questionLang;

	@FXML
	private TextArea ta_questionText;

	@FXML
	private TextArea ta_answear1;

	@FXML
	private TextArea ta_answear2;

	@FXML
	private TextArea ta_answear3;

	@FXML
	private TextArea ta_answear4;

	@FXML
	private TextField tf_correctAnswear;

	@FXML
	private ToggleGroup odpowiedzi;

	@FXML
	private RadioButton rb_answear1;

	@FXML
	private RadioButton rb_answear4;

	@FXML
	private RadioButton rb_answear3;

	@FXML
	private RadioButton rb_answear2;

	@FXML
	private RadioButton rb_answearFalse;

	@FXML
	private Button btn_newQuestion;

	@FXML
	private Button btn_questionEditSave;

	@FXML
	private Button btn_questionFilter;

	@FXML
	private Button btn_questionCancel;

	@FXML
	private TextField tf_questionTimestamp;

	// Result view
	@FXML
	private AnchorPane view_results;

	@FXML
	private Button btn_results_users;

	@FXML
	private Button btn_results_groups;

	@FXML
	private Button btn_result_show_one_group;

	@FXML
	private Button btn_result_show_all_groups;

	@FXML
	private Button btn_result_filter_groups;

	@FXML
	private Button btn_result_show_one_user;

	@FXML
	private Button btn_result_show_all_users;

	@FXML
	private Button btn_result_filter_users;

	@FXML
	private AnchorPane view_result_details;

	@FXML
	private ComboBox<String> combo_result_user_credentials;

	@FXML
	private Label lbl_result_table;

	@FXML
	private GridPane gridUser;

	@FXML
	private TextField user_all_liczba_testow;

	@FXML
	private TextField user_BD_liczba_testow;

	@FXML
	private TextField user_Git_liczba_testow;

	@FXML
	private TextField user_Python_liczba_testow;

	@FXML
	private TextField user_FE_liczba_testow;

	@FXML
	private TextField user_Java_liczba_testow;

	@FXML
	private TextField user_Spring_liczba_testow;

	@FXML
	private TextField user_all_liczba_pytan;

	@FXML
	private TextField user_BD_liczba_pytan;

	@FXML
	private TextField user_Git_liczba_pytan;

	@FXML
	private TextField user_Python_liczba_pytan;

	@FXML
	private TextField user_FE_liczba_pytan;

	@FXML
	private TextField user_Java_liczba_pytan;

	@FXML
	private TextField user_Spring_liczba_pytan;

	@FXML
	private TextField user_all_dobrych;

	@FXML
	private TextField user_BD_dobrych;

	@FXML
	private TextField user_Git_dobrych;

	@FXML
	private TextField user_Python_dobrych;

	@FXML
	private TextField user_FE_dobrych;

	@FXML
	private TextField user_Java_dobrych;

	@FXML
	private TextField user_Spring_dobrych;

	@FXML
	private AnchorPane view_result_filter;

	@FXML
	private TextField tf_result_user_login_filter;

	@FXML
	private TextField tf_result_user_imie_filter;

	@FXML
	private TextField tf_result_user_nazwisko_filter;

	@FXML
	private Button btn_result_filter;

	@FXML
	private Button btn_result_filter_clear;

	@FXML
	private ComboBox<String> combo_result_grupa_filtr;

	@FXML
	private ComboBox<String> combo_result_jezyk_filtr;

	@FXML
	private TableView<Results> tbl_userResults;

	@FXML
	private TableColumn<Results, String> col_resultLogin;

	@FXML
	private TableColumn<Results, String> col_resultImie;

	@FXML
	private TableColumn<Results, String> col_resultNazwisko;

	@FXML
	private TableColumn<Results, String> col_resultGrupa;

	@FXML
	private TableColumn<Results, Integer> col_resultIDWynik;

	@FXML
	private TableColumn<Results, String> col_resultJezyk;

	@FXML
	private TableColumn<Results, Integer> col_resultLiczbaPytan;

	@FXML
	private TableColumn<Results, Float> col_resultWynik;

	@FXML
	private TableColumn<Results, String> col_resultTime;

	@FXML
	private TableView<Results> tbl_groupResults;

	@FXML
	private TableColumn<Results, String> col_resultgroupGrupa;

	@FXML
	private TableColumn<Results, String> col_resultgroupJezyk;

	@FXML
	private TableColumn<Results, Integer> col_resultgroupLiczbaTestow;

	@FXML
	private TableColumn<Results, Integer> col_resultgroupLiczbaPytan;

	@FXML
	private TableColumn<Results, Integer> col_resultgroupSredniWynik;

	// Dodane spoza scheletona
	ObservableList<String> rola = FXCollections.observableArrayList("user", "admin");
	ObservableList<String> lang = FXCollections.observableArrayList("BD", "Git", "Python", "FE", "Java", "Spring");
	ObservableList<String> grupa;
	ObservableList<String> jezyk;
	ObservableList<String> nazwiskoImie;

	Connection conn = DBConnector.getConnection();
	public ObservableList<Users> usersList;
	public ObservableList<Questions> questionsList;
	public ObservableList<Results> resultsList;

	HashMap<String, String> loginCredentials = new HashMap<>();

	// GÅ‚Ã³wne menu:
	@FXML
	void actionUsers(MouseEvent event) {
		view_questions.setVisible(false);
		view_users.setVisible(true);
		view_results.setVisible(false);
	}

	@FXML
	void actionQuestions(MouseEvent event) {
		view_users.setVisible(false);
		view_questions.setVisible(true);
		view_results.setVisible(false);
	}

	@FXML
	void actionResults(MouseEvent event) {
		view_users.setVisible(false);
		view_questions.setVisible(false);
		view_results.setVisible(true);
		view_result_details.setVisible(false);
		view_result_filter.setVisible(false);
		btn_result_show_one_group.setDisable(true);
		btn_result_show_all_groups.setDisable(true);
		btn_result_filter_groups.setDisable(true);
		btn_result_show_one_user.setDisable(true);
		btn_result_show_all_users.setDisable(true);
		btn_result_filter_users.setDisable(true);
		combo_result_user_credentials.setDisable(true);
	}

	// Wewnï¿½trz anchor_users:
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
						view_error.setContentText("Taki login ju¿ istnieje w bazie - podaj inny login");
						view_error.setHeaderText("B³¹d!");
						view_error.setTitle("Okno b³êdu");
						view_error.showAndWait();
						break;
					}
				} catch (Exception e) {
				}
			} else {
				Alert view_error = new Alert(AlertType.ERROR);
				view_error.setContentText("Nie wpisano loginu");
				view_error.setHeaderText("B³¹d!");
				view_error.setTitle("Okno b³êdu");
				view_error.showAndWait();
				break;
			}
			if (tf_haslo.getText() != null) {
				haslo = tf_haslo.getText();
			} else {
				Alert view_error = new Alert(AlertType.ERROR);
				view_error.setContentText("Nie wpisano hasÅ‚a");
				view_error.setHeaderText("B³¹d!");
				view_error.setTitle("Okno b³êdu");
				view_error.showAndWait();
				break;
			}
			if (tf_imie.getText() != null) {
				imie = tf_imie.getText();
			} else {
				Alert view_error = new Alert(AlertType.ERROR);
				view_error.setContentText("Nie podano imienia");
				view_error.setHeaderText("B³¹d!");
				view_error.setTitle("Okno b³êdu");
				view_error.showAndWait();
				break;
			}
			if (tf_nazwisko.getText() != null) {
				nazwisko = tf_nazwisko.getText();
			} else {
				Alert view_error = new Alert(AlertType.ERROR);
				view_error.setContentText("Nie podano imienia");
				view_error.setHeaderText("B³¹d!");
				view_error.setTitle("Okno b³êdu");
				view_error.showAndWait();
				break;
			}
			if (tf_grupa.getText() != null) {
				grupa = tf_grupa.getText();
			} else {
				Alert view_error = new Alert(AlertType.ERROR);
				view_error.setContentText("Nie podano grupy");
				view_error.setHeaderText("B³¹d!");
				view_error.setTitle("Okno b³êdu");
				view_error.showAndWait();
				break;
			}
			if (combo_rola.getValue() != null) {
				rola = combo_rola.getValue();
			} else {
				Alert view_error = new Alert(AlertType.ERROR);
				view_error.setContentText("Nie wybrano roli");
				view_error.setHeaderText("B³¹d!");
				view_error.setTitle("Okno b³êdu");
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
		tf_questionID.setDisable(true);
		usersList = FXCollections.observableArrayList();
		ResultSet rs = conn.createStatement().executeQuery("select * from uzytkownicy");
		while (rs.next()) {
			usersList.add(new Users(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
		}
		tbl_users.setItems(null);
		tbl_users.setItems(usersList);
	}

	@FXML
	void actionFilterUsers(MouseEvent event) {
		tbl_edit.setVisible(false);
		tbl_filter.setVisible(true);
		lbl_filterUser.setVisible(true);
		tf_questionID.setDisable(false);
		lbl_addUser.setVisible(false);
		lbl_editUser.setVisible(false);
	}

	@FXML
	void actionFilter(MouseEvent event) throws SQLException {
		PreparedStatement pstm = null;
		usersList = FXCollections.observableArrayList();
		String login = "";
		String haslo = "";
		String imie = "";
		String nazwisko = "";
		String grupa = "";
		String rola = "";

		if (!Objects.isNull(tf_loginFiltr)) {
			login = Util.convert(tf_loginFiltr.getText());
		}
		if (!Objects.isNull(tf_hasloFiltr)) {
			haslo = Util.convert(tf_hasloFiltr.getText());
		}
		if (!Objects.isNull(tf_imieFiltr)) {
			imie = Util.convert(tf_imieFiltr.getText());
		}
		if (!Objects.isNull(tf_nazwiskoFiltr)) {
			nazwisko = Util.convert(tf_nazwiskoFiltr.getText());
		}
		if (!Objects.isNull(tf_grupaFiltr)) {
			grupa = Util.convert(tf_grupaFiltr.getText());
		}
		if (combo_rolaFiltr.getValue() != null) {
			rola = combo_rolaFiltr.getValue();
		}
		// Budowa zapytania do filtrowania uÅ¼ytkownikÃ³w:
		String sql = "SELECT * FROM uzytkownicy WHERE 1 = 1";
		if (!login.isEmpty()) {
			sql += " AND login LIKE '" + login + "'";
		}
		if (!haslo.isEmpty()) {
			sql += " AND haslo LIKE '" + haslo + "'";
		}
		if (!imie.isEmpty()) {
			sql += " AND imie LIKE '" + imie + "'";
		}
		if (!nazwisko.isEmpty()) {
			sql += " AND nazwisko LIKE '" + nazwisko + "'";
		}
		if (!grupa.isEmpty()) {
			sql += " AND grupa LIKE '" + grupa + "'";
		}
		if (!rola.isEmpty()) {
			sql += " AND rola = '" + rola + "'";
		}
		System.out.println(sql);
		pstm = conn.prepareStatement(sql);
		ResultSet rs = conn.createStatement().executeQuery(sql);
		while (rs.next()) {
			usersList.add(new Users(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
		}
		tbl_users.setItems(null);
		tbl_users.setItems(usersList);
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

	// Metody dotyczÄ…ce sekcji pytaÅ„:
	// Metoda pokazujÄ…ca wszystkie pytania
	@FXML
	void actionShowQuestions(ActionEvent event) throws SQLException {
		tbl_questionEdit.setVisible(false);
		questionsList = FXCollections.observableArrayList();
		ResultSet rs = conn.createStatement().executeQuery("select * from pytania");
		while (rs.next()) {
			// Questions(Integer id_pytania, String jezyk, String tresc, String
			// odp1, String odp2, String odp3, String odp4, String
			// prawidlowa_odp, String czas)
			questionsList.add(new Questions(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
					rs.getString(8), rs.getString(9)));
		}
		tbl_questions.setItems(null);
		tbl_questions.setItems(questionsList);
	}

	// Metoda uruchamiajÄ…ca panel dodawania pytaÅ„
	@FXML
	void actionAddQuestion(ActionEvent event) {
		tbl_questionEdit.setVisible(true);
		btn_questionEditSave.setVisible(false);
		btn_newQuestion.setVisible(true);
		tf_questionID.clear();
		combo_questionLang.setValue(null);
		ta_questionText.clear();
		ta_answear1.clear();
		ta_answear2.clear();
		ta_answear3.clear();
		ta_answear4.clear();
		tf_questionTimestamp.clear();
		rb_answearFalse.setSelected(true);
	}

	// Metoda zapisujÄ…ca pytanie do bazy
	@FXML
	void actionQuestionSave(ActionEvent event) throws SQLException {
		String jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp;
		while (true) {
			if (combo_questionLang.getValue() != null) {
				jezyk = combo_questionLang.getValue();
			} else {
				Alert view_error = new Alert(AlertType.ERROR);
				view_error.setContentText("Nie wpisano jêzyka");
				view_error.setHeaderText("B³¹d!");
				view_error.setTitle("Okno b³êdu");
				view_error.showAndWait();
				break;
			}
			if (ta_questionText.getText() != null) {
				tresc = ta_questionText.getText();
			} else {
				Alert view_error = new Alert(AlertType.ERROR);
				view_error.setContentText("Nie wpisano treœci pytania");
				view_error.setHeaderText("B³¹d!");
				view_error.setTitle("Okno b³êdu");
				view_error.showAndWait();
				break;
			}
			if (ta_answear1.getText() != null) {
				odp1 = ta_answear1.getText();
			} else {
				Alert view_error = new Alert(AlertType.ERROR);
				view_error.setContentText("Nie podano pierwszej odpowiedzi");
				view_error.setHeaderText("B³¹d!");
				view_error.setTitle("Okno b³êdu");
				view_error.showAndWait();
				break;
			}
			if (ta_answear2.getText() != null) {
				odp2 = ta_answear2.getText();
			} else {
				Alert view_error = new Alert(AlertType.ERROR);
				view_error.setContentText("Nie podano drugiej odpowiedzi");
				view_error.setHeaderText("B³¹d!");
				view_error.setTitle("Okno b³êdu");
				view_error.showAndWait();
				break;
			}
			if (ta_answear3.getText() != null) {
				odp3 = ta_answear3.getText();
			} else {
				Alert view_error = new Alert(AlertType.ERROR);
				view_error.setContentText("Nie podano trzeciej odpowiedzi");
				view_error.setHeaderText("B³¹d!");
				view_error.setTitle("Okno b³êdu");
				view_error.showAndWait();
				break;
			}
			if (ta_answear4.getText() != null) {
				odp4 = ta_answear4.getText();
			} else {
				Alert view_error = new Alert(AlertType.ERROR);
				view_error.setContentText("Nie podano czwartej odpowiedzi");
				view_error.setHeaderText("B³¹d!");
				view_error.setTitle("Okno b³êdu");
				view_error.showAndWait();
				break;
			}
			// Pobranie prawidï¿½owej odpowiedzi z radio button'ï¿½w
			{
				if (!rb_answear1.equals("")) {
					prawidlowa_odp = ta_answear1.getText();
					tf_correctAnswear.setText("1");
				} else if (!rb_answear2.equals("")) {
					prawidlowa_odp = ta_answear2.getText();
					tf_correctAnswear.setText("2");
				} else if (!rb_answear3.equals("")) {
					prawidlowa_odp = ta_answear3.getText();
					tf_correctAnswear.setText("3");
				} else if (!rb_answear4.equals("")) {
					prawidlowa_odp = ta_answear4.getText();
					tf_correctAnswear.setText("4");
				} else {
					Alert view_error = new Alert(AlertType.ERROR);
					view_error.setContentText("Nie wybra³eœ ¿adnej prawid³owej odpowiedzi");
					view_error.setHeaderText("B³¹d!");
					view_error.setTitle("Okno b³êdu");
					view_error.showAndWait();
					break;
				}
			}
			String sql = "INSERT INTO pytania (jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp) VALUES ('" + jezyk + "', '" + tresc + "', '" + odp1
					+ "', '" + odp2 + "', '" + odp3 + "', '" + odp4 + "', '" + prawidlowa_odp + "')";
			System.out.println(sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			actionShowQuestions(event);
			actionAddQuestion(event);
			break;
		}
	}

	// Metoda uruchamiajÄ…ca panel edycji pytania zaznaczonego w tabeli
	@FXML
	void actionEditQuestion(ActionEvent event) {
		tf_questionID.setDisable(true);
		Questions selectedItem = tbl_questions.getSelectionModel().getSelectedItem();
		if (selectedItem != null) {
			tbl_questionEdit.setVisible(true);
			btn_questionEditSave.setVisible(true);
			btn_newQuestion.setVisible(false);
			tf_questionID.setText(String.valueOf(selectedItem.getId_pytania()));
			combo_questionLang.setValue(selectedItem.getJezyk());
			ta_questionText.setText(selectedItem.getTresc());
			ta_answear1.setText(selectedItem.getOdp1());
			ta_answear2.setText(selectedItem.getOdp2());
			ta_answear3.setText(selectedItem.getOdp3());
			ta_answear4.setText(selectedItem.getOdp4());
			tf_questionTimestamp.setText(selectedItem.getCzas());
			rb_answearFalse.setSelected(true);
			// Znalezienie prawidÅ‚owej odpowiedzi i ustawienie radio button'a
			{
				if (ta_answear1.getText().equals(selectedItem.getPrawidlowa_odp())) {
					rb_answear1.setSelected(true);
					tf_correctAnswear.setText("1");
				} else if (ta_answear2.getText().equals(selectedItem.getPrawidlowa_odp())) {
					rb_answear2.setSelected(true);
					tf_correctAnswear.setText("2");
				} else if (ta_answear3.getText().equals(selectedItem.getPrawidlowa_odp())) {
					rb_answear3.setSelected(true);
					tf_correctAnswear.setText("3");
				} else if (ta_answear4.getText().equals(selectedItem.getPrawidlowa_odp())) {
					rb_answear4.setSelected(true);
					tf_correctAnswear.setText("4");
				}
			}
		} else {
			tbl_questionEdit.setVisible(true);
			btn_questionEditSave.setVisible(true);
			btn_newQuestion.setVisible(false);
		}

	}

	@FXML
	void actionQuestionFilter(ActionEvent event) throws SQLException {
		PreparedStatement pstm = null;
		questionsList = FXCollections.observableArrayList();
		String jezyk = "";
		String tresc = "";
		String odp1 = "";
		String odp2 = "";
		String odp3 = "";
		String odp4 = "";

		if (!Objects.isNull(combo_questionLang.getValue())) {
			jezyk = combo_questionLang.getValue();
		}
		if (!Objects.isNull(ta_questionText)) {
			tresc = Util.convert(ta_questionText.getText());
		}
		if (!Objects.isNull(ta_answear1)) {
			odp1 = Util.convert(ta_answear1.getText());
		}
		if (!Objects.isNull(ta_answear2)) {
			odp2 = Util.convert(ta_answear2.getText());
		}
		if (!Objects.isNull(ta_answear3)) {
			odp3 = Util.convert(ta_answear3.getText());
		}
		if (!Objects.isNull(ta_answear4)) {
			odp4 = Util.convert(ta_answear4.getText());
		}
		// Budowa zapytania do filtrowania u¿ytkowników:
		String sql = "SELECT * FROM pytania WHERE 1 = 1";
		if (!jezyk.isEmpty()) {
			sql += " AND jezyk LIKE '" + jezyk + "'";
		}
		if (!tresc.isEmpty()) {
			sql += " AND tresc LIKE '" + tresc + "'";
		}
		if (!odp1.isEmpty()) {
			sql += " AND odp1 LIKE '" + odp1 + "'";
		}
		if (!odp2.isEmpty()) {
			sql += " AND odp2 LIKE '" + odp2 + "'";
		}
		if (!odp3.isEmpty()) {
			sql += " AND odp3 LIKE '" + odp3 + "'";
		}
		if (!odp4.isEmpty()) {
			sql += " AND odp4 LIKE '" + odp4 + "'";
		}
		System.out.println(sql);
		pstm = conn.prepareStatement(sql);
		ResultSet rs = conn.createStatement().executeQuery(sql);
		while (rs.next()) {
			questionsList.add(new Questions(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
					rs.getString(8), rs.getString(9)));
		}
		tbl_questions.setItems(null);
		tbl_questions.setItems(questionsList);
	}

	// Metoda usuwaj¹ca zaznaczone w tabeli pytanie
	@FXML
	void actionDeleteQuestion(ActionEvent event) throws SQLException {
		tbl_questionEdit.setVisible(false);
		Questions selectedItem = tbl_questions.getSelectionModel().getSelectedItem();
		if (selectedItem != null) {
			Integer id = selectedItem.getId_pytania();
			String sql = "DELETE FROM pytania WHERE id_pytania = " + id;
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			actionShowQuestions(event);
		} else {
			Alert view_error = new Alert(AlertType.ERROR);
			view_error.setContentText("Nie wybrano ¿adnego wiersza w tabeli do usuniêcia, najpierw wybierz wiersz do usuniêcia");
			view_error.setHeaderText("B³¹d!");
			view_error.setTitle("Okno b³êdu");
			view_error.showAndWait();
		}
	}

	// Metoda zatwierdzaj¹ca zmiany w pytaniu WPROWADZONE W TABELI I
	// ZATWIERDZONE ENTEREM
	@FXML
	void actionCommitQuestion(ActionEvent event) throws SQLException {
		String jezyk, tresc, odp1, odp2, odp3, odp4, prawidlowa_odp;
		Questions selectedItem = tbl_questions.getSelectionModel().getSelectedItem();
		if (selectedItem != null) {
			Integer id = selectedItem.getId_pytania();
			jezyk = selectedItem.getJezyk();
			tresc = selectedItem.getTresc();
			odp1 = selectedItem.getOdp1();
			odp2 = selectedItem.getOdp2();
			odp3 = selectedItem.getOdp3();
			odp4 = selectedItem.getOdp4();
			{
				if (rb_answear1.isSelected()) {
					prawidlowa_odp = ta_answear1.getText();
					tf_correctAnswear.setText("1");
				} else if (rb_answear2.isSelected()) {
					prawidlowa_odp = ta_answear2.getText();
					tf_correctAnswear.setText("2");
				} else if (rb_answear3.isSelected()) {
					prawidlowa_odp = ta_answear3.getText();
					tf_correctAnswear.setText("3");
				} else if (rb_answear4.isSelected()) {
					prawidlowa_odp = ta_answear4.getText();
					tf_correctAnswear.setText("4");
				}
			}
			prawidlowa_odp = selectedItem.getPrawidlowa_odp();
			String sql = "UPDATE pytania SET jezyk = '" + jezyk + "', tresc = '" + tresc + "', odp1 = '" + odp1 + "', odp2 = '" + odp2 + "', odp3 = '" + odp3
					+ "', odp4 = '" + odp4 + "', prawidlowa_odp = '" + prawidlowa_odp + "' WHERE id_pytania = " + id;
			System.out.println(sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			actionShowQuestions(event);
		}
	}

	// Metoda zapisujÄ…ca pytanie po edycji
	@FXML
	void actionQuestionEditSave(ActionEvent event) throws SQLException {
		String prawidlowa_odp = "";
		{
			if (rb_answear1.isSelected()) {
				prawidlowa_odp = Util.convert(ta_answear1.getText());
				tf_correctAnswear.setText("1");
			} else if (rb_answear2.isSelected()) {
				prawidlowa_odp = Util.convert(ta_answear2.getText());
				tf_correctAnswear.setText("2");
			} else if (rb_answear3.isSelected()) {
				prawidlowa_odp = Util.convert(ta_answear3.getText());
				tf_correctAnswear.setText("3");
			} else if (rb_answear4.isSelected()) {
				prawidlowa_odp = Util.convert(ta_answear4.getText());
				tf_correctAnswear.setText("4");
			}
		}
		String sql = "UPDATE pytania SET jezyk = '" + combo_questionLang.getValue() + "', tresc = '" + ta_questionText.getText() + "', odp1 = '"
				+ ta_answear1.getText() + "', odp2 = '" + ta_answear2.getText() + "', odp3 = '" + ta_answear3.getText() + "', odp4 = '" + ta_answear4.getText()
				+ "', prawidlowa_odp = '" + prawidlowa_odp + "' WHERE id_pytania = " + Integer.valueOf(tf_questionID.getText());
		System.out.println(sql);
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.executeUpdate();
		tbl_questionEdit.setVisible(false);
		btn_questionEditSave.setVisible(false);
		actionShowQuestions(event);
	}

	// Metoda anuluj¹ca wprowadzanie zmian/dodawanie pytania
	@FXML
	void actionQuestionCancel(ActionEvent event) throws SQLException {
		tbl_questionEdit.setVisible(true);
		btn_questionEditSave.setVisible(true);
		btn_newQuestion.setVisible(false);
		tf_questionID.clear();
		combo_questionLang.setValue(null);
		ta_questionText.clear();
		ta_answear1.clear();
		ta_answear2.clear();
		ta_answear3.clear();
		ta_answear4.clear();
		tf_questionTimestamp.clear();
		rb_answearFalse.setSelected(true);
		actionShowQuestions(event);
		actionEditQuestion(event);
	}

	// Metody Results:
	@FXML
	void actionResultClearFilter(ActionEvent event) throws SQLException {
		tf_result_user_login_filter.clear();
		tf_result_user_imie_filter.clear();
		tf_result_user_nazwisko_filter.clear();
		combo_result_grupa_filtr.setValue(null);
		combo_result_jezyk_filtr.setValue(null);
		if (tf_result_user_login_filter.isVisible() != true) {
			actionResultsAllGroups(event);
			actionResultsFilterGroups(event);
		} else {
			actionResultsAllUsers(event);
			actionResultsFilterUsers(event);
		}
	}

	@FXML
	void actionResultFilter(ActionEvent event) throws SQLException {
		if (tf_result_user_login_filter.isVisible() != true) {
			PreparedStatement pstm = null;
			resultsList = FXCollections.observableArrayList();
			String grupa = "";
			String jezyk = "";
			System.out.println("combo grupa: " + combo_result_grupa_filtr.getValue());
			if (combo_result_grupa_filtr.getValue() != null) {
				grupa = combo_result_grupa_filtr.getValue();
			}
			if (combo_result_jezyk_filtr.getValue() != null) {
				jezyk = combo_result_jezyk_filtr.getValue();
			}
			// Budowa zapytania do filtrowania uÅ¼ytkownikÃ³w:
			String sql = "SELECT grupa, jezyk, count(login) as liczba_testow, avg(liczba_pytan) as liczba_pytan, avg(wynik) as wynik FROM wyniki LEFT JOIN uzytkownicy USING (login) WHERE 1 = 1";
			if (!grupa.isEmpty()) {
				sql += " AND grupa LIKE '" + grupa + "'";
			}
			if (!jezyk.isEmpty()) {
				sql += " AND jezyk LIKE '" + jezyk + "'";
			}
			sql += " GROUP BY concat(grupa, jezyk)";
			System.out.println(sql);
			pstm = conn.prepareStatement(sql);
			ResultSet rs = conn.createStatement().executeQuery(sql);
			while (rs.next()) {
				Integer wynik = Math.round(rs.getFloat(4) * 100);
				resultsList.add(new Results(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), wynik));
			}
			tbl_groupResults.setItems(null);
			tbl_groupResults.setItems(resultsList);
		} else {
			PreparedStatement pstm = null;
			resultsList = FXCollections.observableArrayList();
			String login = "";
			String imie = "";
			String nazwisko = "";
			String grupa = "";
			String jezyk = "";

			if (!Objects.isNull(tf_result_user_login_filter)) {
				login = Util.convert(tf_result_user_login_filter.getText());
			}
			if (!Objects.isNull(tf_result_user_imie_filter)) {
				imie = Util.convert(tf_result_user_imie_filter.getText());
			}
			if (!Objects.isNull(tf_result_user_nazwisko_filter)) {
				nazwisko = Util.convert(tf_result_user_nazwisko_filter.getText());
			}
			if (combo_result_grupa_filtr.getValue() != null) {
				grupa = combo_result_grupa_filtr.getValue();
			}
			if (combo_result_jezyk_filtr.getValue() != null) {
				jezyk = combo_result_jezyk_filtr.getValue();
			}
			// Budowa zapytania do filtrowania uÅ¼ytkownikÃ³w:
			String sql = "SELECT login, imie, nazwisko, grupa, id_wynik, jezyk, liczba_pytan, wynik, wyniki.czas FROM wyniki LEFT JOIN uzytkownicy USING (login) WHERE 1 = 1";
			if (!login.isEmpty()) {
				sql += " AND login LIKE '" + login + "'";
			}
			if (!imie.isEmpty()) {
				sql += " AND imie LIKE '" + imie + "'";
			}
			if (!nazwisko.isEmpty()) {
				sql += " AND nazwisko LIKE '" + nazwisko + "'";
			}
			if (!grupa.isEmpty()) {
				sql += " AND grupa LIKE '" + grupa + "'";
			}
			if (!jezyk.isEmpty()) {
				sql += " AND jezyk LIKE '" + jezyk + "'";
			}
			System.out.println(sql);
			pstm = conn.prepareStatement(sql);
			ResultSet rs = conn.createStatement().executeQuery(sql);
			while (rs.next()) {
				Integer wynik = Math.round(rs.getFloat(8) * 100);
				resultsList.add(new Results(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getInt(7),
						wynik, rs.getString(9)));
			}
			tbl_userResults.setItems(null);
			tbl_userResults.setItems(resultsList);
		}
	}

	@FXML
	void actionResultsAllGroups(ActionEvent event) throws SQLException {
		view_result_details.setVisible(false);
		combo_result_user_credentials.setValue(null);
		tbl_userResults.setVisible(false);
		tbl_groupResults.setVisible(true);
		lbl_result_table.setVisible(true);
		lbl_result_table.setText("Œrednie wyniki dla wszystkich grup");
		view_result_filter.setVisible(false);
		resultsList = FXCollections.observableArrayList();
		ResultSet rs = conn.createStatement().executeQuery(
				"SELECT grupa, jezyk, count(login) as liczba_testow, avg(liczba_pytan) as liczba_pytan, avg(wynik) as wynik FROM wyniki LEFT JOIN uzytkownicy USING (login) GROUP BY concat(grupa, jezyk)");
		while (rs.next()) {
			Integer wynik = Math.round(rs.getFloat(5) * 100);
			resultsList.add(new Results(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), wynik));
		}
		tbl_groupResults.setItems(null);
		tbl_groupResults.setItems(resultsList);
	}

	@FXML
	void actionResultsAllUsers(ActionEvent event) throws SQLException {
		combo_result_user_credentials.setValue(null);
		tbl_userResults.setVisible(true);
		view_result_details.setVisible(false);
		view_result_filter.setVisible(false);
		lbl_result_table.setVisible(true);
		lbl_result_table.setText("Wyniki dla wszystkich u¿ytkowników");
		resultsList = FXCollections.observableArrayList();
		ResultSet rs = conn.createStatement().executeQuery(
				"select login, imie, nazwisko, grupa, id_wynik, jezyk, liczba_pytan, wynik, wyniki.czas FROM wyniki LEFT JOIN uzytkownicy USING (login)");
		while (rs.next()) {
			Integer wynik = Math.round(rs.getFloat(8) * 100);
			resultsList.add(new Results(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getInt(7), wynik,
					rs.getString(9)));
		}
		tbl_userResults.setItems(null);
		tbl_userResults.setItems(resultsList);
	}

	@FXML
	void actionResultsFilterGroups(ActionEvent event) {
		view_result_filter.setVisible(true);
	}

	@FXML
	void actionResultsFilterUsers(ActionEvent event) {
		view_result_filter.setVisible(true);

	}

	@FXML
	void actionResultsGroups(ActionEvent event) {
		lbl_result_table.setVisible(false);
		btn_result_show_one_group.setDisable(false);
		btn_result_show_all_groups.setDisable(false);
		btn_result_filter_groups.setDisable(false);
		btn_result_show_one_user.setDisable(true);
		btn_result_show_all_users.setDisable(true);
		btn_result_filter_users.setDisable(true);
		combo_result_user_credentials.setDisable(true);
		tf_result_user_login_filter.setVisible(false);
		tf_result_user_imie_filter.setVisible(false);
		tf_result_user_nazwisko_filter.setVisible(false);
		tbl_userResults.setVisible(false);
	}

	@FXML
	void actionResultsOneGroup(ActionEvent event) throws SQLException {
		view_result_details.setVisible(false);
		ResultSet rs;
		PreparedStatement pstm = null;
		Results selectedItem = tbl_groupResults.getSelectionModel().getSelectedItem();
		if (selectedItem != null) {
			lbl_result_table.setVisible(true);
			lbl_result_table.setText("Œrednie wyniki dla grupy " + selectedItem.getUser().getGrupa());
			String user = selectedItem.getUser().getGrupa();
			// Budowa zapytania do filtrowania u¿ytkowników:
			String sql = "SELECT grupa, jezyk, count(login) as liczba_testow, avg(liczba_pytan) as liczba_pytan, avg(wynik) as wynik FROM wyniki LEFT JOIN uzytkownicy USING (login) WHERE 1 = 1";
			sql += " AND grupa LIKE '" + selectedItem.getUser().getGrupa() + "' ";
			sql += "GROUP BY concat(grupa, jezyk)";
			System.out.println(sql);
			pstm = conn.prepareStatement(sql);
			rs = conn.createStatement().executeQuery(sql);
			resultsList.clear();
			while (rs.next()) {
				Integer wynik = Math.round(rs.getFloat(5) * 100);
				resultsList.add(new Results(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), wynik));
			}
			tbl_groupResults.setItems(null);
			tbl_groupResults.setItems(resultsList);
		}
	}

	@FXML
	void actionResultsOneUser(ActionEvent event) throws SQLException {
		ResultSet rs;
		Results selectedItem = tbl_userResults.getSelectionModel().getSelectedItem();
		String user = "";
		String imie = "";
		String nazwisko = "";
		boolean flag = false;
		lbl_result_table.setVisible(true);
		System.out.println(combo_result_user_credentials.getValue());
		if (combo_result_user_credentials.getValue() != null) {
			tbl_userResults.getSelectionModel().clearSelection();
			flag = true;
			System.out.println(
					"SELECT login, imie, nazwisko from uzytkownicy WHERE concat(nazwisko, ' ', imie) LIKE '" + combo_result_user_credentials.getValue() + "'");
			rs = conn.createStatement().executeQuery(
					"SELECT login, imie, nazwisko from uzytkownicy WHERE concat(nazwisko, ' ', imie) LIKE '" + combo_result_user_credentials.getValue() + "'");
			while (rs.next()) {
				user = rs.getString(1);
				imie = rs.getString(2);
				nazwisko = rs.getString(3);
			}
			view_result_details.setVisible(true);
			view_result_filter.setVisible(false);
			lbl_result_table.setText("Œrednie wyniki dla " + imie + " " + nazwisko);
			combo_result_user_credentials.setValue(null);
		} else if (selectedItem != null) {
			flag = true;
			view_result_details.setVisible(true);
			view_result_filter.setVisible(false);
			lbl_result_table.setText("Œrednie wyniki dla " + selectedItem.getUser().getImie() + " " + selectedItem.getUser().getNazwisko());
			user = selectedItem.getLogin();
		}
		if (flag) {
			// Liczba testów / wszystkie
			rs = conn.createStatement().executeQuery("select count(id_wynik) from wyniki where login = '" + user + "'");
			while (rs.next()) {
				if (rs.getInt(1) != 0) {
					user_all_liczba_testow.setText(String.valueOf(rs.getInt(1)));
				} else {
					user_all_liczba_testow.clear();
				}
			}
			// Œrednia liczba pytañ / wszystkie
			rs = conn.createStatement().executeQuery("select avg(liczba_pytan) from wyniki where login = '" + user + "'");
			while (rs.next()) {
				if (rs.getInt(1) != 0) {
					user_all_liczba_pytan.setText(String.valueOf(rs.getInt(1)));
				} else {
					user_all_liczba_testow.clear();
				}
			}
			// Œrednia % poprawnych / wszystkie
			rs = conn.createStatement().executeQuery("select avg(wynik) from wyniki where login = '" + user + "'");
			while (rs.next()) {
				if (rs.getDouble(1) != 0) {
					Long wynik = Math.round(rs.getDouble(1) * 100);
					user_all_dobrych.setText(String.valueOf(wynik) + "%");
				} else {
					user_all_dobrych.clear();
				}
			}
			// Liczba testïów / Python
			rs = conn.createStatement().executeQuery("select count(id_wynik) from wyniki where jezyk = 'Python' AND login = '" + user + "'");
			while (rs.next()) {
				if (rs.getInt(1) != 0) {
					user_Python_liczba_testow.setText(String.valueOf(rs.getInt(1)));
				} else {
					user_Python_liczba_testow.clear();
				}
			}
			// Œrednia liczba pytañ / Python
			rs = conn.createStatement().executeQuery("select avg(liczba_pytan) from wyniki where jezyk = 'Python' AND login = '" + user + "'");
			while (rs.next()) {
				if (rs.getInt(1) != 0) {
					user_Python_liczba_pytan.setText(String.valueOf(rs.getInt(1)));
				} else {
					user_Python_liczba_pytan.clear();
				}
			}
			// Œrednia % poprawnych / Python
			rs = conn.createStatement().executeQuery("select avg(wynik) from wyniki where jezyk = 'Python' AND login = '" + user + "'");
			while (rs.next()) {
				if (rs.getDouble(1) != 0) {
					Long wynik = Math.round(rs.getDouble(1) * 100);
					user_Python_dobrych.setText(String.valueOf(wynik) + "%");
				} else {
					user_Python_dobrych.clear();
				}
			}
			// Liczba testów / Java
			rs = conn.createStatement().executeQuery("select count(id_wynik) from wyniki where jezyk = 'Java' AND login = '" + user + "'");
			while (rs.next()) {
				if (rs.getInt(1) != 0) {
					user_Java_liczba_testow.setText(String.valueOf(rs.getInt(1)));
				} else {
					user_Java_liczba_testow.clear();
				}
			}
			// Œrednia liczba pytañ / Java
			rs = conn.createStatement().executeQuery("select avg(liczba_pytan) from wyniki where jezyk = 'Java' AND login = '" + user + "'");
			while (rs.next()) {
				if (rs.getInt(1) != 0) {
					user_Java_liczba_pytan.setText(String.valueOf(rs.getInt(1)));
				} else {
					user_Java_liczba_pytan.clear();
				}
			}
			// Œrednia % poprawnych / Java
			rs = conn.createStatement().executeQuery("select avg(wynik) from wyniki where jezyk = 'Java' AND login = '" + user + "'");
			while (rs.next()) {
				if (rs.getDouble(1) != 0) {
					Long wynik = Math.round(rs.getDouble(1) * 100);
					user_Java_dobrych.setText(String.valueOf(wynik) + "%");
				} else {
					user_Java_dobrych.clear();
				}
			}
			// Liczba testów / BD
			rs = conn.createStatement().executeQuery("select count(id_wynik) from wyniki where jezyk = 'BD' AND login = '" + user + "'");
			while (rs.next()) {
				if (rs.getInt(1) != 0) {
					user_BD_liczba_testow.setText(String.valueOf(rs.getInt(1)));
				} else {
					user_BD_liczba_testow.clear();
				}
			}
			// Œrednia liczba pytañ / BD
			rs = conn.createStatement().executeQuery("select avg(liczba_pytan) from wyniki where jezyk = 'BD' AND login = '" + user + "'");
			while (rs.next()) {
				if (rs.getInt(1) != 0) {
					user_BD_liczba_pytan.setText(String.valueOf(rs.getInt(1)));
				} else {
					user_BD_liczba_pytan.clear();
				}
			}
			// Œrednia % poprawnych / BD
			rs = conn.createStatement().executeQuery("select avg(wynik) from wyniki where jezyk = 'BD' AND login = '" + user + "'");
			while (rs.next()) {
				if (rs.getDouble(1) != 0) {
					Long wynik = Math.round(rs.getDouble(1) * 100);
					user_BD_dobrych.setText(String.valueOf(wynik) + "%");
				} else {
					user_BD_dobrych.clear();
				}
			}
			// Liczba testów / Git
			rs = conn.createStatement().executeQuery("select count(id_wynik) from wyniki where jezyk = 'Git' AND login = '" + user + "'");
			while (rs.next()) {
				if (rs.getInt(1) != 0) {
					user_Git_liczba_testow.setText(String.valueOf(rs.getInt(1)));
				} else {
					user_Git_liczba_testow.clear();
				}
			}
			// Œrednia liczba pytañ / Git
			rs = conn.createStatement().executeQuery("select avg(liczba_pytan) from wyniki where jezyk = 'Git' AND login = '" + user + "'");
			while (rs.next()) {
				if (rs.getInt(1) != 0) {
					user_Git_liczba_pytan.setText(String.valueOf(rs.getInt(1)));
				} else {
					user_Git_liczba_pytan.clear();
				}
			}
			// Œrednia % poprawnych / Git
			rs = conn.createStatement().executeQuery("select avg(wynik) from wyniki where jezyk = 'Git' AND login = '" + user + "'");
			while (rs.next()) {
				if (rs.getDouble(1) != 0) {
					Long wynik = Math.round(rs.getDouble(1) * 100);
					user_Git_dobrych.setText(String.valueOf(wynik) + "%");
				} else {
					user_Git_dobrych.clear();
				}
			}
			// Liczba testów / FE
			rs = conn.createStatement().executeQuery("select count(id_wynik) from wyniki where jezyk = 'FE' AND login = '" + user + "'");
			while (rs.next()) {
				if (rs.getInt(1) != 0) {
					user_FE_liczba_testow.setText(String.valueOf(rs.getInt(1)));
				} else {
					user_FE_liczba_testow.clear();
				}
			}
			// Œrednia liczba pytañ / FE
			rs = conn.createStatement().executeQuery("select avg(liczba_pytan) from wyniki where jezyk = 'FE' AND login = '" + user + "'");
			while (rs.next()) {
				if (rs.getInt(1) != 0) {
					user_FE_liczba_pytan.setText(String.valueOf(rs.getInt(1)));
				} else {
					user_FE_liczba_pytan.clear();
				}
			}
			// Œrednia % poprawnych / FE
			rs = conn.createStatement().executeQuery("select avg(wynik) from wyniki where jezyk = 'FE' AND login = '" + user + "'");
			while (rs.next()) {
				if (rs.getDouble(1) != 0) {
					Long wynik = Math.round(rs.getDouble(1) * 100);
					user_FE_dobrych.setText(String.valueOf(wynik) + "%");
				} else {
					user_FE_dobrych.clear();
				}
			}
			// Liczba testów / Spring
			rs = conn.createStatement().executeQuery("select count(id_wynik) from wyniki where jezyk = 'Spring' AND login = '" + user + "'");
			while (rs.next()) {
				if (rs.getInt(1) != 0) {
					user_Spring_liczba_testow.setText(String.valueOf(rs.getInt(1)));
				} else {
					user_Spring_liczba_testow.clear();
				}
			}
			// Œednia liczba pytañ / Spring
			rs = conn.createStatement().executeQuery("select avg(liczba_pytan) from wyniki where jezyk = 'Spring' AND login = '" + user + "'");
			while (rs.next()) {
				if (rs.getInt(1) != 0) {
					user_Spring_liczba_pytan.setText(String.valueOf(rs.getInt(1)));
				} else {
					user_Spring_liczba_pytan.clear();
				}
			}
			// Œrednia % poprawnych / Spring
			rs = conn.createStatement().executeQuery("select avg(wynik) from wyniki where jezyk = 'Spring' AND login = '" + user + "'");
			while (rs.next()) {
				if (rs.getDouble(1) != 0) {
					Long wynik = Math.round(rs.getDouble(1) * 100);
					user_Spring_dobrych.setText(String.valueOf(wynik) + "%");
				} else {
					user_Spring_dobrych.clear();
				}
			}
		} else {
			view_result_details.setVisible(false);
			view_result_filter.setVisible(false);
		}
	}

	@FXML
	void actionResultsUsers(ActionEvent event) {
		lbl_result_table.setVisible(false);
		btn_result_show_one_group.setDisable(true);
		btn_result_show_all_groups.setDisable(true);
		btn_result_filter_groups.setDisable(true);
		btn_result_show_one_user.setDisable(false);
		btn_result_show_all_users.setDisable(false);
		btn_result_filter_users.setDisable(false);
		combo_result_user_credentials.setDisable(false);
		tf_result_user_login_filter.setVisible(true);
		tf_result_user_imie_filter.setVisible(true);
		tf_result_user_nazwisko_filter.setVisible(true);
		tbl_userResults.setVisible(true);
		tbl_groupResults.setVisible(false);
	}

	@FXML
	void actionLogOut(ActionEvent event) {
		try {
			Stage stage = new Stage();
			Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/view/LoginView.fxml"));
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.setTitle("Test Reaktora");
			((Node) (event.getSource())).getScene().getWindow().hide();
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initialize() throws SQLException {
		System.out.println("initializing...");
		tbl_edit.setVisible(false);
		combo_rola.setItems(rola);
		combo_rolaFiltr.setItems(rola);
		combo_questionLang.setItems(lang);
		// Inicjalizacja combo_result_grupa_filtr:
		ResultSet rs = conn.createStatement().executeQuery("SELECT grupa FROM wyniki LEFT JOIN uzytkownicy USING (login) GROUP BY grupa");
		List<String> grupaTempList = new ArrayList<>();
		grupaTempList.add("");
		while (rs.next()) {
			String current = rs.getString(1);
			grupaTempList.add(current);
		}
		grupa = FXCollections.observableArrayList(grupaTempList);
		combo_result_grupa_filtr.setItems(grupa);
		// Inicjalizacja combo_result_jezyk_filtr:
		rs = conn.createStatement().executeQuery("SELECT jezyk FROM wyniki GROUP BY jezyk");
		List<String> jezykTempList = new ArrayList<>();
		jezykTempList.add("");
		while (rs.next()) {
			String current = rs.getString(1);
			jezykTempList.add(current);
		}
		jezyk = FXCollections.observableArrayList(jezykTempList);
		combo_result_jezyk_filtr.setItems(jezyk);
		// Inicjalizacja combo_result_user_credentials:
		rs = conn.createStatement()
				.executeQuery("SELECT CONCAT(nazwisko, ' ', imie) " + "AS credentials, login FROM uzytkownicy GROUP BY credentials ORDER BY credentials");
		List<String> credentialsTempList = new ArrayList<>();
		credentialsTempList.add("");
		while (rs.next()) {
			String current = rs.getString(1);
			credentialsTempList.add(current);
			loginCredentials.put(rs.getString(2), current);
		}
		nazwiskoImie = FXCollections.observableArrayList(credentialsTempList);
		combo_result_user_credentials.setItems(nazwiskoImie);
		// Inicjalizacja tabel
		tbl_userResults.setItems(null);
		tbl_userResults.setItems(resultsList);
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
		// Tabela pytaï¿½
		col_questionId.setCellValueFactory(new PropertyValueFactory<Questions, Integer>("id_pytania"));
		col_questionLang.setCellValueFactory(new PropertyValueFactory<Questions, String>("jezyk"));
		col_questionLang.setCellFactory(TextFieldTableCell.forTableColumn());
		col_questionLang.setOnEditCommit(new EventHandler<CellEditEvent<Questions, String>>() {
			@Override
			public void handle(CellEditEvent<Questions, String> t) {
				((Questions) t.getTableView().getItems().get(t.getTablePosition().getRow())).setJezyk(t.getNewValue());
			}
		});
		col_question.setCellValueFactory(new PropertyValueFactory<Questions, String>("tresc"));
		col_question.setCellFactory(TextFieldTableCell.forTableColumn());
		col_question.setOnEditCommit(new EventHandler<CellEditEvent<Questions, String>>() {
			@Override
			public void handle(CellEditEvent<Questions, String> t) {
				((Questions) t.getTableView().getItems().get(t.getTablePosition().getRow())).setTresc(t.getNewValue());
			}
		});
		col_answear1.setCellValueFactory(new PropertyValueFactory<Questions, String>("odp1"));
		col_answear1.setCellFactory(TextFieldTableCell.forTableColumn());
		col_answear1.setOnEditCommit(new EventHandler<CellEditEvent<Questions, String>>() {
			@Override
			public void handle(CellEditEvent<Questions, String> t) {
				((Questions) t.getTableView().getItems().get(t.getTablePosition().getRow())).setOdp1(t.getNewValue());
			}
		});
		col_answear2.setCellValueFactory(new PropertyValueFactory<Questions, String>("odp2"));
		col_answear2.setCellFactory(TextFieldTableCell.forTableColumn());
		col_answear2.setOnEditCommit(new EventHandler<CellEditEvent<Questions, String>>() {
			@Override
			public void handle(CellEditEvent<Questions, String> t) {
				((Questions) t.getTableView().getItems().get(t.getTablePosition().getRow())).setOdp2(t.getNewValue());
			}
		});
		col_answear3.setCellValueFactory(new PropertyValueFactory<Questions, String>("odp3"));
		col_answear3.setCellFactory(TextFieldTableCell.forTableColumn());
		col_answear3.setOnEditCommit(new EventHandler<CellEditEvent<Questions, String>>() {
			@Override
			public void handle(CellEditEvent<Questions, String> t) {
				((Questions) t.getTableView().getItems().get(t.getTablePosition().getRow())).setOdp3(t.getNewValue());
			}
		});
		col_answear4.setCellValueFactory(new PropertyValueFactory<Questions, String>("odp4"));
		col_answear4.setCellFactory(TextFieldTableCell.forTableColumn());
		col_answear4.setOnEditCommit(new EventHandler<CellEditEvent<Questions, String>>() {
			@Override
			public void handle(CellEditEvent<Questions, String> t) {
				((Questions) t.getTableView().getItems().get(t.getTablePosition().getRow())).setOdp4(t.getNewValue());
			}
		});
		col_correctAnswear.setCellValueFactory(new PropertyValueFactory<Questions, String>("prawidlowa_odp"));
		col_correctAnswear.setCellFactory(TextFieldTableCell.forTableColumn());
		col_correctAnswear.setOnEditCommit(new EventHandler<CellEditEvent<Questions, String>>() {
			@Override
			public void handle(CellEditEvent<Questions, String> t) {
				((Questions) t.getTableView().getItems().get(t.getTablePosition().getRow())).setPrawidlowa_odp(t.getNewValue());
			}
		});
		col_questionTimestamp.setCellValueFactory(new PropertyValueFactory<Questions, String>("czas"));
		// Tabela wynikÃ³w uÅ¼ytkownikÃ³w:
		col_resultLogin.setCellValueFactory(new PropertyValueFactory<Results, String>("login"));
		col_resultImie.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUser().getImie()));
		col_resultNazwisko.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUser().getNazwisko()));
		col_resultGrupa.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUser().getGrupa()));
		col_resultIDWynik.setCellValueFactory(new PropertyValueFactory<Results, Integer>("id_wynik"));
		col_resultJezyk.setCellValueFactory(new PropertyValueFactory<Results, String>("jezyk"));
		col_resultLiczbaPytan.setCellValueFactory(new PropertyValueFactory<Results, Integer>("liczba_pytan"));
		col_resultWynik.setCellValueFactory(new PropertyValueFactory<Results, Float>("wynik"));
		col_resultTime.setCellValueFactory(new PropertyValueFactory<Results, String>("czas"));
		// Tabela wynikÃ³w grup:
		col_resultgroupGrupa.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUser().getGrupa()));
		col_resultgroupJezyk.setCellValueFactory(new PropertyValueFactory<Results, String>("jezyk"));
		col_resultgroupLiczbaTestow.setCellValueFactory(new PropertyValueFactory<Results, Integer>("liczba_testow"));
		col_resultgroupLiczbaPytan.setCellValueFactory(new PropertyValueFactory<Results, Integer>("liczba_pytan"));
		col_resultgroupSredniWynik.setCellValueFactory(new PropertyValueFactory<Results, Integer>("wynik"));
		System.out.println("initializing completed!");
	}

}

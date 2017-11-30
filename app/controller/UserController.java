package app.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import app.database.DBConnector;
import app.model.Questions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.Util;

public class UserController {

	@FXML
	private AnchorPane user_view;

	@FXML
	private CheckBox cb_bazy;

	@FXML
	private CheckBox cb_git;

	@FXML
	private CheckBox cb_python;

	@FXML
	private CheckBox cb_front;

	@FXML
	private CheckBox cb_java;

	@FXML
	private CheckBox cb_spring;

	@FXML
	private Spinner<Integer> spin_liczba;
	SpinnerValueFactory<Integer> valueFactory;

	@FXML
	private RadioButton rb_odp1;

	@FXML
	private RadioButton rb_odp2;

	@FXML
	private RadioButton rb_odp3;

	@FXML
	private RadioButton rb_odp4;

	@FXML
	private RadioButton rb_false;

	@FXML
	private Label l_nrpytania;

	@FXML
	private Label l_tresc;

	@FXML
	private Label l_odp1;

	@FXML
	private Label l_odp2;

	@FXML
	private Label l_odp3;

	@FXML
	private Label l_odp4;

	@FXML
	private Button btn_next;

	@FXML
	private Button btn_exit;

	@FXML
	private Button btn_start;

	// Spoza scheletona:
	Connection db;
	ArrayDeque<Questions> questionsTest = new ArrayDeque<>();
	int licznik = 1;
	int liczba_pytan;
	int licznik_prawidlowych;
	Questions initial, regular;
	StringBuilder jezyki_wynikow;

	// nawiazanie polaczenia
	Connection conn = DBConnector.getConnection();

	@FXML
	void startTest(ActionEvent event) throws SQLException {

		cb_bazy.setDisable(true);
		cb_git.setDisable(true);
		cb_python.setDisable(true);
		cb_front.setDisable(true);
		cb_java.setDisable(true);
		cb_spring.setDisable(true);
		spin_liczba.setDisable(true);

		btn_start.setVisible(false);

		l_tresc.setVisible(true);
		l_nrpytania.setVisible(true);
		rb_odp1.setVisible(true);
		rb_odp2.setVisible(true);
		rb_odp3.setVisible(true);
		rb_odp4.setVisible(true);
		l_odp1.setVisible(true);
		l_odp2.setVisible(true);
		l_odp3.setVisible(true);
		l_odp4.setVisible(true);
		btn_next.setVisible(true);

		// ustawienie tresci pytan i odpowiedzi, ktore maja sie wyswietlac na
		// ekranie
		ArrayList<String> tablicaList = new ArrayList<String>();
		if (cb_bazy.isSelected()) {
			tablicaList.add("BD");
		}
		if (cb_git.isSelected()) {
			tablicaList.add("Git");
		}
		if (cb_python.isSelected()) {
			tablicaList.add("Python");
		}
		if (cb_front.isSelected()) {
			tablicaList.add("FE");
		}
		if (cb_java.isSelected()) {
			tablicaList.add("Java");
		}
		if (cb_spring.isSelected()) {
			tablicaList.add("Spring");
		}
		if (tablicaList.size() == 0) {
			Alert view_error = new Alert(AlertType.ERROR);
			view_error.setContentText("Nie wybra³eœ ¿adnego jêzyka");
			view_error.setHeaderText("B³¹d!");
			view_error.setTitle("Okno b³êdu");
			view_error.showAndWait();
		} else {
			// pobranie liczby elementow tablicy utworzonej z wybranych jezykow
			// System.out.println(tablicaList.size());
			int liczba_jezykow = tablicaList.size();
			// stworzenie stringa z wybranych przez uzytkownika jezykow
			String string_jezykow = "";
			jezyki_wynikow = new StringBuilder();
			if (liczba_jezykow == 1) {
				string_jezykow = "SELECT * FROM pytania WHERE jezyk LIKE '" + tablicaList.get(0) + "'";
				jezyki_wynikow.append(tablicaList.get(0));
			} else {
				for (int k = 0; k < liczba_jezykow; k++) {
					if (k == 0) {
						string_jezykow = "SELECT * FROM pytania WHERE jezyk LIKE '" + tablicaList.get(0) + "'";
						jezyki_wynikow.append(tablicaList.get(0));
					} else {
						string_jezykow += " OR jezyk LIKE '" + tablicaList.get(k) + "'";
						jezyki_wynikow.append(", " + tablicaList.get(k));
					}
				}
			}
			System.out.println(string_jezykow);
			// pobranie liczby pytan, ktore maja byc losowane
			// System.out.println(spin_liczba.getValue());
			liczba_pytan = (spin_liczba.getValue());
			System.out.println("liczba pytañ: " + liczba_pytan);
			// losowanie pytan
			LinkedList<Questions> allQuestionsList = new LinkedList<>();
			ResultSet rs = conn.createStatement().executeQuery(string_jezykow);
			while (rs.next()) {
				allQuestionsList.add(new Questions(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
						rs.getString(7), rs.getString(8), rs.getString(9)));
			}
			int iloscWszystkichPytan = allQuestionsList.size();
			System.out.println("wszystkich " + iloscWszystkichPytan);
			// Losowanie pytañ z listy do testu:
			Random random = new Random();
			int temp;
			for (int i = 0; i < liczba_pytan; i++) {
				temp = random.nextInt(iloscWszystkichPytan);
				questionsTest.add(allQuestionsList.get(temp));
				iloscWszystkichPytan = allQuestionsList.size();
			}
			initial = questionsTest.poll();

			setQuestion(initial);
		}

	}

	private void setQuestion(Questions question) {
		rb_false.setSelected(true);
		l_nrpytania.setText("Pytanie nr " + licznik + " z " + liczba_pytan + ", jêzyk: " + question.getJezyk());
		l_tresc.setText(question.getTresc());
		l_odp1.setText(question.getOdp1());
		l_odp2.setText(question.getOdp2());
		l_odp3.setText(question.getOdp3());
		l_odp4.setText(question.getOdp4());
	}

	@FXML
	void nextQuest(ActionEvent event) throws SQLException {
		boolean flag = false;
		PreparedStatement pstm = null;
		System.out.println("rozmiar: " + questionsTest.size());
		if (questionsTest.size() == 1) {
			btn_next.setText("ZatwierdŸ i zakoñcz test");
		}
		if (questionsTest.size() != 0) {
			String prawidlowa_odp = "";
			if (rb_odp1.isSelected()) {
				prawidlowa_odp = l_odp1.getText();
				flag = true;
			} else if (rb_odp2.isSelected()) {
				prawidlowa_odp = l_odp2.getText();
				flag = true;
			} else if (rb_odp3.isSelected()) {
				prawidlowa_odp = l_odp3.getText();
				flag = true;
			} else if (rb_odp4.isSelected()) {
				prawidlowa_odp = l_odp4.getText();
				flag = true;
			}
			if (flag) {
				// Sprawdzenie prawid³owej odpowiedzi:
				// Statement stmt = db.createStatement();
				String sql = "SELECT prawidlowa_odp FROM pytania WHERE tresc LIKE \"" + Util.sqlconvert(l_tresc.getText()) + "\" AND odp1 LIKE \""
						+ Util.sqlconvert(l_odp1.getText()) + "\" AND odp2 LIKE \"" + Util.sqlconvert(l_odp2.getText()) + "\" AND odp3 LIKE \""
						+ Util.sqlconvert(l_odp3.getText()) + "\" AND odp4 LIKE \"" + Util.sqlconvert(l_odp4.getText()) + "\" AND prawidlowa_odp LIKE \""
						+ prawidlowa_odp + "\"";
				System.out.println(sql);
				pstm = conn.prepareStatement(sql);
				ResultSet rs = conn.createStatement().executeQuery(sql);
				// ResultSet rs = stmt.executeQuery("SELECT prawidlowa_odp FROM
				// pytania WHERE tresc LIKE '" + l_tresc.getText() + "' AND odp1
				// LIKE '"
				// + l_odp1.getText() + "' AND odp2 LIKE '" + l_odp2.getText() +
				// "' AND odp3 LIKE '" + l_odp3.getText() + "' AND odp4 LIKE '"
				// + l_odp4.getText() + "' AND prawidlowa_odp LIKE '" +
				// prawidlowa_odp + "';");
				if (rs.next()) {
					if (rs.getString("prawidlowa_odp") != null) {
						System.out.println(rs.getString("prawidlowa_odp"));
						licznik_prawidlowych++;
					}
				}
				// Pobranie kolejnego pytania z kolejki:
				regular = questionsTest.poll();
				// Za³adowanie nowego pytania:
				licznik++;
				setQuestion(regular);
			} else {
				Alert view_error = new Alert(AlertType.ERROR);
				view_error.setContentText("Nie wybra³eœ ¿adnej odpowiedzi");
				view_error.setHeaderText("B³¹d!");
				view_error.setTitle("Okno b³êdu");
				view_error.showAndWait();
			}

		} else {
			// Ostatnie pytanie
			String prawidlowa_odp = "";
			flag = false;
			{
				if (rb_odp1.isSelected()) {
					prawidlowa_odp = l_odp1.getText();
					flag = true;
				} else if (rb_odp2.isSelected()) {
					prawidlowa_odp = l_odp2.getText();
					flag = true;
				} else if (rb_odp3.isSelected()) {
					prawidlowa_odp = l_odp3.getText();
					flag = true;
				} else if (rb_odp4.isSelected()) {
					prawidlowa_odp = l_odp4.getText();
					flag = true;
				}
				if (flag) {
					// Sprawdzenie prawid³owej odpowiedzi:
					// Statement stmt = db.createStatement();
					String sql = "SELECT prawidlowa_odp FROM pytania WHERE tresc LIKE '" + Util.sqlconvert(l_tresc.getText()) + "' AND odp1 LIKE '"
							+ Util.sqlconvert(l_odp1.getText()) + "' AND odp2 LIKE '" + Util.sqlconvert(l_odp2.getText()) + "' AND odp3 LIKE '"
							+ Util.sqlconvert(l_odp3.getText()) + "' AND odp4 LIKE '" + Util.sqlconvert(l_odp4.getText()) + "' AND prawidlowa_odp LIKE '"
							+ Util.sqlconvert(prawidlowa_odp) + "'";
					pstm = conn.prepareStatement(sql);
					ResultSet rs = pstm.executeQuery(sql);
					System.out.println(sql);
					if (rs.next()) {
						if (rs.getString("prawidlowa_odp") != null) {
							System.out.println(rs.getString("prawidlowa_odp"));
							licznik_prawidlowych++;
						}
					}
					// Zapisanie wyniku do bazy rezultatów:
					Float wynik = (float) licznik_prawidlowych / liczba_pytan;
					sql = "INSERT INTO wyniki (login, jezyk, liczba_pytan, wynik) VALUES ('" + LoginController.login + "', '" + jezyki_wynikow + "', "
							+ liczba_pytan + ", " + wynik + ")";
					System.out.println(sql);
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.executeUpdate();
					// Informacja o wyniku testu:
					{
						Integer procent = Integer.valueOf(Math.round((float) licznik_prawidlowych / liczba_pytan * 100));
						Alert view_result = new Alert(AlertType.INFORMATION);
						view_result.setContentText("Udzieli³eœ ³¹cznie " + licznik_prawidlowych + " odpowiedzi z " + liczba_pytan + " pytañ ³¹cznie" + ", tj. "
								+ procent + "% wszystkich odpowiedzi by³o poprawnych");
						view_result.setHeaderText("Koniec testu");
						view_result.setTitle("Wynik testu");
						view_result.showAndWait();
					}
					licznik = 1;
					// Widocznoœæ pól - reset do stanu wyjœciowego:
					l_tresc.setVisible(false);
					l_nrpytania.setVisible(false);
					rb_odp1.setVisible(false);
					rb_odp2.setVisible(false);
					rb_odp3.setVisible(false);
					rb_odp4.setVisible(false);
					l_odp1.setVisible(false);
					l_odp2.setVisible(false);
					l_odp3.setVisible(false);
					l_odp4.setVisible(false);
					btn_next.setVisible(false);
					btn_next.setVisible(false);
					btn_next.setText("Nastêpne pytanie");
					btn_start.setVisible(true);
					cb_bazy.setDisable(false);
					cb_git.setDisable(false);
					cb_python.setDisable(false);
					cb_front.setDisable(false);
					cb_java.setDisable(false);
					cb_spring.setDisable(false);
					spin_liczba.setDisable(false);
				} else {
					Alert view_error = new Alert(AlertType.ERROR);
					view_error.setContentText("Nie wybra³eœ ¿adnej odpowiedzi");
					view_error.setHeaderText("B³¹d!");
					view_error.setTitle("Okno b³êdu");
					view_error.showAndWait();
				}
			}

		}
	}

	@FXML
	void exitTest(MouseEvent event) {
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

	public void initialize() {
		valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(5, 20, 5);
		spin_liczba.setValueFactory(valueFactory);
		l_tresc.setVisible(false);
		l_nrpytania.setVisible(false);
		rb_odp1.setVisible(false);
		rb_odp2.setVisible(false);
		rb_odp3.setVisible(false);
		rb_odp4.setVisible(false);
		l_odp1.setVisible(false);
		l_odp2.setVisible(false);
		l_odp3.setVisible(false);
		l_odp4.setVisible(false);
		btn_next.setVisible(false);
	}
}
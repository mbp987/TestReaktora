package app.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import app.database.DBConnector;
import app.model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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

    
    //nawiazanie polaczenia
    Connection conn = DBConnector.getConnection();
	


    @FXML
    void startTest(MouseEvent event) throws SQLException {
    		
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
    	
		//ustawienie tresci pytan i odpowiedzi, ktore maja sie wyswietlac na ekranie
		
		String [] tablica = new String [6];
		
		ArrayList tablicaList = new ArrayList();
		
		int i = 0;
		
		if (cb_bazy.isSelected()) {
			tablica [i] = "Bazy";
			i += 1;
			tablicaList.add("Bazy");
		}
		if (cb_git.isSelected()) {
			tablica [i] = "Git";
			i += 1;
			tablicaList.add("Git");
		}
		if (cb_python.isSelected()) {
			tablica [i] = "Python";
			i += 1;
			tablicaList.add("Python");
		}
		if (cb_front.isSelected()) {
			tablica [i] = "Front";
			i += 1;
			tablicaList.add("Front");
		}
		if (cb_java.isSelected()) {
			tablica [i] = "Java";
			i += 1;
			tablicaList.add("Java");
		}
		if (cb_spring.isSelected()) {
			tablica [i] = "Spring";
			i += 1;
			tablicaList.add("Spring");
		}
		
		//pobranie liczby elementow tablicy utworzonej z wybranych jezykow
		//System.out.println(tablicaList.size());
		int liczba_jezykow = tablicaList.size();
		
		
		//stworzenie stringa z wybranych przez uzytkownika jezykow
		
		
		
		
		
		
		
			
		
		
		Random random = new Random();

		//pobranie liczby pytan, ktore maja byc losowane
		//System.out.println(spin_liczba.getValue());
		int liczba_pytan = (spin_liczba.getValue());
		
		
		
		
		
		ObservableList<String> jezyk = FXCollections.observableArrayList("Bazy", "Git", "Python", "Front", "Java", "Spring");
		Connection conn = DBConnector.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM pytania;");
		
		System.out.println(rs);
		
	    
	 //   int select = random.nextInt(tablicaList.length); 

	         // prints out the value at the randomly selected index
	    //System.out.println(tablica[select]);

			
		
		

		//SELECT * FROM pytania WHERE jezyk IN ('Java','Python');
			
		
		
		
		
		
//		l_odp1.setText("SELECT odp1 FROM pytania WHERE jezyk='python' AND id_pytania='1'");
//		l_odp2.setText("abc");
//		l_odp3.setText("abc");
//		l_odp4.setText("abc");
	
		
		
    }

    



	public void initialize() {
    	 	valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 0);
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
    
    
    @FXML
    void nextQuest(MouseEvent event) {

    }
    
    
    
    @FXML
    void exitTest(MouseEvent event) {
    		Stage stage = (Stage) btn_exit.getScene().getWindow();
    		stage.close();
    }

   
    
    
}







    
    


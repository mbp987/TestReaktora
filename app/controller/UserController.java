package app.controller;

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

    
    
    
    

    @FXML
    void startTest(MouseEvent event) {
    		
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







    
    


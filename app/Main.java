package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// Dodanie zasobu widoku aplikacji
			Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/view/LoginView.fxml"));
			// Osadzenie zasobu na scenie
			Scene scene = new Scene(parent);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			// Osadzenie sceny na stage'u
			primaryStage.setScene(scene);
			// Dodanie tytu³u
			primaryStage.setTitle("Reaktor PWN - Tester");
			// Ustawienie widocznoœci aplikacji
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}

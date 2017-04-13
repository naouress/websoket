package webSocket;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class discussionMain extends Application {
	@Override
	public void start(Stage primaryStage) {

		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("AdminsDiscussions.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

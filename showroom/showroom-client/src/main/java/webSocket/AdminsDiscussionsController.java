package webSocket;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

import artRoom.entities.AdminMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.input.ScrollEvent;

public class AdminsDiscussionsController implements Initializable {
	@FXML
	private VBox vbox;
	@FXML
	private TextArea msg;

	private static VBox messages;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		AdminDiscussionsClient.connect();
		messages = vbox;
		
	}

	// Event Listener on ScrollPane.onScrollFinished
	@FXML
	public void scrollFinished(ScrollEvent event) {
		System.out.println("scollllllll end ");
	}

	// Event Listener on Button.onAction
	@FXML
	public void send(ActionEvent event) {
		AdminDiscussionsClient.send(msg.getText());
		msg.setText("");
	}

	public static void addMessage(AdminMessage msg) {
		
		try {
			msgController.adm=msg;
			messages.getChildren().add((AnchorPane) FXMLLoader.load(AdminsDiscussionsController.class.getResource("msg.fxml")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

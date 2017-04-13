package webSocket;

import java.net.URL;
import java.util.ResourceBundle;

import artRoom.entities.AdminMessage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;

public class msgController implements Initializable{
	@FXML
	private Label user;
	@FXML
	private Label txt;

	@FXML
	private ToolBar bar;
	public static AdminMessage adm;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		txt.setTooltip(new Tooltip(adm.getMail()+" :"+adm.getDate()));
		txt.setWrapText(true);
		txt.setText(adm.getText());
		user.setText(adm.getMail().charAt(0)+"");
		if(adm.getMail().equals(AdminDiscussionsClient.Mail)){
			bar.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		}
		
		
	}

}

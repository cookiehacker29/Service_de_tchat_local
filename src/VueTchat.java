import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VueTchat extends Application{
	
	private TextField message;
	private ScrollPane lsMsg;
	private BorderPane bp;
	private Thread t;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public Scene scene() throws IOException {
		//PAN
		bp = new BorderPane();
		VBox zoneEnvoie = new VBox();
		lsMsg = new ScrollPane();
		
		//Envoie
		Label lmsg = new Label("Message : ");
		message = new TextField();
		Button valider = new Button("Envoyer !");
		valider.setUserData("send");
		ActionButton act = new ActionButton(this);
		valider.setOnAction(act);
		
		zoneEnvoie.getChildren().add(lmsg);
		zoneEnvoie.getChildren().add(message);
		zoneEnvoie.getChildren().add(valider);
		zoneEnvoie.setAlignment(Pos.CENTER);
		zoneEnvoie.setSpacing(5);
		zoneEnvoie.setPadding(new Insets(5,5,5,5));
		
		bp.setLeft(zoneEnvoie);
		
		return new Scene(bp,500,500);
	}

	@Override
	public void start(Stage a) throws Exception {
		t = new Thread(new ListeMessage(this));
		t.start();
		a.setTitle("Tchat local");
		a.setScene(scene());
		a.setResizable(false);
		a.show();
		a.setOnCloseRequest(new ActionWindows());
	}
	
	public Thread getT(){
		return t;
	}
	
	public TextField getMessage() {
		return message;
	}

	public BorderPane getBp() {
		return bp;
	}

	public void setBp(BorderPane bp) {
		this.bp = bp;
	}

	public ScrollPane getLsMsg() {
		return lsMsg;
	}
	
	

}

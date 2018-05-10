import java.io.IOException;
import java.net.InetAddress;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ActionButton implements EventHandler<ActionEvent>{
	
	private VueTchat app;
	
	ActionButton(VueTchat app){
		this.app = app;
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		Button b = (Button) arg0.getSource();
		if(b.getUserData() == "send") {
			try {
				if(!app.getMessage().getText().isEmpty()) {
					Envoie.broadcast(app.getMessage().getText(), InetAddress.getByName("255.255.255.255"));
					app.getMessage().clear();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}

}

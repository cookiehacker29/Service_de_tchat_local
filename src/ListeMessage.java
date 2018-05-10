import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class ListeMessage implements Runnable{
	private List<String> lsMsg;
	static int port = 5555;
	private static int taille = 1024;
	
	private VueTchat app;
	private DatagramSocket socket;
	
	public ListeMessage(VueTchat app) {
		this.app = app;
		lsMsg = new ArrayList<String>();
	}
	
	@Override
	public void run() {
		try {
			socket = new DatagramSocket(port);
			while(true) {
				VBox res = new VBox();
				byte buffer[] = new byte[taille];
				DatagramPacket data = new DatagramPacket(buffer,buffer.length); 
				socket.receive(data);
				
				
				if(lsMsg.size()>=1 && !lsMsg.get(lsMsg.size()-1).equals(data.getAddress().getHostName()+" : "+new String(data.getData()))){
					System.out.println(data.getAddress()+" : "+new String(data.getData()));
					lsMsg.add(data.getAddress().getHostName()+" : "+new String(data.getData()));
					System.out.println(lsMsg.size());
				}
				if(lsMsg.size()<1) {
					System.out.println(data.getAddress()+" : "+new String(data.getData()));
					lsMsg.add(data.getAddress().getHostName()+" : "+new String(data.getData()));
					System.out.println(lsMsg.size());
				}
				
				
				for(String m : lsMsg) {
					res.getChildren().add(new Label(m));
				}
				Platform.runLater(() -> app.getLsMsg().setContent(res));
				Platform.runLater(() -> app.getBp().setCenter(app.getLsMsg()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}


	}
}

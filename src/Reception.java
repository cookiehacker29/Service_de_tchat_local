import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Reception {
	static int port = 5555;
	private static int taille = 1024;
	private static String valR = "";
	
	public Reception() throws IOException {
		while(true) {
		DatagramSocket socket = new DatagramSocket(port); 
		byte buffer[] = new byte[taille];
		DatagramPacket data = new DatagramPacket(buffer,buffer.length); 
		socket.receive(data); 
		System.out.println(data.getAddress()+" : "+new String(data.getData()));
		valR = data.getAddress()+" : "+new String(data.getData());
		}
	}
	
	public String getValR() {
		return this.valR;
	}
}

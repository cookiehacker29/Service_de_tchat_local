import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Envoie{
	private static DatagramSocket socket = null;

//	    public static void main(String[] args) throws IOException {
//	    	while(true) {
//	    		System.out.println("Message> ");
//	    		Scanner message = new Scanner(System.in);
//	    		broadcast(message.nextLine(), InetAddress.getByName("255.255.255.255"));
//	    	}
//	        
//	    }
	
	public static void broadcast(
			String broadcastMessage, InetAddress address) throws IOException {
		socket = new DatagramSocket();
		socket.setBroadcast(true);

		byte[] buffer = broadcastMessage.getBytes();

		DatagramPacket packet 
		= new DatagramPacket(buffer, buffer.length, address, 5555);
		socket.send(packet);
		socket.close();
	}
}

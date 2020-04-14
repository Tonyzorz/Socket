package journalDevSocket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static ServerSocket server;
	
	public static int port = 4700;
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		server = new ServerSocket(port);
		Socket socket = server.accept();
		DataInputStream ois = new DataInputStream(socket.getInputStream());
		DataOutputStream oos = new DataOutputStream(socket.getOutputStream());

		while(true) {
			System.out.println("Waiting for client request");
			
			
			String message = (String) ois.readUTF();
			System.out.println("Received message = " + message);
			
			oos.writeUTF("Hi client " + message);
			
			
			
			if(message.equalsIgnoreCase("exit")) {
				ois.close();
				oos.close();
				socket.close();
				break;
			}
			
		}
		System.out.println("Shutting down Socket server!");
		server.close();
	}
}

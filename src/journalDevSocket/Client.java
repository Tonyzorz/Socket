package journalDevSocket;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
/*		InetAddress host = InetAddress.getLocalHost();
*/		Socket socket = new Socket("localhost", 4700);
		System.out.println("Connected!");
		DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
		DataInputStream ois = new DataInputStream(socket.getInputStream());
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			System.out.println("Sending request to Socket Server");
			String message = br.readLine();
			
			if(message.equalsIgnoreCase("exit")) {
				System.out.println("Shutting down client!");
				ois.close();
				oos.close();
				socket.close();
				break;
			}
			oos.writeUTF(message);
			
			String serverMessage = (String) ois.readUTF();
			System.out.println("Server : " + serverMessage);
			
		}
		
		
	}
}

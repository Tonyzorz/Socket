package simpleSocket;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) {
		try {
			System.out.println("Establishing connection to server...");
			Socket client = new Socket("127.0.0.1", 4848);
			System.out.println("Connected!");
			DataInputStream din = new DataInputStream(client.getInputStream());
			DataOutputStream dout = new DataOutputStream(client.getOutputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			String str1 = "", str2 = "";
			
			while(!str1.trim().equals("stop")) {
				System.out.print("Client : ");
				str1 = br.readLine();
				dout.writeUTF(str2);
				dout.flush();
				System.out.println();
				
				System.out.println("Waiting input from server.....");
				str2 = din.readUTF();
				System.out.println("From Server : " + str2);
				
			}
			
			dout.close();
			client.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

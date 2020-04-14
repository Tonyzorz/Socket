package simpleSocket;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(4848);
			System.out.println("Server started...");
			Socket server = ss.accept();
			DataInputStream din = new DataInputStream(server.getInputStream());
			DataOutputStream dout = new DataOutputStream(server.getOutputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			String str1 = "", str2 = "";
			
			while(!str1.trim().equals("stop")) {
				System.out.println("Waiting from client");
				str1 = din.readUTF();
				System.out.println("From Client : " + str1);
				
				System.out.print("Server : ");
				str2 = br.readLine();
				dout.writeUTF(str2);
				System.out.println();
				dout.flush();
			}
			
			din.close();
			server.close();
			
			//server will also close
			ss.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

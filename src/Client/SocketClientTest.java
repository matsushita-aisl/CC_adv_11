package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import Server.SocketServerTest;



public class SocketClientTest{

	public static final String HOST = "localhost";
	
	
	public static void main(String[] args){
		try(Socket socket = new Socket(HOST, SocketServerTest.PORT);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(socket.getOutputStream())){
			
			System.out.println("ClientがServerに接続しました");
			
			out.print((int)Math.random()*10);
			out.flush();
			System.out.println("Serverにデータを送信しました");
			
			String msg = in.readLine();
			System.out.println("Serverからデータを受信しました\n"	 + msg);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}

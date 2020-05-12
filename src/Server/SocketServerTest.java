package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;



public class SocketServerTest{
	public static final int PORT = 9999;
	
	public static void main(String[] args){
		try(ServerSocket server = new ServerSocket(PORT)){
			InetAddress address = InetAddress.getLocalHost();

			//while(true){
				Socket socket = server.accept(); 
				
				InetAddress client = socket.getInetAddress();
				System.out.println("ポート番号：[" + PORT + "]でServerが起動しました");
				
				try(BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())))){
					String msg = in.readLine();
					System.out.println("Clientからデータを受信しました");
					
					msg = ("受け取った値は" + msg + "で，"
							+ "2乗した値は" + Math.pow(Integer.parseInt(msg), 2)
							+ "です");
					
					out.println(msg);
					System.out.println("Serverからデータを送信しました");
					
				}
			//}
		}catch(IOException e){
			e.printStackTrace();
		}
	}	
}

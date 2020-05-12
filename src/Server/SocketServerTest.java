/********************
 * 同一パッケージ内にmain()は一つしか定義できないため
 * 今回はサーバとクライアントでパッケージを分けてあります
 *******************/

package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;



public class SocketServerTest{
	
	public static final int PORT = 9999;
	
	public static void main(String[] args){
		try(ServerSocket server = new ServerSocket(PORT)){

			Socket socket = server.accept(); 
			System.out.println("ポート番号：[" + PORT + "]でServerが起動しました");
			
			try(BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())))){

				String msg = in.readLine();
				System.out.println("Clientからデータを受信しました");
				//送信メッセージ作成．Mathライブラリで2乗
				msg = ("受け取った値は" + msg + "で，"
						+ "2乗した値は" + (int)Math.pow(Integer.parseInt(msg), 2)
						+ "です");
				
				out.println(msg);
				out.flush();	//なくてもいけるがあったほうが吉
				System.out.println("Serverからデータを送信しました");
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}	
}

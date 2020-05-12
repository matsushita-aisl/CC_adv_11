/********************
 * 同一パッケージ内にmain()は一つしか定義できないため
 * 今回はサーバとクライアントでパッケージを分けてあります
 *******************/

package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
//パッケージ名.クラス名でサーバクラスの情報を読む
import Server.SocketServerTest;



public class SocketClientTest{

	public static final String HOST = "localhost";
	
	
	public static void main(String[] args){
		//接続
		try(Socket socket = new Socket(HOST, SocketServerTest.PORT);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(socket.getOutputStream())){
			
			System.out.println("ClientがServerに接続しました");
			
			int num = (int)(Math.random()*10);
			out.println(num);	//最後に改行がないとサーバで止まる
			out.flush();	//これがないとサーバで止まる
			System.out.println("Serverにデータを送信しました" + num);
			
			String msg = in.readLine();
			System.out.println("Serverからデータを受信しました\n"	 + msg);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}

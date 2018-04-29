import java.io.*;
import java.net.*;

public class Cliente1{
	public static void main(String[] args){
		Socket yo = null;
		try{
			//yo = new Socket("131.178.XXX.XXX","5000");
			yo = new Socket("127.0.0.1",5000);
		}catch(UnknownHostException e){
			System.out.println(e.getMessage());
			System.exit(1);
		}catch(IOException e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
		System.out.println("Ya se conecto al servidor");
		try{
			yo.close();
		}catch(IOException e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
}
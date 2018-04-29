import java.io.*;
import java.net.*;

public class Servidor1{
	public static void main(String[] args){
		ServerSocket yo = null;
		Socket cliente = null;
		try{
			yo = new ServerSocket(5000);
		}catch(IOException e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
		System.out.println("Socket escuchando en puerto 5000");
		try{
			cliente = yo.accept();
		}catch(IOException e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
		System.out.println("Ua se conecto al cliente");
		try{
			cliente.close();
			yo.close();
		}catch(IOException e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
}
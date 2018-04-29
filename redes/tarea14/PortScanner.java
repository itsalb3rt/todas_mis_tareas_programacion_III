import java.net.*;
import java.io.IOException;

//ejemplo al ejecutar java PortScanner wwww.midominio.com revisa todos los puertos escuchando o abiertos

public class PortScanner{

	public static void main(String[] args){

		for(int i = 0; i<args.length;i++){
			try{
				InetAddress ia = InetAddress.getByName(args[i]);
				scan(ia);
			}catch(UnknownHostException ex){
				System.err.println(args[i] + " No es un nombre de Host Valido.");
			}
		}
	}

	public static void scan(InetAddress remote){

		String hostname = remote.getHostName();
		for(int port = 0; port < 65536;port++){
			try{
				Socket s = new Socket(remote,port);
				System.out.println("A server is listening on port " + port + " of " + hostname);
			s.close();
			}catch(IOException ex){

			}
		}
	}
	public static void scan(String remote) throws UnknownHostException{
		InetAddress ia = InetAddress.getByName(remote);
	}
}
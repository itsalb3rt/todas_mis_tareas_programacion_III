import java.io.*;
import java.net.*;

public class ServidorM2{
	public static void main(String[] args){
		ServerSocket servidor = null;
		Socket cliente = null;
		boolean escuchando = true;
		final int PUERTO = 5000;
		try{
			servidor = new ServerSocket(PUERTO);
			System.out.println("Socket escuchando en puerto " + PUERTO);
		}catch(IOException e){
			System.err.println("Error al ServerSocket: " + e.getMessage());
			System.err.println("Terminando ejecucion del servidor.....");
			System.exit(1);
		}

		//Servidor escuchando por peticiones de conexion
		//ciclo principal, creando un hilo para manejar cada conexion pedida

		while(escuchando){
			try{
				cliente = servidor.accept();
			}catch(IOException e){
				System.err.println("Error al llegar una peticion de conexion: " + e.getMessage());
				cliente = null;
			}
			if(cliente != null){
				new AtiendeM2(cliente).start();
			}
		}
		try{
			servidor.close();
		}catch(IOException e){}
	}
}

class DatosSocket{
	InetAddress dirlPlocal = null;
	InetAddress dirlPRemota = null;
	int puertoLocal = 0;
	int puertoRemoto = 0;

	public DatosSocket(Socket socket){
		dirlPRemota = socket.getInetAddress();
		puertoRemoto = socket.getPort();
		dirlPlocal = socket.getLocalAddress();
		puertoLocal = socket.getLocalPort();
	}
	public String toString(){
		String string = "Remoto: " + dirlPRemota.getHostAddress() + "" + puertoRemoto;
		string += " Local: " + dirlPlocal.getHostAddress() + "" + puertoLocal;
		return string;
	}
}

class AtiendeM2 extends Thread{
	private BufferedReader entrada;
	private DataOutputStream salida;
	private String llego;
	private Socket cliente = null;
	DatosSocket dSocket = null;

	public AtiendeM2(Socket cliente){
		this.cliente = cliente;
		dSocket = new DatosSocket(cliente);
		System.out.println("Ya se conecto --> " + dSocket.toString());
	}
	public void run(){
		try{
			entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			salida = new DataOutputStream(cliente.getOutputStream());
			do{
				llego = entrada.readLine();
				System.out.println("(" + dSocket.toString() + ") Llego: " + llego );
				salida.writeInt(llego.length());
			}while(llego.length() != 0);
		}catch(SocketException se){
			//Para que el controlC no haga "tronar" al servidor
			System.err.println("Error al recibir datos, cerrando conexion.....");
		}catch(IOException e){
			System.out.println("Error al recibir datos: " + e.getMessage());
			e.printStackTrace();
		}
		try{
			entrada.close();
			salida.close();
			cliente.close();
		}catch(IOException e){}
		System.out.println("Ya se desconecto " + dSocket.toString());
	}
}
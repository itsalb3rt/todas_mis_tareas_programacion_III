import java.net.*;
public class PruebanetAddress{
	public static void main(String args[]){
		//analisis del parametro dado en la linea de comandos
		String argumento;
		InetAddress direccion = null;
		try{
			argumento = args[0];
		}catch(ArrayIndexOutOfBoundsException e){
			argumento = null;
		}

		//Obtencion de la direccion IP, de la maquina dada como parametro o de la maquina local
		try{
			if(argumento!=null)
				direccion=InetAddress.getByName(argumento);
			else
				direccion = InetAddress.getLocalHost();
		}catch(UnknownHostException e){
			System.out.println(e.getMessage());
			System.exit(0);
		}
		//Obtencion de datos de la direccion IP con metodos de la clave InnetAddress
		System.out.println(direccion.getHostAddress());
		System.out.println(direccion.getHostName());
		System.out.println(direccion.toString());
		byte[]dir = direccion.getAddress();
		for(int i = 0;i<dir.length;i++){
			int b = dir[i] < 0 ? dir[i]+256:dir[i];
			System.out.print(b + ".");
		}
		System.out.println();
		try{
			InetAddress[] direcciones = InetAddress.getAllByName(direccion.getHostName());
			for(int i = 0;i<direcciones.length;i++){
				System.out.println(direcciones[i].toString());
			}
		}catch(UnknownHostException e){}
	}
}
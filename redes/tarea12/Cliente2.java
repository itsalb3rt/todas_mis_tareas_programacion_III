import java.io.*;
import java.net.*;

public class Cliente2{
	public static void main(String[] comandos){
		Socket yo = null;
		PrintWriter alServidor;
		BufferedReader delTeclado;
		DataInputStream delServidor;
		String tecleado;

		try{
				yo = new Socket("127.0.0.1",5000);
				System.out.println("Ya se conecto al Servidor");

				delTeclado = new BufferedReader(new InputStreamReader(System.in));
				alServidor = new PrintWriter(yo.getOutputStream(),true);
				delServidor = new DataInputStream(yo.getInputStream());

				do{
					System.out.print("Teclea un string: ");
					tecleado = delTeclado.readLine();
					alServidor.println(tecleado);
					System.out.println("Longitud =" + delServidor.readInt());
				}while(tecleado.length() != 0);

				System.out.println("Ya termine de enviar");
				delServidor.close();
				delTeclado.close();
				alServidor.close();
				yo.close();
		}catch(ArrayIndexOutOfBoundsException | IOException   e){
			System.out.println(e.getMessage());
			System.exit(1);
		}

	}
}
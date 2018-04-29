import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

//<applet width="400" height="400" code="Servidor "></applet>

public class Servidor extends JFrame
{
	private JTextField campoIntroducir;
	private JTextArea areaPantalla;
	private ObjectOutputStream salida;
	private ObjectInputStream entrada;
	private ServerSocket servidor;
	private Socket conexion;
	private int contador=1;
	int palabra=0;
	int linea=0;
	//configurar el Servidor
	public Servidor()
	{
		super("Servidor");
		Container contenedor=getContentPane();
		//crear campoIntroducir y registrar componente de escucha
		campoIntroducir=new JTextField();
		campoIntroducir.setEditable(false);
		campoIntroducir.addActionListener(
		new ActionListener()
		{
		
		  //enviar mensaje al cliente
		  public void actionPerformed(ActionEvent evento)
		  {
			enviarDatos(evento.getActionCommand());
			campoIntroducir.setText("");
	      }
	    }
		);
		contenedor.add(campoIntroducir, BorderLayout.NORTH);
		//crear areaPantalla
		areaPantalla=new JTextArea();
		contenedor.add(new JScrollPane(areaPantalla),BorderLayout.CENTER);
		setSize(300,150);
		setVisible(true);
	}//fin del constructor servidor
	//configurar y ejecutar el servidor
	public void ejecutarServidor()
	{
		//configurar servidor para que reciba conexion; procesar las conexiones
		try
		{
			//Paso 1: crear un objeto ServerSocket.
			servidor=new ServerSocket(12345,100);
			while(true)
			{
				try
				{
					esperarConexion();//Paso 2: esperar una conexion
					obtenerFlujos();//Paso 3: obtener flujos de entrada y salida
					procesarConexion();//Paso 4: procesar la conexion.
				}
				//procesar excepcion EOFException cuando el cliente cierre la conexion
				catch(EOFException excepcionEOF)
				{
					System.err.println("el servidor termino la conexion");
				}
				finally
				{
					cerrarConexion();//Paso 5: cerrar la conexion.
					++contador;
				}
			}//fin de la instruccion while
		}//fin del bloque try
		//procesar problemas con E/S.
		
		catch(IOException excepcionES)
		{
			excepcionES.printStackTrace();
		}
	}//fin del metodo ejecutarServidor
	
	//esperar que la conexion llegue, despues mostrar informacion de la conexion
	private void esperarConexion() throws IOException
	{
		mostrarMensaje("Esperando una conexion \n ");
		conexion=servidor.accept();//permitir al servidor aceptar la conexion.
		mostrarMensaje("Conexion "+contador+"recibida de: "+conexion.getInetAddress().getHostName());
	}
	//obtener flujos para enviar y recibir datos
	private void obtenerFlujos() throws IOException
	{
		//establecer flujos de salida para lo objetos
		salida=new ObjectOutputStream(conexion.getOutputStream());
		salida.flush();//vaciar bufer de salida para enviar informacion de encabezado
		
		//establecer flujo de entrada para los objetos
		entrada=new ObjectInputStream(conexion.getInputStream());
		
		mostrarMensaje("\n Se recibieron los flujos de E/S \n ");
	}
	//procesar la conexion con el cliente
	private void procesarConexion() throws IOException
	{
		//enviar mensaje de conexion exitosa al cliente
		String mensaje = "Conexion exitosa ";
		enviarDatos(mensaje);
		//habilitar campoIntroducir para que el usuario del servidor pueda enviar mensaje
		establecerCampoTextoEditable(true);
		do
		{
			//procesar los mensajes enviados por el cliente
			
			//leer el mensaje y mostrarlo en pantalla
		    try
			{
				mensaje=(String)entrada.readObject();
				contarpalabras(mensaje);
				mostrarMensaje("\n "+mensaje);
			}
			//atrapar problemas que pueden ocurrir al tratar de leer del cliente
			catch(ClassNotFoundException excepcionClaseNoEncontrada)
			{
				mostrarMensaje("\n Se recibio un tipo de objeto desconocido");
			}
		}while(!mensaje.equals("FIN"));
		
	}//fin del metodo procesarConexion
	//cerrar flujos y socket
	private void cerrarConexion()
	{
		mostrarMensaje("\n Finalizando de la conexion\n");
		establecerCampoTextoEditable(false);//deshabilitar campoIntroducir
		try
		{
			salida.close();
			entrada.close();
			conexion.close();
		}
		catch(IOException excepcionES)
		{
			excepcionES.printStackTrace();
		}
	}
	//Contar palabras
	private void contarpalabras(String mensaje)
	{
		//METODO PAR CONTAR LAS PALABRAS Y LINEAS DE MESAJES ENVIADOS AL SERVIDOR
		String s=mensaje;
		String control;
		StringTokenizer stt=new StringTokenizer(s);
		int i=0;
		while(stt.hasMoreTokens())
		{
			control=stt.nextToken();
			++i;
		}
		if(mensaje!=null)
		{
			palabra=palabra + i-1;
			linea++;
		}
		try
		{
			salida.writeObject("SERVIDOR --> ha enviado "+ palabra +"palabras y "+linea+ " lineas");
			salida.flush();
		}
		//procesar problemas que pueden ocurrir al enviar el objeto
		catch(IOException excepcionES)
		{
			areaPantalla.append("\n Error al escribir objeto");
		}
	}
	//enviar mensaje al cliente
	private void enviarDatos(String mensaje)
	{
		try
		{
			salida.writeObject("SERVIDOR--> "+mensaje);
			salida.flush();
			mostrarMensaje("\nSERVIDOR--> "+mensaje);
		}
		//procesar problemas que pueden ocurrir al enviar el objeto
		catch(IOException excepcionES)
		{
			areaPantalla.append("\nError al escribir objeto ");
		}
	}
	//metodo utilitario que es llamado desde otros subprocesos para manipular a areaPantalla
	//en el subproceso despachador de eventos
	private void mostrarMensaje(final String mensajeAMostrar)
	{
		//mostrar mensaje del subproceso de ejecucion despachador de eventos
		SwingUtilities.invokeLater(new Runnable(){
			//clase interna para asegurar que el texArea del servidor se actualice apropiadamente
			public void run()//actualiza areaPantalla
			{
				areaPantalla.append(mensajeAMostrar);
				areaPantalla.setCaretPosition(areaPantalla.getText().length());
			}
		}//fin de la clase interna
		);//fin de la llamada a SwingUtilities.invokeLater
	}
	//metodo utilitario que es llamado desde otros subprocesos para manipular a campoIntroducir
	//en el subproceso despachador de eventos
	private void establecerCampoTextoEditable(final boolean editable)
	{
		//mostrar mensaje del subproceso de ejecucion despachador de eventos
		SwingUtilities.invokeLater(new Runnable(){
			//clase interna para asegurar que la GUI se actualice apropiadamente.
			public void run()
			{
				//establece la capacidad de modificar a campoIntroducir
				campoIntroducir.setEditable(editable);
			}
			
		}//fin de la clase interna 
		);//fin de la llamada a SwingUtilities.invokeLater
	}
	public static void main(String args[])
	{
		Servidor aplicacion=new Servidor();
		aplicacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aplicacion.ejecutarServidor();
	}
}//fin de la clase Servidor
import java.sql.*;
import java.io.*;

public class BaseDeDatos5F{
	public static void main(String[] args){
		String matri = "";
		InputStreamReader entrada = new InputStreamReader(System.in);
		BufferedReader teclado = new BufferedReader(entrada);
		System.out.println("Digita la matricula del alumno que desea dar baja");

		try{
			int matri3 = Integer.parseInt(teclado.readLine());
			matri = Integer.toString(matri3);
			System.out.println("Esta dando de baja al alumno de matricula " + matri);

		}catch(IOException e){
			System.out.println(e.getMessage());
		}
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		}catch(ClassNotFoundException e){
			System.out.println(e.getMessage());
		}

		try{
			Connection conexion = DriverManager.getConnection("jdbc:odbc:curso1","jdbc","jdbc");

			Statement estatuto = conexion.createStatement();
			Statement estatuto1 = conexion.createStatement();
			//borrar el estudiante de ambas tablas
			estatuto.executeUpdate("DELETE FROM lista WHERE MATRICULA = " + matri);
			estatuto1.executeUpdate("DELETE FROM final WHERE MATRICULA = " + matri);

			estatuto.close();
			estatuto1.close();
			conexion.close();

		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		System.out.println("El Alumno fue dado de baja");
		try{
			teclado.close();
			entrada.close();
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
	}
}
import java.sql.*; //importar la clases necesarias para consultas
public class BaseDeDatos2{
	public static void main(String[] args){
		//Cargar y registrar el driver
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		}catch(ClassNotFoundException e){
			System.out.println(e.getMessage());
		}
		//Poner un solo try para hacer la discucion mas agil todos los metodos arrojan
		//SQLException, normalmente es una aplicacion real se sera un poco mas selectivo
		try{
			//abrir la conexion a la base de datos con data source name curso1
			Connection conexion = DriverManager.getConnection("jdbc:odbc:curso1");

			//Crear el estatuto para hacer los accesos
			Statement estatuto = conexion.createStatement();

			//Hacer la consulta, en rs obtiendo todos los registros que nos regresa el query
			ResultSet rs = estatuto.executeQuery("SELECT MATRICULA,NOMBRES FROM lista WHERE NOMBRES > 'b' AND NOMBRES < 'f' ORDER BY NOMBRES DESC");

			//BARRER TODOS LOS DATOS REGISTRADOS QUE ARROJA EL QUERY
			while(rs.next()){
				//Obtener todos los campos de cada uno de los registros, con el metodo get adecuado
				String matricula = rs.getString("MATRICULA");
				String nombre = rs.getString("NOMBRES");

				//imprimir en pantalla todos los campos obtenidos para un registro
				System.out.println(matricula + " " + nombre );
			}
			//Cerrar resulset, estatuto y conexion
			rs.close();
			estatuto.close();
			conexion.close();
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}
}
package application;

import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Conexion {

	private String bd;
	private String url= "jdbc:oracle:thin:@localhost:1521:XE";
	private String usr = "system";
	private String pwd = "1511noviembre";
	private static Connection Conexion;
	

	public Conexion()  {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Conexion = DriverManager.getConnection(url, usr, pwd);
			
			if(!Conexion.isClosed()) {
				System.out.println("Conexión establecida");
				//conexion.close();
			}
			else
				System.out.println("Fallo en Conexión");	
			

		}catch (Exception e) {
			System.out.println("ERROR en conexión con ORACLE");	
			e.printStackTrace();
		}
		
	}
	
	public ObservableList<Alumno> Consulta() {
		
	ObservableList<Alumno> aux = FXCollections.observableArrayList();
		
		try {
			Statement stmt = Conexion.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT DNI_Alumno, Nombre, Apellido, Telefono, Provincia,  Localidad, "
			+ "Correoelectronico, Nacionalidad, DNI_Tutor FROM PATRICIA.ALUMNO" );
			while(rset.next()) {
				String DNI_Alumno = rset.getString(1);
				String Nombre = rset.getString(2); 
				String Apellido = rset.getString(3);
				String Telefono = rset.getString(4);
				String Provincia = rset.getString(5);
				String Localidad = rset.getString(6);
				String Correoelectronico = rset.getString(7); 
				String Nacionalidad = rset.getString(8);
				String DNI_Tutor = rset.getString(9);
				Alumno auxAlum = new Alumno(DNI_Alumno, Nombre, Apellido, Telefono, Provincia,  Localidad, Correoelectronico, Nacionalidad, DNI_Tutor);
				aux.add(auxAlum);
			}
			rset.close();
			stmt.close();
			
		}catch (SQLException s){
			s.printStackTrace();
		}
		return aux;
		
	}
}
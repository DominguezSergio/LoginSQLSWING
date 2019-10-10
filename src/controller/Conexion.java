package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	public Connection conectar() {
		Connection DbConnection = null; // variable de metodo
		try {
			Class.forName("com.mysql.jdbc.Driver");
			DbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/wbeducar_java", "root", "");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return DbConnection;
	}
}//Cierra el class

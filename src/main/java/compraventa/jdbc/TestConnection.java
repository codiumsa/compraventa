package compraventa.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que muestra cómo establecer una conexión a la BD utilizando JDBC.
 * 
 * @author jorge
 */
public class TestConnection {
	public static void main(String[] args) {
		System.out.println("-------- Oracle JDBC. Probando conexión ------");
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (ClassNotFoundException e) {
			System.out.println("El driver no se registró correctamente.");
			e.printStackTrace();
			return;
		}
		System.out.println("Oracle JDBC Driver registrado!");
		Connection connection = null;

		try {
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:49161:xe", "system", "oracle");

		} catch (SQLException e) {
			System.out.println("No se pudo establecer la conexión a la base de datos.");
			e.printStackTrace();
			return;

		}

		if (connection != null) {
			System.out.println("Se puedo establecer conexión a la base de datos!");

			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("No se puedo establecer la conexión.");
		}
	}
}

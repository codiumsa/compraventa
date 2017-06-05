package compraventa.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import compraventa.Persona;

/**
 * Capa de acceso a datos para la tabla persona.
 * 
 * @author jorge
 */
public class PersonasDAO {

	/**
	 * Crea la tabla persona.
	 * 
	 * @throws Exception
	 */
	public void createTable() throws Exception {
		System.out.println("Creando la tabla persona...");
		Connection con = getConnection();
		String sql = "create table persona(id number primary key, nombre varchar(200))";
		Statement stm = con.createStatement();
		stm.executeUpdate(sql);
		stm.close();
		con.close();
		System.out.println("Tabla persona creada.");
	}

	/**
	 * Elimina la tabla persona.
	 * 
	 * @throws Exception
	 */
	public void dropTable() throws Exception {
		System.out.println("Eliminando la tabla persona...");
		Connection con = getConnection();
		String sql = "drop table persona";
		Statement stm = con.createStatement();
		stm.executeUpdate(sql);
		stm.close();
		con.close();
		System.out.println("Tabla persona eliminada.");
	}

	/**
	 * Guarda el persona dado en la base de datos.
	 * 
	 * @param p
	 */
	public void persist(Persona p) throws Exception {
		String sql = "INSERT INTO persona(id, nombre) VALUES(?, ?)";
		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		int count = allCount(con);
		ps.setInt(1, count + 1);
		ps.setString(2, p.getNombre());
		ps.executeUpdate();
		ps.close();
		con.close();
		System.out.println("Registro guardado!");
	}

	/**
	 * Retorna todos los personas que se encuentran en la BD.
	 * 
	 * @return
	 */
	public List<Persona> all() throws Exception {
		String sql = "SELECT * from persona";
		List<Persona> personas = new ArrayList<>();
		Connection con = getConnection();
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		
		while(rs.next()) {
			Persona p = new Persona();
			p.setId(rs.getLong("id"));
			p.setNombre(rs.getString("nombre"));
			personas.add(p);
		}
		rs.close();
		stm.close();
		con.close();
		System.out.println("Lista de personas recuperada!");
		return personas;
	}
	
	/**
	 * Retorna el total de registros en la tabla.
	 * 
	 * @return
	 * @throws Exception
	 */
	public int allCount() throws Exception {
		return allCount(null);
	}

	private int allCount(Connection con) throws Exception {
		boolean close = false;

		if (con == null) {
			con = getConnection();
			close = true;
		}
		String sql = "SELECT COUNT(*) AS count FROM persona";
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		rs.next();
		int count = rs.getInt("count");
		
		if (close) {
			con.close();
		}
		return count;
	}

	/**
	 * Retorna una nueva conexión a la base de datos.
	 * 
	 * @return
	 * @throws Exception
	 */
	private Connection getConnection() throws Exception {
		System.out.println("Obteniendo la conexión a la base de datos");
		return DriverManager.getConnection("jdbc:oracle:thin:@localhost:49161:xe", "system", "oracle");
	}
}

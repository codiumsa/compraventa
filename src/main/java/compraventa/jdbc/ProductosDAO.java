package compraventa.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import compraventa.Producto;

/**
 * Capa de acceso a datos para la tabla producto.
 * 
 * @author jorge
 */
public class ProductosDAO {

	/**
	 * Crea la tabla producto.
	 * 
	 * @throws Exception
	 */
	public void createTable() throws Exception {
		System.out.println("Creando la tabla producto...");
		Connection con = getConnection();
		String sql = "create table producto(id number primary key, existencia integer, codigo varchar(30), precio number, nombre varchar(200))";
		Statement stm = con.createStatement();
		stm.executeUpdate(sql);
		stm.close();
		con.close();
		System.out.println("Tabla producto creada.");
	}

	/**
	 * Elimina la tabla producto.
	 * 
	 * @throws Exception
	 */
	public void dropTable() throws Exception {
		System.out.println("Eliminando la tabla producto...");
		Connection con = getConnection();
		String sql = "drop table producto";
		Statement stm = con.createStatement();
		stm.executeUpdate(sql);
		stm.close();
		con.close();
		System.out.println("Tabla producto eliminada.");
	}

	/**
	 * Guarda el producto dado en la base de datos.
	 * 
	 * @param p
	 */
	public void persist(Producto p) throws Exception {
		String sql = "INSERT INTO producto(id, nombre, codigo, existencia, precio) VALUES(?, ?, ?, ?, ?)";
		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		int count = allCount(con);
		ps.setInt(1, count + 1);
		ps.setString(2, p.getNombre());
		ps.setString(3, p.getCodigo());
		ps.setInt(4, p.getExistencia());
		ps.setLong(5, p.getPrecio());
		ps.executeUpdate();
		ps.close();
		con.close();
		System.out.println("Registro guardado!");
	}

	/**
	 * Retorna todos los productos que se encuentran en la BD.
	 * 
	 * @return
	 */
	public List<Producto> all() throws Exception {
		String sql = "SELECT * from producto";
		List<Producto> productos = new ArrayList<>();
		Connection con = getConnection();
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		
		while(rs.next()) {
			Producto p = new Producto();
			p.setId(rs.getLong("id"));
			p.setCodigo(rs.getString("codigo"));
			p.setExistencia(rs.getInt("existencia"));
			p.setNombre(rs.getString("nombre"));
			p.setPrecio(rs.getLong("precio"));
			productos.add(p);
		}
		rs.close();
		stm.close();
		con.close();
		System.out.println("Lista de productos recuperada!");
		return productos;
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
		String sql = "SELECT COUNT(*) AS count FROM producto";
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

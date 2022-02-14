package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConexionBD {

	private static final String DB_USER = "root";
	private static final String DB_PASS = "Cr1s1996#";
	private static final String DB_URI = "jdbc:mysql://localhost:3306/bartender?allowPublicKeyRetrieval=true&useSSL=false";

	public static int[] obtenerDatosBD(int id) {
		PreparedStatement stmt;
		ResultSet rs;
		String array = null;
		String[] arrayTemp = null;
		int[] a = null;
		try {
			Connection conexion = DriverManager.getConnection(ConexionBD.DB_URI, ConexionBD.DB_USER,
					ConexionBD.DB_PASS);
			stmt = conexion.prepareStatement("SELECT * FROM bartender.arrays WHERE id=" + id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				array = rs.getString("input_array");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		arrayTemp = array.split(",");
		a = new int[arrayTemp.length];
		for (int i = 0; i < arrayTemp.length; i++) {
			a[i] = Integer.parseInt(arrayTemp[i]);
		}
		return a;
	}

}

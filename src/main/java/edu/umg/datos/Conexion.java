package edu.umg.datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {
    // URL, usuario y contraseña JDBC del servidor MySQL
    private static final String JDBC_URL = "jdbc:mysql://localhost/p2a?useSSL=false&serverTimezone=UTC";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "Obed2003.1";

    // Método para establecer una conexión con la base de datos
    public static Connection getConnection() throws SQLException {
        // Establecer una conexión usando la URL, usuario y contraseña especificados
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
    }

    // Método para cerrar un ResultSet
    public static void close(ResultSet rs) {
        try {
            // Cerrar el ResultSet para liberar recursos
            rs.close();
        } catch (SQLException ex) {
            // Imprimir cualquier SQLException que ocurra durante el cierre del ResultSet
            ex.printStackTrace(System.out);
        }
    }

    // Método para cerrar un PreparedStatement
    public static void close(PreparedStatement stmt) {
        try {
            // Cerrar el PreparedStatement para liberar recursos
            stmt.close();
        } catch (SQLException ex) {
            // Imprimir cualquier SQLException que ocurra durante el cierre del PreparedStatement
            ex.printStackTrace(System.out);
        }
    }

    // Método para cerrar una conexión a la base de datos
    public static void close(Connection conn) {
        try {
            // Cerrar la conexión para liberar recursos
            conn.close();
        } catch (SQLException ex) {
            // Imprimir cualquier SQLException que ocurra durante el cierre de la conexión
            ex.printStackTrace(System.out);
        }
    }
}


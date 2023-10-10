// UsuarioDAO.java
package edu.umg.datos;

import edu.umg.domain.Usuario;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    private Connection conexionTransaccional;

    // Consultas SQL
    private static final String SQL_INSERT = "INSERT INTO usuario(username, password) VALUES(?, ?)";
    private static final String SQL_VALIDATE_USER = "SELECT * FROM usuario WHERE username = ? AND password = ?";

    // Constructor por defecto
    public UsuarioDAO() {
    }

    // Constructor con conexión transaccional
    public UsuarioDAO(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    // Método para insertar un nuevo usuario en la base de datos
    public int insert(Usuario usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            // Obtener la conexión, utilizando la transaccional si está presente
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, usuario.getNombre_usuario());
            stmt.setString(2, hashPassword(usuario.getContraseña())); // Se encripta la contraseña antes de insertar

            System.out.println("Ejecutando query: " + SQL_INSERT);
            // Ejecutar la inserción y obtener el número de filas afectadas
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados: " + rows);
        } finally {
            // Cerrar recursos (PreparedStatement, Connection) según sea necesario
            Conexion.close(stmt);
            if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }
        }

        return rows;
    }

    // Método para validar las credenciales de un usuario en la base de datos
    public boolean validateUser(String nombreUsuario, String contraseña) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean isValid = false;

        try {
            // Obtener la conexión, utilizando la transaccional si está presente
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_VALIDATE_USER);
            stmt.setString(1, nombreUsuario);
            stmt.setString(2, hashPassword(contraseña)); // Se encripta la contraseña antes de comparar

            rs = stmt.executeQuery();
            isValid = rs.next();
        } finally {
            // Cerrar recursos (ResultSet, PreparedStatement, Connection) según sea necesario
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (this.conexionTransaccional == null) {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return isValid;
    }

    // Método para realizar el hash de la contraseña utilizando el algoritmo MD5
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // Manejar la excepción si no se puede obtener el algoritmo de hash MD5
            e.printStackTrace();
            return password;
        }
    }
}

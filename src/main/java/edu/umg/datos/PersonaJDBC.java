package edu.umg.datos;

import edu.umg.domain.Persona;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaJDBC {

    private Connection conexionTransaccional;

    // Consultas SQL
    private static final String SQL_SELECT = "SELECT id_persona, nombre, apellido, email, telefono FROM persona";
    private static final String SQL_INSERT = "INSERT INTO persona(nombre, apellido, email, telefono) VALUES(?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE persona SET nombre=?, apellido=?, email=?, telefono=? WHERE id_persona = ?";
    private static final String SQL_DELETE = "DELETE FROM persona WHERE id_persona=?";

    // Constructor por defecto
    public PersonaJDBC() {
    }

    // Constructor con conexión transaccional
    public PersonaJDBC(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    // Método para seleccionar todas las personas de la base de datos
    public List<Persona> select() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Persona persona = null;
        List<Persona> personas = new ArrayList<>();

        try {
            // Obtener la conexión, utilizando la transaccional si está presente
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            // Procesar los resultados y construir objetos Persona
            while (rs.next()) {
                int id_persona = rs.getInt("id_persona");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");

                persona = new Persona();
                persona.setId_persona(id_persona);
                persona.setNombre(nombre);
                persona.setApellido(apellido);
                persona.setEmail(email);
                persona.setTelefono(telefono);

                personas.add(persona);
            }
        } finally {
            // Cerrar recursos (ResultSet, PreparedStatement, Connection) según sea necesario
            Conexion.close(rs);
            Conexion.close(stmt);
            if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }
        }

        return personas;
    }

    // Método para insertar una nueva persona en la base de datos
    public int insert(Persona persona) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            // Obtener la conexión, utilizando la transaccional si está presente
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            // Establecer parámetros para la inserción
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTelefono());

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

    // Método para actualizar la información de una persona en la base de datos
    public int update(Persona persona) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            // Obtener la conexión, utilizando la transaccional si está presente
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            // Establecer parámetros para la actualización
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTelefono());
            stmt.setInt(5, persona.getId_persona());

            System.out.println("Ejecutando query: " + SQL_UPDATE);
            // Ejecutar la actualización y obtener el número de filas afectadas
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

    // Método para eliminar una persona de la base de datos
    public int delete(Persona persona) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            // Obtener la conexión, utilizando la transaccional si está presente
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            // Establecer parámetros para la eliminación
            stmt.setInt(1, persona.getId_persona());

            System.out.println("Ejecutando query: " + SQL_DELETE);
            // Ejecutar la eliminación y obtener el número de filas afectadas
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
}

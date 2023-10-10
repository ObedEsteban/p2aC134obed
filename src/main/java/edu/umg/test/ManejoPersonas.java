package edu.umg.test;

import edu.umg.datos.Conexion;
import edu.umg.datos.PersonaJDBC;
import edu.umg.domain.Persona;
import java.sql.*;
import java.util.Scanner;

/**
 * Clase que realiza pruebas de manejo de transacciones y operaciones CRUD en la tabla Persona.
 */
public class ManejoPersonas {

    /**
     * Método que despliega todas las personas almacenadas en la base de datos.
     */
    public static void desplegar() {
        Connection conexion = null;
        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }

            PersonaJDBC personaJdbc = new PersonaJDBC(conexion);

            // Mostrar todas las personas almacenadas
            for (Persona persona : personaJdbc.select()) {
                System.out.println("Persona: " + persona);
            }

            // Confirmar la transacción
            conexion.commit();
            System.out.println("Se ha hecho commit de la transacción");

        } catch (SQLException ex) {
            // Manejar excepciones de SQL, realizar rollback en caso de error
            ex.printStackTrace(System.out);
            System.out.println("Entramos al rollback");
            try {
                if (conexion != null) {
                    conexion.rollback();
                }
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        } finally {
            // Cerrar la conexión en el bloque finally
            Conexion.close(conexion);
        }
    }

    /**
     * Método principal que realiza pruebas de inserción de una nueva persona en la base de datos.
     * Luego despliega todas las personas almacenadas.
     * @param args Argumentos de la línea de comandos (no se utilizan en este caso).
     */
    public static void main(String[] args) {

        // Desplegar personas existentes
        desplegar();

        Connection conexion = null;
        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }

            PersonaJDBC personaJdbc = new PersonaJDBC(conexion);

            // Insertar nueva persona
            Persona nuevaPersona = new Persona();
            nuevaPersona.setNombre("Monica");
            nuevaPersona.setApellido("Veliz");
            personaJdbc.insert(nuevaPersona);

            // Confirmar la transacción
            conexion.commit();
            System.out.println("Se ha hecho commit de la transacción");

        } catch (SQLException ex) {
            // Manejar excepciones de SQL, realizar rollback en caso de error
            ex.printStackTrace(System.out);
            System.out.println("Entramos al rollback");
            try {
                if (conexion != null) {
                    conexion.rollback();
                }
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        } finally {
            // Cerrar la conexión en el bloque finally
            Conexion.close(conexion);
        }
    }
}

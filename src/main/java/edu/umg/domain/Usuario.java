// Usuario.java
package edu.umg.domain;

/**
 * Clase que representa la entidad Usuario con sus atributos básicos.
 */
public class Usuario {

    private String nombre_usuario;   // Nombre de usuario
    private String contraseña;       // Contraseña del usuario

    // Métodos de acceso y modificación para el atributo nombre_usuario
    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    // Métodos de acceso y modificación para el atributo contraseña
    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}

package edu.umg.domain;

/**
 * Clase que representa una entidad Persona con sus atributos básicos.
 */
public class Persona {

    private int id_persona;     // Identificador único de la persona
    private String nombre;      // Nombre de la persona
    private String apellido;    // Apellido de la persona
    private String email;       // Dirección de correo electrónico de la persona
    private String telefono;    // Número de teléfono de la persona

    // Métodos de acceso y modificación para el atributo id_persona
    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    // Métodos de acceso y modificación para el atributo nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Métodos de acceso y modificación para el atributo apellido
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    // Métodos de acceso y modificación para el atributo email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Métodos de acceso y modificación para el atributo telefono
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Sobrescritura del método toString para obtener una representación en cadena de la instancia Persona.
     * @return Cadena que representa la instancia de Persona.
     */
    @Override
    public String toString() {
        return "Persona{" +
                "id_persona=" + id_persona +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}

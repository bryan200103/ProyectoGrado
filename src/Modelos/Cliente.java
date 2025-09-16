    
package Modelos;

/**
 *
 * @author Bryan
 */
public class Cliente {
    private int id;
    private String nombre;
    private String cedula;
    private String telefono;
    private String direccion;
    private String correo;
    
    
    //constructor
    public Cliente() {
        this.id = 0;
        this.nombre = "";
        this.cedula = "";
        this.telefono = "";
        this.direccion = "";
        this.correo = "";
    }

    public Cliente(String nombre, String cedula, String telefono, String direccion, String correo) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
    }

    public int getId() {
        return id;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    
    
}



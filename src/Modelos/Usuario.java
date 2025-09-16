/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *
 * @author Bryan
 */
public class Usuario {
    private int id;
    private String nombre;
    private String clave;
    private static String rolsesion;
    private String rol;
    private String cedula;
    private String user;
    
    
    
    
//    public Usuario() {
//    // Constructor vacío obligatorio
//}

    public Usuario() {
        this.id = 0;
        this.nombre = "";
        this.clave = "";
        this.rol = "";
        this.cedula="";
        this.user="";
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

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public static String getRolSession() {
        return rolsesion;
    }

    public static void setRolSession(String rolsesionn) {
        rolsesion = rolsesionn;
    }
    
    public  String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
        //Usuario.rolsesion=rol;
        
    }
    
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    
    
    
    

}

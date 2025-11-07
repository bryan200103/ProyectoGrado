/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Conexion.Conexionmy;
import Modelos.Usuario;
import Ventanas.UsuarioG;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Bryan
 */
public class ControladorUsuario {
    private int id_cliente;

    //metodo para inisiar sesion 
    public boolean login(Usuario objeto) {
        boolean respuesta = false;
        Connection cn = Conexionmy.Conectar();
        String sql = "SELECT nombre_usuario, clave, rol FROM usuarios WHERE userlogin = ? AND clave = ?";

        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, objeto.getNombre());
            ps.setString(2, objeto.getClave());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                respuesta = true;

                // Guardar el rol en el objeto Usuario
                objeto.setRol(rs.getString("rol"));
            }

        } catch (SQLException e) {
            System.out.println("Error al inicio de sesión: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al inicio de sesión");
        }
        return respuesta;
    }

    public boolean guardar(Usuario objeto) {
        String sql = "INSERT INTO usuarios (nombre_usuario, clave, rol, cedula, userlogin) VALUES (?, ?, ?, ?, ?)";;
        try (Connection cn = Conexionmy.Conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, objeto.getNombre());
            ps.setString(2, objeto.getClave());
            ps.setString(3, objeto.getRol());
            ps.setString(4, objeto.getCedula());
            ps.setString(5, objeto.getUser());
            System.out.println("Nombre: " + objeto.getNombre());
            System.out.println("Cedula: " + objeto.getCedula());
            System.out.println("User: " + objeto.getUser());
            System.out.println("Clave: " + objeto.getClave());
            System.out.println("Rol: " + objeto.getRol());

            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar cliente: " + e.getMessage());
            return false;
        }
    }

    public boolean UsuarioExiste(String cedula) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE cedula = ?";
        try (Connection cn = Conexionmy.Conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, cedula);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; // si count > 0, el cliente ya existe
            }
        } catch (SQLException e) {
            System.out.println("Error al validar cliente: " + e.getMessage());
        }
        return false;

    }

    public boolean usuarioExistePorUserLogin(String userlogin) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE userlogin = ?";
        try (Connection cn = Conexionmy.Conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, userlogin.trim());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; // true si ya existe
            }
        } catch (SQLException e) {
            System.out.println("error" + e);
        }
        return false; // no existe
    }
    
    public String buscarUsuarioPorCedula(String cedula) {
        Connection con = Conexionmy.Conectar();
        String cedulaBuscar = cedula;

        if (cedulaBuscar.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese cedula para buscar clientes");
            return "500"; // No hacer búsqueda si está vacío
        }

        DefaultTableModel model = new DefaultTableModel();

        String sql = "SELECT id_usuario, nombre_usuario, clave, rol, cedula, userlogin FROM usuarios WHERE cedula LIKE ?;";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, "%" + cedulaBuscar + "%"); // Busca coincidencias parciales en la cédula
            
            ResultSet rs = pst.executeQuery();

            model.addColumn("N°");
            model.addColumn("nombre");
            model.addColumn("clave");
            model.addColumn("rol");
            model.addColumn("cedula");
            model.addColumn("User");
            
           
            boolean hayDatos = false;
            
            while (rs.next()) {
                hayDatos = true;
                Object[] fila = new Object[6];
                for (int i = 0; i < 6; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                model.addRow(fila);
            }

            UsuarioG.jTable_usuarios.setModel(model);

            con.close();
            
            if(!hayDatos){
                return "404";
            }
            
            
            
            return "200";
        } catch (SQLException e) {
            System.out.println("Error al buscar usuario por cédula: " + e);
            return "401";
        }
    }
    
    
//    public void CargarTablaUsuario() {
//        Connection con = Conexionmy.Conectar();
//        DefaultTableModel model = new DefaultTableModel();
//        String sql = "SELECT id_usuario, nombre_usuario, clave, rol, cedula, userlogin FROM usuarios;";
//        Statement st;
//
//        try {
//            st = con.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            GestionUsuarios.jTable_usuario = new JTable(model);
//            GestionUsuarios.jScrollPane1.setViewportView(GestionUsuarios.jTable_usuario);
//
//            model.addColumn("N°");
//            model.addColumn("nombre");
//            model.addColumn("clave");
//            model.addColumn("rol");
//            model.addColumn("cedula");
//            model.addColumn("User");
//
//            while (rs.next()) {
//
//                Object fila[] = new Object[6];
//                for (int i = 0; i < 6; i++) {
//                    fila[i] = rs.getObject(i + 1);
//
//                }
//
//                model.addRow(fila);
//            }
//            con.close();
//
//        } catch (SQLException e) {
//            System.out.println("Error al llenar tabla usuario" + e);
//        }
//
//        jTable_usuario.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                int fila_point = jTable_usuario.rowAtPoint(e.getPoint());
//                int columna_point = 0;
//                int selectedRow = jTable_usuario.getSelectedRow();
//
//                if (fila_point >= 0) {
//                    id_cliente = (int) jTable_usuario.getValueAt(selectedRow, 0);
//                    //EnviarDatosClienteSeleccionada(idCliente);
//
//                }
//            }
//        });
//
//    }
    
    
    
    
    public String buscarUsuarioPorUser(String user) {
        Connection con = Conexionmy.Conectar();
        String BuscarUser = user;

        if (BuscarUser.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese nombre para buscar clientes");
            return "500"; // No hacer búsqueda si está vacío
        }

        DefaultTableModel model = new DefaultTableModel();

        String sql = "SELECT id_usuario, nombre_usuario, clave, rol, cedula, userlogin FROM usuarios WHERE userlogin LIKE ?;";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, "%" + BuscarUser + "%"); // Busca coincidencias parciales en la cédula

            ResultSet rs = pst.executeQuery();

            model.addColumn("N°");
            model.addColumn("nombre");
            model.addColumn("clave");
            model.addColumn("rol");
            model.addColumn("cedula");
            model.addColumn("User");
            
            boolean hayDatos = false;

            while (rs.next()) {
                hayDatos= true;
                Object[] fila = new Object[6];
                for (int i = 0; i < 6; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                model.addRow(fila);
            }

            UsuarioG.jTable_usuarios.setModel(model);

            con.close();
            
            if(!hayDatos){
                return "404";
            }
            return "200";
        } catch (SQLException e) {
            System.out.println("Error al buscar usuario: " + e);
            return "401";
        }
    }
    
    public boolean actualizar(Usuario objeto, int idUsuario) {
        boolean respuesta = false;
        Connection cn = Conexionmy.Conectar();
        
        try {
                   
            com.mysql.jdbc.PreparedStatement consulta = (com.mysql.jdbc.PreparedStatement) cn.prepareStatement("UPDATE usuarios SET nombre_usuario=?, clave=?, rol=?, cedula=?, userlogin=? WHERE id_usuario=?");
            consulta.setString(1, objeto.getNombre());
            consulta.setString(2,objeto.getClave());
            consulta.setString(3,objeto.getRol());
            consulta.setString(4, objeto.getCedula());
            consulta.setString(5, objeto.getUser());
            consulta.setInt(6, idUsuario);
            
            
            if(consulta.executeUpdate()>0){
                respuesta=true;
            }
            cn.close();


        } catch (SQLException e) {
            System.out.println("error al actualizar Cliente"+ e);

        }
        return respuesta;

    }
    
    public void CargarTablaUsuario(JTable tabla, JScrollPane scroll) {
    Connection con = Conexionmy.Conectar();
    DefaultTableModel model = new DefaultTableModel();
    String sql = "SELECT id_usuario, nombre_usuario, clave, rol, cedula, userlogin FROM usuarios;";
    Statement st;

    try {
        st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        // Definir columnas
        model.addColumn("N°");
        model.addColumn("Nombre");
        model.addColumn("Clave");
        model.addColumn("Rol");
        model.addColumn("Cédula");
        model.addColumn("User");

        // Llenar filas
        while (rs.next()) {
            Object fila[] = new Object[6];
            for (int i = 0; i < 6; i++) {
                fila[i] = rs.getObject(i + 1);
            }
            model.addRow(fila);
        }
        con.close();

        // Asignar el modelo a la tabla y al scrollpane
        tabla.setModel(model);
        scroll.setViewportView(tabla);

    } catch (SQLException e) {
        System.out.println("Error al llenar tabla usuario: " + e);
    }
}

}

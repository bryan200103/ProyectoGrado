
package Controladores;

import Conexion.Conexionmy;
import Modelos.Cliente;
import Ventanas.ClientesG;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Bryan
 */
public class ControladorCliente {
    public boolean guardar(Cliente objeto){
        String sql = "INSERT INTO clientes (nombre, cedula, telefono, direccion, correo) VALUES (?, ?, ?, ?, ?)";
        try (Connection cn = Conexionmy.Conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, objeto.getNombre());
            ps.setString(2, objeto.getCedula());
            ps.setString(3, objeto.getTelefono());
            ps.setString(4, objeto.getDireccion());
            ps.setString(5, objeto.getCorreo());

            int filas = ps.executeUpdate();
            System.out.println("Guardado con exito ");
            return filas > 0;
            
        } catch (SQLException e) {
            System.out.println("Error al insertar cliente: " + e.getMessage());
            return false;
        }
    }
    
    
    public boolean clienteExiste(String cedula) {
    String sql = "SELECT COUNT(*) FROM clientes WHERE cedula = ?";
    try (Connection cn = Conexionmy.Conectar();
         PreparedStatement ps = cn.prepareStatement(sql)) {

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
    
public boolean actualizar(Cliente objeto, int idCliente) {
        boolean respuesta = false;
        Connection cn = Conexionmy.Conectar();
        
        try {
                   
            com.mysql.jdbc.PreparedStatement consulta = (com.mysql.jdbc.PreparedStatement) cn.prepareStatement("UPDATE clientes SET nombre=?, cedula=?, telefono=?, direccion=?, correo=? WHERE id_cliente=?");
            consulta.setString(1, objeto.getNombre());
            consulta.setString(2,objeto.getCedula());
            consulta.setString(3,objeto.getTelefono());
            consulta.setString(4, objeto.getDireccion());
            consulta.setString(5, objeto.getCorreo());
            consulta.setInt(6, idCliente);
            
            
            if(consulta.executeUpdate()>0){
                respuesta=true;
            }
            cn.close();


        } catch (SQLException e) {
            System.out.println("error al actualizar Cliente"+ e);

        }
        return respuesta;

    }

public String buscarUsuarioPorCedula(String cedula) {
        Connection con = Conexionmy.Conectar();
        String BuscarCedula = cedula;

        if (BuscarCedula.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese cedula para buscar cliente");
            return "500"; // No hacer búsqueda si está vacío
        }

        DefaultTableModel model = new DefaultTableModel();

        String sql = "SELECT * FROM clientes WHERE cedula LIKE ?;";
        
        
        boolean hayDatos = false ;

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, "%" + BuscarCedula + "%"); // Busca coincidencias parciales en la cédula

            ResultSet rs = pst.executeQuery();

            model.addColumn("N°");
            model.addColumn("Nombre");
            model.addColumn("Cedula");
            model.addColumn("Telefono");
            model.addColumn("Direccion");
            model.addColumn("Correo");

            while (rs.next()) {
                hayDatos= true;
                Object[] fila = new Object[6];
                for (int i = 0; i < 6; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                model.addRow(fila);
            }

            ClientesG.jTable_cliente.setModel(model);
            

            con.close();
            if(!hayDatos){
                return "404";
            }
            return "200";
        } catch (SQLException e) {
            System.out.println("Error al buscar Cliente: " + e);
            return "401";
        }
        
        
    }





public void buscarUsuarioPorName(String name) {
        Connection con = Conexionmy.Conectar();
        String BuscarUser = name;

        if (BuscarUser.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese cedula para buscar cliente");
            return; // No hacer búsqueda si está vacío
        }

        DefaultTableModel model = new DefaultTableModel();

        String sql = "SELECT * FROM clientes WHERE nombre LIKE ?;";
        boolean hayDatos= false;
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, "%" + BuscarUser + "%"); // Busca coincidencias parciales en la cédula

            ResultSet rs = pst.executeQuery();

            model.addColumn("N°");
            model.addColumn("Nombre");
            model.addColumn("Cedula");
            model.addColumn("Telefono");
            model.addColumn("Direccion");
            model.addColumn("Correo");

            while (rs.next()) {
                hayDatos = true;
                Object[] fila = new Object[6];
                for (int i = 0; i < 6; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                model.addRow(fila);
            }

            ClientesG.jTable_cliente.setModel(model);

            con.close();
            if(!hayDatos){
                JOptionPane.showMessageDialog(null, "Nombre mal ingresado o cliente no existe");
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar Cliente: " + e);
        }
        
        
    }




}





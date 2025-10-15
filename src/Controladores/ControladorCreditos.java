/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Conexion.Conexionmy;
import Modelos.Creditos;
import Modelos.PagosCr;
import Modelos.Usuario;
import Ventanas.CreditosG;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
//import java.util.Date;

/**
 *
 * @author Bryan
 */
public class ControladorCreditos {
    
    public boolean guardar(Creditos objeto){
        java.sql.Date fechaSQL = new java.sql.Date(objeto.getFechainicio().getTime());
        java.sql.Date fechaFinSQL = new java.sql.Date(objeto.getFechafin().getTime());

        String sql = "INSERT INTO creditos (id_cliente, monto, interes, cuotas, monto_total, valor_cuota, saldo_pendiente, fecha_inicio,fecha_fin, estado, observaciones,tipo_credito) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection cn = Conexionmy.Conectar();
            

             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, objeto.getIdcliente());
            ps.setDouble(2, objeto.getMonto());
            ps.setDouble(3, objeto.getInteres());
            ps.setDouble(4, objeto.getCuotas());
            ps.setDouble(5, objeto.getMontoTotal());
            ps.setDouble(6, objeto.getValorCuota());
            ps.setDouble(7, objeto.getSaldoPendiente());
            ps.setDate(8, fechaSQL);
            ps.setDate(9, fechaFinSQL);
            ps.setString(10, objeto.getEstado());
            ps.setString(11, objeto.getObservaciones());
            ps.setInt(12, objeto.getTipoCredito());
  



            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar credito: " + e.getMessage());
            return false;
        }
    }
    
    
    public boolean actualizarCredito(Creditos objeto, int idCredito) {
        boolean respuesta = false;
        Connection cn = Conexionmy.Conectar();
        
        try {
                   
            com.mysql.jdbc.PreparedStatement consulta = (com.mysql.jdbc.PreparedStatement) cn.prepareStatement("UPDATE creditos SET cuotas=?, saldo_pendiente=? WHERE id_credito=?");
            consulta.setDouble(1, objeto.getCuotas());
            consulta.setDouble(2,objeto.getSaldoPendiente());
            consulta.setInt(3, idCredito);
            
            
            if(consulta.executeUpdate()>0){
                respuesta=true;
            }
            cn.close();


        } catch (SQLException e) {
            System.out.println("error al actualIZAR el pago del credito"+ e);

        }
        return respuesta;

    }
    public boolean actualizarEstado(String estado, int idCredito) {
        boolean respuesta = false;
        Connection cn = Conexionmy.Conectar();
        
        try {
                   
            com.mysql.jdbc.PreparedStatement consulta = (com.mysql.jdbc.PreparedStatement) cn.prepareStatement("UPDATE creditos SET estado=? WHERE id_credito=?");
            consulta.setString(1,estado);
            consulta.setInt(2, idCredito);
            
            
            if(consulta.executeUpdate()>0){
                respuesta=true;
            }
            cn.close();


        } catch (SQLException e) {
            System.out.println("error al actualizar estado"+ e);

        }
        return respuesta;

    }

    
    
    public boolean guardarPago(PagosCr objeto){
        java.sql.Date fechaSQL = new java.sql.Date(objeto.getFechaPago().getTime());
        //java.sql.Date fechaFinSQL = new java.sql.Date(objeto.getFechafin().getTime());

        String sql = "INSERT INTO pagos (id_pago, id_credito, numero_cuota, monto_pagado, fecha_pago,saldo_pendiente, observaciones) VALUES (?, ?, ?, ?, ?, ?,?)";
        try (Connection cn = Conexionmy.Conectar();
            

             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, objeto.getId());
            ps.setInt(2, objeto.getIdCredito());
            ps.setDouble(3, objeto.getNumCuota());
            ps.setDouble(4, objeto.getMontoPago());
            ps.setDate(5,fechaSQL);
            ps.setDouble(6, objeto.getSaldo_pendiente());
            ps.setString(7, objeto.getObservanes());
            
            
            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar Pago: " + e.getMessage());
            return false;
        }
    }
    
    
    
    public void buscarCreditoPorCedula(String cedula) {
        Connection con = Conexionmy.Conectar();
        String BuscarCedula = cedula;
        boolean encontrados = false;

        if (BuscarCedula.isEmpty()) {
            return; // No hacer búsqueda si está vacío
        }

        DefaultTableModel model = new DefaultTableModel();

        String sql = "SELECT c.id_credito, cl.nombre, cl.cedula, c.monto, c.interes, c.cuotas, c.valor_cuota, c.monto_total,c.saldo_pendiente, c.fecha_inicio, c.fecha_fin, c.estado FROM creditos c "
                + " INNER JOIN clientes cl ON c.id_cliente = cl.id_cliente "
                + " WHERE cedula LIKE ?;";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, "%" + BuscarCedula + "%"); // Busca coincidencias parciales en la cédula

            ResultSet rs = pst.executeQuery();

            model.addColumn("N°");
            model.addColumn("Nombre");
            model.addColumn("Cedula");
            model.addColumn("Monto");
            model.addColumn("Interes");
            model.addColumn("Cuotas");
            model.addColumn("Valor cuota");
            model.addColumn("Monto total");
            model.addColumn("Saldo Pendiente");
            model.addColumn("Fecha inicio");
            model.addColumn("Fecha fin");
            model.addColumn("Estado");

            while (rs.next()) {
                
                    Object[] fila = new Object[12];
                    for (int i = 0; i < 12; i++) {
                        fila[i] = rs.getObject(i + 1);
                    }
                    model.addRow(fila);
                    encontrados = true;
                    
            }
            if(!encontrados){JOptionPane.showMessageDialog(null, "Credito no encontrado");}
            else{
                CreditosG.jTable_creditos.setModel(model);
            }
            
            

            con.close();
        } catch (SQLException e) {
            System.out.println("Error al buscar Cliente: " + e);
        }
        
        
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.util.Date;

/**
 *
 * @author Bryan
 */
public class PagosCr {

    public int id;
    public int idCredito;
    public int numCuota;
    public double montoPago;
    public Date fechaPago;
    public String observanes;

    public PagosCr() {
        
        this.id = 0;
        this.idCredito=0;
        this.numCuota=0;
        this.montoPago=0;
        this.fechaPago = new Date();
        this.observanes="";
    }

    
    
    public PagosCr(int id, int idCredito, int numCuota, double montoPago, Date fechaPago, String observanes) {
        this.id = id;
        this.idCredito = idCredito;
        this.numCuota = numCuota;
        this.montoPago = montoPago;
        this.fechaPago = fechaPago;
        this.observanes = observanes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCredito() {
        return idCredito;
    }

    public void setIdCredito(int idCredito) {
        this.idCredito = idCredito;
    }

    public int getNumCuota() {
        return numCuota;
    }

    public void setNumCuota(int numCuota) {
        this.numCuota = numCuota;
    }

    public double getMontoPago() {
        return montoPago;
    }

    public void setMontoPago(double montoPago) {
        this.montoPago = montoPago;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getObservanes() {
        return observanes;
    }

    public void setObservanes(String observanes) {
        this.observanes = observanes;
    }
    
    

}

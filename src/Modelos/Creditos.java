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
public class Creditos {
    private int id;
    private int idcliente;
    private double monto;
    private double interes;
    private int cuotas;
    private double montoTotal;
    private double valorCuota;
    private double saldoPendiente;
    private Date Fechainicio;
    private Date Fechafin;
    private String estado;
    private String observaciones;
    private int tipoCredito;

    public Creditos() {
        this.id = 0;
        this.idcliente = 0;
        this.monto = 0;
        this.interes = 0.0;
        this.cuotas = 0;
        this.montoTotal = 0;
        this.valorCuota = 0;
        this.saldoPendiente = 0;
        this.Fechainicio = new Date();
        this.Fechafin = new Date();
        this.estado= "";
        this.observaciones = "";
        this.tipoCredito = 0;
    }
    public Creditos(int id, double monto, int cuotas, double saldoPendiente, double valor_cuota) {
    this.id = id;
    this.monto = monto;
    this.cuotas = cuotas;
    this.saldoPendiente = saldoPendiente;
    this.valorCuota=valor_cuota;
}
@Override
public String toString() {
    return "Crédito #" + id + " - Monto: " + monto;
}
    
    
    
    public Creditos(int id, int idcliente, double monto, double interes, int cuotas, double montoTotal, double valorCuota, double saldoPendiente, Date Fechainicio, Date Fechafin,String estado, String observaciones, int tipoCredito) {
        this.id = id;
        this.idcliente = idcliente;
        this.monto = monto;
        this.interes = interes;
        this.cuotas = cuotas;
        this.montoTotal = montoTotal;
        this.valorCuota = valorCuota;
        this.saldoPendiente = saldoPendiente;
        this.Fechainicio = Fechainicio;
        this.Fechafin = Fechafin;
        this.estado= estado;
        this.observaciones = observaciones;
        this.tipoCredito = tipoCredito;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getInteres() {
        return interes;
    }

    public void setInteres(double interes) {
        this.interes = interes;
    }

    public int getCuotas() {
        return cuotas;
    }

    public void setCuotas(int cuotas) {
        this.cuotas = cuotas;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public double getValorCuota() {
        return valorCuota;
    }

    public void setValorCuota(double valorCuota) {
        this.valorCuota = valorCuota;
    }

    public double getSaldoPendiente() {
        return saldoPendiente;
    }

    public void setSaldoPendiente(double saldoPendiente) {
        this.saldoPendiente = saldoPendiente;
    }

    public Date getFechainicio() {
        return Fechainicio;
    }

    public void setFechainicio(Date Fechainicio) {
        this.Fechainicio = Fechainicio;
    }

    public Date getFechafin() {
        return Fechafin;
    }

    public void setFechafin(Date Fechafin) {
        this.Fechafin = Fechafin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getTipoCredito() {
        return tipoCredito;
    }

    public void setTipoCredito(int tipoCredito) {
        this.tipoCredito = tipoCredito;
    }

    
    
    
}


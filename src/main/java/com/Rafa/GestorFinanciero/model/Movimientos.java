package com.Rafa.GestorFinanciero.model;

import java.sql.Timestamp;

public class Movimientos {

	private String correo;
	private Timestamp fecha;
	private Double cantidad;
	private String concepto; 
	
	public Movimientos(String correo, Timestamp fecha, Double cantidad, String concepto) {
		super();
		this.correo = correo;
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.concepto = concepto;
	}
	
	public Movimientos(String correo, Timestamp fecha, Double cantidad) {
		super();
		this.correo = correo;
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.concepto = "";
	}
	
	public Movimientos() {
		super();
		this.correo = "";
		this.fecha = null;
		this.cantidad = 0.0;
		this.concepto = "";
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	@Override
	public String toString() {
		return "Movimientos [correo=" + correo + ", fecha=" + fecha + ", cantidad=" + cantidad + ", concepto="
				+ concepto + "]";
	}
	
	
}
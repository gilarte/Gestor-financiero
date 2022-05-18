package com.Rafa.GestorFinanciero.model;

import java.time.LocalDateTime;

public class Movimientos {

	/**
	 * Atributos que va a tener un obeto de tipo Movimiento
	 */
	private int id;
	private String correo;
	private LocalDateTime fecha;
	private Double cantidad;
	private String concepto; 
	
	/**
	 * Constructor de Movimientos con todos sus campos detallados
	 * @param correo
	 * @param fecha
	 * @param cantidad
	 * @param concepto
	 */
	public Movimientos(String correo, LocalDateTime fecha, Double cantidad, String concepto) {
		super();
		this.correo = correo;
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.concepto = concepto;
	}
	
	/**
	 * Constructor de Movimientos sin concepto, ya que es opcional
	 * @param correo
	 * @param fecha
	 * @param cantidad
	 */
	public Movimientos(String correo, LocalDateTime fecha, Double cantidad) {
		super();
		this.correo = correo;
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.concepto = "";
	}
	
	/**
	 * Constructor de Movimientos por defecto
	 */
	public Movimientos() {
		super();
		this.correo = "";
		this.fecha = null;
		this.cantidad = 0.0;
		this.concepto = "";
	}

	
	/**
	 * Getters & Setters
	 * @return
	 */
	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
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
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * toString de Movimientos
	 */
	@Override
	public String toString() {
		return "Movimientos [id=" + id + ", correo=" + correo + ", fecha=" + fecha + ", cantidad=" + cantidad
				+ ", concepto=" + concepto + "]";
	}

	
	
	
}

package com.Rafa.GestorFinanciero.model;

public class Usuario {

	/**
	 * Atributos que va a tener un objeto de tipo Usuario
	 */
	private String correo;
	private String nombre;
	private String contraseña;
	private Double dinero;
	
	/**
	 * Constructor de Usuario con todos sus atributos detallados
	 * @param correo
	 * @param nombre
	 * @param contraseña
	 * @param dinero
	 */
	public Usuario(String correo, String nombre, String contraseña, Double dinero) {
		super();
		this.correo = correo;
		this.nombre = nombre;
		this.contraseña = contraseña;
		this.dinero = dinero;
	}
	
	/**
	 * Constructor de Usuario con valores por defecto
	 */
	public Usuario() {
		super();
		this.correo = "";
		this.nombre = "";
		this.contraseña = "0000";
		this.dinero = 0.0;
	}

	/**
	 * Getters & Setters
	 */
	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public Double getDinero() {
		return dinero;
	}

	public void setDinero(Double dinero) {
		this.dinero = dinero;
	}

	/**
	 * toString de Usuario
	 */
	@Override
	public String toString() {
		return "Usuario [correo=" + correo + ", nombre=" + nombre + ", contraseña=" + contraseña + ", dinero=" + dinero
				+ "]";
	}
	
	
	
}

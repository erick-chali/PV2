package com.imsa.puntoventa.datos;

public class DatosBusquedaClientes {
	
	public DatosBusquedaClientes(String nit, String nombre) {
		super();
		this.nit = nit;
		this.nombre = nombre;
	}
	public DatosBusquedaClientes(){}
	
	private String nit;
	private String nombre;
	public String getNit() {
		return nit;
	}
	public void setNit(String nit) {
		this.nit = nit;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}

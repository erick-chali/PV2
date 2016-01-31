package com.imsa.puntoventa.datos;

public class DatosBodegas {
	
	public DatosBodegas(String codigoBodega, String descripcion) {
		super();
		this.codigoBodega = codigoBodega;
		this.descripcion = descripcion;
	}
	public DatosBodegas(){}
	
	private String codigoBodega;
	private String descripcion;
	public String getCodigoBodega() {
		return codigoBodega;
	}
	public void setCodigoBodega(String codigoBodega) {
		this.codigoBodega = codigoBodega;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}

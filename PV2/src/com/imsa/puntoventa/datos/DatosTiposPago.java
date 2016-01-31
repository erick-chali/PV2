package com.imsa.puntoventa.datos;

public class DatosTiposPago {
	public DatosTiposPago(int idPago, String descripcion, String esCredito) {
		// TODO Auto-generated constructor stub
		this.setIdPago(idPago);
		this.setDescripcion(descripcion);
		this.setEsCredito(esCredito);
		
	}
	public DatosTiposPago() {
		// TODO Auto-generated constructor stub
	}
	private int idPago;
	private String descripcion;
	private String esCredito;
	public int getIdPago() {
		return idPago;
	}
	public void setIdPago(int idPago) {
		this.idPago = idPago;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getEsCredito() {
		return esCredito;
	}
	public void setEsCredito(String esCredito) {
		this.esCredito = esCredito;
	}
	
	
}

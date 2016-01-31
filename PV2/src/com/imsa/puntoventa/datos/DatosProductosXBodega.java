package com.imsa.puntoventa.datos;

public class DatosProductosXBodega {

	
	public DatosProductosXBodega(String codigoProducto, String descripcionProducto, String marca, float precio,
			int disponible, String bodega, String backOrder, String fechaEsperado, int transito, String familia,
			String referencia, String df) {
		super();
		this.codigoProducto = codigoProducto;
		this.descripcionProducto = descripcionProducto;
		Marca = marca;
		this.precio = precio;
		this.disponible = disponible;
		this.bodega = bodega;
		this.backOrder = backOrder;
		this.fechaEsperado = fechaEsperado;
		this.transito = transito;
		this.familia = familia;
		this.referencia = referencia;
		Df = df;
	}
	public DatosProductosXBodega(){}
	
	private String codigoProducto;
	private String descripcionProducto;
	private String Marca;
	private float precio;
	private int disponible;
	private String bodega;
	private String backOrder;
	private String fechaEsperado;
	private int transito;
	private String familia;
	private String referencia;
	private String Df;
	public String getCodigoProducto() {
		return codigoProducto;
	}
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	public String getDescripcionProducto() {
		return descripcionProducto;
	}
	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}
	public String getMarca() {
		return Marca;
	}
	public void setMarca(String marca) {
		Marca = marca;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public int getDisponible() {
		return disponible;
	}
	public void setDisponible(int disponible) {
		this.disponible = disponible;
	}
	public String getBodega() {
		return bodega;
	}
	public void setBodega(String bodega) {
		this.bodega = bodega;
	}
	public String getBackOrder() {
		return backOrder;
	}
	public void setBackOrder(String backOrder) {
		this.backOrder = backOrder;
	}
	public String getFechaEsperado() {
		return fechaEsperado;
	}
	public void setFechaEsperado(String fechaEsperado) {
		this.fechaEsperado = fechaEsperado;
	}
	public int getTransito() {
		return transito;
	}
	public void setTransito(int transito) {
		this.transito = transito;
	}
	public String getFamilia() {
		return familia;
	}
	public void setFamilia(String familia) {
		this.familia = familia;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getDf() {
		return Df;
	}
	public void setDf(String df) {
		Df = df;
	}
	
	
}

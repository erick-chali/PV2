package com.im.puntoventa.datos;

public class DatosAlternos {
	public DatosAlternos(String codigoProducto, String descripcion, int  disponible, String bodega, float precio){
		this.setCodigoProducto(codigoProducto);
		this.setDescripcion(descripcion);
		this.setDisponible(disponible);
		this.setBodega(bodega);
		this.setPrecio(precio);
	}
	public DatosAlternos(){}
	private String codigoProducto;
	private String descripcion;
	private int disponible;
	private String bodega;
	private float precio;
	
	/**
	 * @return the precio
	 */
	public float getPrecio() {
		return precio;
	}
	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	/**
	 * @return the codigoProducto
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}
	/**
	 * @param codigoProducto the codigoProducto to set
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return the disponible
	 */
	public int getDisponible() {
		return disponible;
	}
	/**
	 * @param disponible the disponible to set
	 */
	public void setDisponible(int disponible) {
		this.disponible = disponible;
	}
	/**
	 * @return the bodega
	 */
	public String getBodega() {
		return bodega;
	}
	/**
	 * @param bodega the bodega to set
	 */
	public void setBodega(String bodega) {
		this.bodega = bodega;
	}
	
}

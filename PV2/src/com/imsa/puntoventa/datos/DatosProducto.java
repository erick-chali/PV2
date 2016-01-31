
package com.imsa.puntoventa.datos;

public class DatosProducto {
	
	
	
	public DatosProducto(String codigoProducto, String descripcion, int unidadMedida, String medida, float precio,
			float descuentoMaximo, int disponible, String codigoBodega, float porcentajeDescuentoMin, String esKit,
			int promocion, int correlativoKit, int codigoPromocion, String serieDevProy, String numeroDevProy,
			int ordenCompra) {
		super();
		this.codigoProducto = codigoProducto;
		this.descripcion = descripcion;
		this.unidadMedida = unidadMedida;
		this.medida = medida;
		this.precio = precio;
		this.descuentoMaximo = descuentoMaximo;
		this.disponible = disponible;
		this.codigoBodega = codigoBodega;
		this.porcentajeDescuentoMin = porcentajeDescuentoMin;
		this.esKit = esKit;
		this.promocion = promocion;
		this.correlativoKit = correlativoKit;
		this.codigoPromocion = codigoPromocion;
		this.serieDevProy = serieDevProy;
		this.numeroDevProy = numeroDevProy;
		this.ordenCompra = ordenCompra;
	}
	public DatosProducto(){
	}
	
	private String codigoProducto;
	private String descripcion;
	private int unidadMedida;
	private String medida;
	private float precio;
	private float descuentoMaximo;
	private int disponible;
	private String codigoBodega;
	private float porcentajeDescuentoMin;
	private String esKit;
	private int promocion;
	private int correlativoKit;
	private int codigoPromocion;
	private String serieDevProy;
	private String numeroDevProy;
	private int ordenCompra;
	
	
	public int getPromocion() {
		return promocion;
	}
	public void setPromocion(int promocion) {
		this.promocion = promocion;
	}
	public int getCorrelativoKit() {
		return correlativoKit;
	}
	public void setCorrelativoKit(int correlativoKit) {
		this.correlativoKit = correlativoKit;
	}
	public int getCodigoPromocion() {
		return codigoPromocion;
	}
	public void setCodigoPromocion(int codigoPromocion) {
		this.codigoPromocion = codigoPromocion;
	}
	public String getSerieDevProy() {
		return serieDevProy;
	}
	public void setSerieDevProy(String serieDevProy) {
		this.serieDevProy = serieDevProy;
	}
	public String getNumeroDevProy() {
		return numeroDevProy;
	}
	public void setNumeroDevProy(String numeroDevProy) {
		this.numeroDevProy = numeroDevProy;
	}
	public int getOrdenCompra() {
		return ordenCompra;
	}
	public void setOrdenCompra(int ordenCompra) {
		this.ordenCompra = ordenCompra;
	}
	public String getCodigoProducto() {
		return codigoProducto;
	}
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getUnidadMedida() {
		return unidadMedida;
	}
	public void setUnidadMedida(int unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	public String getMedida() {
		return medida;
	}
	public void setMedida(String medida) {
		this.medida = medida;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public float getDescuentoMaximo() {
		return descuentoMaximo;
	}
	public void setDescuentoMaximo(float descuentoMaximo) {
		this.descuentoMaximo = descuentoMaximo;
	}
	public int getDisponible() {
		return disponible;
	}
	public void setDisponible(int disponible) {
		this.disponible = disponible;
	}
	public String getCodigoBodega() {
		return codigoBodega;
	}
	public void setCodigoBodega(String codigoBodega) {
		this.codigoBodega = codigoBodega;
	}
	public float getPorcentajeDescuentoMin() {
		return porcentajeDescuentoMin;
	}
	public void setPorcentajeDescuentoMin(float porcentajeDescuentoMin) {
		this.porcentajeDescuentoMin = porcentajeDescuentoMin;
	}
	public String getEsKit() {
		return esKit;
	}
	public void setEsKit(String esKit) {
		this.esKit = esKit;
	}
	
	
}

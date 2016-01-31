package com.imsa.puntoventa.datos;

public class DatosDetalleCotizacion {

	public DatosDetalleCotizacion(){}
	
	public DatosDetalleCotizacion(int correlativo, String codigoProducto, String medida, int codigoMedida,
			String descripcionProducto, int cantidad, float precioUnitario, int disponible, float porcentajeDesc,
			float descuento, float totalLinea, String codigoBodega, float porcentajeDescMin, String observaciones,
			String esKit, int correlativoKit, int codigoPromocion, String serieDevProy, int numeroDevProy, int envia,
			String df) {
		super();
		this.correlativo = correlativo;
		this.codigoProducto = codigoProducto;
		this.medida = medida;
		this.codigoMedida = codigoMedida;
		this.descripcionProducto = descripcionProducto;
		this.cantidad = cantidad;
		this.precioUnitario = precioUnitario;
		this.disponible = disponible;
		this.porcentajeDesc = porcentajeDesc;
		this.descuento = descuento;
		this.totalLinea = totalLinea;
		this.codigoBodega = codigoBodega;
		this.porcentajeDescMin = porcentajeDescMin;
		this.observaciones = observaciones;
		this.esKit = esKit;
		this.correlativoKit = correlativoKit;
		this.codigoPromocion = codigoPromocion;
		this.serieDevProy = serieDevProy;
		this.numeroDevProy = numeroDevProy;
		this.envia = envia;
		this.df = df;
	}

	private int correlativo;
	private String codigoProducto;
	private String medida;
	private int codigoMedida;
	private String descripcionProducto;
	private int cantidad;
	private float precioUnitario;
	private int disponible;
	private float porcentajeDesc;
	private float descuento;
	private float totalLinea;
	private String codigoBodega;
	private float porcentajeDescMin;
	private String observaciones;
	private String esKit;
	private int correlativoKit;
	private int codigoPromocion;
	private String serieDevProy;
	private int numeroDevProy;
	private int envia;
	private String df;
	public int getCorrelativo() {
		return correlativo;
	}
	public void setCorrelativo(int correlativo) {
		this.correlativo = correlativo;
	}
	public String getCodigoProducto() {
		return codigoProducto;
	}
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	public String getMedida() {
		return medida;
	}
	public void setMedida(String medida) {
		this.medida = medida;
	}
	public int getCodigoMedida() {
		return codigoMedida;
	}
	public void setCodigoMedida(int codigoMedida) {
		this.codigoMedida = codigoMedida;
	}
	public String getDescripcionProducto() {
		return descripcionProducto;
	}
	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public float getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public int getDisponible() {
		return disponible;
	}
	public void setDisponible(int disponible) {
		this.disponible = disponible;
	}
	public float getPorcentajeDesc() {
		return porcentajeDesc;
	}
	public void setPorcentajeDesc(float porcentajeDesc) {
		this.porcentajeDesc = porcentajeDesc;
	}
	public float getDescuento() {
		return descuento;
	}
	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}
	public float getTotalLinea() {
		return totalLinea;
	}
	public void setTotalLinea(float totalLinea) {
		this.totalLinea = totalLinea;
	}
	public String getCodigoBodega() {
		return codigoBodega;
	}
	public void setCodigoBodega(String codigoBodega) {
		this.codigoBodega = codigoBodega;
	}
	public float getPorcentajeDescMin() {
		return porcentajeDescMin;
	}
	public void setPorcentajeDescMin(float porcentajeDescMin) {
		this.porcentajeDescMin = porcentajeDescMin;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getEsKit() {
		return esKit;
	}
	public void setEsKit(String esKit) {
		this.esKit = esKit;
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
	public int getNumeroDevProy() {
		return numeroDevProy;
	}
	public void setNumeroDevProy(int numeroDevProy) {
		this.numeroDevProy = numeroDevProy;
	}
	public int getEnvia() {
		return envia;
	}
	public void setEnvia(int envia) {
		this.envia = envia;
	}
	public String getDf() {
		return df;
	}
	public void setDf(String df) {
		this.df = df;
	}
	
	
}

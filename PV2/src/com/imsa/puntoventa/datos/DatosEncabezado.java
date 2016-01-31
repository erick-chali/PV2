package com.imsa.puntoventa.datos;

import java.sql.Date;

public class DatosEncabezado {
	
	
	
	public DatosEncabezado(int codigoCliente, String nit, String nombre, String telefono, String tarjeta,
			String direccionFactura, String direccionEnvio, int codigoVendedor, String username, int tipoDocumento,
			int noDocumento, Date fechaVencimiento, int tipoPago, int tipoCredito, String autoriza, Date fechaDocumento,
			float cargosEnvio, float otrosCargos, float montoVenta, float montoTotal, String serieDevolucion,
			int noDocumentoDevolucion, String observaciones, int tipoNota, String caja, Date fechaEntrega,
			int codigoDepartamento, int codigoGenerado, int noConsigna, int codigoMovimientoDevolucion,
			String generaSolicitud, int tipoPagoNC, int tipoCliente, int codigoNegocio, float cantidadDevolver,
			String autorizoDespacho, float saldo, String fVence, String fEntrega, String fDocumento,
			String descripcionDocumento) {
		this.codigoCliente = codigoCliente;
		this.nit = nit;
		this.nombre = nombre;
		this.telefono = telefono;
		this.tarjeta = tarjeta;
		this.direccionFactura = direccionFactura;
		this.direccionEnvio = direccionEnvio;
		this.codigoVendedor = codigoVendedor;
		this.username = username;
		this.tipoDocumento = tipoDocumento;
		this.noDocumento = noDocumento;
		this.fechaVencimiento = fechaVencimiento;
		this.tipoPago = tipoPago;
		this.tipoCredito = tipoCredito;
		this.autoriza = autoriza;
		this.fechaDocumento = fechaDocumento;
		this.cargosEnvio = cargosEnvio;
		this.otrosCargos = otrosCargos;
		this.montoVenta = montoVenta;
		this.montoTotal = montoTotal;
		this.serieDevolucion = serieDevolucion;
		this.noDocumentoDevolucion = noDocumentoDevolucion;
		this.observaciones = observaciones;
		this.tipoNota = tipoNota;
		this.caja = caja;
		this.fechaEntrega = fechaEntrega;
		this.codigoDepartamento = codigoDepartamento;
		this.codigoGenerado = codigoGenerado;
		this.noConsigna = noConsigna;
		this.codigoMovimientoDevolucion = codigoMovimientoDevolucion;
		this.generaSolicitud = generaSolicitud;
		this.tipoPagoNC = tipoPagoNC;
		this.tipoCliente = tipoCliente;
		this.codigoNegocio = codigoNegocio;
		this.cantidadDevolver = cantidadDevolver;
		this.autorizoDespacho = autorizoDespacho;
		this.saldo = saldo;
		this.fVence = fVence;
		this.fEntrega = fEntrega;
		this.fDocumento = fDocumento;
		this.descripcionDocumento = descripcionDocumento;
	}
	public DatosEncabezado(){}
	private int codigoCliente;
	private String nit;
	private String nombre;
	private String telefono;
	private String tarjeta;
	private String direccionFactura;
	private String direccionEnvio;
	private int codigoVendedor;
	private String username;
	private int tipoDocumento;
	private int noDocumento;
	private Date fechaVencimiento;
	private int tipoPago;
	private int tipoCredito;
	private String autoriza;
	private Date fechaDocumento;
	private float cargosEnvio;
	private float otrosCargos;
	private float montoVenta;
	private float montoTotal;
	private String serieDevolucion;
	private int noDocumentoDevolucion;
	private String observaciones;
	private int tipoNota;
	private String caja;
	private Date fechaEntrega;
	private int codigoDepartamento;
	private int codigoGenerado;
	private int noConsigna;
	private int codigoMovimientoDevolucion;
	private String generaSolicitud;
	private int tipoPagoNC;
	private int tipoCliente;
	private int codigoNegocio;
	private float cantidadDevolver;
	private String autorizoDespacho;
	private float saldo;
	private String fVence;
	private String fEntrega;
	private String fDocumento;
	private String descripcionDocumento;
	
	public String getDescripcionDocumento() {
		return descripcionDocumento;
	}
	public void setDescripcionDocumento(String descripcionDocumento) {
		this.descripcionDocumento = descripcionDocumento;
	}
	public String getfVence() {
		return fVence;
	}
	public void setfVence(String fVence) {
		this.fVence = fVence;
	}
	public String getfEntrega() {
		return fEntrega;
	}
	public void setfEntrega(String fEntrega) {
		this.fEntrega = fEntrega;
	}
	public String getfDocumento() {
		return fDocumento;
	}
	public void setfDocumento(String fDocumento) {
		this.fDocumento = fDocumento;
	}
	public int getCodigoCliente() {
		return codigoCliente;
	}
	public void setCodigoCliente(int codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
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
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getTarjeta() {
		return tarjeta;
	}
	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}
	public String getDireccionFactura() {
		return direccionFactura;
	}
	public void setDireccionFactura(String direccionFactura) {
		this.direccionFactura = direccionFactura;
	}
	public String getDireccionEnvio() {
		return direccionEnvio;
	}
	public void setDireccionEnvio(String direccionEnvio) {
		this.direccionEnvio = direccionEnvio;
	}
	public int getCodigoVendedor() {
		return codigoVendedor;
	}
	public void setCodigoVendedor(int codigoVendedor) {
		this.codigoVendedor = codigoVendedor;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(int tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public int getNoDocumento() {
		return noDocumento;
	}
	public void setNoDocumento(int noDocumento) {
		this.noDocumento = noDocumento;
	}
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public int getTipoPago() {
		return tipoPago;
	}
	public void setTipoPago(int tipoPago) {
		this.tipoPago = tipoPago;
	}
	public int getTipoCredito() {
		return tipoCredito;
	}
	public void setTipoCredito(int tipoCredito) {
		this.tipoCredito = tipoCredito;
	}
	public String getAutoriza() {
		return autoriza;
	}
	public void setAutoriza(String autoriza) {
		this.autoriza = autoriza;
	}
	public Date getFechaDocumento() {
		return fechaDocumento;
	}
	public void setFechaDocumento(Date fechaDocumento) {
		this.fechaDocumento = fechaDocumento;
	}
	public float getCargosEnvio() {
		return cargosEnvio;
	}
	public void setCargosEnvio(float cargosEnvio) {
		this.cargosEnvio = cargosEnvio;
	}
	public float getOtrosCargos() {
		return otrosCargos;
	}
	public void setOtrosCargos(float otrosCargos) {
		this.otrosCargos = otrosCargos;
	}
	public float getMontoVenta() {
		return montoVenta;
	}
	public void setMontoVenta(float montoVenta) {
		this.montoVenta = montoVenta;
	}
	public float getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(float montoTotal) {
		this.montoTotal = montoTotal;
	}
	public String getSerieDevolucion() {
		return serieDevolucion;
	}
	public void setSerieDevolucion(String serieDevolucion) {
		this.serieDevolucion = serieDevolucion;
	}
	public int getNoDocumentoDevolucion() {
		return noDocumentoDevolucion;
	}
	public void setNoDocumentoDevolucion(int noDocumentoDevolucion) {
		this.noDocumentoDevolucion = noDocumentoDevolucion;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public int getTipoNota() {
		return tipoNota;
	}
	public void setTipoNota(int tipoNota) {
		this.tipoNota = tipoNota;
	}
	public String getCaja() {
		return caja;
	}
	public void setCaja(String caja) {
		this.caja = caja;
	}
	public Date getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	public int getCodigoDepartamento() {
		return codigoDepartamento;
	}
	public void setCodigoDepartamento(int codigoDepartamento) {
		this.codigoDepartamento = codigoDepartamento;
	}
	public int getCodigoGenerado() {
		return codigoGenerado;
	}
	public void setCodigoGenerado(int codigoGenerado) {
		this.codigoGenerado = codigoGenerado;
	}
	public int getNoConsigna() {
		return noConsigna;
	}
	public void setNoConsigna(int noConsigna) {
		this.noConsigna = noConsigna;
	}
	public int getCodigoMovimientoDevolucion() {
		return codigoMovimientoDevolucion;
	}
	public void setCodigoMovimientoDevolucion(int codigoMovimientoDevolucion) {
		this.codigoMovimientoDevolucion = codigoMovimientoDevolucion;
	}
	public String getGeneraSolicitud() {
		return generaSolicitud;
	}
	public void setGeneraSolicitud(String generaSolicitud) {
		this.generaSolicitud = generaSolicitud;
	}
	public int getTipoPagoNC() {
		return tipoPagoNC;
	}
	public void setTipoPagoNC(int tipoPagoNC) {
		this.tipoPagoNC = tipoPagoNC;
	}
	public int getTipoCliente() {
		return tipoCliente;
	}
	public void setTipoCliente(int tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	public int getCodigoNegocio() {
		return codigoNegocio;
	}
	public void setCodigoNegocio(int codigoNegocio) {
		this.codigoNegocio = codigoNegocio;
	}
	public float getCantidadDevolver() {
		return cantidadDevolver;
	}
	public void setCantidadDevolver(float cantidadDevolver) {
		this.cantidadDevolver = cantidadDevolver;
	}
	public String getAutorizoDespacho() {
		return autorizoDespacho;
	}
	public void setAutorizoDespacho(String autorizoDespacho) {
		this.autorizoDespacho = autorizoDespacho;
	}
	public float getSaldo() {
		return saldo;
	}
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	
	
}

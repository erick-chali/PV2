package com.imsa.puntoventa.datos;

public class DatosGrabarEncabezado {
	
	public DatosGrabarEncabezado(){}
	
	public DatosGrabarEncabezado(int codigoCliente, String nit, String nombreCliente, String direccionFactura,
			String telefono, String tarjeta, String direccionEnvio, int codigoVendedor, String usuario,
			int tipoDocumento, String noDocumento, String fechaVence, int tipoPago, int tipoCredito, String autoriza,
			String fechaDocumento, float cargosEnvio, float otrosCargos, float montoVenta, float montoTotal,
			String serieDev, int numeroDocDev, String observaciones, int tipoNota, String caja, String fechaEntrega,
			String codigoDept, String codGen, String noConsigna, int codMovDev, String generaSolicitud, int tipoPagoNC,
			int tipoCliente, String codigoNegocio, float cantidadDevolver, String autorizoDespacho, float saldo) {
		super();
		this.codigoCliente = codigoCliente;
		this.nit = nit;
		this.nombreCliente = nombreCliente;
		this.direccionFactura = direccionFactura;
		this.telefono = telefono;
		this.tarjeta = tarjeta;
		this.direccionEnvio = direccionEnvio;
		this.codigoVendedor = codigoVendedor;
		this.usuario = usuario;
		this.tipoDocumento = tipoDocumento;
		this.noDocumento = noDocumento;
		this.fechaVence = fechaVence;
		this.tipoPago = tipoPago;
		this.tipoCredito = tipoCredito;
		this.autoriza = autoriza;
		this.fechaDocumento = fechaDocumento;
		this.cargosEnvio = cargosEnvio;
		this.otrosCargos = otrosCargos;
		this.montoVenta = montoVenta;
		this.montoTotal = montoTotal;
		this.serieDev = serieDev;
		this.numeroDocDev = numeroDocDev;
		this.observaciones = observaciones;
		this.tipoNota = tipoNota;
		this.caja = caja;
		this.fechaEntrega = fechaEntrega;
		this.codigoDept = codigoDept;
		this.codGen = codGen;
		this.noConsigna = noConsigna;
		this.codMovDev = codMovDev;
		this.generaSolicitud = generaSolicitud;
		this.tipoPagoNC = tipoPagoNC;
		this.tipoCliente = tipoCliente;
		this.codigoNegocio = codigoNegocio;
		this.cantidadDevolver = cantidadDevolver;
		this.autorizoDespacho = autorizoDespacho;
		this.saldo = saldo;
	}

	private int codigoCliente;
	private String nit;
	private String nombreCliente;
	private String direccionFactura;
	private String telefono;
	private String tarjeta;
	private String direccionEnvio;
	private int codigoVendedor;
	private String usuario;
	private int tipoDocumento;
	private String noDocumento;
	private String fechaVence;
	private int tipoPago;
	private int tipoCredito;
	private String autoriza;
	private String fechaDocumento;
	private float cargosEnvio;
	private float otrosCargos;
	private float montoVenta;
	private float montoTotal;
	private String serieDev;
	private int numeroDocDev;
	private String observaciones;
	private int tipoNota;
	private String caja;
	private String fechaEntrega;
	private String codigoDept;
	private String codGen;
	private String noConsigna;
	private int codMovDev;
	private String generaSolicitud;
	private int tipoPagoNC;
	private int tipoCliente;
	private String codigoNegocio;
	private float cantidadDevolver;
	private String autorizoDespacho;
	private float saldo;
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
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getDireccionFactura() {
		return direccionFactura;
	}
	public void setDireccionFactura(String direccionFactura) {
		this.direccionFactura = direccionFactura;
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
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public int getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(int tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getNoDocumento() {
		return noDocumento;
	}
	public void setNoDocumento(String noDocumento) {
		this.noDocumento = noDocumento;
	}
	public String getFechaVence() {
		return fechaVence;
	}
	public void setFechaVence(String fechaVence) {
		this.fechaVence = fechaVence;
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
	public String getFechaDocumento() {
		return fechaDocumento;
	}
	public void setFechaDocumento(String fechaDocumento) {
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
	public String getSerieDev() {
		return serieDev;
	}
	public void setSerieDev(String serieDev) {
		this.serieDev = serieDev;
	}
	public int getNumeroDocDev() {
		return numeroDocDev;
	}
	public void setNumeroDocDev(int numeroDocDev) {
		this.numeroDocDev = numeroDocDev;
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
	public String getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(String fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	public String getCodigoDept() {
		return codigoDept;
	}
	public void setCodigoDept(String codigoDept) {
		this.codigoDept = codigoDept;
	}
	public String getCodGen() {
		return codGen;
	}
	public void setCodGen(String codGen) {
		this.codGen = codGen;
	}
	public String getNoConsigna() {
		return noConsigna;
	}
	public void setNoConsigna(String noConsigna) {
		this.noConsigna = noConsigna;
	}
	public int getCodMovDev() {
		return codMovDev;
	}
	public void setCodMovDev(int codMovDev) {
		this.codMovDev = codMovDev;
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
	public String getCodigoNegocio() {
		return codigoNegocio;
	}
	public void setCodigoNegocio(String codigoNegocio) {
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

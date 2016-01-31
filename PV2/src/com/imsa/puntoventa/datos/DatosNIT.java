package com.imsa.puntoventa.datos;

public class DatosNIT {
	
	public DatosNIT(int codigoCliente, String nombreCliente, String direccionFacturacion, String direccionEnvio,
			String tarjeta, String telefono, int tipoLimite, int codigoGrupo, float limiteCredito, float saldo,
			String cc, int diasCredito, int profesion, String descripcionProfesion, int tipoCliente) {
		super();
		this.codigoCliente = codigoCliente;
		this.nombreCliente = nombreCliente;
		this.direccionFacturacion = direccionFacturacion;
		this.direccionEnvio = direccionEnvio;
		this.tarjeta = tarjeta;
		this.telefono = telefono;
		this.tipoLimite = tipoLimite;
		this.codigoGrupo = codigoGrupo;
		this.limiteCredito = limiteCredito;
		this.saldo = saldo;
		this.cc = cc;
		this.diasCredito = diasCredito;
		this.profesion = profesion;
		this.descripcionProfesion = descripcionProfesion;
		this.tipoCliente = tipoCliente;
	}
	
	public DatosNIT(){}
	private int codigoCliente;
	private String nombreCliente;
	private String direccionFacturacion;
	private String direccionEnvio;
	private String tarjeta;
	private String telefono;
	private int tipoLimite;
	private int codigoGrupo;
	private float limiteCredito;
	private float saldo;
	private String cc;
	private int diasCredito;
	private int profesion;
	private String descripcionProfesion;
	private int tipoCliente;
	public int getCodigoCliente() {
		return codigoCliente;
	}
	public void setCodigoCliente(int codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getDireccionFacturacion() {
		return direccionFacturacion;
	}
	public void setDireccionFacturacion(String direccionFacturacion) {
		this.direccionFacturacion = direccionFacturacion;
	}
	public String getDireccionEnvio() {
		return direccionEnvio;
	}
	public void setDireccionEnvio(String direccionEnvio) {
		this.direccionEnvio = direccionEnvio;
	}
	public String getTarjeta() {
		return tarjeta;
	}
	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public int getTipoLimite() {
		return tipoLimite;
	}
	public void setTipoLimite(int tipoLimite) {
		this.tipoLimite = tipoLimite;
	}
	public int getCodigoGrupo() {
		return codigoGrupo;
	}
	public void setCodigoGrupo(int codigoGrupo) {
		this.codigoGrupo = codigoGrupo;
	}
	public float getLimiteCredito() {
		return limiteCredito;
	}
	public void setLimiteCredito(float limiteCredito) {
		this.limiteCredito = limiteCredito;
	}
	public float getSaldo() {
		return saldo;
	}
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public int getDiasCredito() {
		return diasCredito;
	}
	public void setDiasCredito(int diasCredito) {
		this.diasCredito = diasCredito;
	}
	public int getProfesion() {
		return profesion;
	}
	public void setProfesion(int profesion) {
		this.profesion = profesion;
	}
	public String getDescripcionProfesion() {
		return descripcionProfesion;
	}
	public void setDescripcionProfesion(String descripcionProfesion) {
		this.descripcionProfesion = descripcionProfesion;
	}
	public int getTipoCliente() {
		return tipoCliente;
	}
	public void setTipoCliente(int tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	
}

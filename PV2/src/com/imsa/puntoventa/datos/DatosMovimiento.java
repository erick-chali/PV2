package com.imsa.puntoventa.datos;

import java.sql.Date;

public class DatosMovimiento {
	
	
	
	public DatosMovimiento(int codigoMovimiento, String movimiento, int tipoMovimiento, Date fechaVence,
			String fechaFormateada) {
		this.codigoMovimiento = codigoMovimiento;
		this.movimiento = movimiento;
		this.tipoMovimiento = tipoMovimiento;
		this.fechaVence = fechaVence;
		this.fechaFormateada = fechaFormateada;
	}
	public DatosMovimiento(){}
	private int codigoMovimiento;
	private String movimiento;
	private int tipoMovimiento;
	private Date fechaVence;
	private String fechaFormateada;
	
	public String getFechaFormateada() {
		return fechaFormateada;
	}
	public void setFechaFormateada(String fechaFormateada) {
		this.fechaFormateada = fechaFormateada;
	}
	public Date getFechaVence() {
		return fechaVence;
	}
	public void setFechaVence(Date fechaVence) {
		this.fechaVence = fechaVence;
	}
	public int getTipoMovimiento() {
		return tipoMovimiento;
	}
	public void setTipoMovimiento(int tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}
	public int getCodigoMovimiento() {
		return codigoMovimiento;
	}
	public void setCodigoMovimiento(int codigoMovimiento) {
		this.codigoMovimiento = codigoMovimiento;
	}
	public String getMovimiento() {
		return movimiento;
	}
	public void setMovimiento(String movimiento) {
		this.movimiento = movimiento;
	}
	
	
}

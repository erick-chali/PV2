package com.im.puntoventa.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.im.puntoventa.conexion.ConectarDB;
import com.im.puntoventa.datos.DatosEnc;

public class CargarDatosEncabezado {
	
	
	public static ArrayList<DatosEnc> obtenerEncabezado(int tipoDoc, String serie, int numDocumento){
		Connection con = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		ArrayList<DatosEnc> listado = new ArrayList<DatosEnc>();
		DatosEnc datos = null;
		try{
			con = new ConectarDB().getConnection();
			stmt = con.prepareCall("{call stp_UDPV_LookUp_Mov_Enc(?,?,?)}");
			stmt.setInt(1, tipoDoc);
			stmt.setString(2, null);
			stmt.setInt(3, numDocumento);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				datos = new DatosEnc();
				datos.setTipoPago(rs.getString("codigo_pago"));
				datos.setTipoCredito(rs.getString("tipo_credito"));
				datos.setFechaVence(rs.getString("vence"));
				datos.setNit(rs.getString("nit"));
				datos.setDirecFactura(rs.getString("direccion"));
				datos.setCodigoCliente(rs.getString("cliente"));
				datos.setAutoriza(rs.getString("autoriza"));
				datos.setMontoTotal(rs.getString("total"));
				datos.setMontoVenta(rs.getString("subtotal"));
				datos.setObservaciones(rs.getString("observaciones"));
				datos.setTipoDocumento(rs.getString("codigo_movimiento"));
				datos.setAutorizoDespacho(rs.getString("usuarioaut"));
				datos.setNombreCliente(rs.getString("nombre"));
				listado.add(datos);
			}
			con.close();
			stmt.close();
			rs.close();
			
		}catch(SQLException e){
			System.out.println("Error al cargar encabezado: " + e.getMessage());
		}
		return listado;
	}
}

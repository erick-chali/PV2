package com.im.puntoventa.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.im.puntoventa.conexion.ConectarDB;
import com.im.puntoventa.datos.DatosDet;

public class CargarDatosDet {
	
	
	public static ArrayList<DatosDet> obtenerDetalles(int tipoDocumento, String serie, int numDocumento){
		
		Connection con = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		ArrayList<DatosDet> listado = new ArrayList<DatosDet>();
		DatosDet datos = null;
		
		try{
			con = new ConectarDB().getConnection();
			stmt = con.prepareCall("{call stp_UDPV_LookUp_Mov_Det_New(?,?,?)}");
			stmt.setInt(1, tipoDocumento);
			stmt.setString(2, serie);
			stmt.setInt(3, numDocumento);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				datos = new DatosDet();
				datos.setCorrelativo(rs.getString("correlativo"));
				datos.setCodigoProducto(rs.getString("codigo_producto"));
				datos.setuMedida(rs.getString("Medida"));
				datos.setDescripcion(rs.getString("Descripcion"));
				datos.setCantidad(rs.getString("cantidad"));
				datos.setDisponible(rs.getString("Disponible"));
				datos.setPrecio(rs.getString("PrecioU"));
				datos.setPorDescuento(rs.getString("por_Descuento"));
				datos.setDescuento(rs.getString("Descuento"));
				datos.setTotal(rs.getString("total_linea"));
				datos.setBodega(rs.getString("bodega"));
				datos.setEnvia(rs.getString("envia"));
				datos.setKit(rs.getString("es_kit"));
				datos.setObservaciones(rs.getString("observaciones"));
				listado.add(datos);
			}
		}catch(SQLException e){
			System.out.println("Error al traer detalle: " + e.getMessage());
		}
		return listado;
	}
}

package com.imsa.puntoventa.servlet;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.im.puntoventa.conexion.ConectarDB;
import com.imsa.puntoventa.datos.DatosEncabezado;

/**
 * Servlet implementation class CargaEncabezadoCotizaciones
 */
@WebServlet("/CargaEncabezadoCotizaciones")
public class CargaEncabezadoCotizaciones extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CargaEncabezadoCotizaciones() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Connection con = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		ArrayList<DatosEncabezado> datosEncabezado = new ArrayList<DatosEncabezado>();
		try{
			con = new ConectarDB().getConnection();
			stmt = con.prepareCall("{call stp_UDPV_LookUp_Cotizaciones_New(?)}");
			stmt.setInt(1, Integer.parseInt((String)request.getSession().getAttribute("codSucursal")));
			rs = stmt.executeQuery();
			
			while(rs.next()){
				DatosEncabezado dato = new DatosEncabezado();
				dato.setNoDocumento(rs.getInt("no_cotizacion"));
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				dato.setfVence(formato.format(rs.getDate("Fecha")));
				dato.setNit(rs.getString("Nit"));
				dato.setNombre(rs.getString("Nombre"));
				dato.setMontoTotal(rs.getFloat("Monto"));
				datosEncabezado.add(dato);
				
			}
			rs.close();
			stmt.close();
			con.close();
		}catch(SQLException e){
			response.setContentType("application/json");
			response.getWriter().print(e.getMessage());
		}
		Gson gson = new Gson();
		JsonElement elemento = gson.toJsonTree(datosEncabezado, new TypeToken<List<DatosEncabezado>>(){}.getType());
		JsonArray arreglo = elemento.getAsJsonArray();
		datosEncabezado = null;
		response.setContentType("application/json");
		
		response.getWriter().print(arreglo);
	}

}

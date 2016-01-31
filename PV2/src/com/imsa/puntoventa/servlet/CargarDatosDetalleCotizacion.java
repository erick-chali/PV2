package com.imsa.puntoventa.servlet;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import com.imsa.puntoventa.datos.DatosDetalleCotizacion;

/**
 * Servlet implementation class CargarDatosDetalleCotizacion
 */
@WebServlet("/CargarDatosDetalleCotizacion")
public class CargarDatosDetalleCotizacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CargarDatosDetalleCotizacion() {
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
		request.getSession().setAttribute("numeroCotizacion", request.getParameter("numeroCotizacion"));
		Connection con = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		ArrayList<DatosDetalleCotizacion> datos = new ArrayList<>();
		try{
			con = new ConectarDB().getConnection();
			stmt = con.prepareCall("{call stp_UDPV_LookUp_Mov_Det_New(?,?,?)}");
			stmt.setInt(1, 1);
			stmt.setString(2, null);
			stmt.setInt(3, Integer.parseInt((String)request.getSession().getAttribute("numeroCotizacion")));
			
			rs = stmt.executeQuery();
			while(rs.next()){
				DatosDetalleCotizacion detalle = new DatosDetalleCotizacion();
				detalle.setCorrelativo(rs.getInt("correlativo"));
				detalle.setCodigoProducto(rs.getString("codigo_producto"));
				detalle.setCodigoMedida(rs.getInt("Unidad_Medida"));
				detalle.setMedida(rs.getString("Medida"));
				detalle.setDescripcionProducto(rs.getString("Descripcion"));
				detalle.setCantidad(rs.getInt("cantidad"));
				detalle.setDisponible(rs.getInt("Disponible"));
				detalle.setPrecioUnitario(rs.getFloat("PrecioU"));
				detalle.setPorcentajeDesc(rs.getFloat("por_Descuento"));
				detalle.setDescuento(rs.getFloat("Descuento"));
				detalle.setTotalLinea(rs.getFloat("total_linea"));
				detalle.setCodigoBodega(rs.getString("bodega"));
				detalle.setPorcentajeDescMin(rs.getFloat("por_descmin"));
				detalle.setObservaciones(rs.getString("observaciones"));
				detalle.setEsKit(rs.getString("es_kit"));
				detalle.setCorrelativoKit(rs.getInt("CorrKit"));
				detalle.setCodigoPromocion(rs.getInt("codProm"));
				detalle.setSerieDevProy(rs.getString("SerieDevProy"));
				detalle.setNumeroDevProy(rs.getInt("NumeroDevProy"));
				detalle.setEnvia(rs.getInt("envia"));
				detalle.setDf(rs.getString("Df"));
				datos.add(detalle);
			}
		}catch(SQLException e){
			response.setContentType("application/json");
			response.getWriter().print(e.getMessage());
		}finally{
			if(con != null || stmt != null || rs != null){
				try {
					con.close();
					stmt.close();
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					response.setContentType("application/json");
					response.getWriter().print(e.getMessage());
				}
			}
		}
		
		Gson gson = new Gson();
		JsonElement elemento = gson.toJsonTree(datos, new TypeToken<List<DatosDetalleCotizacion>>(){}.getType());
		JsonArray arreglo = elemento.getAsJsonArray();
		datos = null;
		response.setContentType("application/json");
		
		response.getWriter().print(arreglo);
	}

}

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
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.im.puntoventa.conexion.ConectarDB;
import com.imsa.puntoventa.datos.DatosProducto;

/**
 * Servlet implementation class GrabarDetalle
 */
@WebServlet("/GrabarDetalle")
public class GrabarDetalle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GrabarDetalle() {
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
//		request.getSession().setAttribute("serieDocumento", request.getParameter("serieDocumento"));
		request.getSession().setAttribute("detallesProductos", request.getParameter("datosDetallesProducto"));
		JsonParser parser = new JsonParser();
		JsonElement arreglo = parser.parse((String) request.getSession().getAttribute("detallesProductos"));
		
		int numeroRegistros = arreglo.getAsJsonArray().size();
		ArrayList<DatosProducto> datosProductos = new ArrayList<>();
		try{
			for(int x = 0 ; x < numeroRegistros ; x++){
				con = new ConectarDB().getConnection();
				stmt = con.prepareCall("{call stp_UDPV_InUp_Mov_Det_New(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
				stmt.setInt(1, Integer.parseInt((String)request.getSession().getAttribute("tipoDocumento")));
				stmt.setString(2, (String)request.getSession().getAttribute("serieDocumento"));
				stmt.setString(3, (String)request.getSession().getAttribute("numeroDocumento"));
				stmt.setInt(4, (x+1));
				stmt.setString(5, arreglo.getAsJsonArray().get(x).getAsJsonObject().get("codigoProducto").getAsString());
				stmt.setString(6, arreglo.getAsJsonArray().get(x).getAsJsonObject().get("unidadMedida").getAsString());
				stmt.setBigDecimal(7, arreglo.getAsJsonArray().get(x).getAsJsonObject().get("cantidad").getAsBigDecimal());
				stmt.setBigDecimal(8, arreglo.getAsJsonArray().get(x).getAsJsonObject().get("precio").getAsBigDecimal());
				stmt.setBigDecimal(9, arreglo.getAsJsonArray().get(x).getAsJsonObject().get("descuento").getAsBigDecimal());
				stmt.setBigDecimal(10, arreglo.getAsJsonArray().get(x).getAsJsonObject().get("descuentoMaximo").getAsBigDecimal());
				stmt.setBigDecimal(11, arreglo.getAsJsonArray().get(x).getAsJsonObject().get("importe").getAsBigDecimal());
				stmt.setInt(12, Integer.parseInt((String)request.getSession().getAttribute("codigoCliente")));
				stmt.setInt(13, arreglo.getAsJsonArray().get(x).getAsJsonObject().get("promocion").getAsInt());
				stmt.setString(14, arreglo.getAsJsonArray().get(x).getAsJsonObject().get("bodega").getAsString());
				if(arreglo.getAsJsonArray().get(x).getAsJsonObject().get("envia").getAsBoolean()){
					stmt.setInt(15, 1);
				}else{
					stmt.setInt(15, 0);
				}
				stmt.setString(16, arreglo.getAsJsonArray().get(x).getAsJsonObject().get("observaciones").getAsString());
				stmt.setInt(17, 1);//codigo lista, debido a que solo se maneja una lista de precios hasta el momento se dejo estatico.
				stmt.setInt(18, Integer.parseInt((String)request.getSession().getAttribute("codigoPago")));
				stmt.setString(19, arreglo.getAsJsonArray().get(x).getAsJsonObject().get("esKit").getAsString());
				stmt.setInt(20, arreglo.getAsJsonArray().get(x).getAsJsonObject().get("correlativoKit").getAsInt());
				stmt.setInt(21, arreglo.getAsJsonArray().get(x).getAsJsonObject().get("codigoPromocion").getAsInt());
//				stmt.setString(22, arreglo.getAsJsonArray().get(x).getAsJsonObject().get("serieDevProy").getAsString());
//				stmt.setString(23, arreglo.getAsJsonArray().get(x).getAsJsonObject().get("numeroDevProy").getAsString());
				stmt.setString(22, null);
				stmt.setString(23, null);
				stmt.setInt(24, arreglo.getAsJsonArray().get(x).getAsJsonObject().get("ordenCompra").getAsInt());
				rs = stmt.executeQuery();
				while(rs.next()){
					if(rs.getString("error").equals("0")){
						DatosProducto datos = new DatosProducto();
						datos.setCodigoProducto(arreglo.getAsJsonArray().get(x).getAsJsonObject().get("codigoProducto").getAsString());
						datosProductos.add(datos);
					}
				}
			}
		}catch(SQLException e){
			response.setContentType("application/json");
			response.getWriter().print(e.getMessage());
		}finally{
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					response.setContentType("application/json");
					response.getWriter().print(e.getMessage());
				}
			}
			if(stmt != null){
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					response.setContentType("application/json");
					response.getWriter().print(e.getMessage());
				}
			}
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					response.setContentType("application/json");
					response.getWriter().print(e.getMessage());
				}
			}
		}
		Gson gson = new Gson();
		JsonElement elemento = gson.toJsonTree(datosProductos, new TypeToken<List<DatosProducto>>(){}.getType());
		JsonArray arreglo2 = elemento.getAsJsonArray();
		datosProductos = null;
		response.setContentType("application/json");
		
		response.getWriter().print(arreglo2);
	}

}

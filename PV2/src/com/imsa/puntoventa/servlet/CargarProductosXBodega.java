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
import com.imsa.puntoventa.datos.DatosProductosXBodega;

/**
 * Servlet implementation class CargarProductosXBodega
 */
@WebServlet("/CargarProductosXBodega")
public class CargarProductosXBodega extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CargarProductosXBodega() {
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
		request.getSession().setAttribute("codigoProducto", request.getParameter("codigoProducto"));
		request.getSession().setAttribute("codigoPago", request.getParameter("codigoPago"));
		request.getSession().setAttribute("codigoLista", request.getParameter("codigoLista"));
		
		Connection con = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		ArrayList<DatosProductosXBodega> productos = new ArrayList<>();
		try{
			con = new ConectarDB().getConnection();
			stmt = con.prepareCall("{call stp_UDPV_Lookup_ProductosXBodega_New(?,?,?)}");
			stmt.setInt(1, Integer.parseInt((String)request.getSession().getAttribute("codigoLista")));
			stmt.setInt(2, Integer.parseInt((String)request.getSession().getAttribute("codigoPago")));
			stmt.setString(3, (String)request.getSession().getAttribute("codigoProducto"));
			rs = stmt.executeQuery();
			while(rs.next()){
				DatosProductosXBodega datos = new DatosProductosXBodega();
				datos.setCodigoProducto(rs.getString("Codigo"));
				datos.setDescripcionProducto(rs.getString("Descripcion"));
				datos.setMarca(rs.getString("Marca"));
				datos.setPrecio(rs.getFloat("PrecioU"));
				datos.setDisponible(rs.getInt("Disponible"));
				datos.setBodega(rs.getString("Bodega"));
				datos.setBackOrder(rs.getString("Backorder"));
				datos.setFechaEsperado(rs.getString("Fecha Esperado"));
				datos.setTransito(rs.getInt("Transito"));
				datos.setFamilia(rs.getString("Familia"));
				datos.setReferencia(rs.getString("Referencia"));
				datos.setDf(rs.getString("Df"));
				productos.add(datos);
			}
			con.close();
			rs.close();
			stmt.close();
		}catch(SQLException e){
			response.setContentType("application/json");
			response.getWriter().print(e.getMessage());
		}
		Gson gson = new Gson();
		JsonElement elemento = gson.toJsonTree(productos, new TypeToken<List<DatosProductosXBodega>>(){}.getType());
		JsonArray arreglo = elemento.getAsJsonArray();
		productos = null;
		response.setContentType("application/json");
		
		response.getWriter().print(arreglo);
	}

}

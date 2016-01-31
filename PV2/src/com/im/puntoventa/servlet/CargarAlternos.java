package com.im.puntoventa.servlet;

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
import com.im.puntoventa.datos.DatosAlternos;


/**
 * Servlet implementation class CargarAlternos
 */
@WebServlet("/CargarAlternos")
public class CargarAlternos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CargarAlternos() {
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
		
		request.getSession().setAttribute("lista", request.getParameter("lista"));
		request.getSession().setAttribute("pago", request.getParameter("pago"));
		request.getSession().setAttribute("codigoProducto", request.getParameter("codigoProducto"));
		ArrayList<DatosAlternos> listaAlternos = new ArrayList<>();
		
		try {
			con = new ConectarDB().getConnection();
			stmt = con.prepareCall("{call stp_UDPV_Lookup_ProductosAlternos(?,?,?)}");
			stmt.setString(1, (String) request.getSession().getAttribute("lista"));
			stmt.setString(2, (String) request.getSession().getAttribute("pago"));
			stmt.setString(3, (String) request.getSession().getAttribute("codigoProducto"));
			rs = stmt.executeQuery();
			while(rs.next()){
				DatosAlternos datos = new DatosAlternos();
				datos.setCodigoProducto(rs.getString("Codigo"));
				datos.setDescripcion(rs.getString("Descripcion"));
				datos.setBodega(rs.getString("Bodega"));
				datos.setDisponible(rs.getInt("Disponible"));
				datos.setPrecio(rs.getFloat("PrecioU"));
				listaAlternos.add(datos);
			}
			con.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			response.setContentType("text/html");
			response.getWriter().write("Error: " + e.getMessage());
		}
		Gson gson = new Gson();
		JsonElement elemento = gson.toJsonTree(listaAlternos, new TypeToken<List<DatosAlternos>>(){}.getType());
		JsonArray arreglo = elemento.getAsJsonArray();
		response.setContentType("application/json");
		listaAlternos = null;
		response.getWriter().print(arreglo);
	}

}

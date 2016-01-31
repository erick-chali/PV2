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
import com.imsa.puntoventa.datos.DatosProducto;

/**
 * Servlet implementation class CargarDatosProducto
 */
@WebServlet("/CargarDatosProducto")
public class CargarDatosProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CargarDatosProducto() {
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
		request.getSession().setAttribute("codigoBodegaAlternativa", request.getParameter("codigoBodega"));
		Connection con = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		ArrayList<DatosProducto> datosProducto = new ArrayList<DatosProducto>();
		
		try{
			con = new ConectarDB().getConnection();
			stmt = con.prepareCall("{call stp_obtenerMedida_New(?,?)}");
			stmt.setString(1, (String)request.getSession().getAttribute("codigoProducto"));
			stmt.setInt(2, Integer.parseInt((String)request.getSession().getAttribute("codigoLista")));
			rs = stmt.executeQuery();
			while(rs.next()){
				request.getSession().setAttribute("unidadMedida", rs.getString("unidad_medida"));
			}
		}catch(SQLException e){
			response.setContentType("json/application");
			response.getWriter().write(e.getMessage());
		}
		
		try{
			con = new ConectarDB().getConnection();
			stmt = con.prepareCall("{call stp_UDPV_Get_Producto_New(?,?,?,?,?,?,?)}");
			stmt.setInt(1, Integer.parseInt((String)request.getSession().getAttribute("codigoLista")));
			stmt.setString(2, (String)request.getSession().getAttribute("codigoProducto"));
			stmt.setString(3, (String)request.getSession().getAttribute("unidadMedida"));
			if((String)request.getSession().getAttribute("codigoBodegaAlternativa") == "" || (String)request.getSession().getAttribute("codigoBodegaAlternativa") == null){
				stmt.setString(4,(String)request.getSession().getAttribute("codigoBodegaLocal"));
			}else{
				stmt.setString(4, (String)request.getSession().getAttribute("codigoBodegaAlternativa"));
			}
			
			stmt.setInt(5, Integer.parseInt((String)request.getSession().getAttribute("codigoPago")));
			stmt.setString(6, null);
			stmt.setString(7, null);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				DatosProducto dato = new DatosProducto();
				dato.setCodigoProducto(rs.getString("Codigo_Producto"));
				dato.setDescripcion(rs.getString("Descripcion"));
				dato.setUnidadMedida(rs.getInt("Unidad_Medida"));
				dato.setMedida(rs.getString("Medida"));
				dato.setPrecio(rs.getFloat("PrecioU"));
				dato.setDescuentoMaximo(rs.getFloat("Descuento"));
				dato.setDisponible(rs.getInt("Disponible"));
				dato.setCodigoBodega(rs.getString("codigo_bodega"));
				dato.setPorcentajeDescuentoMin(rs.getFloat("por_descmin"));
				dato.setEsKit(rs.getString("es_kit"));
				dato.setPromocion(0);
				dato.setCorrelativoKit(0);
				dato.setCodigoPromocion(0);
				dato.setOrdenCompra(0);
				datosProducto.add(dato);
				
			}
		}catch(SQLException e){
			response.setContentType("json/application");
			response.getWriter().write(e.getMessage());
		}
		Gson gson = new Gson();
		JsonElement elemento = gson.toJsonTree(datosProducto, new TypeToken<List<DatosProducto>>(){}.getType());
		JsonArray arreglo = elemento.getAsJsonArray();
		datosProducto = null;
		response.setContentType("application/json");
		
		response.getWriter().print(arreglo);
	}

}

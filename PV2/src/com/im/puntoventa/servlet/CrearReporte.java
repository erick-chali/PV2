package com.im.puntoventa.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.im.puntoventa.dao.GenerarReporte;
import com.imsa.puntoventa.datos.DatosRespuestasVarias;

/**
 * Servlet implementation class CrearReporte
 */
@WebServlet("/CrearReporte")
public class CrearReporte extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrearReporte() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getSession().setAttribute("tipo_doc", request.getParameter("tipoDoc"));
		request.getSession().setAttribute("no_doc", request.getParameter("noDoc"));
		request.getSession().setAttribute("serie_doc", request.getParameter("serie"));
		request.getSession().setAttribute("tipo_pago", request.getParameter("tipoPago"));
		request.getSession().setAttribute("caja", request.getParameter("caja"));
		
		System.out.println("tDoc: " + (String)request.getSession().getAttribute("tipo_doc"));
		System.out.println("no_doc: " + (String)request.getSession().getAttribute("no_doc"));
		System.out.println("serie_doc: " + (String)request.getSession().getAttribute("serie_doc"));
		System.out.println("tipo_pago: " + (String)request.getSession().getAttribute("tipo_pago"));
		System.out.println("caja: " + (String)request.getSession().getAttribute("caja"));
		
		Connection con;
		con = new ConectarDB().getConnection();
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("codigo_movimiento", Integer.parseInt((String)request.getSession().getAttribute("tipo_doc")));
		parametros.put("numero_doc", Integer.parseInt((String)request.getSession().getAttribute("no_doc")));
		parametros.put("serie_doc", (String)request.getSession().getAttribute("serie_doc"));
		parametros.put("codigo_caja", Integer.parseInt((String)request.getSession().getAttribute("caja")));
		parametros.put("codigo_sucursal", Integer.parseInt((String) request.getSession().getAttribute("codSucursal")));
		String nombreDoc = (String)request.getSession().getAttribute("no_doc") + ".pdf";
		nombreDoc = (String)request.getSession().getAttribute("no_doc") + ".pdf";
		GenerarReporte.crearReporte(con,
									"C:\\Plantillas\\cotizacion_vertical.jasper", 
									parametros);
		GenerarReporte.exportarPDF("C:\\ReportesGenerados\\"+nombreDoc);
//		GenerarReporte.mostrarPDF();
		if(GenerarReporte.generado == 1 && GenerarReporte.exportado == 1){
			ArrayList<DatosRespuestasVarias> respuestas = new ArrayList<>();
			DatosRespuestasVarias datos = new DatosRespuestasVarias();
			datos.setMensaje("Reporte generado y exportado con numero: " + request.getSession().getAttribute("no_doc"));
			respuestas.add(datos);
			Gson gson = new Gson();
			JsonElement elemento = gson.toJsonTree(respuestas, new TypeToken<List<DatosRespuestasVarias>>(){}.getType());
			JsonArray arreglo = elemento.getAsJsonArray();
			respuestas = null;
			response.setContentType("application/json");
			response.getWriter().print(arreglo);
		}else if(GenerarReporte.generado == 0){
			response.setContentType("text/html");
			PrintWriter respuesta  = response.getWriter();
			respuesta.println(GenerarReporte.err1);
		}else if(GenerarReporte.exportado == 0){
			response.setContentType("text/html");
			PrintWriter respuesta  = response.getWriter();
			respuesta.println(GenerarReporte.err2);
		}
	}
}

package com.im.puntoventa.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.im.puntoventa.conexion.ConectarDB;
import com.im.puntoventa.dao.GenerarReporte;

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
		int noDoc=0, tipoDoc = 0, codSucursal = 0, caja =0;
		String serie = null;
		request.getSession().setAttribute("tipo_doc", request.getParameter("tipoDoc"));
		request.getSession().setAttribute("no_doc", request.getParameter("noDoc"));
		request.getSession().setAttribute("serie_doc", request.getParameter("serie"));
		request.getSession().setAttribute("tipo_pago", request.getParameter("tipoPago"));
		request.getSession().setAttribute("caja", request.getParameter("caja"));
		
		tipoDoc = Integer.parseInt((String)request.getSession().getAttribute("tipo_doc"));
		noDoc = Integer.parseInt((String)request.getSession().getAttribute("no_doc"));
		serie = (String)request.getSession().getAttribute("serie_doc");
		caja = Integer.parseInt((String) request.getSession().getAttribute("caja"));
		codSucursal = Integer.parseInt((String) request.getSession().getAttribute("codSucursal"));
		Connection con;
		con = new ConectarDB().getConnection();
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("tipo_doc", tipoDoc);
		parametros.put("no_doc", noDoc);
		parametros.put("serie_doc", serie);
		parametros.put("codigo_caja", caja);
		parametros.put("codigo_sucursal", codSucursal);
		String nombreDoc = (String)request.getSession().getAttribute("no_doc") + ".pdf";
		nombreDoc = (String)request.getSession().getAttribute("no_doc") + ".pdf";
		GenerarReporte.crearReporte(con,
									"C:\\Plantillas\\cotizacion_horizontal.jasper", 
									parametros);
		GenerarReporte.exportarPDF("C:\\ReportesGenerados\\"+nombreDoc);
		GenerarReporte.mostrarPDF();
		if(GenerarReporte.generado == 1 && GenerarReporte.exportado == 1){
			response.setContentType("text/html");
			PrintWriter respuesta  = response.getWriter();
			respuesta.println("Reporte generado y exportado con numero: " + request.getSession().getAttribute("no_doc"));
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

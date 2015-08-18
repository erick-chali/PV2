package com.im.puntoventa.servlet;

import java.io.IOException;
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
import com.im.puntoventa.dao.CargarDatosEncabezado;
import com.im.puntoventa.datos.DatosEnc;

/**
 * Servlet implementation class CargarEncabezado
 */
@WebServlet("/CargarEncabezado")
public class CargarEncabezado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CargarEncabezado() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("entro a cargar encabezado");
		int tipoDocumento=0;
		int numDocumento=0;
		String serie = null;
		
		request.getSession().setAttribute("tipoDocumento", request.getParameter("tipoDoc"));
		request.getSession().setAttribute("serie", request.getParameter("serie"));
		request.getSession().setAttribute("numeroDocumento", request.getParameter("numDocumento"));
		
		tipoDocumento = Integer.parseInt((String) request.getSession().getAttribute("tipoDocumento"));
		numDocumento = Integer.parseInt((String) request.getSession().getAttribute("numeroDocumento"));
		serie = (String) request.getSession().getAttribute("serie");
		
		ArrayList<DatosEnc> listado = null;
		listado = new ArrayList<DatosEnc>();
		listado = CargarDatosEncabezado.obtenerEncabezado(tipoDocumento, serie, numDocumento);
		
		
		Gson gson = new Gson();
		JsonElement elemento = gson.toJsonTree(listado, new TypeToken<List<DatosEnc>>(){}.getType());
		JsonArray arreglo = elemento.getAsJsonArray();
		response.setContentType("application/json");
		
		response.getWriter().print(arreglo);
	}

}

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
import com.im.puntoventa.dao.CargarDatosDet;
import com.im.puntoventa.datos.DatosDet;

/**
 * Servlet implementation class CargarDetalle
 */
@WebServlet("/CargarDetalle")
public class CargarDetalle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CargarDetalle() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int tipoDoc=0, numeroDocumento=0;
		String serie=null;
		
		request.getSession().setAttribute("tipoDocumento", request.getParameter("tipoDoc"));
		request.getSession().setAttribute("serie", request.getParameter("serie"));
		request.getSession().setAttribute("numeroDocumento", request.getParameter("numDocumento"));
		
		tipoDoc = Integer.parseInt((String) request.getSession().getAttribute("tipoDocumento"));
		numeroDocumento = Integer.parseInt((String) request.getSession().getAttribute("numeroDocumento"));
		serie = (String) request.getSession().getAttribute("serie");
		
		ArrayList<DatosDet> listado = null;
		listado = new ArrayList<DatosDet>();
		listado = CargarDatosDet.obtenerDetalles(tipoDoc, serie, numeroDocumento);
		
		
		Gson gson = new Gson();
		JsonElement elemento = gson.toJsonTree(listado, new TypeToken<List<DatosDet>>(){}.getType());
		JsonArray arreglo = elemento.getAsJsonArray();
		response.setContentType("application/json");
		
		response.getWriter().print(arreglo);
	}

}

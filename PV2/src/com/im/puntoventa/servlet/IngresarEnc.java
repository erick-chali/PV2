package com.im.puntoventa.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.im.puntoventa.dao.ImplementaDatosEnc;
import com.im.puntoventa.dao.InterfazDatosEnc;
import com.im.puntoventa.datos.DatosEnc;

/**
 * Servlet implementation class IngresarEnc
 */
@WebServlet("/IngresarEnc")
public class IngresarEnc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IngresarEnc() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DatosEnc datos = null;
		InterfazDatosEnc interfaz = new ImplementaDatosEnc();
		
		datos = new DatosEnc();
		datos.setCodigoCliente(request.getParameter("codigoCliente"));
		System.out.println(datos.getCodigoCliente());
		datos.setNit(request.getParameter("nit"));
		System.out.println(datos.getNit());
		datos.setNombreCliente(request.getParameter("nombreCliente"));
		System.out.println(datos.getNombreCliente());
		datos.setDirecFactura(request.getParameter("direcFactura"));
		System.out.println(datos.getDirecFactura());
		datos.setTel(request.getParameter("tel"));
		System.out.println(datos.getTel());
		datos.setTarjeta(request.getParameter("tarjeta"));
		System.out.println(datos.getTarjeta());
		datos.setDirecEnvio(request.getParameter("direcEnvio"));
		System.out.println(datos.getDirecEnvio());
		datos.setCodigoVendedor((String) request.getSession().getAttribute("codigoVendedor"));
		System.out.println(datos.getCodigoVendedor());
		datos.setUsername((String) request.getSession().getAttribute("usuario"));
		datos.setTipoDocumento(request.getParameter("tipoDoc"));
		datos.setFechaVence(request.getParameter("fechaVence"));
		datos.setTipoPago(request.getParameter("tipoPago"));
		datos.setTipoCredito(request.getParameter("tipoCredito"));
		datos.setAutoriza(request.getParameter("autoriza"));
		datos.setFechaDocumento(request.getParameter("fechaEntrega"));
		datos.setCargosEnvio(request.getParameter("cargosEnvio"));
		System.out.println(datos.getCargosEnvio());
		datos.setOtrosCargos(request.getParameter("otrosCargos"));
		System.out.println(datos.getOtrosCargos());
		datos.setMontoVenta(request.getParameter("montoVenta"));
		System.out.println(datos.getMontoVenta());
		datos.setMontoTotal(request.getParameter("montoTotal"));
		System.out.println(datos.getMontoTotal());
		datos.setObservaciones(request.getParameter("observaciones"));
		datos.setTipoNota(request.getParameter("tipoNota"));
		datos.setCaja(request.getParameter("caja"));
		datos.setFechaEntrega(request.getParameter("fechaEntrega"));
		datos.setNoConsigna(request.getParameter("noConsigna"));
		datos.setCodMovDev(request.getParameter("codMovDev"));
		datos.setGeneraSolicitud(request.getParameter("generaSolicitud"));
		datos.setTipoPagoNC(request.getParameter("tipoPagoNC"));
		datos.setTipoCliente(request.getParameter("tipoCliente"));
		datos.setCodigoNegocio(request.getParameter("codigoNegocio"));
		datos.setCantidadDevolver(request.getParameter("cantidadDevolver"));
		datos.setAutorizoDespacho(request.getParameter("autorizaDespacho"));
		datos.setSaldo(request.getParameter("saldo"));
		
		datos = interfaz.obtenerDatos(datos);
		
		if(datos.getNoEnc()!=null){
			response.setContentType("text/html");
			PrintWriter resp = response.getWriter();
			resp.println(datos.getNoEnc());
		}
	}

}

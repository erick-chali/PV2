package com.im.puntoventa.dao;

import java.sql.Connection;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public abstract class GenerarReporte {
	private static JasperReport reporte;
	private static JasperPrint reporteLleno;
	public static int generado = 0;
	public static int exportado =0;
	public static String err1 = null, err2 = null;
	@SuppressWarnings("unchecked")
	public static void crearReporte(Connection con, String ruta, @SuppressWarnings("rawtypes") Map parametros){
		try {
			reporte = (JasperReport)JRLoader.loadObjectFromFile(ruta);
			reporteLleno = JasperFillManager.fillReport(reporte, parametros, con);
			generado = 1;
		} catch (JRException e) {
			// TODO: handle exception
			System.out.println("Error: " + e.getMessage());
			err1 = "Reporte no Generado: " + e.getMessage();
		}
	}
	public static void mostrarPDF(){
//		visor = new JasperViewer(reporteLleno);
//		visor.setVisible(true);
		JasperViewer.viewReport(reporteLleno, false);
		
	}
	public static void exportarPDF(String rutaPDF){
		try {
			JasperExportManager.exportReportToPdfFile(reporteLleno, rutaPDF);
			exportado = 1;
		} catch (JRException e) {
			// TODO: handle exception
			System.out.println("Error: " + e.getMessage());
			err2 = "Reporte no exportado: " + e.getMessage();
		}
		
	}
}

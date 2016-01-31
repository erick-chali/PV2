<%@page import="com.im.puntoventa.conexion.ConectarDB"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.CallableStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" lang="es">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>PUNTO VENTA</title>
        
        <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css">
        <link type="text/css" rel="stylesheet" href="//cdn.datatables.net/plug-ins/1.10.7/integration/bootstrap/3/dataTables.bootstrap.css">
        <link href="//cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.0/bootstrap3-editable/css/bootstrap-editable.css" rel="stylesheet"/>
        <link type="text/css" rel="stylesheet" href="css/pushy.css">
        <link type="text/css" rel="stylesheet" href="css/BeatPicker.min.css">
        <link type="text/css" rel="stylesheet" href="css/style.css">
    </head>
    <body>
    
    <!-- Pushy Menu -->
        <nav class="pushy pushy-left">
            <ul>
                <li><a>Punto Venta</a></li>
                <li><a href="Logout">Cerrar Sesi&oacute;n</a></li>
            </ul>
        </nav>
        <div class="site-overlay"></div>
    <div id="container">
        <div class="panel panel-default">
                <div class="panel-body">
                    			<h1 class="text-center" id="nombreEmpresa">${nombreEmpresa}</h1>
                    <div id="toolbar">
                    			<label id="indice">0</label>
                    			<label id="codigoCliente"></label>
                    			<label id="codigoProd"></label>
                    			<label id="numFilas"></label>
                    			<label id="saldoCliente"></label>
                    			<label id="tipoCliente"></label>
                    			<label id="mensaje"></label>
                    			<label id="codigoLista">1</label>
                    			<label id="fechaPrueba"></label>
                    			<label id="permiso"></label>
                    			<label id="caja">0</label>
                    			<label id="numDoc"></label>
                    			<label id="serie"></label>
                    		</div>
                    
                    
                    <div class="well well-sm" id="parteInferior">
                    	<div class="row">
                    	<div class="col-sm-3 col-md-3">
                    		<button type="button" id="agregarFila" class="btn btn-primary btn-sm" style="margin-top: 2px;">
                    			<span class="glyphicon glyphicon-plus" aria-hidden="true" ></span>
                    		</button>
                    		<button type="button" id="barra" class="btn btn-primary btn-sm menu-btn" style="margin-top: 2px;">
                    			<span id="superiorDown" class="glyphicon glyphicon-menu-hamburger" aria-hidden="true"></span>
                    		</button>
                    		<button type="button" id="ayuda" class="btn btn-primary btn-sm" style="margin-top: 2px;">
                    			<span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>
                    		</button>
                    	</div>
                    	<div class="col-sm-1 col-md-1" >
                    	<h6 style="color: #c12929;">Forma Pago</h6>
                    	</div>
                        <div class="col-sm-2 col-md-2">
                                
                            <div class="input-group" id="divFormaPago">
                                <input type="text" class="form-control input-sm" placeholder="Forma Pago" id="fPago">
                                <span class="input-group-btn">
                                    <button class="btn btn-primary btn-sm" type="button" id="f1">F1</button>
                                </span>
                            </div><!-- /input-group -->
                        </div>
                        <div class="col-sm-1 col-md-1" >
                    		<h6 style="color: #c12929;">Tipo Credito</h6>
                    	</div>
                        <div class="col-sm-2 col-md-2">
                            
                            <select class="form-control input-sm" id="tCredito" disabled> 
                            	<option value="">Tipos de cr&eacute;dito</option>
                            	<option value="1">credito</option>
                            	<option value="2">contado</option>
                            </select> 
                        </div>
                        <div class="col-sm-1 col-md-1">
                        	<h6 style="color: #c12929;">Limite Credito</h6>
                        </div>
                        <div class="col-sm-2 col-md-2">
                            
                            <div class="input-group">
                            	<span class="input-group-addon">${moneda}</span>
                                <input type="text" class="form-control input-sm" placeholder="Limite de Cr&eacute;dito" id="lCredito" disabled>
                                <span class="input-group-btn">
                                    <button class="btn btn-primary btn-sm" type="button" id="exportarPDf">
                                    	<span class="glyphicon glyphicon-export" aria-hidden="true"></span>
                                    </button>
                                </span>
                            </div><!-- /input-group -->
                        </div>
                        
                    </div><!--fin de fila-->
                    		

                    	<!-- <div class="table-responsive" id="contenedorDatosVarios"> -->
                    	
						<table id="datosVarios" class="table table-striped table-condensed table-bordered table-hover">
	                    <thead>
	                    	<tr style="background-color: #0088CC; color: #ffffff;">
	                    		<th style="width: 30px"> </th>
	                    		<th style="width: 75px;">Cod. Prod.</th>
	                    		<th style="width: 20px;">U.M.</th>
	                    		<th style="width: 300px;">Descripcion</th>
	                    		<th style="width: 50px;">Cant.</th>
	                    		<th style="width: 50px;">Disp.</th>
	                    		<th style="width: 75px;">P. U.</th>
	                    		<th style="width: 75px;">%</th>
	                    		<th style="width: 25px;">Desc</th>
	                    		<th style="width: 75px;">Importe</th>
	                    		<th style="width: 25px;">BD</th>
	                    		<th style="width: 20px;">Env.</th>
	                    		<th style="width: 25px;">DM</th>
	                    		<th style="width: 100px;">Observaciones</th>
	                    		<th class="kit">Es Kit</th>
	                    		<th class="correlativo"> </th>
	                    		<th class="alternos"></th>
	                    		
	                    	</tr>
	                    </thead>
	                    <tbody>
	                    	<tr>
	                    		<td class=""><span class="glyphicon glyphicon-minus text-danger borrar" aria-hidden="true"></span>  <span class="glyphicon glyphicon-eye-open ojoProducto" aria-hidden="true"></span></td>
	                    		<td class="codigoProducto"></td>
	                    		<td class="medida"></td>
	                    		<td class="descripcion"><div class="contenDescrip" data-toggle="tooltip"></div></td>
	                    		<td class="cantidad"></td>
	                    		<td class="disponible"></td>
	                    		<td class="precio"></td>
	                    		<td class="porcentaje numeric"></td>
	                    		<td class="descuento" ></td>
	                    		<td class="importe"></td>
	                    		<td class="bodega"></td>
	                    		<td class="envio"><input type="checkbox"></td>
	                    		<td class="dm" ></td>
	                    		<td class="obser"></td>
	                    		<td class="kit"></td>
	                    		<td class="correlativo"></td>
	                    		<td class="alternos"></td>
	                    	</tr>
	                    </tbody>
                    </table>
                    	
                    	<!--</div>  -->
                    	<h5 class="text-right" id="subTotal">SubTotal: 0.00</h5>
                    	<h5 class="text-right" id="total">Total: 0.00</h5>
                    </div>
                    <button type="button" id="ocultarInferior" class="btn btn-primary btn-sm">
                    	<span id="superiorDown" class="glyphicon glyphicon-menu-hamburger" aria-hidden="true"></span>
                    </button>
                    <button type="button" class="btn btn-primary btn-sm" id="grabarDocumento">
  						<span class="glyphicon glyphicon-save" aria-hidden="true" ></span> Grabar
					</button>
                    
                    <div class="well well-sm" style="margin-top: 3px;">
                    	
                    	<div class="row">
                    	<div class="col-sm-1 col-md-1">
                    		<h6 style="color: #c12929;">Tipo Doc.</h6>
                    	</div>
                        <div class="col-sm-2 col-md-2">
		                    	<div class="input-group">
		                        	<input type="text" class="form-control input-sm" placeholder="Tipo Documento" id="tDoc">
		                           	<span class="input-group-btn">
		                            	<button class="btn btn-primary btn-sm" type="button" id="f2">F2</button>
		                           	</span>
		                        </div><!-- /input-group -->
                        </div>
                        <div class="col-sm-1 col-md-1">
                    		<h6 style="color: #c12929;">No. Doc.</h6>
                    	</div>
                        <div class="col-sm-2 col-md-2">
                            <div class="input-group">
                            	<span class="input-group-addon" id="serieDoc"></span>
                                <input type="text" class="form-control input-sm" placeholder="No. Documento" id="nDoc">
                                <span class="input-group-btn">
                                    <button class="btn btn-primary btn-sm" type="button" id="f3">F3</button>
                                </span>
                            </div><!-- /input-group -->
                        </div>
                        <div class="col-sm-1 col-md-1">
                        	<h6 style="color: #c12929;">F. Vence</h6>
                        </div>
                        <div class="col-sm-2 col-md-2">
                        	
                           	<input type="text" class="form-control input-sm" id="fechaVencimiento" disabled>
                        </div>
                        <div class="col-sm-1 col-md-1">
                    		<h6 style="color: #c12929;">F. Entrega</h6>
                    	</div>
                        <div class="col-sm-2 col-md-2">
                        	<input type="text" id="fechaEntrega"  data-beatpicker="true" data-beatpicker-format="['DD','MM','YYYY'],separator:'/'" data-beatpicker-disable="{from:[2015,8,28],to:'<'}" />
                            <!--<input type="text" class="form-control input-sm" id="fechaEntrega">-->
                        </div>
                    </div><!--fin de fila-->
                    
                    
                    	<div class="row">
                    	<div class="col-sm-1 col-md-1">
                        	<h6 style="color: #c12929;">Nit</h6>
                        </div>
                        <div class="col-sm-2 col-md-2">
                            <div class="input-group">
                                <input type="text" class="form-control input-sm" placeholder="NIT" id="nit">
                                <span class="input-group-btn">
                                    <button class="btn btn-primary btn-sm" type="button" id="f4">F4</button>
                                </span>
                            </div><!-- /input-group -->
                        </div>
                        <div class="col-sm-1 col-md-1">
                        	<h6 style="color: #c12929;">Nombre</h6>
                        </div>
                        <div class="col-sm-2 col-md-2">
                            <input type="text" class="form-control input-sm" id="nombre" placeholder="Nombre Cliente">     
                        </div>
                        
                        <div class="col-sm-1 col-md-1">
                        	<h6 style="color: #c12929;">Direc. Fac.</h6>
                        </div>
                        <div class="col-sm-2 col-md-2">
                            <input class="form-control input-sm" type="text" id="direcF" placeholder="Dirección Factura">
                        </div>
                        <div class="col-sm-1 col-md-1">
                        	<h6 style="color: #c12929;">Direc. Env.</h6>
                        </div>
                        <div class="col-sm-2 col-md-2">
                            <input class="form-control input-sm" type="text" id="direcE" placeholder="Dirección Envío">
                        </div>
                    </div><!--fin de fila-->
                    
                    <div class="row">
                    	<div class="col-sm-1 col-md-1">
                    		<h6 style="color: #c12929;">Teléfono</h6>
                    	</div>
                        <div class="col-sm-2 col-md-2">
                            <input type="tel" class="form-control input sm" id="telefono" placeholder="Teléfono">
                        </div>
                        <div class="col-sm-1 col-md-1">
                    		<h6 style="color: #c12929;">Tarjeta</h6>
                    	</div>
                        <div class="col-sm-2 col-md-2">
                            <input type="text" class="form-control input-sm" id="tarjeta" placeholder="Tarjeta" >     
                        </div>
                        
                        
                    </div><!--fin de fila-->
                    
                    
                    </div>
                    
                    <div class="row">
                        <div class="col-sm-2 col-md-2 text-center">
                        	<h5 id="fecha"></h5>
                        </div>
                        <div class="col-sm-2 col-md-2 text-center">
                            <h5>${sucursal}</h5>
                        </div>
                        <div class="col-sm-2 col-md-2 text-center">
                            <h5>Usuario: ${usuario}</h5>  
                        </div>
                        <div class="col-sm-2 col-md-2 text-center">
                            <h5>Nombre: ${vendedor}</h5>    
                        </div>
                        <div class="col-sm-4 col-md-4 text-center">
                            <h5>DB: ${nombreDB}</h5>    
                        </div>
                    </div><!--fin de fila-->
                </div>
        </div><!-- FIN PANEL COTIZACION -->
        
        
        
        	
        
        <!-- Modal de autorizaciones -->
		<div id="autorizar" class="modal fade" role="dialog" data-keyboard="true" tabindex="-1">
		  <div class="modal-dialog">
		
		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title">Solicitud de Permisos <small>(Ingrese sus credenciales)</small></h4>
		      </div>
		      <div class="modal-body">
		      	<label id="opcionPermisos"></label><br>
		      	<label for="popUser">Usuario</label>
		        <input type="text" class="form-control input-sm" id="permisosUsuario">
		        <label for="popPass">Contrase&ntilde;a</label>
		        <input type="password" class="form-control input-sm" id="permisosClave">
		        <button type="button" class="btn btn-primary btn-sm" style="margin-top: 10px;" id="autorizacion">Dar Permisos</button>
		        <button type="button" class="btn btn-danger btn-sm" data-dismiss="modal" style="margin-top: 10px;" id="cancelar">Cancelar</button>
		      </div>
		      <div class="modal-footer">
		      	<p id="notificacion" class="text-danger"></p>
		      </div>
		    </div>
		
		  </div>
		</div><!-- FIN DE MODAL -->
		
		<!-- Modal BUSQUEDA DOCUMENTOS -->
		<div id="buscarDocumentos" class="modal fade" role="dialog" data-keyboard="true" tabindex="-1">
		  <div class="modal-dialog modal-lg">
		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header">
		      	<button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title">Busqueda de Documentos</h4>
		      </div>
		      <div class="modal-body">
		      	<div class="col-md-6" style="margin-bottom: 10px;">
		      		<select id="filtroComboDocumentos" class="form-control input-sm">
		      		<option value="">Seleccione Filtro</option>
		      		<option value="1">Nombre</option>
		      		<option value="2">NIT</option>
		      		</select>
		      	</div>
		      	<div class="col-md-6" style="margin-bottom: 10px;">
		      		<input type="text" class="form-control input-sm col-md-6" id="filtroTextoDocumentos">
		      	</div>
		      	<div id="contenedorCotizaciones">
		      		
		      	</div>
		        <button type="button" class="btn btn-danger btn-sm" data-dismiss="modal" style="margin-top: 10px;" id="cancelar">Cancelar</button>
		      </div>
		      <div class="modal-footer">
		      	<p id="notificacion" class="text-danger"></p>
		      </div>
		    </div>
		  </div>
		</div><!-- FIN DE MODAL -->
		
		<!-- Modal BUSQUEDA PRODUCTOS -->
		<div id="buscarProductos" class="modal fade" role="dialog"  data-keyboard="true" tabindex="-1">
		  <div class="modal-dialog modal-lg">
		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header">
		      	<button type="button" class="close visible-sm visible-xs" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title">Busqueda de Productos</h4>
		      </div>
		      <div class="modal-body">
		      	<div class="col-md-6" style="margin-bottom: 10px;">
		      		<select id="filtroComboProductos" class="form-control input-sm">
		      		<option value="">Seleccione Filtro</option>
		      		<option value="1">Referencia</option>
		      		<option value="2">Descripci&oacute;n</option>
		      		<option value="3">Marca</option>
		      		<option value="4">Familia</option>
		      		</select>
		      	</div>
		      	<div class="col-md-6" style="margin-bottom: 10px;">
		      		<input type="text" class="form-control input-sm col-md-6" id="filtroTextoProductos">
		      	</div>
		      	<div id="contenedorProductos">
		      		 
		      	</div>
		        <button type="button" class="btn btn-danger btn-sm" data-dismiss="modal" style="margin-top: 10px;" id="cancelar">Cancelar</button>
		      </div>
		      <div class="modal-footer">
		      	<p id="notificacion" class="text-danger"></p>
		      </div>
		    </div>
		  </div>
		</div><!-- FIN DE MODAL -->
		
		<!-- Modal BUSQUEDA PRODUCTOS OTRAS BODEGAS -->
		<div id="buscarProductosBodegas" class="modal" role="dialog" data-keyboard="true" tabindex="-1">
		  <div class="modal-dialog modal-lg">
		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header">
		      	<button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title">Busqueda de Productos en Otras Bodegas</h4>
		      </div>
		      <div class="modal-body">
		      	<div id="botones">
		      		<label>La cantidad de producto excede al monto disponible, Desea buscar en otra bodega?</label>
		      		<button type="button" class="btn btn-primary btn-sm" style="margin-top: 10px;" id="siBodegas">Si</button>
		      		<button type="button" class="btn btn-danger btn-sm" data-dismiss="modal" style="margin-top: 10px;" id="noBodegas">No</button>
		      	</div>
		      	<div id="escondido">
		      		
			      	<div id="contenedorProductosBodegas">
			      		 
			      	</div>
		        	<button type="button" class="btn btn-danger btn-sm" data-dismiss="modal" style="margin-top: 10px;" id="cancelar">Cancelar</button>
		      	</div>
		      	
		      	
		      </div>
		      <div class="modal-footer">
		      	<p id="notificacion" class="text-danger"></p>
		      </div>
		    </div>
		  </div>
		</div><!-- FIN DE MODAL -->
		
		<!-- Modal BUSQUEDA PAGOS -->
		<div id="buscarPagos" class="modal" role="dialog" data-keyboard="true" tabindex="-1">
		  <div class="modal-dialog">
		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header">
		      	<button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title">Busqueda de Pagos</h4>
		      </div>
		      <div class="modal-body">
		      	<div id="contenedorPagos">
		      		
		      	</div>
		        <button type="button" class="btn btn-danger btn-sm" data-dismiss="modal" style="margin-top: 10px;" id="cancelar">Cancelar</button>
		      </div>
		      <div class="modal-footer">
		      	<p id="notificacion" class="text-danger"></p>
		      </div>
		    </div>
		  </div>
		</div><!-- FIN DE MODAL -->
		
		
		<!-- Modal BUSQUEDA Clientes -->
		<div id="buscarClientes" class="modal" role="dialog" data-keyboard="true" tabindex="-1">
		  <div class="modal-dialog modal-lg">
		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header">
		      	<button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title">Busqueda de Clientes</h4>
		      </div>
		      <div class="modal-body">
		      	<div class="col-md-6" style="margin-bottom: 10px;">
		      		<select id="filtroComboClientes" class="form-control input-sm">
		      		<option value="">Seleccione Filtro</option>
		      		<option value="1">Nombre</option>
		      		<option value="2">Nit</option>
		      		<option value="3">Tarjeta</option>
		      		</select>
		      	</div>
		      	<div class="col-md-6" style="margin-bottom: 10px;">
		      		<input type="text" class="form-control input-sm col-md-6" id="filtroTextoClientes">
		      	</div>
		      	<div id="contenedorClientes">
		      		 
		      	</div>
		        <button type="button" class="btn btn-danger btn-sm" data-dismiss="modal" style="margin-top: 10px;" id="cancelar">Cancelar</button>
		      </div>
		      <div class="modal-footer">
		      	<p id="notificacion" class="text-danger"></p>
		      </div>
		    </div>
		  </div>
		</div><!-- FIN DE MODAL -->
		
		
		<!-- Modal BUSQUEDA Clientes -->
		<div id="detallesKit" class="modal" role="dialog" data-keyboard="true" tabindex="-1">
		  <div class="modal-dialog modal-lg">
		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header">
		      	<button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title" id="tituloModalKit"></h4>
		      </div>
		      <div class="modal-body">
		      	
		      	<div id="contenedorKits">
		      		 
		      	</div>
		        <button type="button" class="btn btn-danger btn-sm" data-dismiss="modal" style="margin-top: 10px;" id="cancelar">Cancelar</button>
		      </div>
		      <div class="modal-footer">
		      	<p id="notificacion" class="text-danger"></p>
		      </div>
		    </div>
		  </div>
		</div><!-- FIN DE MODAL -->
		
		<!-- Modal BUSQUEDA INFORMACION PRODUCTO -->
		<div id="infoProducto" class="modal" role="dialog" data-keyboard="true" tabindex="-1">
		  <div class="modal-dialog">
		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header">
		      	<button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title" id="tituloModalKit">Informaci&oacute;n de Producto</h4>
		      </div>
		      <div class="modal-body">
		      	
		      	<ul class="nav nav-tabs">
				  <li class="active"><a data-toggle="tab" href="#imagenProducto">Imagen</a></li>
				  <li><a data-toggle="tab" href="#caracteristicas">Caracter&iacute;sticas</a></li>
				</ul>

				<div class="tab-content">
				  <div id="imagenProducto" class="tab-pane fade in active">
				  	<div class="row">
					  <div id="contenedorImagen"class="col-xs-6 col-sm-6 col-md-6">
					    <a href="#" class="thumbnail" id="thumbIMG">
					    	
					      <!--<img id="imgProducto">-->
					    </a>
					  </div>
					</div>
				  </div>
				  <div id="caracteristicas" class="tab-pane fade">
				  	<embed src="pdf/demo.pdf" width="100%" height="500" pluginspage="http://www.adobe.com/products/acrobat/readstep2.html">
				  </div>
				</div>
		        <button type="button" class="btn btn-danger btn-sm" data-dismiss="modal" style="margin-top: 10px;" id="cancelar">Cerrar</button>
		      </div>
		      <div class="modal-footer">
		      	<p id="notificacion" class="text-danger"></p>
		      </div>
		    </div>
		  </div>
		</div><!-- FIN DE MODAL -->
		
		<!-- Modal PRODUCTOS ALTERNOS-->
		<div id="productosAlternos" class="modal" role="dialog" data-keyboard="true" tabindex="-1">
		  <div class="modal-dialog">
		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header">
		      	<button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title" id="tituloModalKit">Informaci&oacute;n de Producto</h4>
		      </div>
		      <div class="modal-body">
		      	<div id="contenedorAlternos">
		      	</div>
		        <button type="button" class="btn btn-danger btn-sm" data-dismiss="modal" style="margin-top: 10px;" id="cancelar">Cerrar</button>
		      </div>
		      <div class="modal-footer">
		      	<p id="notificacion" class="text-danger"></p>
		      </div>
		    </div>
		  </div>
		</div><!-- FIN DE MODAL -->
		</div>
    <script type="text/javascript" src="js/jquery-1.5.min.js"></script>
	<script type="text/javascript">
		var $jq = jQuery.noConflict();
	</script>
	<!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>  -->
	<script src="js/jquery-1.11.3.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jeditable.min.js"></script>
	<script src="js/maskedinput.min.js"></script>
	<script src="js/BeatPicker.min.js"></script>
	<script src="js/jquery.freezeheader.js"></script>
	<script src="js/pushy.min.js"></script>
	<script src="js/scriptPV.js"></script>
	
    
    
    </body>
</html>
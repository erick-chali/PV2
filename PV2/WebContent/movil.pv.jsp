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
        <link type="text/css" rel="stylesheet" href="css/style.css">
    </head>
    <body>
    
    <!-- Pushy Menu -->
        <nav class="pushy pushy-left">
            <ul>
                <li><a href="pv.jsp">Punto Venta</a></li>
                <li><a href="Logout">Cerrar Sesi&oacute;n</a></li>
            </ul>
        </nav>
        <div class="site-overlay"></div>
    <!-- Fixed navbar -->
    <!--  
    <nav class="navbar navbar-inverse navbar-fixed-top" id="barraNavegacion">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="pv.jsp">Punto venta</a></li>
            <li><a href="datoscliente.jsp">Ingreso Datos Cliente</a></li>
            <li><a href="">Cambio Tipo Cliente</a></li>
            <li><a href="">Cambio Vendedor</a></li>
            
          </ul>
          <ul class="nav navbar-nav navbar-right">
          	<li></li>
            <li><a href="Logout"><span class="glyphicon glyphicon-off" aria-hidden="true"></span> Cerrar Sesion</a></li>
          </ul>
        </div>
      </div>
    </nav>
    -->
    <!-- FIXED NAVBAR -->
    <div id="container">
        <div class="panel panel-default">
                <div class="panel-body">
                    			<p class="text-center" style="font-weight: bold; font-size: large;">${nombreEmpresa}</p>
                    <div id="toolbar">
                    			<label id="indice">0</label>
                    			<label id="codigoCliente"></label>
                    			<label id="codigoProd"></label>
                    			<label id="numFilas"></label>
                    			<label id="numDocumento"></label>
                    			<label id="saldoCliente"></label>
                    			<label id="tipoCliente"></label>
                    			<label id="mensaje"></label>
                    			<label id="codigoLista">1</label>
                    			<label id="fechaPrueba"></label>
                    			<label id="autoriza"></label>
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
                                    <button class="btn btn-primary btn-sm" type="button" id="exp">
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
	                    		
	                    		<th style="width: 75px;">Cod. Prod.</th>
	                    		<th style="width: 50px;">U. M.</th>
	                    		<th style="width: 250px;">Descripcion</th>
	                    		<th style="width: 50px;">Cant.</th>
	                    		<th style="width: 50px;">Disp.</th>
	                    		<th style="width: 75px;">P. U.</th>
	                    		<th style="width: 75px;">%</th>
	                    		<th style="width: 25px;">Desc</th>
	                    		<th style="width: 75px;">Importe</th>
	                    		<th style="width: 25px;">BD</th>
	                    		<th style="width: 20px;">Env.</th>
	                    		<th style="width: 75px;">DM</th>
	                    		<th style="width: 100px;">Observaciones</th>
	                    		<th class="kit">Es Kit</th>
	                    		
	                    	</tr>
	                    </thead>
	                    <tbody>
	                    	<tr>
	                    		<td class="codigoProducto"></td>
	                    		<td class="medida"></td>
	                    		<td class="descripcion"><div class="contenDescrip"></div></td>
	                    		<td class="cantidad"></td>
	                    		<td class="disponible"></td>
	                    		<td class="precio"></td>
	                    		<td class="porcentaje"></td>
	                    		<td class="descuento" ></td>
	                    		<td class="importe"></td>
	                    		<td class="bodega"></td>
	                    		<td class="envio"><input type="checkbox"></td>
	                    		<td class="dm" ></td>
	                    		<td class="obser"></td>
	                    		<td class="kit"></td>
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
                            <input type="text" class="form-control input-sm" id="fechaEntrega">
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
                        <div class="col-sm-3 col-md-3 text-center">
                        	<h5 id="fecha"></h5>
                        </div>
                        <div class="col-sm-3 col-md-3 text-center">
                            <h5>${sucursal}</h5>
                        </div>
                        <div class="col-sm-3 col-md-3 text-center">
                            <h5>${usuario}</h5>  
                        </div>
                        <div class="col-sm-3 col-md-3 text-center">
                            <h5>${vendedor}</h5>    
                        </div>
                    </div><!--fin de fila-->
                </div>
        </div><!-- FIN PANEL COTIZACION -->
        
        
        
        	
        
        <!-- Modal -->
		<div id="autorizar" class="modal" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		  <div class="modal-dialog">
		
		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close visible-sm visible-xs" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title">Autorizar Documento</h4>
		      </div>
		      <div class="modal-body">
		      	<label for="popUser">Usuario</label>
		        <input type="text" class="form-control input-sm" id="popUser">
		        <label for="popPass">Contrase&ntilde;a</label>
		        <input type="password" class="form-control input-sm" id="popPass">
		        <button type="button" class="btn btn-primary btn-sm" style="margin-top: 10px;" id="autorizacion">Autorizar</button>
		        <button type="button" class="btn btn-danger btn-sm" data-dismiss="modal" style="margin-top: 10px;" id="cancelar">Cancelar</button>
		      </div>
		      <div class="modal-footer">
		      	<p id="notificacion" class="text-danger"></p>
		      </div>
		    </div>
		
		  </div>
		</div><!-- FIN DE MODAL -->
		
		<!-- Modal BUSQUEDA DOCUMENTOS -->
		<div id="buscarDocumentos" class="modal" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="true" tabindex="-1">
		  <div class="modal-dialog modal-lg">
		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header">
		      	<button type="button" class="close visible-sm visible-xs" data-dismiss="modal">&times;</button>
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
		<div id="buscarProductos" class="modal" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="true" tabindex="-1">
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
		<div id="buscarProductosBodegas" class="modal" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="true" tabindex="-1">
		  <div class="modal-dialog modal-lg">
		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header">
		      	<button type="button" class="close visible-sm visible-xs" data-dismiss="modal">&times;</button>
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
		<div id="buscarPagos" class="modal" role="dialog" aria-hidden="true" data-backdrop="static" tabindex="-1">
		  <div class="modal-dialog modal-lg">
		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header">
		      	<button type="button" class="close visible-sm visible-xs" data-dismiss="modal">&times;</button>
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
		<div id="buscarClientes" class="modal" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="true" tabindex="-1">
		  <div class="modal-dialog modal-lg">
		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header">
		      	<button type="button" class="close visible-sm visible-xs" data-dismiss="modal">&times;</button>
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
		<div id="detallesKit" class="modal" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="true" tabindex="-1">
		  <div class="modal-dialog modal-lg">
		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header">
		      	<button type="button" class="close visible-sm visible-xs" data-dismiss="modal">&times;</button>
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
		</div>
    <script type="text/javascript" src="js/jquery-1.5.min.js"></script>
	<script type="text/javascript">
		var $jq = jQuery.noConflict();
	</script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jeditable.min.js"></script>
	<script src="js/maskedinput.min.js"></script>
	<script src="js/BeatPicker.min.js"></script>
	<script src="js/jquery.freezeheader.js"></script>
	<script src="js/pushy.min.js"></script>
	<script src="js/script.js"></script>
	<script src="//cdn.datatables.net/1.10.7/js/jquery.dataTables.min.js"></script>
	<script src="//cdn.datatables.net/plug-ins/1.10.7/integration/bootstrap/3/dataTables.bootstrap.js"></script>
	
    
    
    </body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" charset="utf-8">
<title>Punto de Venta</title>
<link rel="stylesheet" href="css/bootstrap.css" >
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.9.0/bootstrap-table.min.css">
<link href="//cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.0/bootstrap3-editable/css/bootstrap-editable.css" rel="stylesheet"/>
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/css/bootstrap-datepicker.min.css" rel="stylesheet"/>
<link rel="stylesheet" href="css/animate.css">

<link rel="stylesheet" href="css/estilosPV.css" >
</head>
<body>
	<h4 class="text-center">${nombreEmpresa}</h4>
	<h5 id="indiceFila" hidden="true"></h5>
	<h5 id="totalFilas" hidden="true"></h5>
	<h5 id="filaSeleccionada" hidden="true"></h5>
	<div class="panel-body">
		<div class="row">
			<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
				<button type="button" class="btn btn-primary" id="1">1</button>
				<button type="button" class="btn btn-primary" id="2">
				<span class="glyphicon glyphicon-off" aria-hidden="true"></span></button>
				<button type="button" class="btn btn-primary" id="3">
					<span class="glyphicon glyphicon-resize-full" aria-hidden="true"></span>
				</button>
			</div>
			<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1">
				<h6 class="text-danger"><strong>Forma Pago</strong></h6>
			</div>
			<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
				<div class="input-group">
					<span class="input-group-addon input-sm" id="codigoPago"></span>
					<input type="text" class="form-control input-sm" id="fPago">
					<span class="input-group-btn">
        				<button class="btn btn-primary btn-sm" type="button" id="f1">F1</button>
      				</span>
				</div>
				
			</div>
			<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1 contenedorTipoCredito">
				<h6 class="text-danger"><strong>Tipo Cr&eacute;dito</strong></h6>
			</div>
			<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2 contenedorTipoCredito" id="">
				<select class="form-control input-sm" id="tCredito" disabled>
					<option value="0">TipoCr&eacute;dito</option>
					<option value="1">Efectivo</option>
					<option value="2">Cr&eacute;dito</option>
				</select>
			</div>
			<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1">
				<h6 class="text-danger">L&iacute;mite Cr&eacute;dito</h6>
			</div>
			<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
				<div class="input-group">
					<span class="input-group-addon input-sm" id="simboloMoneda"></span>
					<input type="text" class="form-control input-sm" id="lCredito" readonly>
					<span class="input-group-btn">
        				<button class="btn btn-primary btn-sm" type="button" id="exportar">E</button>
      				</span>
				</div>
			</div>
		</div>
	</div>
	<div class="row" id="contenedorTabs">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<ul class="nav nav-tabs">
			    <li class="active"><a data-toggle="tab" href="#datosProducto">Datos Producto</a></li>
			    <li><a data-toggle="tab" href="#pdfProducto">PDF Producto</a></li>
			    <li><a data-toggle="tab" href="#imagenProducto">Imagen Producto</a></li>
			    <li><a data-toggle="tab" href="#busquedaProductos">Busqueda Productos</a></li>
			    <li><a data-toggle="tab" href="#detallesKitProducto">Detalles Kit</a></li>
  			</ul>
		<div class="tab-content">
			<div id="datosProducto" class="tab-pane fade in active">
		    	<div class="row">
		    		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" >
		    			<h5 class="text-left" id="textoProductoModo"></h5>
		    			<h3 class="text-center" id="textoProductoSeleccionado"></h3>
		    		</div>
					<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
						<h5>Codigo Producto</h5>
						<input type="text" class="form-control input-sm" id="codigoProductoTab">
					</div>
					<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
						<h5>UM</h5>
						<input type="text" class="form-control input-sm" id="medidaTab" readonly>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
						<h5>Cantidad</h5>
						<input type="number" class="form-control input-sm" id="cantidadTab">
					</div>
					<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
						<h5>Disponible</h5>
						<input type="text" class="form-control input-sm" id="disponibleTab" readonly>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
						<h5>P. U.</h5>
						<input type="text" class="form-control input-sm" id="precioTab" readonly>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
						<h5>Porcentaje</h5>
						<input type="number" class="form-control input-sm" id="porcentajeTab">
					</div>
					<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
						<h5>Descuento</h5>
						<input type="text" class="form-control input-sm" id="descuentoTab" readonly>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
						<h5>Importe</h5>
						<input type="text" class="form-control input-sm" id="importeTab" readonly>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
						<h5>Bodega</h5>
						<select id="bodegaTab" class="form-control input-sm"></select>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
						<h5>Envia</h5>
						<select id="enviaTab" class="form-control input-sm">
							<option value="0">No</option>
							<option value="1">Si</option>
						</select>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
						<h5>Desc. Max.</h5>
						<input type="number" class="form-control input-sm" id="descuentoMaximoTab" readonly>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
						<h5>Observaciones</h5>
						<input type="text" class="form-control input-sm" id="observacionesTab">
					</div>
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<h5>Descripcion</h5>
						<input type="text" class="form-control input-sm" id="descripcionTab" readonly>
					</div>
					<div class="row" id="contenedoresDatosExtra">
						<div class="col-xs-3 col-sm-4 col-md-4 col-lg-4">
							<h5>Es Kit</h5>
							<input type="text" class="form-control input-sm" id="esKitTab">
						</div>
						<div class="col-xs-3 col-sm-4 col-md-4 col-lg-4">
							<h5>Defectuoso</h5>
							<input type="text" class="form-control input-sm" id="esDfTab">
						</div>
						<div class="col-xs-3 col-sm-4 col-md-4 col-lg-4">
							<h5>Promocion</h5>
							<input type="text" class="form-control input-sm" id="promocionTab">
						</div>
						<div class="col-xs-3 col-sm-4 col-md-4 col-lg-4">
							<h5>CorrelativoKit</h5>
							<input type="text" class="form-control input-sm" id="correlativoKitTab">
						</div>
						<div class="col-xs-3 col-sm-4 col-md-4 col-lg-4">
							<h5>Codigo Promocion</h5>
							<input type="text" class="form-control input-sm" id="codigoPromocionTab">
						</div>
						<div class="col-xs-3 col-sm-4 col-md-4 col-lg-4">
							<h5>Orden Compra</h5>
							<input type="text" class="form-control input-sm" id="ordenCompraTab">
						</div>
						<div class="col-xs-3 col-sm-4 col-md-4 col-lg-4">
							<h5>Unidad Medida</h5>
							<input type="text" class="form-control input-sm" id="unidadMedidaTab">
						</div>
					</div>
				</div>
		    </div>
		    <div id="pdfProducto" class="tab-pane fade">
		      <h3>PDF</h3>
		      
		    </div>
		    <div id="imagenProducto" class="tab-pane fade">
		      <h3>Imagen Producto</h3>
		    </div>
		    <div id="busquedaProductos" class="tab-pane fade">
		      <h3>Busqueda Producto</h3>
		      <div class="row">
		      	<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
		      		<label>Criterio</label>
		      		<select class="form-control input-sm" id="criterioBusqueda">
		      			<option value="0">Descripcion</option>
		      			<option value="1">Marca</option>
		      			<option value="2">Familia</option>
		      			<option value="3">Referencia</option>
		      		</select>
		      	</div>
		      	<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
		      		<label>Texto a buscar</label>
		      		<input type="text" class="form-control input-sm" id="criterioBusquedaTexto">
		      	</div>
		      	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		      		<table id="busquedaProductosTabla"></table>
		      	</div>
		      </div>
		    </div>
		    <div id="detallesKitProducto" class="tab-pane fade">
		      <h3>Detalle Kit</h3>
		    </div>
	  	</div>
		</div>
	</div>
	
	<!-- 
	<div class="container-fluid">
		<button type="button" id="btnAgregar" class="btn btn-default btn-sm">Agregar Producto</button>
	</div> -->
	<div>
		<h3 id="contenedorContadorProductos"></h3>
	</div>
	<div class="row" id="contenedorTablaDatosVarios">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">	
			<div class="table-responsive">
				<table id="datosVarios"></table>
			</div>
		</div>
		<div class="row">
		<div class="col-xs-6 col-sm-4 col-md-3 col-lg-3">
			<button type="button" class="btn btn-primary form-control" id="grabarDocumento"> Grabar 
		  		<span class="glyphicon glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>
			</button>
		</div>
	</div>
	
	</div>
	<div class="row" id="contenedorNotasCredito">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<nav class="navbar navbar-default">
				<div class="container-fluid">
			    	<div class="navbar-header">
			      		<h1>NOTA CREDITO</h1>
			    	</div>
			  	</div>
			</nav>
			<div class="row">
				<div class="col-xs-6 col-sm-4 col-md-3 col-lg-1">
					<label>Serie: </label>
				</div>
				<div class="col-xs-6 col-sm-4 col-md-3 col-lg-3">
					<input type="text" class="form-control input-sm" id="notaCreditoSerie">
				</div>
			</div>
			<div class="row">
				<div class="col-xs-6 col-sm-4 col-md-3 col-lg-1">
					<label>N&uacute;mero: </label>
				</div>
				<div class="col-xs-6 col-sm-4 col-md-3 col-lg-3">
					<input type="number" class="form-control input-sm" id="notaCreditoNumero">
				</div>
			</div>
			<div class="row">
				<div class="col-xs-6 col-sm-4 col-md-3 col-lg-1">
					<label>Motivo: </label>
				</div>
				<div class="col-xs-6 col-sm-4 col-md-3 col-lg-3">
					<select class="form-control input-sm" id="notaCreditoMotivo"></select>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-6 col-sm-4 col-md-3 col-lg-1">
					<label>Tipo pago N/C: </label>
				</div>
				<div class="col-xs-6 col-sm-4 col-md-3 col-lg-3">
					<select class="form-control input-sm" id="notaCreditoTipoPago"></select>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="radio">
						<label>
						    <input type="radio" id="notaCreditoCargarLocal" value="0" >
						    Mercader&iacute;a se carga a bodega local.
					  	</label>
					</div>
					<div class="radio">
						<label>
						    <input type="radio" id="notaCreditoCargarOrigen" value="1" >
						    Mercader&iacute;a se carga a bodega de origen.
					  	</label>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="container-fluid" id="contenedorDatos">
		<div class="row">
			<div class="col-sm-1 col-md-1 col-lg-1">
				<h6 class="text-danger"><strong>Tipo Doc.</strong></h6>
			</div>
			<div class="col-sm-2 col-md-2 col-lg-2">
				<div class="input-group">
					<span class="input-group-addon input-sm" id="codigoTipoDoc"></span>
					<input type="text" class="form-control input-sm" id="tDoc" placeholder="Tipo Doc.">
					<span class="input-group-btn">
	        			<button class="btn btn-primary btn-sm" type="button" id="f2">F2</button>
	      			</span>
				</div>
			</div>
			<div class="col-sm-1 col-md-1 col-lg-1">
				<h6 class="text-danger"><strong>No. Doc.</strong></h6>
			</div>
			<div class="col-sm-2 col-md-2 col-lg-2">
				<div class="input-group">
					<span class="input-group-addon input-sm" id="serieDocumento"></span>
					<input type="text" class="form-control input-sm" id="nDoc" placeholder="No. Doc.">
					<span class="input-group-btn">
	        			<button class="btn btn-primary btn-sm" type="button" id="f3">F3</button>
	      			</span>
				</div>
			</div>
			<div class="col-sm-1 col-md-1 col-lg-1">
				<h6 class="text-danger"><strong>F. Vence</strong></h6>
			</div>
			<div class="col-sm-2 col-md-2 col-lg-2">
		    		<input type="text" class="form-control input-sm" id="fechaVence" disabled>
			</div>
			<div class="col-sm-1 col-md-1 col-lg-1">
				<h6 class="text-danger"><strong>F. Entrega</strong></h6>
			</div>
			<div class="col-sm-2 col-md-2 col-lg-2">
		    		<input type="text" class="form-control input-sm fechaEntrega" id="fechaEntrega" readonly>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-1 col-md-1 col-lg-1">
				<h6 class="text-danger"><strong>NIT</strong></h6>
			</div>
			<div class="col-sm-2 col-md-2 col-lg-2">
				<div class="input-group">
		    		<input type="text" class="form-control input-sm" id="nit" placeholder="NIT">
		    		<span class="input-group-btn">
	        			<button class="btn btn-primary btn-sm" type="button" id="f5">F5</button>
	      			</span>
		    	</div>
			</div>
			<div class="col-sm-1 col-md-1 col-lg-1">
				<h6 class="text-danger"><strong>Nombre</strong></h6>
			</div>
			<div class="col-sm-2 col-md-2 col-lg-2">
		    		<input type="text" class="form-control input-sm" id="nombre" placeholder="Cliente">
			</div>
			<div class="col-sm-1 col-md-1 col-lg-1">
				<h6 class="text-danger"><strong>Direc. Fac.</strong></h6>
			</div>
			<div class="col-sm-2 col-md-2 col-lg-2">
		    		<input type="text" class="form-control input-sm" id="direccionFactura" placeholder="Direcci&oacute;n Factura">
			</div>
			<div class="col-sm-1 col-md-1 col-lg-1">
				<h6 class="text-danger"><strong>Direc. Env.</strong></h6>
			</div>
			<div class="col-sm-2 col-md-2 col-lg-2">
		    		<input type="text" class="form-control input-sm" id="direccionEnvio" placeholder="Direcci&oacute;n Env&iacute;o">
			</div>
		</div>
		<div class="row">
			<div class="col-sm-1 col-md-1 col-lg-1">
				<h6 class="text-danger"><strong>Tel.</strong></h6>
			</div>
			<div class="col-sm-2 col-md-2 col-lg-2">
				<input type="tel" class="form-control input-sm" id="telefono" placeholder="Tel&eacute;fono">
			</div>
		</div>
	</div>
	
	<footer class="footer">
    	<div class="row">
    		<div class="col-xs-6 col-sm-4 col-md-3 col-lg-3">
    			<h5>USUARIO: ${vendedor}</h5>
    		</div>
    		<div class="col-xs-6 col-sm-4 col-md-3 col-lg-3">
    			<h5>BD: ${nombreDB}</h5>
    		</div>
    	</div>
    </footer>
	
	<div id="modalBuscaDocumento" class="modal fade" role="dialog">
		<div class="modal-dialog">
	    <!-- Modal content-->
			<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Seleccione un documento</h4>
		    </div>
			<div class="modal-body">
				
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					</div>
				</div>
				<div class="table-responsive">
					<table id="tablaDocumentos"></table>
				</div>
				
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" data-dismiss="modal" id="btnModalDocumentosCerrar">Cerrar</button>
			</div>
		    </div>
	  	</div>
	</div>
	<div id="modalValidaUsuario" class="modal fade" data-backdrop="static" data-keyboard="false" role="dialog">
		<div class="modal-dialog">
	    <!-- Modal content-->
			<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Ingrese sus credenciales</h4>
		    </div>
			<div class="modal-body">
				<h5 hidden="true" id="operacionValidaPermiso"></h5>
				<label>Usuario</label>
				<input type="text" class="input input-sm form-control" id="validaPermisosUsuario">
				<label>Contrase&ntilde;a</label>
				<input type="password" class="input input-sm form-control" id="validaPermisosClave">
				<button type="button" class="btn btn-primary btn-sm form-control" id="btnValidarUsuario">Validar</button>
				<h5 class="text-danger" id="txtNotificacionValidaPermisos"></h5>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" id="btnValidaPermisosCerrar">Cerrar</button>
			</div>
		    </div>
	  	</div>
	</div>
	<div id="modalConfirmar" class="modal fade" role="dialog">
		<div class="modal-dialog">
	    <!-- Modal content-->
			<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Si, No</h4>
		    </div>
			<div class="modal-body">
				<h5 id="operacionModalConfirmar"></h5>
				<h5 id="mensajeModalConfirmar"></h5>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-success" data-dismiss="modal" id="btnModalConfirmarConfirmar">Si</button>
				<button type="button" class="btn btn-danger" data-dismiss="modal" id="btnModalConfirmarCancelar">No</button>
			</div>
		    </div>
	  	</div>
	</div>
	<div id="modalBuscaProductosXBodega" class="modal fade" data-backdrop="static" data-keyboard="false" role="dialog">
		<div class="modal-dialog">
	    <!-- Modal content-->
			<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Seleccione la ubicacion deseada.</h4>
		    </div>
			<div class="modal-body">
				<table id="tablaModalBuscaProductosXBodega"></table>
				<h5 class="text-danger" id="mensajeModalBuscaProductosXBodega"></h5>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" data-dismiss="modal" id="btnModalBuscaProductosXBodegaCerrar">Cancelar</button>
			</div>
		    </div>
	  	</div>
	</div>
	<div id="modalMostrarPDFGenerado" class="modal fade" data-backdrop="static" data-keyboard="false" role="dialog">
		<div class="modal-dialog">
	    <!-- Modal content-->
			<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Cotizacion Generada</h4>
		    </div>
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" id="contenedorPDF">
						
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" data-dismiss="modal" id="btnModalBuscaProductosXBodegaCerrar">Cancelar</button>
			</div>
		    </div>
	  	</div>
	</div>
	<div id="modalBuscaClientes" class="modal fade" role="dialog">
		<div class="modal-dialog">
	    <!-- Modal content-->
			<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">B&uacute;squeda Clientes</h4>
		    </div>
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
						<select id="modalBuscaClientesCriterio" class="input-sm">
							<option value="0">Nombre</option>
							<option value="1">Nit</option>
						</select>
					</div>
					<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
						<input type="text" class="input input-sm" id="modalBuscaClientesTexto">
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<label class="text-danger" id="modalBuscaClientesNotificacion"></label>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<table id="tablaClientes"></table>
					</div>
				</div>
				
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" data-dismiss="modal" id="btnModalPagosCerrar">Cerrar</button>
			</div>
		    </div>
	  	</div>
	</div>
	<div id="modalTiposPago" class="modal fade" role="dialog">
		<div class="modal-dialog">
	    <!-- Modal content-->
			<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Seleccione tipo pago.</h4>
		    </div>
			<div class="modal-body">
				<table id="tablaPagos"></table>
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<label class="text-danger" id='modalTiposPagoNotificacion'></label>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<label id='modalTiposPagoNotificacionNormal'></label>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" data-dismiss="modal" id="btnModalPagosCerrar">Cerrar</button>
			</div>
		    </div>
	  	</div>
	</div>
	<div id="modalTiposMovimiento" class="modal fade" role="dialog">
		<div class="modal-dialog">
	    <!-- Modal content-->
			<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Seleccione un movimiento.</h4>
		    </div>
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<label class="text-danger" id="modalTiposMovimientoNotificacion"></label>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<label id="modalTiposMovimientoNotificacionNormal"></label>
					</div>
				</div>
				<table id="tablaMovimientos"></table>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" data-dismiss="modal" id="btnModalMovimientosCerrar">Cerrar</button>
			</div>
		    </div>
	  	</div>
	</div>
	
	<div id="modalIngresoNIT" class="modal fade" role="dialog">
		<div class="modal-dialog">
	    <!-- Modal content-->
			<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Ingrese NIT</h4>
		    </div>
			<div class="modal-body">
				<label>NIT</label>
				<input type="text" id="modalNIT" class="input-sm" placeholder="Ingrese NIT">
				<br>
				<button type="button" class="btn btn-primary" >Buscar NIT</button>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" data-dismiss="modal" id="btnModalNIT">Cerrar</button>
			</div>
		    </div>
	  	</div>
	</div>
	<script src="js/jquery-2.1.4.min.js"></script>
	<script src="js/bootstrap.min.js" ></script>
	<script src="js/bootstrap-table.js"></script>
	<script src="js/bootstrap-table-es-MX.js"></script>
	<script src="js/bootstrap-table-editable.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.0/bootstrap3-editable/js/bootstrap-editable.min.js"></script>
	<!-- 
	<script src="js/bootstrap-table-resizable.js"></script>
	<script src="js/colResizable-1.5.min.js"></script> -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/js/bootstrap-datepicker.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/locales/bootstrap-datepicker.es.min.js"></script>
	<script src="js/bootstrap-notify.min.js"></script>
	<script src="js/scriptPV.js"></script>
</body>
</html>
	(function(yourcode) {
		yourcode(window.jQuery, window, document);
	}(function($, window, document) {
	$(function() {
		var filaActual = 0;
		var totalFilas = 0;
		$('.contenedorTipoCredito').hide();
		$('#contenedoresDatosExtra').hide();
		$('#contenedorNotasCredito').hide();
		$('#textoProductoModo').text('Modo b\u00FAsqueda');
		llenarBodegas();
		/**CAPTURAS DE TECLAS**/
		$('#contenedorTablaDatosVarios').toggle();
		/**MOVIMIENTOS ENTRE INPUTS**/
		$('#codigoProductoTab').keydown(function(e){
			if(e.keyCode == 37){
				e.preventDefault();
			}
		});
		$('#codigoProductoTab').keydown(function(e){
			if(e.keyCode == 39){
				e.preventDefault();
				$('#cantidadTab').focus();
				$('#cantidadTab').click();
				$('#cantidadTab').select();
			}
		});
		$('#cantidadTab').keydown(function(e){
			if(e.keyCode == 37){
				e.preventDefault();
				$('#codigoProductoTab').focus();
				$('#codigoProductoTab').click();
				$('#codigoProductoTab').select();
			}
		});
		$('#cantidadTab').keydown(function(e){
			if(e.keyCode == 39){
				e.preventDefault();
				$('#porcentajeTab').focus();
				$('#porcentajeTab').click();
				$('#porcentajeTab').select();
			}
		});
		$('#cantidadTab').keydown(function(e){
			if(e.keyCode == 13){
				var cantidad;
				var cantidadDisponible;
				cantidad = parseInt($('#cantidadTab').val());
				cantidadDisponible = parseInt($('#disponibleTab').val());
				if($('#codigoProductoTab').val()==''){
					$('#codigoProductoTab').focus();
					$('#codigoProductoTab').click();
					$('#codigoProductoTab').select();
				}else if(isNaN($(this).val())){
					notificacionAlerta('La cantidad debe ser un numero.');
					$('#cantidadTab').focus();
					$('#cantidadTab').click();
					$('#cantidadTab').select();
				}else if($(this).val() == '' || $(this).val() == 0){
					notificacionAlerta('La cantidad no puede ser 0 o estar vacia.');
					$('#cantidadTab').focus();
					$('#cantidadTab').click();
					$('#cantidadTab').select();
				}else if(cantidad < 0){
					notificacionAlerta('La cantidad no puede ser menor a 0');
					$('#cantidadTab').focus();
					$('#cantidadTab').click();
					$('#cantidadTab').select();
				}else if(cantidad > cantidadDisponible){
					console.log('.|.');
					$('#modalBuscaProductosXBodega').modal('toggle');
//					var buscarOtrasBodegas = ('La cantidad deseada es mayor a la disponible en esta bodega, desea buscar en otras?');
//					if(buscarOtrasBodegas == true){
//						$('#modalBuscaProductosXBodega').modal('toggle');
//					}else{
//						$('#cantidadTab').val(0);
//						$('#cantidadTab').focus();
//						$('#cantidadTab').click();
//						$('#cantidadTab').select();
//					}
				}else if($(this).val() != ''){
					calcularImporteCantidad();
				}
			}
			
		});
		$('#porcentajeTab').keydown(function(e){
			if(e.keyCode == 37){
				e.preventDefault();
				$('#cantidadTab').focus();
				$('#cantidadTab').click();
				$('#cantidadTab').select();
			}
		});
		$('#porcentajeTab').keydown(function(e){
			if(e.keyCode == 39){
				e.preventDefault();
				$('#bodegaTab').focus();
				$('#bodegaTab').click();
				$('#bodegaTab').select();
			}
		});
		$('#porcentajeTab').keydown(function(e){
			if(e.keyCode == 13){
				var porcentaje = 0;
				porcentaje = Number($('#porcentajeTab').val());
				var porcentajeMaximo = 0;
				porcentajeMaximo = Number($('#descuentoMaximoTab').val());
				if(isNaN($(this).val())){
					notificacionAlerta('El porcentaje de descuento debe ser un numero.');
					$('#cantidadTab').focus();
					$('#cantidadTab').click();
					$('#cantidadTab').select();
				}else if($(this).val() == ''){
					notificacionAlerta('El porcentaje de descuento estar vacio.');
					$('#cantidadTab').focus();
					$('#cantidadTab').click();
					$('#cantidadTab').select();
				}else if($(this).val() < 0){
					notificacionAlerta('El porcentaje de descuento no puede ser menor a 0');
					$('#cantidadTab').focus();
					$('#cantidadTab').click();
					$('#cantidadTab').select();
				}else if(porcentaje < porcentajeMaximo){
					calcularDescuento();
				}else if(porcentaje > porcentajeMaximo){
					$('#operacionValidaPermiso').text(6);
					$('#modalValidaUsuario').modal('toggle');
				}
			}
		});
		$('#bodegaTab').keydown(function(e){
			if(e.keyCode == 37){
				e.preventDefault();
				$('#porcentajeTab').focus();
				$('#porcentajeTab').click();
				$('#porcentajeTab').select();
			}
		});
		$('#bodegaTab').keydown(function(e){
			if(e.keyCode == 39){
				e.preventDefault();
				$('#enviaTab').focus();
				$('#enviaTab').click();
				$('#enviaTab').select();
			}
		});
		$('#enviaTab').keydown(function(e){
			if(e.keyCode == 37){
				e.preventDefault();
				$('#bodegaTab').focus();
				$('#bodegaTab').click();
				$('#bodegaTab').select();
			}
		});
		$('#enviaTab').keydown(function(e){
			if(e.keyCode == 39){
				e.preventDefault();
				$('#observacionesTab').focus();
				$('#observacionesTab').click();
				$('#observacionesTab').select();
			}
		});
		$('#observacionesTab').keydown(function(e){
			if(e.keyCode == 37){
				e.preventDefault();
				$('#enviaTab').focus();
				$('#enviaTab').click();
				$('#enviaTab').select();
			}
		});
		$('#observacionesTab').keydown(function(e){
			if(e.keyCode == 39){
				e.preventDefault();
			}
		});
		$('#observacionesTab').keydown(function(e){
			if(e.keyCode == 13){
				var cantidad = 0;
				var cantidadDisponible = 0;
				var porcentaje = 0;
				var descuento = 0;
				var descuentoMaximo = 0;
				cantidad = Number($('#cantidadTab').val());
				cantidadDisponible = Number($('#disponibleTab').val());
				porcentaje = Number($('#porcentajeTab').val());
				descuento = Number($('#descuentoTab').val());
				descuentoMaximo = Number($('#descuentoMaximoTab').val());
				if($('#codigoProductoTab').val()==''){
					notificacionAlerta('Codigo producto es requerido.');
					$('#codigoProductoTab').focus();
					$('#codigoProductoTab').click();
					$('#codigoProductoTab').select();
				}else if($('#cantidadTab').val()=='' || $('#cantidadTab').val()==0){
 					notificacionAlerta('Cantidad es requerida.');
					$('#cantidadTab').focus();
					$('#cantidadTab').click();
					$('#cantidadTab').select();
				}else if(cantidad > cantidadDisponible){
					var buscarOtrasBodegas = confirm('La cantidad deseada es mayor a la disponible en esta bodega, desea buscar en otras?');
					if(buscarOtrasBodegas == true){
						$('#modalBuscaProductosXBodega').modal('toggle');
					}else{
						$('#cantidadTab').val(0);
						$('#cantidadTab').focus();
						$('#cantidadTab').click();
						$('#cantidadTab').select();
					}
				}else if((porcentaje > descuentoMaximo) && descuento == 0){
					$('#operacionValidaPermiso').text(6);
					$('#modalValidaUsuario').modal('toggle');
				}else{
					if($('#uniqueId').text()==''){
						var envia;
						if($('#enviaTab').val()==0){
							envia = false;
						}else{
							envia = true;
						}
						$tabla = $('#datosVarios');
						$tabla.bootstrapTable('insertRow', {
					           index: 0,
					           row: {
					        	   indice: '',
					               codigoProducto: $('#codigoProductoTab').val(),
					               unidad: $('#medidaTab').val(),
					               cantidad: $('#cantidadTab').val(),
					               descripcion: $('#descripcionTab').val(),
					               porcentaje: $('#porcentajeTab').val(),
					               descuento: $('#descuentoTab').val(),
					               descuentoMaximo: $('#descuentoMaximoTab').val(),
					               envia: envia,
					               bodega: $('#bodegaTab').val(),
					               disponible: $('#disponibleTab').val(),
					               precio: $('#precioTab').val(),
					               importe: $('#importeTab').val(),
					               observaciones: $('#observacionesTab').val(),
					               esKit: $('#esKitTab').val(),
					               defectuoso: $('#esDfTab').val(),
					               correlativoKit: $('#correlativoKitTab').val(),
					               promocion: $('#promocionTab').val(),
					               codigoPromocion: $('#codigoPromocionTab').val(),
					               ordenCompra: $('#ordenCompraTab').val(),
					               unidadMedida: $('#unidadMedidaTab').val(),
					               
					           }
					       });
						limpiarDatosProductoTab();
						$('#datosVarios').bootstrapTable('resetView');
						$('#datosVarios').bootstrapTable('resetWidth');
					}else{
						var envia;
						if($('#enviaTab').val()==0){
							envia = false;
						}else{
							envia = true;
						}
						$tabla = $('#datosVarios');
						$tabla.bootstrapTable('updateRow', {
			                index: parseInt($('#tableIndexSelected').text()),
			                row: {
			                	codigoProducto: $('#codigoProductoTab').val(),
			                	unidad: $('#medidaTab').val(),
					            cantidad: $('#cantidadTab').val(),
					            descripcion: $('#descripcionTab').val(),
					            porcentaje: $('#porcentajeTab').val(),
					            descuento: $('#descuentoTab').val(),
					            descuentoMaximo: $('#descuentoMaximoTab').val(),
					            envia: envia,
					            bodega: $('#bodegaTab').val(),
					            disponible: $('#disponibleTab').val(),
					            precio: $('#precioTab').val(),
					            importe: $('#importeTab').val(),
					            observaciones: $('#observacionesTab').val(),
					            esKit: $('#esKitTab').val(),
					            defectuoso: $('#esDfTab').val(),
					            correlativoKit: $('#correlativoKitTab').val(),
					            promocion: $('#promocionTab').val(),
					            codigoPromocion: $('#codigoPromocionTab').val(),
					            ordenCompra: $('#ordenCompraTab').val(),
					            unidadMedida: $('#unidadMedidaTab').val(),
			                }
			            });
						$('#uniqueId').text('');
						limpiarDatosProductoTab();
						$('#datosVarios').bootstrapTable('resetView');
						$('#datosVarios').bootstrapTable('resetWidth');
					}
				}
				
				
			}
		});
		
		$(document).on('keydown', '#modalBuscaClientesTexto', function(e){
			if(e.keyCode == 13){
				if($('#modalBuscaClientesTexto').val() == ''){
					$('#modalBuscaClientesNotificacion').text('Debe ingresar un texto para buscar');
					$('#modalBuscaClientesTexto').focus();
					$('#modalBuscaClientesTexto').click();
					$('#modalBuscaClientesTexto').select();
				}else{
					$('#modalBuscaClientesNotificacion').text('');
					cargarTablaBusquedaClientes($('#modalBuscaClientesCriterio').val(), $('#modalBuscaClientesTexto').val()).done(function (data){
						$('#tablaClientes').bootstrapTable('destroy');
						$('#tablaClientes').bootstrapTable({
						    data: data,
						    search: true,
						    pagination: true,
						    height: 400,
						    pageSize: 25,
						    pageList: [25,50,75,100],
						    sortable: true,
						    columns: [{
						        field: 'nit',
						        title: 'Nit',
						        sortable: true
						    },{
						        field: 'nombre',
						        title: 'Nombre',
						        sortable: true
						    }
						    ]
						});
					});
				}
			}
		});
		$(document).on('keydown','#criterioBusquedaTexto', function(e){
			if(e.keyCode == 37){
				$('#criterioBusqueda').focus();
				$('#criterioBusqueda').click();
				$('#criterioBusqueda').select();
			}
		});
		$('#tablaPagos').on('load-error.bs.table', function (e, status) {
	        console.log('Event: load-error.bs.table');
	        console.log('error e: ' + e);
	        console.log('error e: ' + JSON.stringify(e));
	        console.log('error e: ' + status);
	        console.log('error e: ' + JSON.stringify(status));
	    })
		$(document).on('keydown','#criterioBusquedaTexto', function(e){
			if(e.keyCode == 13){
				$('#busquedaProductosTabla').bootstrapTable('destroy');
				cargarTablaBusquedaProductos2($('#criterioBusqueda').val(), $('#criterioBusquedaTexto').val(), $('#codigoPago').text()).done(function (data){
					if(data != ''){
						$('#busquedaProductosTabla').bootstrapTable({
						    data: data,
						    search: true,
						    pagination: true,
						    height: 400,
						    pageSize: 25,
						    pageList: [25,50,75,100],
						    sortable: true,
						    columns: [{
						        field: 'codigoProducto',
						        title: 'Codigo',
						        sortable: true
						    },{
						        field: 'bodega',
						        title: 'Bodega'
						    },{
						        field: 'disponible',
						        title: 'Disponible'
						    },{
						        field: 'precio',
						        title: 'Precio'
						    },{
						        field: 'descripcionProducto',
						        title: 'descripcion'
						    },{
						        field: 'backOrder',
						        title: 'Bck. Ord.'
						    },{
						        field: 'fechaEsperado',
						        title: 'F. Esp.'
						    },{
						        field: 'transito',
						        title: 'Transito'
						    },{
						        field: 'Df',
						        title: 'DF'
						    }
						    ]
						});
						$('#busquedaProductosTabla').bootstrapTable('resetView');
						$('#busquedaProductosTabla').bootstrapTable('resetWidth');
					}
				});
			}
		});
		$(document).on('keydown', function(e){
			if(e.keyCode == 46){
				if($('#filaSeleccionada').text()!=''){
					var borrarProducto = confirm('Desea borrar el producto de la tabla?');
					if(borrarProducto == true){
						$('#datosVarios').bootstrapTable('removeByUniqueId', $('#filaSeleccionada').text());
						$('#uniqueId').text('');
						limpiarDatosProductoTab();
						$('#datosVarios').bootstrapTable('resetView');
						$('#datosVarios').bootstrapTable('resetWidth');
					}else{
						$('#codigoProductoTab').focus();
						$('#codigoProductoTab').click();
						$('#codigoProductoTab').select();
					}
					
				}
			}
			
		});
		$(document).on('keydown', '#cantidadTab', function(e){
			// Allow: backspace, delete, tab, escape, enter and .
	        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
	             // Allow: Ctrl+A, Command+A
	            (e.keyCode == 65 && ( e.ctrlKey === true || e.metaKey === true ) ) || 
	             // Allow: home, end, left, right, down, up
	            (e.keyCode >= 35 && e.keyCode <= 40)) {
	                 // let it happen, don't do anything
	                 return;
	        }
	        // Ensure that it is a number and stop the keypress
	        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
	            e.preventDefault();
	        }
		});
		$(document).on('keydown', function (e){
			if(e.keyCode == 45){
				e.preventDefault();
				limpiarDatosProductoTab();
				$('#totalFilas').text('');
				$('#filaSeleccionada').text('');
				$('#textoProductoSeleccionado').text('');
				$('#textoProductoModo').text('Modo b\u00FAsqueda');
			}
		});
		$(document).on('keydown', '#codigoProductoTab', function(e){
			if(e.keyCode == 38){
				e.preventDefault();
				if($('#totalFilas').text() == ''){
					var numeroFilas = 0;
					numeroFilas = $('#datosVarios tbody tr').length;
					$('#totalFilas').text(numeroFilas);
				}
				if($('#filaSeleccionada').text() == '' && $('#totalFilas').text() > 0){
					$('#filaSeleccionada').text(1);
					var datosProductoSeleccionado;
					datosProductoSeleccionado = $('#datosVarios').bootstrapTable('getRowByUniqueId', $('#filaSeleccionada').text());
					llenarDatosProductoTab(datosProductoSeleccionado);
					$('#textoProductoSeleccionado').text('Producto ' + $('#filaSeleccionada').text() + ' de ' + $('#totalFilas').text());
					$('#textoProductoModo').text('Modo edici\u00F3n');
				}else{
					var numeroFila = Number($('#filaSeleccionada').text());
					if($('#filaSeleccionada').text() < $('#totalFilas').text()){
						numeroFila ++;
						$('#filaSeleccionada').text(numeroFila);
						var datosProductoSeleccionado;
						datosProductoSeleccionado = $('#datosVarios').bootstrapTable('getRowByUniqueId', $('#filaSeleccionada').text());
						llenarDatosProductoTab(datosProductoSeleccionado);
						$('#textoProductoSeleccionado').text('Producto ' + $('#filaSeleccionada').text() + ' de ' + $('#totalFilas').text());
						$('#textoProductoModo').text('Modo edici\u00F3n');
					}
				}
			}
			if(e.keyCode == 40){
				e.preventDefault();
				if($('#totalFilas').text() == ''){
					var numeroFilas = 0;
					numeroFilas = $('#datosVarios tbody tr').length;
					$('#totalFilas').text(numeroFilas);
				}
				if($('#filaSeleccionada').text() == '' && $('#totalFilas').text() > 0){
					$('#filaSeleccionada').text(1);
					var datosProductoSeleccionado;
					datosProductoSeleccionado = $('#datosVarios').bootstrapTable('getRowByUniqueId', $('#filaSeleccionada').text());
					llenarDatosProductoTab(datosProductoSeleccionado);
					$('#textoProductoSeleccionado').text('Producto ' + $('#filaSeleccionada').text() + ' de ' + $('#totalFilas').text());
					$('#textoProductoModo').text('Modo edici\u00F3n');
				}else{
					var numeroFila = Number($('#filaSeleccionada').text());
					if($('#filaSeleccionada').text() > 1){
						numeroFila --;
						$('#filaSeleccionada').text(numeroFila);
						var datosProductoSeleccionado;
						datosProductoSeleccionado = $('#datosVarios').bootstrapTable('getRowByUniqueId', $('#filaSeleccionada').text());
						llenarDatosProductoTab(datosProductoSeleccionado);
						$('#textoProductoModo').text('Modo edici\u00F3n');
						$('#textoProductoSeleccionado').text('Producto ' + $('#filaSeleccionada').text() + ' de ' + $('#totalFilas').text());
					}
				}
			}
		});
		$(document).on('keydown', '#codigoProductoTab', function(e){
			if(e.keyCode == 13){
				if($('#codigoPago').text()=='' || $('#fPago').val()== ''){
					notificacionAlerta('Debe seleccionar una forma de pago primero.');
					$('#fPago').focus();
					$('#fPago').click();
					$('#fPago').select();
				}else if($('#codigoProductoTab').val()==''){
					notificacionAlerta('Las busquedas, requieren un codigo de producto.');
					$('#codigoProductoTab').focus();
					$('#codigoProductoTab').click();
					$('#codigoProductoTab').select();
				}else{
					llenarDatosProducto($(this).val(), $('#codigoPago').text(), '');
				}
			}
		});
		$(document).on('keydown',function(e){
			if(e.keyCode == 40){
				var datosFila;
				var filaConvertida;
				if(totalFilas > 0 && filaActual <= totalFilas){
					console.log('fila actual flecha abajo: ' + filaActual);
					
					$('#tableIndexSelected').text(filaActual);
					console.log(JSON.stringify($('#datosVarios').bootstrapTable('getData')));
					alert('getRowByUniqueId: ' + JSON.stringify($('#datosVarios').bootstrapTable('getRowByUniqueId', filaActual)));
				}
			}
			
		});
		$('#fPago').focus();
		$('#fPago').click();
		$('#fPago').on('focus', function(){
			$(this).select();
		});
		$.fn.editable.defaults.mode = 'inline';
		inicializarTabla();
		$('#datosVarios tbody').empty();
		/**ACCIONES TABS**/
		$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
			if(($(e.target).closest('li').index() + 1)==1){
				$('#codigoProductoTab').focus();
				$('#codigoProductoTab').click();
				$('#codigoProductoTab').select();
			}
			if(($(e.target).closest('li').index() + 1)==4){
				$('#criterioBusqueda').focus();
				$('#criterioBusqueda').click();
				$('#criterioBusqueda').select();
			}
		})
		/**TABLA DATOS VARIOS**/
		$('#datosVarios').on('click', 'tbody tr', function(e) {
		    $(this).addClass('filaSeleccionada').siblings().removeClass('filaSeleccionada');
		});
		/**BOTONES F**/
		$('#f1').click(function(){
			$('#modalTiposPago').modal('toggle');
		});
		$('#f2').click(function(){
			$('#modalTiposMovimiento').modal('toggle');
		});
		$('#f3').click(function(){
			$('#modalBuscaDocumento').modal('toggle');
		});
		$('#f5').click(function(){
			$('#modalBuscaClientes').modal('toggle');
		});
		$('#1').click(function(){
			console.log($('#datosVarios').html());
		});
		$('#3').click(function(){
			$("#contenedorTablaDatosVarios").toggle( "fast", function() {
				$('#datosVarios').bootstrapTable('resetView');
			});
		});
		$('#2').click(function(){
			location.href = 'Logout';
		});
		$('#bodegaTab').on('change', function(){
			if($('#codigoProductoTab').val() == ''){
				notificacionAlerta('No puede cambiar bodega si no ha buscado un producto.');
				$('#codigoProductoTab').focus();
				$('#codigoProductoTab').click();
				$('#codigoProductoTab').select();
			}else{
				llenarDatosProducto($('#codigoProductoTab').val(), $('#codigoPago').text(), $('#bodegaTab').val());
			}
		});
		$('#criterioBusqueda').on('change', function(){
			$('#criterioBusquedaTexto').focus();
			$('#criterioBusquedaTexto').click();
			$('#criterioBusquedaTexto').select();
		});
		$('#exportar').click(function(){
			if($('#nDoc').val()==''){
				notificacionAlerta('Debe seleccionar una cotizacion primero');
			}else{
				generarCotizacion($('#codigoTipoDoc').text(), $('#nDoc').val(), $('#serieDocumento').text(), $('#codigoPago').text(), 1).done(function(data){
					$.each(data, function(i, e) {
						notificacionExito(e.mensaje);
			        });
					cargarDocumentoPDf($('#nDoc').val()).done(function (data){
//						$('#modalMostrarPDFGenerado').modal('toggle');
						$.each(data, function(i, e) {
							window.open("data:application/pdf;base64," + $.trim(e.mensaje));
//							
				        });
						
					});
				});
			}
		});
		$('#grabarDocumento').click(function(){
//			if($('#nDoc').val() == ''){
				grabarDatosEncabezado($('#nit').val(), $('#nombre').val(), $('#direccionFactura').val(), $('#telefono').val(), $('#direccionEnvio').val(), $('#codigoTipoDoc').text(), $('#nDoc').val(), $('#fechaVence').val(), $('#codigoPago').text(), $('#tCredito').val(), 'N', 0, 0, 100, 100, '', '', '', 0, 0, $('#fechaEntrega').val(), '','', 0, 0, 'N', '0', 0, '').done(function(data){
					if(data!=null){
						$('#nDoc').val(data);
					}
					grabarDatosdetalle($('#datosVarios').bootstrapTable('getData')).done(function(data){
						$.each(data, function(i, e) {
							notificacionExito('Producto '+ e.codigoProducto +' grabado en documento: ' +  $('#nDoc').val());
				        });
						setTimeout(function(){ 
							location.reload(); 
						}, 3000);
					});
//					
				});
//			}
		});
		$('#btnAgregar').click(function(){
			var filasVacias = 0;
			var indice = 0;
			var numeroFilas = $("#datosVarios tbody tr").length;
			
			var datosTabla = $('#datosVarios').bootstrapTable('getData')
			$.each(datosTabla, function(index,element){
				if(element.codigoProducto == '' || element.cantidad == 0){
					filasVacias = filasVacias + 1 ;
					indice = index;
					console.log(indice);
				}
			});
			if(filasVacias > 0){
				notificacionAlerta('No puede dejar filas vacias');
				$("#datosVarios tbody tr").eq(indice).children().eq(0).children().eq(0).click();
			}else{
				agregarFila();
				
			}
			
		});
		$('#btnModalBuscaProductosXBodegaCerrar').click(function(){
			$('#cantidadTab').val(0);
			calcularImporteCantidad();
			$('#cantidadTab').focus();
			$('#cantidadTab').click();
			$('#cantidadTab').select();
			$('#modalBuscaProductosXBodega').modal('toggle');
		});
		$('#btnValidarUsuario').click(function(){
			
			if(!(validacionCamposLLenos('validaPermisosUsuario','El usuario no puede quedar vac\u00EDo.')||
			   validacionCamposLLenos('validaPermisosClave','La clave no puede quedar vac\u00EDa.'))){
				console.log('usuario: ' +  $('#validaPermisosUsuario').val());
				console.log('clave: ' +  $('#validaPermisosClave').val());
				console.log('operacion: ' +  $('#operacionValidaPermiso').text());
				validaPermisos($('#validaPermisosUsuario').val(), $('#validaPermisosClave').val(), $('#operacionValidaPermiso').text()).done(function(data){
					if($.trim($('#operacionValidaPermiso').text()) == 6){
						if($.trim(data) == '0'){
							$('#txtNotificacionValidaPermisos').text('No posee permisos para proporcionar el descuento');
						}else{
							$('#modalValidaUsuario').modal('toggle');
							calcularDescuento();
							$('#porcentajeTab').focus();
							$('#porcentajeTab').click();
							$('#porcentajeTab').select();
						}
					}else if($.trim($('#operacionValidaPermiso').text()) == 7){
						if($.trim(data) == '0'){
							$('#txtNotificacionValidaPermisos').text('No posee permisos para crear facturas manuales.');
						}else{
							$('#modalValidaUsuario').modal('toggle');
							llenarDatosMovimiento($.trim($('#tDoc').val()));
						}
					}
					
				});
				
			}
		});
		$('#btnValidaPermisosCerrar').click(function(){
			if($.trim($('#operacionValidaPermiso').text()) == 6){
				$('#porcentajeTab').val(0);
				calcularDescuento();
				$('#modalValidaUsuario').modal('toggle');
				$('#porcentajeTab').focus();
				$('#porcentajeTab').click();
				$('#porcentajeTab').select();
			}else if($.trim($('#operacionValidaPermiso').text()) == 7){
				$('#modalValidaUsuario').modal('toggle');
				$('#tDoc').val('');
				$('#tDoc').focus();
				$('#tDoc').click();
				$('#tDoc').select();
			}
		});
		/**FECHAS**/
		$('.fechaEntrega').datepicker({
		    disableTouchKeyboard: true,
			format: 'dd/mm/yyyy',
		    startDate: '+1d',
		    enableOnReadonly: true,
		    daysOfWeekDisabled: '[0,6]',
		    language: 'es',
		    orientation: 'left top',
		    title: 'Fecha Entrega',
		    autoclose: true
		});
		/**ACCIONES DE GUARDAR**/
		$('#datosVarios').on('editable-shown.bs.table', function(e, field, row, $el, editable){
			$('#datosVarios').bootstrapTable('resetView');
			$('#datosVarios').bootstrapTable('resetWidth');
			editable.input.$input.val('');
			
		});
		$('#datosVarios').on('editable-hidden.bs.table', function(e, field, row, $el, reason){
			$('#datosVarios').bootstrapTable('resetView');
			$('#datosVarios').bootstrapTable('resetWidth');
		});
		$('#datosVarios').on('editable-save.bs.table', function(e,field, row, oldValue, $el){
			
			$('#datosVarios').bootstrapTable('resetView');
			$('#datosVarios').bootstrapTable('resetWidth');
			$table = $('#datosVarios');
			
			if(field == 'codigoProducto'){
				var $els = $table.find('.editable'),
	            next = $els.index($el) + 1;
				if($('#codigoPago').text()==''){
					notificacionAlerta('Necesita seleccionar un tipo de pago antes de poder buscar un producto.');
					$('#fPago').click();
					$('#fPago').focus();
				}else{
					traerDatosProducto(row.codigoProducto, $.trim($('#codigoPago').text())).done(function(data){
						console.log(JSON.stringify(data));
						$.each(data, function(i, e) {
							$table.bootstrapTable('updateRow', {
				                index: $('#indiceFila').text(),
				                row: {
				                	codigoProducto : $.trim(e.codigoProducto),
				                	descripcion : e.descripcion,
				                	unidad : e.medida,
				                	descuentoMaximo : e.descuentoMaximo,
				                	precio : e.precio,
				                	disponible : e.disponible,
				                	descuento: 0.00,
				                	importe: 0.00,
				                	esKit : e.esKit,
				                	bodega : e.codigoBodega,
				                	esDf: 'N',
				                	correlativoKit: e.correlativoKit,
				                	promocion: e.promocion,
				                	codigoPromocion: e.codigoPromocion,
				                	ordenCompra: e.ordenCompra,
				                	unidadMedida: e.unidadMedida,
				                	porcentaje: 0
				                }
				            });
							
				        });
						if(row.esKit == 'N'){
							console.log('no es kit el: ' + row.codigoProducto);
						}else{
							console.log('si es kit el: ' + row.codigoProducto);
						}
						
//						console.log('next: ' + next);
//						console.log('$els.index($el): '+$els.index($el));
//						console.log('largo: ' + $els.length);
//						if (next >= $els.length) {
//							console.log('escape');
//			                return;
//			            }
//						console.log('show');
//			            $els.eq(next).editable('show');
					});
					
				}
			}else if(field == 'cantidad'){
				var nuevoImporte;
				nuevoImporte = row.cantidad * row.precio;
				$('#datosVarios').bootstrapTable('updateRow',{
					index: $('#indiceFila').text(),
	                row: {
	                	importe: nuevoImporte
	                }
				});
				$('#datosVarios').bootstrapTable('resetView');
				$('#datosVarios').bootstrapTable('resetWidth');
//				var $els = $table.find('.editable'),
//	            next = $els.index($el) + 1;
//
//				console.log('next: ' + next);
//				console.log('$els.index($el): '+$els.index($el));
//				console.log('largo: ' + $els.length);
//	            if (next >= $els.length) {
//	                return;
//	            }
//	            $els.eq(next).editable('show');
			}
			
		});
		
		/**ACCIONES TABLA BOOTSTRAP**/
		
		$('#datosVarios').on('click-cell.bs.table', function (e, field, value, row, $element) {
			
			console.log('campo: ' + field);
		});
		$('#tablaClientes').on('load-success.bs.table', function (e, data) {
			$('#tablaClientes').bootstrapTable('resetView');
		});
		$('#busquedaProductosTabla').on('click-row.bs.table', function (e, row, $element) {
			if($('#uniqueId').text()==''){
				llenarDatosProducto($.trim(row.codigoProducto), $('#codigoPago').text(), '');
				$('#contenedorTabs a[href="#datosProducto"]').tab('show');
				$('#cantidadTab').focus();
				$('#cantidadTab').click();
				$('#cantidadTab').select();
			}else if($('#uniqueId').text()!=''){
				var datosProductoActual = JSON.parse(JSON.stringify($('#datosVarios').bootstrapTable('getRowByUniqueId', $('#uniqueId').text())));
				
				var confirmarEditar = confirm('Tiene seleccionado el producto: ' + datosProductoActual.codigoProducto + ' desea reemplazarlo con el producto que selecciono?');
				if(confirmarEditar == true){
					llenarDatosProducto(row.codigoProducto, $('#codigoPago').text(), '');
					$('#contenedorTabs a[href="#datosProducto"]').tab('show');
					$('#cantidadTab').focus();
					$('#cantidadTab').click();
					$('#cantidadTab').select();
				}else{
					
				}
			}
		});
		$('#datosVarios').on('click-row.bs.table', function (e, row, $element) {
//			totalFilas = $('#datosVarios tbody tr').length;
//			console.log('totalFilas: ' + totalFilas);
//			filaActual = $element.index()+1;
			llenarDatosProductoTab(row);
		});
		$('#tablaMovimientos').on('click-row.bs.table', function (e, row, $element) {
			$('#modalTiposMovimiento').modal('toggle');
			llenarDatosMovimiento(row.codigoMovimiento);
		});
		$('#tablaPagos').on('click-row.bs.table', function (e, row, $element) {
			$('#modalTiposPago').modal('toggle');
			if(row.idPago==3){
				$('#fPago').val($.trim(row.idPago));
				if($('#lCredito').val()==''){
					$('#modalIngresoNIT').modal('toggle');
					$('#modalNIT').val('');
				}else if(parseInt($('#lCredito').val())<=0){
					$('#modalIngresoNIT').modal('toggle');
					$('#modalNIT').val('');
				}
			}else{
				llenarDatosPago(row.idPago);
			}
		});
		$('#tablaModalBuscaProductosXBodega').on('click-row.bs.table', function (e, row, $element) {
			var disponible;
			var cantidad;
			disponible = Number(row.disponible);
			cantidad = Number($('#cantidadTab').val());
			if(cantidad > disponible){
				$('#mensajeModalBuscaProductosXBodega').text('No puede dejar la bodega '+row.bodega+' en negativos');
			}else{
				$('#disponibleTab').val($.trim(row.disponible));
				$('#bodegaTab').val($.trim(row.bodega));
				calcularImporteCantidad();
				$('#modalBuscaProductosXBodega').modal('toggle');
			}
		});
		$('#tablaDocumentos').on('click-row.bs.table', function (e, row, $element) {
			$('#modalBuscaDocumento').modal('toggle');
			$('#grabarDocumento').prop('disabled', true);
			llenarDatosEncabezado(row.noDocumento);
		});
		/**MODALS**/
		$('#modalBuscaProductosXBodega').on('shown.bs.modal', function (){
			$('#tablaModalBuscaProductosXBodega').bootstrapTable({
			    method: 'POST',
				url: 'CargarProductosXBodega',
			    contentType: 'application/x-www-form-urlencoded',
			    queryParams: function(p) {
			    	p.codigoProducto= $('#codigoProductoTab').val();
			    	p.codigoLista = 1;
			    	p.codigoPago = $('#codigoPago').text();
			    	return p;
			    },
			    search: true,
			    pagination: true,
			    showRefresh: true,
			    height: 400,
			    pageSize: 25,
			    pageList: [25,50,75,100],
			    sortable: true,
			    columns: [{
			        field: 'codigoProducto',
			        title: 'Codigo'
			    },{
			        field: 'bodega',
			        title: 'Bodega'
			    },{
			        field: 'disponible',
			        title: 'Disponible'
			    },{
			        field: 'precio',
			        title: 'Precio'
			    },{
			        field: 'backOrder',
			        title: 'Bck. Ord.'
			    },{
			        field: 'fechaEsperado',
			        title: 'F. Esp.'
			    },{
			        field: 'transito',
			        title: 'Transito'
			    },{
			        field: 'Df',
			        title: 'DF'
			    }
			    ]
			});
		});
		$('#modalValidaUsuario').on('shown.bs.modal', function () {
			$('#validaPermisosUsuario').focus();
			$('#validaPermisosUsuario').click();
			$('#validaPermisosUsuario').select();
		});
		$('#modalTiposMovimiento').on('hidden.bs.modal', function () {
			$('#modalTiposMovimientoNotificacion').text('');
			$('#modalTiposMovimientoNotificacionNormal').text('');
			$('#tablaMovimientos').bootstrapTable('destroy');
		});
		$('#modalTiposMovimiento').on('shown.bs.modal', function () {
			$tablaMovimientos = $('#tablaMovimientos');
			$tablaMovimientos.bootstrapTable({
				method: 'POST',
			    url: 'CargarMovimientos',
			    search: true,
			    pagination: true,
			    height: 400,
			    pageSize: 25,
			    pageList: [25,50,75,100],
			    sortable: true,
			    columns: [{
			    	field: 'indice',
			    	title: '#',
			    	formatter: formatoIndice
			    },{
			        field: 'codigoMovimiento',
			        title: 'ID',
			        visible: false
			    },{
			        field: 'movimiento',
			        title: 'Descripcion Movimiento'
			    }]
			});
		});
		
		$('#modalTiposPago').on('hidden.bs.modal', function () {
			$('#modalTiposPagoNotificacion').text('');
			$('#modalTiposPagoNotificacionNormal').text('');
			$('#tablaPagos').bootstrapTable('destroy');
		});
		$('#modalTiposPago').on('shown.bs.modal', function () {
			$('#modalTiposPagoNotificacionNormal').text('Cargando Tabla Pagos...');
			cargarTiposPago().done(function (data){
				$('#modalTiposPagoNotificacionNormal').text('');
				$tablaMovimientos = $('#tablaPagos');
				$tablaMovimientos.bootstrapTable({
					data : data,
				    search: true,
				    height: 400,
				    pagination: true,
				    pageSize: 25,
				    pageList: [10,25,50,100],
				    sortable: true,
				    columns: [{
				    	field: 'indice',
				    	title: '#',
				        sortable: true,
				    	formatter: formatoIndice
				    },{
				        field: 'idPago',
				        title: 'ID',
				        visible: false
				    },{
				        field: 'descripcion',
				        title: 'Descripcion Pago',
				        sortable: true
				    }]
				});
			});
		});
		$('#modalBuscaClientes').on('shown.bs.modal', function () {
			$('#modalBuscaClientesTexto').focus();
			$('#modalBuscaClientesNotificacion').text('');
		})
		$('#modalBuscaDocumento').on('shown.bs.modal', function () {
			$tablaMovimientos = $('#tablaDocumentos');
			$tablaMovimientos.bootstrapTable({
				method: 'POST',
			    url: 'CargaEncabezadoCotizaciones',
			    search: true,
			    pagination: true,
			    height: 400,
			    showRefresh: true,
			    pageSize: 25,
			    pageList: [25,50,75,100],
			    sortable: true,
			    columns: [{
			        field: 'noDocumento',
			        title: 'No.'
			    },{
			        field: 'nit',
			        title: 'NIT'
			    },{
			        field: 'nombre',
			        title: 'Nombre'
			    },{
			        field: 'montoTotal',
			        title: 'Monto'
			    },{
			        field: 'fVence',
			        title: 'Fecha'
			    }]
			});
		});
		$('#modalIngresoNIT').on('shown.bs.modal', function () {
		    $('#modalNIT').focus();
		})
		$('#modalIngresoNIT').on('hidden.bs.modal', function () {
			if($('#codigoPago').text()==''){
				$('#fPago').val('');
			    $('#fPago').focus();
			}else{
				
			}
		    
		})
		$('#modalBuscaProductosXBodega').on('hidden.bs.modal', function (){
			$('#tablaModalBuscaProductosXBodega').bootstrapTable('destroy');
		});
		$('#modalValidaUsuario').on('hidden.bs.modal', function () {
			$('#validaPermisosUsuario').val('');
			$('#validaPermisosClave').val('');
			$('#operacionValidaPermiso').text('');
			$('#txtNotificacionValidaPermisos').text('');
		});
		/**ACCIONES INPUTS**/
		$(document).on('keydown','#modalNIT',function(e){
			if(e.keyCode==13){
				if($('#modalNIT').val()==''){
					alert('Para buscar se necesita un NIT');
				}else{
					cargarNIT($('#modalNIT').val()).done(function(data) {
						
						$.each(data, function(index, element) {
							if(element.limiteCredito>0){
								$('#lCredito').val(parseFloat(element.limiteCredito).toFixed(2));
								$('#nit').val($.trim($('#modalNIT').val()));
								$('#nombre').val($.trim(element.nombreCliente));
								$('#telefono').val($.trim(element.telefono));
								$('#direccionFactura').val($.trim(element.direccionFacturacion));
								$('#direccionEnvio').val($.trim(element.direccionEnvio));
								
								
								$('#modalIngresoNIT').modal('toggle');
								llenarDatosPago($('#fPago').val());
							}else{
								$('#modalIngresoNIT').modal('toggle');
								$('#fPago').val('');
								alert('El limite de credito de el nit ingresado no es mayor a cero');
								$('#fPago').focus();
							}
						});
					});
				}
			}
		});
//		$(document).on('keydown','#fPago', function(e){
		$('#fPago').keydown(function(e){
			if(e.keyCode == 13){
				$('#codigoPago').text('');
				if($('#fPago').val()==''){
					alert('Debe ingresar algo');
				}else if(isNaN($('#fPago').val())){
					alert('Debe ingresar un Numero');
					$('#fPago').val('');
					$('#fPago').focus();
				}else if(parseInt($('#fPago').val())==3){
					if($('#lCredito').val()==''){
						$('#modalIngresoNIT').modal('toggle');
						$('#modalNIT').val('');
					}else if(parseInt($('#lCredito').val())<=0){
						$('#modalIngresoNIT').modal('toggle');
						$('#modalNIT').val('');
					}
				}else{
					llenarDatosPago($('#fPago').val());
					$('#codigoProductoTab').focus();
					$('#codigoProductoTab').click();
					$('#codigoProductoTab').select();
				}
			}
		});
		$(document).on('keydown','#tDoc', function(e){
			if(e.keyCode==13){
				$('#codigoTipoDoc').text('');
				$('#fechaVence').val('');
				if($('#tDoc').val()==''){
					notificacionAlerta('Necesita ingresar un numero que corresponda a un tipo de documento.');
				}else if(isNaN($('#tDoc').val())){
					notificacionAlerta('Debe buscar un numero.');
				}else if($('#tDoc').val() == 1){
					$('#operacionValidaPermiso').text(7);
					$('#modalValidaUsuario').modal('toggle');
				}else{
					llenarDatosMovimiento($('#tDoc').val());
				}
			}
		});
		$(document).on('keydown','#nit', function(e){
			if(e.keyCode==13){
				$('#lCredito').val('');
				$('#nombre').val('');
				$('#telefono').val('');
				$('#direccionFactura').val('');
				$('#direccionEnvio').val('');
				if($('#nit').val()==''){
					notificacionAlerta('Necesita ingresar un NIT para buscar');
				}else{
					llenarDatosNit($('#nit').val());
					
				}
			}
		});
	});
	//FUNCIONES
	function inicializarTabla(){
		$('#datosVarios').bootstrapTable({
	   		height: 400,
			classes: 'table table-condensed table-hover',
			pagination: true,
			pageSize: 50,
			pageList: [10, 25, 50, 75, 100, 200],
			undefinedText: '',
	        checkboxHeader: false,
	        showFooter: true,
	        uniqueId: 'indice',
			formatNoMatches: function () {
	            return '';
	        },
		columns: [
		{
           field: 'indice',
           title: '#',
           formatter: formatoIndice 
           
		},{
           field: 'codigoProducto',
           title: 'C\u00F3digo',
//           editable: {
//               type: 'number',
//               title: 'C\u00F3digo',
//               showbuttons: false,
//               emptytext: '---',
//               placement: 'bottom',
//               placeholder: 'Ingrese Producto',
//               savenochange: true,
//               validate: function(value){
//            	   value = $.trim(value);
//            	   if(!value){
//            		   
//            		   return 'El Codigo deProducto no puede quedar vacio';
//            	   }
//                   return '';
//               }
//           }
       }, 
       {
           field: 'unidad',
           title: 'UM',
       },
       {
           field: 'descripcion',
           title: 'Descripcion',
           width: '500px'
       },
       {
           field: 'cantidad',
           title: 'Cant.',
//           editable: {
//               type: 'number',
//               title: 'Cantidad',
//               showbuttons: false,
//               emptytext: '---',
//               placeholder: 'Ingrese Cantidad',
//               placement: 'bottom',
//               defaultValue: '',
//               validate: function(value){
//            	   value = $.trim(value);
//            	   if(!value){
//            		   
//            		   return 'La cantidad no puede quedar vacia';
//            	   }
//                   if (isNaN(value)) {
//                       return 'Necesita ingresar un numero';
//                   }
//                   return '';
//               }
//           }
       },
       {
           field: 'disponible',
           title: 'Disp.',
       },
       {
           field: 'precio',
           title: 'PU',
           formatter: formatoValores 
       },
       {
           field: 'porcentaje',
           title: '%',
//           editable: {
//               type: 'number',
//               title: 'Porcentaje',
//               showbuttons: false,
//               emptytext: '---',
//               placeholder: 'Porcentaje',
//               placement: 'bottom',
//               validate: function(value){
//            	   value = $.trim(value);
//            	   if(!value){
//            		   value = 0;
////            		   return 'La cantidad no puede quedar vacia';
//            	   }
//                   if (isNaN(value)) {
//                       return 'Necesita ingresar un numero';
//                   }
//                   return '';
//               },
//               display: function(value) {
//         	      $(this).text(value + '%');
//               }
//           }
       },
       {
           field: 'descuento',
           title: 'Desc.',
           formatter: formatoValores,
           footerFormatter: formatoTextoTotal 
       },
       {
           field: 'importe',
           title: 'Importe',
           formatter: formatoValores,
           footerFormatter: formatoImporte 
       },
       {
           field: 'bodega',
           title: 'BD',
//           editable: {
//               type: 'number',
//               title: 'Bodega',
//               showbuttons: false,
//               emptytext: '---',
//               placeholder: 'Bodega',
//               placement: 'bottom',
//               validate: function(value){
//            	   value = $.trim(value);
//            	   if(!value){
//            		   return 'Debe ingresar un codigo de bodega';
//            	   }
//                   if (isNaN(value)) {
//                       return 'Necesita ingresar un numero';
//                   }
//                   return '';
//               }
//           }
       },
       {
           field: 'envia',
           title: 'Env',
           checkbox: true
       },
       {
           field: 'descuentoMaximo',
           title: 'DM',
           formatter: formatoPorcentaje,
       },
       {
           field: 'observaciones',
           title: 'Observaciones',
//           editable: {
//               type: 'text',
//               title: 'Observaciones',
//               showbuttons: false,
//               emptytext: '---',
//               placeholder: 'Observaciones',
//               placement: 'bottom',
//               validate: function(value){
//            	   value = $.trim(value);
//            	   if(!value){
//            		   value='';
//            	   }
//                   return '';
//               }
//           }
       },
       {
           field: 'esKit',
           title: 'Kit',
           visible: false
       },
       {
           field: 'defectuoso',
           title: 'DF',
           visible: false
       },
       {
           field: 'promocion',
           title: 'Promo',
           visible: false
       },
       {
           field: 'correlativoKit',
           title: 'Corr.Kit.',
           visible: false
       },
       {
           field: 'codigoPromocion',
           title: 'Cod.Prom.',
           visible: false
       },
       {
           field: 'ordenCompra',
           title: 'Ord.Com.',
           visible: false
       },
       {
           field: 'unidadMedida',
           title: 'Cod.Medida.',
           visible: false
       }
       ]
		});
	}
	function llenarDatosProductoPorBodega(codigoProducto, tipoPago, codigoBodega){
		traerDatosProducto(codigoProducto, tipoPago, codigoBodega).done(function(data){
			$.each(data, function(i, e) {
				$('#codigoProductoTab').val($.trim(e.codigoProducto));
				$('#descripcionTab').val($.trim(e.descripcion));
				$('#medidaTab').val(e.medida);
				$('#descuentoMaximoTab').val(parseFloat(e.descuentoMaximo).toFixed(2));
				$('#precioTab').val(parseFloat(e.precio).toFixed(2));
				$('#disponibleTab').val(e.disponible);
				$('#descuentoTab').val(parseFloat(0).toFixed(2));
				$('#importeTab').val(parseFloat(0).toFixed(2));
				$('#esKitTab').val(e.esKit);
				$('#bodegaTab').val(e.codigoBodega);
				$('#esDfTab').val('N');
				$('#enviaTab').val('0');
				$('#correlativoKitTab').val(e.correlativoKit);
				$('#promocionTab').val(e.promocion);
				$('#codigoPromocionTab').val(e.codigoPromocion);
				$('#ordenCompraTab').val(e.ordenCompra);
				$('#unidadMedidaTab').val(e.unidadMedida);
				$('#porcentajeTab').val(parseFloat(0).toFixed(2));
				$('#cantidadTab').val(0);
				$('#cantidadTab').focus();
				$('#cantidadTab').click();
				$('#cantidadTab').select();
	        });
		});
	}
	function cargarTiposPago(){
		return $.ajax({
			      url: "CargarTiposPago",
			      method: "POST",
			      dataType: 'json',
			      error: function(data) {
			    	  console.log(JSON.stringify(data));
					   var obj = JSON.parse(JSON.stringify(data));
					   $('#modalTiposPagoNotificacionNormal').text('');
					   $('#modalTiposPagoNotificacion').text(obj.responseText);
				  }
		    });
	}
	function llenarDatosProducto(codigoProducto, tipoPago, codigoBodega){
		traerDatosProducto(codigoProducto, tipoPago, codigoBodega).done(function(data){
			$.each(data, function(i, e) {
				$('#codigoProductoTab').val($.trim(e.codigoProducto));
				$('#descripcionTab').val($.trim(e.descripcion));
				$('#medidaTab').val(e.medida);
				$('#descuentoMaximoTab').val(parseFloat(e.descuentoMaximo).toFixed(2));
				$('#precioTab').val(parseFloat(e.precio).toFixed(2));
				$('#disponibleTab').val(e.disponible);
				$('#descuentoTab').val(parseFloat(0).toFixed(2));
				$('#importeTab').val(parseFloat(0).toFixed(2));
				$('#esKitTab').val(e.esKit);
				$('#bodegaTab').val(e.codigoBodega);
				$('#esDfTab').val('N');
				$('#enviaTab').val('0');
				$('#correlativoKitTab').val(e.correlativoKit);
				$('#promocionTab').val(e.promocion);
				$('#codigoPromocionTab').val(e.codigoPromocion);
				$('#ordenCompraTab').val(e.ordenCompra);
				$('#unidadMedidaTab').val(e.unidadMedida);
				$('#porcentajeTab').val(parseFloat(0).toFixed(2));
				$('#cantidadTab').val(0);
				$('#cantidadTab').focus();
				$('#cantidadTab').click();
				$('#cantidadTab').select();
	        });
		});
	}
	function traerDatosProducto(codigoProducto, tipoPago, codigoBodega){
		return $.ajax({
			   method: 'POST',
			   url: 'CargarDatosProducto',
			   dataType: 'json',
			   data: {
				   codigoProducto: codigoProducto,
				   codigoPago: tipoPago,
				   codigoLista: 1,
				   codigoBodega: codigoBodega
			   },
			   error: function(data) {
			    	  console.log(JSON.stringify(data));
					   var obj = JSON.parse(JSON.stringify(data));
					   notificacionError(obj.responseText);
			   }
		   });
	}
	function traerTipoPago(codigo_pago){
		return $.ajax({
		      url: "CargaTiposPago",
		      method: "POST",
		      dataType: 'json',
		      data: {
		    	  codigoPago: codigo_pago
		      }
		    });
	}
	function llenarDatosPago(codigo_pago){
		traerTipoPago(codigo_pago).done(function(data) {
			if(data==''){
				notificacionAlerta('El tipo de pago ingresado no devolvio resultados');
			}else{
				$.each(data, function(index, element) {
					$('#codigoPago').text($.trim(element.idPago));
					$('#fPago').val($.trim(element.descripcion));
					if(element.esCredito=='N'){
						$('#tCredito').val(1);
					}else{
						$('#tCredito').val(2);
					}
				});
			}
		});
	}
	function cargarNIT(nit){
		return $.ajax({
		      url: "CargaDatosNIT",
		      method: "POST",
		      dataType: 'json',
		      error: function(data) {
				   var obj = JSON.parse(JSON.stringify(data));
				   notificacionError(obj.responseText);
			  },
		      data: {
		    	  nit: nit
		      }
		    });
	}
	function llenarDatosNit(nit){
		cargarNIT(nit).done(function(data) {
			
			var obj = JSON.parse(JSON.stringify(data));
			if(data==''){
				notificacionAlerta('NIT no devolvio resultados');
				$('#nit').val('');
				$('#nit').focus();
			}else{
				$.each(data, function(index, element) {
					$('#lCredito').val(parseFloat(element.limiteCredito).toFixed(2));
					$('#nombre').val($.trim(element.nombreCliente));
					$('#telefono').val($.trim(element.telefono));
					$('#direccionFactura').val($.trim(element.direccionFacturacion));
					$('#direccionEnvio').val($.trim(element.direccionEnvio));
				});
			}
			
		});
	}
	function cargarMovimiento(codigoMovimiento){
		return $.ajax({
		      url: "CargarMovimiento",
		      method: "POST",
		      dataType: 'json',
		      error: function(data) {
				   var obj = JSON.parse(JSON.stringify(data));
				   notificacionError(obj.responseText);
			  },
		      data: {
		    	  codigoMovimiento: codigoMovimiento
		      }
		    });
	}

	function llenarDatosMovimiento(codigoMovimiento){
		cargarMovimiento(codigoMovimiento).done(function(data){
			$('#tDoc').val('');
			if(data==''){
				notificacionAlerta('Movimiento no asignado a usuario');
			}else{
				$.each(data, function(index, element) {
					$('#codigoTipoDoc').text($.trim(element.codigoMovimiento));
					$('#tDoc').val($.trim(element.movimiento));
					$('#fechaVence').val($.trim(element.fechaFormateada));
				});
			}
		});
	}
	function cargarMovimientos(){
		return $.ajax({
		      url: "CargarMovimientos",
		      method: "POST",
		      dataType: 'json',
		      error: function(data) {
				   var obj = JSON.parse(JSON.stringify(data));
				   notificacionError(obj.responseText);
			  },
		    });
	}
	function cargarDocumentoPDf(nombreDocumento){
		console.log('doc: ' + nombreDocumento);
		return $.ajax({
		      url: "CargarDocumentoPDF",
		      method: "POST",
		      dataType: 'json',
		      data: {
		    	  nombreDocumento: nombreDocumento
		      },
		      error: function(data) {
				   var obj = JSON.parse(JSON.stringify(data));
				   notificacionError(obj.responseText);
			  },
		    });
	}
	function generarCotizacion(tipoDocumento, numeroDocumento, serieDocumento, tipoPago, caja){
		return $.ajax({
		      url: "CrearReporte",
		      method: "POST",
		      dataType: 'json',
		      data: {
		    	  tipoDoc: tipoDocumento,
		    	  noDoc: numeroDocumento,
		    	  serie: serieDocumento,
		    	  tipoPago: tipoPago,
		    	  caja: caja
		      },
		      error: function(data) {
				   var obj = JSON.parse(JSON.stringify(data));
				   notificacionError(obj.responseText);
			  },
		    });
	}
	function grabarDatosdetalle(datosDetallesProducto){
		return $.ajax({
		      url: "GrabarDetalle",
		      method: "POST",
		      dataType: 'json',
		      data: {
		    	  datosDetallesProducto: JSON.stringify(datosDetallesProducto)
		      },
		      error: function(data) {
				   var obj = JSON.parse(JSON.stringify(data));
				   notificacionError(obj.responseText);
			  },
		    });
	}
	function grabarDatosEncabezado(nit, nombreCliente, direccionFactura, telefono, direccionEnvio, tipoDocumento,
									noDoc, fechaVence, tipoPago, tipoCredito, autoriza, cargosEnvio, otrosCargos,
									montoVenta, montoTotal, serieDev, noDocDev, observaciones, tipoNota, caja,
									fechaEntrega, codDept, codGen, noConsigna, codMovDev, generaSolicitud,
									tipoPagoNC, cantidadDevolver, autorizoDespacho
									){
		return $.ajax({
		      url: "GrabarEncabezado",
		      method: "POST",
		      dataType: 'json',
		      data: {
		    	  nit : nit,
		    	  nombreCliente : nombreCliente,
		    	  direccionFactura : direccionFactura,
		    	  telefono: telefono,
		    	  direccionEnvio : direccionEnvio,
		    	  tipoDocumento : tipoDocumento,
		    	  noDoc: noDoc,
		    	  fechaVence : fechaVence,
		    	  tipoPago : tipoPago,
		    	  tipoCredito : tipoCredito,
		    	  autoriza : autoriza,
		    	  cargosEnvio : cargosEnvio,
		    	  otrosCargos : otrosCargos,
		    	  montoVenta : montoVenta,
		    	  montoTotal : montoTotal,
		    	  serieDev : serieDev,
		    	  noDocDev : noDocDev,
		    	  observaciones : observaciones,
		    	  tipoNota : tipoNota,
		    	  caja : caja,
		    	  fechaEntrega : fechaEntrega, 
		    	  codDept : codDept, 
		    	  codGen : codGen, 
		    	  noConsigna : noConsigna,
		    	  codMovDev : codMovDev,
		    	  generaSolicitud : generaSolicitud, 
		    	  tipoPagoNC : tipoPagoNC, 
		    	  cantidadDevolver : cantidadDevolver, 
		    	  autorizoDespacho : autorizoDespacho
		      },
		      error: function(data) {
				   var obj = JSON.parse(JSON.stringify(data));
				   notificacionError(obj.responseText);
			  },
		    });
	}
	function cargarDatosEncabezado(noDocumento){
		return $.ajax({
		      url: "CargarDatosEncabezado",
		      method: "POST",
		      dataType: 'json',
		      data: {
		    	  numeroDocumento : noDocumento  
		      },
		      error: function(data) {
		    	  console.log(JSON.stringify(data));
				   var obj = JSON.parse(JSON.stringify(data));
				   notificacionError(obj.responseText);
			  },
		    });
	}
	function llenarDatosEncabezado(noDocumento){
		cargarDatosEncabezado(noDocumento).done(function(data){
			$.each(data, function(index, element) {
				$('#codigoTipoDoc').text(element.tipoDocumento);
				$('#tDoc').val(element.descripcionDocumento);
				$('#fechaVence').val(element.fVence);
				$('#nit').val(element.nit);
				$('#nDoc').val(noDocumento);
				llenarDatosNit(element.nit);
				llenarDatosPago(element.tipoPago);
				llenarDatosDetalle(noDocumento);
			});
		});
	}
	function cargarDatosDetalle(noDocumento){
		return $.ajax({
		      url: "CargarDatosDetalleCotizacion",
		      method: "POST",
		      dataType: 'json',
		      data: {
		    	  numeroCotizacion : noDocumento  
		      },
		      error: function(data) {
		    	  console.log(JSON.stringify(data));
				   var obj = JSON.parse(JSON.stringify(data));
				   notificacionError(obj.responseText);
			  },
		    });
	}
	function llenarDatosDetalle(noDocumento){
		cargarDatosDetalle(noDocumento).done(function (data){
			$('#datosVarios').bootstrapTable('removeAll');
			
			$.each(data, function(index, element) {
				var envia;
				if(element.envia == 1){
					envia = true;
				}else{
					envia = false;
				}
				$tabla = $('#datosVarios');
				$tabla.bootstrapTable('insertRow', {
					index: ((element.correlativo) - 1),
					row: {
						codigoProducto: element.codigoProducto,
						unidad: element.medida,
						descripcion: element.descripcionProducto,
						precio: element.precioUnitario,
						disponible: element.disponible,
						importe: element.totalLinea,
						observaciones: element.observaciones,
						bodega: element.codigoBodega,
						porcentaje: element.porcentajeDesc,
						descuento: element.descuento,
						descuentoMaximo: element.porcentajeDescMin,
						esKit: element.esKit,
						df: element.df,
						envia: envia,
						cantidad: element.cantidad
					}
				});
			});
		});
	}
	function agregarFila(){
		$tabla = $('#datosVarios');
		$tabla.bootstrapTable('insertRow', {
	           index: 0,
	           row: {
	               codigoProducto: '',
	               unidad: '',
	               cantidad: '',
	               descripcion: '',
	               porcentaje: '',
	               descuento: '',
	               descuentoMaximo: '',
	               envia: '',
	               bodega: '',
	               disponible: '',
	               precio: '',
	               importe: '',
	               observaciones: '',
	           }
	       });
	}
	function cargarBodegas(){
		return $.ajax({
			   method: 'POST',
			   url: 'CargarBodegas',
			   dataType: 'json',
			   error: function(data) {
			    	  console.log(JSON.stringify(data));
					   var obj = JSON.parse(JSON.stringify(data));
					   notificacionError(obj.responseText);
			   }
		   });
	}
	function llenarBodegas(){
		cargarBodegas().done(function (data){
			var bodega= $("#bodegaTab");
			bodega.empty();
//			bodega.append("<option value='0'>Codigo Emisor</option>");
			$.each(data, function(index, element) {
				bodega.append("<option value='" + element.codigoBodega + "'>" + $.trim(element.descripcion) + "</option>");
			});
		});
	}
	function notificacionError(mensaje){
		$.notify({
			// options
			message: mensaje 
		},{
			// settings
			element: 'body',
			type: 'danger',
			delay: 10000,
			spacing: 2,
			showProgressbar: false,
			placement: {
				from: "bottom",
				align: "center"
			},
			animate: {
				enter: 'animated fadeInDown',
				exit: 'animated fadeOutUp'
			},
			newest_on_top: true,
			allow_dismiss: true,
		});
	}
	function notificacionExito(mensaje){
		$.notify({
			// options
			message: mensaje 
		},{
			// settings
			element: 'body',
			type: 'success',
			delay: 2500,
			spacing: 2,
			showProgressbar: false,
			placement: {
				from: "top",
				align: "right"
			},
			animate: {
				enter: 'animated fadeInDown',
				exit: 'animated fadeOutUp'
			},
			newest_on_top: true,
			allow_dismiss: false,
			template: '<div data-notify="container" class="col-xs-11 col-sm-3 alert alert-{0}" role="alert">' +
			'<span data-notify="title">{1}</span>' +
			'<span data-notify="message"><strong>{2}</strong></span>' +
		'</div>'
		});
	}
	function notificacionInfo(mensaje){
		$.notify({
			// options
			message: mensaje 
		},{
			// settings
			element: 'body',
			type: 'info',
			delay: 5000,
			spacing: 2,
			showProgressbar: false,
			placement: {
				from: "top",
				align: "left"
			},
			animate: {
				enter: 'animated fadeInDown',
				exit: 'animated fadeOutUp'
			},
			newest_on_top: true,
			allow_dismiss: true,
			template: '<div data-notify="container" class="col-xs-11 col-sm-3 alert alert-{0}" role="alert">' +
			'<span data-notify="title">{1}</span>' +
			'<span data-notify="message"><strong>{2}</strong></span>' +
		'</div>'
		});
	}
	function notificacionAlerta(mensaje){
		$.notify({
			// options
			message: mensaje 
		},{
			// settings
			element: 'body',
			type: 'warning',
			delay: 10000,
			spacing: 2,
			showProgressbar: false,
			placement: {
				from: "top",
				align: "center"
			},
			animate: {
				enter: 'animated fadeInDown',
				exit: 'animated fadeOutUp'
			},
			newest_on_top: true,
			allow_dismiss: true
		});
	}
	function formatoIndice(value, row, index){
		row.indice = index + 1;
		return index + 1;
	}
	function formatoValores(value, row, index){
		if(value == ''){
			value = 0;
		}
		return parseFloat(value).toFixed(2);
	}
	function formatoPorcentaje(value, row, index){
		if(value == ''){
			value = 0;
		}
		return parseFloat(value).toFixed(2)+'%';
	}
	function formatoTextoTotal(data){
		return 'Total:'
	}
	function formatoImporte(data) {
        var total = 0;
        $.each(data, function (i, row) {
            total += +(row.importe);
        });
        return parseFloat(total).toFixed(2);
    }
	function calcularImporteCantidad(){
		var importe;
		importe = $('#cantidadTab').val() * parseFloat($('#precioTab').val()).toFixed(2);
		$('#importeTab').val(parseFloat(importe).toFixed(2));
		if($('#porcentajeTab').val() != 0 || $('#porcentajeTab').val() != ''){
			var importe;
			var descuento;
			descuento = ($('#porcentajeTab').val()/100) * (parseFloat($('#cantidadTab').val()).toFixed(2)*parseFloat($('#precioTab').val()).toFixed(2));
			$('#descuentoTab').val(parseFloat(descuento).toFixed(2));
			$('#porcentajeTab').val(parseFloat($('#porcentajeTab').val()).toFixed(2));
			importe = (parseFloat($('#cantidadTab').val()).toFixed(2)*parseFloat($('#precioTab').val()).toFixed(2)) - (parseFloat(descuento).toFixed(2)) ;
			console.log('descuento: ' + descuento);
			console.log('importe: ' + importe)
			$('#importeTab').val(parseFloat(importe).toFixed(2));
			$('#porcentajeTab').focus();
			$('#porcentajeTab').click();
			$('#porcentajeTab').select();
		}
	}
	function calcularDescuento(){
		var importe;
		var descuento;
		var porcentaje;
		porcentaje = parseFloat($('#porcentajeTab').val()).toFixed(2);
		descuento = (parseFloat(porcentaje).toFixed(2)/100) * (parseFloat($('#cantidadTab').val()).toFixed(2)*parseFloat($('#precioTab').val()).toFixed(2));
		importe = (parseFloat($('#cantidadTab').val()).toFixed(2)*parseFloat($('#precioTab').val()).toFixed(2)) - (parseFloat(descuento).toFixed(2)) ;
		$('#descuentoTab').val(parseFloat(descuento).toFixed(2));
		$('#porcentajeTab').val(parseFloat($('#porcentajeTab').val()).toFixed(2));
		console.log('porcentaje: ' + porcentaje);
		console.log('descuento: ' + descuento);
		console.log('importe: ' + importe)
		$('#importeTab').val(parseFloat(importe).toFixed(2));
		$('#bodegaTab').focus();
		$('#bodegaTab').select();
	}
	function llenarDatosProductoTab(datosFila){
		var arregloDatos = JSON.parse(JSON.stringify(datosFila));
		$('#uniqueId').text($.trim(arregloDatos.indice));
		$('#codigoProductoTab').val($.trim(arregloDatos.codigoProducto));
		$('#descripcionTab').val($.trim(arregloDatos.descripcion));
		$('#medidaTab').val(arregloDatos.unidad);
		$('#descuentoMaximoTab').val(parseFloat(arregloDatos.descuentoMaximo).toFixed(2));
		$('#precioTab').val(parseFloat(arregloDatos.precio).toFixed(2));
		$('#disponibleTab').val(arregloDatos.disponible);
		$('#descuentoTab').val(parseFloat(arregloDatos.descuento).toFixed(2));
		$('#importeTab').val(parseFloat(arregloDatos.importe).toFixed(2));
		$('#esKitTab').val(arregloDatos.esKit);
		$('#bodegaTab').val($.trim(arregloDatos.bodega));
		$('#esDfTab').val(arregloDatos.defectuoso);
		$('#correlativoKitTab').val(arregloDatos.correlativoKit);
		$('#promocionTab').val(arregloDatos.promocion);
		$('#codigoPromocionTab').val(arregloDatos.codigoPromocion);
		$('#ordenCompraTab').val(arregloDatos.ordenCompra);
		$('#unidadMedidaTab').val(arregloDatos.unidadMedida);
		$('#porcentajeTab').val(parseFloat(arregloDatos.porcentaje).toFixed(2));
		$('#cantidadTab').val(arregloDatos.cantidad);
		$('#codigoProductoTab').focus();
		$('#codigoProductoTab').click();
		$('#codigoProductoTab').select();
		if(arregloDatos.envia){
			$('#enviaTab').val('1');
		}else{
			$('#enviaTab').val('0');
		}
	}
	function limpiarDatosProductoTab(){
		$('#codigoProductoTab').val('');
		$('#descripcionTab').val('');
		$('#medidaTab').val('');
		$('#descuentoMaximoTab').val('');
		$('#precioTab').val('');
		$('#disponibleTab').val('');
		$('#descuentoTab').val('');
		$('#importeTab').val('');
		$('#esKitTab').val('');
		$('#bodegaTab').val('00');
		$('#esDfTab').val('');
		$('#correlativoKitTab').val('');
		$('#promocionTab').val('');
		$('#codigoPromocionTab').val('');
		$('#ordenCompraTab').val('');
		$('#unidadMedidaTab').val('');
		$('#porcentajeTab').val('');
		$('#enviaTab').val('0');
		$('#cantidadTab').val('');
		$('#codigoProductoTab').focus();
		$('#codigoProductoTab').click();
		$('#codigoProductoTab').select();
	}
	function validaPermisos(usuario,clave,operacion){
		return $.ajax({
		      url: "ValidaPermisos",
		      method: "POST",
		      dataType: 'json',
		      data: {
		    	  usuario : usuario,
		    	  clave: clave,
		    	  operacion: operacion
		      },
		      error: function(data) {
		    	  console.log(JSON.stringify(data));
				   var obj = JSON.parse(JSON.stringify(data));
				   notificacionError(obj.responseText);
			  },
		    });
	}
	function cargarTablaBusquedaClientes(criterioBusquedaClientes, textoBusquedaClientes){
		return $.ajax({
		      url: "CargarBusquedaClientes",
		      method: "POST",
		      dataType: 'json',
		      data: {
		    	  criterioBusquedaClientes : criterioBusquedaClientes,
		    	  textoBusquedaClientes: textoBusquedaClientes
		      },
		      error: function(data) {
		    	  console.log(JSON.stringify(data));
				   var obj = JSON.parse(JSON.stringify(data));
				   notificacionError(obj.responseText);
			  },
		    });
	}
	function validacionCamposLLenos(idCampo, mensajeError){
		if($('#'+idCampo).val()==''){
			$('#txtNotificacionValidaPermisos').text(mensajeError);
			$('#'+idCampo).focus()
			$('#'+idCampo).click()
			$('#'+idCampo).select()
			return true;
		}else{
			return false;
		}
	}
	function ajaxRequest(params) {
        // data you need
        console.log(params.data);
        // just use setTimeout
        setTimeout(function () {
            params.success({
                total: 100,
                rows: [{
                    "idPago": 0,
                    "descripcion": "Item 0",
                    "price": "$0"
                }]
            });
            // hide loading
            params.complete();
        }, 1000);
    }
	
	function cargarTablaBusquedaProductos2(criterioBusqueda, textoBusqueda, codigoPago){
		return $.ajax({
		      url: "CargarBusquedaProductos",
		      method: "POST",
		      dataType: 'json',
		      data: {
		    	  criterioBusqueda : criterioBusqueda,
		    	  textoBusqueda: textoBusqueda,
		    	  codigoPago: codigoPago
		      },
		      error: function(data) {
		    	  console.log(JSON.stringify(data));
				   var obj = JSON.parse(JSON.stringify(data));
				   notificacionError(obj.responseText);
			  },
		    });
	}
	function cargarTablaBusquedaProductos(criterioBusqueda, textoBusqueda, codigoPago){
		$('#busquedaProductosTabla').bootstrapTable({
		    method: 'POST',
		    dataType: 'json',
			url: 'CargarBusquedaProductos',
		    contentType: 'application/x-www-form-urlencoded',
		    queryParams: function(p) {
		    	p.criterioBusqueda = criterioBusqueda;
		    	p.textoBusqueda = textoBusqueda;
		    	p.codigoPago = codigoPago;
		    	return p;
		    },
		    search: true,
		    pagination: true,
		    showRefresh: true,
		    height: 400,
		    pageSize: 25,
		    pageList: [25,50,75,100],
		    sortable: true,
		    columns: [{
		        field: 'codigoProducto',
		        title: 'Codigo'
		    },{
		        field: 'bodega',
		        title: 'Bodega'
		    },{
		        field: 'disponible',
		        title: 'Disponible'
		    },{
		        field: 'precio',
		        title: 'Precio'
		    },{
		        field: 'descripcionProducto',
		        title: 'descripcion'
		    },{
		        field: 'backOrder',
		        title: 'Bck. Ord.'
		    },{
		        field: 'fechaEsperado',
		        title: 'F. Esp.'
		    },{
		        field: 'transito',
		        title: 'Transito'
		    },{
		        field: 'Df',
		        title: 'DF'
		    }
		    ]
		});
	}
	}));